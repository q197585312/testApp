package com.nanyang.app.main.home.sport.europe;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class EuropeMixInfo extends BallInfo  {
    String SocOddsId ;
    String SocOddsId_FH ;
    String Live ;
    String IsLastCall ;
    String MatchDate ;
    String IsHomeGive ;
    String Home ;
    String Away ;
    String X12New ;
    String HasX12 ;
    String IsInetBet ;
    String X12_1Odds ;
    String X12_XOdds ;
    String X12_2Odds ;
    String HasHdp ;
    String Hdp ;
    String HdpOdds ;
    String HomeHdpOdds ;
    String AwayHdpOdds ;
    String HasOU ;
    String OU ;
    String IsHdpNew ;//HdpNew
    String IsOUNew ;//OUNew
    String OUOdds ;
    String OverOdds ;
    String UnderOdds ;
    String OENew ;
    String HasOE ;
    String OEOdds ;
    String OddOdds ;
    String EvenOdds ;
    String X12New_FH ;
    String HasX12_FH ;
    String IsInetBet_FH ;
    String X12_1Odds_FH ;
    String X12_XOdds_FH ;
    String X12_2Odds_FH ;
    String HasHdp_FH ;
    String IsHomeGive_FH ;
    String Hdp_FH ;
    String IsHdpNew_FH ;//HdpNew_FH
    String HdpOdds_FH ;
    String HomeHdpOdds_FH ;
    String AwayHdpOdds_FH ;
    String HasOU_FH ;
    String OU_FH ;
    String IsOUNew_FH ;//OUNew_FH
    String OUOdds_FH ;
    String OverOdds_FH ;
    String UnderOdds_FH ;
    String PreSocOddsId ;
    String CurMinute ;
    String Status ;
    String StatsId ;
    String RCHome ;
    String RCAway ;

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
        if(X12_1Odds.equals("0")){
            X12_1Odds="";
        }
        else if(!X12_1Odds.isEmpty()){
            X12_1Odds=AfbUtils.decimalValue(Float.valueOf(X12_1Odds),"0.00");
        }
        return X12_1Odds;
    }

    @Override
    public void setX12_1Odds(String x12_1Odds) {
        X12_1Odds = x12_1Odds;
    }

    public String getX12_XOdds() {
        if(X12_XOdds.equals("0")){
            X12_XOdds="";
        }
        else if(!X12_XOdds.isEmpty()){
            X12_XOdds=AfbUtils.decimalValue(Float.valueOf(X12_XOdds),"0.00");
        }
        return X12_XOdds;
    }

    public void setX12_XOdds(String x12_XOdds) {
        X12_XOdds = x12_XOdds;
    }

    public String getX12_2Odds() {
        if(X12_2Odds.equals("0")){
            X12_2Odds="";
        }
        else if(!X12_2Odds.isEmpty()){
            X12_2Odds=AfbUtils.decimalValue(Float.valueOf(X12_2Odds),"0.00");
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
        if(X12_1Odds_FH.equals("0")){
            X12_1Odds_FH="";
        }
        else if(!X12_1Odds_FH.isEmpty()){
            X12_1Odds_FH=AfbUtils.decimalValue(Float.valueOf(X12_1Odds_FH),"0.00");
        }
        return X12_1Odds_FH;
    }

    public void setX12_1Odds_FH(String x12_1Odds_FH) {
        X12_1Odds_FH = x12_1Odds_FH;
    }

    public String getX12_XOdds_FH() {
        if(X12_XOdds_FH.equals("0")){
            X12_XOdds_FH="";
        }
        else if(!X12_XOdds_FH.isEmpty()){
            X12_XOdds_FH=AfbUtils.decimalValue(Float.valueOf(X12_XOdds_FH),"0.00");
        }
        return X12_XOdds_FH;
    }

    public void setX12_XOdds_FH(String x12_XOdds_FH) {
        X12_XOdds_FH = x12_XOdds_FH;
    }

    public String getX12_2Odds_FH() {
        if(X12_2Odds_FH.equals("0")){
            X12_2Odds_FH="";
        }
        else if(!X12_2Odds_FH.isEmpty()){
            X12_2Odds_FH=AfbUtils.decimalValue(Float.valueOf(X12_2Odds_FH),"0.00");
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

    @Override
    public String getIsHomeGive() {
        return IsHomeGive;
    }

    @Override
    public void setIsHomeGive(String isHomeGive) {
        IsHomeGive = isHomeGive;
    }

    @Override
    public String getHasHdp() {
        return HasHdp;
    }

    @Override
    public void setHasHdp(String hasHdp) {
        HasHdp = hasHdp;
    }

    @Override
    public String getHdp() {
        return Hdp;
    }

    @Override
    public void setHdp(String hdp) {
        Hdp = hdp;
    }

    @Override
    public String getHdpOdds() {
        return HdpOdds;
    }

    @Override
    public void setHdpOdds(String hdpOdds) {
        HdpOdds = hdpOdds;
    }

    @Override
    public String getHomeHdpOdds() {
        return HomeHdpOdds;
    }

    @Override
    public void setHomeHdpOdds(String homeHdpOdds) {
        HomeHdpOdds = homeHdpOdds;
    }

    @Override
    public String getAwayHdpOdds() {
        return AwayHdpOdds;
    }

    @Override
    public void setAwayHdpOdds(String awayHdpOdds) {
        AwayHdpOdds = awayHdpOdds;
    }

    @Override
    public String getHasOU() {
        return HasOU;
    }

    @Override
    public void setHasOU(String hasOU) {
        HasOU = hasOU;
    }

    @Override
    public String getOU() {
        return OU;
    }

    @Override
    public void setOU(String OU) {
        this.OU = OU;
    }

    @Override
    public String getIsHdpNew() {
        return IsHdpNew;
    }

    @Override
    public void setIsHdpNew(String isHdpNew) {
        IsHdpNew = isHdpNew;
    }

    @Override
    public String getIsOUNew() {
        return IsOUNew;
    }

    @Override
    public void setIsOUNew(String isOUNew) {
        IsOUNew = isOUNew;
    }

    @Override
    public String getOUOdds() {
        return OUOdds;
    }

    @Override
    public void setOUOdds(String OUOdds) {
        this.OUOdds = OUOdds;
    }

    @Override
    public String getOverOdds() {
        return OverOdds;
    }

    @Override
    public void setOverOdds(String overOdds) {
        OverOdds = overOdds;
    }

    @Override
    public String getUnderOdds() {
        return UnderOdds;
    }

    @Override
    public void setUnderOdds(String underOdds) {
        UnderOdds = underOdds;
    }

    public String getHasHdp_FH() {
        return HasHdp_FH;
    }

    public void setHasHdp_FH(String hasHdp_FH) {
        HasHdp_FH = hasHdp_FH;
    }

    public String getIsHomeGive_FH() {
        return IsHomeGive_FH;
    }

    public void setIsHomeGive_FH(String isHomeGive_FH) {
        IsHomeGive_FH = isHomeGive_FH;
    }

    public String getHdp_FH() {
        return Hdp_FH;
    }

    public void setHdp_FH(String hdp_FH) {
        Hdp_FH = hdp_FH;
    }

    public String getIsHdpNew_FH() {
        return IsHdpNew_FH;
    }

    public void setIsHdpNew_FH(String isHdpNew_FH) {
        IsHdpNew_FH = isHdpNew_FH;
    }

    public String getHdpOdds_FH() {
        return HdpOdds_FH;
    }

    public void setHdpOdds_FH(String hdpOdds_FH) {
        HdpOdds_FH = hdpOdds_FH;
    }

    public String getHomeHdpOdds_FH() {
        return HomeHdpOdds_FH;
    }

    public void setHomeHdpOdds_FH(String homeHdpOdds_FH) {
        HomeHdpOdds_FH = homeHdpOdds_FH;
    }

    public String getAwayHdpOdds_FH() {
        return AwayHdpOdds_FH;
    }

    public void setAwayHdpOdds_FH(String awayHdpOdds_FH) {
        AwayHdpOdds_FH = awayHdpOdds_FH;
    }

    public String getHasOU_FH() {
        return HasOU_FH;
    }

    public void setHasOU_FH(String hasOU_FH) {
        HasOU_FH = hasOU_FH;
    }

    public String getOU_FH() {
        return OU_FH;
    }

    public void setOU_FH(String OU_FH) {
        this.OU_FH = OU_FH;
    }

    public String getIsOUNew_FH() {
        return IsOUNew_FH;
    }

    public void setIsOUNew_FH(String isOUNew_FH) {
        IsOUNew_FH = isOUNew_FH;
    }

    public String getOUOdds_FH() {
        return OUOdds_FH;
    }

    public void setOUOdds_FH(String OUOdds_FH) {
        this.OUOdds_FH = OUOdds_FH;
    }

    public String getOverOdds_FH() {
        return OverOdds_FH;
    }

    public void setOverOdds_FH(String overOdds_FH) {
        OverOdds_FH = overOdds_FH;
    }

    public String getUnderOdds_FH() {
        return UnderOdds_FH;
    }

    public void setUnderOdds_FH(String underOdds_FH) {
        UnderOdds_FH = underOdds_FH;
    }
}