package de.homedev.primefaces.frontend.bank;

import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.frontend.common.IJsfController;
import de.homedev.primefaces.frontend.common.details.AbstractDetailsController;

public class BlzDetailsController extends AbstractDetailsController<BlzEntity> {
    // -- serializable ---------------------------------------------------------

    private static final long serialVersionUID = 1L;

    private IJsfController parentCb;

    /**
     * Constructor @param parent
     */
    public BlzDetailsController(BlzControllerBean parent) {
        super();
        this.parentCb = parentCb;
    }

}
