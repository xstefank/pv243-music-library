package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;

import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface SongDao extends GenericDAO<Song, Long> {

    List<Song> searchByTitle(String titleFragment);

    List<Song> findByAlbum(Album album);

    List<Song> findByArtist(Artist artist);

    List<Song> findByGenre(Genre genre);
}
