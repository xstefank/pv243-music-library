package cz.muni.fi.pv243.musiclib.dao.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mstyk on 4/14/17.
 */
@ApplicationScoped
public class EntityManagerFactory {

    @Produces
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;
}
