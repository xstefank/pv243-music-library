package cz.muni.fi.pv243.musiclib.entity;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Indexed
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    @Field(analyze = Analyze.NO, index = Index.YES)
    private String title;

    public Genre() {
    }

    public Genre(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
