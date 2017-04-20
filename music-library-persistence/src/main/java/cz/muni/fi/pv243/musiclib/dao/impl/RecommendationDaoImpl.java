package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.RecommendationDao;
import cz.muni.fi.pv243.musiclib.entity.Recommendation;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class RecommendationDaoImpl extends GenericDaoImpl<Recommendation, Long> implements RecommendationDao, Serializable {

    public RecommendationDaoImpl() {
        super(Recommendation.class);
    }
}
