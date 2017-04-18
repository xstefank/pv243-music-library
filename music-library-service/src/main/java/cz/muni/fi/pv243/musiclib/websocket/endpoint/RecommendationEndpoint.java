package cz.muni.fi.pv243.musiclib.websocket.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.qualifier.RecommendationMessage;
import cz.muni.fi.pv243.musiclib.websocket.service.SessionService;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Singleton
@ServerEndpoint("/recommendations")
public class RecommendationEndpoint {

    @Inject
    private SessionService sessionService;


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection established");
        sessionService.addSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected");
        sessionService.removeSession(session);
    }

    public void onRecommend(@Observes @RecommendationMessage Song song) {
        System.out.println("onRecommend event!" + song.getTitle());
        sendPushUpdate(song);
    }

    private void sendPushUpdate(Song song) {
        sessionService.getAllSessions().stream()
                .forEach(session -> {
                    session.getAsyncRemote().sendObject(song.getTitle());
                });
    }
}
