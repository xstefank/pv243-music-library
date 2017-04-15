package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.GenericDAO;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public abstract class GenericDaoImpl<T, U> implements GenericDAO<T, U> {

    @Inject
    protected EntityManager em;

    private Class<T> type;

    public GenericDaoImpl(Class<T> entityType) {
        type = entityType;
    }

    @Override
    public T create(final T t) {
        em.persist(t);
        return t;
    }

    @Override
    public void remove(U id) {
        em.remove(this.em.getReference(type, id));
    }

    @Override
    public T find(U id) {
        return em.find(type, id);
    }

    @Override
    public T update(final T t) {
        return em.merge(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return em.createQuery("FROM " + type.getName()).<T>getResultList();

    }
}