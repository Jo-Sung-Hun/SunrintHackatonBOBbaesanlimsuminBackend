package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao;

import ch.qos.logback.core.util.OptionHelper;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.domain.UserLoginEntity;

import java.util.Optional;

public interface UserAuthRepository {
    Optional<UserLoginEntity> findByEmail(String email);
}
