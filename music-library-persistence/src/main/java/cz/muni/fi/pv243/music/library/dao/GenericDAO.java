package cz.muni.fi.pv243.music.library.dao;

import java.util.*;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface GenericDAO<T, U> {

    T create(final T t);

    void remove(U id);

    T find(U id);

    List<T> findAll();

    T update(final T t);

}
