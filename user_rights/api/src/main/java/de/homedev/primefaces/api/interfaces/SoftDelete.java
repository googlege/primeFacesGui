/*
 * Copyright (c) 2018 Vermop.
 */

package de.homedev.primefaces.api.interfaces;

import java.time.ZonedDateTime;

import de.homedev.primefaces.api.model.UserEntity;

public interface SoftDelete {
	public Long getId();

	public boolean isDeleted();

	public void setDeleted(boolean deleted);

	public void setDeletedBy(UserEntity deletedBy);

	public void setDeletedAt(ZonedDateTime deletedAt);

	// public User getDeletedBy();
	// public ZonedDateTime getDeletedAt();
}
