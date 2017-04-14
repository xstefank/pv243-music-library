package cz.muni.fi.pv243.music.library.service;

import cz.muni.fi.pv243.music.library.entity.Album;
import cz.muni.fi.pv243.music.library.entity.Artist;
import cz.muni.fi.pv243.music.library.entity.Genre;
import cz.muni.fi.pv243.music.library.entity.Song;

import java.util.List;

/**
 * Created by mstyk on 4/14/17.
 *
 * Application name	Music Library
 API key	0af72b19227c2a416d268f2fb768218b
 Shared secret	19f5b60f2a6c29639e53a7bcb7277567
 Registered to	madki81
 */
public interface ArtistService {

    /**
     * Created new artist.
     *
     * @param artist entity to be created
     */
    Artist create(Artist artist);

    /**
     * Updates artist.
     *
     * @param artist entity to be updated
     * @return updated artist entity
     */
    Artist update(Artist artist);

    /**
     * Removes artist.
     *
     * @param artist entity to be removed
     * */
    void remove(Artist artist);

    /**
     * Get artist entity by unique ID.
     *
     * @param id id of artist entity
     * @return artist entity with given id, null if no artist with this id exists
     */
    Artist findById(Long id);

    /**
     * Returns all artists.
     *
     * @return list of all artists
     */
    List<Artist> findAll();

    /**
     * Get detailed biography of artist
     *
     * @param artist artists entity
     * @return artists bio
     */
    String fetchArtistsBio(Artist artist);

}
