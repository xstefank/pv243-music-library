package cz.muni.fi.pv243.music.library.dao.cache;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Access point to our caches.
 *
 * Created by mstyk on 4/12/17.
 */
@ApplicationScoped
public class CacheProvider {

    @Inject
    private EmbeddedCacheManager cacheManager;

    public <String, T> Cache<String, T> getCache(Class<?> type) {
        return cacheManager.getCache(type.getSimpleName());
    }
}
