package com.nanyang.app.main.center.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */

public class StakeListBean implements Serializable{


    private List<DicAllBean> dicAll;

    public List<DicAllBean> getDicAll() {
        return dicAll;
    }

    public void setDicAll(List<DicAllBean> dicAll) {
        this.dicAll = dicAll;
    }

    public static class DicAllBean implements Serializable,Cloneable {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        /**
         * SocTransId : 137090827
         * TransType : 1DT
         * RefNo : 1DT1370908272
         * TransDate : 22/03 5:28:23 pm
         * GameType3 : N
         * ModuleTitle : HUAY THAI Top Prize 1D
         * Home : HUAY THAI Top Prize 1D
         * Away : HUAY THAI Top Prize 1D
         * FullTimeId : 0
         * IsRun : false
         * RunHomeScore : 0
         * RunAwayScore : 0
         * WorkingDate : 01/04 12:00:00 am
         * IsHomeGive : false
         * IsBetHome : true
         * BetType : HUAY THAI Top Prize 1D
         * DangerStatus : N
         * ARTime : 22/03 5:28:23 pm
         * BetUserId : 0
         * Pct : -1
         * CombInfo :
         * Res2 : 0
         * OddsType :
         * Hdp : 1
         * Odds : 0.10
         * KenoType :
         * DisplayHdp : +1
         * DisplayOdds : 0.10
         * DisplayOdds2 : 1.00
         * DisplayOdds3 : 1.0
         * CSScore : 0-1
         * DisplayD2 : 01
         * DisplayD3 : 001
         * DisplayD4 : 0001
         * CSStatus : 2
         * CSRScore :
         * TGScore : 0-1
         * HTFTScore : DH
         * FGLGScore : HUAY THAI Top Prize 1D (最后得分)
         * FS1Score :
         * DCScore :
         * MMPct : -1
         * Amt : 32.00
         * R_DateTime : [22/03 5:28:23 pm]
         */
/*[26968, 'Waiting', 'HDP', 'Handicap', 'Home', 'HDP000269686', '24/08 2:17:53 pm', 'test', 'TEST [A]', 'TEST [B]', 1, 1, '-1.25', '1.19', 'HK', '', '20', 'D', '0-0', 'N', '', 'True'],*/
        private int SocTransId;
        private String TransType;
        private String RefNo;
        private String TransDate;
        private String GameType3;
        private String ModuleTitle;
        private String Home;
        private String Away;
        private int FullTimeId;
        private int IsRun;
        private int RunHomeScore;
        private int RunAwayScore;
        private String WorkingDate;
        private boolean IsHomeGive;
        private boolean IsBetHome;
        private String BetType;
        private String DangerStatus;
        private String ARTime;
        private int BetUserId;
        private int Pct;
        private String CombInfo;
        private String Res2;
        private String OddsType;
        private String Hdp;
        private String Odds;
        private String KenoType;
        private String DisplayHdp;
        private String DisplayOdds;
        private String DisplayOdds2;
        private String DisplayOdds3;
        private String CSScore;
        private String DisplayD2;
        private String DisplayD3;
        private String DisplayD4;
        private int CSStatus;
        private String CSRScore;
        private String TGScore;
        private String HTFTScore;
        private String FGLGScore;
        private String FS1Score;
        private String DCScore;
        private String MMPct;
        private String Amt;
        private String R_DateTime;

        public int getSocTransId() {
            return SocTransId;
        }

        public void setSocTransId(int SocTransId) {
            this.SocTransId = SocTransId;
        }

        public String getTransType() {
            return TransType;
        }

        public void setTransType(String TransType) {
            this.TransType = TransType;
        }

        public String getRefNo() {
            return RefNo;
        }

        public void setRefNo(String RefNo) {
            this.RefNo = RefNo;
        }

        public String getTransDate() {
            return TransDate;
        }

        public void setTransDate(String TransDate) {
            this.TransDate = TransDate;
        }

        public String getGameType3() {
            return GameType3;
        }

        public void setGameType3(String GameType3) {
            this.GameType3 = GameType3;
        }

        public String getModuleTitle() {
            return ModuleTitle;
        }

