package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.service.generic.GenericCRUDService;

import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface GenreService extends GenericCRUDService<Genre, Long> {

    /**
     * Returns list of {@link Genre} instances matching given title
     *
     * @param title search string
     * @return list of valid {@link Genre} instance or empty list
     */
    List<Genre> searchByTitle(String title);
}
