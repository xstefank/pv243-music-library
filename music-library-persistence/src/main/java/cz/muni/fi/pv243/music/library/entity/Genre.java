package cz.muni.fi.pv243.music.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Genre implements UniqueId {

    @Id
    private String id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String title;

    public Genre() {
    }

    public Genre(String id) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + ((title == null) ? 0 : title.hashCode());
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

        final Genre other = (Genre) obj;

        if ((title != null) ? !title.equals(other.getTitle()) : other.getTitle() != null) {
            return false;
        }

        return true;
    }

}
