package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
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
     * Returns valid {@link Album} objects for the given artist
     *
     * @param artist search artist
     * @return matched {@link Album} instances or empty list
     */
    List<Album> searchByArtist(Artist artist);

    /**
     * Returns the sample of albums stored in the DB. Returns up
     * to count albums or less if there is no sufficient ammount.
     *
     * @param count number of albums to return
     * @return list of sample albums
     */
    List<Album> getAlbumSample(int count);
}
