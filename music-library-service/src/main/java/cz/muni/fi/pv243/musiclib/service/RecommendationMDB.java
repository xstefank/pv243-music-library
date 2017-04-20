package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.qualifier.RecommendationMessage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import static cz.muni.fi.pv243.musiclib.service.RecommendationMDB.RECOMMENDED_SONGS_QUEUE;
import static cz.muni.fi.pv243.musiclib.service.RecommendationMDB.RECOMMENDED_SONGS_QUEUE_JNDI;

/**
 * Message driven bean which reads messages from RecommendedSongs queue and process new recommendation of song.
 * Recommendation messages are produced in @link{RecommendationServiceImpl.recommend} and handled async here in MDB.
 *
 * When new recommendation is processed, event is fired
 *
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = RECOMMENDED_SONGS_QUEUE_JNDI,
                interfaceName = "javax.jms.Queue",
                destinationName = RECOMMENDED_SONGS_QUEUE
        )
})
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination",
                        propertyValue = RECOMMENDED_SONGS_QUEUE_JNDI)
        })
public class RecommendationMDB implements MessageListener {

    public static final String RECOMMENDED_SONGS_QUEUE = "RecommendedSongs";
    public static final String RECOMMENDED_SONGS_QUEUE_JNDI = "java:jboss/exported/jms/queue/" + RECOMMENDED_SONGS_QUEUE;
    public static final String SONG_ID_PROPERTY = "songId";
    public static final String USER_NAME_PROPERTY = "userId";

    @Inject
    @RecommendationMessage
    private Event<Song> recommendEvent;

    @Inject
    private SongService songService;

    @Inject
    private UserService userService;

    @Inject
    private RecommendationService recommendationService;

    @Override
    public void onMessage(Message msg) {

        try {
            Long songId = msg.getLongProperty(SONG_ID_PROPERTY);
            Song song = songService.findById(songId);
            String userName = msg.getStringProperty(USER_NAME_PROPERTY);
            User user = userService.findByEmail(userName);

            System.out.println("onMessage song " + song.getTitle() + " with username " + userName );

            Recommendation recommendation = new Recommendation();
            recommendation.setSong(song);
            recommendation.setUser(user);

            recommendationService.create(recommendation);

            recommendEvent.fire(song);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
