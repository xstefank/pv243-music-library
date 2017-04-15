package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Genre;

import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface GenreDAO extends GenericDAO<Genre, Long> {

    /**
     * Returns all genres containing given title fragment in their title
     *
     * @param titleFragment title fragment of genre to look for
     * @return list of all genre entities containing given title fragment , empty if no such album exists
     */
    List<Genre> searchByTitle(String titleFragment);
}
