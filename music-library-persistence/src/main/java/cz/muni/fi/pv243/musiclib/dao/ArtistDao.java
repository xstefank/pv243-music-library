package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Artist;

import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface ArtistDao extends GenericDAO<Artist, Long> {

    /**
     * Returns all artists containing given artist name fragment in their name
     *
     * @param artistNameFragment fragment of artist name to look for
     * @return list of all musician entities containing given artist name fragment, empty if no such musician exists
     */
    List<Artist> searchByArtistName(String artistNameFragment);

}
