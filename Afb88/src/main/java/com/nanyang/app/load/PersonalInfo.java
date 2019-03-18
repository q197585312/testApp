package com.nanyang.app.load;

/**
 * Created by Administrator on 2017/3/27.
 */

public class PersonalInfo {

    String password="";

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * LoginName : @@@AFB88###
     * CurCode2 :
     * balances : 0
     * Etotalstanding : 0
     * MinLimit : 0
     * credit2 : 0
     * TotalCredit : 0
     * "CurCode2":"MYR",
     */

    private String lastLoginDate;
    private String LoginName;
    private String CurCode2;
    private String balances;
    private String Etotalstanding;
    private String MinLimit;
    private String credit2;
    private String TotalCredit;




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getCurCode2() {
        return CurCode2;
    }

    public void setCurCode2(String CurCode2) {
        this.CurCode2 = CurCode2;
    }

    public String getBalances() {
        return balances;
    }

    public void setBalances(String balances) {
        this.balances = balances;
    }

    public String getEtotalstanding() {
        return Etotalstanding;
    }

    public void setEtotalstanding(String Etotalstanding) {
        this.Etotalstanding = Etotalstanding;
    }

    public String getMinLimit() {
        return MinLimit;
    }

    public void setMinLimit(String MinLimit) {
        this.MinLimit = MinLimit;
    }

    public String getCredit2() {
        return credit2;
    }

    public void setCredit2(String credit2) {
        this.credit2 = credit2;
    }

    public String getTotalCredit() {
        return TotalCredit;
    }

    public void setTotalCredit(String TotalCredit) {
        this.TotalCredit = TotalCredit;
    }
}
