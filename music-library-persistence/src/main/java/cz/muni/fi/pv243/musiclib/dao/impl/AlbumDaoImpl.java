package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.AlbumDAO;
import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.util.LuceneQueryUtil;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class AlbumDaoImpl extends GenericDaoImpl<Album, Long> implements AlbumDAO {

    public AlbumDaoImpl() {
        super(Album.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Album> searchByTitle(String titleFragment) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(LuceneQueryUtil
                .createFuzzyFieldQuery(fullTextEntityManager, Album.class, "title", titleFragment));

        return jpaQuery.getResultList();
    }

    @Override
    public List<Album> getAlbumSample(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be a possitive number");
        }

        TypedQuery<Album> query = em.createQuery("SELECT a FROM Album a ORDER BY RANDOM()", Album.class);
        query.setMaxResults(count);
        return query.getResultList();
    }
}
