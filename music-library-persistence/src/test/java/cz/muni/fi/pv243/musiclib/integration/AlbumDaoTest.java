package cz.muni.fi.pv243.musiclib.integration;

import cz.muni.fi.pv243.musiclib.dao.AlbumDao;
import cz.muni.fi.pv243.musiclib.entity.Album;
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
public class AlbumDaoTest {

    @Deployment
    public static WebArchive deployment() {
        return ShrinkWrap.create(WebArchive.class, AlbumDaoTest.class.getSimpleName() + ".war")
                .addPackages(true, "cz.muni.fi.pv243.musiclib")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AlbumDao albumDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Album testAlbum1;
    private Album testAlbum2;

    @Before
    public void before() {
        testAlbum1 = Album.builder()
                .title("testAlbum1")
                .dateOfRelease(LocalDate.now().minusDays(1))
                .build();

        testAlbum2 = Album.builder()
                .title("testAlbum2")
                .dateOfRelease(LocalDate.now().minusDays(2))
                .build();
    }

    @After
    public void after() {
        albumDao.findAll().stream().map(Album::getId).forEach(albumDao::remove);
    }

    @Test
    public void testValidCreateThenFind() {
        albumDao.create(testAlbum1);

        Assert.assertEquals(testAlbum1, albumDao.find(testAlbum1.getId()));
    }

    @Test
    public void testCreateSameTitle() {
        expectedException.expect(PersistenceException.class);

        albumDao.create(testAlbum1);
        albumDao.create(testAlbum1);
    }

    @Test
    public void testCreateNull() {
        expectedException.expect(IllegalArgumentException.class);

        albumDao.create(null);
    }

    @Test
    public void testCreateNullTitle() {
        expectedException.expect(ConstraintViolationException.class);

        testAlbum1.setTitle(null);
        albumDao.create(testAlbum1);
    }

    @Test
    public void testCreateInvalidDate() {
        expectedException.expect(ConstraintViolationException.class);

        testAlbum1.setDateOfRelease(LocalDate.now());
        albumDao.create(testAlbum1);
    }

    @Test
    public void testUpdateValid() {
        albumDao.create(testAlbum1);
        testAlbum1.setTitle("I'm not from Hobbit");

        Album updated = albumDao.update(testAlbum1);
        Assert.assertEquals(updated, testAlbum1);
    }

    @Test
    public void updateNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        albumDao.update(null);
    }

    @Test
    public void testUpdateNullTitle() {
        expectedException.expect(RollbackException.class);

        albumDao.create(testAlbum1);
        testAlbum1.setTitle(null);

        albumDao.update(testAlbum1);
    }

    @Test
    public void testRemoveValid() {
        albumDao.create(testAlbum1);

        albumDao.remove(testAlbum1.getId());
        Assert.assertNull(albumDao.find(testAlbum1.getId()));
    }

    @Test
    public void testRemoveAlreadyRemoved() {
        expectedException.expect(EntityNotFoundException.class);

        albumDao.create(testAlbum1);
        albumDao.remove(testAlbum1.getId());

        Assert.assertNotNull(testAlbum1);
        albumDao.remove(testAlbum1.getId());
    }

    @Test
    public void removeNullTest() {
        expectedException.expect(IllegalArgumentException.class);

        albumDao.remove(null);
    }

    @Test
    public void testFindValidId() {
        albumDao.create(testAlbum1);

        Album result = albumDao.find(testAlbum1.getId());
        Assert.assertEquals(result, testAlbum1);
    }

    @Test
    public void testFindInvalidId() {
        Album album = albumDao.find(0L);
        Assert.assertNull(album);
    }

    @Test
    public void testFindRemoved() {
        albumDao.create(testAlbum1);
        Album result = albumDao.find(testAlbum1.getId());
        Assert.assertNotNull(result);

        albumDao.remove(testAlbum1.getId());
        result = albumDao.find(testAlbum1.getId());
        Assert.assertNull(result);
    }

    @Test
    public void testFindNullId() {
        expectedException.expect(IllegalArgumentException.class);

        albumDao.find(null);
    }

    @Test
    public void testSearchByTitleExact() {
        String title = "testAlbum";
        testAlbum1.setTitle(title);
        albumDao.create(testAlbum1);

        List<Album> result = albumDao.searchByTitle(title);
        Assert.assertTrue(result.contains(testAlbum1));
    }

    @Test
    public void testSearchByTitleSimilar() {
        String title = "testAlbum";
        testAlbum1.setTitle(title);
        albumDao.create(testAlbum1);

        List<Album> result = albumDao.searchByTitle("testElbum");
        Assert.assertTrue(result.contains(testAlbum1));
    }

    @Test
    public void testSearchByTitleFragment() {
        String title = "testAlbum";
        testAlbum1.setTitle(title);
        albumDao.create(testAlbum1);

        List<Album> result = albumDao.searchByTitle("testAlb");
        Assert.assertTrue(result.contains(testAlbum1));
    }

    @Test
    public void testSearchByTitleNull() {
        expectedException.expect(ConstraintViolationException.class);

        albumDao.searchByTitle(null);
    }

    @Test
    public void testSearchByArtistNull() {
        expectedException.expect(ConstraintViolationException.class);

        albumDao.searchByArtist(null);
    }

    @Test
    public void testFindAllValid() {
        albumDao.create(testAlbum1);
        albumDao.create(testAlbum2);

        List<Album> result = albumDao.findAll();
        Assert.assertEquals(result, Arrays.asList(testAlbum1, testAlbum2));
    }

    @Test
    public void testGetAlbumSampleValid() {
        albumDao.create(testAlbum1);
        albumDao.create(testAlbum2);

        List<Album> result = albumDao.getAlbumSample(2);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void testGetAlbumSampleValidLessCount() {
        albumDao.create(testAlbum1);
        albumDao.create(testAlbum2);

        List<Album> result = albumDao.getAlbumSample(1);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
    }

    @Test
    public void getAlbumSampleInvlaidCountTest() {
        expectedException.expect(IllegalArgumentException.class);

        albumDao.create(testAlbum1);
        albumDao.create(testAlbum2);

        albumDao.getAlbumSample(0);
    }
}
