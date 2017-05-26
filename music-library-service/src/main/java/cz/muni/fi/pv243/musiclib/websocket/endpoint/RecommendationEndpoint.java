package cz.muni.fi.pv243.musiclib.websocket.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Recommendation;
import cz.muni.fi.pv243.musiclib.logging.MusicLibLogger;
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

    @OnOpen
    public void onOpen(Session session) {
        MusicLibLogger.LOGGER.info(RecommendationEndpoint.class.getSimpleName() + ": Connection established");
        sessionService.addSession(session);
        sendPushUpdate(recommendationService.getTopTenMostRecommendedLastDay(), session);
    }

    @OnClose
    public void onClose(Session session) {
        MusicLibLogger.LOGGER.info(RecommendationEndpoint.class.getSimpleName() + ": Disconnected");
        sessionService.removeSession(session);
    }

    @OnMessage
    public void onRecommendationMessage(long songId, Session session) {
        MusicLibLogger.LOGGER.info(RecommendationEndpoint.class.getSimpleName() + "::onRecommendationMessage");
        //TODO this will work when security is configured, use workaround
        //   String loggedUserName = session.getUserPrincipal().getName();
        String loggedUserName = "admin@musiclib.com";
        recommendationService.recommend(songId, loggedUserName);
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

}
