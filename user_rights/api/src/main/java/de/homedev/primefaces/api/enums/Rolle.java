
package de.homedev.primefaces.api.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Rolle {
    ADMIN("ROLE_ADMIN",
        new CurrentPermission(Permission.BLZ_READ, false, true),
        new CurrentPermission(Permission.BLZ_WRITE, false, true),
        new CurrentPermission(Permission.BLZ_DELETE, false, true)
        ),
    USER("ROLE_USER",
        new CurrentPermission(Permission.BLZ_READ, false, true),
        new CurrentPermission(Permission.BLZ_WRITE, false, true),
        new CurrentPermission(Permission.BLZ_DELETE, false, true)
        ),
    UNKNOWN("UNKNOWN");
    private final String authority;
    private final CurrentPermission[] permissions;

    private Rolle(String authority, final CurrentPermission... permissions) {
        this.authority = authority;
        this.permissions = permissions;
    }

    public String getAuthority() {
        return authority;
    }
    
    public GrantedAuthority getGrantedAuthority() {
        return new SimpleGrantedAuthority(this.authority);
    }
    
    public CurrentPermission[] getPermissions() {
        return permissions;
    }
    
    public CurrentPermission findPermission(Permission permission) {
        for(CurrentPermission perm:permissions) {
            if (perm.getPermission()==permission) {
                return perm;
            }
        }
        return null;
    }

    public static Rolle getEnum(String authority) {
        for (Rolle e : Rolle.values()) {
            if (e.getAuthority().equalsIgnoreCase(authority)) {
                return e;
            }
        }
        return null;
    }

}
