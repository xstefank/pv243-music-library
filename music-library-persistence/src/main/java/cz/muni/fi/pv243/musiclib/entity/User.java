package cz.muni.fi.pv243.musiclib.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Indexed
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    @Pattern(regexp = ".+@.+\\....?")
    @Field(analyze = Analyze.NO, index = Index.YES)
    private String email;

    @NotNull
    @JsonIgnore
    private String passwordHash;

    @NotEmpty
    @Field(analyze = Analyze.NO, index = Index.YES)
    private String firstName;

    @NotEmpty
    @Field(analyze = Analyze.NO, index = Index.YES)
    private String lastName;

    private boolean admin;

    @Embedded
    private MusicLibrary musicLibrary = new MusicLibrary();

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public MusicLibrary getMusicLibrary() {
        return musicLibrary;
    }

    public void setMusicLibrary(MusicLibrary musicLibrary) {
        this.musicLibrary = musicLibrary;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + ((email == null) ? 0 : email.hashCode());
        hash = 23 * hash + ((firstName == null) ? 0 : firstName.hashCode());
        hash = 23 * hash + ((lastName == null) ? 0 : lastName.hashCode());
        hash = 23 * hash + ((passwordHash == null) ? 0 : passwordHash.hashCode());
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
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        if ((email != null) ? !email.equals(other.getEmail()) : other.getEmail() != null) {
            return false;
        }

        if ((passwordHash != null) ? !passwordHash.equals(other.getPasswordHash()) : other.getPasswordHash() != null) {
            return false;
        }

        if ((firstName != null) ? !firstName.equals(other.getFirstName()) : other.getFirstName() != null) {
            return false;
        }

        if ((lastName != null) ? !lastName.equals(other.getLastName()) : other.getLastName() != null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", admin=" + admin +
                ", musicLibrary=" + musicLibrary +
                '}';
    }
}
