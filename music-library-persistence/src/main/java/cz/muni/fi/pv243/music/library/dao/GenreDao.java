package cz.muni.fi.pv243.music.library.dao;

import cz.muni.fi.pv243.music.library.entity.Genre;

import java.util.List;

/**
 * Created by mstyk on 4/11/17.
 */
public interface GenreDao {


    /**
     * Creates genre and persists it to database
     *
     * @param genre entity to be persisted
     */
    void create(Genre genre);

    /**
     * Updates genre in database
     *
     * @param genre entity to be updated
     * @return updated persisted entity
     */
    Genre update(Genre genre);

    /**
     * Removes entity from persistence
     *
     * @param genre entity to be removed
     * @throws IllegalArgumentException if passed genre is null or is not stored in DB
     */
    void remove(Genre genre);

    /**
     * Get genre by unique id
     *
     * @param id of entity
     * @return object with given id
     */
    Genre findById(Long id);

    /**
     * Returns all genres containing given title fragment in their title
     *
     * @param titleFragment title fragment of genres to look for
     * @return list of all genre entities containing given title fragment , empty if no such genre exists
     */
    List<Genre> searchByTitle(String titleFragment);

    /**
     * Get all genres
     *
     * @return list of all genres
     */
    List<Genre> findAll();

}
