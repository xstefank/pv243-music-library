package cz.muni.fi.pv243.musiclib.integration;

import cz.muni.fi.pv243.musiclib.dao.AlbumDAO;
import cz.muni.fi.pv243.musiclib.entity.Album;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

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

    @Test
    public void testValidCreateThenFind() {
        Album album = new Album();
        album.setTitle("TestAlbum");
        Album created = albumDAO.create(album);

        Assert.assertEquals(created, albumDAO.find(created.getId()));
    }
}
