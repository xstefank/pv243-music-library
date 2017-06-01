package cz.muni.fi.pv243.musiclib.entity;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public enum Role {

    ADMIN("ADMIN"),
    USER("USER"),
    GUEST("GUEST")
    ;

    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
