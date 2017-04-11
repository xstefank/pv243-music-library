package cz.muni.fi.pv243.music.library.rest;

import cz.muni.fi.pv243.music.library.dao.GenreDao;
import cz.muni.fi.pv243.music.library.entity.Genre;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest endpoint for genres
 */
@Path("/genres")
public class GenreEndpoint {

    @Inject
    private GenreDao genreDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genre> getMessages() {
        return genreDao.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMessage(Genre genre) {
        genreDao.create(genre);
    }

}
