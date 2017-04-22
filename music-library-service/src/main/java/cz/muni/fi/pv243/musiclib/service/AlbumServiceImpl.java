package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.AlbumDao;
import cz.muni.fi.pv243.musiclib.dao.SongDao;
import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class AlbumServiceImpl implements AlbumService {

    @Resource(lookup = AlbumImageDownloaderMDB.ALBUM_IMAGE_PROCESSING_QUEUE_JNDI)
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    @Inject
    private AlbumDao albumDao;

    @Inject
    private SongDao songDao;

    @Override
    public Album create(Album album) {
        Album created = albumDao.create(album);
        fetchAlbumImage(created);
        return created;
    }

    @Override
    public Album update(Album album) {
        return albumDao.update(album);
    }

    @Override
    public void remove(Album album) throws IllegalArgumentException {
        songDao.searchByAlbum(album)
                .forEach(song -> song.setAlbum(null));

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

    @Override
    public void fetchAlbumImage(Album album) {
        Message message = jmsContext.createObjectMessage(album);
        jmsContext.createProducer().send(queue, message);
    }
}
