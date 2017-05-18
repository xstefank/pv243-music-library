package cz.muni.fi.pv243.musiclib.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.muni.fi.pv243.musiclib.util.LocalDateDeserializer;
import cz.muni.fi.pv243.musiclib.util.LocalDateSerializer;
import cz.muni.fi.pv243.musiclib.validation.PastDate;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Indexed
@Analyzer(definition = "entityAnalyzer")
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    @Field(analyze = Analyze.YES, index = Index.YES)
    private String name;

    @PastDate
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    @Field(analyze = Analyze.NO, index = Index.YES)
    private String commentary;

    public Artist() {
    }

    public Artist(Long id) {
        this();
        this.id = id;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(final String commentary) {
        this.commentary = commentary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, commentary);
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
        if ((commentary != null) ? !commentary.equals(other.getCommentary()) : other.getCommentary() != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", commentary=" + commentary +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private LocalDate dateOfBirth;
        private String commentary;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder commentary(String commentary) {
            this.commentary = commentary;
            return this;
        }

        public Artist build() {
            Artist artist = new Artist();

            artist.setName(name);
            artist.setDateOfBirth(dateOfBirth);
            artist.setCommentary(commentary);

            return artist;
        }

    }

}

