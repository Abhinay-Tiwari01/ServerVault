package com.abhi.backend.CustomerDeatils.DTOS.LoginDTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class ShowLoginUserInfoDTO {
    private Integer userId;

    @NotBlank(message = "User Name is Required")
    private String userName;

    @NotBlank(message = "Mobile no is Required")
    @Max(value = 10, message = "Mobile Number Cannot Exceed 10 Digit")
    private String mobile;

    private String getEncryptedPassword;

    public ShowLoginUserInfoDTO(Integer userId, String userName, String mobile, String getEncryptedPassword) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
        this.getEncryptedPassword = getEncryptedPassword;
    }

    public String getGetEncryptedPassword() {
        return getEncryptedPassword;
    }

    public void setGetEncryptedPassword(String getEncryptedPassword) {
        this.getEncryptedPassword = getEncryptedPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ShowLoginUserInfoDTO(Integer userId,String userName, String mobile) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
    }

    public ShowLoginUserInfoDTO() {
    }
}
