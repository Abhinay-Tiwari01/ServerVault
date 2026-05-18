package com.abhi.backend.CustomerDeatils.DTOS;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ServerDetailsDTO {

    @NotBlank(message = "Company name is Required")
    @Size(max = 150, message = "Only 150 chars are allowed")
    private String companyName;

    @Size(max = 250, message = "Only 250 chars are allowed")
    private String companyAddress;

    @NotBlank(message = "Need to fill Payment Amount")
    @Size(max = 15, message = "Only 15 chars are allowed")
    private String paymentAmount;

    @NotBlank(message = "Need to fill Payment Year")
    @Size(max = 4, message = "Only 4 digits are allowed")
    private String paymentYear;

    @NotBlank(message = "Need to fill Server Name / Ip address")
    @Size(max = 50, message = "Only 50 chars are allowed")
    private String serverIpName;

    @NotBlank(message = "Need to fill Mcomp name")
    @Size(max = 15, message = "Only 15 chars are allowed")
    private String mcompName;

    @NotNull(message = "Upload date is required")
    @PastOrPresent(message = "Upload date must not be in the future")
    private LocalDate uploadDate;

    @NotBlank(message = "Need to fill Login user name")
    @Max(value = 15, message = "Only 15 chars are allowed")
    private String loginUserName;

    @NotBlank(message = "Need to fill Password")
    @Size(min = 4, max = 15, message = "Password must be 4–15 chars long")
    private String password;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentYear() {
        return paymentYear;
    }

    public void setPaymentYear(String paymentYear) {
        this.paymentYear = paymentYear;
    }

    public String getServerIpName() {
        return serverIpName;
    }

    public void setServerIpName(String serverIpName) {
        this.serverIpName = serverIpName;
    }

    public String getMcompName() {
        return mcompName;
    }

    public void setMcompName(String mcompName) {
        this.mcompName = mcompName;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ServerDetailsDTO(String companyName, String companyAddress,
                            String paymentAmount, String paymentYear,
                            String serverIpName, String mcompName,
                            LocalDate uploadDate,
                            String loginUserName, String password) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.paymentAmount = paymentAmount;
        this.paymentYear = paymentYear;
        this.serverIpName = serverIpName;
        this.mcompName = mcompName;
        this.uploadDate = uploadDate;
        this.loginUserName = loginUserName;
        this.password = password;
    }

    public ServerDetailsDTO() {
    }
}
