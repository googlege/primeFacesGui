package de.homedev.primefaces.frontend.bank;

import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import de.homedev.primefaces.api.fassade.IBlzFassade;
import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.frontend.common.bean.AbstractTableDetailsBeanController;
import de.homedev.primefaces.frontend.config.FrontendSettings;
import de.homedev.primefaces.frontend.util.RmiClientUtil;

@ManagedBean(name = "blzControllerBean")
@ViewScoped
public class BlzControllerBean extends AbstractTableDetailsBeanController<BlzEntity> {
	// -- serializable ---------------------------------------------------------
	private static final long serialVersionUID = 18023857565625L;
	private BlzTableController table;
	private BlzDetailsController details;

	@Override
	public BlzTableController getTable() {
		if (table == null) {
			table = new BlzTableController(this);
		}
		return table;
	}

	public void setTable(BlzTableController table) {
		this.table = table;
	}

	@Override
	public BlzDetailsController getDetails() {
		if (details == null) {
			details = new BlzDetailsController(this);
		}
		return details;
	}

	public void setDetails(BlzDetailsController details) {
		this.details = details;
	}

	public void doDelete() {
		try {
			BlzEntity entity = getTable().getSelectedElement();
			getFassade().deleteById(entity.getId(), this.readCurrentUser());
			getTable().setSelectedElement(null);
			this.addCommonDeletedMsg();
		} catch (RemoteException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public IBlzFassade getFassade() {
		try {
			return RmiClientUtil.getBlzFassade(FrontendSettings.getSettings());
		} catch (NamingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public BlzEntity newEntity() {
		return new BlzEntity();
	}

}
