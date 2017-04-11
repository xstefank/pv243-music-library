package cz.muni.fi.pv243.music.library.dao;

import java.util.Collection;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface GenericDAO<T, U> {

    void create(T t);

    void remove(U id);

    T find(U id);

    Collection<T> findAll();

    T update(T t);

}
