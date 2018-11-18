package de.homedev.primefaces.frontend.common.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import de.homedev.primefaces.frontend.common.IJsfController;

public abstract class AbstractBeanController implements IJsfController {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
    }

    @Override
    public String localize(String i18nKey) {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle text = ResourceBundle.getBundle("messages", locale);
        return text.getString(i18nKey);
    }

    public void addMsg(Severity severity, String summary, String detail) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMsgAndTranslate(Severity severity, String i18nKeySummary, String i18nKeyDetails) {
        addMsg(severity, localize(i18nKeySummary), localize(i18nKeyDetails));
    }

    public void addInfoMsgAndTranslate(String i18nKeySummary, String i18nKeyDetails) {
        addMsg(FacesMessage.SEVERITY_INFO, localize(i18nKeySummary), localize(i18nKeyDetails));
    }

    public void addInfoMsgAndTranslate(String i18nKeyDetails) {
        addMsg(FacesMessage.SEVERITY_INFO, localize("common.msg.success"), localize(i18nKeyDetails));
    }

    public void addErrorMsgAndTranslate(String i18nKeySummary, String i18nKeyDetails) {
        addMsg(FacesMessage.SEVERITY_ERROR, localize(i18nKeySummary), localize(i18nKeyDetails));
    }

    public void addErrorMsgAndTranslate(String i18nKeyDetails) {
        addMsg(FacesMessage.SEVERITY_ERROR, localize("common.msg.error"), localize(i18nKeyDetails));
    }

    public void addSavedMsg(boolean newEntity, String newI18nKey, String updatedI18nKey) {
        if (!newEntity) {
            addMsg(FacesMessage.SEVERITY_INFO, localize("common.msg.success"), localize(updatedI18nKey));
        } else {
            addMsg(FacesMessage.SEVERITY_INFO, localize("common.msg.success"), localize(newI18nKey));
        }
    }

    public void addCommonSavedMsg(boolean newEntity) {
        addSavedMsg(newEntity, "common.msg.new", "common.msg.updated");
    }

    public void addCommonDeletedMsg() {
        addInfoMsgAndTranslate("common.msg.deleted");
    }
}
