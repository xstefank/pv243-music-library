package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.SongDao;
import cz.muni.fi.pv243.musiclib.dao.UserDao;
import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.logging.LogMessages;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@Stateless
public class SongServiceImpl implements SongService {

    @Inject
    private SongDao songDao;

    @Inject
    private UserDao userDao;

    @Override
    public Song create(Song song) {
        Song created = songDao.create(song);

        LogMessages.LOGGER.logSongCreated(created);
        return created;
    }

    @Override
    public Song update(Song song) {
        Song updated = songDao.update(song);

        LogMessages.LOGGER.logSongUpdated(song);
        return updated;
    }

    @Override
    public void remove(Song song) {
        songDao.remove(song.getId());
        LogMessages.LOGGER.logSongRemoved(song);
    }

    @Override
    public Song findById(Long id) {
        return songDao.find(id);
    }

    @Override
    public List<Song> findAll() {
        return songDao.findAll();
    }

    @Override
    public List<Song> searchByTitle(String title) {
        return songDao.searchByTitle(title);
    }

    @Override
    public List<Song> searchByAlbum(Album album) {
        return songDao.searchByAlbum(album);
    }

    @Override
    public List<Song> searchByArtist(Artist artist) {
        return songDao.searchByArtist(artist);
    }

    @Override
    public List<Song> searchByGenre(Genre genre) {
        return songDao.searchByGenre(genre);
    }

    @Override
    public Album getAlbumForId(Long id) {
        return songDao.find(id).getAlbum();
    }

    @Override
    public Artist getArtistForId(Long id) {
        return songDao.find(id).getArtist();
    }

    @Override
    public Genre getGenreForId(Long id) {
        return songDao.find(id).getGenre();
    }
}
