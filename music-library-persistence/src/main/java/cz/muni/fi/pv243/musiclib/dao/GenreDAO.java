package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Genre;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface GenreDAO extends GenericDAO<Genre, Long> {

    /**
     * Returns all {@link Genre} containing the given title fragment in their title
     *
     * @param titleFragment title fragment of the {@link Genre} to look for
     * @return list of valid {@link Genre} entities or empty list
     */
    List<Genre> searchByTitle(@NotNull String titleFragment);
}
