package com.nanyang.app.main.home.sport.model;

/**
 * Created by Administrator on 2017/3/11.
 */

import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;

import java.util.List;

import cn.finalteam.toolsfinal.StringUtils;

 /* SportInfo.Type type;
          String moduleId;
          CharSequence ModuleTitle;
          String SocOddsId, Home, IsInetBet, isX12New, HasX12, X12_1Odds, PreSocOddsId;*/

/**
 * ],String[[12683157 ,String12683159 ,String'' ,String0,String'04/06 02:45AM' ,String'Juventus [n]' ,String'Real Madrid' ,String0,String1,String1,String-10,String10,String9,String0,String1,String2.89,String3.13,2.47,1,1,3.5,1.98,3.21,0,0,0,0,0,0,0,0]]],[[;
 */
public class BallInfo extends SportInfo implements IRTMatchInfo {

    private static final String TAG = "BallInfo";
    String hasHdp_FH;
    String hasOU_FH;
    String isHdpNew_FH;
    String isOUNew_FH;

    public String getTvPathIBC() {
        return tvPathIBC;
    }

    String tvPathIBC;
    List<BallInfo> repeatRow;
    public boolean isHomeScoreBigger;
    public boolean isAwayScoreBigger;
    public int isHomeBigger;
    public int isAwayBigger;
    public int isOverBigger;
    public int isUnderBigger;
    public int isFt1Bigger;
    public int isFt2Bigger;
    public int isFtXBigger;
    public int isX12_XNew;
    public int isX12_1New;
    public int isX12_2New;

    public String getShowGoal() {
        return showGoal;
    }

    public String getIsHomeGoal() {
        return isHomeGoal;
    }

    private String showGoal;
    private String isHomeGoal;

    public String getMainModuleId() {
        return MainModuleId;
    }

    public void setMainModuleId(String mainModuleId) {
        MainModuleId = mainModuleId;
    }

    public String getMainHomeId() {
        return MainHomeId;
    }

    public void setMainHomeId(String mainHomeId) {
        MainHomeId = mainHomeId;
    }

    public String getMainAwayId() {
        return MainAwayId;
    }

    public void setMainAwayId(String mainAwayId) {
        MainAwayId = mainAwayId;
    }

    String MainModuleId;
    String MainHomeId;
    String MainAwayId;

    public int isFt1Bigger() {
        return isFt1Bigger;
    }

    public void setFt1Bigger(int ft1Bigger) {
        isFt1Bigger = ft1Bigger;
    }

    public int isFt2Bigger() {
        return isFt2Bigger;
    }

    public void setFt2Bigger(int ft2Bigger) {
        isFt2Bigger = ft2Bigger;
    }

    public int isFtXBigger() {
        return isFtXBigger;
    }

    public void setFtXBigger(int ftXBigger) {
        isFtXBigger = ftXBigger;
    }


    public void setIsHdpNew(String isHdpNew) {
        this.isHdpNew = isHdpNew;
    }

    public void setIsOUNew(String isOUNew) {
        this.isOUNew = isOUNew;
    }

    public void setIsOENew_FH(String isOENew_FH) {
        this.isOENew_FH = isOENew_FH;
    }

    String isHdpNew;
    String isOUNew;
    String isOENew;
    String hasOE;
    String hasOE_FH;
    String isOENew_FH;

    @Override
    public String getSocOddsId() {
        return SocOddsId;
    }

    @Override
    public void setSocOddsId(String socOddsId) {
        SocOddsId = socOddsId;
    }

    @Override
    public String getPreSocOddsId() {
        return PreSocOddsId;
    }

