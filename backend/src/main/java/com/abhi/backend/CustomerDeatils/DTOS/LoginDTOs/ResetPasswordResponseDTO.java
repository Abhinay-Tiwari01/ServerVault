package com.abhi.backend.CustomerDeatils.DTOS.LoginDTOs;

public class ResetPasswordResponseDTO {
    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResetPasswordResponseDTO(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ResetPasswordResponseDTO() {
    }
}
