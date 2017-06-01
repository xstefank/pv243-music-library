package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.SongDao;
import cz.muni.fi.pv243.musiclib.dao.UserDao;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.logging.LogMessages;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Stateless
public class LibraryServiceImpl implements LibraryService {

    @Inject
    private UserDao userDao;

    @Inject
    private SongDao songDao;

    @Override
    public List<Song> findSongsInUserLib(String userEmail) {
        User user = userDao.findByEmail(userEmail);

        LogMessages.LOGGER.logFetchSongsForUser(user);
        return new ArrayList<>(user.getMusicLibrary().getSongs());
    }

    @Override
    public Boolean addSongToUserLib(Long songId, String userEmail) {
        User user = userDao.findByEmail(userEmail);
        Song song = songDao.find(songId);

        LogMessages.LOGGER.logAddSongToUserLib(song, user);
        boolean newlyAdded = user.getMusicLibrary().addSong(song);

        userDao.update(user);

        return newlyAdded;
    }

    @Override
    public Boolean removeSongFromUserLib(Long songId, String userEmail) {
        User user = userDao.findByEmail(userEmail);
        Song song = songDao.find(songId);

        LogMessages.LOGGER.logRemoveSongFromUserLib(song, user);
        boolean removed = user.getMusicLibrary().removeSong(song);

        userDao.update(user);

        return removed;
    }
}
