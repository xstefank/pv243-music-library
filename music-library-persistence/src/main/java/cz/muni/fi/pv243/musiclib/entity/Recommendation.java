package cz.muni.fi.pv243.musiclib.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Recommendation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Song song;

    public Recommendation() {
    }

    public Recommendation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + ((user == null) ? 0 : user.hashCode());
        hash = 97 * hash + ((song == null) ? 0 : song.hashCode());
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
        if (!(obj instanceof Recommendation)) {
            return false;
        }

        final Recommendation other = (Recommendation) obj;

        if ((user != null) ? !user.equals(other.getUser()) : other.getUser() != null) {
            return false;
        }
        if ((song != null) ? !song.equals(other.getSong()) : other.getSong() != null) {
            return false;
        }

        return true;
    }

}
