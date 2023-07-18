package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao.UserAuthRepositoryImpl;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao.UserRepository;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.domain.UserLoginEntity;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto.JWTResult;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto.UserLoginData;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto.UserLoginEntityUserDetails;
import com.sunrint.hackaton._2023sunrinhackatonbackend.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserAuthServiceImpl implements UserAuthService {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);
    private final UserAuthRepositoryImpl userAuthRepository;
    private final PrincipalDetailsService principalDetailsService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    @Autowired
    public UserAuthServiceImpl(UserAuthRepositoryImpl userAuthRepository, PrincipalDetailsService principalDetailsService, ObjectMapper objectMapper, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userAuthRepository = userAuthRepository;
        this.principalDetailsService = principalDetailsService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    @Override
    public String login(UserLoginData userLoginData, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        UserLoginEntityUserDetails result = (UserLoginEntityUserDetails) principalDetailsService.loadUserByUsername(userLoginData.getUserLoginEmail());

        if (!result.getPassword().equals(userLoginData.getUserLoginPassword())) {
            response(new ResponseEntity("비밀번호가 일치하지 않습니다.", null, 400));
        }
        String accessToken = jwtUtil.accessTokenGenerateToken(result, httpServletRequest);
        String refreshToken = jwtUtil.refreshTokenGenerateToken(result, httpServletRequest);
        logger.info("UserLoginEntityUserDetails: {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new JWTResult(accessToken, refreshToken));
    }
    private ResponseEntity response(ResponseEntity responseEntity) {
        if (responseEntity == null) {
            return ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @Override
    public Optional<UserLoginEntity> signin(UserLoginData userLoginData) {
        UserLoginEntity userLoginEntity = new UserLoginEntity();
        userLoginEntity.setUserLoginEmail(userLoginData.getUserLoginEmail());
        userLoginEntity.setUserLoginPassword(userLoginData.getUserLoginPassword());

        return Optional.of(userRepository.save(userLoginEntity));
    }
}
