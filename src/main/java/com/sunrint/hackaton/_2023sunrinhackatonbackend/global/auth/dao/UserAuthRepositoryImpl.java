package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao;

import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.domain.UserLoginEntity;
import jakarta.persistence.EntityManager;

import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchProviderException;
import java.util.Optional;

public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserAuthRepositoryImpl.class);
    public UserAuthRepositoryImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserLoginEntity> findByEmail(String email) {
        if (email == null) {
            throw new NullPointerException("email is null");
        }
        return userRepository.findByUserLoginEmail(email);
    }


}