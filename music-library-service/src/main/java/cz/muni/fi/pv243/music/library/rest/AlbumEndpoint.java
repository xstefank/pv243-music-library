package cz.muni.fi.pv243.music.library.rest;

import cz.muni.fi.pv243.music.library.dao.AlbumDAO;
import cz.muni.fi.pv243.music.library.dao.qualifier.CacheDAO;
import cz.muni.fi.pv243.music.library.entity.Album;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public List<Album> getAll(){
        return albumDAO.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void createAlbum(Album album){
        albumDAO.create(album);
    }
}
