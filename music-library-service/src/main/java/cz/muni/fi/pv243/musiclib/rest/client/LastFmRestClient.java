package cz.muni.fi.pv243.musiclib.rest.client;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.StringReader;

/**
 * @author Martin Styk
 * @version 14.04.2017
 */
@RequestScoped
public class LastFmRestClient {
    private static final String REST_TARGET_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "0af72b19227c2a416d268f2fb768218b";

    public String getArtistBio(String artistName) {
        Response response = ClientBuilder.newClient().target(REST_TARGET_URL)
                .queryParam("method", "artist.getinfo")
                .queryParam("artist", artistName)
                .queryParam("api_key", API_KEY)
                .queryParam("format", "json")
                .request().get();

        JsonReader jsonReader = Json.createReader(new StringReader(response.readEntity(String.class)));
        JsonObject jsonObject = jsonReader.readObject();

        if (response.getStatus() != 200) {
            return null;
        }

        if (jsonObject.containsKey("error")) {
            return jsonObject.getString("message");
        } else {
            return jsonObject.getJsonObject("artist").getJsonObject("bio").getString("summary");
        }
    }

}
