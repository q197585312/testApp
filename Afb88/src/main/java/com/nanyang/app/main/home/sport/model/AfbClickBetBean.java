package com.nanyang.app.main.home.sport.model;

import com.nanyang.app.main.home.sport.main.AfbParseHelper;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/26.
 */

public class AfbClickBetBean implements Serializable {


    /**
     * id : s|over|9|466844|466844|
     * Hdp : (116.5)
     * Odds : 0.99
     * League : NBA 美国职业篮球赛
     * Home : 夏洛特黄蜂
     * Away : 密爾沃基公鹿
     * Credit : 999809.86
     * MaxLimit : 2000
     * MinLimit : 3
     * MatchLimit : 1000000
     * OddsMaxLimit : 2000
     * RJBetAmt : 3.6028797018964E21
     * AccMaxLimit : 1000000
     * Score :
     * IsRun : 0
     * IsGive : 0
     * bTT : 半场.大@
     * bTeam : Home
     * beturl : hBetSub.ashx?betGrp=9&betType=over&oId=466844&oId_fh=466844&ou=116.5&isBetHome=True&isFH=True&accType=MY&odds=9.9&reducePercent=1
     * AmtS : 0
     * outright : false
     * IsFH : 上半场
     */

    private String id;
    private String Hdp;
    private String Odds;
    private String League;
    private String Home;
    private String Away;
    private double Credit;
    private int MaxLimit;
    private int MinLimit;
    private double MatchLimit;
    private double OddsMaxLimit;
    private double RJBetAmt;
    private int AccMaxLimit;
    private String Score;
    private int IsRun;
    private int IsGive;
    private String bTT;
    private String bTeam;
    private String beturl;
    private String AmtS;
    private String outright;
    private String IsFH;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLeague() {
        return League;
    }

    public void setLeague(String League) {
        this.League = League;
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

    public double getCredit() {
        return Credit;
    }

    public void setCredit(double Credit) {
        this.Credit = Credit;
    }

    public int getMaxLimit() {
        return MaxLimit;
    }

    public void setMaxLimit(int MaxLimit) {
        this.MaxLimit = MaxLimit;
    }

    public int getMinLimit() {
        return MinLimit;
    }

    public void setMinLimit(int MinLimit) {
        this.MinLimit = MinLimit;
    }

    public double getMatchLimit() {
        return MatchLimit;
    }

    public void setMatchLimit(double MatchLimit) {
        this.MatchLimit = MatchLimit;
    }

    public double getOddsMaxLimit() {
        return OddsMaxLimit;
    }

    public void setOddsMaxLimit(double OddsMaxLimit) {
        this.OddsMaxLimit = OddsMaxLimit;
    }

    public double getRJBetAmt() {
        return RJBetAmt;
    }

    public void setRJBetAmt(double RJBetAmt) {
        this.RJBetAmt = RJBetAmt;
    }

    public int getAccMaxLimit() {
        return AccMaxLimit;
    }

    public void setAccMaxLimit(int AccMaxLimit) {
        this.AccMaxLimit = AccMaxLimit;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String Score) {
        this.Score = Score;
    }

    public int getIsRun() {
        return IsRun;
    }

    public void setIsRun(int IsRun) {
        this.IsRun = IsRun;
    }

    public int getIsGive() {
        return IsGive;
    }

    public void setIsGive(int IsGive) {
        this.IsGive = IsGive;
    }

    public String getBTT() {
        return bTT;
    }

    public void setBTT(String bTT) {
        this.bTT = bTT;
    }

    public String getBTeam() {
        return bTeam;
    }

    public void setBTeam(String bTeam) {
        this.bTeam = bTeam;
    }

    public String getBeturl() {
        return beturl;
    }

    public void setBeturl(String beturl) {
        this.beturl = beturl;
    }

    public String getAmtS() {
        return AmtS;
    }

    public void setAmtS(String AmtS) {
        this.AmtS = AmtS;
    }

    public String getOutright() {
        return outright;
    }

    public void setOutright(String outright) {
        this.outright = outright;
    }

    public String getIsFH() {
        return IsFH;
    }

    public void setIsFH(String IsFH) {
        this.IsFH = IsFH;
    }

    public String getSocOddsId (){
        return new AfbParseHelper<>().getSocOddsIdFromId(id);
    }
    public String getOddsType (){
        return new AfbParseHelper<>().getBetTypeFromId(id);
    }
    public String getOddsG (){
        return new AfbParseHelper<>().getSocOddsG(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AfbClickBetBean)) return false;

        AfbClickBetBean that = (AfbClickBetBean) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
