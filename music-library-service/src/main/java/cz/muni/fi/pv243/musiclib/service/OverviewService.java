package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;

import java.util.Map;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface OverviewService {

    Map<Artist, Integer> getArtistNumberSongs();

    Map<Artist, Integer> getArtistNumberAlbums();

    Map<Genre, Integer> getGenreNumberSongs();

}
