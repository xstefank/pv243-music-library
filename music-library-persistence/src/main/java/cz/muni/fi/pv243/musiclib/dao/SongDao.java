package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;

import java.util.List;

/**
 * Created by mstyk on 4/14/17.
 */
public interface SongDao extends GenericDAO<Song, Long> {

    /**
     * Returns all songs that contain given title fragment in their title.
     *
     * @param titleFragment fragment of the title to look for
     * @return list of songs containing given fragment in their title
     */
    List<Song> searchByTitle(String titleFragment);

    /**
     * Returns all songs for the given album
     *
     * @param album album to which songs belong
     * @return list of Song entities in the given album
     */
    List<Song> findByAlbum(Album album);

    /**
     * Return all songs for the given musician
     *
     * @param artist musician to whom song belong
     * @return all Song entities for the given musician
     * @throws IllegalArgumentException if musician is null
     */
    List<Song> findByMusician(Artist artist);

    /**
     * Returns all songs for the given genre
     *
     * @param genre genre to which songs belong
     * @return all Song entities for the given genre
     * @throws IllegalArgumentException if the genre is null
     */
    List<Song> findByGenre(Genre genre);
}
