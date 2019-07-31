package com.nanyang.app.load.register;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/22.
 */

public class RegisterInfo {
    String btnCheckAvailability;
    String txtUserName;
    String txtPassword;
    String txtVerifyPwd;
    String lstCurrency;
    String txtContactNum;
    String txtMobileNum;
    String txtEmail;
    String txtFullName;
    String txtReferral;
    String lstBank;
    String txtAccName;
    String txtAccNumber;
    String txtCode;

    public RegisterInfo(String txtUserName, String txtPassword, String txtVerifyPwd, String lstCurrency, String txtContactNum, String txtEmail, String lstBank, String txtAccName, String txtAccNumber) {
        this.txtUserName = txtUserName;
        this.txtPassword = txtPassword;
        this.txtVerifyPwd = txtVerifyPwd;
        this.lstCurrency = lstCurrency;
        this.txtContactNum = txtContactNum;
        this.txtEmail = txtEmail;
        this.lstBank = lstBank;
        this.txtAccName = txtAccName;
        this.txtAccNumber = txtAccNumber;
    }

    public RegisterInfo(String txtUserName) {
        this.txtUserName = txtUserName;
    }

    public Map<String, String> getCheckUserMap() {
        Map<String, String> map = new HashMap<>();
        map.put("txtUserName", txtUserName);
        map.put("btnCheckAvailability", "Check Availability");
        map.put("lstCurrency", "15");
        map.put("lstBank", "KASIKORN BANK");
        return map;
    }

    public Map<String, String> getRegisterMap() {
        Map<String, String> map = new HashMap<>();
        map.put("txtUserName", txtUserName);
        map.put("txtPassword", txtPassword);
        map.put("txtVerifyPwd", txtVerifyPwd);
        map.put("lstCurrency", lstCurrency);
        map.put("txtContactNum", txtContactNum);
        map.put("txtEmail", txtEmail);
        map.put("lstBank", lstBank);
        map.put("txtAccName", txtAccName);
        map.put("txtAccNumber", txtAccNumber);
        map.put("chkTNC", "on");
        map.put("btnSubmit", "Save");
        return map;
    }

    public String getBtnCheckAvailability() {
        return btnCheckAvailability;
    }

    public void setBtnCheckAvailability(String btnCheckAvailability) {
        this.btnCheckAvailability = btnCheckAvailability;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public String getTxtVerifyPwd() {
        return txtVerifyPwd;
    }

    public void setTxtVerifyPwd(String txtVerifyPwd) {
        this.txtVerifyPwd = txtVerifyPwd;
    }

    public String getLstCurrency() {
        return lstCurrency;
    }

    public void setLstCurrency(String lstCurrency) {
        this.lstCurrency = lstCurrency;
    }

    public String getTxtContactNum() {
        return txtContactNum;
    }

    public void setTxtContactNum(String txtContactNum) {
        this.txtContactNum = txtContactNum;
    }

    public String getTxtMobileNum() {
        return txtMobileNum;
    }

    public void setTxtMobileNum(String txtMobileNum) {
        this.txtMobileNum = txtMobileNum;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtFullName() {
        return txtFullName;
    }

    public void setTxtFullName(String txtFullName) {
        this.txtFullName = txtFullName;
    }

    public String getTxtReferral() {
        return txtReferral;
    }

    public void setTxtReferral(String txtReferral) {
        this.txtReferral = txtReferral;
    }

    public String getLstBank() {
        return lstBank;
    }

    public void setLstBank(String lstBank) {
        this.lstBank = lstBank;
    }

    public String getTxtAccName() {
        return txtAccName;
    }

    public void setTxtAccName(String txtAccName) {
        this.txtAccName = txtAccName;
    }

    public String getTxtAccNumber() {
        return txtAccNumber;
    }

    public void setTxtAccNumber(String txtAccNumber) {
        this.txtAccNumber = txtAccNumber;
    }

    public String getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(String txtCode) {
        this.txtCode = txtCode;
    }

    public String getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName = txtUserName;
    }
}
