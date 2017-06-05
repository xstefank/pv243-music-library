package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.service.OverviewService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Path("/overview")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OverviewEndpoint {

    @Inject
    private OverviewService overviewService;

    @GET
    @Path("/artistSongs")
    public Response getArtistNumberSongs() {
        Map<Artist, Integer> data = overviewService.getArtistNumberSongs();

        Map<String, Integer> dataFormated = new HashMap<>(data.size());
        for (Map.Entry<Artist, Integer> entry : data.entrySet()) {
            dataFormated.put(entry.getKey().getName(), entry.getValue());
        }

        return Response.ok(dataFormated).build();
    }

    @GET
    @Path("/artistAlbums")
    public Response getArtistNumberAlbums() {
        Map<Artist, Integer> data = overviewService.getArtistNumberAlbums();

        Map<String, Integer> dataFormated = new HashMap<>(data.size());
        for (Map.Entry<Artist, Integer> entry : data.entrySet()) {
            dataFormated.put(entry.getKey().getName(), entry.getValue());
        }

        return Response.ok(dataFormated).build();
    }

    @GET
    @Path("/genreSongs")
    public Response getGenreNumberSongs() {
        Map<Genre, Integer> data = overviewService.getGenreNumberSongs();

        Map<String, Integer> dataFormated = new HashMap<>(data.size());
        for (Map.Entry<Genre, Integer> entry : data.entrySet()) {
            dataFormated.put(entry.getKey().getTitle(), entry.getValue());
        }
        return Response.ok(dataFormated).build();
    }
}
