package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.RecommendationDao;
import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.logging.MusicLibLogger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class RecommendationServiceImpl implements RecommendationService {

    @Resource(lookup = RecommendationNotifierMDB.NOTIFICATION_TOPIC_JNDI)
    private Topic topic;

    @Inject
    private JMSContext jmsContext;

    @Inject
    private RecommendationDao recommendationDao;

    @Inject
    private SongService songService;

    @Inject
    private UserService userService;

    @Override
    public void recommend(Long songId, String userName) {

        Song song = songService.findById(songId);
        User user = userService.findByEmail(userName);
        MusicLibLogger.LOGGER.info("Recommending song " + song.getTitle() + " with username " + userName);

        Recommendation recommendation = new Recommendation();
        recommendation.setSong(song);
        recommendation.setUser(user);
        recommendation.setTime(LocalDateTime.now(Clock.systemUTC()));

        create(recommendation);

        jmsContext.createProducer().send(topic, jmsContext.createMessage());
    }

    @Override
    public List<Recommendation.Aggregate> getTopTenMostRecommendedLastDay() {
        return getMostRecommended(
                LocalDateTime.now(Clock.systemUTC()).minusDays(1),
                LocalDateTime.now(Clock.systemUTC()),
                10);
    }

    @Override
    public List<Recommendation.Aggregate> getMostRecommended(LocalDateTime from, LocalDateTime to, int count) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Timeframe is invalid from is before to");
        }
        return recommendationDao.getMostRecommended(from, to, count);
    }

    @Override
    public Recommendation create(Recommendation entity) {
        if (entity.getTime() == null) {
            entity.setTime(LocalDateTime.now(Clock.systemUTC()));
        }
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
