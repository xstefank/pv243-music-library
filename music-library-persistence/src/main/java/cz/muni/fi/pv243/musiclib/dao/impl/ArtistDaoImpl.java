package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.ArtistDao;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.util.LuceneQueryUtil;
import org.hibernate.search.jpa.FullTextEntityManager;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class ArtistDaoImpl extends GenericDaoImpl<Artist, Long> implements ArtistDao {

    public ArtistDaoImpl() {
        super(Artist.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Artist> searchByName(String artistNameFragment) {
        if (artistNameFragment == null) {
            throw new IllegalArgumentException("artistNameFragment cannot be null.");
        }

        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(LuceneQueryUtil
                .createFuzzyFieldQuery(fullTextEntityManager, Artist.class, "name"));

        return jpaQuery.getResultList();
    }
}
