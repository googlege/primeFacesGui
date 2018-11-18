package de.homedev.primefaces.frontend.common;

import java.io.Serializable;

public interface IJsfController extends Serializable, ILocalizator {
    /**
     * method for default initialization of the controller bean. For main controllers this methods
     * is called when the corresponding menuitem is selected.
     */
    public void init();

}
