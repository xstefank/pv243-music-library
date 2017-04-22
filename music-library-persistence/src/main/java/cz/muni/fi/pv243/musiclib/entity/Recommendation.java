package cz.muni.fi.pv243.musiclib.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "song_id"})})
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

    @NotNull
    private LocalDateTime time;

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
        if ((time != null) ? !time.equals(other.getTime()) : other.getTime() != null) {
            return false;
        }

        return true;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Song song;
        private User user;
        private LocalDateTime time;

        Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder song(Song song) {
            this.song = song;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder time(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public Recommendation build() {
            Recommendation recommendation = new Recommendation();
            recommendation.setId(id);
            recommendation.setUser(user);
            recommendation.setSong(song);
            recommendation.setTime(time);
            clear();
            return recommendation;
        }

        private void clear() {
            this.id = null;
            this.user = null;
            this.time = null;
            this.user = null;
        }
    }

    public static class Aggregate {
        private Song song;
        private List<User> users;

        public Aggregate(Song song, List<User> users) {
            this.song = song;
            this.users = users;
        }

        public Song getSong() {
            return song;
        }

        public List<User> getUsers() {
            return users;
        }
    }

}
