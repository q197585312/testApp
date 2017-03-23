package com.nanyang.app.main.center.model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */

public class StakeListBean {

    private List<DicAllBean> dicAll;

    public List<DicAllBean> getDicAll() {
        return dicAll;
    }

    public void setDicAll(List<DicAllBean> dicAll) {
        this.dicAll = dicAll;
    }

    public static class DicAllBean {
        /**
         * TransType : HDP
         * RefNo : HDP1371231446
         * TransDate : 23/03 9:28:45 am
         * Home : Kristiansund
         * Away : Molde
         * IsHomeGive : false
         * IsBetHome : true
         * BetType : Kristiansund
         * Hdp : 0-0.5
         * RunHomeScore : 0
         * RunAwayScore : 0
         * Odds : 1.13
         * ModuleTitle : NORWAY ELITESERIEN
         * GameType : 亚州盤
         * FullTimeId : 0
         * DangerStatus : N
         * Amt : 11
         * R_DateTime : [23/03 9:28:45 am]
         */

        private String TransType;
        private String RefNo;
        private String TransDate;
        private String Home;
        private String Away;
        private boolean IsHomeGive;
        private boolean IsBetHome;
        private String BetType;
        private String Hdp;
        private int RunHomeScore;
        private int RunAwayScore;
        private String Odds;
        private String ModuleTitle;
        private String GameType;
        private int FullTimeId;
        private String DangerStatus;
        private String Amt;
        private String R_DateTime;

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

        public String getHdp() {
            return Hdp;
        }

        public void setHdp(String Hdp) {
            this.Hdp = Hdp;
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

        public String getOdds() {
            return Odds;
        }

        public void setOdds(String Odds) {
            this.Odds = Odds;
        }

        public String getModuleTitle() {
            return ModuleTitle;
        }

        public void setModuleTitle(String ModuleTitle) {
            this.ModuleTitle = ModuleTitle;
        }

        public String getGameType() {
            return GameType;
        }

        public void setGameType(String GameType) {
            this.GameType = GameType;
        }

        public int getFullTimeId() {
            return FullTimeId;
        }

        public void setFullTimeId(int FullTimeId) {
            this.FullTimeId = FullTimeId;
        }

        public String getDangerStatus() {
            return DangerStatus;
        }

        public void setDangerStatus(String DangerStatus) {
            this.DangerStatus = DangerStatus;
        }

        public String getAmt() {
            return Amt;
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
