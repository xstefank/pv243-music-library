package cz.muni.fi.pv243.musiclib.websocket.service;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.Index;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.websocket.Session;

/**
 * Infinispan cache manager producer for websocket sessions.
 *
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@ApplicationScoped
public class SessionCacheConfiguration {

    private static final String SESSION_CACHE_NAME = "SESSION_CACHE";

    private EmbeddedCacheManager manager;

    @Produces
    public Cache<String, Session> sessionCache() {
        if (manager == null)
            getCacheContainer();
        return manager.getCache(SESSION_CACHE_NAME);
    }


    public void getCacheContainer() {
        GlobalConfiguration glob = new GlobalConfigurationBuilder()
                .globalJmxStatistics().allowDuplicateDomains(true)
                .build();

        Configuration carCacheConfig = new ConfigurationBuilder().jmxStatistics().enable()
                .clustering().cacheMode(CacheMode.LOCAL)
                .indexing().index(Index.LOCAL) // enabled local indexing to allow querying
                .build();

        manager = new DefaultCacheManager(glob);
        manager.defineConfiguration(SESSION_CACHE_NAME, carCacheConfig);
        manager.start();
    }

    @PreDestroy
    public void cleanUp() {
        manager.stop();
        manager = null;
    }
}
