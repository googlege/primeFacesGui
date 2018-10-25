package de.homedev.primefaces.frontend.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import de.homedev.primefaces.api.model.AppConstants;

@FacesValidator(value = "telefonValidator")
public class TelefonValidator extends NotNullValidator {
	private static final char[] validCharacters = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', ' ', '+', '(', ')' };

	public static boolean isCharacherValid(char ch) {
		for (int i = 0; i < validCharacters.length; i++) {
			if (ch == validCharacters[i])
				return true;
		}
		return false;
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		super.validate(context, component, value);
		String telefon = (String) value;
		for (int i = 0; i < telefon.length(); i++) {
			char ch = telefon.charAt(i);
			if (!isCharacherValid(ch)) {
				String msgStr = "Telefonnummer ungultig.(Zeichen: " + ch + " nicht erlaubt)";
				FacesMessage message = new FacesMessage();
				message.setDetail(msgStr);
				message.setSummary(msgStr);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
			if ((telefon != null) && (telefon.length() > AppConstants.LASTNAME_MAX_LENGTH)) {
				FacesMessage message = new FacesMessage();
				String msgStr = "Max Länge:" + AppConstants.LASTNAME_MAX_LENGTH;
				message.setDetail(msgStr);
				message.setSummary(msgStr);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}
}