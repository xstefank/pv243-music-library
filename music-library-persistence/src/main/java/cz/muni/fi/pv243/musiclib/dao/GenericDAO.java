package cz.muni.fi.pv243.musiclib.dao;

import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 *
 * @param <T> entity type
 * @parem <U> identificator type
 */
public interface GenericDAO<T, U> {

    T create(final T entity);

    T update(final T entity);

    void remove(U id);

    T find(U id);

    List<T> findAll();

}
