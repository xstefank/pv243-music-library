package cz.muni.fi.pv243.musiclib.dao.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@ApplicationScoped
public class EntityManagerFactory {

    @Produces
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;
}
