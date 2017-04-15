package cz.muni.fi.pv243.musiclib.dao.resource;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.persistence.*;

/**
 * Created by mstyk on 4/14/17.
 */
@ApplicationScoped
public class EntityManagerFactory {

    @Produces
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;
}
