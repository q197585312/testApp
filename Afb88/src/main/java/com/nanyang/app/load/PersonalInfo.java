package com.nanyang.app.load;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/3/27.
 */

public class PersonalInfo {

    String password = "";

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
     * "credit2":"-3732.93",
     */

    private String lastLoginDate;
    private String LoginName;
    private String CurCode2;
    private String balances;
    private String Etotalstanding;
    private String MinLimit;
    private String credit2;
    private String TotalCredit;
    String CTOddsDiff;
    String CTSpreadDiff;
    String oddsDiff;
    String spreadDiff;

    public String getXYCredit() {
        return XYCredit;
    }

    public void setXYCredit(String XYCredit) {
        this.XYCredit = XYCredit;
    }

    public String getISAPI() {
        return ISAPI;
    }

    public void setISAPI(String ISAPI) {
        this.ISAPI = ISAPI;
    }

    String XYCredit;
    String ISAPI;

    public String getBetable() {
        return betable;
    }

    public void setBetable(String betable) {
        this.betable = betable;
    }

    public String getTfDate() {
        return tfDate;
    }

    public void setTfDate(String tfDate) {
        this.tfDate = tfDate;
    }

    public String getLangCol() {
        return LangCol;
    }

    public void setLangCol(String langCol) {
        LangCol = langCol;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    String betable;
    String tfDate;
    String LangCol;
    String um;
    String delay;

    public String getCTOddsDiff() {
        return CTOddsDiff;
    }

    public void setCTOddsDiff(String CTOddsDiff) {
        this.CTOddsDiff = CTOddsDiff;
    }

    public String getCTSpreadDiff() {
        return CTSpreadDiff;
    }

    public void setCTSpreadDiff(String CTSpreadDiff) {
        this.CTSpreadDiff = CTSpreadDiff;
    }

    public String getOddsDiff() {
        return oddsDiff;
    }

    public void setOddsDiff(String oddsDiff) {
        this.oddsDiff = oddsDiff;
    }

    public String getSpreadDiff() {
        return spreadDiff;
    }

    public void setSpreadDiff(String spreadDiff) {
        this.spreadDiff = spreadDiff;
    }


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
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(Double.valueOf(getXYCredit()));
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
