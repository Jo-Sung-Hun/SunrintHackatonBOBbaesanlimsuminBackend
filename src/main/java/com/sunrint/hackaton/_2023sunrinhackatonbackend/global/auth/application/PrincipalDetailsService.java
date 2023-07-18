package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.application;

import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao.UserAuthRepository;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao.UserRepository;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.domain.UserLoginEntity;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto.UserLoginEntityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userAuthRepository;

    @Autowired
    public PrincipalDetailsService(UserRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserLoginEntity> userLoginEntity = userAuthRepository.findByUserLoginEmail(username);
        if (userLoginEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserLoginEntityUserDetails(userLoginEntity.get());
    }
}
