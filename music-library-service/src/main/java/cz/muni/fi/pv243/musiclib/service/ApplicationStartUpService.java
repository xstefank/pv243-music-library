package cz.muni.fi.pv243.musiclib.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Place to do some actions on application start
 *
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Singleton
@Startup
public class ApplicationStartUpService {

    @Inject
    private AlbumService albumService;

    /**
     * We insert sample data into database, but we don't deal with images. Download them async on
     * application start up
     */
    @PostConstruct
    public void onStartUp() {
        albumService.findAll().stream()
                .filter(album -> album.getAlbumArt() == null)
                .forEach(albumService::fetchAlbumImage);
    }

}
