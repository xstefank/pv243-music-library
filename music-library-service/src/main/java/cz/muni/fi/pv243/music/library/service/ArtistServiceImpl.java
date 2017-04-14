package cz.muni.fi.pv243.music.library.service;

import cz.muni.fi.pv243.music.library.dao.ArtistDao;
import cz.muni.fi.pv243.music.library.dao.qualifier.JpaDAO;
import cz.muni.fi.pv243.music.library.entity.Artist;
import cz.muni.fi.pv243.music.library.rest.client.LastFmRestClient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by mstyk on 4/14/17.
 */
@Stateless
public class ArtistServiceImpl implements ArtistService {

    @Inject
    @JpaDAO
    private ArtistDao artistDao;

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
        artistDao.remove(artist.getId());
    }

    @Override
    public Artist findById(Long id) {
        return artistDao.find(id);
    }

    @Override
    public List<Artist> findAll() {
        return artistDao.findAll();
    }

    @Override
    public String fetchArtistsBio(Artist artist) {
        if (artist == null || artist.getArtistName() == null) {
            throw new IllegalArgumentException("No artist name is specified in entity " + artist);
        }
        return lastFmClient.getArtistBio(artist.getArtistName());
    }


}
