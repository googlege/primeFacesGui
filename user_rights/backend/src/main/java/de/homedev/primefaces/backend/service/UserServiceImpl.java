package de.homedev.primefaces.backend.service;

import de.homedev.primefaces.api.model.UserEntity;
import de.homedev.primefaces.backend.dao.RemovedPermissionDao;
import de.homedev.primefaces.backend.dao.UserDao;
import de.homedev.primefaces.backend.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(IUserService.SERVICE_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserServiceImpl implements IUserService, UserDetailsService {
	// private static Logger log =
	// LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RemovedPermissionDao removedPermissionDao;

	@Override
	public List<UserEntity> findUser(String username, String userPassword) {
		return userDao.findByUsernameAndPassword(username, userPassword);
	}

	@Override
	public UserEntity saveUser(UserEntity entity) {
		return userDao.save(entity);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity> list = userDao.findByUsername(username);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}



}
