package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.UserDao;
import cz.muni.fi.pv243.musiclib.entity.User;

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
    public User create(User entity) {
        return userDao.create(entity);
    }

    @Override
    public User update(User entity) {
        return userDao.update(entity);
    }

    @Override
    public void remove(User entity) {
        userDao.remove(entity.getId());
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