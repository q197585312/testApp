package com.nanyang.app.main.DepositAndWithdraw.Bean;

public class WithdrawHistoryBean {
    private String PayDate;
    private String Description;
    private String Amt;
    private String Status;

    public WithdrawHistoryBean(String payDate, String description, String amt, String status) {
        PayDate = payDate;
        Description = description;
        Amt = amt;
        Status = status;
    }

    public String getPayDate() {
        return PayDate;
    }

    public void setPayDate(String payDate) {
        PayDate = payDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String amt) {
        Amt = amt;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
