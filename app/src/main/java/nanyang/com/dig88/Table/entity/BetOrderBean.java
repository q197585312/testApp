package nanyang.com.dig88.Table.entity;

import android.text.Html;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/12/4.
 */
public class BetOrderBean implements Serializable {

    /**
     * TransType : HDP
     * RefNo : HDP1129678890
     * TransDate : 03/12 11:52:07 pm
     * Home : Sassuolo
     * Away : Cagliari
     * IsHomeGive : true
     * IsBetHome : true
     * BetType : Sassuolo
     * Hdp : 0.5-1
     * RunHomeScore : 0
     * RunAwayScore : 0
     * Odds : <span class='Negative'>-0.87</span>
     * ModuleTitle : ITALY CUP
     * GameType : Handicap
     * FullTimeId : 0
     * DangerStatus : N
     * Amt : 1
     * R_DateTime : [03/12 11:52:07 pm]
     */

    private List<DicAllEntity> dicAll;

    public List<DicAllEntity> getDicAll() {
        return dicAll;
    }

    public void setDicAll(List<DicAllEntity> dicAll) {
        this.dicAll = dicAll;
    }

    public static class DicAllEntity implements Serializable {
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
        private String  Par_sign;
        private String Par_Url;//": "_norm/ParTrans_App.aspx?userName=t@dc2stest006&id=114012337",

        public String getPar_Url() {
            return Par_Url;
        }

        public void setPar_Url(String par_Url) {
            Par_Url = par_Url;
        }

        public boolean isBetHome() {
            return IsBetHome;
        }

        public void setBetHome(boolean betHome) {
            IsBetHome = betHome;
        }

        public boolean isHomeGive() {
            return IsHomeGive;
        }

        public void setHomeGive(boolean homeGive) {
            IsHomeGive = homeGive;
        }

        public String getPar_sign() {
            return Par_sign;
        }

        public void setPar_sign(String par_sign) {
            Par_sign = par_sign;
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
            return Html.fromHtml(Odds).toString();
        }

        public void setOdds(String Odds) {
            this.Odds =Odds;
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
