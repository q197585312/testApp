package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/30.
 */
public class ScaleBean implements Serializable{

    /**
     * ModuleTitle : AUSTRALIA HYUNDAI A LEAGUE
     * Home : Sydney FC
     * Away : Newcastle Jets
     * Date : 04/12 04:40PM
     * FT_1x2 : {"1":"1.57","2":"5.80","oid":9112330,"x":"3.72"}
     * FT_DC : {"12":"1.23","oid":9112330,"1x":"1.10","x2":"2.27"}
     * FT_OE : {"oid":9112330,"ODD":"1.00","EVEN":"0.90"}
     * FH_1x2 : {"1":"2.03","2":"5.38","oid":9112336,"x":"2.28"}
     * FH_DC : {"12":"1.47","oid":9112336,"1x":"1.07","x2":"1.60"}
     * FH_OE_N : {"":""}
     * FT_CS_N : {{"oid":9112327,"0:0":"14.5","0:1":"8.75","0:2":"11","0:3":"20","0:4":"48","1:0":"13","1:1":"6.4","1:2":"7.4","1:3":"13.5","1:4":"32","2:0":"23","2:1":"11.5","2:2":"11.5","2:3":"18","2:4":"44","3:0":"60","3:1":"32","3:2":"32","3:3":"48","3:4":"95","4:0":"125","4:1":"110","4:2":"115","4:3":"125","4:4":"125","AOS":"0"}}
     * FH_CS_N : {"":""}
     * HTFT : {"oid":9112329,"HH":"2.25","HD":"13","HA":"30","DH":"4.35","DD":"6.9","DA":"12","AH":"22","AD":"17","AA":"10"}
     * FGLG : {"oid":9112329,"HF":"1.42","HL":"1.42","AF":"3","AL":"3","NO GOAL":"15"}
     * TG : {"oid":9112329,"0~1":"4.15","2~3":"1.86","4~6":"2.62","7 & OVER":"25.00"}
     * HOMETEAMTG_N : {"":""}
     * AWAYTEAMTG_N : {"":""}
     * FT15MINSHANDICAP_OVER_UNDER_0_N : {"":""}
     * FT15MINSHANDICAP_OVER_UNDER_15_N : {"":""}
     * FT15MINSHANDICAP_OVER_UNDER_30_N : {"":""}
     * FT15MINSHANDICAP_OVER_UNDER_45_N : {"":""}
     * FT15MINSHANDICAP_OVER_UNDER_60_N : {"":""}
     */

    @com.google.gson.annotations.SerializedName("ModuleTitle")
    private String ModuleTitle;
    @com.google.gson.annotations.SerializedName("Home")
    private String Home;
    @com.google.gson.annotations.SerializedName("Away")
    private String Away;
    @com.google.gson.annotations.SerializedName("Date")
    private String Date;
    /**
     * 1 : 1.57
     * 2 : 5.80
     * oid : 9112330
     * x : 3.72
     */

    @com.google.gson.annotations.SerializedName("FT_1x2")
    private FT1x2Bean FT1x2;
    /**
     * 12 : 1.23
     * oid : 9112330
     * 1x : 1.10
     * x2 : 2.27
     */

    @com.google.gson.annotations.SerializedName("FT_DC")
    private FTDCBean FTDC;
    /**
     * oid : 9112330
     * ODD : 1.00
     * EVEN : 0.90
     */

    @com.google.gson.annotations.SerializedName("FT_OE")
    private FTOEBean FTOE;
    @com.google.gson.annotations.SerializedName("FH_OE")
    private FTOEBean FHOE;
    /**
     * 1 : 2.03
     * 2 : 5.38
     * oid : 9112336
     * x : 2.28
     */

    @com.google.gson.annotations.SerializedName("FH_1x2")
    private FH1x2Bean FH1x2;
    /**
     * 12 : 1.47
     * oid : 9112336
     * 1x : 1.07
     * x2 : 1.60
     */

    @com.google.gson.annotations.SerializedName("FH_DC")
    private FHDCBean FHDC;
    /**
     * oid : 9112329
     * HH : 2.25
     * HD : 13
     * HA : 30
     * DH : 4.35
     * DD : 6.9
     * DA : 12
     * AH : 22
     * AD : 17
     * AA : 10
     */

    @com.google.gson.annotations.SerializedName("HTFT")
    private HTFTBean HTFT;
    /**
     * oid : 9112329
     * HF : 1.42
     * HL : 1.42
     * AF : 3
     * AL : 3
     * NO GOAL : 15
     */

    @com.google.gson.annotations.SerializedName("FGLG")
    private FGLGBean FGLG;
   private TGBean TG;
    @com.google.gson.annotations.SerializedName("FT_CS")
    private CSBean FTCS;
    @com.google.gson.annotations.SerializedName("FH_CS")
    private CSBean FHCS;

    public CSBean getFTCS() {
        if(FTCS==null)
            return new CSBean();
        return FTCS;
    }

    public void setFTCS(CSBean FTCS) {
        this.FTCS = FTCS;
    }

    public CSBean getFHCS() {
        if(FHCS==null)
            return new CSBean();
        return FHCS;
    }

    public void setFHCS(CSBean FHCS) {
        this.FHCS = FHCS;
    }

    public String getModuleTitle() {
        return ModuleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        ModuleTitle = moduleTitle;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public FT1x2Bean getFT1x2() {
        if(FT1x2==null)
            return new FT1x2Bean();
        return FT1x2;
    }

    public void setFT1x2(FT1x2Bean FT1x2) {
        this.FT1x2 = FT1x2;
    }

    public FTDCBean getFTDC() {
        if(FTDC==null)
            return new FTDCBean();
        return FTDC;
    }

    public void setFTDC(FTDCBean FTDC) {
        this.FTDC = FTDC;
    }

    public FTOEBean getFTOE() {
        if(FTOE!=null)
            return FTOE;
        return new FTOEBean();
    }

    public void setFTOE(FTOEBean FTOE) {
        this.FTOE = FTOE;
    }

    public FH1x2Bean getFH1x2() {
        if(FH1x2==null)
            return new FH1x2Bean();
        return FH1x2;
    }

    public void setFH1x2(FH1x2Bean FH1x2) {
        this.FH1x2 = FH1x2;
    }

    public FHDCBean getFHDC() {
        if(FHDC==null)
            return  new FHDCBean();
        return FHDC;
    }

    public void setFHDC(FHDCBean FHDC) {
        this.FHDC = FHDC;
    }

    public HTFTBean getHTFT() {
        if(HTFT==null)
            return new HTFTBean();
        return HTFT;
    }

    public void setHTFT(HTFTBean HTFT) {
        this.HTFT = HTFT;
    }

    public FGLGBean getFGLG() {
        if(FGLG==null)
            return new FGLGBean();
        return FGLG;
    }

    public void setFGLG(FGLGBean FGLG) {
        this.FGLG = FGLG;
    }

    public TGBean getTG() {
        if(TG==null)
            return new TGBean();
        return TG;
    }

    public void setTG(TGBean TG) {
        this.TG = TG;
    }

    public FTOEBean getFHOE() {
        if(FHOE==null)
            return new FTOEBean();
        return FHOE;
    }

    public void setFHOE(FTOEBean FHOE) {
        this.FHOE = FHOE;
    }
}
