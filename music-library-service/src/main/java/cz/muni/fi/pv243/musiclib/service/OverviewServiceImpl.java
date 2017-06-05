package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class OverviewServiceImpl implements OverviewService {

    @Inject
    private ArtistService artistService;

    @Inject
    private SongService songService;

    @Inject
    private AlbumService albumService;

    @Inject
    private GenreService genreService;

    public Map<Artist, Integer> getArtistNumberSongs() {
        List<Artist> artistList = artistService.findAll();

        Map<Artist, Integer> stats = new HashMap<>(artistList.size());

        for(Artist artist : artistList){
            List<Song> songs = songService.searchByArtist(artist);
            stats.put(artist, songs.size());
        }

        return stats;
    }

    public Map<Artist, Integer> getArtistNumberAlbums() {
        List<Artist> artistList = artistService.findAll();

        Map<Artist, Integer> stats = new HashMap<>(artistList.size());

        for(Artist artist : artistList){
            List<Album> albums = albumService.searchByArtist(artist);
            stats.put(artist, albums.size());
        }

        return stats;
    }

    public Map<Genre, Integer> getGenreNumberSongs() {
        List<Genre> genres = genreService.findAll();

        Map<Genre, Integer> stats = new HashMap<>(genres.size());

        for(Genre genre : genres){
            List<Song> songs = songService.searchByGenre(genre);
            stats.put(genre, songs.size());
        }

        return stats;
    }
}
