package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface SongDao extends GenericDao<Song, Long> {

    List<Song> searchByTitle(@NotNull String titleFragment);

    List<Song> searchByAlbum(@NotNull Album album);

    List<Song> searchByArtist(@NotNull Artist artist);

    List<Song> searchByGenre(@NotNull Genre genre);
}
