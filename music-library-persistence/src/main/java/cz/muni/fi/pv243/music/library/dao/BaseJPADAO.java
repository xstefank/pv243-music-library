package cz.muni.fi.pv243.music.library.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public abstract class BaseJPADAO {

    @PersistenceContext
    private EntityManager entityManager;

    protected final EntityManager getEntityManager() {
        return entityManager;
    }
}
