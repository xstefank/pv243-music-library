package cz.muni.fi.pv243.musiclib.websocket.service;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 **/
@ApplicationScoped
public class SessionServiceImpl implements SessionService {

    private final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    public Set<Session> getAllSessions() {
        return Collections.unmodifiableSet(sessions);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
}
