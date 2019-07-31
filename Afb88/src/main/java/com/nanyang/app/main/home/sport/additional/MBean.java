package com.nanyang.app.main.home.sport.additional;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/4.
 */

public class MBean implements Serializable {

    /**
     * ModuleTitle : 3
     * SocOddsId : 482202
     * LeagueName : 英格兰超级联赛
     * homeName : 般尼茅夫
     * awayName : 哈德斯菲尔德
     * live : 05/12 03:45 AM
     * Param : 'pgajaxS.axd?T=MB2&ot=&oId=482202&home=&away=&ModuleTitle=&date='
     * Betable : True
     * O2 : 大
     * U2 : 小
     * FTHDP_CNT : 3
     * FTodds : [{"SocOddsId":"482202","Home":"主场","Away":"客场","HDP":"0.75","Hdp_visible":"True","HomeOdds":"9.2","AwayOdds":"-9.8","Over":"大","Under":"小","OU":"2.5","OU_visible":"True","OverOdds":"9.7","UnderOdds":"9.3","IsHomeGive":"True","IsRun":"False","HasPar":"True"},{"SocOddsId":"482203","Home":"主场","Away":"客场","HDP":"0.5","Hdp_visible":"True","HomeOdds":"6.8","AwayOdds":"-7.4","Over":"大","Under":"小","OU":"2.25","OU_visible":"True","OverOdds":"7.2","UnderOdds":"-8.2","IsHomeGive":"True","IsRun":"False","HasPar":"True"},{"SocOddsId":"482204","Home":"主场","Away":"客场","HDP":"1","Hdp_visible":"True","HomeOdds":"-7.9","AwayOdds":"7.3","Over":"大","Under":"小","OU":"2.75","OU_visible":"True","OverOdds":"-8.2","UnderOdds":"7.2","IsHomeGive":"True","IsRun":"False","HasPar":"True"}]
     * FHHDP_CNT : 2
     * FHodds : [{"SocOddsId":"482281","Home":"主场","Away":"客场","HDP":"0.25","Hdp_visible":"True","HomeOdds":"8.9","AwayOdds":"-9.9","Over":"大","Under":"小","OU":"1","OU_visible":"True","OverOdds":"9.1","UnderOdds":"9.9","IsHomeGive":"True","IsRun":"False","HasPar":"True"},{"SocOddsId":"482282","Home":"主场","Away":"客场","HDP":"0.5","Hdp_visible":"True","HomeOdds":"-7.4","AwayOdds":"6.4","Over":"大","Under":"小","OU":"1.25","OU_visible":"True","OverOdds":"-7.1","UnderOdds":"6.1","IsHomeGive":"True","IsRun":"False","HasPar":"True"}]
     * F1X2_CNT : 1
     * F1X2_SocOddsId : 482202
     * F1X2_IsRun : False
     * HasPar : True
     * 1IsInetBet : True
     * F1 : 1.70
     * XIsInetBet : True
     * FX : 3.61
     * 2IsInetBet : True
     * F2 : 5.22
     * FDB_CNT : 1
     * FDB_SocOddsId : 482202
     * FDB_IsRun : False
     * FHDIsInetBet : True
     * FHD : 1.16
     * FHAIsInetBet : True
     * FHA : 1.28
     * FDAIsInetBet : True
     * FDA : 2.13
     * FOE_CNT : 1
     * FOE_SocOddsId : 482202
     * FOE_IsRun : False
     * FOE_HasPar : True
     * FOddIsInetBet : True
     * FOdd : -10
     * FEvenIsInetBet : True
     * FEven : 9
     * H1X2_CNT : 1
     * H1X2_SocOddsId : 482281
     * H1X2_IsRun : False
     * HHasPar : True
     * H1IsInetBet : True
     * H1 : 2.34
     * HXIsInetBet : True
     * HX : 2.23
     * H2IsInetBet : True
     * H2 : 4.54
     * HDB_CNT : 1
     * HDB_SocOddsId : 482281
     * HDB_IsRun : False
     * HHDIsInetBet : True
     * HHD : 1.14
     * HHAIsInetBet : True
     * HHA : 1.54
     * HDAIsInetBet : True
     * HDA : 1.50
     * HOE_CNT : 0
     * FCS_CNT : 1
     * FCS_SocOddsId : 482202
     * FCS_IsRun : False
     * G11IsInetBet : True
     * G11 : 9.8
     * G17IsInetBet : True
     * G17 : 13
     * G18IsInetBet : True
     * G18 : 34.4
     * G20IsInetBet : True
     * G20 : 130.8
     * G23IsInetBet : True
     * G23 : 226
     * G1IsInetBet : True
     * G1 : 6.08
     * G12IsInetBet : True
     * G12 : 6.88
     * G19IsInetBet : True
     * G19 : 17.8
     * G21IsInetBet : True
     * G21 : 70.8
     * G24IsInetBet : True
     * G24 : 226
     * G2IsInetBet : True
     * G2 : 7.6
     * G3IsInetBet : True
     * G3 : 7.2
     * G13IsInetBet : True
     * G13 : 18.4
     * G22IsInetBet : True
     * G22 : 72.4
     * G25IsInetBet : True
     * G25 : 226
     * G4IsInetBet : True
     * G4 : 14.2
     * G5IsInetBet : True
     * G5 : 13.2
     * G6IsInetBet : True
     * G6 : 30.2
     * G14IsInetBet : True
     * G14 : 108.4
     * G26IsInetBet : True
     * G26 : 226
     * G7IsInetBet : True
     * G7 : 34.8
     * G8IsInetBet : True
     * G8 : 33.6
     * G9IsInetBet : True
     * G9 : 62.2
     * G10IsInetBet : True
     * G10 : 226
     * G15IsInetBet : True
     * G15 : 240
     * G16IsInetBet : True
     * G16 : 27.8
     * HCS_CNT : 1
     * HCS_SocOddsId : 482281
     * HCS_IsRun : False
     * HG11IsInetBet : True
     * HG11 : 2.25
     * HG17IsInetBet : True
     * HG17 : 7.14
     * HG18IsInetBet : True
     * HG18 : 46.6
     * HG20IsInetBet : True
     * HG20 : 226
     * HG1IsInetBet : True
     * HG1 : 3.02
     * HG12IsInetBet : True
     * HG12 : 7.1
     * HG19IsInetBet : True
     * HG19 : 46.8
     * HG21IsInetBet : True
     * HG21 : 226
     * HG2IsInetBet : True
     * HG2 : 8.26
     * HG3IsInetBet : True
     * HG3 : 14.2
     * HG13IsInetBet : True
     * HG13 : 87
     * HG22IsInetBet : True
     * HG22 : 226
     * HG4IsInetBet : True
     * HG4 : 33.6
     * HG5IsInetBet : True
     * HG5 : 60.4
     * HG6IsInetBet : True
     * HG6 : 226
     * HG14IsInetBet : True
     * HG14 : 230
     * HG16IsInetBet : True
     * HG16 : 85.4
     * ISRUN : True
     * HTFT_CNT : 1
     * HTFT_SocOddsId : 482202
     * HTFT_IsRun : False
     * HHIsInetBet : True
     * HH : 2.33
     * HDIsInetBet : True
     * HD : 16.4
     * HAIsInetBet : True
     * HA : 41.4
     * DHIsInetBet : True
     * DH : 4.36
     * DDIsInetBet : True
     * DD : 5.5
     * DAIsInetBet : True
     * DA : 11.8
     * AHIsInetBet : True
     * AH : 25.8
     * ADIsInetBet : True
     * AD : 17.2
     * AAIsInetBet : True
     * AA : 11.12
     * FGLG_CNT : 1
     * FGLG_SocOddsId : 482202
     * FGLG_IsRun : False
     * HFGIsInetBet : True
     * HFG : 1.46
     * HLGIsInetBet : True
     * HLG : 1.46
     * AFGIsInetBet : True
     * AFG : 3.09
     * ALGIsInetBet : True
     * ALG : 3.09
     * NGIsInetBet : True
     * NG : 10.2
     * TG_CNT : 1
     * TG_SocOddsId : 482202
     * TG_IsRun : False
     * Goal1IsInetBet : True
     * Goal1 : 3.20
     * Goal2IsInetBet : True
     * Goal2 : 1.90
     * Goal3IsInetBet : True
     * Goal3 : 3.64
     * Goal4IsInetBet : True
     * Goal4 : 25.00
     * HTTG_CNT : 1
     * HTTG_SocOddsId : 482388
     * HTTG_IsRun : False
     * HTTG_OU : 1.5
     * HTTG_IsHomeGive : True
     * HTTG_FHOU : 0.5
     * HTTG_FHIsHomeGive : True
     * HTHDP1 : 1.5
     * HTHDP2 : 0.5
     * FHOOddsIsInetBet : True
     * FHOOdds : 8.8
     * HHOOddsIsInetBet : True
     * HHOOdds : 7.7
     * FHUOddsIsInetBet : True
     * FHUOdds : 9.8
     * HHUOddsIsInetBet : True
     * HHUOdds : -9.1
     * ATTG_CNT : 1
     * ATTG_SocOddsId : 482387
     * ATTG_IsRun : False
     * ATTG_OU : 0.5
     * ATTG_IsHomeGive : True
     * ATTG_FHOU : 0.5
     * ATTG_FHIsHomeGive : True
     * ATHDP1 : 0.5
     * ATHDP2 : 0.5
     * FAOOddsIsInetBet : True
     * FAOOdds : 6.3
     * HAOOddsIsInetBet : True
     * HAOOdds : -5.4
     * FAUOddsIsInetBet : True
     * FAUOdds : -7.7
     * HAUOddsIsInetBet : True
     * HAUOdds : 4.1
     * MG15_CNT : 1
     * MG15_SocOddsId : 482468
     * MG15_IsRun : False
     * MG15_OU : 0.5
     * MG15_IsHomeGive : True
     * FT15InfoOU : 0.5
     * FT15InfoOUIsInetBet : True
     * FT15InfoO : -4.5
     * FT15InfoUIsInetBet : True
     * FT15InfoU : 2.5
     * MG30_CNT : 1
     * MG30_SocOddsId : 482469
     * MG30_IsRun : False
     * MG30_OU : 0.5
     * MG30_IsHomeGive : True
     * FT30InfoOU : 0.5
     * FT30InfoOIsInetBet : True
     * FT30InfoO : -5.2
     * FT30InfoUIsInetBet : True
     * FT30InfoU : 3.2
     * MG45_CNT : 0
     * MG60_CNT : 1
     * MG60_SocOddsId : 482470
     * MG60_IsRun : False
     * MG60_OU : 0.5
     * MG60_IsHomeGive : True
     * FT60InfoOU : 0.5
     * FT60InfoOIsInetBet : True
     * FT60InfoO : -5.6
     * FT60InfoUIsInetBet : True
     * FT60InfoU : 3.6
     * MG75_CNT : 1
     * MG75_SocOddsId : 482471
     * MG75_IsRun : False
     * MG75_OU : 0.5
     * MG75_IsHomeGive : True
     * FT75InfoOU : 0.5
     * FT75InfoOIsInetBet : True
     * FT75InfoO : -6.1
     * FT75InfoUIsInetBet : True
     * FT75InfoU : 4.1
     * _count : 14
     */

