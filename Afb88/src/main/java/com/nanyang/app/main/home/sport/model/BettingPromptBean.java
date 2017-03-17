package com.nanyang.app.main.home.sport.model;

/**
 * Created by Administrator on 2015/11/24.
 */
public class BettingPromptBean {


    /**{"BetType": "away",
     * "GTitle": "亚州盤",
     * "ModuleTitle": "欧洲冠军联赛",
     * "FullTimeId": "0",
     * "IsHomeGive": true,
     * "Home": "AS摩纳哥",
     * "Away": "曼城",
     * "IsRun": false,
     * "IsOddsChange": false,
     * "RunHomeScore": "0",
     * "RunAwayScore": "0",
     * "GameType3": "N",
     * "BetHdp": "0",
     * "BetOdds": "1.08",
     * "BetUrl":
     * "PanelBet.aspx?betGrp=1&betType=away&oId=12219287&isHomeGive=True&isBetHome=False&isFH=False&hdp=0&accType=HK&odds=10.8&reducePercent=1",
     * "MinLimit": "10",
     * "MaxLimit": "30000",
     * "HidOdds": "1.08",
     * "AmtS": "0", "ExRate": "1", "BetterOdds": 1, "MoreBetUrl": "../_View/MoreBet.aspx?oId=12219287&home=AS%e6%91%a9%e7%ba%b3%e5%93%a5&away=%e6%9b%bc%e5%9f%8e&moduleTitle=%e6%ac%a7%e6%b4%b2%e5%86%a0%e5%86%9b%e8%81%94%e8%b5%9b&date=03%3a45AM&lang=eng", "Test": "testing" }*/
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
    private String BetType;
    private String GTitle;
    private String ModuleTitle;
    private String FullTimeId;
    private boolean IsHomeGive;
    private String Home;
    private String Away;
    private boolean IsRun;
    private boolean IsOddsChange;
    private String RunHomeScore;
    private String RunAwayScore;
    private String GameType3;
    private String BetHdp;
    private String BetOdds;
    private String BetUrl;
    private String MinLimit;
    private String MaxLimit;
    private String HidOdds;
    private String AmtS;
    private String ExRate;
    private int BetterOdds;
    private String MoreBetUrl;
    private String Test;

    public void setBetType(String BetType) {
        this.BetType = BetType;
    }

    public void setGTitle(String GTitle) {
        this.GTitle = GTitle;
    }

    public void setModuleTitle(String ModuleTitle) {
        this.ModuleTitle = ModuleTitle;
    }

    public void setFullTimeId(String FullTimeId) {
        this.FullTimeId = FullTimeId;
    }

    public void setIsHomeGive(boolean IsHomeGive) {
        this.IsHomeGive = IsHomeGive;
    }

    public void setHome(String Home) {
        this.Home = Home;
    }

    public void setAway(String Away) {
        this.Away = Away;
    }

    public void setIsRun(boolean IsRun) {
        this.IsRun = IsRun;
    }

    public void setIsOddsChange(boolean IsOddsChange) {
        this.IsOddsChange = IsOddsChange;
    }

    public void setRunHomeScore(String RunHomeScore) {
        this.RunHomeScore = RunHomeScore;
    }

    public void setRunAwayScore(String RunAwayScore) {
        this.RunAwayScore = RunAwayScore;
    }

    public void setGameType3(String GameType3) {
        this.GameType3 = GameType3;
    }

    public void setBetHdp(String BetHdp) {
        this.BetHdp = BetHdp;
    }

    public void setBetOdds(String BetOdds) {
        this.BetOdds = BetOdds;
    }

    public void setBetUrl(String BetUrl) {
        this.BetUrl = BetUrl;
    }

    public void setMinLimit(String MinLimit) {
        this.MinLimit = MinLimit;
    }

    public void setMaxLimit(String MaxLimit) {
        this.MaxLimit = MaxLimit;
    }

    public void setHidOdds(String HidOdds) {
        this.HidOdds = HidOdds;
    }

    public void setAmtS(String AmtS) {
        this.AmtS = AmtS;
    }

    public void setExRate(String ExRate) {
        this.ExRate = ExRate;
    }

    public void setBetterOdds(int BetterOdds) {
        this.BetterOdds = BetterOdds;
    }

    public void setMoreBetUrl(String MoreBetUrl) {
        this.MoreBetUrl = MoreBetUrl;
    }

    public void setTest(String Test) {
        this.Test = Test;
    }

    public String getBetType() {
        return BetType;
    }

    public String getGTitle() {
        return GTitle;
    }

    public String getModuleTitle() {
        return ModuleTitle;
    }

    public String getFullTimeId() {
        return FullTimeId;
    }

    public boolean isIsHomeGive() {
        return IsHomeGive;
    }

    public String getHome() {
        return Home;
    }

    public String getAway() {
        return Away;
    }

    public boolean isIsRun() {
        return IsRun;
    }

    public boolean isIsOddsChange() {
        return IsOddsChange;
    }

    public String getRunHomeScore() {
        return RunHomeScore;
    }

    public String getRunAwayScore() {
        return RunAwayScore;
    }

    public String getGameType3() {
        return GameType3;
    }

    public String getBetHdp() {
        return BetHdp;
    }

    public String getBetOdds() {
        return BetOdds;
    }

    public String getBetUrl() {
        return BetUrl;
    }

    public String getMinLimit() {
        return MinLimit;
    }

    public String getMaxLimit() {
        return MaxLimit;
    }

    public String getHidOdds() {
        return HidOdds;
    }

    public String getAmtS() {
        return AmtS;
    }

    public String getExRate() {
        return ExRate;
    }

    public int getBetterOdds() {
        return BetterOdds;
    }

    public String getMoreBetUrl() {
        return MoreBetUrl;
    }

    public String getTest() {
        return Test;
    }
}
