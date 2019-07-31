package com.nanyang.app.main.home.sport.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/30.
 */
public class AdditionBean implements Serializable {

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

    @SerializedName("ModuleTitle")
    private String ModuleTitle;
    @SerializedName("Home")
    private String Home;
    @SerializedName("Away")
    private String Away;
    @SerializedName("Date")
    private String Date;
    /**
     * 1 : 1.57
     * 2 : 5.80
     * oid : 9112330
     * x : 3.72
     */

    @SerializedName("FT_1x2")
    private F1x2Bean FT1x2;
    /**
     * 12 : 1.23
     * oid : 9112330
     * 1x : 1.10
     * x2 : 2.27
     */

    @SerializedName("FT_DC")
    private DCBean FTDC;
    /**
     * oid : 9112330
     * ODD : 1.00
     * EVEN : 0.90
     */

    @SerializedName("FT_OE")
    private FTOEBean FTOE;
    @SerializedName("FH_OE")
    private FTOEBean FHOE;
    /**
     * 1 : 2.03
     * 2 : 5.38
     * oid : 9112336
     * x : 2.28
     */

    @SerializedName("FH_1x2")
    private F1x2Bean FH1x2;
    /**
     * 12 : 1.47
     * oid : 9112336
     * 1x : 1.07
     * x2 : 1.60
     */

    @SerializedName("FH_DC")
    private DCBean FHDC;
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

    @SerializedName("HTFT")
    private HTFTBean HTFT;
    /**
     * oid : 9112329
     * HF : 1.42
     * HL : 1.42
     * AF : 3
     * AL : 3
     * NO GOAL : 15
     */

    @SerializedName("FGLG")
    private FGLGBean FGLG;
    private TGBean TG;
    @SerializedName("FT_CS")
    private CSBean FTCS;
    @SerializedName("FH_CS")
    private CSBean FHCS;
    /**
     * oid : 12732354
     * oid_FH : 12733127
     * FT_OU : 1.5-2
     * FT_O : 1.00
     * FT_U : 0.82
     * FH_OU : 0.5-1
     * FH_O : 1.07
     * FH_U : 0.75
     */

    private TEAMTGBean HOMETEAMTG;
    /**
     * oid : 12732356
     * oid_FH : 12733128
     * FT_OU : 0.5-1
     * FT_O : 0.72
     * FT_U : 1.11
     * FH_OU : 0.5
     * FH_O : 1.56
     * FH_U : 0.46
     */

    private TEAMTGBean AWAYTEAMTG;

    public CSBean getFTCS() {
        if (FTCS == null)
            return new CSBean();
        return FTCS;
    }

    public void setFTCS(CSBean FTCS) {
        this.FTCS = FTCS;
    }

