package cz.muni.fi.pv243.musiclib.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class RecommendationServiceImpl implements RecommendationService {

    @Resource(lookup = RecommendationMDB.RECOMMENDED_SONGS_QUEUE_JNDI)
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    @Override
    public void recommend(Long songId) {
        Message message = jmsContext.createMessage();
        try {
            message.setLongProperty(RecommendationMDB.SONG_ID_PROPERTY, songId);
        } catch (JMSException e) {
            e.printStackTrace();
            return;
        }
        jmsContext.createProducer().send(queue, message);
        System.out.println("Song with id " + songId + "will be recommended!");
    }

}