        public void setModuleTitle(String ModuleTitle) {
            this.ModuleTitle = ModuleTitle;
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

        public int getFullTimeId() {
            return FullTimeId;
        }

        public void setFullTimeId(int FullTimeId) {
            this.FullTimeId = FullTimeId;
        }

        public int isIsRun() {
            return IsRun;
        }

        public void setIsRun(int IsRun) {
            this.IsRun = IsRun;
        }

        public int getRunHomeScore() {
            return RunHomeScore;
        }

        public void setRunHomeScore(int RunHomeScore) {
            this.RunHomeScore = RunHomeScore;
        }

        public int getRunAwayScore() {
            return RunAwayScore;
        }

        public void setRunAwayScore(int RunAwayScore) {
            this.RunAwayScore = RunAwayScore;
        }

        public String getWorkingDate() {
            return WorkingDate;
        }

        public void setWorkingDate(String WorkingDate) {
            this.WorkingDate = WorkingDate;
        }

        public boolean isIsHomeGive() {
            return IsHomeGive;
        }

        public void setIsHomeGive(boolean IsHomeGive) {
            this.IsHomeGive = IsHomeGive;
        }

        public boolean isIsBetHome() {
            return IsBetHome;
        }

        public void setIsBetHome(boolean IsBetHome) {
            this.IsBetHome = IsBetHome;
        }

        public String getBetType() {
            return BetType;
        }

        public void setBetType(String BetType) {
            this.BetType = BetType;
        }

        public String getDangerStatus() {
            return DangerStatus;
        }

        public void setDangerStatus(String DangerStatus) {
            this.DangerStatus = DangerStatus;
        }

        public String getARTime() {
            return ARTime;
        }

        public void setARTime(String ARTime) {
            this.ARTime = ARTime;
        }

        public int getBetUserId() {
            return BetUserId;
        }

        public void setBetUserId(int BetUserId) {
            this.BetUserId = BetUserId;
        }

        public int getPct() {
            return Pct;
        }

        public void setPct(int Pct) {
            this.Pct = Pct;
        }

        public String getCombInfo() {
            return CombInfo;
        }

        public void setCombInfo(String CombInfo) {
            this.CombInfo = CombInfo;
        }

        public String getRes2() {
            return Res2;
        }

        public void setRes2(String Res2) {
            this.Res2 = Res2;
        }

        public String getOddsType() {
            return OddsType;
        }

        public void setOddsType(String OddsType) {
            this.OddsType = OddsType;
        }

        public String getHdp() {
            return Hdp;
        }

        public void setHdp(String Hdp) {
            this.Hdp = Hdp;
        }

        public String getOdds() {
            return Odds;
        }

        public void setOdds(String Odds) {
            this.Odds = Odds;
        }

        public String getKenoType() {
            return KenoType;
        }

        public void setKenoType(String KenoType) {
            this.KenoType = KenoType;
        }

        public String getDisplayHdp() {
            return DisplayHdp;
        }

        public void setDisplayHdp(String DisplayHdp) {
            this.DisplayHdp = DisplayHdp;
        }

        public String getDisplayOdds() {
            return DisplayOdds;
        }

        public void setDisplayOdds(String DisplayOdds) {
            this.DisplayOdds = DisplayOdds;
        }

        public String getDisplayOdds2() {
            return DisplayOdds2;
        }

        public void setDisplayOdds2(String DisplayOdds2) {
            this.DisplayOdds2 = DisplayOdds2;
        }

        public String getDisplayOdds3() {
            return DisplayOdds3;
        }

        public void setDisplayOdds3(String DisplayOdds3) {
            this.DisplayOdds3 = DisplayOdds3;
        }

        public String getCSScore() {
            return CSScore;
        }

        public void setCSScore(String CSScore) {
            this.CSScore = CSScore;
        }

        public String getDisplayD2() {
            return DisplayD2;
        }

        public void setDisplayD2(String DisplayD2) {
            this.DisplayD2 = DisplayD2;
        }

        public String getDisplayD3() {
            return DisplayD3;
        }

        public void setDisplayD3(String DisplayD3) {
            this.DisplayD3 = DisplayD3;
        }

        public String getDisplayD4() {
            return DisplayD4;
        }

        public void setDisplayD4(String DisplayD4) {
            this.DisplayD4 = DisplayD4;
        }

        public int getCSStatus() {
            return CSStatus;
        }

        public void setCSStatus(int CSStatus) {
            this.CSStatus = CSStatus;
        }

        public String getCSRScore() {
            return CSRScore;
        }

        public void setCSRScore(String CSRScore) {
            this.CSRScore = CSRScore;
        }

        public String getTGScore() {
            return TGScore;
        }

        public void setTGScore(String TGScore) {
            this.TGScore = TGScore;
        }

        public String getHTFTScore() {
            return HTFTScore;
        }

        public void setHTFTScore(String HTFTScore) {
            this.HTFTScore = HTFTScore;
        }

        public String getFGLGScore() {
            return FGLGScore;
        }

        public void setFGLGScore(String FGLGScore) {
            this.FGLGScore = FGLGScore;
        }

        public String getFS1Score() {
            return FS1Score;
        }

        public void setFS1Score(String FS1Score) {
            this.FS1Score = FS1Score;
        }

        public String getDCScore() {
            return DCScore;
        }

        public void setDCScore(String DCScore) {
            this.DCScore = DCScore;
        }

        public String getMMPct() {
            return MMPct;
        }

        public void setMMPct(String MMPct) {
            this.MMPct = MMPct;
        }

        public String getAmt() {
            float   i=Float.valueOf(Amt);
            int a= (int) i;
            return a+"";
        }

        public void setAmt(String Amt) {
            this.Amt = Amt;
        }

        public String getR_DateTime() {
            return R_DateTime;
        }

        public void setR_DateTime(String R_DateTime) {
            this.R_DateTime = R_DateTime;
        }
    }



}
