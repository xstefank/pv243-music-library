package cz.muni.fi.pv243.music.library.service;

import cz.muni.fi.pv243.music.library.dao.*;
import cz.muni.fi.pv243.music.library.dao.qualifier.*;
import cz.muni.fi.pv243.music.library.entity.*;

import javax.ejb.*;
import javax.inject.*;
import java.util.*;

/**
 * Created by mstyk on 4/14/17.
 */
@Stateless
public class AlbumServiceImpl implements AlbumService {

    @Inject
    @JpaDAO
    private AlbumDAO albumDao;

    @Inject
    @JpaDAO
    private SongDao songDao;

    @Override
    public Album create(Album album) {
        return albumDao.create(album);
    }

    @Override
    public Album update(Album album) {
        return albumDao.update(album);
    }

    @Override
    public void remove(Album album) throws IllegalArgumentException {
        albumDao.remove(album.getId());
    }

    @Override
    public Album findById(Long id) {
        return albumDao.find(id);
    }

    @Override
    public List<Album> searchByTitle(String title) {
        return albumDao.searchByTitle(title);
    }

    @Override
    public List<Album> findAll() {
        return albumDao.findAll();
    }

    @Override
    public void addSong(Album album, Song song) {

        if (album.getSongs().contains(song)) {
            throw new IllegalArgumentException("Album already contains this song; Album: "
                    + album.getId() + ", song: " + song.getId());
        }

        song.setAlbum(album);
        album.addSong(song);
        songDao.update(song);
    }

    @Override
    public void removeSong(Album album, Song song) {
        if (album == null) {
            throw new IllegalArgumentException("album cannot be null");
        }
        if (song == null) {
            throw new IllegalArgumentException("song cannot be null");
        }

        if (!album.getSongs().contains(song)) {
            throw new IllegalArgumentException("Album does not contains the desired song; Album: "
                    + album.getId() + ", song: " + song.getId());
        }
        album.removeSong(song);
        song.setAlbum(null);
    }

    @Override
    public List<Genre> getGenresForAlbum(Album album) {
        if (album == null) {
            throw new IllegalArgumentException("Album cannot be null");
        }

        Set<Genre> genres = new HashSet<>();
        for (Song song : album.getSongs()) {
            genres.add(song.getGenre());
        }

        List<Genre> genresList = new ArrayList<>(genres.size());
        genresList.addAll(genres);
        return genresList;
    }

    @Override
    public List<Album> getAlbumSample(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be a possitive number");
        }

        return albumDao.getAlbumSample(count);
    }
}
