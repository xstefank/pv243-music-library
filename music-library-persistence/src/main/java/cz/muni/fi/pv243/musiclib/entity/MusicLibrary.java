package cz.muni.fi.pv243.musiclib.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Embeddable
public class MusicLibrary {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MusicLib", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> songs = new HashSet<>();

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public boolean addSong(Song song) {
        return songs.add(song);
    }

    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicLibrary that = (MusicLibrary) o;

        return songs != null ? songs.equals(that.songs) : that.songs == null;
    }

    @Override
    public int hashCode() {
        return songs != null ? songs.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MusicLibrary{" +
                "songs=" + songs +
                '}';
    }
}
