package cz.muni.fi.pv243.musiclib.integration;

import cz.muni.fi.pv243.musiclib.dao.ArtistDao;
import cz.muni.fi.pv243.musiclib.entity.Artist;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@RunWith(Arquillian.class)
public class ArtistDAOTest {

    @Deployment
    public static WebArchive deployment() {
        return ShrinkWrap.create(WebArchive.class, ArtistDAOTest.class.getSimpleName() + ".war")
                .addPackages(true, "cz.muni.fi.pv243.musiclib")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Inject
    private ArtistDao artistDao;

    private Artist metalica;
    private Artist queen;

    @Before
    public void before() {
        metalica = Artist.builder()
                .name("Metalica")
                .dateOfBirth(LocalDate.now().minusDays(20).minusYears(20))
                .build();

        queen = Artist.builder()
                .name("Qeen")
                .dateOfBirth(LocalDate.now().minusDays(2).minusWeeks(100))
                .build();
    }

    @After
    public void after() {
        artistDao.findAll().stream().map(Artist::getId).forEach(artistDao::remove);
    }

    @Test
    public void testValidCreateThenFind() {
        artistDao.create(metalica);

        Assert.assertEquals(metalica, artistDao.find(metalica.getId()));
    }

    @Test
    public void testCreateSameTitle() {
        expectedException.expect(PersistenceException.class);

        artistDao.create(metalica);
        artistDao.create(metalica);
    }

    @Test
    public void testCreateNull() {
        expectedException.expect(IllegalArgumentException.class);

        artistDao.create(null);
    }

    @Test
    public void testCreateNullName() {
        expectedException.expect(ConstraintViolationException.class);

        metalica.setName(null);
        artistDao.create(metalica);
    }

    @Test
    public void testCreateEmptyName() {
        expectedException.expect(ConstraintViolationException.class);

        metalica.setName("");
        artistDao.create(metalica);
    }

    @Test
    public void testCreateInvalidDate() {
        expectedException.expect(ConstraintViolationException.class);

        metalica.setDateOfBirth(LocalDate.now().plusWeeks(1));
        artistDao.create(metalica);
    }

    @Test
    public void testUpdateValid() {
        artistDao.create(metalica);
        metalica.setDateOfBirth(LocalDate.of(1999, 1, 1));

        Artist updated = artistDao.update(metalica);
        Assert.assertEquals(updated, metalica);
    }

    @Test
    public void updateNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        artistDao.update(null);
    }

    @Test
    public void testUpdateEmptyName() {
        artistDao.create(metalica);
        metalica.setName("");

        expectedException.expect(RollbackException.class);

        artistDao.update(metalica);
    }

    @Test
    public void testRemoveValid() {
        artistDao.create(metalica);

        artistDao.remove(metalica.getId());
        Assert.assertNull(artistDao.find(metalica.getId()));
    }

    @Test()
    public void testRemoveAlreadyRemoved() {
        artistDao.create(metalica);
        artistDao.remove(metalica.getId());

        expectedException.expect(EntityNotFoundException.class);

        artistDao.remove(metalica.getId());
    }

    @Test
    public void removeNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        artistDao.remove(null);
    }

    @Test
    public void testFindValidId() {
        artistDao.create(metalica);

        Artist result = artistDao.find(metalica.getId());
        Assert.assertEquals(result, metalica);
    }

    @Test
    public void testFindInvalidId() {
        Assert.assertNull(artistDao.find(0L));
    }

    @Test
    public void testFindRemoved() {
        artistDao.create(metalica);
        Artist result = artistDao.find(metalica.getId());
        Assert.assertNotNull(result);

        artistDao.remove(metalica.getId());
        result = artistDao.find(metalica.getId());
        Assert.assertNull(result);
    }

    @Test
    public void testFindNullId() {
        expectedException.expect(IllegalArgumentException.class);

        artistDao.find(null);
    }

    @Test
    public void testSearchByNameExact() {
        artistDao.create(metalica);
        artistDao.create(queen);

        List<Artist> result = artistDao.searchByName(metalica.getName());
        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(metalica));
    }

    @Test
    public void testSearchByNameSimilar() {
        String name = "metalica";
        metalica.setName(name);
        artistDao.create(metalica);
        artistDao.create(queen);

        List<Artist> result = artistDao.searchByName("Metaika");
        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(metalica));
    }

    @Test
    public void testSearchByNameNull() {
        expectedException.expect(ConstraintViolationException.class);

        artistDao.searchByName(null);
    }

    @Test
    public void testSearchByNameEmpty() {
        expectedException.expect(ConstraintViolationException.class);

        artistDao.searchByName("");
    }


    @Test
    public void testFindAllValid() {
        artistDao.create(metalica);
        artistDao.create(queen);

        List<Artist> result = artistDao.findAll();
        Assert.assertEquals(result, Arrays.asList(metalica, queen));
    }

    @Test
    public void testFindAllEmptyResult() {
        List<Artist> result = artistDao.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
