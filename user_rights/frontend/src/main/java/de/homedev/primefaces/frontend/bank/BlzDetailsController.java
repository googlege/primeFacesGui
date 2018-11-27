package de.homedev.primefaces.frontend.bank;

import java.rmi.RemoteException;

import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.frontend.common.details.AbstractDetailsController;

public class BlzDetailsController extends AbstractDetailsController<BlzEntity> {
    // -- serializable ---------------------------------------------------------

    private static final long serialVersionUID = 1L;

    private final BlzControllerBean parentCb;

    /**
     * Constructor @param parent
     */
    public BlzDetailsController(final BlzControllerBean parentCb) {
        super();
        this.parentCb = parentCb;
    }

    @Override
    public void doSave() {
        try {
            boolean newEntity = (this.getEntry().getId() == null);
            parentCb.getFassade().save(this.getEntry());
            parentCb.addCommonSavedMsg(newEntity);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
