package cz.muni.fi.pv243.music.library.dao.impl;

import cz.muni.fi.pv243.music.library.dao.*;
import cz.muni.fi.pv243.music.library.dao.qualifier.*;
import cz.muni.fi.pv243.music.library.entity.*;

import javax.enterprise.context.*;
import javax.transaction.*;
import java.util.*;

/**
 * Created by mstyk on 4/14/17.
 */
@JpaDAO
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class ArtistDaoImpl extends GenericDaoImpl<Artist, Long> implements ArtistDao {

    public ArtistDaoImpl() {
        super(Artist.class);
    }

    @Override
    public List<Artist> searchByArtistName(String artistNameFragment) {
        if (artistNameFragment == null) {
            throw new IllegalArgumentException("artistNameFragment cannot be null.");
        }
        return em.createQuery("SELECT m FROM Musician m WHERE UPPER(m.artistName) LIKE '%'||:artistNameFragment||'%'", Artist.class)
                .setParameter("artistNameFragment", artistNameFragment.toUpperCase()).getResultList();
    }
}
