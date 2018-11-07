package de.homedev.primefaces.frontend.common.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import de.homedev.primefaces.frontend.common.IJsfController;

public abstract class AbstractBeanController implements IJsfController {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public String localize(String i18nKey) {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle text = ResourceBundle.getBundle("messages", locale);
        return text.getString(i18nKey);
    }

}
