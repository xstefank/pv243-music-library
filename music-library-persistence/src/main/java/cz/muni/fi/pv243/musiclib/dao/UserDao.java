package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.User;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface UserDao extends GenericDao<User, Long> {

    User findByEmail(@NotNull String email);
}
