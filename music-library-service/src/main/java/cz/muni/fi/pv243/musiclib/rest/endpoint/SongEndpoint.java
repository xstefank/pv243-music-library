package cz.muni.fi.pv243.musiclib.rest.endpoint;

import cz.muni.fi.pv243.musiclib.entity.Album;
import cz.muni.fi.pv243.musiclib.entity.Artist;
import cz.muni.fi.pv243.musiclib.entity.Genre;
import cz.muni.fi.pv243.musiclib.entity.Song;
import cz.muni.fi.pv243.musiclib.service.AlbumService;
import cz.muni.fi.pv243.musiclib.service.LibraryService;
import cz.muni.fi.pv243.musiclib.service.SongService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@Path("/song")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SongEndpoint {

    @Inject
    private SongService songService;

    @Inject
    private AlbumService albumService;

    @Inject
    private LibraryService libraryService;

    @GET
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
    public Response getSongForId(@PathParam("id") Long id) {
        Song song = songService.findById(id);
        return Response.ok(song).build();
    }

    @POST
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
    @Path("/user")
    public Response getSongForUser(@Context HttpServletRequest req) {
        List<Song> songs = libraryService.findSongsInUserLib(req.getRemoteUser());
        return Response.ok(songs).build();
    }

    @PUT
    @Path("/{id}/user")
    public Response addSongToUser(@PathParam("id") Long songId, @Context HttpServletRequest req) {
        Boolean added = libraryService.addSongToUserLib(songId, req.getRemoteUser());
        return Response.ok(added).build();
    }

    @DELETE
    @Path("/{id}/user")
    public Response removeSongFromUser(@PathParam("id") Long songId, @Context HttpServletRequest req) {
        Boolean added = libraryService.removeSongFromUserLib(songId, req.getRemoteUser());
        return Response.ok(added).build();
    }

    @GET
    @Path("/{id}/album")
    public Response getAlbumForSong(@PathParam("id") Long id) {
        Album album = songService.getAlbumForId(id);
        return Response.ok(album).build();
    }

    @GET
    @Path("/{id}/artist")
    public Response getArtistForSong(@PathParam("id") Long id) {
        Artist artist = songService.getArtistForId(id);
        return Response.ok(artist).build();
    }

    @GET
    @Path("/{id}/genre")
    public Response getGenreForSong(@PathParam("id") Long id) {
        Genre genre = songService.getGenreForId(id);
        return Response.ok(genre).build();
    }

    @GET
    @Path("/album/{id}")
    public Response getSongsByAlbum(@PathParam("id") Long id) {
        Response.ResponseBuilder builder;
        Album album = albumService.findById(id);
        if (album == null) {
            builder = Response.status(Response.Status.NOT_FOUND);
        } else {
            List<Song> songs = songService.searchByAlbum(album);
            builder = Response.ok(songs);
        }
        return builder.build();
    }
}
