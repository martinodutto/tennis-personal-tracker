package com.martinodutto.tpt.security;

/**
 * Roles and authorities that the users of the application can have.
 * The id MUST be kept in sync with the ROLE_ID field of the Roles table
 * (see {@link com.martinodutto.tpt.database.mappers.RolesMapper}).
 */
public enum Authorities {
    ROLE_ADMIN(1),
    ROLE_USER(2);

    private int id;

    Authorities(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
