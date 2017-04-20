package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Song;
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

    @Inject
    @RecommendationMessage
    private Event<Song> recommendEvent;

    @Inject
    private SongService songService;

    @Override
    public void onMessage(Message msg) {

        try {
            Long songId = msg.getLongProperty(SONG_ID_PROPERTY);
            Song song = songService.findById(songId);

            System.out.println("onMessage song " + song.getTitle());
            recommendEvent.fire(song);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
