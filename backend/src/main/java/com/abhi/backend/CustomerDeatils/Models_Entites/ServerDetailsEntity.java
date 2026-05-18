package com.abhi.backend.CustomerDeatils.Models_Entites;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ServerDetailsEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Comp_Id")
    private int serverId;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "Comp_Address")
    private String companyAddress;

    @Column(name = "Pay_Amount")
    private String paymentAmount;

    @Column(name = "PaymentYear")
    private String paymentYear;

    @Column(name = "UploadDate")
    private LocalDate uploadDate;

    @Column(name = "IpName")
    private String serverIpName;

    @Column(name = "McompName")
    private String mcompName;

    @Column(name = "LoginUserId")
    private String loginUserName;

    @Column(name = "Password")
    private String password;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
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

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
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

    public ServerDetailsEntity(String companyName, String companyAddress,
                               String paymentAmount, String paymentYear,
                               LocalDate uploadDate, String serverIpName,
                               String mcompName, String loginUserName, String password) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.paymentAmount = paymentAmount;
        this.paymentYear = paymentYear;
        this.uploadDate = uploadDate;
        this.serverIpName = serverIpName;
        this.mcompName = mcompName;
        this.loginUserName = loginUserName;
        this.password = password;
    }

    public ServerDetailsEntity() {
    }
}
