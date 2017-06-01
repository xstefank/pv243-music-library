package cz.muni.fi.pv243.musiclib.entity;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public enum Role {

    ADMIN("admin"),
    USER("user"),
    GUEST("guest")
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
