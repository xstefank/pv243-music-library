package cz.muni.fi.pv243.musiclib.batching.job;

import cz.muni.fi.pv243.musiclib.dao.ArtistDao;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.logging.MusicLibLogger;
import cz.muni.fi.pv243.musiclib.rest.client.LastFmRestClient;

import javax.batch.api.Batchlet;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author Ondrej Oravcok
 * @version 18.5.2017
 */
@Named("commentaryBatchlet")
public class CommentaryBatchlet implements Batchlet {

    @Inject
    ArtistDao artistDao;

    @Inject
    private LastFmRestClient lastFmRestClient;

    @Override
    public String process() throws Exception {
        MusicLibLogger.LOGGER.info("Commentary batch STARTED.");

        List<Artist> artists = artistDao.getArtistsWithEmptyCommentary();
        for (Artist artist : artists) {
            String biography = lastFmRestClient.getArtistBio(artist.getName());
            artist.setCommentary(biography);
            artistDao.update(artist);
        }

        return "END";
    }

    @Override
    public void stop() throws Exception {
        MusicLibLogger.LOGGER.info("Commentary batch STOPPED.");
    }

}
