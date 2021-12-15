package com.nanyang.app.main.DepositAndWithdraw.Bean;

public class AutoDepositBean {
    private String BankId;
    private String BankCode;
    private String BankName;
    private String BankAccountName;
    private String BankAccountNum;
    private String MinDepositAmt;
    private String ExpiredInterval;

    public AutoDepositBean(String bankId, String bankCode, String bankName, String bankAccountName, String bankAccountNum, String minDepositAmt, String expiredInterval) {
        BankId = bankId;
        BankCode = bankCode;
        BankName = bankName;
        BankAccountName = bankAccountName;
        BankAccountNum = bankAccountNum;
        MinDepositAmt = minDepositAmt;
        ExpiredInterval = expiredInterval;
    }

    public String getBankId() {
        return BankId;
    }

    public void setBankId(String bankId) {
        BankId = bankId;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankAccountName() {
        return BankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        BankAccountName = bankAccountName;
    }

    public String getBankAccountNum() {
        return BankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        BankAccountNum = bankAccountNum;
    }

    public String getMinDepositAmt() {
        return MinDepositAmt;
    }

    public void setMinDepositAmt(String minDepositAmt) {
        MinDepositAmt = minDepositAmt;
    }

    public String getExpiredInterval() {
        return ExpiredInterval;
    }

    public void setExpiredInterval(String expiredInterval) {
        ExpiredInterval = expiredInterval;
    }
}
