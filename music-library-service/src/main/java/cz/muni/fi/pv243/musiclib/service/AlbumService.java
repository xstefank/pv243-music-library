package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.service.generic.GenericCRUDService;

import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface AlbumService extends GenericCRUDService<Album, Long> {

    /**
     * Returns valid {@link Album} object for the given title
     *
     * @param title search string
     * @return matched {@link Album} instances or empty list
     */
    List<Album> searchByTitle(String title);

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
