package cz.muni.fi.pv243.music.unit;

import cz.muni.fi.pv243.music.library.dao.jpa.JpaAlbumDAO;
import cz.muni.fi.pv243.music.library.dao.jpa.JpaAlbumDAOImpl;
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
public class JpaAlbumDAOTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private JpaAlbumDAO jpaAlbumDAO = new JpaAlbumDAOImpl();

    private Album albumStub;

    @Before
    public void init() {
        albumStub = new Album(1L);
        Mockito.when(entityManager.find(Album.class, 1L)).thenReturn(albumStub);
    }

    @Test
    public void testCreateValidNoException() {
        jpaAlbumDAO.create(albumStub);
    }

    @Test
    public void testFindValidId() {
        Album result = jpaAlbumDAO.find(1L);
        
        Assert.assertEquals(albumStub, result);
    }
}
