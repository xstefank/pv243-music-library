package cz.muni.fi.pv243.musiclib.entity;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public enum Role {

    ADMIN,
    USER,
    SUPER_USER,
    GUEST;

    // We can use constants in @RolesAllowed annotations
    public static final String ALLOW_ADMIN = "ADMIN";
    public static final String ALLOW_USER =  "USER";
    public static final String ALLOW_GUEST = "GUEST";
    public static final String ALLOW_SUPER_USER = "SUPER_USER";

}
