package cz.muni.fi.pv243.music.library.dao.impl;

import cz.muni.fi.pv243.music.library.dao.*;
import cz.muni.fi.pv243.music.library.dao.qualifier.*;
import cz.muni.fi.pv243.music.library.entity.*;
import org.hibernate.search.jpa.*;
import org.hibernate.search.query.dsl.*;

import javax.enterprise.context.*;
import javax.persistence.*;
import javax.transaction.*;
import java.util.*;

/**
 * Created by mstyk on 4/14/17.
 */
@JpaDAO
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
