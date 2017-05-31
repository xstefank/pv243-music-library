package cz.muni.fi.pv243.musiclib.logging;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;
import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@MessageLogger(projectCode = "MUSICLIB")
public interface LogMessages extends BasicLogger {

    LogMessages LOGGER = Logger.getMessageLogger(LogMessages.class, LogMessages.class.getPackage().getName());
    int BASE = 1000;

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 0, value = "Created new artist: {0}", format = Message.Format.MESSAGE_FORMAT)
    void logArtistCreated(Artist artist);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 5, value = "Artist {0} has been updated", format = Message.Format.MESSAGE_FORMAT)
    void logArtistUpdated(Artist artist);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 10, value = "Artist {0} has been removed", format = Message.Format.MESSAGE_FORMAT)
    void logArtistRemoved(Artist artist);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 15, value = "Fetching artist biography for the artist: {0}", format = Message.Format.MESSAGE_FORMAT)
    void logFetchArtistBio(Artist artist);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 20, value = "Created new album: {0}", format = Message.Format.MESSAGE_FORMAT)
    void logAlbumCreated(Album album);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 25, value = "Album {0} has been updated", format = Message.Format.MESSAGE_FORMAT)
    void logAlbumUpdated(Album album);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 30, value = "Album {0} has been removed", format = Message.Format.MESSAGE_FORMAT)
    void logAlbumRemoved(Album album);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 35, value = "Fetching random albums sample", format = Message.Format.MESSAGE_FORMAT)
    void logGetAlbumSample();

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 40, value = "Created new genre: {0}", format = Message.Format.MESSAGE_FORMAT)
    void logGenreCreated(Genre genre);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 45, value = "Genre {0} has been updated", format = Message.Format.MESSAGE_FORMAT)
    void logGenreUpdated(Genre genre);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 50, value = "Genre {0} has been removed", format = Message.Format.MESSAGE_FORMAT)
    void logGenreRemoved(Genre genre);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 55, value = "Created new song: {0}", format = Message.Format.MESSAGE_FORMAT)
    void logSongCreated(Song song);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 60, value = "Song {0} has been updated", format = Message.Format.MESSAGE_FORMAT)
    void logSongUpdated(Song song);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 65, value = "Song {0} has been removed", format = Message.Format.MESSAGE_FORMAT)
    void logSongRemoved(Song song);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 70, value = "Fetching library songs for the user: {0}", format = Message.Format.MESSAGE_FORMAT)
    void logFetchSongsForUser(User user);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 75, value = "Adding song {0} to user library {1}", format = Message.Format.MESSAGE_FORMAT)
    void logAddSongToUserLib(Song song, User user);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 76, value = "Removing song {0} from user library {1}", format = Message.Format.MESSAGE_FORMAT)
    void logRemoveSongFromUserLib(Song song, User user);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 80, value = "Created new user: {0}", format = Message.Format.MESSAGE_FORMAT)
    void logUserCreated(User user);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 85, value = "User {0} has been updated", format = Message.Format.MESSAGE_FORMAT)
    void logUserUpdated(User user);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 90, value = "User {0} has been removed", format = Message.Format.MESSAGE_FORMAT)
    void logUserRemoved(User user);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 95, value = "{0}::{1}", format = Message.Format.MESSAGE_FORMAT)
    void logMethodEntered(String className, String methodName);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 100, value = "Sending reccomended songs", format = Message.Format.MESSAGE_FORMAT)
    void logFireReccomendEvent();

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 105, value = "Recommending song {0} with user: {1}", format = Message.Format.MESSAGE_FORMAT)
    void logReccomendSong(String songName, String userName);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 110, value = "{0} connection established", format = Message.Format.MESSAGE_FORMAT)
    void logWebsocketConnect(String websocket);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 115, value = "{0} connection disconnected", format = Message.Format.MESSAGE_FORMAT)
    void logWebsocketDisconnect(String websocket);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 120, value = "Added session {0}. Total number of sessions is {1}", format = Message.Format.MESSAGE_FORMAT)
    void logAddSession(String sessionId, int size);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 125, value = "Removed session {0}. Total number of sessions is {1}", format = Message.Format.MESSAGE_FORMAT)
    void logRemoveSession(String sessionId, int size);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 130, value = "Job running, with id = {0}", format = Message.Format.MESSAGE_FORMAT)
    void logBatchJobRunning(long jobId);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 135, value =  "Number of batch items to process {0}", format = Message.Format.MESSAGE_FORMAT)
    void logBatchNumberOfItems(int size);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 140, value =  "Processing {0}", format = Message.Format.MESSAGE_FORMAT)
    void logProcessingBatchItem(String itemName);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = BASE + 145, value =  "Processed {0}", format = Message.Format.MESSAGE_FORMAT)
    void logBatchItemProcessed(String itemName);

    @LogMessage(level = Logger.Level.WARN)
    @Message(id = BASE + 150, value =  "LastFM webservice is unavailable {0}", format = Message.Format.MESSAGE_FORMAT)
    void logLastFmUnavailable(Exception ex);

}
