package cz.muni.fi.pv243.music.library.rest.endpoint;

import cz.muni.fi.pv243.music.library.dao.GenreDAO;
import cz.muni.fi.pv243.music.library.entity.Genre;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by mstyk on 4/12/17.
 */
@Path("/genres")
public class GenreEndpoint {

    @Inject
    GenreDAO genreDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genre> getAll() {
        return genreDAO.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createGenre(Genre genre) {
        genreDAO.create(genre);
    }
}
