package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.service.ArtistService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by mstyk on 4/12/17.
 */
@Path("/artists")
public class ArtistEndpoint {

    @Inject
    ArtistService artistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("name") String name) {
        List<Artist> artists = null;
        if (name == null) {
            artists = artistService.findAll();
        } else {
           //TODO// albums = artistService.(title);
        }
        return Response.ok(artists).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Artist artist = artistService.findById(id);
        return Response.ok(artist).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createArtist(Artist artist) {
        Response.ResponseBuilder builder;
        try {
            Artist created = artistService.create(artist);
            builder = Response.ok(created);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @DELETE
    @Path("{id}")
    public Response removeArtist(@PathParam("id") Long id) throws Exception {
        Response.ResponseBuilder builder;
        Artist artist = artistService.findById(id);
        try {
            artistService.remove(artist);
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Artist artist) {
        Response.ResponseBuilder builder;
        Artist oldArtist = artistService.findById(id);
        artist.setId(oldArtist.getId());
        try {
            Artist updated = artistService.update(artist);
            builder = Response.ok(updated);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @GET
    @Path("{id}/bio")
    public Response getArtistsBio(@PathParam("id") Long id) throws Exception {
        Response.ResponseBuilder builder;
        Artist artist = artistService.findById(id);
        try {
            String bio = artistService.fetchArtistsBio(artist);
            builder = Response.ok(bio);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }


}
