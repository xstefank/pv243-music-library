package cz.muni.fi.pv243.music.library.dao.impl;

import cz.muni.fi.pv243.music.library.dao.*;
import cz.muni.fi.pv243.music.library.dao.qualifier.*;
import cz.muni.fi.pv243.music.library.entity.*;

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
