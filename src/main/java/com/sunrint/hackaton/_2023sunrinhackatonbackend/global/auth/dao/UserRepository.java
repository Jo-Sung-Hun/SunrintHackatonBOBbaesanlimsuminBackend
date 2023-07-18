package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dao;

import com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.domain.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserLoginEntity, UUID>{
    Optional<UserLoginEntity> findByUserLoginEmail(String email);
}