    private String ModuleTitle;
    private String SocOddsId;
    private String LeagueName;
    private String homeName;
    private String awayName;
    private String live;
    private String Param;
    private String Betable;
    private String O2;
    private String U2;
    private String FTHDP_CNT;
    private String FHHDP_CNT;
    private String F1X2_CNT;
    private String F1X2_SocOddsId;
    private String F1X2_IsRun;
    private String HasPar;
    @SerializedName("1IsInetBet")
    private String value1IsInetBet;
    private String F1;
    private String XIsInetBet;
    private String FX;
    @SerializedName("2IsInetBet")
    private String value2IsInetBet;
    private String F2;
    private String FDB_CNT;
    private String FDB_SocOddsId;
    private String FDB_IsRun;
    private String FHDIsInetBet;
    private String FHD;
    private String FHAIsInetBet;
    private String FHA;
    private String FDAIsInetBet;
    private String FDA;
    private String FOE_CNT;
    private String FOE_SocOddsId;
    private String FOE_IsRun;
    private String FOE_HasPar;
    private String FOddIsInetBet;
    private String FOdd;
    private String FEvenIsInetBet;
    private String FEven;
    private String H1X2_CNT;
    private String H1X2_SocOddsId;
    private String H1X2_IsRun;
    private String HHasPar;
    private String H1IsInetBet;
    private String H1;
    private String HXIsInetBet;
    private String HX;
    private String H2IsInetBet;
    private String H2;
    private String HDB_CNT;
    private String HDB_SocOddsId;
    private String HDB_IsRun;
    private String HHDIsInetBet;
    private String HHD;
    private String HHAIsInetBet;
    private String HHA;
    private String HDAIsInetBet;
    private String HDA;
    private String HOE_CNT;
    private String FCS_CNT;
    private String FCS_SocOddsId;
    private String FCS_IsRun;
    private String G11IsInetBet;
    private String G11;
    private String G17IsInetBet;
    private String G17;
    private String G18IsInetBet;
    private String G18;
    private String G20IsInetBet;
    private String G20;
    private String G23IsInetBet;
    private String G23;
    private String G1IsInetBet;
    private String G1;
    private String G12IsInetBet;
    private String G12;
    private String G19IsInetBet;
    private String G19;
    private String G21IsInetBet;
    private String G21;
    private String G24IsInetBet;
    private String G24;
    private String G2IsInetBet;
    private String G2;
    private String G3IsInetBet;
    private String G3;
    private String G13IsInetBet;
    private String G13;
    private String G22IsInetBet;
    private String G22;
    private String G25IsInetBet;
    private String G25;
    private String G4IsInetBet;
    private String G4;
    private String G5IsInetBet;
    private String G5;
    private String G6IsInetBet;
    private String G6;
    private String G14IsInetBet;
    private String G14;
    private String G26IsInetBet;
    private String G26;
    private String G7IsInetBet;
    private String G7;
    private String G8IsInetBet;
    private String G8;
    private String G9IsInetBet;
    private String G9;
    private String G10IsInetBet;
    private String G10;
    private String G15IsInetBet;
    private String G15;
    private String G16IsInetBet;
    private String G16;
    private String HCS_CNT;
    private String HCS_SocOddsId;
    private String HCS_IsRun;
    private String HG11IsInetBet;
    private String HG11;
    private String HG17IsInetBet;
    private String HG17;
    private String HG18IsInetBet;
    private String HG18;
    private String HG20IsInetBet;
    private String HG20;
    private String HG1IsInetBet;
    private String HG1;
    private String HG12IsInetBet;
    private String HG12;
    private String HG19IsInetBet;
    private String HG19;
    private String HG21IsInetBet;
    private String HG21;
    private String HG2IsInetBet;
    private String HG2;
    private String HG3IsInetBet;
    private String HG3;
    private String HG13IsInetBet;
    private String HG13;
    private String HG22IsInetBet;
    private String HG22;
    private String HG4IsInetBet;
    private String HG4;
    private String HG5IsInetBet;
    private String HG5;
    private String HG6IsInetBet;
    private String HG6;
    private String HG14IsInetBet;
    private String HG14;
    private String HG16IsInetBet;
    private String HG16;
    private String ISRUN;
    private String HTFT_CNT;
    private String HTFT_SocOddsId;
    private String HTFT_IsRun;
    private String HHIsInetBet;
    private String HH;
    private String HDIsInetBet;
    private String HD;
    private String HAIsInetBet;
    private String HA;
    private String DHIsInetBet;
    private String DH;
    private String DDIsInetBet;
    private String DD;
    private String DAIsInetBet;
    private String DA;
    private String AHIsInetBet;
    private String AH;
    private String ADIsInetBet;
    private String AD;
    private String AAIsInetBet;
    private String AA;
    private String FGLG_CNT;
    private String FGLG_SocOddsId;
    private String FGLG_IsRun;
    private String HFGIsInetBet;
    private String HFG;
    private String HLGIsInetBet;
    private String HLG;
    private String AFGIsInetBet;
    private String AFG;
    private String ALGIsInetBet;
    private String ALG;
    private String NGIsInetBet;
    private String NG;
    private String TG_CNT;
    private String TG_SocOddsId;
    private String TG_IsRun;
    private String Goal1IsInetBet;
    private String Goal1;
    private String Goal2IsInetBet;
    private String Goal2;
    private String Goal3IsInetBet;
    private String Goal3;
    private String Goal4IsInetBet;
    private String Goal4;
    private String HTTG_CNT;
    private String HTTG_SocOddsId;
    private String HTTG_IsRun;
    private String HTTG_OU;
    private String HTTG_IsHomeGive;
    private String HTTG_FHOU;
    private String HTTG_FHIsHomeGive;
    private String HTHDP1;
    private String HTHDP2;
    private String FHOOddsIsInetBet;
    private String FHOOdds;
    private String HHOOddsIsInetBet;
    private String HHOOdds;
    private String FHUOddsIsInetBet;
    private String FHUOdds;
    private String HHUOddsIsInetBet;
    private String HHUOdds;
    private String ATTG_CNT;
    private String ATTG_SocOddsId;
    private String ATTG_IsRun;
    private String ATTG_OU;
    private String ATTG_IsHomeGive;
    private String ATTG_FHOU;
    private String ATTG_FHIsHomeGive;
    private String ATHDP1;
    private String ATHDP2;
    private String FAOOddsIsInetBet;
    private String FAOOdds;
    private String HAOOddsIsInetBet;
    private String HAOOdds;
    private String FAUOddsIsInetBet;
    private String FAUOdds;
    private String HAUOddsIsInetBet;
    private String HAUOdds;
    private String MG15_CNT;
    private String MG15_SocOddsId;
    private String MG15_IsRun;
    private String MG15_OU;
    private String MG15_IsHomeGive;
    private String FT15InfoOU;
    private String FT15InfoOUIsInetBet;
    private String FT15InfoO;
    private String FT15InfoUIsInetBet;
    private String FT15InfoU;
    private String MG30_CNT;
    private String MG30_SocOddsId;
    private String MG30_IsRun;
    private String MG30_OU;
    private String MG30_IsHomeGive;
    private String FT30InfoOU;
    private String FT30InfoOIsInetBet;
    private String FT30InfoO;
    private String FT30InfoUIsInetBet;
    private String FT30InfoU;
    private String MG45_CNT;
    private String MG60_CNT;
    private String MG60_SocOddsId;
    private String MG60_IsRun;
    private String MG60_OU;
    private String MG60_IsHomeGive;
    private String FT60InfoOU;
    private String FT60InfoOIsInetBet;
    private String FT60InfoO;
    private String FT60InfoUIsInetBet;
    private String FT60InfoU;
    private String MG75_CNT;
    private String MG75_SocOddsId;
    private String MG75_IsRun;
    private String MG75_OU;
    private String MG75_IsHomeGive;
    private String FT75InfoOU;
    private String FT75InfoOIsInetBet;
    private String FT75InfoO;
    private String FT75InfoUIsInetBet;
    private String FT75InfoU;
    private String _count;
    /**
     * SocOddsId : 482202
     * Home : 主场
     * Away : 客场
     * HDP : 0.75
     * Hdp_visible : True
     * HomeOdds : 9.2
     * AwayOdds : -9.8
     * Over : 大
     * Under : 小
     * OU : 2.5
     * OU_visible : True
     * OverOdds : 9.7
     * UnderOdds : 9.3
     * IsHomeGive : True
     * IsRun : False
     * HasPar : True
     */

