package com.abhi.backend.CustomerDeatils.DTOS.ServerDetailDTO;

import java.time.LocalDate;

public class ShowServerDetailsDTO {
    private Integer serverId;
    private String companyName;
    private String companyAddress;
    private String paymentAmount;
    private String paymentYear;
    private String serverIpName;
    private String mcompName;
    private LocalDate uploadDate;
    private String loginUserName;
    private String password;

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

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

    public ShowServerDetailsDTO(Integer serverId, String companyName,
                                String companyAddress, String paymentAmount,
                                String paymentYear, String serverIpName,
                                String mcompName, LocalDate uploadDate,
                                String loginUserName, String password) {
        this.serverId = serverId;
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
}
