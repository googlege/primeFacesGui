package de.homedev.primefaces.api.enums;

/**
 * Current user static (default) permission
 * @author mikhavia
 *
 */
public final class CurrentPermission {
    private final Permission permission;
    private final boolean editable;
    private final boolean permited;

    /**
     *
     * @param permission - permission object
     * @param editable - true/false - permission could be changed on not
     * @param permited - if permited is true and RemovedPermission exists than object user permission is false
     *                   if permited is false and RemovedPermission exists than object user permission is true.
     * (this allows that object permission per default could be disabled)
     */
    public CurrentPermission(final Permission permission, final boolean editable, final boolean permited) {
        super();
        this.permission = permission;
        this.editable = editable;
        this.permited = permited;
    }

    public Permission getPermission() {
        return permission;
    }

    public boolean isEditable() {
        return editable;
    }

    public boolean isPermited() {
        return permited;
    }



}