    private List<FToddsBean> FTodds;
    /**
     * SocOddsId : 482281
     * Home : 主场
     * Away : 客场
     * HDP : 0.25
     * Hdp_visible : True
     * HomeOdds : 8.9
     * AwayOdds : -9.9
     * Over : 大
     * Under : 小
     * OU : 1
     * OU_visible : True
     * OverOdds : 9.1
     * UnderOdds : 9.9
     * IsHomeGive : True
     * IsRun : False
     * HasPar : True
     */

    private List<FHoddsBean> FHodds;

    public String getModuleTitle() {
        return ModuleTitle;
    }

    public void setModuleTitle(String ModuleTitle) {
        this.ModuleTitle = ModuleTitle;
    }

    public int getSocOddsId() {
        return Integer.valueOf(SocOddsId);
    }

    public void setSocOddsId(String SocOddsId) {
        this.SocOddsId = SocOddsId;
    }

    public String getLeagueName() {
        return LeagueName;
    }

    public void setLeagueName(String LeagueName) {
        this.LeagueName = LeagueName;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getParam() {
        return Param;
    }

    public void setParam(String Param) {
        this.Param = Param;
    }

    public String getBetable() {
        return Betable;
    }

    public void setBetable(String Betable) {
        this.Betable = Betable;
    }

    public String getO2() {
        return O2;
    }

    public void setO2(String O2) {
        this.O2 = O2;
    }

    public String getU2() {
        return U2;
    }

    public void setU2(String U2) {
        this.U2 = U2;
    }

    public String getFTHDP_CNT() {
        return FTHDP_CNT;
    }

    public void setFTHDP_CNT(String FTHDP_CNT) {
        this.FTHDP_CNT = FTHDP_CNT;
    }

    public String getFHHDP_CNT() {
        return FHHDP_CNT;
    }

    public void setFHHDP_CNT(String FHHDP_CNT) {
        this.FHHDP_CNT = FHHDP_CNT;
    }

    public String getF1X2_CNT() {
        return F1X2_CNT;
    }

    public void setF1X2_CNT(String F1X2_CNT) {
        this.F1X2_CNT = F1X2_CNT;
    }

    public int getF1X2_SocOddsId() {
        return Integer.valueOf(F1X2_SocOddsId);
    }

    public void setF1X2_SocOddsId(String F1X2_SocOddsId) {
        this.F1X2_SocOddsId = F1X2_SocOddsId;
    }

    public String getF1X2_IsRun() {
        return F1X2_IsRun;
    }

    public void setF1X2_IsRun(String F1X2_IsRun) {
        this.F1X2_IsRun = F1X2_IsRun;
    }

    public String getHasPar() {
        return HasPar;
    }

    public void setHasPar(String HasPar) {
        this.HasPar = HasPar;
    }

    public String getValue1IsInetBet() {
        return value1IsInetBet;
    }

    public void setValue1IsInetBet(String value1IsInetBet) {
        this.value1IsInetBet = value1IsInetBet;
    }

    public String getF1() {
        return F1;
    }

    public void setF1(String F1) {
        this.F1 = F1;
    }

    public String getXIsInetBet() {
        return XIsInetBet;
    }

    public void setXIsInetBet(String XIsInetBet) {
        this.XIsInetBet = XIsInetBet;
    }

    public String getFX() {
        return FX;
    }

    public void setFX(String FX) {
        this.FX = FX;
    }

    public String getValue2IsInetBet() {
        return value2IsInetBet;
    }

    public void setValue2IsInetBet(String value2IsInetBet) {
        this.value2IsInetBet = value2IsInetBet;
    }

    public String getF2() {
        return F2;
    }

    public void setF2(String F2) {
        this.F2 = F2;
    }

    public String getFDB_CNT() {
        return FDB_CNT;
    }

    public void setFDB_CNT(String FDB_CNT) {
        this.FDB_CNT = FDB_CNT;
    }

    public int getFDB_SocOddsId() {
        return Integer.valueOf(FDB_SocOddsId);
    }

    public void setFDB_SocOddsId(String FDB_SocOddsId) {
        this.FDB_SocOddsId = FDB_SocOddsId;
    }

    public String getFDB_IsRun() {
        return FDB_IsRun;
    }

    public void setFDB_IsRun(String FDB_IsRun) {
        this.FDB_IsRun = FDB_IsRun;
    }

    public String getFHDIsInetBet() {
        return FHDIsInetBet;
    }

    public void setFHDIsInetBet(String FHDIsInetBet) {
        this.FHDIsInetBet = FHDIsInetBet;
    }

    public String getFHD() {
        return FHD;
    }

    public void setFHD(String FHD) {
        this.FHD = FHD;
    }

    public String getFHAIsInetBet() {
        return FHAIsInetBet;
    }

    public void setFHAIsInetBet(String FHAIsInetBet) {
        this.FHAIsInetBet = FHAIsInetBet;
    }

    public String getFHA() {
        return FHA;
    }

    public void setFHA(String FHA) {
        this.FHA = FHA;
    }

    public String getFDAIsInetBet() {
        return FDAIsInetBet;
    }

    public void setFDAIsInetBet(String FDAIsInetBet) {
        this.FDAIsInetBet = FDAIsInetBet;
    }

    public String getFDA() {
        return FDA;
    }

    public void setFDA(String FDA) {
        this.FDA = FDA;
    }

    public String getFOE_CNT() {
        return FOE_CNT;
    }

    public void setFOE_CNT(String FOE_CNT) {
        this.FOE_CNT = FOE_CNT;
    }

    public int getFOE_SocOddsId() {
        return Integer.valueOf(FOE_SocOddsId);
    }

    public void setFOE_SocOddsId(String FOE_SocOddsId) {
        this.FOE_SocOddsId = FOE_SocOddsId;
    }

    public String getFOE_IsRun() {
        return FOE_IsRun;
    }

    public void setFOE_IsRun(String FOE_IsRun) {
        this.FOE_IsRun = FOE_IsRun;
    }

    public String getFOE_HasPar() {
        return FOE_HasPar;
    }

    public void setFOE_HasPar(String FOE_HasPar) {
        this.FOE_HasPar = FOE_HasPar;
    }

    public String getFOddIsInetBet() {
        return FOddIsInetBet;
    }

    public void setFOddIsInetBet(String FOddIsInetBet) {
        this.FOddIsInetBet = FOddIsInetBet;
    }

    public String getFOdd() {
        return FOdd;
    }

    public void setFOdd(String FOdd) {
        this.FOdd = FOdd;
    }

    public String getFEvenIsInetBet() {
        return FEvenIsInetBet;
    }

    public void setFEvenIsInetBet(String FEvenIsInetBet) {
        this.FEvenIsInetBet = FEvenIsInetBet;
    }

    public String getFEven() {
        return FEven;
    }

    public void setFEven(String FEven) {
        this.FEven = FEven;
    }

    public String getH1X2_CNT() {
        return H1X2_CNT;
    }

    public void setH1X2_CNT(String H1X2_CNT) {
        this.H1X2_CNT = H1X2_CNT;
    }

    public int getH1X2_SocOddsId() {
        return Integer.valueOf(H1X2_SocOddsId);
    }

    public void setH1X2_SocOddsId(String H1X2_SocOddsId) {
        this.H1X2_SocOddsId = H1X2_SocOddsId;
    }

    public String getH1X2_IsRun() {
        return H1X2_IsRun;
    }

    public void setH1X2_IsRun(String H1X2_IsRun) {
        this.H1X2_IsRun = H1X2_IsRun;
    }

    public String getHHasPar() {
        return HHasPar;
    }

    public void setHHasPar(String HHasPar) {
        this.HHasPar = HHasPar;
    }

    public String getH1IsInetBet() {
        return H1IsInetBet;
    }

    public void setH1IsInetBet(String H1IsInetBet) {
        this.H1IsInetBet = H1IsInetBet;
    }

    public String getH1() {
        return H1;
    }

    public void setH1(String H1) {
        this.H1 = H1;
    }

    public String getHXIsInetBet() {
        return HXIsInetBet;
    }

    public void setHXIsInetBet(String HXIsInetBet) {
        this.HXIsInetBet = HXIsInetBet;
    }

    public String getHX() {
        return HX;
    }

    public void setHX(String HX) {
        this.HX = HX;
    }

    public String getH2IsInetBet() {
        return H2IsInetBet;
    }

    public void setH2IsInetBet(String H2IsInetBet) {
        this.H2IsInetBet = H2IsInetBet;
    }

    public String getH2() {
        return H2;
    }

    public void setH2(String H2) {
        this.H2 = H2;
    }

    public String getHDB_CNT() {
        return HDB_CNT;
    }

    public void setHDB_CNT(String HDB_CNT) {
        this.HDB_CNT = HDB_CNT;
    }

    public int getHDB_SocOddsId() {
        return Integer.valueOf(HDB_SocOddsId);
    }

    public void setHDB_SocOddsId(String HDB_SocOddsId) {
        this.HDB_SocOddsId = HDB_SocOddsId;
    }

    public String getHDB_IsRun() {
        return HDB_IsRun;
    }

    public void setHDB_IsRun(String HDB_IsRun) {
        this.HDB_IsRun = HDB_IsRun;
    }

    public String getHHDIsInetBet() {
        return HHDIsInetBet;
    }

    public void setHHDIsInetBet(String HHDIsInetBet) {
        this.HHDIsInetBet = HHDIsInetBet;
    }

    public String getHHD() {
        return HHD;
    }

    public void setHHD(String HHD) {
        this.HHD = HHD;
    }

    public String getHHAIsInetBet() {
        return HHAIsInetBet;
    }

    public void setHHAIsInetBet(String HHAIsInetBet) {
        this.HHAIsInetBet = HHAIsInetBet;
    }

    public String getHHA() {
        return HHA;
    }

    public void setHHA(String HHA) {
        this.HHA = HHA;
    }

    public String getHDAIsInetBet() {
        return HDAIsInetBet;
    }

    public void setHDAIsInetBet(String HDAIsInetBet) {
        this.HDAIsInetBet = HDAIsInetBet;
    }

    public String getHDA() {
        return HDA;
    }

    public void setHDA(String HDA) {
        this.HDA = HDA;
    }

    public String getHOE_CNT() {
        return HOE_CNT;
    }

    public void setHOE_CNT(String HOE_CNT) {
        this.HOE_CNT = HOE_CNT;
    }

    public String getFCS_CNT() {
        return FCS_CNT;
    }

    public void setFCS_CNT(String FCS_CNT) {
        this.FCS_CNT = FCS_CNT;
    }

    public String getFCS_SocOddsId() {
        return FCS_SocOddsId;
    }

    public void setFCS_SocOddsId(String FCS_SocOddsId) {
        this.FCS_SocOddsId = FCS_SocOddsId;
    }

    public String getFCS_IsRun() {
        return FCS_IsRun;
    }

    public void setFCS_IsRun(String FCS_IsRun) {
        this.FCS_IsRun = FCS_IsRun;
    }

    public String getG11IsInetBet() {
        return G11IsInetBet;
    }

    public void setG11IsInetBet(String G11IsInetBet) {
        this.G11IsInetBet = G11IsInetBet;
    }

    public String getG11() {
        return G11;
    }

    public void setG11(String G11) {
        this.G11 = G11;
    }

    public String getG17IsInetBet() {
        return G17IsInetBet;
    }

    public void setG17IsInetBet(String G17IsInetBet) {
        this.G17IsInetBet = G17IsInetBet;
    }

    public String getG17() {
        return G17;
    }

    public void setG17(String G17) {
        this.G17 = G17;
    }

    public String getG18IsInetBet() {
        return G18IsInetBet;
    }

    public void setG18IsInetBet(String G18IsInetBet) {
        this.G18IsInetBet = G18IsInetBet;
    }

    public String getG18() {
        return G18;
    }

    public void setG18(String G18) {
        this.G18 = G18;
    }

    public String getG20IsInetBet() {
        return G20IsInetBet;
    }

    public void setG20IsInetBet(String G20IsInetBet) {
        this.G20IsInetBet = G20IsInetBet;
    }

    public String getG20() {
        return G20;
    }

    public void setG20(String G20) {
        this.G20 = G20;
    }

    public String getG23IsInetBet() {
        return G23IsInetBet;
    }

    public void setG23IsInetBet(String G23IsInetBet) {
        this.G23IsInetBet = G23IsInetBet;
    }

    public String getG23() {
        return G23;
    }

    public void setG23(String G23) {
        this.G23 = G23;
    }

    public String getG1IsInetBet() {
        return G1IsInetBet;
    }

    public void setG1IsInetBet(String G1IsInetBet) {
        this.G1IsInetBet = G1IsInetBet;
    }

    public String getG1() {
        return G1;
    }

    public void setG1(String G1) {
        this.G1 = G1;
    }

    public String getG12IsInetBet() {
        return G12IsInetBet;
    }

    public void setG12IsInetBet(String G12IsInetBet) {
        this.G12IsInetBet = G12IsInetBet;
    }

    public String getG12() {
        return G12;
    }

    public void setG12(String G12) {
        this.G12 = G12;
    }

    public String getG19IsInetBet() {
        return G19IsInetBet;
    }

    public void setG19IsInetBet(String G19IsInetBet) {
        this.G19IsInetBet = G19IsInetBet;
    }

    public String getG19() {
        return G19;
    }

    public void setG19(String G19) {
        this.G19 = G19;
    }

    public String getG21IsInetBet() {
        return G21IsInetBet;
    }

    public void setG21IsInetBet(String G21IsInetBet) {
        this.G21IsInetBet = G21IsInetBet;
    }

    public String getG21() {
        return G21;
    }

    public void setG21(String G21) {
        this.G21 = G21;
    }

    public String getG24IsInetBet() {
        return G24IsInetBet;
    }

    public void setG24IsInetBet(String G24IsInetBet) {
        this.G24IsInetBet = G24IsInetBet;
    }

    public String getG24() {
        return G24;
    }

    public void setG24(String G24) {
        this.G24 = G24;
    }

    public String getG2IsInetBet() {
        return G2IsInetBet;
    }

    public void setG2IsInetBet(String G2IsInetBet) {
        this.G2IsInetBet = G2IsInetBet;
    }

    public String getG2() {
        return G2;
    }

    public void setG2(String G2) {
        this.G2 = G2;
    }

    public String getG3IsInetBet() {
        return G3IsInetBet;
    }

    public void setG3IsInetBet(String G3IsInetBet) {
        this.G3IsInetBet = G3IsInetBet;
    }

    public String getG3() {
        return G3;
    }

    public void setG3(String G3) {
        this.G3 = G3;
    }

    public String getG13IsInetBet() {
        return G13IsInetBet;
    }

    public void setG13IsInetBet(String G13IsInetBet) {
        this.G13IsInetBet = G13IsInetBet;
    }

    public String getG13() {
        return G13;
    }

    public void setG13(String G13) {
        this.G13 = G13;
    }

    public String getG22IsInetBet() {
        return G22IsInetBet;
    }

    public void setG22IsInetBet(String G22IsInetBet) {
        this.G22IsInetBet = G22IsInetBet;
    }

    public String getG22() {
        return G22;
    }

    public void setG22(String G22) {
        this.G22 = G22;
    }

    public String getG25IsInetBet() {
        return G25IsInetBet;
    }

    public void setG25IsInetBet(String G25IsInetBet) {
        this.G25IsInetBet = G25IsInetBet;
    }

    public String getG25() {
        return G25;
    }

    public void setG25(String G25) {
        this.G25 = G25;
    }

    public String getG4IsInetBet() {
        return G4IsInetBet;
    }

    public void setG4IsInetBet(String G4IsInetBet) {
        this.G4IsInetBet = G4IsInetBet;
    }

    public String getG4() {
        return G4;
    }

    public void setG4(String G4) {
        this.G4 = G4;
    }

    public String getG5IsInetBet() {
        return G5IsInetBet;
    }

    public void setG5IsInetBet(String G5IsInetBet) {
        this.G5IsInetBet = G5IsInetBet;
    }

    public String getG5() {
        return G5;
    }

    public void setG5(String G5) {
        this.G5 = G5;
    }

    public String getG6IsInetBet() {
        return G6IsInetBet;
    }

    public void setG6IsInetBet(String G6IsInetBet) {
        this.G6IsInetBet = G6IsInetBet;
    }

    public String getG6() {
        return G6;
    }

    public void setG6(String G6) {
        this.G6 = G6;
    }

    public String getG14IsInetBet() {
        return G14IsInetBet;
    }

    public void setG14IsInetBet(String G14IsInetBet) {
        this.G14IsInetBet = G14IsInetBet;
    }

    public String getG14() {
        return G14;
    }

    public void setG14(String G14) {
        this.G14 = G14;
    }

    public String getG26IsInetBet() {
        return G26IsInetBet;
    }

    public void setG26IsInetBet(String G26IsInetBet) {
        this.G26IsInetBet = G26IsInetBet;
    }

    public String getG26() {
        return G26;
    }

    public void setG26(String G26) {
        this.G26 = G26;
    }

    public String getG7IsInetBet() {
        return G7IsInetBet;
    }

    public void setG7IsInetBet(String G7IsInetBet) {
        this.G7IsInetBet = G7IsInetBet;
    }

    public String getG7() {
        return G7;
    }

    public void setG7(String G7) {
        this.G7 = G7;
    }

    public String getG8IsInetBet() {
        return G8IsInetBet;
    }

    public void setG8IsInetBet(String G8IsInetBet) {
        this.G8IsInetBet = G8IsInetBet;
    }

    public String getG8() {
        return G8;
    }

    public void setG8(String G8) {
        this.G8 = G8;
    }

    public String getG9IsInetBet() {
        return G9IsInetBet;
    }

    public void setG9IsInetBet(String G9IsInetBet) {
        this.G9IsInetBet = G9IsInetBet;
    }

    public String getG9() {
        return G9;
    }

    public void setG9(String G9) {
        this.G9 = G9;
    }

    public String getG10IsInetBet() {
        return G10IsInetBet;
    }

    public void setG10IsInetBet(String G10IsInetBet) {
        this.G10IsInetBet = G10IsInetBet;
    }

    public String getG10() {
        return G10;
    }

    public void setG10(String G10) {
        this.G10 = G10;
    }

    public String getG15IsInetBet() {
        return G15IsInetBet;
    }

    public void setG15IsInetBet(String G15IsInetBet) {
        this.G15IsInetBet = G15IsInetBet;
    }

    public String getG15() {
        return G15;
    }

    public void setG15(String G15) {
        this.G15 = G15;
    }

    public String getG16IsInetBet() {
        return G16IsInetBet;
    }

    public void setG16IsInetBet(String G16IsInetBet) {
        this.G16IsInetBet = G16IsInetBet;
    }

    public String getG16() {
        return G16;
    }

    public void setG16(String G16) {
        this.G16 = G16;
    }

    public String getHCS_CNT() {
        return HCS_CNT;
    }

    public void setHCS_CNT(String HCS_CNT) {
        this.HCS_CNT = HCS_CNT;
    }

    public String getHCS_SocOddsId() {
        return HCS_SocOddsId;
    }

    public void setHCS_SocOddsId(String HCS_SocOddsId) {
        this.HCS_SocOddsId = HCS_SocOddsId;
    }

    public String getHCS_IsRun() {
        return HCS_IsRun;
    }

    public void setHCS_IsRun(String HCS_IsRun) {
        this.HCS_IsRun = HCS_IsRun;
    }

    public String getHG11IsInetBet() {
        return HG11IsInetBet;
    }

    public void setHG11IsInetBet(String HG11IsInetBet) {
        this.HG11IsInetBet = HG11IsInetBet;
    }

    public String getHG11() {
        return HG11;
    }

    public void setHG11(String HG11) {
        this.HG11 = HG11;
    }

    public String getHG17IsInetBet() {
        return HG17IsInetBet;
    }

    public void setHG17IsInetBet(String HG17IsInetBet) {
        this.HG17IsInetBet = HG17IsInetBet;
    }

    public String getHG17() {
        return HG17;
    }

    public void setHG17(String HG17) {
        this.HG17 = HG17;
    }

    public String getHG18IsInetBet() {
        return HG18IsInetBet;
    }

    public void setHG18IsInetBet(String HG18IsInetBet) {
        this.HG18IsInetBet = HG18IsInetBet;
    }

    public String getHG18() {
        return HG18;
    }

    public void setHG18(String HG18) {
        this.HG18 = HG18;
    }

    public String getHG20IsInetBet() {
        return HG20IsInetBet;
    }

    public void setHG20IsInetBet(String HG20IsInetBet) {
        this.HG20IsInetBet = HG20IsInetBet;
    }

    public String getHG20() {
        return HG20;
    }

    public void setHG20(String HG20) {
        this.HG20 = HG20;
    }

    public String getHG1IsInetBet() {
        return HG1IsInetBet;
    }

    public void setHG1IsInetBet(String HG1IsInetBet) {
        this.HG1IsInetBet = HG1IsInetBet;
    }

    public String getHG1() {
        return HG1;
    }

    public void setHG1(String HG1) {
        this.HG1 = HG1;
    }

    public String getHG12IsInetBet() {
        return HG12IsInetBet;
    }

    public void setHG12IsInetBet(String HG12IsInetBet) {
        this.HG12IsInetBet = HG12IsInetBet;
    }

    public String getHG12() {
        return HG12;
    }

    public void setHG12(String HG12) {
        this.HG12 = HG12;
    }

    public String getHG19IsInetBet() {
        return HG19IsInetBet;
    }

    public void setHG19IsInetBet(String HG19IsInetBet) {
        this.HG19IsInetBet = HG19IsInetBet;
    }

    public String getHG19() {
        return HG19;
    }

    public void setHG19(String HG19) {
        this.HG19 = HG19;
    }

    public String getHG21IsInetBet() {
        return HG21IsInetBet;
    }

    public void setHG21IsInetBet(String HG21IsInetBet) {
        this.HG21IsInetBet = HG21IsInetBet;
    }

    public String getHG21() {
        return HG21;
    }

    public void setHG21(String HG21) {
        this.HG21 = HG21;
    }

    public String getHG2IsInetBet() {
        return HG2IsInetBet;
    }

    public void setHG2IsInetBet(String HG2IsInetBet) {
        this.HG2IsInetBet = HG2IsInetBet;
    }

    public String getHG2() {
        return HG2;
    }

    public void setHG2(String HG2) {
        this.HG2 = HG2;
    }

    public String getHG3IsInetBet() {
        return HG3IsInetBet;
    }

    public void setHG3IsInetBet(String HG3IsInetBet) {
        this.HG3IsInetBet = HG3IsInetBet;
    }

    public String getHG3() {
        return HG3;
    }

    public void setHG3(String HG3) {
        this.HG3 = HG3;
    }

    public String getHG13IsInetBet() {
        return HG13IsInetBet;
    }

    public void setHG13IsInetBet(String HG13IsInetBet) {
        this.HG13IsInetBet = HG13IsInetBet;
    }

    public String getHG13() {
        return HG13;
    }

    public void setHG13(String HG13) {
        this.HG13 = HG13;
    }

    public String getHG22IsInetBet() {
        return HG22IsInetBet;
    }

    public void setHG22IsInetBet(String HG22IsInetBet) {
        this.HG22IsInetBet = HG22IsInetBet;
    }

    public String getHG22() {
        return HG22;
    }

    public void setHG22(String HG22) {
        this.HG22 = HG22;
    }

    public String getHG4IsInetBet() {
        return HG4IsInetBet;
    }

    public void setHG4IsInetBet(String HG4IsInetBet) {
        this.HG4IsInetBet = HG4IsInetBet;
    }

    public String getHG4() {
        return HG4;
    }

    public void setHG4(String HG4) {
        this.HG4 = HG4;
    }

    public String getHG5IsInetBet() {
        return HG5IsInetBet;
    }

    public void setHG5IsInetBet(String HG5IsInetBet) {
        this.HG5IsInetBet = HG5IsInetBet;
    }

    public String getHG5() {
        return HG5;
    }

    public void setHG5(String HG5) {
        this.HG5 = HG5;
    }

    public String getHG6IsInetBet() {
        return HG6IsInetBet;
    }

    public void setHG6IsInetBet(String HG6IsInetBet) {
        this.HG6IsInetBet = HG6IsInetBet;
    }

    public String getHG6() {
        return HG6;
    }

    public void setHG6(String HG6) {
        this.HG6 = HG6;
    }

    public String getHG14IsInetBet() {
        return HG14IsInetBet;
    }

    public void setHG14IsInetBet(String HG14IsInetBet) {
        this.HG14IsInetBet = HG14IsInetBet;
    }

    public String getHG14() {
        return HG14;
    }

    public void setHG14(String HG14) {
        this.HG14 = HG14;
    }

    public String getHG16IsInetBet() {
        return HG16IsInetBet;
    }

    public void setHG16IsInetBet(String HG16IsInetBet) {
        this.HG16IsInetBet = HG16IsInetBet;
    }

    public String getHG16() {
        return HG16;
    }

    public void setHG16(String HG16) {
        this.HG16 = HG16;
    }

    public String getISRUN() {
        return ISRUN;
    }

    public void setISRUN(String ISRUN) {
        this.ISRUN = ISRUN;
    }

    public String getHTFT_CNT() {
        return HTFT_CNT;
    }

    public void setHTFT_CNT(String HTFT_CNT) {
        this.HTFT_CNT = HTFT_CNT;
    }

    public String getHTFT_SocOddsId() {
        return HTFT_SocOddsId;
    }

    public void setHTFT_SocOddsId(String HTFT_SocOddsId) {
        this.HTFT_SocOddsId = HTFT_SocOddsId;
    }

    public String getHTFT_IsRun() {
        return HTFT_IsRun;
    }

    public void setHTFT_IsRun(String HTFT_IsRun) {
        this.HTFT_IsRun = HTFT_IsRun;
    }

    public String getHHIsInetBet() {
        return HHIsInetBet;
    }

    public void setHHIsInetBet(String HHIsInetBet) {
        this.HHIsInetBet = HHIsInetBet;
    }

    public String getHH() {
        return HH;
    }

    public void setHH(String HH) {
        this.HH = HH;
    }

    public String getHDIsInetBet() {
        return HDIsInetBet;
    }

    public void setHDIsInetBet(String HDIsInetBet) {
        this.HDIsInetBet = HDIsInetBet;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public String getHAIsInetBet() {
        return HAIsInetBet;
    }

    public void setHAIsInetBet(String HAIsInetBet) {
        this.HAIsInetBet = HAIsInetBet;
    }

    public String getHA() {
        return HA;
    }

    public void setHA(String HA) {
        this.HA = HA;
    }

    public String getDHIsInetBet() {
        return DHIsInetBet;
    }

    public void setDHIsInetBet(String DHIsInetBet) {
        this.DHIsInetBet = DHIsInetBet;
    }

    public String getDH() {
        return DH;
    }

    public void setDH(String DH) {
        this.DH = DH;
    }

    public String getDDIsInetBet() {
        return DDIsInetBet;
    }

    public void setDDIsInetBet(String DDIsInetBet) {
        this.DDIsInetBet = DDIsInetBet;
    }

    public String getDD() {
        return DD;
    }

    public void setDD(String DD) {
        this.DD = DD;
    }

    public String getDAIsInetBet() {
        return DAIsInetBet;
    }

    public void setDAIsInetBet(String DAIsInetBet) {
        this.DAIsInetBet = DAIsInetBet;
    }

    public String getDA() {
        return DA;
    }

    public void setDA(String DA) {
        this.DA = DA;
    }

    public String getAHIsInetBet() {
        return AHIsInetBet;
    }

    public void setAHIsInetBet(String AHIsInetBet) {
        this.AHIsInetBet = AHIsInetBet;
    }

    public String getAH() {
        return AH;
    }

    public void setAH(String AH) {
        this.AH = AH;
    }

    public String getADIsInetBet() {
        return ADIsInetBet;
    }

    public void setADIsInetBet(String ADIsInetBet) {
        this.ADIsInetBet = ADIsInetBet;
    }

    public String getAD() {
        return AD;
    }

    public void setAD(String AD) {
        this.AD = AD;
    }

    public String getAAIsInetBet() {
        return AAIsInetBet;
    }

    public void setAAIsInetBet(String AAIsInetBet) {
        this.AAIsInetBet = AAIsInetBet;
    }

    public String getAA() {
        return AA;
    }

    public void setAA(String AA) {
        this.AA = AA;
    }

    public String getFGLG_CNT() {
        return FGLG_CNT;
    }

    public void setFGLG_CNT(String FGLG_CNT) {
        this.FGLG_CNT = FGLG_CNT;
    }

    public String getFGLG_SocOddsId() {
        return FGLG_SocOddsId;
    }

    public void setFGLG_SocOddsId(String FGLG_SocOddsId) {
        this.FGLG_SocOddsId = FGLG_SocOddsId;
    }

    public String getFGLG_IsRun() {
        return FGLG_IsRun;
    }

    public void setFGLG_IsRun(String FGLG_IsRun) {
        this.FGLG_IsRun = FGLG_IsRun;
    }

    public String getHFGIsInetBet() {
        return HFGIsInetBet;
    }

    public void setHFGIsInetBet(String HFGIsInetBet) {
        this.HFGIsInetBet = HFGIsInetBet;
    }

    public String getHFG() {
        return HFG;
    }

    public void setHFG(String HFG) {
        this.HFG = HFG;
    }

    public String getHLGIsInetBet() {
        return HLGIsInetBet;
    }

    public void setHLGIsInetBet(String HLGIsInetBet) {
        this.HLGIsInetBet = HLGIsInetBet;
    }

    public String getHLG() {
        return HLG;
    }

    public void setHLG(String HLG) {
        this.HLG = HLG;
    }

    public String getAFGIsInetBet() {
        return AFGIsInetBet;
    }

    public void setAFGIsInetBet(String AFGIsInetBet) {
        this.AFGIsInetBet = AFGIsInetBet;
    }

    public String getAFG() {
        return AFG;
    }

    public void setAFG(String AFG) {
        this.AFG = AFG;
    }

    public String getALGIsInetBet() {
        return ALGIsInetBet;
    }

    public void setALGIsInetBet(String ALGIsInetBet) {
        this.ALGIsInetBet = ALGIsInetBet;
    }

    public String getALG() {
        return ALG;
    }

    public void setALG(String ALG) {
        this.ALG = ALG;
    }

    public String getNGIsInetBet() {
        return NGIsInetBet;
    }

    public void setNGIsInetBet(String NGIsInetBet) {
        this.NGIsInetBet = NGIsInetBet;
    }

    public String getNG() {
        return NG;
    }

    public void setNG(String NG) {
        this.NG = NG;
    }

    public String getTG_CNT() {
        return TG_CNT;
    }

    public void setTG_CNT(String TG_CNT) {
        this.TG_CNT = TG_CNT;
    }

    public String getTG_SocOddsId() {
        return TG_SocOddsId;
    }

    public void setTG_SocOddsId(String TG_SocOddsId) {
        this.TG_SocOddsId = TG_SocOddsId;
    }

    public String getTG_IsRun() {
        return TG_IsRun;
    }

    public void setTG_IsRun(String TG_IsRun) {
        this.TG_IsRun = TG_IsRun;
    }

    public String getGoal1IsInetBet() {
        return Goal1IsInetBet;
    }

    public void setGoal1IsInetBet(String Goal1IsInetBet) {
        this.Goal1IsInetBet = Goal1IsInetBet;
    }

    public String getGoal1() {
        return Goal1;
    }

    public void setGoal1(String Goal1) {
        this.Goal1 = Goal1;
    }

    public String getGoal2IsInetBet() {
        return Goal2IsInetBet;
    }

    public void setGoal2IsInetBet(String Goal2IsInetBet) {
        this.Goal2IsInetBet = Goal2IsInetBet;
    }

    public String getGoal2() {
        return Goal2;
    }

    public void setGoal2(String Goal2) {
        this.Goal2 = Goal2;
    }

    public String getGoal3IsInetBet() {
        return Goal3IsInetBet;
    }

    public void setGoal3IsInetBet(String Goal3IsInetBet) {
        this.Goal3IsInetBet = Goal3IsInetBet;
    }

    public String getGoal3() {
        return Goal3;
    }

    public void setGoal3(String Goal3) {
        this.Goal3 = Goal3;
    }

    public String getGoal4IsInetBet() {
        return Goal4IsInetBet;
    }

    public void setGoal4IsInetBet(String Goal4IsInetBet) {
        this.Goal4IsInetBet = Goal4IsInetBet;
    }

    public String getGoal4() {
        return Goal4;
    }

    public void setGoal4(String Goal4) {
        this.Goal4 = Goal4;
    }

    public String getHTTG_CNT() {
        return HTTG_CNT;
    }

    public void setHTTG_CNT(String HTTG_CNT) {
        this.HTTG_CNT = HTTG_CNT;
    }

    public String getHTTG_SocOddsId() {
        return HTTG_SocOddsId;
    }

    public void setHTTG_SocOddsId(String HTTG_SocOddsId) {
        this.HTTG_SocOddsId = HTTG_SocOddsId;
    }

    public String getHTTG_IsRun() {
        return HTTG_IsRun;
    }

    public void setHTTG_IsRun(String HTTG_IsRun) {
        this.HTTG_IsRun = HTTG_IsRun;
    }

    public String getHTTG_OU() {
        return HTTG_OU;
    }

    public void setHTTG_OU(String HTTG_OU) {
        this.HTTG_OU = HTTG_OU;
    }

    public String getHTTG_IsHomeGive() {
        return HTTG_IsHomeGive;
    }

    public void setHTTG_IsHomeGive(String HTTG_IsHomeGive) {
        this.HTTG_IsHomeGive = HTTG_IsHomeGive;
    }

    public String getHTTG_FHOU() {
        return HTTG_FHOU;
    }

    public void setHTTG_FHOU(String HTTG_FHOU) {
        this.HTTG_FHOU = HTTG_FHOU;
    }

    public String getHTTG_FHIsHomeGive() {
        return HTTG_FHIsHomeGive;
    }

    public void setHTTG_FHIsHomeGive(String HTTG_FHIsHomeGive) {
        this.HTTG_FHIsHomeGive = HTTG_FHIsHomeGive;
    }

    public String getHTHDP1() {
        return HTHDP1;
    }

    public void setHTHDP1(String HTHDP1) {
        this.HTHDP1 = HTHDP1;
    }

    public String getHTHDP2() {
        return HTHDP2;
    }

    public void setHTHDP2(String HTHDP2) {
        this.HTHDP2 = HTHDP2;
    }

    public String getFHOOddsIsInetBet() {
        return FHOOddsIsInetBet;
    }

    public void setFHOOddsIsInetBet(String FHOOddsIsInetBet) {
        this.FHOOddsIsInetBet = FHOOddsIsInetBet;
    }

    public String getFHOOdds() {
        return FHOOdds;
    }

    public void setFHOOdds(String FHOOdds) {
        this.FHOOdds = FHOOdds;
    }

    public String getHHOOddsIsInetBet() {
        return HHOOddsIsInetBet;
    }

    public void setHHOOddsIsInetBet(String HHOOddsIsInetBet) {
        this.HHOOddsIsInetBet = HHOOddsIsInetBet;
    }

    public String getHHOOdds() {
        return HHOOdds;
    }

    public void setHHOOdds(String HHOOdds) {
        this.HHOOdds = HHOOdds;
    }

    public String getFHUOddsIsInetBet() {
        return FHUOddsIsInetBet;
    }

    public void setFHUOddsIsInetBet(String FHUOddsIsInetBet) {
        this.FHUOddsIsInetBet = FHUOddsIsInetBet;
    }

    public String getFHUOdds() {
        return FHUOdds;
    }

    public void setFHUOdds(String FHUOdds) {
        this.FHUOdds = FHUOdds;
    }

    public String getHHUOddsIsInetBet() {
        return HHUOddsIsInetBet;
    }

    public void setHHUOddsIsInetBet(String HHUOddsIsInetBet) {
        this.HHUOddsIsInetBet = HHUOddsIsInetBet;
    }

    public String getHHUOdds() {
        return HHUOdds;
    }

    public void setHHUOdds(String HHUOdds) {
        this.HHUOdds = HHUOdds;
    }

    public String getATTG_CNT() {
        return ATTG_CNT;
    }

    public void setATTG_CNT(String ATTG_CNT) {
        this.ATTG_CNT = ATTG_CNT;
    }

    public String getATTG_SocOddsId() {
        return ATTG_SocOddsId;
    }

    public void setATTG_SocOddsId(String ATTG_SocOddsId) {
        this.ATTG_SocOddsId = ATTG_SocOddsId;
    }

    public String getATTG_IsRun() {
        return ATTG_IsRun;
    }

    public void setATTG_IsRun(String ATTG_IsRun) {
        this.ATTG_IsRun = ATTG_IsRun;
    }

    public String getATTG_OU() {
        return ATTG_OU;
    }

    public void setATTG_OU(String ATTG_OU) {
        this.ATTG_OU = ATTG_OU;
    }

    public String getATTG_IsHomeGive() {
        return ATTG_IsHomeGive;
    }

    public void setATTG_IsHomeGive(String ATTG_IsHomeGive) {
        this.ATTG_IsHomeGive = ATTG_IsHomeGive;
    }

    public String getATTG_FHOU() {
        return ATTG_FHOU;
    }

    public void setATTG_FHOU(String ATTG_FHOU) {
        this.ATTG_FHOU = ATTG_FHOU;
    }

    public String getATTG_FHIsHomeGive() {
        return ATTG_FHIsHomeGive;
    }

    public void setATTG_FHIsHomeGive(String ATTG_FHIsHomeGive) {
        this.ATTG_FHIsHomeGive = ATTG_FHIsHomeGive;
    }

    public String getATHDP1() {
        return ATHDP1;
    }

    public void setATHDP1(String ATHDP1) {
        this.ATHDP1 = ATHDP1;
    }

    public String getATHDP2() {
        return ATHDP2;
    }

    public void setATHDP2(String ATHDP2) {
        this.ATHDP2 = ATHDP2;
    }

    public String getFAOOddsIsInetBet() {
        return FAOOddsIsInetBet;
    }

    public void setFAOOddsIsInetBet(String FAOOddsIsInetBet) {
        this.FAOOddsIsInetBet = FAOOddsIsInetBet;
    }

    public String getFAOOdds() {
        return FAOOdds;
    }

    public void setFAOOdds(String FAOOdds) {
        this.FAOOdds = FAOOdds;
    }

    public String getHAOOddsIsInetBet() {
        return HAOOddsIsInetBet;
    }

    public void setHAOOddsIsInetBet(String HAOOddsIsInetBet) {
        this.HAOOddsIsInetBet = HAOOddsIsInetBet;
    }

    public String getHAOOdds() {
        return HAOOdds;
    }

    public void setHAOOdds(String HAOOdds) {
        this.HAOOdds = HAOOdds;
    }

    public String getFAUOddsIsInetBet() {
        return FAUOddsIsInetBet;
    }

    public void setFAUOddsIsInetBet(String FAUOddsIsInetBet) {
        this.FAUOddsIsInetBet = FAUOddsIsInetBet;
    }

    public String getFAUOdds() {
        return FAUOdds;
    }

    public void setFAUOdds(String FAUOdds) {
        this.FAUOdds = FAUOdds;
    }

    public String getHAUOddsIsInetBet() {
        return HAUOddsIsInetBet;
    }

    public void setHAUOddsIsInetBet(String HAUOddsIsInetBet) {
        this.HAUOddsIsInetBet = HAUOddsIsInetBet;
    }

    public String getHAUOdds() {
        return HAUOdds;
    }

    public void setHAUOdds(String HAUOdds) {
        this.HAUOdds = HAUOdds;
    }

    public String getMG15_CNT() {
        return MG15_CNT;
    }

    public void setMG15_CNT(String MG15_CNT) {
        this.MG15_CNT = MG15_CNT;
    }

    public String getMG15_SocOddsId() {
        return MG15_SocOddsId;
    }

    public void setMG15_SocOddsId(String MG15_SocOddsId) {
        this.MG15_SocOddsId = MG15_SocOddsId;
    }

    public String getMG15_IsRun() {
        return MG15_IsRun;
    }

    public void setMG15_IsRun(String MG15_IsRun) {
        this.MG15_IsRun = MG15_IsRun;
    }

    public String getMG15_OU() {
        return MG15_OU;
    }

    public void setMG15_OU(String MG15_OU) {
        this.MG15_OU = MG15_OU;
    }

    public String getMG15_IsHomeGive() {
        return MG15_IsHomeGive;
    }

    public void setMG15_IsHomeGive(String MG15_IsHomeGive) {
        this.MG15_IsHomeGive = MG15_IsHomeGive;
    }

    public String getFT15InfoOU() {
        return FT15InfoOU;
    }

    public void setFT15InfoOU(String FT15InfoOU) {
        this.FT15InfoOU = FT15InfoOU;
    }

    public String getFT15InfoOUIsInetBet() {
        return FT15InfoOUIsInetBet;
    }

    public void setFT15InfoOUIsInetBet(String FT15InfoOUIsInetBet) {
        this.FT15InfoOUIsInetBet = FT15InfoOUIsInetBet;
    }

    public String getFT15InfoO() {
        return FT15InfoO;
    }

    public void setFT15InfoO(String FT15InfoO) {
        this.FT15InfoO = FT15InfoO;
    }

    public String getFT15InfoUIsInetBet() {
        return FT15InfoUIsInetBet;
    }

    public void setFT15InfoUIsInetBet(String FT15InfoUIsInetBet) {
        this.FT15InfoUIsInetBet = FT15InfoUIsInetBet;
    }

    public String getFT15InfoU() {
        return FT15InfoU;
    }

    public void setFT15InfoU(String FT15InfoU) {
        this.FT15InfoU = FT15InfoU;
    }

    public String getMG30_CNT() {
        return MG30_CNT;
    }

    public void setMG30_CNT(String MG30_CNT) {
        this.MG30_CNT = MG30_CNT;
    }

    public String getMG30_SocOddsId() {
        return MG30_SocOddsId;
    }

    public void setMG30_SocOddsId(String MG30_SocOddsId) {
        this.MG30_SocOddsId = MG30_SocOddsId;
    }

    public String getMG30_IsRun() {
        return MG30_IsRun;
    }

    public void setMG30_IsRun(String MG30_IsRun) {
        this.MG30_IsRun = MG30_IsRun;
    }

    public String getMG30_OU() {
        return MG30_OU;
    }

    public void setMG30_OU(String MG30_OU) {
        this.MG30_OU = MG30_OU;
    }

    public String getMG30_IsHomeGive() {
        return MG30_IsHomeGive;
    }

    public void setMG30_IsHomeGive(String MG30_IsHomeGive) {
        this.MG30_IsHomeGive = MG30_IsHomeGive;
    }

    public String getFT30InfoOU() {
        return FT30InfoOU;
    }

    public void setFT30InfoOU(String FT30InfoOU) {
        this.FT30InfoOU = FT30InfoOU;
    }

    public String getFT30InfoOIsInetBet() {
        return FT30InfoOIsInetBet;
    }

    public void setFT30InfoOIsInetBet(String FT30InfoOIsInetBet) {
        this.FT30InfoOIsInetBet = FT30InfoOIsInetBet;
    }

    public String getFT30InfoO() {
        return FT30InfoO;
    }

    public void setFT30InfoO(String FT30InfoO) {
        this.FT30InfoO = FT30InfoO;
    }

    public String getFT30InfoUIsInetBet() {
        return FT30InfoUIsInetBet;
    }

    public void setFT30InfoUIsInetBet(String FT30InfoUIsInetBet) {
        this.FT30InfoUIsInetBet = FT30InfoUIsInetBet;
    }

    public String getFT30InfoU() {
        return FT30InfoU;
    }

    public void setFT30InfoU(String FT30InfoU) {
        this.FT30InfoU = FT30InfoU;
    }

    public String getMG45_CNT() {
        return MG45_CNT;
    }

    public void setMG45_CNT(String MG45_CNT) {
        this.MG45_CNT = MG45_CNT;
    }

    public String getMG60_CNT() {
        return MG60_CNT;
    }

    public void setMG60_CNT(String MG60_CNT) {
        this.MG60_CNT = MG60_CNT;
    }

    public String getMG60_SocOddsId() {
        return MG60_SocOddsId;
    }

    public void setMG60_SocOddsId(String MG60_SocOddsId) {
        this.MG60_SocOddsId = MG60_SocOddsId;
    }

    public String getMG60_IsRun() {
        return MG60_IsRun;
    }

    public void setMG60_IsRun(String MG60_IsRun) {
        this.MG60_IsRun = MG60_IsRun;
    }

    public String getMG60_OU() {
        return MG60_OU;
    }

    public void setMG60_OU(String MG60_OU) {
        this.MG60_OU = MG60_OU;
    }

    public String getMG60_IsHomeGive() {
        return MG60_IsHomeGive;
    }

    public void setMG60_IsHomeGive(String MG60_IsHomeGive) {
        this.MG60_IsHomeGive = MG60_IsHomeGive;
    }

    public String getFT60InfoOU() {
        return FT60InfoOU;
    }

    public void setFT60InfoOU(String FT60InfoOU) {
        this.FT60InfoOU = FT60InfoOU;
    }

    public String getFT60InfoOIsInetBet() {
        return FT60InfoOIsInetBet;
    }

    public void setFT60InfoOIsInetBet(String FT60InfoOIsInetBet) {
        this.FT60InfoOIsInetBet = FT60InfoOIsInetBet;
    }

    public String getFT60InfoO() {
        return FT60InfoO;
    }

    public void setFT60InfoO(String FT60InfoO) {
        this.FT60InfoO = FT60InfoO;
    }

    public String getFT60InfoUIsInetBet() {
        return FT60InfoUIsInetBet;
    }

    public void setFT60InfoUIsInetBet(String FT60InfoUIsInetBet) {
        this.FT60InfoUIsInetBet = FT60InfoUIsInetBet;
    }

    public String getFT60InfoU() {
        return FT60InfoU;
    }

    public void setFT60InfoU(String FT60InfoU) {
        this.FT60InfoU = FT60InfoU;
    }

    public String getMG75_CNT() {
        return MG75_CNT;
    }

    public void setMG75_CNT(String MG75_CNT) {
        this.MG75_CNT = MG75_CNT;
    }

    public String getMG75_SocOddsId() {
        return MG75_SocOddsId;
    }

    public void setMG75_SocOddsId(String MG75_SocOddsId) {
        this.MG75_SocOddsId = MG75_SocOddsId;
    }

    public String getMG75_IsRun() {
        return MG75_IsRun;
    }

    public void setMG75_IsRun(String MG75_IsRun) {
        this.MG75_IsRun = MG75_IsRun;
    }

    public String getMG75_OU() {
        return MG75_OU;
    }

    public void setMG75_OU(String MG75_OU) {
        this.MG75_OU = MG75_OU;
    }

    public String getMG75_IsHomeGive() {
        return MG75_IsHomeGive;
    }

    public void setMG75_IsHomeGive(String MG75_IsHomeGive) {
        this.MG75_IsHomeGive = MG75_IsHomeGive;
    }

    public String getFT75InfoOU() {
        return FT75InfoOU;
    }

    public void setFT75InfoOU(String FT75InfoOU) {
        this.FT75InfoOU = FT75InfoOU;
    }

    public String getFT75InfoOIsInetBet() {
        return FT75InfoOIsInetBet;
    }

    public void setFT75InfoOIsInetBet(String FT75InfoOIsInetBet) {
        this.FT75InfoOIsInetBet = FT75InfoOIsInetBet;
    }

    public String getFT75InfoO() {
        return FT75InfoO;
    }

    public void setFT75InfoO(String FT75InfoO) {
        this.FT75InfoO = FT75InfoO;
    }

    public String getFT75InfoUIsInetBet() {
        return FT75InfoUIsInetBet;
    }

    public void setFT75InfoUIsInetBet(String FT75InfoUIsInetBet) {
        this.FT75InfoUIsInetBet = FT75InfoUIsInetBet;
    }

    public String getFT75InfoU() {
        return FT75InfoU;
    }

    public void setFT75InfoU(String FT75InfoU) {
        this.FT75InfoU = FT75InfoU;
    }

    public String get_count() {
        return _count;
    }

    public void set_count(String _count) {
        this._count = _count;
    }

    public List<FToddsBean> getFTodds() {
        return FTodds;
    }

    public void setFTodds(List<FToddsBean> FTodds) {
        this.FTodds = FTodds;
    }

    public List<FHoddsBean> getFHodds() {
        return FHodds;
    }

    public void setFHodds(List<FHoddsBean> FHodds) {
        this.FHodds = FHodds;
    }

    public static class FToddsBean {
        private String SocOddsId;
        private String Home;
        private String Away;
        private String HDP;
        private String Hdp_visible;
        private String HomeOdds;
        private String AwayOdds;
        private String Over;
        private String Under;
        private String OU;
        private String OU_visible;
        private String OverOdds;
        private String UnderOdds;
        private String IsHomeGive;
        private String IsRun;
        private String HasPar;

        public String getSocOddsId() {
            return SocOddsId;
        }

        public void setSocOddsId(String SocOddsId) {
            this.SocOddsId = SocOddsId;
        }

        public String getHome() {
            return Home;
        }

        public void setHome(String Home) {
            this.Home = Home;
        }

        public String getAway() {
            return Away;
        }

        public void setAway(String Away) {
            this.Away = Away;
        }

        public String getHDP() {
            return HDP;
        }

        public void setHDP(String HDP) {
            this.HDP = HDP;
        }

        public String getHdp_visible() {
            return Hdp_visible;
        }

        public void setHdp_visible(String Hdp_visible) {
            this.Hdp_visible = Hdp_visible;
        }

        public String getHomeOdds() {
            return HomeOdds;
        }

        public void setHomeOdds(String HomeOdds) {
            this.HomeOdds = HomeOdds;
        }

        public String getAwayOdds() {
            return AwayOdds;
        }

        public void setAwayOdds(String AwayOdds) {
            this.AwayOdds = AwayOdds;
        }

        public String getOver() {
            return Over;
        }

        public void setOver(String Over) {
            this.Over = Over;
        }

        public String getUnder() {
            return Under;
        }

        public void setUnder(String Under) {
            this.Under = Under;
        }

        public String getOU() {
            return OU;
        }

        public void setOU(String OU) {
            this.OU = OU;
        }

        public String getOU_visible() {
            return OU_visible;
        }

        public void setOU_visible(String OU_visible) {
            this.OU_visible = OU_visible;
        }

        public String getOverOdds() {
            return OverOdds;
        }

        public void setOverOdds(String OverOdds) {
            this.OverOdds = OverOdds;
        }

        public String getUnderOdds() {
            return UnderOdds;
        }

        public void setUnderOdds(String UnderOdds) {
            this.UnderOdds = UnderOdds;
        }

        public String getIsHomeGive() {
            return IsHomeGive;
        }

        public void setIsHomeGive(String IsHomeGive) {
            this.IsHomeGive = IsHomeGive;
        }

        public String getIsRun() {
            return IsRun;
        }

        public void setIsRun(String IsRun) {
            this.IsRun = IsRun;
        }

        public String getHasPar() {
            return HasPar;
        }

        public void setHasPar(String HasPar) {
            this.HasPar = HasPar;
        }
    }

    public static class FHoddsBean {
        private String SocOddsId;
        private String Home;
        private String Away;
        private String HDP;
        private String Hdp_visible;
        private String HomeOdds;
        private String AwayOdds;
        private String Over;
        private String Under;
        private String OU;
        private String OU_visible;
        private String OverOdds;
        private String UnderOdds;
        private String IsHomeGive;
        private String IsRun;
        private String HasPar;

        public String getSocOddsId() {
            return SocOddsId;
        }

        public void setSocOddsId(String SocOddsId) {
            this.SocOddsId = SocOddsId;
        }

        public String getHome() {
            return Home;
        }

        public void setHome(String Home) {
            this.Home = Home;
        }

        public String getAway() {
            return Away;
        }

        public void setAway(String Away) {
            this.Away = Away;
        }

        public String getHDP() {
            return HDP;
        }

        public void setHDP(String HDP) {
            this.HDP = HDP;
        }

        public String getHdp_visible() {
            return Hdp_visible;
        }

        public void setHdp_visible(String Hdp_visible) {
            this.Hdp_visible = Hdp_visible;
        }

        public String getHomeOdds() {
            return HomeOdds;
        }

        public void setHomeOdds(String HomeOdds) {
            this.HomeOdds = HomeOdds;
        }

        public String getAwayOdds() {
            return AwayOdds;
        }

        public void setAwayOdds(String AwayOdds) {
            this.AwayOdds = AwayOdds;
        }

        public String getOver() {
            return Over;
        }

        public void setOver(String Over) {
            this.Over = Over;
        }

        public String getUnder() {
            return Under;
        }

        public void setUnder(String Under) {
            this.Under = Under;
        }

        public String getOU() {
            return OU;
        }

        public void setOU(String OU) {
            this.OU = OU;
        }

        public String getOU_visible() {
            return OU_visible;
        }

        public void setOU_visible(String OU_visible) {
            this.OU_visible = OU_visible;
        }

        public String getOverOdds() {
            return OverOdds;
        }

        public void setOverOdds(String OverOdds) {
            this.OverOdds = OverOdds;
        }

        public String getUnderOdds() {
            return UnderOdds;
        }

        public void setUnderOdds(String UnderOdds) {
            this.UnderOdds = UnderOdds;
        }

        public String getIsHomeGive() {
            return IsHomeGive;
        }

        public void setIsHomeGive(String IsHomeGive) {
            this.IsHomeGive = IsHomeGive;
        }

        public String getIsRun() {
            return IsRun;
        }

        public void setIsRun(String IsRun) {
            this.IsRun = IsRun;
        }

        public String getHasPar() {
            return HasPar;
        }

        public void setHasPar(String HasPar) {
            this.HasPar = HasPar;
        }
    }
}
