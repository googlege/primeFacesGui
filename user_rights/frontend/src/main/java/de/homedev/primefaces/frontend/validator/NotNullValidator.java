package de.homedev.primefaces.frontend.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class NotNullValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if ((value == null) || (value.toString().isEmpty())) {
            String msgStr = "Bitte fuehlen Sie das Pflichtfeld aus.";
            FacesMessage message = new FacesMessage();
            message.setDetail(msgStr);
            message.setSummary(msgStr);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
