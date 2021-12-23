package com.nanyang.app.main.DepositAndWithdraw.Bean;

import com.google.gson.Gson;

public class DepositWithdrawParam {
    String ACT;
    String PT;
    String pgLable;
    String vsn;
    String Amountinput;
    String Passwordinput;
    String fdate;
    String todate;
    String type;
    String Deposit;
    String AccName;
    String AccNumber;
    String lstBank;
    String PayMethod;
    String Remark;
    String imgurl;
    String MinAmt;
    String depReqId;

    public DepositWithdrawParam(String ACT, String PT, String Amountinput, String Passwordinput, String pgLable, String vsn) {
        this.ACT = ACT;
        this.PT = PT;
        this.Amountinput = Amountinput;
        this.Passwordinput = Passwordinput;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public DepositWithdrawParam(String ACT, String PT, String fdate, String todate, String type, String pgLable, String vsn) {
        this.ACT = ACT;
        this.PT = PT;
        this.fdate = fdate;
        this.todate = todate;
        this.type = type;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public DepositWithdrawParam(String ACT, String PT, String Deposit, String AccName, String AccNumber, String lstBank, String PayMethod, String Remark, String imgurl, String pgLable, String vsn) {
        this.ACT = ACT;
        this.PT = PT;
        this.Deposit = Deposit;
        this.AccName = AccName;
        this.AccNumber = AccNumber;
        this.lstBank = lstBank;
        this.PayMethod = PayMethod;
        this.Remark = Remark;
        this.imgurl = imgurl;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public DepositWithdrawParam(String ACT, String PT, String Deposit, String AccName, String AccNumber, String lstBank, String MinAmt, String pgLable, String vsn) {
        this.ACT = ACT;
        this.PT = PT;
        this.Deposit = Deposit;
        this.AccName = AccName;
        this.AccNumber = AccNumber;
        this.lstBank = lstBank;
        this.MinAmt = MinAmt;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public DepositWithdrawParam(String ACT, String PT, String depReqId, String pgLable, String vsn) {
        this.ACT = ACT;
        this.PT = PT;
        this.depReqId = depReqId;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public String getJson() {
        return new Gson().toJson(this);
    }
}
