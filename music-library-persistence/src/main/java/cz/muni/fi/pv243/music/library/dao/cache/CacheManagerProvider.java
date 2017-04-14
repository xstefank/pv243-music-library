package cz.muni.fi.pv243.music.library.dao.cache;

import cz.muni.fi.pv243.music.library.entity.*;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.persistence.jpa.configuration.JpaStoreConfigurationBuilder;
import org.infinispan.transaction.TransactionMode;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Producer of CacheManager.
 * Cache configuration is done here. Cache is configured to use JPA cache store to store data.
 * We use simple names of entity classes as names of caches.
 * <p>
 * Created by mstyk on 4/12/17.
 */
@ApplicationScoped
public class CacheManagerProvider {

    private EmbeddedCacheManager manager;

    @Produces
    public EmbeddedCacheManager getCacheContainer() {
        if (manager == null) {
            GlobalConfiguration glob = new GlobalConfigurationBuilder()
                    .clusteredDefault().globalJmxStatistics().enable()
                    .build();

            Configuration defaultConfig = new ConfigurationBuilder()
                    .transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL).autoCommit(false)
                    .build();

            JpaStoreConfigurationBuilder jpaConfig = new ConfigurationBuilder()
                    .transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL).autoCommit(false)
                    .persistence().addStore(JpaStoreConfigurationBuilder.class)
                    .persistenceUnitName("primary")
                    .preload(true);

//            Configuration cacheConfig = new ConfigurationBuilder().persistence()
//                    .addStore(JpaStoreConfigurationBuilder.class)
//                    .persistenceUnitName("primary")
//                    .entityClass(User.class)
//                    .build();


            manager = new DefaultCacheManager(glob, defaultConfig);

            //define all caches - one for every entity
            manager.defineConfiguration(User.class.getSimpleName(), jpaConfig.entityClass(Song.class).build());
            manager.defineConfiguration(Song.class.getSimpleName(), jpaConfig.entityClass(Song.class).build());
            manager.defineConfiguration(Genre.class.getSimpleName(), jpaConfig.entityClass(Genre.class).build());
            manager.defineConfiguration(Artist.class.getSimpleName(), jpaConfig.entityClass(Artist.class).build());
            manager.defineConfiguration(Album.class.getSimpleName(), jpaConfig.entityClass(Album.class).build());
            manager.start();
        }
        return manager;
    }

//    @Produces
//    public EmbeddedCacheManager getCacheContainer() {
//        if (manager == null) {
//            GlobalConfiguration glob = new GlobalConfigurationBuilder()
//                    .globalJmxStatistics().allowDuplicateDomains(true)
//                    .build();
//
//            Configuration loc = new ConfigurationBuilder()
//                    .eviction().size(4).strategy(EvictionStrategy.LRU) // maximum 4 entries at the time in the cache
//                    .persistence().passivation(true).addSingleFileStore().purgeOnStartup(true) // evicted entries will be stored into cache store
//                    .build();
//
//            manager = new DefaultCacheManager(glob, loc);
//        }
//        return manager;
//    }

    @PreDestroy
    public void cleanUp() {
        manager.stop();
        manager = null;
    }
}
