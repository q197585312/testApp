package nanyang.com.dig88.Table.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
public class BettingParPromptBean {

    /**
     * BetType : 1_par
     * GTitle : PARLAY
     * ParIsHomeGive : false
     * ParHome : Newcastle Jets
     * ParAway : Adelaide United
     * ParIsRun : false
     * ParGameType3 : N
     * MaxParTicket : 16233
     * MaxPar : -10020844
     * BetPar : [{"SocOddsId":9220030,"Home":"Schalke 04","Away":"TSG Hoffenheim","IsHomeGive":true,"IsBetHome":true,"ParFullTimeId":"0","TransType":"1","IsOddsChange":false,"ParUrl":"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?&g=2&b=1_par&oId=9220027&odds=0.24","BetHdp":"0","BetOu":"0","BetOdds":"1.62","Test2":"testing2"},{"SocOddsId":9220027,"Home":"Newcastle Jets","Away":"Adelaide United","IsHomeGive":true,"IsBetHome":true,"ParFullTimeId":"0","TransType":"1","IsOddsChange":false,"ParUrl":"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?&g=2&b=1_par&oId=9220027&odds=0.24","BetHdp":"0","BetOu":"0","BetOdds":"-0.02","Test2":"testing2"}]
     * PayoutOdds : -0.032
     * BetUrl : PanelBet.aspx?betType=1_par&odds=-0.0323999992847443
     * MinLimit : -10020844
     * MaxLimit : -10020844
     * HidOdds : -1.032
     * AmtS : 0
     * ExRate : 0.308
     * BetterOdds : 1
     * MoreBetUrl :
     * Test : testing
     */

    @SerializedName("BetType")
    private String BetType;
    @SerializedName("GTitle")
    private String GTitle;
    @SerializedName("ParIsHomeGive")
    private boolean ParIsHomeGive;
    @SerializedName("ParHome")
    private String ParHome;
    @SerializedName("ParAway")
    private String ParAway;
    @SerializedName("ParIsRun")
    private boolean ParIsRun;
    @SerializedName("ParGameType3")
    private String ParGameType3;
    @SerializedName("MaxParTicket")
    private String MaxParTicket;
    @SerializedName("MaxPar")
    private String MaxPar;
    @SerializedName("PayoutOdds")
    private double PayoutOdds;
    @SerializedName("BetUrl")
    private String BetUrl;
    @SerializedName("MinLimit")
    private String MinLimit;
    @SerializedName("MaxLimit")
    private String MaxLimit;
    @SerializedName("HidOdds")
    private String HidOdds;
    @SerializedName("AmtS")
    private String AmtS;
    @SerializedName("ExRate")
    private String ExRate;
    @SerializedName("BetterOdds")
    private int BetterOdds;
    @SerializedName("MoreBetUrl")
    private String MoreBetUrl;
    @SerializedName("Test")
    private String Test;
    /**
     * SocOddsId : 9220030
     * Home : Schalke 04
     * Away : TSG Hoffenheim
     * IsHomeGive : true
     * IsBetHome : true
     * ParFullTimeId : 0
     * TransType : 1
     * IsOddsChange : false
     * ParUrl : http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?&g=2&b=1_par&oId=9220027&odds=0.24
     * BetHdp : 0
     * BetOu : 0
     * BetOdds : 1.62
     * Test2 : testing2
     */

    @SerializedName("BetPar")
    private List<BetParBean> BetPar;

    public String getBetType() {
        return BetType;
    }

    public void setBetType(String BetType) {
        this.BetType = BetType;
    }

    public String getGTitle() {
        return GTitle;
    }

    public void setGTitle(String GTitle) {
        this.GTitle = GTitle;
    }

    public boolean isParIsHomeGive() {
        return ParIsHomeGive;
    }

    public void setParIsHomeGive(boolean ParIsHomeGive) {
        this.ParIsHomeGive = ParIsHomeGive;
    }

    public String getParHome() {
        return ParHome;
    }

    public void setParHome(String ParHome) {
        this.ParHome = ParHome;
    }

    public String getParAway() {
        return ParAway;
    }

    public void setParAway(String ParAway) {
        this.ParAway = ParAway;
    }

    public boolean isParIsRun() {
        return ParIsRun;
    }

    public void setParIsRun(boolean ParIsRun) {
        this.ParIsRun = ParIsRun;
    }

    public String getParGameType3() {
        return ParGameType3;
    }

