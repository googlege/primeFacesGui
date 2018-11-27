package de.homedev.primefaces.api.enums;

import java.util.Arrays;

public enum Permission {

    BLZ_READ(0),
    BLZ_WRITE(1),
    BLZ_DELETE(2);
    

    private final long id;

    private Permission(final long id) {
        this.id=id;
    }

    public long getId() {
        return id;
    }

    public static Permission getEnum(final long id) {
        return Arrays.stream(Permission.values()).filter(obj->{return obj.getId()==id;}).findFirst().orElse(null);
    }


}
