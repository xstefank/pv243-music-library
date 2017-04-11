package cz.muni.fi.pv243.music;

import cz.muni.fi.pv243.music.library.dao.AlbumDAO;
import cz.muni.fi.pv243.music.library.dao.AlbumDAOImpl;
import cz.muni.fi.pv243.music.library.entity.Album;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class AlbumDAOTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private AlbumDAO albumDAO = new AlbumDAOImpl();

    private Album albumStub;

    @Before
    public void init() {
        albumStub = new Album(1L);
        Mockito.when(entityManager.find(Album.class, 1L)).thenReturn(albumStub);
    }

    @Test
    public void testCreateValid() {
        albumDAO.create(albumStub);
    }

    @Test
    public void testFindValidId() {
        Album result = albumDAO.find(1L);
        
        Assert.assertEquals(albumStub, result);
    }
}