    public void setParGameType3(String ParGameType3) {
        this.ParGameType3 = ParGameType3;
    }

    public String getMaxParTicket() {
        return MaxParTicket;
    }

    public void setMaxParTicket(String MaxParTicket) {
        this.MaxParTicket = MaxParTicket;
    }

    public String getMaxPar() {
        return MaxPar;
    }

    public void setMaxPar(String MaxPar) {
        this.MaxPar = MaxPar;
    }

    public double getPayoutOdds() {
        return PayoutOdds;
    }

    public void setPayoutOdds(double PayoutOdds) {
        this.PayoutOdds = PayoutOdds;
    }

    public String getBetUrl() {
        return BetUrl;
    }

    public void setBetUrl(String BetUrl) {
        this.BetUrl = BetUrl;
    }

    public String getMinLimit() {
        return MinLimit;
    }

    public void setMinLimit(String MinLimit) {
        this.MinLimit = MinLimit;
    }

    public String getMaxLimit() {
        return MaxLimit;
    }

    public void setMaxLimit(String MaxLimit) {
        this.MaxLimit = MaxLimit;
    }

    public String getHidOdds() {
        return HidOdds;
    }

    public void setHidOdds(String HidOdds) {
        this.HidOdds = HidOdds;
    }

    public String getAmtS() {
        return AmtS;
    }

    public void setAmtS(String AmtS) {
        this.AmtS = AmtS;
    }

    public String getExRate() {
        return ExRate;
    }

    public void setExRate(String ExRate) {
        this.ExRate = ExRate;
    }

    public int getBetterOdds() {
        return BetterOdds;
    }

    public void setBetterOdds(int BetterOdds) {
        this.BetterOdds = BetterOdds;
    }

    public String getMoreBetUrl() {
        return MoreBetUrl;
    }

    public void setMoreBetUrl(String MoreBetUrl) {
        this.MoreBetUrl = MoreBetUrl;
    }

    public String getTest() {
        return Test;
    }

    public void setTest(String Test) {
        this.Test = Test;
    }

    public List<BetParBean> getBetPar() {
        return BetPar;
    }

    public void setBetPar(List<BetParBean> BetPar) {
        this.BetPar = BetPar;
    }

    public static class BetParBean {
        @SerializedName("SocOddsId")
        private int SocOddsId;
        @SerializedName("Home")
        private String Home;
        @SerializedName("Away")
        private String Away;
        @SerializedName("IsHomeGive")
        private boolean IsHomeGive;
        @SerializedName("IsBetHome")
        private boolean IsBetHome;
        @SerializedName("ParFullTimeId")
        private String ParFullTimeId;
        @SerializedName("TransType")
        private String TransType;
        @SerializedName("IsOddsChange")
        private boolean IsOddsChange;
        @SerializedName("ParUrl")
        private String ParUrl;
        @SerializedName("BetHdp")
        private String BetHdp;
        @SerializedName("BetOu")
        private String BetOu;
        @SerializedName("BetOdds")
        private String BetOdds;
        @SerializedName("Test2")
        private String Test2;

        public int getSocOddsId() {
            return SocOddsId;
        }

        public void setSocOddsId(int SocOddsId) {
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

        public String getParFullTimeId() {
            return ParFullTimeId;
        }

        public void setParFullTimeId(String ParFullTimeId) {
            this.ParFullTimeId = ParFullTimeId;
        }

        public String getTransType() {
            return TransType;
        }

        public void setTransType(String TransType) {
            this.TransType = TransType;
        }

        public boolean isIsOddsChange() {
            return IsOddsChange;
        }

        public void setIsOddsChange(boolean IsOddsChange) {
            this.IsOddsChange = IsOddsChange;
        }

        public String getParUrl() {
            return ParUrl;
        }

        public void setParUrl(String ParUrl) {
            this.ParUrl = ParUrl;
        }

        public String getBetHdp() {
            return BetHdp;
        }

        public void setBetHdp(String BetHdp) {
            this.BetHdp = BetHdp;
        }

        public String getBetOu() {
            return BetOu;
        }

        public void setBetOu(String BetOu) {
            this.BetOu = BetOu;
        }

        public String getBetOdds() {
            return BetOdds;
        }

        public void setBetOdds(String BetOdds) {
            this.BetOdds = BetOdds;
        }

        public String getTest2() {
            return Test2;
        }

        public void setTest2(String Test2) {
            this.Test2 = Test2;
        }
    }
}
