package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Artist;

import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface ArtistDao extends GenericDAO<Artist, Long> {

    /**
     * Returns all artists containing given artist name fragment in their name
     *
     * @param artistNameFragment fragment of the artist name to look for
     * @return list of {@code {@link Artist}} valid entities or empty list
     */
    List<Artist> searchByName(String artistNameFragment);

}
