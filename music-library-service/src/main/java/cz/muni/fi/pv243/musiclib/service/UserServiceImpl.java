package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.UserDao;
import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.logging.LogMessages;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        User created = userDao.create(user);

        LogMessages.LOGGER.logUserCreated(created);
        return created;
    }

    @Override
    public User update(User user) {
        User updated = userDao.update(user);

        LogMessages.LOGGER.logUserUpdated(user);
        return updated;
    }

    @Override
    public void remove(User user) {
        userDao.remove(user.getId());
        LogMessages.LOGGER.logUserRemoved(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.find(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}