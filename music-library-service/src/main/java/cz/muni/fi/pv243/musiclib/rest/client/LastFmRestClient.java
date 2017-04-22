package cz.muni.fi.pv243.musiclib.rest.client;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Access REST API endpoints of Last.fm
 *
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
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

    public URL getAlbumPictureDownloadLink(String artistName, String albumName) throws MalformedURLException {
        Response response = ClientBuilder.newClient().target(REST_TARGET_URL)
                .queryParam("method", "album.getinfo")
                .queryParam("artist", artistName)
                .queryParam("album", albumName)
                .queryParam("api_key", API_KEY)
                .queryParam("format", "json")
                .request().get();

        JsonReader jsonReader = Json.createReader(new StringReader(response.readEntity(String.class)));
        JsonObject jsonObject = jsonReader.readObject();

        if (response.getStatus() != 200 || jsonObject.containsKey("error")) {
            return null;
        }

        JsonArray imageArray = jsonObject.getJsonObject("album").getJsonArray("image");
        String downloadLink = null;
        for (int i = 0; i < imageArray.size(); i++) {
            downloadLink = imageArray.getJsonObject(i).getString("#text");
            if (imageArray.getJsonObject(i).getString("size").contains("large")) {
                break;
            }
        }
        return new URL(downloadLink);
    }

}
