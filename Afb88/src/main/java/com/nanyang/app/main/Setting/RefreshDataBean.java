package com.nanyang.app.main.Setting;

/**
 * Created by ASUS on 2019/6/28.
 */

public class RefreshDataBean {

    /**
     * token : 1iosh1kwcq1knifohbiukvuj
     * um : 1|
     * delay : 0
     * pn : 1
     * tf : 1
     * betable : 1
     * lang : en
     * LangCol :
     * accType : HK
     * CTOddsDiff : -0.2
     * CTSpreadDiff : -1
     * oddsDiff : 0
     * spreadDiff : 0
     */

    private String token;
    private String um;
    private String delay;
    private String pn;
    private String tf;

    public void setBetable(boolean betable) {
        this.betable = betable;
    }

    private boolean betable;
    private String lang;
    private String LangCol;
    private String accType;
    private String CTOddsDiff;
    private String CTSpreadDiff;
    private String oddsDiff;
    private String spreadDiff;


    public void setTp(String tp) {
        this.tp = tp;
    }

    private String tp;

    public void setACT(String ACT) {
        this.ACT = ACT;
    }

    public void setTimess(String timess) {
        this.timess = timess;
    }

    public void setFAV(String FAV) {
        this.FAV = FAV;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    String ACT="LOS";// "LOS",
    String DBID;// "1_1_2",
    String ot;// "t",
    String timess;

    public void setDBID(String DBID) {
        this.DBID = DBID;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }



    public void setMt(String mt) {
        this.mt = mt;
    }

    public void setOv(int ov) {
        this.ov = ov;
    }

    int ov;// 0,
    String mt;// 0,

    String FAV="";// "",
    String SL="";// "",
    boolean fh=false;// false,

    public void setFh(boolean fh) {
        this.fh = fh;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    boolean isToday=false;// false

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }



    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLangCol() {
        return LangCol;
    }

    public void setLangCol(String LangCol) {
        this.LangCol = LangCol;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

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


    @Override
    public String toString() {
        return "{" +
                "token='" + token + '\'' +
                ", um='" + um + '\'' +
                ", delay='" + delay + '\'' +
                ", pn='" + pn + '\'' +
                ", tf='" + tf + '\'' +
                ", betable='" + betable + '\'' +
                ", lang='" + lang + '\'' +
                ", LangCol='" + LangCol + '\'' +
                ", accType='" + accType + '\'' +
                ", CTOddsDiff='" + CTOddsDiff + '\'' +
                ", CTSpreadDiff='" + CTSpreadDiff + '\'' +
                ", oddsDiff='" + oddsDiff + '\'' +
                ", spreadDiff='" + spreadDiff + '\'' +
                ", ACT='" + ACT + '\'' +
                ", DBID='" + DBID + '\'' +
                ", ot='" + ot + '\'' +
                ", timess='" + timess + '\'' +
                ", ov=" + ov +
                ", mt='" + mt + '\'' +
                ", FAV='" + FAV + '\'' +
                ", SL='" + SL + '\'' +
                ", fh=" + fh +
                ", isToday=" + isToday +
                '}';
    }
}
