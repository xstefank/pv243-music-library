package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.GenreDAO;
import cz.muni.fi.pv243.musiclib.entity.Genre;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by mstyk on 4/14/17.
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class GenreDaoImpl extends GenericDaoImpl<Genre, Long> implements GenreDAO {

    public GenreDaoImpl() {
        super(Genre.class);
    }

    @Override
    public List<Genre> searchByTitle(String titleFragment) {
        if (titleFragment == null) {
            throw new IllegalArgumentException("titleFragment");
        }
        TypedQuery<Genre> q = em.createQuery("SELECT g FROM Genre g WHERE UPPER(g.title) LIKE '%'||:titleFragment||'%'", Genre.class)
                .setParameter("titleFragment", titleFragment.toUpperCase());
        return q.getResultList();
    }
}
