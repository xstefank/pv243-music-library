package cz.muni.fi.pv243.musiclib.integration;

import cz.muni.fi.pv243.musiclib.dao.AlbumDAO;
import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.util.AlbumBuilder;
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
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@RunWith(Arquillian.class)
public class AlbumDAOTest {

    @Deployment
    public static WebArchive deployment() {
        return ShrinkWrap.create(WebArchive.class, AlbumDAOTest.class.getSimpleName() + ".war")
                .addPackages(true, "cz.muni.fi.pv243.musiclib")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AlbumDAO albumDAO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Album testAlbum1;
    private Album testAlbum2;

    @Before
    public void before() {
        testAlbum1 = new AlbumBuilder()
                .title("testAlbum1")
                .dateOfRelease(LocalDate.now().minusDays(1))
                .build();

        testAlbum2 = new AlbumBuilder()
                .title("testAlbum2")
                .dateOfRelease(LocalDate.now().minusDays(2))
                .build();
    }

    @After
    public void after() {
        albumDAO.findAll().stream().map(Album::getId).forEach(albumDAO::remove);
    }

    @Test
    public void testValidCreateThenFind() {
        albumDAO.create(testAlbum1);

        Assert.assertEquals(testAlbum1, albumDAO.find(testAlbum1.getId()));
    }

    @Test
    public void testCreateSameTitle() {
        expectedException.expect(PersistenceException.class);

        albumDAO.create(testAlbum1);
        albumDAO.create(testAlbum1);
    }

    @Test
    public void testCreateNull() {
        expectedException.expect(IllegalArgumentException.class);

        albumDAO.create(null);
    }

    @Test
    public void testCreateNullTitle() {
        expectedException.expect(ConstraintViolationException.class);

        testAlbum1.setTitle(null);
        albumDAO.create(testAlbum1);
    }

    @Test
    public void testCreateInvalidDate() {
        expectedException.expect(ConstraintViolationException.class);

        testAlbum1.setDateOfRelease(LocalDate.now());
        albumDAO.create(testAlbum1);
    }

    @Test
    public void testUpdateValid() {
        albumDAO.create(testAlbum1);
        testAlbum1.setTitle("I'm not from Hobbit");

        Album updated = albumDAO.update(testAlbum1);
        Assert.assertEquals(updated, testAlbum1);
    }

    @Test
    public void updateNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        albumDAO.update(null);
    }

    @Test
    public void testUpdateNullTitle() {
        expectedException.expect(RollbackException.class);

        albumDAO.create(testAlbum1);
        testAlbum1.setTitle(null);

        albumDAO.update(testAlbum1);
    }

    @Test
    public void testRemoveValid() {
        albumDAO.create(testAlbum1);

        albumDAO.remove(testAlbum1.getId());
        Assert.assertNull(albumDAO.find(testAlbum1.getId()));
    }

    @Test
    public void testRemoveAlreadyRemoved() {
        expectedException.expect(EntityNotFoundException.class);

        albumDAO.create(testAlbum1);
        albumDAO.remove(testAlbum1.getId());

        Assert.assertNotNull(testAlbum1);
        albumDAO.remove(testAlbum1.getId());
    }

    @Test
    public void removeNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        albumDAO.remove(null);
    }

    @Test
    public void testFindValidId() {
        albumDAO.create(testAlbum1);

        Album result = albumDAO.find(testAlbum1.getId());
        Assert.assertEquals(result, testAlbum1);
    }

    @Test
    public void testFindInvalidId() {
        Album album = albumDAO.find(0L);
        Assert.assertNull(album);
    }

    @Test
    public void testFindRemoved() {
        albumDAO.create(testAlbum1);
        Album result = albumDAO.find(testAlbum1.getId());
        Assert.assertNotNull(result);

        albumDAO.remove(testAlbum1.getId());
        result = albumDAO.find(testAlbum1.getId());
        Assert.assertNull(result);
    }

    @Test
    public void testFindNullId() {
        expectedException.expect(IllegalArgumentException.class);

        albumDAO.find(null);
    }

    @Test
    public void testSearchByTitleExact() {
        String title = "testAlbum";
        testAlbum1.setTitle(title);
        albumDAO.create(testAlbum1);

        List<Album> result = albumDAO.searchByTitle(title);
        Assert.assertTrue(result.contains(testAlbum1));
    }

    @Test
    public void testSearchByTitleSimilar() {
        String title = "testAlbum";
        testAlbum1.setTitle(title);
        albumDAO.create(testAlbum1);

        List<Album> result = albumDAO.searchByTitle("testElbum");
        Assert.assertTrue(result.contains(testAlbum1));
    }

    @Test
    public void testSearchByTitleFragment() {
        String title = "testAlbum";
        testAlbum1.setTitle(title);
        albumDAO.create(testAlbum1);

        List<Album> result = albumDAO.searchByTitle("testAlb");
        Assert.assertTrue(result.contains(testAlbum1));
    }

    @Test
    public void testSearchByTitleNull() {
        expectedException.expect(ConstraintViolationException.class);

        albumDAO.searchByTitle(null);
    }

    @Test
    public void testSearchByArtistNull() {
        expectedException.expect(ConstraintViolationException.class);

        albumDAO.searchByArtist(null);
    }

    @Test
    public void testFindAllValid() {
        albumDAO.create(testAlbum1);
        albumDAO.create(testAlbum2);

        List<Album> result = albumDAO.findAll();
        Assert.assertEquals(result, Arrays.asList(testAlbum1, testAlbum2));
    }

    @Test
    public void testGetAlbumSampleValid() {
        albumDAO.create(testAlbum1);
        albumDAO.create(testAlbum2);

        List<Album> result = albumDAO.getAlbumSample(2);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result, Arrays.asList(testAlbum1, testAlbum2));
    }

    @Test
    public void testGetAlbumSampleValidLessCount() {
        albumDAO.create(testAlbum1);
        albumDAO.create(testAlbum2);

        List<Album> result = albumDAO.getAlbumSample(1);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
    }

    @Test
    public void getAlbumSampleInvlaidCountTest() {
        expectedException.expect(IllegalArgumentException.class);

        albumDAO.create(testAlbum1);
        albumDAO.create(testAlbum2);

        albumDAO.getAlbumSample(0);
    }
}
