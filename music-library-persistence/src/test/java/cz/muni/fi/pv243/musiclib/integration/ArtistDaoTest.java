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
public class ArtistDaoTest {

    @Deployment
    public static WebArchive deployment() {
        return ShrinkWrap.create(WebArchive.class, ArtistDaoTest.class.getSimpleName() + ".war")
                .addPackages(true, "cz.muni.fi.pv243.musiclib")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Inject
    private ArtistDao artistDao;

    private Artist metallica;
    private Artist queen;

    @Before
    public void before() {
        metallica = Artist.builder()
                .name("Metallica")
                .dateOfBirth(LocalDate.now().minusDays(20).minusYears(20))
                .commentary("best metal band")
                .build();

        queen = Artist.builder()
                .name("Qeen")
                .dateOfBirth(LocalDate.now().minusDays(2).minusWeeks(100))
                .commentary("King Of UK music")
                .build();
    }

    @After
    public void after() {
        artistDao.findAll().stream().map(Artist::getId).forEach(artistDao::remove);
    }

    @Test
    public void testValidCreateThenFind() {
        artistDao.create(metallica);

        Assert.assertEquals(metallica, artistDao.find(metallica.getId()));
    }

    @Test
    public void testCreateSameTitle() {
        expectedException.expect(PersistenceException.class);

        artistDao.create(metallica);
        artistDao.create(Artist.builder()
                .name(metallica.getName())
                .dateOfBirth(metallica.getDateOfBirth())
                .commentary(metallica.getCommentary())
                .build());
    }

    @Test
    public void testCreateNull() {
        expectedException.expect(IllegalArgumentException.class);

        artistDao.create(null);
    }

    @Test
    public void testCreateNullName() {
        expectedException.expect(ConstraintViolationException.class);

        metallica.setName(null);
        artistDao.create(metallica);
    }

    @Test
    public void testCreateEmptyName() {
        expectedException.expect(ConstraintViolationException.class);

        metallica.setName("");
        artistDao.create(metallica);
    }

    @Test
    public void testCreateInvalidDate() {
        expectedException.expect(ConstraintViolationException.class);

        metallica.setDateOfBirth(LocalDate.now().plusWeeks(1));
        artistDao.create(metallica);
    }

    @Test
    public void testUpdateValid() {
        artistDao.create(metallica);
        metallica.setDateOfBirth(LocalDate.of(1999, 1, 1));

        Artist updated = artistDao.update(metallica);
        Assert.assertEquals(updated, metallica);
    }

    @Test
    public void updateNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        artistDao.update(null);
    }

    @Test
    public void testUpdateEmptyName() {
        artistDao.create(metallica);
        metallica.setName("");

        expectedException.expect(RollbackException.class);

        artistDao.update(metallica);
    }

    @Test
    public void testRemoveValid() {
        artistDao.create(metallica);

        artistDao.remove(metallica.getId());
        Assert.assertNull(artistDao.find(metallica.getId()));
    }

    @Test()
    public void testRemoveAlreadyRemoved() {
        artistDao.create(metallica);
        artistDao.remove(metallica.getId());

        expectedException.expect(EntityNotFoundException.class);

        artistDao.remove(metallica.getId());
    }

    @Test
    public void removeNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        artistDao.remove(null);
    }

    @Test
    public void testFindValidId() {
        artistDao.create(metallica);

        Artist result = artistDao.find(metallica.getId());
        Assert.assertEquals(result, metallica);
    }

    @Test
    public void testFindInvalidId() {
        Assert.assertNull(artistDao.find(0L));
    }

    @Test
    public void testFindRemoved() {
        artistDao.create(metallica);
        Artist result = artistDao.find(metallica.getId());
        Assert.assertNotNull(result);

        artistDao.remove(metallica.getId());
        result = artistDao.find(metallica.getId());
        Assert.assertNull(result);
    }

    @Test
    public void testFindNullId() {
        expectedException.expect(IllegalArgumentException.class);

        artistDao.find(null);
    }

    @Test
    public void testSearchByNameExact() {
        artistDao.create(metallica);
        artistDao.create(queen);

        List<Artist> result = artistDao.searchByName(metallica.getName());
        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(metallica));
    }

    @Test
    public void testSearchByNameSimilar() {
        String name = "metalica";
        metallica.setName(name);
        artistDao.create(metallica);
        artistDao.create(queen);

        List<Artist> result = artistDao.searchByName("Metaika");
        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(metallica));
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
        artistDao.create(metallica);
        artistDao.create(queen);

        List<Artist> result = artistDao.findAll();
        Assert.assertEquals(result, Arrays.asList(metallica, queen));
    }

    @Test
    public void testFindAllEmptyResult() {
        List<Artist> result = artistDao.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
