package cz.muni.fi.pv243.music.library.dao;

import cz.muni.fi.pv243.music.library.entity.*;

/**
 * Created by mstyk on 4/14/17.
 */
public interface UserDao extends GenericDAO<User, Long> {
    /**
     * Returns all Users with given email
     *
     * @return all persisted User with given email entities
     */
    User findByEmail(String email);
}
