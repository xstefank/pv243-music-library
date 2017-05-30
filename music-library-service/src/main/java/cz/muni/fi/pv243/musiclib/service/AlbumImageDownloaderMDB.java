package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.logging.LogMessages;
import cz.muni.fi.pv243.musiclib.rest.client.LastFmRestClient;
import org.apache.commons.io.IOUtils;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;
import java.net.URL;

import static cz.muni.fi.pv243.musiclib.service.AlbumImageDownloaderMDB.ALBUM_IMAGE_PROCESSING_QUEUE;
import static cz.muni.fi.pv243.musiclib.service.AlbumImageDownloaderMDB.ALBUM_IMAGE_PROCESSING_QUEUE_JNDI;

/**
 * Downloads image of album and stores it in database
 *
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = ALBUM_IMAGE_PROCESSING_QUEUE_JNDI,
                interfaceName = "javax.jms.Queue",
                destinationName = ALBUM_IMAGE_PROCESSING_QUEUE
        )
})
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination",
                        propertyValue = ALBUM_IMAGE_PROCESSING_QUEUE_JNDI)
        })
public class AlbumImageDownloaderMDB implements MessageListener {

    public static final String ALBUM_IMAGE_PROCESSING_QUEUE = "AlbumImageProcessing";
    public static final String ALBUM_IMAGE_PROCESSING_QUEUE_JNDI = "java:jboss/exported/jms/queue/" + ALBUM_IMAGE_PROCESSING_QUEUE;

    @Inject
    private AlbumService albumService;

    @Inject
    private LastFmRestClient lastFmRestClient;

    @Override
    public void onMessage(Message msg) {
        LogMessages.LOGGER.logMethodEntered(getClass().getSimpleName(), "onMessage");
        Album album = null;
        try {
            album = msg.getBody(Album.class);
        } catch (JMSException e) {
            //TODO error logging
            System.out.println("Can not read Album body of message");
            e.printStackTrace();
            return;
        }

        URL downloadURL = null;
        byte[] bytes = null;
        try {
            downloadURL = lastFmRestClient.getAlbumPictureDownloadLink(album.getArtist().getName(), album.getTitle());
            if (downloadURL == null) {
                System.out.println("No image found for " + album.getTitle());
                return;
            }
            bytes = IOUtils.toByteArray(downloadURL);
        } catch (IOException e) {
            LogMessages.LOGGER.info("Can not download image from URL " + downloadURL);
            e.printStackTrace();
        }
        album.setAlbumArt(bytes);
        albumService.update(album);
    }
}
