package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.logging.LogMessages;
import cz.muni.fi.pv243.musiclib.qualifier.RecommendationMessage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;

import static cz.muni.fi.pv243.musiclib.service.RecommendationNotifierMDB.NOTIFICATION_TOPIC;
import static cz.muni.fi.pv243.musiclib.service.RecommendationNotifierMDB.NOTIFICATION_TOPIC_JNDI;

/**
 * This MDB serves as a point of cluster communication. When new Recommendation is created, message is send to
 * notification topic. This is done in RecommendationServiceImpl.recommend. RecommendationNotifierMDB reads this
 * message on every server in cluster.
 * Thanks to this, websocket client is notified about change in recommendations even if the change was done on cluster
 * node which doesn't hold clients connection
 * <p>
 * When new top recommendation are processed, recommendation event is fired
 *
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = NOTIFICATION_TOPIC_JNDI,
                interfaceName = "javax.jms.Topic",
                destinationName = NOTIFICATION_TOPIC
        )
})
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType",
                        propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName = "destination",
                        propertyValue = NOTIFICATION_TOPIC_JNDI),
                @ActivationConfigProperty(propertyName = "subscriptionName",
                        propertyValue = "RecommendationNotifierMDB")
        })
public class RecommendationNotifierMDB implements MessageListener {

    public static final String NOTIFICATION_TOPIC = "RecommendationNotifications";
    public static final String NOTIFICATION_TOPIC_JNDI = "java:jboss/exported/jms/topic/" + NOTIFICATION_TOPIC;

    @Inject
    @RecommendationMessage
    private Event<List<Recommendation.Aggregate>> recommendEvent;

    @Inject
    private RecommendationService recommendationService;

    @Override
    public void onMessage(Message msg) {
        List<Recommendation.Aggregate> topTen = recommendationService.getTopTenMostRecommendedLastDay();

        LogMessages.LOGGER.logFireReccomendEvent();
        recommendEvent.fire(topTen);
    }

}