    public CSBean getFHCS() {
        if (FHCS == null)
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

    public F1x2Bean getFT1x2() {
        if (FT1x2 == null)
            return new F1x2Bean();
        return FT1x2;
    }

    public void setFT1x2(F1x2Bean FT1x2) {
        this.FT1x2 = FT1x2;
    }

    public DCBean getFTDC() {
        if (FTDC == null)
            return new DCBean();
        return FTDC;
    }

    public void setFTDC(DCBean FTDC) {
        this.FTDC = FTDC;
    }

    public FTOEBean getFTOE() {
        if (FTOE != null)
            return FTOE;
        return new FTOEBean();
    }

    public void setFTOE(FTOEBean FTOE) {
        this.FTOE = FTOE;
    }

    public F1x2Bean getFH1x2() {
        if (FH1x2 == null)
            return new F1x2Bean();
        return FH1x2;
    }

    public void setFH1x2(F1x2Bean FH1x2) {
        this.FH1x2 = FH1x2;
    }

    public DCBean getFHDC() {
        if (FHDC == null)
            return new DCBean();
        return FHDC;
    }

    public void setFHDC(DCBean FHDC) {
        this.FHDC = FHDC;
    }

    public HTFTBean getHTFT() {
        if (HTFT == null)
            return new HTFTBean();
        return HTFT;
    }

    public void setHTFT(HTFTBean HTFT) {
        this.HTFT = HTFT;
    }

    public FGLGBean getFGLG() {
        if (FGLG == null)
            return new FGLGBean();
        return FGLG;
    }

    public void setFGLG(FGLGBean FGLG) {
        this.FGLG = FGLG;
    }

    public TGBean getTG() {
        if (TG == null)
            return new TGBean();
        return TG;
    }

    public void setTG(TGBean TG) {
        this.TG = TG;
    }

    public FTOEBean getFHOE() {
        if (FHOE == null)
            return new FTOEBean();
        return FHOE;
    }

    public void setFHOE(FTOEBean FHOE) {
        this.FHOE = FHOE;
    }


    /**
     * oid : 12731785
     * FT_OU : 0.5
     * O : 2.27
     * U : 0.24
     */

    private FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_0;
    /**
     * oid : 12731786
     * FT_OU : 0.5
     * O : 2.08
     * U : 0.28
     */

    private FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_15;
    /**
     * :
     */

    private FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_30_N;
    /**
     * oid : 12731788
     * FT_OU : 0.5
     * O : 1.92
     * U : 0.32
     */

    private FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_45;
    /**
     * oid : 12731831
     * FT_OU : 0.5
     * O : 1.78
     * U : 0.36
     */

    private FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_60;

    public FT15MINSHANDICAPOVERUNDERBean getFT15MINSHANDICAP_OVER_UNDER_0() {
        return FT15MINSHANDICAP_OVER_UNDER_0;
    }

    public void setFT15MINSHANDICAP_OVER_UNDER_0(FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_0) {
        this.FT15MINSHANDICAP_OVER_UNDER_0 = FT15MINSHANDICAP_OVER_UNDER_0;
    }

    public FT15MINSHANDICAPOVERUNDERBean getFT15MINSHANDICAP_OVER_UNDER_15() {
        return FT15MINSHANDICAP_OVER_UNDER_15;
    }

    public void setFT15MINSHANDICAP_OVER_UNDER_15(FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_15) {
        this.FT15MINSHANDICAP_OVER_UNDER_15 = FT15MINSHANDICAP_OVER_UNDER_15;
    }

    public FT15MINSHANDICAPOVERUNDERBean getFT15MINSHANDICAP_OVER_UNDER_30_N() {
        return FT15MINSHANDICAP_OVER_UNDER_30_N;
    }

    public void setFT15MINSHANDICAP_OVER_UNDER_30_N(FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_30_N) {
        this.FT15MINSHANDICAP_OVER_UNDER_30_N = FT15MINSHANDICAP_OVER_UNDER_30_N;
    }

    public FT15MINSHANDICAPOVERUNDERBean getFT15MINSHANDICAP_OVER_UNDER_45() {
        return FT15MINSHANDICAP_OVER_UNDER_45;
    }

    public void setFT15MINSHANDICAP_OVER_UNDER_45(FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_45) {
        this.FT15MINSHANDICAP_OVER_UNDER_45 = FT15MINSHANDICAP_OVER_UNDER_45;
    }

    public FT15MINSHANDICAPOVERUNDERBean getFT15MINSHANDICAP_OVER_UNDER_60() {
        return FT15MINSHANDICAP_OVER_UNDER_60;
    }

    public void setFT15MINSHANDICAP_OVER_UNDER_60(FT15MINSHANDICAPOVERUNDERBean FT15MINSHANDICAP_OVER_UNDER_60) {
        this.FT15MINSHANDICAP_OVER_UNDER_60 = FT15MINSHANDICAP_OVER_UNDER_60;
    }

    public TEAMTGBean getHOMETEAMTG() {
        return HOMETEAMTG;
    }

    public void setHOMETEAMTG(TEAMTGBean HOMETEAMTG) {
        this.HOMETEAMTG = HOMETEAMTG;
    }

    public TEAMTGBean getAWAYTEAMTG() {
        return AWAYTEAMTG;
    }

    public void setAWAYTEAMTG(TEAMTGBean AWAYTEAMTG) {
        this.AWAYTEAMTG = AWAYTEAMTG;
    }

    public static class FT15MINSHANDICAPOVERUNDERBean {
        private int oid;
        private String FT_OU;
        private String O;
        private String U;

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getFT_OU() {
            return FT_OU;
        }

        public void setFT_OU(String FT_OU) {
            this.FT_OU = FT_OU;
        }

        public String getO() {
            return O;
        }

        public void setO(String O) {
            this.O = O;
        }

        public String getU() {
            return U;
        }

        public void setU(String U) {
            this.U = U;
        }
    }


    public static class TEAMTGBean {
        private int oid;
        private int oid_FH;
        private String FT_OU;
        private String FT_O;
        private String FT_U;
        private String FH_OU;
        private String FH_O;
        private String FH_U;

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public int getOid_FH() {
            return oid_FH;
        }

        public void setOid_FH(int oid_FH) {
            this.oid_FH = oid_FH;
        }

        public String getFT_OU() {
            if (FT_OU.equals("0"))
                FT_OU = "";
            return FT_OU;
        }

        public void setFT_OU(String FT_OU) {
            this.FT_OU = FT_OU;
        }

        public String getFT_O() {
            if (FT_OU.equals("") || FT_OU.equals("0"))
                FT_O = "";
            return FT_O;
        }

        public void setFT_O(String FT_O) {
            this.FT_O = FT_O;
        }

        public String getFT_U() {
            if (FT_OU.equals("") || FT_OU.equals("0"))
                FT_U = "";
            return FT_U;
        }

        public void setFT_U(String FT_U) {
            this.FT_U = FT_U;
        }

        public String getFH_OU() {
            if (FH_OU.equals("0"))
                FH_OU = "";
            return FH_OU;
        }

        public void setFH_OU(String FH_OU) {
            this.FH_OU = FH_OU;
        }

        public String getFH_O() {
            if (FH_OU.equals("") || FH_OU.equals("0"))
                FH_O = "";
            return FH_O;
        }

        public void setFH_O(String FH_O) {
            this.FH_O = FH_O;
        }

        public String getFH_U() {
            if (FH_OU.equals("") || FH_OU.equals("0"))
                FH_U = "";
            return FH_U;
        }

        public void setFH_U(String FH_U) {
            this.FH_U = FH_U;
        }
    }
}
