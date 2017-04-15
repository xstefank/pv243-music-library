package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;

import java.util.List;

/**
 * Created by mstyk on 4/14/17.
 */
public interface AlbumService {

    /**
     * Created new album.
     *
     * @param album entity to be created
     */
    Album create(Album album);

    /**
     * Updates album.
     *
     * @param album entity to be updated
     * @return updated album entity
     */
    Album update(Album album);

    /**
     * Removes album.
     *
     * @param album entity to be removed
     * @throws IllegalArgumentException if passed album is null or is not stored in DB
     */
    void remove(Album album) throws IllegalArgumentException;

    /**
     * Get album entity by unique ID.
     *
     * @param id id of album entity
     * @return album entity with given id, null if no album with this id exists
     */
    Album findById(Long id);

    /**
     * Returns all albums containing given title fragment
     *
     * @param title title fragment of albums to look for
     * @return list of all album entities containing given title fragment, empty if no such album exists
     */
    List<Album> searchByTitle(String title);

    /**
     * Returns all albums.
     *
     * @return list of all albums
     */
    List<Album> findAll();

    /**
     * Adds a new song to the album
     *
     * @param album album to which the song is to be added
     * @param song  song to be added
     */
    void addSong(Album album, Song song);

    /**
     * Removes the song from the album
     *
     * @param album album from to which the song belongs
     * @param song  song to be removed
     */
    void removeSong(Album album, Song song);

    /**
     * Retrieves the genre in given album
     *
     * @param album album to be examined
     */
    List<Genre> getGenresForAlbum(Album album);

    /**
     * Returns the sample of albums stored in the DB. Returns up
     * to count albums or less if there is no sufficient ammount.
     *
     * @param count number of albums to return
     * @return list of sample albums
     */
    List<Album> getAlbumSample(int count);
}
