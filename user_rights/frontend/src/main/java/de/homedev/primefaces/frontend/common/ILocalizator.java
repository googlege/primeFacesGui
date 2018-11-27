package de.homedev.primefaces.frontend.common;

import java.io.Serializable;

public interface ILocalizator extends Serializable {
    /**
     * returns the translation of the given i18n-key in the users current language
     * 
     * @param i18nKey
     *                key
     * @return localized string
     */
    public String localize(String i18nKey);

}
