package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.GenreDAO;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.util.LuceneQueryUtil;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class GenreDaoImpl extends GenericDaoImpl<Genre, Long> implements GenreDAO, Serializable {

    public GenreDaoImpl() {
        super(Genre.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Genre> searchByTitle(@NotNull String titleFragment) {
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);

        javax.persistence.Query jpaQuery = ftem.createFullTextQuery(LuceneQueryUtil
                .createFuzzyFieldQuery(ftem, Genre.class, "title", titleFragment));

        return jpaQuery.getResultList();
    }
}
