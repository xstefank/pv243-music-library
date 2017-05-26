package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.service.generic.GenericCRUDService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface RecommendationService extends GenericCRUDService<Recommendation, Long> {

    /**
     * Recommend song with songId as a user userName
     * Sends message to Recommendation Notification topic for further processing
     */
    void recommend(Long songId, String userName);


    /**
     * Return the most recommended song in given time frame
     *
     * @return top ten most recommended songs with list of users recommending given song in given time frame,
     * ordered from most recommended song to least recommended song
     */
    List<Recommendation.Aggregate> getTopTenMostRecommendedLastDay();

    /**
     * Return the most recommended song in given time frame
     *
     * @param from  start of time frame in UTC
     * @param to    end of time frame in UTC
     * @param count number of results
     * @return most recommended songs with list of users recommending given song in given time frame,
     * ordered from most recommended song to least recommended song
     */
    List<Recommendation.Aggregate> getMostRecommended(LocalDateTime from, LocalDateTime to, int count);

}
