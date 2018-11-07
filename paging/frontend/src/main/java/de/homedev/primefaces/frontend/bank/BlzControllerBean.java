package de.homedev.primefaces.frontend.bank;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.homedev.primefaces.frontend.common.bean.AbstractBeanController;

@ManagedBean(name = "blzControllerBean")
@ViewScoped
public class BlzControllerBean extends AbstractBeanController {
    // -- serializable ---------------------------------------------------------
    private static final long serialVersionUID = 18023857565625L;
    private BlzTableController table;
    private BlzDetailsController details;

    public BlzTableController getTable() {
        if (table == null) {
            table = new BlzTableController(this);
        }
        return table;
    }

    public void setTable(BlzTableController table) {
        this.table = table;
    }

    public BlzDetailsController getDetails() {
        if (details == null) {
            details = new BlzDetailsController(this);
        }
        return details;
    }

    public void setDetails(BlzDetailsController details) {
        this.details = details;
    }

}
