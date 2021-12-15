package com.nanyang.app.main.DepositAndWithdraw.Bean;

public class DepositDataBean {
    private int AgBankId;
    private int UserId;
    private String BankName;
    private String AccName;
    private String AccNo;
    private int Preferred;
    private int IsDelete;
    private int MinDepositAmt;
    private int MaxDepositAmt;
    private int MinWithoutitAmt;
    private int MaxWithoutAmt;

    public DepositDataBean(int agBankId, int userId, String bankName, String accName, String accNo, int preferred, int isDelete, int minDepositAmt, int maxDepositAmt, int minWithoutitAmt, int maxWithoutAmt) {
        AgBankId = agBankId;
        UserId = userId;
        BankName = bankName;
        AccName = accName;
        AccNo = accNo;
        Preferred = preferred;
        IsDelete = isDelete;
        MinDepositAmt = minDepositAmt;
        MaxDepositAmt = maxDepositAmt;
        MinWithoutitAmt = minWithoutitAmt;
        MaxWithoutAmt = maxWithoutAmt;
    }

    public int getAgBankId() {
        return AgBankId;
    }

    public void setAgBankId(int agBankId) {
        AgBankId = agBankId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getAccName() {
        return AccName;
    }

    public void setAccName(String accName) {
        AccName = accName;
    }

    public String getAccNo() {
        return AccNo;
    }

    public void setAccNo(String accNo) {
        AccNo = accNo;
    }

    public int getPreferred() {
        return Preferred;
    }

    public void setPreferred(int preferred) {
        Preferred = preferred;
    }

    public int getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(int isDelete) {
        IsDelete = isDelete;
    }

    public int getMinDepositAmt() {
        return MinDepositAmt;
    }

    public void setMinDepositAmt(int minDepositAmt) {
        MinDepositAmt = minDepositAmt;
    }

    public int getMaxDepositAmt() {
        return MaxDepositAmt;
    }

    public void setMaxDepositAmt(int maxDepositAmt) {
        MaxDepositAmt = maxDepositAmt;
    }

    public int getMinWithoutitAmt() {
        return MinWithoutitAmt;
    }

    public void setMinWithoutitAmt(int minWithoutitAmt) {
        MinWithoutitAmt = minWithoutitAmt;
    }

    public int getMaxWithoutAmt() {
        return MaxWithoutAmt;
    }

    public void setMaxWithoutAmt(int maxWithoutAmt) {
        MaxWithoutAmt = maxWithoutAmt;
    }
}
