package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.service.AlbumService;

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
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Path("/albums")
public class AlbumEndpoint {

    @Inject
    AlbumService albumService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("title") String title) {
        List<Album> albums = null;
        if (title == null) {
            albums = albumService.findAll();
        } else {
            albums = albumService.searchByTitle(title);
        }
        return Response.ok(albums).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Album album = albumService.findById(id);
        return Response.ok(album).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAlbum(Album album) {
        Response.ResponseBuilder builder;
        try {
            Album created = albumService.create(album);
            builder = Response.ok(created);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }
    @DELETE
    @Path("{id}")
    public Response removeAlbum(@PathParam("id") Long id) throws Exception {
        Response.ResponseBuilder builder;
        Album album = albumService.findById(id);
        try {
            albumService.remove(album);
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
    public Response update(@PathParam("id") Long id, Album album) {
        Response.ResponseBuilder builder;
        Album oldAlbum = albumService.findById(id);
        album.setId(oldAlbum.getId());
        try {
            Album updated = albumService.update(album);
            builder = Response.ok(updated);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

}
