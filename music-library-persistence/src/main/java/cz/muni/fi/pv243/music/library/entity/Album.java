package cz.muni.fi.pv243.music.library.entity;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Indexed
public class Album implements UniqueId {

    @Id
    private String id;

    @Field
    @NotNull
    @Column(nullable = false, unique = true)
    private String title;

    @Field(analyze = Analyze.NO)
    private String commentary;

    @Field(analyze = Analyze.NO)
    @DateBridge(resolution = Resolution.DAY)
    @Temporal(TemporalType.DATE)
    private Date dateOfRelease;

    @Lob
    @Field(analyze = Analyze.NO, index = Index.NO)
    private byte[] albumArt;

    @Field(analyze = Analyze.NO, index = Index.NO)
    private String albumArtMimeType;

    @IndexedEmbedded
    @OneToMany(mappedBy = "album")
    private List<Song> songs = new ArrayList<>();

    public Album() {
    }

    public Album(String id) {
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public byte[] getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(byte[] albumArt) {
        this.albumArt = albumArt;
    }

    public String getAlbumArtMimeType() {
        return albumArtMimeType;
    }

    public void setAlbumArtMimeType(String albumArtMimeType) {
        this.albumArtMimeType = albumArtMimeType;
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public void setSongs(List<Song> songs) {
        //Collections.copy(this.songs, songs);
        this.songs = new ArrayList<>(songs);
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + ((title == null) ? 0 : title.hashCode());
        hash = 53 * hash + ((commentary == null) ? 0 : commentary.hashCode());
        hash = 53 * hash + ((dateOfRelease == null) ? 0 : dateOfRelease.hashCode());
        hash = 53 * hash + ((albumArtMimeType == null) ? 0 : albumArtMimeType.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Album)) {
            return false;
        }
        final Album other = (Album) obj;
        if ((title != null) ? !title.equals(other.getTitle()) : other.getTitle() != null) {
            return false;
        }

        if ((commentary != null) ? !commentary.equals(other.getCommentary()) : other.getCommentary() != null) {
            return false;
        }

        if ((dateOfRelease != null) ? !dateOfRelease.equals(other.getDateOfRelease()) : other.getDateOfRelease() != null) {
            return false;
        }

        if ((albumArt != null) ? !albumArt.equals(other.getAlbumArt()) : other.getAlbumArt() != null) {
            return false;
        }

        if ((albumArtMimeType != null) ? !albumArtMimeType.equals(other.getAlbumArtMimeType()) : other.getAlbumArtMimeType() != null) {
            return false;
        }
        return true;
    }
}
