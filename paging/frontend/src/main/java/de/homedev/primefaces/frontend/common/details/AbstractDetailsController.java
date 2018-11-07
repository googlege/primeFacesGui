package de.homedev.primefaces.frontend.common.details;

import java.io.Serializable;

public abstract class AbstractDetailsController<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T selectedEntity;

    public T getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

}
