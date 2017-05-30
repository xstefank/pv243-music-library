package cz.muni.fi.pv243.musiclib.websocket.service;

import cz.muni.fi.pv243.musiclib.logging.LogMessages;
import org.infinispan.Cache;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.Session;
import java.util.Collection;
import java.util.Collections;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 **/
@ApplicationScoped
public class SessionServiceImpl implements SessionService {

    @Inject
    private Cache<String, Session> sessions;

    public Collection<Session> getAllSessions() {
        return Collections.unmodifiableCollection(sessions.values());
    }

    public void addSession(Session session) {
        sessions.put(session.getId(), session);
        LogMessages.LOGGER.logAddSession(session.getId(), sessions.size());
    }

    public void removeSession(Session session) {
        sessions.remove(session.getId());
        LogMessages.LOGGER.logRemoveSession(session.getId(), sessions.size());
    }
}
