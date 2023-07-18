package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.application;

import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao.UserAuthRepository;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.domain.UserLoginEntity;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto.UserLoginEntityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class PrincipalDetailsService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    @Autowired
    public PrincipalDetailsService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findByEmail(username)
                .<UserDetails>map(UserLoginEntityUserDetails::new).orElse(null);
    }
}
