package de.homedev.primefaces.api.exceptions;

import java.rmi.RemoteException;

import de.homedev.primefaces.api.util.ApiUtil;

public class BackendException extends RemoteException {
	private static final long serialVersionUID = 1L;

	public BackendException() {
		super();

	}

	public BackendException(Throwable cause) {
		super(ApiUtil.throwableToString(cause));
	}

	public BackendException(String s, Throwable cause) {
		super(s + "\r\n" + ApiUtil.throwableToString(cause));

	}

	public BackendException(String s) {
		super(s);
	}

}
