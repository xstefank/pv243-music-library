package cz.muni.fi.pv243.music.library.dao;

import cz.muni.fi.pv243.music.library.dao.GenericDAO;
import cz.muni.fi.pv243.music.library.entity.Album;

import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface AlbumDAO extends GenericDAO<Album, String> {

    /**
     * Returns all albums containing given title fragment in their title
     *
     * @param titleFragment title fragment of albums to look for
     * @return list of all album entities containing given title fragment , empty if no such album exists
     */
    List<Album> searchByTitle(String titleFragment);

    /**
     * Returns the sample of albums stored in the DB. Returns up
     * to count albums or less if there is no sufficient ammount.
     *
     * @param count number of albums to return
     * @return list of sample albums
     */
    List<Album> getAlbumSample(int count);
}