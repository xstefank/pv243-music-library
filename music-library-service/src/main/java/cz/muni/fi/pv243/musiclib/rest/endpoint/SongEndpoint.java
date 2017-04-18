package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.service.RecommendationService;
import cz.muni.fi.pv243.musiclib.service.SongService;

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
@Path("/song")
public class SongEndpoint {

    @Inject
    private SongService songService;

    @Inject
    private RecommendationService recommendationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongs(@QueryParam("title") String title) {
        List<Song> songs;
        if (title == null) {
            songs = songService.findAll();
        } else {
            songs = songService.searchByTitle(title);
        }
        return Response.ok(songs).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongForId(@PathParam("id") Long id) {
        Song song = songService.findById(id);
        return Response.ok(song).build();
    }

    @GET
    @Path("/{id}/recommend")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recommend(@PathParam("id") Long id) {
        Song song = songService.findById(id);
        recommendationService.recommend(song);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSong(Song song) {
        Response.ResponseBuilder builder;
        try {
            Song created = songService.create(song);
            builder = Response.ok(created);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeSong(@PathParam("id") Long id) throws Exception {
        Response.ResponseBuilder builder;
        Song song = songService.findById(id);
        try {
            songService.remove(song);
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSong(@PathParam("id") Long id, Song song) {
        Response.ResponseBuilder builder;
        Song oldValue = songService.findById(id);
        song.setId(oldValue.getId());
        try {
            Song updated = songService.update(song);
            builder = Response.ok(updated);
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }

    @GET
    @Path("/{id}/album")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbumForSong(@PathParam("id") Long id) {
        Album album = songService.getAlbumForId(id);
        return Response.ok(album).build();
    }

    @GET
    @Path("/{id}/artist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistForSong(@PathParam("id") Long id) {
        Artist artist = songService.getArtistForId(id);
        return Response.ok(artist).build();
    }

    @GET
    @Path("/{id}/genre")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenreForSong(@PathParam("id") Long id) {
        Genre genre = songService.getGenreForId(id);
        return Response.ok(genre).build();
    }
}
