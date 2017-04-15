package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.ArtistDao;
import cz.muni.fi.pv243.musiclib.entity.Artist;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
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
