package com.abhi.backend.CustomerDeatils.DTOS.LoginDTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class ForgetPasswordDTO {
    @NotBlank(message = "Mobile is required")
    @Max(value = 10, message = "Mobile max 10 digits")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ForgetPasswordDTO(String mobile) {
        this.mobile = mobile;
    }

    public ForgetPasswordDTO() {
    }
}
