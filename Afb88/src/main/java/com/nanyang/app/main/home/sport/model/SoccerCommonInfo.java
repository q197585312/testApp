package com.nanyang.app.main.home.sport.model;

import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;

/**
 * Created by Administrator on 2017/3/11.
 */
/*



        ,'7'
        ,'欧克莱卡诺'
        ,0
        ,827493
        ,'10'
        ,'墨尔本港鲨鱼'
        ,0
        ,0.75
        ,8
        ,11.1
        ,1
        ,3
        ,0
        ,0
        ,10.2
        ,8.6
        ,-8.6
        ,1
        ,0.25
        ,1
        ,8
        ,10.8
        ,9.2
        ,1
        ,1
        ,1.25
        ,0
        ,0
        ,10.7
        ,8.1
        ,-8.1
        ,463647411
        ,"06/02/2017"
        ,12845999
        ,1
        ,1.62
        ,4.6
        ,3.74
        ,1
        ,2.12
        ,4.72
        ,2.3
        ,0
        ,0
        ,0
        ,0
        ,0
        ,0
        ,1*/
public class SoccerCommonInfo extends BallInfo  implements IRTMatchInfo {
    String SocOddsId,
            SocOddsId_FH,
            Live,
            HomeId,
            AwayId,
            IsInFavourite,
            ScoreNew,
            IsLastCall,
            MatchDate,
            Status,
            CurMinute,
            IsInetBet,
            HasHdp,
            HdpOdds,
            IsHomeGive,
            HomeRank,
            Home,
            RCHome,
            RTSMatchId,
            AwayRank,
            Away,
            RCAway,
            Hdp,
            HomeHdpOdds,
            AwayHdpOdds,
            HasOU,
            OU,
            RunHomeScore,
            RunAwayScore,
            OverOdds,
            UnderOdds,
            OUOdds,
            HasHdp_FH,
            Hdp_FH,
            IsHomeGive_FH,
            HomeHdpOdds_FH,
            AwayHdpOdds_FH,
            HdpOdds_FH,
            IsInetBet_FH,
            HasOU_FH,
            OU_FH,
            RunHomeScore_FH,
            RunAwayScore_FH,
            OverOdds_FH,
            UnderOdds_FH,
            OUOdds_FH,
            StatsId,
            WorkingDate,
            PreSocOddsId,
            HasX12,
            X12_1Odds,
            X12_2Odds,
            X12_XOdds,
            HasX12_FH,
            X12_1Odds_FH,
            X12_2Odds_FH,
            X12_XOdds_FH,
            IsX12New,
            IsX12New_FH,
            IsHdpNew,
            IsOUNew,
            IsHdpNew_FH,
            IsOUNew_FH,
            FirstOdds;

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

    @Override
    public String getLive() {
        return Live;
    }

