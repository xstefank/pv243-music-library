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
public class GenreDaoCacheImpl extends BaseCacheDao<Genre> implements GenreDAO {

    public GenreDaoCacheImpl(){
        super(Genre.class);
    }

    @Override
    public List<Genre> searchByTitle(String titleFragment) {
        //TODO insert query
        return null;
    }
}
