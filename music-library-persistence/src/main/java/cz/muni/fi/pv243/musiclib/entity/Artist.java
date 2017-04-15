package cz.muni.fi.pv243.musiclib.entity;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Indexed
@Analyzer(definition = "entityAnalyzer")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    @Field(analyze = Analyze.YES, index = Index.YES)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "artist")
    private List<Song> songs = new ArrayList<>();

    public Artist() {
        this.songs = new ArrayList<>();
    }

    public Artist(Long id) {
        this();
        this.id = id;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + ((name == null) ? 0 : name.hashCode());
        hash = 23 * hash + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Artist)) {
            return false;
        }
        final Artist other = (Artist) obj;
        if ((name != null) ? !name.equals(other.getName()) : other.getName() != null) {
            return false;
        }
        if ((dateOfBirth != null) ? !dateOfBirth.equals(other.getDateOfBirth()) : other.getDateOfBirth() != null) {
            return false;
        }

        return true;
    }

}

