package cz.muni.fi.pv243.music.library.dao.impl;

import cz.muni.fi.pv243.music.library.dao.*;
import cz.muni.fi.pv243.music.library.dao.qualifier.*;
import cz.muni.fi.pv243.music.library.entity.*;

import javax.enterprise.context.*;
import javax.persistence.*;
import javax.transaction.*;
import java.util.*;

/**
 * Created by mstyk on 4/14/17.
 */
@JpaDAO
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class SongDaoImpl extends GenericDaoImpl<Song, Long> implements SongDao {

    public SongDaoImpl() {
        super(Song.class);
    }

    @Override
    public List<Song> searchByTitle(String titleFragment) {
        if (titleFragment == null) {
            throw new IllegalArgumentException("titleFragment cannot be null.");
        }
        return em.createQuery("SELECT s FROM Song s WHERE UPPER(s.title) LIKE '%'||:titleFragment||'%'", Song.class)
                .setParameter("titleFragment", titleFragment.toUpperCase())
                .getResultList();
    }

    @Override
    public List<Song> findByAlbum(Album album) {
        TypedQuery<Song> q = em.createQuery("SELECT s FROM Song s WHERE s.album = :albumId",
                Song.class).setParameter("albumId", album);
        return q.getResultList();
    }

    @Override
    public List<Song> findByMusician(Artist artist) {
        if (artist == null) {
            throw new IllegalArgumentException("artist cannot be null");
        }

        TypedQuery<Song> q = em.createQuery("SELECT s FROM Song s WHERE s.artist = :artistId",
                Song.class).setParameter("artistId", artist);
        return q.getResultList();
    }

    @Override
    public List<Song> findByGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("genre cannot be null");
        }

        TypedQuery<Song> q = em.createQuery("SELECT s FROM Song s WHERE s.genre = :genreId",
                Song.class).setParameter("genreId", genre);
        return q.getResultList();
    }
}
