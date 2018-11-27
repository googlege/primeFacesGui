package de.homedev.primefaces.api.converter;

import de.homedev.primefaces.api.enums.Rolle;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.Optional;

@Converter
public class RolleConverter implements AttributeConverter<Rolle, String> {

    @Override
    public String convertToDatabaseColumn(final Rolle rolle) {
        return Optional.ofNullable(rolle)
            .map(Rolle::name)
            .orElse(null);
    }

    @Override
    public Rolle convertToEntityAttribute(final String dbData) {
        Rolle result= Rolle.getEnum(dbData);
        if (result==null) {
            result=Rolle.UNKNOWN; 
            //TODO Log Warning or Error 
        }
        return result;
    }
}
