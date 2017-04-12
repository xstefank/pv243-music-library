package cz.muni.fi.pv243.music.library.dao.cache;

import cz.muni.fi.pv243.music.library.dao.AlbumDAO;
import cz.muni.fi.pv243.music.library.dao.qualifier.CacheDAO;
import cz.muni.fi.pv243.music.library.entity.Album;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by mstyk on 4/12/17.
 */
@CacheDAO
@Stateless
public class AlbumDaoCacheImpl extends BaseCacheDao<Album> implements AlbumDAO {

    @Override
    public List<Album> searchByTitle(String titleFragment) {
        //TODO insert query
        return null;
    }

    @Override
    public List<Album> getAlbumSample(int count) {
        //TODO insert query
        return null;
    }
}
