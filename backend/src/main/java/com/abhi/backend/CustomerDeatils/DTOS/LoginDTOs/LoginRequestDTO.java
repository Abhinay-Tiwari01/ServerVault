package com.abhi.backend.CustomerDeatils.DTOS.LoginDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {
    @NotBlank(message = "User Name is Required")
    private String userName;

    @NotBlank(message = "Password is required")
//    @Size(min = 4 , max = 10, message = "Password must be in 4 to 10 chars")
    private String password;

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

    public LoginRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginRequestDTO() {
    }
}
