package de.homedev.primefaces.api.converter;

import de.homedev.primefaces.api.enums.Permission;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PermissionConverter implements AttributeConverter<Permission, Long> {

    @Override
    public Long convertToDatabaseColumn(final Permission permission) {
        return permission.getId();
    }

    @Override
    public Permission convertToEntityAttribute(final Long dbData) {
        final Permission result = Permission.getEnum(dbData);
        if (result == null) {
            throw new RuntimeException("Can not find permission with id:" + dbData);
        }
        return result;
    }
}
