package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.service.generic.GenericCRUDService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface RecommendationService extends GenericCRUDService<Recommendation, Long> {

    /**
     * Recommend song with songId as a user userName
     */
    void recommend(Long songId, String userName);


    /**
     * Return the most recommended song in given time frame
     *
     * @return top ten most recommended songs with list of users recommending given song in given time frame,
     * ordered from most recommended song to least recommended song
     */
    Map<Song, List<User>> getTopTenMostRecommendedLastDay();

    /**
     * Return the most recommended song in given time frame
     *
     * @param from  start of time frame in UTC
     * @param to    end of time frame in UTC
     * @param count number of results
     * @return most recommended songs with list of users recommending given song in given time frame,
     * ordered from most recommended song to least recommended song
     */
    Map<Song, List<User>> getMostRecommended(LocalDateTime from, LocalDateTime to, int count);

}
