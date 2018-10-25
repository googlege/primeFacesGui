package de.homedev.primefaces.frontend.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import de.homedev.primefaces.api.model.AppConstants;

@FacesValidator(value = "nachnameValidator")
public class NachnameValidator extends NotNullValidator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		super.validate(context, component, value);
		String nachname = (String) value;
		if ((nachname != null) && (nachname.length() > AppConstants.LASTNAME_MAX_LENGTH)) {
			FacesMessage message = new FacesMessage();
			String msgStr = "Max Länge:" + AppConstants.LASTNAME_MAX_LENGTH;
			message.setDetail(msgStr);
			message.setSummary(msgStr);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
