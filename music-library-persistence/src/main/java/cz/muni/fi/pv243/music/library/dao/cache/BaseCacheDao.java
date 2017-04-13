package cz.muni.fi.pv243.music.library.dao.cache;

import cz.muni.fi.pv243.music.library.dao.GenericDAO;
import cz.muni.fi.pv243.music.library.entity.Album;
import cz.muni.fi.pv243.music.library.entity.UniqueId;
import cz.muni.fi.pv243.music.library.util.IdGenerator;
import org.infinispan.Cache;
import org.infinispan.query.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mstyk on 4/12/17.
 */
public class BaseCacheDao<T extends UniqueId> implements GenericDAO<T, String> {

    @Inject
    private CacheProvider cacheProvider;

    @Inject
    private IdGenerator idGenerator;

    private Class<? extends UniqueId> persistentClass;

    public BaseCacheDao() {
        this.persistentClass = getPersistentClass();
    }

    public void create(T t) {
        t.setId(idGenerator.next());
        cacheProvider.getCache(persistentClass).put(t.getId(), t);
    }

    public void remove(String id) {
        cacheProvider.getCache(persistentClass).remove(id);
    }


    public T find(String id) {
        QueryFactory qf = Search.getQueryFactory(cacheProvider.getCache(persistentClass));

        Query query = qf.from(persistentClass)
                .having("id").equal(id)
                .toBuilder()
                .build();

        List<T> searchResults = query.list();
        if (searchResults.isEmpty())
            return null;
        if (searchResults.size() > 1)
            throw new PersistenceException("More objects with id " + id);

        return searchResults.get(0);
    }


    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        Cache<String, T> cache = cacheProvider.getCache(persistentClass);
        for (String key : cache.keySet()) {
            result.add(cache.get(key));
        }

        return result;
    }


    public T update(T t) {
        return (T) cacheProvider.getCache(persistentClass).put(t.getId(), t);
    }

    protected Class<? extends UniqueId> getPersistentClass() {
        if (persistentClass == null) {
            persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return persistentClass;
    }

}
