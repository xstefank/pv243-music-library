/**
 * Created by Martin Styk on 18.04.2017.
 */
var websocketSession;

function f_onmessage(evt) {
    websocketMessages = document.getElementById('websocketMessages');
    websocketMessages.innerHTML = websocketMessages.innerHTML + evt.data + '<br/>';
}

function open() {
    if (!websocketSession) {
        websocketSession = new WebSocket('ws://127.0.0.1:8080/music/recommendations');
        websocketSession.onmessage = f_onmessage;
    }
}

function close() {
    if (websocketSession) {
        websocketSession.close();
    }
}