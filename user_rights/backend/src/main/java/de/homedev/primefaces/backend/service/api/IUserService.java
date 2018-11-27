package de.homedev.primefaces.backend.service.api;

import de.homedev.primefaces.api.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public static final String SERVICE_NAME = "userServiceImpl";

    public UserEntity saveUser(UserEntity user);

    public List<UserEntity> findUser(String username, String userPassword);

    @Override
    public UserDetails loadUserByUsername(String username);

}
