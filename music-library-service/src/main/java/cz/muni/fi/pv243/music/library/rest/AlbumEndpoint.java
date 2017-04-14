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
@Path("/albums")
public class AlbumEndpoint {

    @CacheDAO
    @Inject
    AlbumDAO albumDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Album> getAll() {
        return albumDAO.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAlbum(Album album) {
        albumDAO.create(album);
    }
}
