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

    public String getJson() {
        return new Gson().toJson(this);
    }
}
