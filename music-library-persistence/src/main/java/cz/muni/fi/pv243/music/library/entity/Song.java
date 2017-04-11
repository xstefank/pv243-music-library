package cz.muni.fi.pv243.music.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String commentary;

    private int positionInAlbum;

    private double bitrate;

    private String youtubeLink;

    @ManyToOne
    private Album album;

    @NotNull
    @ManyToOne
    private Artist artist;

    @NotNull
    @ManyToOne
    private Genre genre;

    public Song() {
    }

    public Song(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public int getPositionInAlbum() {
        return positionInAlbum;
    }

    public void setPositionInAlbum(int positionInAlbum) {
        this.positionInAlbum = positionInAlbum;
    }

    public double getBitrate() {
        return bitrate;
    }

    public void setBitrate(double bitrate) {
        this.bitrate = bitrate;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        if(this.album == album) {
            return;
        } else if(album != null) {
            album.addSong(this);
        } else if (this.album != null) {
            this.album.removeSong(this);
        }
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
        if (artist != null) {
            artist.addSong(this);
        }
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Song)) return false;

        Song song = (Song) o;

        if (positionInAlbum != song.getPositionInAlbum()) return false;
        if (Double.compare(song.getBitrate(), bitrate) != 0) return false;
        if (title != null ? !title.equals(song.getTitle()) : song.getTitle() != null) return false;
        if (commentary != null ? !commentary.equals(song.getCommentary()) : song.getCommentary() != null) return false;
        if (youtubeLink != null ? !youtubeLink.equals(song.getYoutubeLink()) : song.getYoutubeLink()!= null) return false;
        if (album != null ? !album.equals(song.getAlbum()) : song.getAlbum() != null) return false;
        if (artist != null ? !artist.equals(song.getArtist()) : song.getArtist() != null) return false;
        return !(genre != null ? !genre.equals(song.getGenre()) : song.getGenre() != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (commentary != null ? commentary.hashCode() : 0);
        result = 31 * result + (youtubeLink != null ? youtubeLink.hashCode() : 0);
        result = 31 * result + positionInAlbum;
        temp = Double.doubleToLongBits(bitrate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
