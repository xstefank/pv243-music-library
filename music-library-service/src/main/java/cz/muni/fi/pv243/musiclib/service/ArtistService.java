package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Artist;

import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface ArtistService {

    Artist create(Artist artist);

    Artist update(Artist artist);

    void remove(Artist artist);

    Artist findById(Long id);

    List<Artist> findAll();

    /**
     * Get detailed biography of artist
     *
     * @param artist artists entity
     * @return artists bio
     */
    String fetchArtistsBio(Artist artist);

}
