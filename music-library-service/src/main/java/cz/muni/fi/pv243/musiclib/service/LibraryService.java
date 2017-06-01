package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;

import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface LibraryService {
    /**
     * Retrieves {@link Song} for the given {@link User}
     */
    List<Song> findSongsInUserLib(String user);

    /**
     * Adds {@link Song} to user's library
     */
    Boolean addSongToUserLib(Long songId, String user);

    /**
     * Removes {@link Song} from user's library
     */
    Boolean removeSongFromUserLib(Long songId, String user);
}
