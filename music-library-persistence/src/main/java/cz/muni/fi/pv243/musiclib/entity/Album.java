package cz.muni.fi.pv243.musiclib.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.muni.fi.pv243.musiclib.util.LocalDateDeserializer;
import cz.muni.fi.pv243.musiclib.util.LocalDateSerializer;
import cz.muni.fi.pv243.musiclib.validation.PastDate;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

@AnalyzerDef(name = "entityAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = StandardFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = StopFilterFactory.class),
        }
)

@Entity
@Indexed
@Analyzer(definition = "entityAnalyzer")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    @Field(analyze = Analyze.YES, index = Index.YES)
    private String title;

    @ManyToOne
    private Artist artist;

    @Field(analyze = Analyze.NO, index = Index.YES)
    private String commentary;

    @PastDate
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfRelease;

    @Lob
    private byte[] albumArt;

    public Album() {
    }

    public Album(Long id) {
        this();
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public byte[] getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(byte[] albumArt) {
        this.albumArt = albumArt;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + ((title == null) ? 0 : title.hashCode());
        hash = 53 * hash + ((commentary == null) ? 0 : commentary.hashCode());
        hash = 53 * hash + ((artist == null) ? 0 : artist.hashCode());
        hash = 53 * hash + ((dateOfRelease == null) ? 0 : dateOfRelease.hashCode());
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

        if ((artist != null) ? !artist.equals(other.getArtist()) : other.getArtist() != null) {
            return false;
        }

        if ((dateOfRelease != null) ? !dateOfRelease.equals(other.getDateOfRelease()) : other.getDateOfRelease() != null) {
            return false;
        }

        return true;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String title;

        private String commentary;

        private LocalDate dateOfRelease;

        private byte[] albumArt;

        Builder() {
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder commentary(String commentary) {
            this.commentary = commentary;
            return this;
        }

        public Builder dateOfRelease(LocalDate dateOfRelease) {
            this.dateOfRelease = dateOfRelease;
            return this;
        }

        public Builder albumArt(byte[] albumArt) {
            this.albumArt = Arrays.copyOf(albumArt, albumArt.length);
            return this;
        }

        public Album build() {
            Album album = new Album();

            album.setTitle(this.title);
            album.setCommentary(this.commentary);
            album.setDateOfRelease(this.dateOfRelease);
            album.setAlbumArt(this.albumArt);

            return album;
        }
    }
}
