package com.nanyang.app.main.DepositAndWithdraw.Bean;

public class PayMethodBean {
    private String payMethod;
    private String payId;

    public PayMethodBean(String payMethod, String payId) {
        this.payMethod = payMethod;
        this.payId = payId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}
