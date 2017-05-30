package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.service.generic.GenericCRUDService;

import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface SongService extends GenericCRUDService<Song, Long> {

    /**
     * Retrieves {@link Song} instances with the given title
     */
    List<Song> searchByTitle(String title);

    /**
     * Returns {@link Song} instances for the given {@link Album}
     */
    List<Song> searchByAlbum(Album album);

    /**
     * Returns {@link Song} instances for the given {@link Artist}
     */
    List<Song> searchByArtist(Artist artist);

    /**
     * Retrieves the {@link Song} in the given {@link Genre}
     */
    List<Song> searchByGenre(Genre genre);

    /**
     * Returns the {@link Album} for the given {@link Song}
     */
    Album getAlbumForId(Long id);

    /**
     * Returns the {@link Artist} for the given {@link Song}
     */
    Artist getArtistForId(Long id);

    /**
     * Returns the {@link Genre} for the given {@link Song}
     */
    Genre getGenreForId(Long id);


    /**
     * Retrieves {@link Song} for the given {@link User}
     */
    List<Song> findSongsByUserID(Long userId);

    /**
     * Adds {@link Song} to user's library
     */
    Boolean addSongToUserLib(Long songId, Long userId);
}