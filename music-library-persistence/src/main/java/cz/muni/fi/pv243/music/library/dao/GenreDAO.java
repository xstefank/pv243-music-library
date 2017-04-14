package cz.muni.fi.pv243.music.library.dao;

import cz.muni.fi.pv243.music.library.entity.*;

import java.util.*;

/**
 * Created by mstyk on 4/14/17.
 */
public interface GenreDAO extends GenericDAO<Genre, String> {

    /**
     * Returns all genres containing given title fragment in their title
     *
     * @param titleFragment title fragment of genre to look for
     * @return list of all genre entities containing given title fragment , empty if no such album exists
     */
    List<Genre> searchByTitle(String titleFragment);
}
