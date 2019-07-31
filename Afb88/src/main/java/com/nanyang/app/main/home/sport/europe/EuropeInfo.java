package com.nanyang.app.main.home.sport.europe;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class EuropeInfo extends BallInfo implements IRTMatchInfo {
    String SocOddsId;
    String SocOddsId_FH;
    String Live;
    String IsLastCall;
    String MatchDate;
    String Home;
    String Away;
    String OENew;
    String IsInetBet;
    String HasOE;
    String OEOdds;
    String OddOdds;
    String EvenOdds;
    String X12New;
    String HasX12;
    String X12_1Odds;
    String X12_XOdds;
    String X12_2Odds;
    String IsInetBet_FH;
    String HasX12_FH;
    String X12_1Odds_FH;
    String X12_XOdds_FH;
    String X12_2Odds_FH;
    String X12New_FH;
    String PreSocOddsId;
    String CurMinute;
    String Status;
    String RTSMatchId;
    String StatsId;
    String RCHome;
    String RCAway;

    @Override
    public String getSocOddsId() {
        return SocOddsId;
    }

    @Override
    public void setSocOddsId(String socOddsId) {
        SocOddsId = socOddsId;
    }

    public String getSocOddsId_FH() {
        return SocOddsId_FH;
    }

    public void setSocOddsId_FH(String socOddsId_FH) {
        SocOddsId_FH = socOddsId_FH;
    }

    public String getLive() {
        return Live;
    }

    public void setLive(String live) {
        Live = live;
    }

    public String getIsLastCall() {
        return IsLastCall;
    }

    public void setIsLastCall(String isLastCall) {
        IsLastCall = isLastCall;
    }

    public String getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(String matchDate) {
        MatchDate = matchDate;
    }

    @Override
    public String getHome() {
        return Home;
    }

    @Override
    public void setHome(String home) {
        Home = home;
    }

    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public String getOENew() {
        return OENew;
    }

    public void setOENew(String OENew) {
        this.OENew = OENew;
    }

    @Override
    public String getIsInetBet() {
        return IsInetBet;
    }

    @Override
    public void setIsInetBet(String isInetBet) {
        IsInetBet = isInetBet;
    }

    public String getHasOE() {
        return HasOE;
    }

    public void setHasOE(String hasOE) {
        HasOE = hasOE;
    }

    public String getOEOdds() {
        return OEOdds;
    }

    public void setOEOdds(String OEOdds) {
        this.OEOdds = OEOdds;
    }

    public String getOddOdds() {
        return OddOdds;
    }

    public void setOddOdds(String oddOdds) {
        OddOdds = oddOdds;
    }

    public String getEvenOdds() {
        return EvenOdds;
    }

    public void setEvenOdds(String evenOdds) {
        EvenOdds = evenOdds;
    }

    public String getX12New() {
        return X12New;
    }

    public void setX12New(String x12New) {
        X12New = x12New;
    }

    @Override
    public String getHasX12() {
        return HasX12;
    }

    @Override
    public void setHasX12(String hasX12) {
        HasX12 = hasX12;
    }

    @Override
    public String getX12_1Odds() {
        if (X12_1Odds.equals("0")) {
            X12_1Odds = "";
        } else if (!X12_1Odds.isEmpty()) {
            X12_1Odds = AfbUtils.decimalValue(Float.valueOf(X12_1Odds), "0.00");
        }
        return X12_1Odds;
    }

    @Override
    public void setX12_1Odds(String x12_1Odds) {
        X12_1Odds = x12_1Odds;
    }

    public String getX12_XOdds() {
        if (X12_XOdds.equals("0")) {
            X12_XOdds = "";
        } else if (!X12_XOdds.isEmpty()) {
            X12_XOdds = AfbUtils.decimalValue(Float.valueOf(X12_XOdds), "0.00");
        }
        return X12_XOdds;
    }

    public void setX12_XOdds(String x12_XOdds) {
        X12_XOdds = x12_XOdds;
    }

    public String getX12_2Odds() {
        if (X12_2Odds.equals("0")) {
            X12_2Odds = "";
        } else if (!X12_2Odds.isEmpty()) {
            X12_2Odds = AfbUtils.decimalValue(Float.valueOf(X12_2Odds), "0.00");
        }
        return X12_2Odds;
    }

    public void setX12_2Odds(String x12_2Odds) {
        X12_2Odds = x12_2Odds;
    }

    public String getIsInetBet_FH() {
        return IsInetBet_FH;
    }

    public void setIsInetBet_FH(String isInetBet_FH) {
        IsInetBet_FH = isInetBet_FH;
    }

    public String getHasX12_FH() {
        return HasX12_FH;
    }

    public void setHasX12_FH(String hasX12_FH) {
        HasX12_FH = hasX12_FH;
    }

    public String getX12_1Odds_FH() {
        if (X12_1Odds_FH.equals("0")) {
            X12_1Odds_FH = "";
        } else if (!X12_1Odds_FH.isEmpty()) {
            X12_1Odds_FH = AfbUtils.decimalValue(Float.valueOf(X12_1Odds_FH), "0.00");
        }
        return X12_1Odds_FH;
    }

    public void setX12_1Odds_FH(String x12_1Odds_FH) {
        X12_1Odds_FH = x12_1Odds_FH;
    }

    public String getX12_XOdds_FH() {
        if (X12_XOdds_FH.equals("0")) {
            X12_XOdds_FH = "";
        } else if (!X12_XOdds_FH.isEmpty()) {
            X12_XOdds_FH = AfbUtils.decimalValue(Float.valueOf(X12_XOdds_FH), "0.00");
        }
        return X12_XOdds_FH;
    }

    public void setX12_XOdds_FH(String x12_XOdds_FH) {
        X12_XOdds_FH = x12_XOdds_FH;
    }

    public String getX12_2Odds_FH() {
        if (X12_2Odds_FH.equals("0")) {
            X12_2Odds_FH = "";
        } else if (!X12_2Odds_FH.isEmpty()) {
            X12_2Odds_FH = AfbUtils.decimalValue(Float.valueOf(X12_2Odds_FH), "0.00");
        }
        return X12_2Odds_FH;
    }

    public void setX12_2Odds_FH(String x12_2Odds_FH) {

        X12_2Odds_FH = x12_2Odds_FH;
    }

    public String getX12New_FH() {
        return X12New_FH;
    }

    public void setX12New_FH(String x12New_FH) {
        X12New_FH = x12New_FH;
    }

    @Override
    public String getPreSocOddsId() {
        return PreSocOddsId;
    }

    @Override
    public void setPreSocOddsId(String preSocOddsId) {
        PreSocOddsId = preSocOddsId;
    }

    public String getCurMinute() {
        return CurMinute;
    }

    public void setCurMinute(String curMinute) {
        CurMinute = curMinute;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRTSMatchId() {
        return RTSMatchId;
    }

    public void setRTSMatchId(String RTSMatchId) {
        this.RTSMatchId = RTSMatchId;
    }

    public String getStatsId() {
        return StatsId;
    }

    public void setStatsId(String statsId) {
        StatsId = statsId;
    }

    public String getRCHome() {
        return RCHome;
    }

    public void setRCHome(String RCHome) {
        this.RCHome = RCHome;
    }

    public String getRCAway() {
        return RCAway;
    }

    public void setRCAway(String RCAway) {
        this.RCAway = RCAway;
    }
}