package com.nanyang.app.main.DepositAndWithdraw.Bean;

public class AutoDepositBean {
    private int BankId;
    private String BankCode;
    private String BankName;
    private String BankAccountName;
    private String BankAccountNum;
    private int MinDepositAmt;
    private int ExpiredInterval;

    public AutoDepositBean(int bankId, String bankCode, String bankName, String bankAccountName, String bankAccountNum, int minDepositAmt, int expiredInterval) {
        BankId = bankId;
        BankCode = bankCode;
        BankName = bankName;
        BankAccountName = bankAccountName;
        BankAccountNum = bankAccountNum;
        MinDepositAmt = minDepositAmt;
        ExpiredInterval = expiredInterval;
    }

    public int getBankId() {
        return BankId;
    }

    public void setBankId(int bankId) {
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

    public int getMinDepositAmt() {
        return MinDepositAmt;
    }

    public void setMinDepositAmt(int minDepositAmt) {
        MinDepositAmt = minDepositAmt;
    }

    public int getExpiredInterval() {
        return ExpiredInterval;
    }

    public void setExpiredInterval(int expiredInterval) {
        ExpiredInterval = expiredInterval;
    }
}
