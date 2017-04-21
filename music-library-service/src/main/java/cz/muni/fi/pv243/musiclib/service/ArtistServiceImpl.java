package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.ArtistDao;
import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.rest.client.LastFmRestClient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@Stateless
public class ArtistServiceImpl implements ArtistService {

    @Inject
    private ArtistDao artistDao;

    @Inject
    private AlbumService albumService;

    @Inject
    private SongService songService;

    @Inject
    private LastFmRestClient lastFmClient;

    @Override
    public Artist create(Artist artist) {
        return artistDao.create(artist);
    }

    @Override
    public Artist update(Artist artist) {
        return artistDao.update(artist);
    }

    @Override
    public void remove(Artist artist) {
        albumService.searchByArtist(artist)
                .forEach(album -> album.setArtist(null));

        //song cannot be without artist
        songService.searchByArtist(artist)
                .forEach(song -> songService.remove(song));

        artistDao.remove(artist.getId());
    }

    @Override
    public Artist findById(Long id) {
        return artistDao.find(id);
    }

    @Override
    public List<Artist> searchByName(String name) {
        return artistDao.searchByName(name);
    }

    @Override
    public List<Artist> findAll() {
        return artistDao.findAll();
    }

    @Override
    public String fetchArtistsBio(Artist artist) {
        if (artist == null || artist.getName() == null) {
            throw new IllegalArgumentException("No artist name is specified in entity " + artist);
        }
        return lastFmClient.getArtistBio(artist.getName());
    }

    @Override
    public List<Album> getAlbumsForId(Long id) {
        return albumService.searchByArtist(findById(id));
    }
}
