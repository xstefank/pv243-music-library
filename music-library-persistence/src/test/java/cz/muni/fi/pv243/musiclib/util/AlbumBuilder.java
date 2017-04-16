package cz.muni.fi.pv243.musiclib.util;

import cz.muni.fi.pv243.musiclib.entity.Album;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class AlbumBuilder {

    private String title;

    private String commentary;

    private LocalDate dateOfRelease;

    private byte[] albumArt;

    private String albumArtMimeType;

    public AlbumBuilder() {
    }

    public AlbumBuilder title(String title) {
        this.title = title;
        return this;
    }

    public AlbumBuilder commentary(String commentary) {
        this.commentary = commentary;
        return this;
    }

    public AlbumBuilder dateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
        return this;
    }

    public AlbumBuilder albumArt(byte[] albumArt) {
        this.albumArt = Arrays.copyOf(albumArt, albumArt.length);
        return this;
    }

    public AlbumBuilder albumArtMimeType(String albumArtMimeType) {
        this.albumArtMimeType = albumArtMimeType;
        return this;
    }

    public Album build() {
        Album album = new Album();

        album.setTitle(this.title);
        album.setCommentary(this.commentary);
        album.setDateOfRelease(this.dateOfRelease);
        album.setAlbumArt(this.albumArt);
        album.setAlbumArtMimeType(this.albumArtMimeType);

        return album;
    }
}
