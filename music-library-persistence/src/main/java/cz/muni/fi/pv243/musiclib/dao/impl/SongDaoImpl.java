package cz.muni.fi.pv243.musiclib.dao.impl;

import cz.muni.fi.pv243.musiclib.dao.SongDao;
import cz.muni.fi.pv243.musiclib.dao.UserDao;
import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@ApplicationScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class SongDaoImpl extends GenericDaoImpl<Song, Long> implements SongDao, Serializable {

    public SongDaoImpl() {
        super(Song.class);
    }

    @Inject
    private UserDao userDao;

    @Override
    public List<Song> searchByTitle(@NotNull String titleFragment) {
        return em.createQuery("SELECT s FROM Song s WHERE UPPER(s.title) LIKE '%'||:titleFragment||'%'", Song.class)
                .setParameter("titleFragment", titleFragment.toUpperCase())
                .getResultList();
    }

    @Override
    public void remove(Long id) {
        //first remove all entries from music lib join table referencing this song
        Query query = em.createNativeQuery("DELETE FROM MusicLib lib WHERE lib.song_id = :songId",
                User.class).setParameter("songId", id);
        query.executeUpdate();
        em.remove(this.em.getReference(Song.class, id));
    }

    @Override
    public List<Song> searchByAlbum(@NotNull Album album) {
        TypedQuery<Song> q = em.createQuery("SELECT s FROM Song s WHERE s.album = :albumId",
                Song.class).setParameter("albumId", album);
        return q.getResultList();
    }

    @Override
    public List<Song> searchByArtist(@NotNull Artist artist) {
        TypedQuery<Song> q = em.createQuery("SELECT s FROM Song s WHERE s.artist = :artistId",
                Song.class).setParameter("artistId", artist);
        return q.getResultList();
    }

    @Override
    public List<Song> searchByGenre(@NotNull Genre genre) {
        TypedQuery<Song> q = em.createQuery("SELECT s FROM Song s WHERE s.genre = :genreId",
                Song.class).setParameter("genreId", genre);
        return q.getResultList();
    }
}
