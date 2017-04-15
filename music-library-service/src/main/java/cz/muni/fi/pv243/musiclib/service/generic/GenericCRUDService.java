package cz.muni.fi.pv243.musiclib.service.generic;

import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 *
 * @param <T> entity type for the CRUD operations
 * @param <U> identification type of the entity
 */
public interface GenericCRUDService<T, U> {

    T create(T entity);

    T update(T entity);

    void remove(T entity);

    T findById(U id);

    List<T> findAll();
}
