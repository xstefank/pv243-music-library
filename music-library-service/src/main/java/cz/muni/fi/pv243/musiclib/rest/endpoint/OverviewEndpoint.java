package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Artist;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Path("/overview")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OverviewEndpoint {

    @GET
    @Path("")
    public Response getArtists() {
        List<Artist> artists;
        if (name == null) {
            artists = artistService.findAll();
        } else {
            artists = artistService.searchByName(name);
        }
        return Response.ok(artists).build();
    }
}
