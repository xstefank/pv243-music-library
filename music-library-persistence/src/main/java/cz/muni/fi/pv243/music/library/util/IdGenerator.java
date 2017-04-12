package cz.muni.fi.pv243.music.library.util;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

/**
 * Created by mstyk on 4/12/17.
 */
@ApplicationScoped
public class IdGenerator {

    public String next() {
        return UUID.randomUUID().toString();
    }
}
