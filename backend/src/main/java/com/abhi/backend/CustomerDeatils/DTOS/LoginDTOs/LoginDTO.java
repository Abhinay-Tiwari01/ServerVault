package com.abhi.backend.CustomerDeatils.DTOS.LoginDTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO {

    @NotBlank(message = "User Name is Required")
    private String userName;

    @NotBlank(message = "Password is required")
//    @Size(min = 4 , max = 10, message = "Password must be in 4 to 10 chars")
    private String password;

    @NotBlank(message = "Mobile no is Required")
    @Size(min = 10, max = 10, message = "Mobile must be 10 digits")
    private String mobile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LoginDTO(String userName, String password, String mobile
//                            ,Integer  userId
    ) {
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
//        this.userId = userId;
    }

    public LoginDTO() {
    }
}
