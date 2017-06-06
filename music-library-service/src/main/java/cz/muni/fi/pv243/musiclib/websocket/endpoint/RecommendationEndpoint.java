package cz.muni.fi.pv243.musiclib.websocket.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.entity.Role;
import cz.muni.fi.pv243.musiclib.logging.LogMessages;
import cz.muni.fi.pv243.musiclib.qualifier.RecommendationMessage;
import cz.muni.fi.pv243.musiclib.service.RecommendationService;
import cz.muni.fi.pv243.musiclib.service.UserService;
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

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Singleton
@ServerEndpoint(value = "/recommendations", encoders = RecommendationSerializer.class)
public class RecommendationEndpoint {

    @Inject
    private SessionService sessionService;

    @Inject
    private RecommendationService recommendationService;

    @Inject
    private UserService userService;

    @OnOpen
    public void onOpen(Session session) {
        LogMessages.LOGGER.logWebsocketConnect(getClass().getSimpleName());
        sessionService.addSession(session);
        sendPushUpdate(recommendationService.getTopTenMostRecommendedLastDay(), session);
    }

    @OnClose
    public void onClose(Session session) {
        LogMessages.LOGGER.logWebsocketDisconnect(getClass().getSimpleName());
        sessionService.removeSession(session);
    }

    @OnMessage
    public void onRecommendationMessage(long songId, Session session) {
        LogMessages.LOGGER.logMethodEntered(getClass().getSimpleName(), "onRecommendationMessage");
        String loggedUserName = session.getUserPrincipal().getName();
        if(isUserInRole(loggedUserName, Role.ADMIN) || isUserInRole(loggedUserName, Role.SUPER_USER)) {
            recommendationService.recommend(songId, loggedUserName);
        }
    }

    public void onRecommend(@Observes @RecommendationMessage List<Recommendation.Aggregate> mostRecommended) {
        sendPushUpdate(mostRecommended);
    }

    private void sendPushUpdate(List<Recommendation.Aggregate> mostRecommended) {
        sessionService.getAllSessions().stream()
                .forEach(session -> {
                    session.getAsyncRemote().sendObject(mostRecommended);
                });
    }

    private void sendPushUpdate(List<Recommendation.Aggregate> mostRecommended, Session session) {
        session.getAsyncRemote().sendObject(mostRecommended);
    }

    private boolean isUserInRole(String loggedUserName, Role admin) {
        return userService.findByEmail(loggedUserName).getRole().equals(admin);
    }

}
