package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.RecommendationDao;
import cz.muni.fi.pv243.musiclib.entity.Recommendation;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class RecommendationServiceImpl implements RecommendationService {

    @Resource(lookup = RecommendationMDB.RECOMMENDED_SONGS_QUEUE_JNDI)
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    @Inject
    private RecommendationDao recommendationDao;

    @Override
    public void recommend(Long songId, String userName) {
        Message message = jmsContext.createMessage();
        try {
            message.setLongProperty(RecommendationMDB.SONG_ID_PROPERTY, songId);
            message.setStringProperty(RecommendationMDB.USER_NAME_PROPERTY, userName);

        } catch (JMSException e) {
            e.printStackTrace();
            return;
        }
        jmsContext.createProducer().send(queue, message);
        System.out.println("Song with id " + songId + "will be recommended!");
    }

    @Override
    public Recommendation create(Recommendation entity) {
        return recommendationDao.create(entity);
    }

    @Override
    public Recommendation update(Recommendation entity) {
        return recommendationDao.update(entity);
    }

    @Override
    public void remove(Recommendation entity) {
        recommendationDao.remove(entity.getId());
    }

    @Override
    public Recommendation findById(Long id) {
        return recommendationDao.find(id);
    }

    @Override
    public List<Recommendation> findAll() {
        return recommendationDao.findAll();
    }
}
