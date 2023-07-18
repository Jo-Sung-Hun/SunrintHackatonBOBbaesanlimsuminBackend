package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.application;

import ch.qos.logback.core.util.OptionHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.domain.UserLoginEntity;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto.JWTResult;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto.UserLoginData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;


public interface UserAuthService {
    String login(UserLoginData userLoginData, HttpServletRequest httpServletRequest) throws JsonProcessingException;
    Optional<UserLoginEntity> signin(UserLoginData userLoginData);

}