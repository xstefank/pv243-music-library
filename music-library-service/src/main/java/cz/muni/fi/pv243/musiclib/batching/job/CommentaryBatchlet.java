package cz.muni.fi.pv243.musiclib.batching.job;

import cz.muni.fi.pv243.musiclib.dao.ArtistDao;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.logging.LogMessages;
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
        LogMessages.LOGGER.logMethodEntered(getClass().getSimpleName(), "process");

        List<Artist> artists = artistDao.getArtistsWithEmptyCommentary();

        LogMessages.LOGGER.logBatchNumberOfItems(artists.size());
        for (Artist artist : artists) {
            LogMessages.LOGGER.logProcessingBatchItem(artist.getName());
            String biography = lastFmRestClient.getArtistBio(artist.getName());
            artist.setCommentary(biography);
            artistDao.update(artist);
            LogMessages.LOGGER.logBatchItemProcessed(artist.getName());
        }

        return "END";
    }

    @Override
    public void stop() throws Exception {
        LogMessages.LOGGER.logMethodEntered(getClass().getSimpleName(), "stop");
    }

}
