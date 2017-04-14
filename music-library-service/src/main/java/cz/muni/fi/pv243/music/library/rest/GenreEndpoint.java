package cz.muni.fi.pv243.music.library.rest;

import cz.muni.fi.pv243.music.library.dao.*;
import cz.muni.fi.pv243.music.library.dao.qualifier.*;
import cz.muni.fi.pv243.music.library.entity.*;

import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * Created by mstyk on 4/12/17.
 */
@Path("/genres")
public class GenreEndpoint {

    @JpaDAO
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
