package cz.muni.fi.pv243.musiclib.websocket.service;

import javax.websocket.Session;
import java.util.Collection;

/**
 * Websocket session service
 *
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface SessionService {

    Collection<Session> getAllSessions();

    void addSession(Session session);

    void removeSession(Session session);
}
