package cz.muni.fi.pv243.music.library.rest;

import cz.muni.fi.pv243.music.library.entity.*;
import cz.muni.fi.pv243.music.library.service.*;

import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

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
        return Response.status(Response.Status.OK).entity(albums).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Album album = albumService.findById(id);
        return Response.status(Response.Status.OK).entity(album).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Album createAlbum(Album album) {
        return albumService.create(album);
    }

    @DELETE
    @Path("{id}")
    public void removeAlbum(@PathParam("id") Long id) throws Exception {
        Album album = albumService.findById(id);
        albumService.remove(album);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Album update(@PathParam("id") Long id, Album album) {
        //TODO
        return null;
    }

}
