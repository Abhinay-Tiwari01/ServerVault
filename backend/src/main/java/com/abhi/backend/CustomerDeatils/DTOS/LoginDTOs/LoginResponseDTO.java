package com.abhi.backend.CustomerDeatils.DTOS.LoginDTOs;

public class LoginResponseDTO {
    private Integer id;
    private String userName;
    private String mobile;
    private String message;
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LoginResponseDTO(Integer id, String userName,
                            String mobile, String message, String role ) {
        this.id = id;
        this.userName = userName;
        this.mobile = mobile;
        this.message = message;
        this.role = role;
    }

    public LoginResponseDTO() {
    }
}
