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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    @PastDate
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

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

