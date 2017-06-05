package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Role;
import cz.muni.fi.pv243.musiclib.service.AlbumService;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.annotation.security.RolesAllowed;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static cz.muni.fi.pv243.musiclib.entity.Role.ALLOW_ADMIN;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Path("/album")
public class AlbumEndpoint {

    @Inject
    private AlbumService albumService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbums(@QueryParam("title") String title) {
        List<Album> albums;
        if (title == null) {
            albums = albumService.findAll();
        } else {
            albums = albumService.searchByTitle(title);
        }
        return Response.ok(albums).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbumById(@PathParam("id") Long id) {
        Album album = albumService.findById(id);
        return Response.ok(album).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(ALLOW_ADMIN)
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
    @RolesAllowed(ALLOW_ADMIN)
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
    @RolesAllowed(ALLOW_ADMIN)
    public Response updateAlbum(@PathParam("id") Long id, Album album) {
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

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed(ALLOW_ADMIN)
    public Response uploadFile(MultipartFormDataInput input) {

        byte[] albumArt = new byte[0];

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        Iterator<Map.Entry<String, List<InputPart>>> iterator = uploadForm.entrySet().iterator();
        Map.Entry<String, List<InputPart>> first = iterator.next();
        List<InputPart> inputParts = first.getValue();

        for (InputPart inputPart : inputParts) {
            try {

                //convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                albumArt = IOUtils.toByteArray(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Map.Entry<String, List<InputPart>> id = iterator.next();
        List<InputPart> idInputParts = id.getValue();
        try {
            final StringBuilder builder = new StringBuilder();
            for (InputPart inputPart : idInputParts) {
                builder.append(inputPart.getBody(String.class, null));
            }

            Album album = albumService.findById(Long.valueOf(builder.toString()));
            album.setAlbumArt(albumArt);
            albumService.update(album);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.status(200)
                .entity("Album art was uploaded.").build();
    }

}