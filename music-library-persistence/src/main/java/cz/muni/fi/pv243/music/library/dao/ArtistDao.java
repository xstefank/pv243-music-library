package cz.muni.fi.pv243.music.library.dao;

import cz.muni.fi.pv243.music.library.entity.*;

import java.util.*;

/**
 * Created by mstyk on 4/14/17.
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