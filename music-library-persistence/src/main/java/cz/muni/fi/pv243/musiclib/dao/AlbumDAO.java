package cz.muni.fi.pv243.musiclib.dao;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface AlbumDAO extends GenericDAO<Album, Long> {

    /**
     * Returns all albums containing given title fragment in their title
     *
     * @param titleFragment title fragment of albums to look for
     * @return list of all album entities containing given title fragment , empty if no such album exists
     */
    List<Album> searchByTitle(@NotNull String titleFragment);

    /**
     * Returns all albums of given artist
     *
     * @param artist artist of album
     * @return list of all albums by given artist
     */
    List<Album> searchByArtist(@NotNull Artist artist);


    /**
     * Returns the sample of albums stored in the DB. Returns up
     * to count albums or less if there is no sufficient ammount.
     *
     * @param count number of albums to return
     * @return list of sample albums
     */
    List<Album> getAlbumSample(int count);
}