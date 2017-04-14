package cz.muni.fi.pv243.music.library.rest;

import cz.muni.fi.pv243.music.library.entity.Album;
import cz.muni.fi.pv243.music.library.service.AlbumService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by mstyk on 4/12/17.
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
