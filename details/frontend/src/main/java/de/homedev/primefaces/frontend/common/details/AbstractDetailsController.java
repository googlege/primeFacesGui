package de.homedev.primefaces.frontend.common.details;

import java.io.Serializable;

import de.homedev.primefaces.frontend.enums.EditMode;

public abstract class AbstractDetailsController<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T entry;
    private EditMode editMode = EditMode.VIEW;

    public T getEntry() {
        return entry;
    }

    public void setEntry(T entry) {
        this.entry = entry;
    }

    public EditMode getEditMode() {
        return editMode;
    }

    public void setEditMode(EditMode editMode) {
        this.editMode = editMode;
    }

    public abstract void doSave();

}
