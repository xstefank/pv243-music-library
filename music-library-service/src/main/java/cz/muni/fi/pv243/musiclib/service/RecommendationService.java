package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.service.generic.GenericCRUDService;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface RecommendationService extends GenericCRUDService<Recommendation, Long> {

    void recommend(Long songId, String userName);

}
