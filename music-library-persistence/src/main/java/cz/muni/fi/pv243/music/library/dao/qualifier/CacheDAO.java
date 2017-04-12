package cz.muni.fi.pv243.music.library.dao.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@Qualifier
@Documented
@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheDAO {
}
