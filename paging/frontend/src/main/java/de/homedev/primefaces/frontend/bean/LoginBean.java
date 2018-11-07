package de.homedev.primefaces.frontend.bean;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;

import de.homedev.primefaces.api.fassade.IUserFassade;
import de.homedev.primefaces.api.model.AppConstants;
import de.homedev.primefaces.api.model.UserEntity;
import de.homedev.primefaces.api.util.ApiUtil;
import de.homedev.primefaces.frontend.config.FrontendSettings;
import de.homedev.primefaces.frontend.util.RmiClientUtil;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    // private static Logger log = Logger.getLogger(LoginBean.class.getName());

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent cse) {
        if (!isSuccessfullyLogged()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/login.jsf?faces-redirect=true");
        }
    }

    public boolean isSuccessfullyLogged() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            Object authException = session.getAttribute(AppConstants.AUTHENTICATION_EXCEPTION);
            if (authException != null) {
                return false;
            }
            UserEntity entity = (UserEntity) session.getAttribute(AppConstants.AUTHENTICATION_USERNAME);
            if (entity != null) {
                return true;
            }
        }
        return false;
    }

    public void login(ActionEvent event) throws NamingException, RemoteException {
        FacesMessage message = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        IUserFassade userFassade = RmiClientUtil.getUserFassade(FrontendSettings.getSettings());
        UserDetails entity = userFassade.loadUserByUsername(username);
        if (ApiUtil.autenticate(entity, password)) {
            session.removeAttribute(AppConstants.AUTHENTICATION_EXCEPTION);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            session.setAttribute(AppConstants.AUTHENTICATION_USERNAME, entity);
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/menue.jsf?faces-redirect=true");
        } else {
            session.setAttribute(AppConstants.AUTHENTICATION_EXCEPTION, "Invalid credentials");
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
        facesContext.addMessage(null, message);
        this.password = null;
    }

    public String getLoggedUsername() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            Object authException = session.getAttribute(AppConstants.AUTHENTICATION_EXCEPTION);
            if (authException != null) {
                session.setAttribute(AppConstants.AUTHENTICATION_USERNAME, null);
                return null;
            }
            UserEntity entity = (UserEntity) session.getAttribute(AppConstants.AUTHENTICATION_USERNAME);
            if (entity != null) {
                return entity.getUsername();
            }
        }
        return null;
    }

    public void toLogout() {
        // @formatter:off
        if (this.password == null) {
            // Eclipse @formatter:on / of tagtest
        }
        // @formatter:on
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null,
            "/logoutSuccess.jsf?faces-redirect=true");
    }

}
