package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.service.GenreService;

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
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@Path("/genre")
public class GenreEndpoint {

    @Inject
    private GenreService genreService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGenre(Genre genre) {
        Response.ResponseBuilder builder;
        try {
            Genre created = genreService.create(genre);
            builder = Response.ok(created);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGenre(@PathParam("id") Long id, Genre genre) {
        Response.ResponseBuilder builder;
        Genre oldValue = genreService.findById(id);
        genre.setId(oldValue.getId());
        try {
            Genre updated = genreService.update(genre);
            builder = Response.ok(updated);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @DELETE
    @Path("{id}")
    public Response removeGenre(@PathParam("id") Long id) {
        Response.ResponseBuilder builder;
        try {
            genreService.remove(genreService.findById(id));
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenreByTitle(@QueryParam("title") String title) {
        List<Genre> genres;
        if (title == null) {
            genres = genreService.findAll();
        } else {
            genres = genreService.searchByTitle(title);
        }
        return Response.ok(genres).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenreById(@PathParam("id") Long id) {
        Genre genre = genreService.findById(id);
        return Response.ok(genre).build();
    }
}
