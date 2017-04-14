package cz.muni.fi.pv243.music.library.dao.impl;

import cz.muni.fi.pv243.music.library.dao.*;
import cz.muni.fi.pv243.music.library.dao.qualifier.*;
import cz.muni.fi.pv243.music.library.entity.*;

import javax.enterprise.context.*;
import javax.persistence.*;
import javax.transaction.*;

/**
 * Created by mstyk on 4/14/17.
 */
@JpaDAO
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
