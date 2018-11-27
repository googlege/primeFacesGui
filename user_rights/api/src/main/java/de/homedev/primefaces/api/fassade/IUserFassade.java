package de.homedev.primefaces.api.fassade;

import de.homedev.primefaces.api.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IUserFassade extends Remote {
	public static final String SERVICE_NAME = "userFassadeImpl";
	public static final String RMI_SERVICE_NAME = "userFassadeRmi";

	public UserEntity saveUser(UserEntity user) throws RemoteException;

	public List<UserEntity> findUser(String username, String userPassword) throws RemoteException;

	public UserDetails loadUserByUsername(String username) throws RemoteException;

}
