package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.application.PrincipalDetailsService;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.application.UserAuthServiceImpl;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao.UserAuthRepositoryImpl;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao.UserRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthBeanConfig {
    @PersistenceContext
    private jakarta.persistence.EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private com.sunrint.hackaton._2023sunrinhackatonbackend.global.util.JwtUtil jwtUtil;
    @Bean
    public UserAuthRepositoryImpl userAuthRepository() {
        return new UserAuthRepositoryImpl(entityManager, userRepository);
    }
    @Bean
    public PrincipalDetailsService principalDetailsService(){
        return new PrincipalDetailsService(userAuthRepository());
    }
    @Bean
    public UserAuthServiceImpl userAuthService() {
        return new UserAuthServiceImpl(userAuthRepository(), principalDetailsService(), objectMapper, jwtUtil, userRepository);
    }
}
