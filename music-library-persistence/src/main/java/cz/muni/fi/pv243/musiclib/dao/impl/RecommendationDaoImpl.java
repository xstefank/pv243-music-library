package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.RecommendationDao;
import cz.muni.fi.pv243.musiclib.dao.SongDao;
import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class RecommendationDaoImpl extends GenericDaoImpl<Recommendation, Long> implements RecommendationDao, Serializable {

    public RecommendationDaoImpl() {
        super(Recommendation.class);
    }

    @Inject
    private SongDao songDao;

    @Override
    public List<Recommendation.Aggregate> getMostRecommended(LocalDateTime from, LocalDateTime to, int count) {
        List<Recommendation.Aggregate> topList = new ArrayList<>();

        Query query = em.createNativeQuery(
                "select r.song_id, count(r.user_id) from Recommendation r where r.time between :start and :end GROUP BY r.song_id ORDER BY count(r.user_id) DESC, r.time DESC");
        query.setParameter("start", from);
        query.setParameter("end", to);

        List<Object[]> list = query.getResultList();

        int iterations = Math.min(count, list.size());

        for (int i = 0; i < iterations; i++) {
            Object[] result = list.get(i);
            Song song = songDao.find(((BigInteger) result[0]).longValue());

            TypedQuery<User> userQuery = em.createQuery("SELECT r.user FROM Recommendation r WHERE r.song = :song and r.time between :start and :end",
                    User.class)
                    .setParameter("song", song)
                    .setParameter("start", from)
                    .setParameter("end", to);
            List<User> users = userQuery.getResultList();

            topList.add(new Recommendation.Aggregate(song, users));
        }

        return Collections.unmodifiableList(topList);
    }
}
