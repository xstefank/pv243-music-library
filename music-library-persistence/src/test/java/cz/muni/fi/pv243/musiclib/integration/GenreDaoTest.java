package cz.muni.fi.pv243.musiclib.integration;

import cz.muni.fi.pv243.musiclib.dao.GenreDao;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.transaction.RollbackException;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@RunWith(Arquillian.class)
public class GenreDaoTest {

    @Deployment
    public static WebArchive deployment() {
        return ShrinkWrap.create(WebArchive.class, GenreDaoTest.class.getSimpleName() + ".war")
                .addPackages(true, "cz.muni.fi.pv243.musiclib")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Inject
    private GenreDao genreDAO;

    private Genre rock;
    private Genre pop;

    @Before
    public void before() {
        rock = Genre.builder().title("rock").build();

        pop = Genre.builder().title("pop").build();
    }

    @After
    public void after() {
        genreDAO.findAll().stream().map(Genre::getId).forEach(genreDAO::remove);
    }

    @Test
    public void testValidCreateThenFind() {
        genreDAO.create(rock);

        Assert.assertEquals(rock, genreDAO.find(rock.getId()));
    }

    @Test
    public void testCreateSameTitle() {
        expectedException.expect(PersistenceException.class);

        genreDAO.create(rock);
        genreDAO.create(rock);
    }

    @Test
    public void testCreateNull() {
        expectedException.expect(IllegalArgumentException.class);

        genreDAO.create(null);
    }

    @Test
    public void testCreateNullName() {
        expectedException.expect(ConstraintViolationException.class);

        rock.setTitle(null);
        genreDAO.create(rock);
    }

    @Test
    public void testCreateEmptyName() {
        expectedException.expect(ConstraintViolationException.class);

        rock.setTitle("");
        genreDAO.create(rock);
    }

    @Test
    public void updateNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        genreDAO.update(null);
    }

    @Test
    public void testUpdateEmptyName() {
        genreDAO.create(rock);
        rock.setTitle("");

        expectedException.expect(RollbackException.class);

        genreDAO.update(rock);
    }

    @Test
    public void testRemoveValid() {
        genreDAO.create(rock);

        genreDAO.remove(rock.getId());
        Assert.assertNull(genreDAO.find(rock.getId()));
    }

    @Test()
    public void testRemoveAlreadyRemoved() {
        genreDAO.create(rock);
        genreDAO.remove(rock.getId());

        expectedException.expect(EntityNotFoundException.class);

        genreDAO.remove(rock.getId());
    }

    @Test
    public void removeNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        genreDAO.remove(null);
    }

    @Test
    public void testFindValidId() {
        genreDAO.create(rock);

        Genre result = genreDAO.find(rock.getId());
        Assert.assertEquals(result, rock);
    }

    @Test
    public void testFindInvalidId() {
        Assert.assertNull(genreDAO.find(0L));
    }

    @Test
    public void testFindRemoved() {
        genreDAO.create(rock);
        Genre result = genreDAO.find(rock.getId());
        Assert.assertNotNull(result);

        genreDAO.remove(rock.getId());
        result = genreDAO.find(rock.getId());
        Assert.assertNull(result);
    }

    @Test
    public void testFindNullId() {
        expectedException.expect(IllegalArgumentException.class);

        genreDAO.find(null);
    }

    @Test
    public void testSearchByTitleExact() {
        genreDAO.create(rock);
        genreDAO.create(pop);

        List<Genre> result = genreDAO.searchByTitle(rock.getTitle());
        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(rock));
    }

    @Test
    public void testSearchByTitleSimilar() {
        String name = "rock";
        rock.setTitle(name);
        genreDAO.create(rock);
        genreDAO.create(pop);

        List<Genre> result = genreDAO.searchByTitle("rok");
        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(rock));
    }

    @Test
    public void testSearchByTitleNull() {
        expectedException.expect(ConstraintViolationException.class);

        genreDAO.searchByTitle(null);
    }

    @Test
    public void testSearchByTitleEmpty() {
        expectedException.expect(ConstraintViolationException.class);

        genreDAO.searchByTitle("");
    }


    @Test
    public void testFindAllValid() {
        genreDAO.create(rock);
        genreDAO.create(pop);

        List<Genre> result = genreDAO.findAll();
        Assert.assertEquals(result, Arrays.asList(rock, pop));
    }

    @Test
    public void testFindAllEmptyResult() {
        List<Genre> result = genreDAO.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
