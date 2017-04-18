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

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = "java:jboss/exported/jms/queue/RecommendedSongs",
                interfaceName = "javax.jms.Queue",
                destinationName = "RecommendedSongs"
        )
})
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination",
                        propertyValue = "java:jboss/exported/jms/queue/RecommendedSongs")
        })
public class RecommendationMDB implements MessageListener {

    @Inject
    @RecommendationMessage
    Event<Song> recommendEvent;

    @Override
    public void onMessage(Message msg) {

        try {
            Song song = msg.getBody(Song.class);
            //TODO : to make sense, we should do something here - something with DB?
            System.out.println("onMessage song " + song.getTitle());
            recommendEvent.fire(song);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
