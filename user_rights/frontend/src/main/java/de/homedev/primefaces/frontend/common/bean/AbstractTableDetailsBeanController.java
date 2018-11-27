package de.homedev.primefaces.frontend.common.bean;

import de.homedev.primefaces.frontend.common.details.AbstractDetailsController;
import de.homedev.primefaces.frontend.common.table.lazy.AbstractScrolLazyTableController;
import de.homedev.primefaces.frontend.enums.EditMode;

public abstract class AbstractTableDetailsBeanController<T> extends AbstractBeanController {
    private static final long serialVersionUID = 1L;

    public abstract AbstractScrolLazyTableController<T> getTable();

    public abstract AbstractDetailsController<T> getDetails();

    public abstract T newEntity();

    public void openView() {
        getDetails().setEditMode(EditMode.VIEW);
        getDetails().setEntry(getTable().getSelectedElement());
    }

    public void openEdit() {
        getDetails().setEditMode(EditMode.EDIT);
        getDetails().setEntry(getTable().getSelectedElement());
    }

    public void openNew() {
        getDetails().setEditMode(EditMode.NEW);
        getDetails().setEntry(newEntity());
    }

    public void openClone() {
        getDetails().setEditMode(EditMode.CLONE);
        getDetails().setEntry(getTable().getSelectedElement());
    }

    public void openDelete() {
        getDetails().setEntry(getTable().getSelectedElement());
    }

}
