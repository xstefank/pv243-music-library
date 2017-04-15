package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.service.generic.GenericCRUDService;

import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface ArtistService extends GenericCRUDService<Artist, Long> {

    /**
     * Returns {@link Artist} for the given name
     * @param name search string
     * @return list of valid {@link Artist} or empty list
     */
    List<Artist> searchByName(String name);

    /**
     * Get detailed biography of artist
     *
     * @param artist artists entity
     * @return artists bio
     */
    String fetchArtistsBio(Artist artist);

    /**
     * Retrieve the list of {@link Album} for the album
     * @param id album id
     * @return list of {@link Album} for artist or empty list
     */
    List<Album> getAlbumsForId(Long id);

}
