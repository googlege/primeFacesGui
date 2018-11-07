package de.homedev.primefaces.frontend.bean;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;

import de.homedev.primefaces.api.fassade.IPersonFassade;
import de.homedev.primefaces.api.model.PersonEntity;
import de.homedev.primefaces.api.util.ApiUtil;
import de.homedev.primefaces.frontend.config.FrontendSettings;
import de.homedev.primefaces.frontend.util.RmiClientUtil;

@ManagedBean(name = "simplePersonBean")
@ViewScoped
public class SimplePersonBean implements Serializable {
    private static final long serialVersionUID = 1L;
    protected static final String ERROR_TO_VIEW_ID = "STAYONTHESAMEPAGE";
    private static Logger log = Logger.getLogger(SimplePersonBean.class.getName());
    private PersonEntity personEntity;

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {

        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }

    public PersonEntity getPersonDto() {
        if (personEntity == null) {
            personEntity = new PersonEntity();
        }
        return personEntity;
    }

    protected boolean validate() {
        return true;
    }

    public void toBestaetigung() throws Exception {
        try {
            boolean validationResult = validate();
            if (!validationResult) {
                return;
            }
            IPersonFassade fassade = RmiClientUtil.getPersonFassade(FrontendSettings.getSettings());
            personEntity = fassade.save(personEntity);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null,
                "/bestaetigung.jsf?faces-redirect=true");
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void toPerson() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/person.jsf?faces-redirect=true");
    }

    public String toPersonXML() throws JAXBException {
        return ApiUtil.objectToXMLFile(personEntity);
    }

}
