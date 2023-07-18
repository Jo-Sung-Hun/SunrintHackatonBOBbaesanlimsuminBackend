package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto;

import lombok.Builder;

public class UserLoginData {
    private String userLoginEmail;
    private String userLoginPassword;
    @Builder
    public UserLoginData(String userLoginEmail, String userLoginPassword) {
        this.userLoginEmail = userLoginEmail;
        this.userLoginPassword = userLoginPassword;
    }
    public UserLoginData() {
    }
    public String getUserLoginEmail() {
        return userLoginEmail;
    }
    public void setUserLoginEmail(String userLoginEmail) {
        this.userLoginEmail = userLoginEmail;
    }
    public String getUserLoginPassword() {
        return userLoginPassword;
    }
    public void setUserLoginPassword(String userLoginPassword) {
        this.userLoginPassword = userLoginPassword;
    }

    @Override
    public String toString() {

        return "UserLoginData{" +
                "userLoginEmail='" + userLoginEmail + '\'' +
                ", userLoginPassword='" + userLoginPassword + '\'' +
                '}';
    }
}
