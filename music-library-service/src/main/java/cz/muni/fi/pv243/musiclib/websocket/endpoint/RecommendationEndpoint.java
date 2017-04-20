package cz.muni.fi.pv243.musiclib.websocket.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.qualifier.RecommendationMessage;
import cz.muni.fi.pv243.musiclib.service.RecommendationService;
import cz.muni.fi.pv243.musiclib.websocket.service.SessionService;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Singleton
@ServerEndpoint("/recommendations")
public class RecommendationEndpoint {

    @Inject
    private SessionService sessionService;

    @Inject
    private RecommendationService recommendationService;


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

    @OnMessage
    public void onRecommendationMessage(long songId, Session session) {
        System.out.println("onRecommendationMessage");
        //TODO this will work when security is configured, use workaround
        //   String loggedUserName = session.getUserPrincipal().getName();
        String loggedUserName = "admin@musiclib.com";
        recommendationService.recommend(songId, loggedUserName);
    }


    public void onRecommend(@Observes @RecommendationMessage Map<Song, List<User>> mostRecommended) {
        System.out.println("Most Recommended");
        for (Map.Entry<Song, List<User>> entry : mostRecommended.entrySet()) {
            System.out.println(entry.getKey().getTitle());
            for (User u : entry.getValue()) {
                System.out.println(u.getEmail());
            }
            System.out.println("------------");
        }

        sendPushUpdate(mostRecommended);
    }

    private void sendPushUpdate(Map<Song, List<User>> mostRecommended) {
        sessionService.getAllSessions().stream()
                .forEach(session -> {
                    session.getAsyncRemote().sendObject(mostRecommended);
                });
    }
}
