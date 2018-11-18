package de.homedev.primefaces.frontend.converter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Simple converter that convert inputs to upper case
 */
@FacesConverter("toUpperCaseConverter")
@ApplicationScoped
public class ToUpperCaseConverter implements Converter {

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String valueAsString = (value != null) ? value.toString().toUpperCase().trim() : null;

        return valueAsString;
    }

    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String valueAsObject = (value != null) ? value.toUpperCase().trim() : null;

        return valueAsObject;
    }

}
