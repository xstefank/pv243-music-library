package cz.muni.fi.pv243.musiclib.entity;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Indexed
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Field(analyze = Analyze.YES, index = Index.YES)
    private String title;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
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
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

        if (title != null ? !title.equals(song.getTitle()) : song.getTitle() != null) return false;
        if (youtubeLink != null ? !youtubeLink.equals(song.getYoutubeLink()) : song.getYoutubeLink() != null)
            return false;
        if (album != null ? !album.equals(song.getAlbum()) : song.getAlbum() != null) return false;
        if (artist != null ? !artist.equals(song.getArtist()) : song.getArtist() != null) return false;
        return !(genre != null ? !genre.equals(song.getGenre()) : song.getGenre() != null);

    }

    @Override
    public int hashCode() {
        int result;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (youtubeLink != null ? youtubeLink.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", album=" + album +
                ", artist=" + artist +
                ", genre=" + genre +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String title;
        private Album album;
        private Artist artist;
        private Genre genre;

        Builder() {
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder album(Album album) {
            this.album = album;
            return this;
        }

        public Builder artist(Artist artist) {
            this.artist = artist;
            return this;
        }

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Song build() {
            Song s = new Song();

            s.setTitle(title);
            s.setAlbum(album);
            s.setArtist(artist);
            s.setGenre(genre);

            clear();

            return s;
        }

        private void clear() {
            this.title = null;
            this.album = null;
            this.artist = null;
            this.genre = null;
        }

    }

}
