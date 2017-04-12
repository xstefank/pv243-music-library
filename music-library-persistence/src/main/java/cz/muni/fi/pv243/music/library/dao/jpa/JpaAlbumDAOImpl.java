package cz.muni.fi.pv243.music.library.dao.jpa;

import cz.muni.fi.pv243.music.library.dao.qualifier.JPADAO;
import cz.muni.fi.pv243.music.library.entity.Album;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@JPADAO
public class JpaAlbumDAOImpl extends BaseJPADAO implements JpaAlbumDAO {

    @Override
    public void create(Album album) {
        getEntityManager().persist(album);
    }

    @Override
    public Album update(Album album) {
        return getEntityManager().merge(album);
    }

    @Override
    public void remove(Long id) {
        getEntityManager().remove(find(id));
    }

    @Override
    public Album find(Long id) {
        return getEntityManager().find(Album.class, id);
    }

    @Override
    public List<Album> searchByTitle(String titleFragment) {
        if(titleFragment == null) {
            throw new IllegalArgumentException("titleFragment cannot be null.");
        }
        return getEntityManager()
                .createQuery("SELECT a FROM Album a WHERE UPPER(a.title) LIKE '%'||:titleFragment||'%'", Album.class)
                .setParameter("titleFragment", titleFragment.toUpperCase())
                .getResultList();
    }

    @Override
    public List<Album> findAll() {
        return getEntityManager().createQuery("SELECT a FROM Album a", Album.class)
                .getResultList();
    }

    @Override
    public List<Album> getAlbumSample(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be a possitive number");
        }

        TypedQuery<Album> query = getEntityManager()
                .createQuery("SELECT a FROM Album a ORDER BY RANDOM()", Album.class);
        query.setMaxResults(count);
        return query.getResultList();
    }
}