package cz.muni.fi.pv243.music.library.dao.cache;

import cz.muni.fi.pv243.music.library.dao.*;
import cz.muni.fi.pv243.music.library.dao.qualifier.*;
import cz.muni.fi.pv243.music.library.entity.*;

import javax.ejb.*;
import javax.enterprise.context.*;
import javax.transaction.*;
import java.util.*;

/**
 * Created by mstyk on 4/12/17.
 */
@CacheDAO
@ApplicationScoped
public class AlbumDaoCacheImpl extends BaseCacheDao<Album> implements AlbumDAO {

    public AlbumDaoCacheImpl(){
        super(Album.class);
    }

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
