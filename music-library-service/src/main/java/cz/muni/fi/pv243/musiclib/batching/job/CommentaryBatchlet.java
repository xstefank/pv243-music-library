package cz.muni.fi.pv243.musiclib.batching.job;

import cz.muni.fi.pv243.musiclib.dao.ArtistDao;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.logging.MusicLibLogger;
import cz.muni.fi.pv243.musiclib.rest.client.LastFmRestClient;

import javax.batch.api.Batchlet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author Ondrej Oravcok
 * @version 18.5.2017
 */
@Named("commentaryBatchlet")
@ApplicationScoped
public class CommentaryBatchlet implements Batchlet {

    @Inject
    ArtistDao artistDao;

    @Inject
    private LastFmRestClient lastFmRestClient;

    @Override
    public String process() throws Exception {
        MusicLibLogger.LOGGER.info("Commentary batch STARTED.");

        List<Artist> artists = artistDao.getArtistsWithEmptyCommentary();

        MusicLibLogger.LOGGER.info("Number of items to process " + artists.size());
        for (Artist artist : artists) {
            MusicLibLogger.LOGGER.info("Processing " + artist.getName());
            String biography = lastFmRestClient.getArtistBio(artist.getName());
            artist.setCommentary(biography);
            artistDao.update(artist);
            MusicLibLogger.LOGGER.info("Processed " + artist.getName());
        }

        return "END";
    }

    @Override
    public void stop() throws Exception {
        MusicLibLogger.LOGGER.info("Commentary batch STOPPED.");
    }

}
