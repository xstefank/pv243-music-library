package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Song;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class RecommendationServiceImpl implements RecommendationService {

    @Resource(lookup = "java:jboss/exported/jms/queue/RecommendedSongs")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    @Override
    public void recommend(Song song) {
        ObjectMessage messagePayload = jmsContext.createObjectMessage(song);
        jmsContext.createProducer().send(queue, messagePayload);
        System.out.println("Song " + song.getTitle() + "will be recommended!");
    }

}
