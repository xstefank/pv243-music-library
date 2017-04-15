package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.AlbumDAO;
import cz.muni.fi.pv243.musiclib.dao.SongDao;
import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class AlbumServiceImpl implements AlbumService {

    @Inject
    private AlbumDAO albumDao;

    @Inject
    private SongDao songDao;

    @Override
    public Album create(Album album) {
        return albumDao.create(album);
    }

    @Override
    public Album update(Album album) {
        return albumDao.update(album);
    }

    @Override
    public void remove(Album album) throws IllegalArgumentException {
        albumDao.remove(album.getId());
    }

    @Override
    public Album findById(Long id) {
        return albumDao.find(id);
    }

    @Override
    public List<Album> searchByTitle(String title) {
        return albumDao.searchByTitle(title);
    }

    @Override
    public List<Album> searchByArtist(Artist artist) {
        return albumDao.searchByArtist(artist);
    }

    @Override
    public List<Album> findAll() {
        return albumDao.findAll();
    }

    @Override
    public List<Album> getAlbumSample(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be a possitive number");
        }

        return albumDao.getAlbumSample(count);
    }
}
