package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Recommendation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface RecommendationDao extends GenericDao<Recommendation, Long> {

    /**
     * Returns given number of most recommended songs in given time frame
     *
     * @param from  start of time frame
     * @param to    end of time frame
     * @param count
     * @return list entries with users recommending given song in given time frame, ordered from most recommended song to least
     * recommended song
     */
    List<Recommendation.Aggregate> getMostRecommended(LocalDateTime from, LocalDateTime to, int count);

}
