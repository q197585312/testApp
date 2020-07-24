package com.nanyang.app.load;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Administrator on 2017/3/27.
 */

public class PersonalInfo {

    String password = "";
    private String IsEnabledWM;

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

    String IsLDEnabled;
    String IsEnabledPG;
    String IsEnabledPRG;
    String IsEnabledPS;
    String IsEnabledSG;
    String IsEnabledSA;
    String IsEnabledEV;
    String IsEnabledDG;

    public String getIsLDEnabled() {
        return IsLDEnabled;
    }

    public void setIsLDEnabled(String isLDEnabled) {
        IsLDEnabled = isLDEnabled;
    }

    public String getIsEnabledPG() {
        return IsEnabledPG;
    }

    public void setIsEnabledPG(String isEnabledPG) {
        IsEnabledPG = isEnabledPG;
    }

    public String getIsEnabledPRG() {
        return IsEnabledPRG;
    }

    public void setIsEnabledPRG(String isEnabledPRG) {
        IsEnabledPRG = isEnabledPRG;
    }

    public String getIsEnabledPS() {
        return IsEnabledPS;
    }

    public void setIsEnabledPS(String isEnabledPS) {
        IsEnabledPS = isEnabledPS;
    }

    public String getIsEnabledSG() {
        return IsEnabledSG;
    }

    public void setIsEnabledSG(String isEnabledSG) {
        IsEnabledSG = isEnabledSG;
    }

    public String getIsEnabledSA() {
        return IsEnabledSA;
    }

    public void setIsEnabledSA(String isEnabledSA) {
        IsEnabledSA = isEnabledSA;
    }

    public String getIsEnabledEV() {
        return IsEnabledEV;
    }

    public void setIsEnabledEV(String isEnabledEV) {
        IsEnabledEV = isEnabledEV;
    }

    public String getIsEnabledDG() {
        return IsEnabledDG;
    }

    public void setIsEnabledDG(String isEnabledDG) {
        IsEnabledDG = isEnabledDG;
    }


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
        String format = "#.00";
       /* Locale enlocale = Locale.US;
        DecimalFormat df = (DecimalFormat)
                NumberFormat.getNumberInstance(enlocale);
        df.applyPattern(format);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        df.setRoundingMode(RoundingMode.FLOOR);
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        String format1 = df.format(getXYCredit());*/

        BigDecimal bd = new BigDecimal(getXYCredit());
        String s = bd.toPlainString();
        Locale enlocale = Locale.US;
        DecimalFormat df = (DecimalFormat)
                NumberFormat.getNumberInstance(enlocale);
        df.applyPattern(format);

        String format1 = df.format(Double.parseDouble(s));

        return format1;
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

    public String getIsEnabledWM() {
        return IsEnabledWM;
    }
}
