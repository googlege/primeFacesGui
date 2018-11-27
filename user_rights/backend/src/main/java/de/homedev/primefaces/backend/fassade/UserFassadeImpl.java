package de.homedev.primefaces.backend.fassade;

import de.homedev.primefaces.api.exceptions.BackendException;
import de.homedev.primefaces.api.exceptions.UsernameNotFoundBackendException;
import de.homedev.primefaces.api.fassade.IUserFassade;
import de.homedev.primefaces.api.model.UserEntity;
import de.homedev.primefaces.backend.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(IUserFassade.SERVICE_NAME)
public class UserFassadeImpl implements IUserFassade {
	private static Logger log = LoggerFactory.getLogger(UserFassadeImpl.class);

	@Autowired
	private IUserService userService;

	@Override
	public UserEntity saveUser(UserEntity user) throws BackendException {
		try {
			return userService.saveUser(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}



	@Override
	public List<UserEntity> findUser(String username, String userPassword) throws BackendException {
		try {
			return userService.findUser(username, userPassword);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws BackendException {
		try {
			return userService.loadUserByUsername(username);

		} catch (UsernameNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new UsernameNotFoundBackendException(e.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

}