    @Override
    public void setPreSocOddsId(String preSocOddsId) {
        PreSocOddsId = preSocOddsId;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCurMinute() {
        return CurMinute;
    }

    public void setCurMinute(String curMinute) {
        CurMinute = curMinute;
    }

    @Override
    public String getIsInetBet() {
        return IsInetBet;
    }

    @Override
    public void setIsInetBet(String isInetBet) {
        IsInetBet = isInetBet;
    }


    public String getIsHomeGive() {
        return IsHomeGive;
    }

    public void setIsHomeGive(String isHomeGive) {
        IsHomeGive = isHomeGive;
    }

    public String getRTSMatchId() {
        return RTSMatchId;
    }

    public void setRTSMatchId(String RTSMatchId) {
        this.RTSMatchId = RTSMatchId;
    }

    public String getHomeId() {
        return HomeId;
    }

    public void setHomeId(String homeId) {
        HomeId = homeId;
    }

    public String getAwayId() {
        return AwayId;
    }

    public void setAwayId(String awayId) {
        AwayId = awayId;
    }

    @Override
    public String getHome() {
        return Home;
    }

    @Override
    public void setHome(String home) {
        Home = home;
    }

    @Override
    public String getAway() {
        return Away;
    }

    @Override
    public void setAway(String away) {
        Away = away;
    }

    public String getHomeRank() {
        return HomeRank;
    }

    public void setHomeRank(String homeRank) {
        HomeRank = homeRank;
    }

    public String getAwayRank() {
        return AwayRank;
    }

    public void setAwayRank(String awayRank) {
        AwayRank = awayRank;
    }

    public String getRunHomeScore() {
        return RunHomeScore;
    }

    public void setRunHomeScore(String runHomeScore) {
        RunHomeScore = runHomeScore;
    }

    public String getRunAwayScore() {
        return RunAwayScore;
    }

    public void setRunAwayScore(String runAwayScore) {
        RunAwayScore = runAwayScore;
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

    public String getGamesSum() {
        return GamesSum;
    }

    public void setGamesSum(String gamesSum) {
        GamesSum = gamesSum;
    }

    public String getHdp() {
        return Hdp;
    }

    public void setHdp(String hdp) {
        Hdp = hdp;
    }

    public String getOU() {
        return OU;
    }

    public void setOU(String OU) {
        this.OU = OU;
    }

    public String getHOdds() {
        return HOdds;
    }

    public void setHOdds(String HOdds) {
        this.HOdds = HOdds;
    }

    public String getAOdds() {
        return AOdds;
    }

    public void setAOdds(String AOdds) {
        this.AOdds = AOdds;
    }

    public String getOOdds() {
        return OOdds;
    }

    public void setOOdds(String OOdds) {
        this.OOdds = OOdds;
    }

    public String getUOdds() {
        return UOdds;
    }

    public void setUOdds(String UOdds) {
        this.UOdds = UOdds;
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

    @Override
    public String getX12_1Odds() {
        return X12_1Odds;
    }

    @Override
    public void setX12_1Odds(String x12_1Odds) {
        X12_1Odds = x12_1Odds;
    }

    public String getX12_XOdds() {
        return X12_XOdds;
    }

    public void setX12_XOdds(String x12_XOdds) {
        X12_XOdds = x12_XOdds;
    }

    public String getX12_2Odds() {
        return X12_2Odds;
    }

    public void setX12_2Odds(String x12_2Odds) {
        X12_2Odds = x12_2Odds;
    }

    public String getHdpOdds() {
        return HdpOdds;
    }

    public void setHdpOdds(String hdpOdds) {
        HdpOdds = hdpOdds;
    }

    public String getOUOdds() {
        return OUOdds;
    }

    public void setOUOdds(String OUOdds) {
        this.OUOdds = OUOdds;
    }

    public String getOEOdds() {
        return OEOdds;
    }

    public void setOEOdds(String OEOdds) {
        this.OEOdds = OEOdds;
    }

    public String getHasPar() {
        return HasPar;
    }

    public void setHasPar(String hasPar) {
        HasPar = hasPar;
    }

    public String getIsInetBet_FH() {
        return IsInetBet_FH;
    }

    public void setIsInetBet_FH(String isInetBet_FH) {
        IsInetBet_FH = isInetBet_FH;
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

    public String getOU_FH() {
        return OU_FH;
    }

    public void setOU_FH(String OU_FH) {
        this.OU_FH = OU_FH;
    }

    public String getHOdds_FH() {
        return HOdds_FH;
    }

    public void setHOdds_FH(String HOdds_FH) {
        this.HOdds_FH = HOdds_FH;
    }

    public String getAOdds_FH() {
        return AOdds_FH;
    }

    public void setAOdds_FH(String AOdds_FH) {
        this.AOdds_FH = AOdds_FH;
    }

    public String getOOdds_FH() {
        return OOdds_FH;
    }

    public void setOOdds_FH(String OOdds_FH) {
        this.OOdds_FH = OOdds_FH;
    }

    public String getUOdds_FH() {
        return UOdds_FH;
    }

    public void setUOdds_FH(String UOdds_FH) {
        this.UOdds_FH = UOdds_FH;
    }

    public String getOddOdds_FH() {
        return OddOdds_FH;
    }

    public void setOddOdds_FH(String oddOdds_FH) {
        OddOdds_FH = oddOdds_FH;
    }

    public String getEvenOdds_FH() {
        return EvenOdds_FH;
    }

    public void setEvenOdds_FH(String evenOdds_FH) {
        EvenOdds_FH = evenOdds_FH;
    }

    public String getX12_1Odds_FH() {
        return X12_1Odds_FH;
    }

    public void setX12_1Odds_FH(String x12_1Odds_FH) {
        X12_1Odds_FH = x12_1Odds_FH;
    }

    public String getX12_XOdds_FH() {
        return X12_XOdds_FH;
    }

    public void setX12_XOdds_FH(String x12_XOdds_FH) {
        X12_XOdds_FH = x12_XOdds_FH;
    }

    public String getX12_2Odds_FH() {
        return X12_2Odds_FH;
    }

    public void setX12_2Odds_FH(String x12_2Odds_FH) {
        X12_2Odds_FH = x12_2Odds_FH;
    }

    public String getHdpOdds_FH() {
        return HdpOdds_FH;
    }

    public void setHdpOdds_FH(String hdpOdds_FH) {
        HdpOdds_FH = hdpOdds_FH;
    }

    public String getOUOdds_FH() {
        return OUOdds_FH;
    }

    public void setOUOdds_FH(String OUOdds_FH) {
        this.OUOdds_FH = OUOdds_FH;
    }

    public String getOEOdds_FH() {
        return OEOdds_FH;
    }

    public void setOEOdds_FH(String OEOdds_FH) {
        this.OEOdds_FH = OEOdds_FH;
    }

    public String getStatsId() {
        return StatsId;
    }

    public void setStatsId(String statsId) {
        StatsId = statsId;
    }

    public String getMExtraTime() {
        return MExtraTime;
    }

    public void setMExtraTime(String MExtraTime) {
        this.MExtraTime = MExtraTime;
    }

    public String getWorkingDate() {
        return WorkingDate;
    }

    public void setWorkingDate(String workingDate) {
        WorkingDate = workingDate;
    }

    public String getTodayDate() {
        return TodayDate;
    }

    public void setTodayDate(String todayDate) {
        TodayDate = todayDate;
    }

    public String getModuleOrder() {
        return ModuleOrder;
    }

    public void setModuleOrder(String moduleOrder) {
        ModuleOrder = moduleOrder;
    }

    @Override
    public String getModuleId() {
        return ModuleId;
    }

    @Override
    public void setModuleId(String moduleId) {
        ModuleId = moduleId;
    }

    public String getMatchCode() {
        return MatchCode;
    }

    public void setMatchCode(String matchCode) {
        MatchCode = matchCode;
    }

    public String getSortNum() {
        return SortNum;
    }

    public void setSortNum(String sortNum) {
        SortNum = sortNum;
    }

    public String getGGID() {
        return GGID;
    }

    public void setGGID(String GGID) {
        this.GGID = GGID;
    }

    public String getHasPar_FH() {
        return HasPar_FH;
    }

    public void setHasPar_FH(String hasPar_FH) {
        HasPar_FH = hasPar_FH;
    }


    String SocOddsId_FH;
    String Live;
    String IsLastCall;
    String MatchDate;
    String Status;
    String CurMinute;

    String IsHomeGive;
    String RTSMatchId;
    String HomeId;
    String AwayId;

    String HomeRank;
    String AwayRank;
    String RunHomeScore;
    String RunAwayScore;
    String RCHome;
    String RCAway;
    String GamesSum;
    String Hdp;
    String OU;
    String HOdds;
    String AOdds;
    String OOdds;
    String UOdds;
    String OddOdds;
    String EvenOdds;

    String X12_XOdds;
    String X12_2Odds;
    String HdpOdds;
    String OUOdds;
    String OEOdds;
    String HasPar;
    String IsInetBet_FH;
    String IsHomeGive_FH;
    String Hdp_FH;
    String OU_FH;
    String HOdds_FH;
    String AOdds_FH;
    String OOdds_FH;
    String UOdds_FH;
    String OddOdds_FH;
    String EvenOdds_FH;
    String X12_1Odds_FH;
    String X12_XOdds_FH;
    String X12_2Odds_FH;
    String HdpOdds_FH;
    String OUOdds_FH;
    String OEOdds_FH;
    String StatsId;
    String MExtraTime;
    String WorkingDate;
    String TodayDate;
    String ModuleOrder;

    String MatchCode;
    String SortNum;
    String GGID;
    String HasPar_FH;

    public String getHasHdp_FH() {
        if (StringUtils.isEmpty(getHOdds_FH()) || StringUtils.isEmpty(getAOdds_FH()) || StringUtils.isEmpty(getHdp_FH()) || Math.abs(Float.valueOf(getAOdds_FH())) < 0.3 || Math.abs(Float.valueOf(getHOdds_FH())) < 0.3)
            return "0";
        return "1";
    }

    public String getHasOU_FH() {
        if (StringUtils.isEmpty(getOOdds_FH()) || StringUtils.isEmpty(getUOdds_FH()) || StringUtils.isEmpty(getOU_FH()))
            return "0";
        return "1";
    }

    public String getIsHdpNew_FH() {
        return isHdpNew_FH;
    }

    public String getIsOUNew_FH() {
        return isOUNew_FH;
    }

    public String getIsHdpNew() {
        return isHdpNew;
    }

    public String getHasHdp() {
        if (StringUtils.isEmpty(getHOdds()) || StringUtils.isEmpty(getAOdds()) || StringUtils.isEmpty(getHdp()) || Math.abs(Float.valueOf(getAOdds())) < 0.3 || Math.abs(Float.valueOf(getHOdds())) < 0.3)
            hasHdp = "0";
        else
            hasHdp = "1";

        return hasHdp;
    }

    String hasHdp;

    public String getIsOUNew() {
        return isOUNew;
    }

    public String getHasOU() {
        if (StringUtils.isEmpty(getOOdds()) || StringUtils.isEmpty(getUOdds()) || StringUtils.isEmpty(getOU()))
            return "0";
        return "1";
    }

    public String getIsOENew() {
        return isOENew;
    }

    public String getHasOE() {
        if (StringUtils.isEmpty(getOddOdds()) || StringUtils.isEmpty(getEvenOdds()))
            return "0";
        return "1";
    }

    public String getHasOE_FH() {
        if (StringUtils.isEmpty(getOddOdds_FH()) || StringUtils.isEmpty(getEvenOdds_FH()))
            return "0";
        return "1";
    }

    public String getIsOENew_FH() {
        return isOENew_FH;
    }

    public String getHasX12_FH() {
        return "1";
    }

    public void setIsOENew(String isOENew) {
        this.isOENew = isOENew;
    }

    public void setIsHdpNew_FH(String isHdpNew_FH) {
        this.isHdpNew_FH = isHdpNew_FH;
    }

    public void setIsOUNew_FH(String isOUNew_FH) {
        this.isOUNew_FH = isOUNew_FH;
    }


    @Override
    public void setValue(int i, String s) {
        switch (i) {
            case 0:
                setSocOddsId(s);
                break;
            case 1:
                setPreSocOddsId(s);
                break;
            case 2:
                setSocOddsId_FH(s);
                break;
            case 3:
                setLive(s);
                break;
            case 4:
                setIsLastCall(s);
                break;
            case 5:
                setMatchDate(s);
                break;
            case 6:
                setStatus(s);
                break;
            case 7:
                setCurMinute(s);
                break;
            case 8:
                setIsInetBet(s);
                break;
            case 9:
                setIsHomeGive(s);
                break;
            case 10:
                setRTSMatchId(s);
                break;
            case 11:
                setHomeId(s);
                break;
            case 12:
                setAwayId(s);
                break;
            case 13:
                setHome(s);
                break;
            case 14:
                setAway(s);
                break;
            case 15:
                setHomeRank(s);
                break;
            case 16:
                setAwayRank(s);
                break;
            case 17:
                setHomeScoreBigger(isScoreBigger(getRunHomeScore(), s));
                setRunHomeScore(s);
                break;
            case 18:
                setAwayScoreBigger(isScoreBigger(getRunAwayScore(), s));
                setRunAwayScore(s);
                break;
            case 19:
                setRCHome(s);
                break;
            case 20:
                setRCAway(s);
                break;
            case 21:
                setGamesSum(s);
                break;
            case 22:
                setHdp(s);
                break;
            case 23:
                setOU(s);
                break;
            case 24:
                this.isHomeBigger = (isBigger(getHOdds(), s));
                setHOdds(s);
                setIsHdpNew("1");
                break;
            case 25:
                this.isAwayBigger = (isBigger(getAOdds(), s));
                setAOdds(s);
                setIsHdpNew("1");
                break;
            case 26:
                this.isOverBigger = (isBigger(getOOdds(), s));
                setOOdds(s);
                setIsOUNew("1");
                break;
            case 27:
                this.isUnderBigger = (isBigger(getUOdds(), s));
                setUOdds(s);
                setIsOUNew("1");
                break;
            case 28:
                setOddOdds(s);
                setIsOENew("1");
                break;
            case 29:

                setEvenOdds(s);
                setIsOENew("1");
                break;
            case 30:
                setFt1Bigger(isBigger(X12_1Odds, s));
                setX12_1Odds(s);
                setIsX12_1New(1);
                setIsX12New("1");
                break;
            case 31:
                setFtXBigger(isBigger(X12_XOdds, s));
                setX12_XOdds(s);
                setIsX12_XNew(1);
                setIsX12New("1");
                break;
            case 32:
                setFt2Bigger(isBigger(X12_2Odds, s));
                setX12_2Odds(s);
                setIsX12_2New(1);
                setIsX12New("1");
                break;
            case 33:
                setHdpOdds(s);
                break;
            case 34:
                setOUOdds(s);
                break;
            case 35:
                setOEOdds(s);
                break;
            case 36:
                setHasPar(s);
                break;
            case 37:
                setIsInetBet_FH(s);
                break;
            case 38:
                setIsHomeGive_FH(s);
                break;
            case 39:
                setHdp_FH(s);
                break;
            case 40:
                setOU_FH(s);
                break;
            case 41:
                setHOdds_FH(s);
                setIsHdpNew_FH("1");
                break;
            case 42:
                setAOdds_FH(s);
                setIsHdpNew_FH("1");
                break;
            case 43:
                setOOdds_FH(s);
                setIsOUNew_FH("1");
                break;
            case 44:
                setUOdds_FH(s);
                setIsOUNew_FH("1");
                break;
            case 45:
                setOddOdds_FH(s);
                setIsOENew_FH("1");
                break;
            case 46:
                setEvenOdds_FH(s);
                setIsOENew_FH("1");
                break;
            case 47:
                setX12_1Odds_FH(s);
                break;
            case 48:
                setX12_XOdds_FH(s);
                break;
            case 49:
                setX12_2Odds_FH(s);
                break;
            case 50:
                setHdpOdds_FH(s);
                break;
            case 51:
                setOUOdds_FH(s);
                break;
            case 52:
                setOEOdds_FH(s);
                break;
            case 53:
                setStatsId(s);
                break;
            case 54:
                setMExtraTime(s);
                break;
            case 55:
                setWorkingDate(s);
                break;
            case 56:
                setTodayDate(s);
                break;
            case 57:
                setModuleOrder(s);
                break;
            case 58:
                setModuleId(s);
                break;
            case 59:
                setMatchCode(s);
                break;
            case 60:
                setSortNum(s);
                break;
            case 61:
                setGGID(s);
                break;
            case 62:
                setHasPar_FH(s);
                break;
        }
    }

    @Override
    public void setNotify(boolean notify) {
        super.setNotify(notify);
        if (notify) {
            setIsHdpNew("1");
            setIsOUNew("1");
            setIsOENew("1");
            setIsHdpNew_FH("1");
            setIsOUNew_FH("1");
            setIsOENew_FH("1");
            setIsX12New("1");
        } else {
            setIsHdpNew("0");
            setIsOUNew("0");
            setIsOENew("0");
            setIsHdpNew_FH("0");
            setIsOUNew_FH("0");
            setIsOENew_FH("0");
            setIsX12New("0");
        }
    }

    public void setTvPathIBC(String tvPathIBC) {
        this.tvPathIBC = tvPathIBC;
    }


    public void setRepeatRow(List<BallInfo> repeatRow) {
        this.repeatRow = repeatRow;
    }

    public List<BallInfo> getRepeatRow() {
        return repeatRow;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setIsHomeBigger(int isHomeBiger) {
        this.isHomeBigger = isHomeBiger;
    }

    public void setIsAwayBigger(int isAwayBigger) {
        this.isAwayBigger = isAwayBigger;
    }

    public void setIsOverBigger(int isOverBigger) {
        this.isOverBigger = isOverBigger;
    }

    public void setIsUnderBigger(int isUnderBigger) {
        this.isUnderBigger = isUnderBigger;
    }


    public boolean isHomeScoreBigger() {
        return isHomeScoreBigger;
    }

    public void setHomeScoreBigger(boolean homeScoreBigger) {
        isHomeScoreBigger = homeScoreBigger;
    }

    public boolean isAwayScoreBigger() {
        return isAwayScoreBigger;
    }

    public void setAwayScoreBigger(boolean awayScoreBigger) {
        isAwayScoreBigger = awayScoreBigger;
    }

    public void setIsX12_XNew(int isX12_XNew) {
        this.isX12_XNew = isX12_XNew;
    }

    public void setIsX12_1New(int isX12_1New) {
        this.isX12_1New = isX12_1New;
    }

    public void setIsX12_2New(int isX12_2New) {
        this.isX12_2New = isX12_2New;
    }

    public void setShowGoal(String showGoal) {
        this.showGoal = showGoal;
    }

    public void setIsHomeGoal(String isHomeGoal) {
        this.isHomeGoal = isHomeGoal;
    }

    String RunHomeScore_FH;
    String RunAwayScore_FH;

    public String getRunHomeScore_FH() {
        return RunHomeScore_FH;
    }

    public void setRunHomeScore_FH(String runHomeScore_FH) {
        RunHomeScore_FH = runHomeScore_FH;
    }

    public String getRunAwayScore_FH() {
        return RunAwayScore_FH;
    }

    public void setRunAwayScore_FH(String runAwayScore_FH) {
        RunAwayScore_FH = runAwayScore_FH;
    }
}
