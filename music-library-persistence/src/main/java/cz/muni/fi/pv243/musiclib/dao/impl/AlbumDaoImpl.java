package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.AlbumDAO;
import cz.muni.fi.pv243.musiclib.entity.Album;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by mstyk on 4/14/17.
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class AlbumDaoImpl extends GenericDaoImpl<Album, Long> implements AlbumDAO {

    public AlbumDaoImpl() {
        super(Album.class);
    }

    @Override
    public List<Album> searchByTitle(String titleFragment) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Album.class).get();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .fuzzy()
                .withPrefixLength(1)
                .withEditDistanceUpTo(2)
                .onFields("title")
                .matching(titleFragment)
                .createQuery();

        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Album.class);

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