    @Override
    public void setLive(String live) {
        Live = live;
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

    public String getIsInFavourite() {
        return IsInFavourite;
    }

    public void setIsInFavourite(String isInFavourite) {
        IsInFavourite = isInFavourite;
    }

    public String getScoreNew() {
        return ScoreNew;
    }

    public void setScoreNew(String scoreNew) {
        ScoreNew = scoreNew;
    }

    @Override
    public String getIsLastCall() {
        return IsLastCall;
    }

    @Override
    public void setIsLastCall(String isLastCall) {
        IsLastCall = isLastCall;
    }

    @Override
    public String getMatchDate() {
        return MatchDate;
    }

    @Override
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

    @Override
    public String getHasHdp() {
        return HasHdp;
    }

    @Override
    public void setHasHdp(String hasHdp) {
        HasHdp = hasHdp;
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
    public String getIsHomeGive() {
        return IsHomeGive;
    }

    @Override
    public void setIsHomeGive(String isHomeGive) {
        IsHomeGive = isHomeGive;
    }

    public String getHomeRank() {
        return HomeRank;
    }

    public void setHomeRank(String homeRank) {
        HomeRank = homeRank;
    }

    @Override
    public String getHome() {
        return Home;
    }

    @Override
    public void setHome(String home) {
        Home = home;
    }

    public String getRCHome() {
        return RCHome;
    }

    public void setRCHome(String RCHome) {
        this.RCHome = RCHome;
    }

    public String getRTSMatchId() {
        return RTSMatchId;
    }

    public void setRTSMatchId(String RTSMatchId) {
        this.RTSMatchId = RTSMatchId;
    }

    public String getAwayRank() {
        return AwayRank;
    }

    public void setAwayRank(String awayRank) {
        AwayRank = awayRank;
    }

    @Override
    public String getAway() {
        return Away;
    }

    @Override
    public void setAway(String away) {
        Away = away;
    }

    public String getRCAway() {
        return RCAway;
    }

    public void setRCAway(String RCAway) {
        this.RCAway = RCAway;
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

    @Override
    public String getOUOdds() {
        return OUOdds;
    }

    @Override
    public void setOUOdds(String OUOdds) {
        this.OUOdds = OUOdds;
    }

    public String getHasHdp_FH() {
        return HasHdp_FH;
    }

    public void setHasHdp_FH(String hasHdp_FH) {
        HasHdp_FH = hasHdp_FH;
    }

    public String getHdp_FH() {
        return Hdp_FH;
    }

    public void setHdp_FH(String hdp_FH) {
        Hdp_FH = hdp_FH;
    }

    public String getIsHomeGive_FH() {
        return IsHomeGive_FH;
    }

    public void setIsHomeGive_FH(String isHomeGive_FH) {
        IsHomeGive_FH = isHomeGive_FH;
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

    public String getHdpOdds_FH() {
        return HdpOdds_FH;
    }

    public void setHdpOdds_FH(String hdpOdds_FH) {
        HdpOdds_FH = hdpOdds_FH;
    }

    public String getIsInetBet_FH() {
        return IsInetBet_FH;
    }

    public void setIsInetBet_FH(String isInetBet_FH) {
        IsInetBet_FH = isInetBet_FH;
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

    public String getOUOdds_FH() {
        return OUOdds_FH;
    }

    public void setOUOdds_FH(String OUOdds_FH) {
        this.OUOdds_FH = OUOdds_FH;
    }

    public String getStatsId() {
        return StatsId;
    }

    public void setStatsId(String statsId) {
        StatsId = statsId;
    }

    public String getWorkingDate() {
        return WorkingDate;
    }

    public void setWorkingDate(String workingDate) {
        WorkingDate = workingDate;
    }

    @Override
    public String getPreSocOddsId() {
        return PreSocOddsId;
    }

    @Override
    public void setPreSocOddsId(String preSocOddsId) {
        PreSocOddsId = preSocOddsId;
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
        return X12_1Odds;
    }

    @Override
    public void setX12_1Odds(String x12_1Odds) {
        X12_1Odds = x12_1Odds;
    }

    @Override
    public String getX12_2Odds() {
        return X12_2Odds;
    }

    @Override
    public void setX12_2Odds(String x12_2Odds) {
        X12_2Odds = x12_2Odds;
    }

    @Override
    public String getX12_XOdds() {
        return X12_XOdds;
    }

    @Override
    public void setX12_XOdds(String x12_XOdds) {
        X12_XOdds = x12_XOdds;
    }

    public String getHasX12_FH() {
        return HasX12_FH;
    }

    public void setHasX12_FH(String hasX12_FH) {
        HasX12_FH = hasX12_FH;
    }

    public String getX12_1Odds_FH() {
        return X12_1Odds_FH;
    }

    public void setX12_1Odds_FH(String x12_1Odds_FH) {
        X12_1Odds_FH = x12_1Odds_FH;
    }

    public String getX12_2Odds_FH() {
        return X12_2Odds_FH;
    }

    public void setX12_2Odds_FH(String x12_2Odds_FH) {
        X12_2Odds_FH = x12_2Odds_FH;
    }

    public String getX12_XOdds_FH() {
        return X12_XOdds_FH;
    }

    public void setX12_XOdds_FH(String x12_XOdds_FH) {
        X12_XOdds_FH = x12_XOdds_FH;
    }

    @Override
    public String getIsX12New() {
        return IsX12New;
    }

    @Override
    public void setIsX12New(String isX12New) {
        IsX12New = isX12New;
    }

    public String getIsX12New_FH() {
        return IsX12New_FH;
    }

    public void setIsX12New_FH(String isX12New_FH) {
        IsX12New_FH = isX12New_FH;
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

    public String getIsHdpNew_FH() {
        return IsHdpNew_FH;
    }

    public void setIsHdpNew_FH(String isHdpNew_FH) {
        IsHdpNew_FH = isHdpNew_FH;
    }

    public String getIsOUNew_FH() {
        return IsOUNew_FH;
    }

    public void setIsOUNew_FH(String isOUNew_FH) {
        IsOUNew_FH = isOUNew_FH;
    }

    public String getFirstOdds() {
        return FirstOdds;
    }

    public void setFirstOdds(String firstOdds) {
        FirstOdds = firstOdds;
    }
    public static class ParseInfo {
        public static int SocOddsId = 0,
                SocOddsId_FH = 1,
                Live = 2,
                HomeId = 3,
                AwayId = 4,
                IsInFavourite = 5,
                ScoreNew = 6,
                IsLastCall = 7,
                MatchDate = 8,
                Status = 9,
                CurMinute = 10,
                IsInetBet = 11,
                HasHdp = 12,
                HdpOdds = 13,
                IsHomeGive = 14,
                HomeRank = 15,
                Home = 16,
                RCHome = 17,
                RTSMatchId = 18,
                AwayRank = 19,
                Away = 20,
                RCAway = 21,
                Hdp = 22,
                HomeHdpOdds = 23,
                AwayHdpOdds = 24,
                HasOU = 25,
                OU = 26,
                RunHomeScore = 27,
                RunAwayScore = 28,
                OverOdds = 29,
                UnderOdds = 30,
                OUOdds = 31,
                HasHdp_FH = 32,
                Hdp_FH = 33,
                IsHomeGive_FH = 34,
                HomeHdpOdds_FH = 35,
                AwayHdpOdds_FH = 36,
                HdpOdds_FH = 37,
                IsInetBet_FH = 38,
                HasOU_FH = 39,
                OU_FH = 40,
                RunHomeScore_FH = 41,
                RunAwayScore_FH = 42,
                OverOdds_FH = 43,
                UnderOdds_FH = 44,
                OUOdds_FH = 45,
                StatsId = 46,
                WorkingDate = 47,
                PreSocOddsId = 48,
                HasX12 = 49,
                X12_1Odds = 50,
                X12_2Odds = 51,
                X12_XOdds = 52,
                HasX12_FH = 53,
                X12_1Odds_FH = 54,
                X12_2Odds_FH = 55,
                X12_XOdds_FH = 56,
                IsX12New = 57,
                IsX12New_FH = 58,
                IsHdpNew = 59,
                IsOUNew = 60,
                IsHdpNew_FH = 61,
                IsOUNew_FH = 62,
                FirstOdds = 63;
    }


}
