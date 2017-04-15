package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.UserDao;
import cz.muni.fi.pv243.musiclib.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * Created by mstyk on 4/14/17.
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :email",
                User.class).setParameter("email", email);
        return q.getSingleResult();
    }
}
