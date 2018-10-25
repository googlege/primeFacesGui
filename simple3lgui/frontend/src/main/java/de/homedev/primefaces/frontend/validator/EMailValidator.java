package de.homedev.primefaces.frontend.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import de.homedev.primefaces.api.model.AppConstants;

@FacesValidator(value = "emailValidator")
public class EMailValidator extends NotNullValidator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		super.validate(context, component, value);
		String eMail = (String) value;
		int atIndex = eMail.indexOf("@");
		String msgStr = "Keine gültige EMail.";
		if (atIndex == -1) {
			FacesMessage message = new FacesMessage();
			message.setDetail(msgStr);
			message.setSummary(msgStr);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		int dotIndexAfterAt = eMail.indexOf(".", atIndex);
		if (dotIndexAfterAt == -1) {
			FacesMessage message = new FacesMessage();
			message.setDetail(msgStr);
			message.setSummary(msgStr);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if ((eMail != null) && (eMail.length() > AppConstants.EMAIL_MAX_LENGTH)) {
			FacesMessage message = new FacesMessage();
			msgStr = "Max Länge:" + AppConstants.EMAIL_MAX_LENGTH;
			message.setDetail(msgStr);
			message.setSummary(msgStr);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
