package com.nanyang.app.main.home.sport.myanmarOdds;

import com.nanyang.app.main.home.sport.model.BallInfo;

/**
 * Created by Administrator on 2017/3/11.
 */

public class MyanmarInfo extends BallInfo {
    String  SocOddsId,         //全场ID
            SocOddsId_FH,     //半场ID
            Live,              //是否显示live
            HomeId,            //主队id
            AwayId,            //客队id
            IsInFavourite ,    //是否加入我的最爱
            ScoreNew,          //IsScoreNew;		//有没有更新比数， 作闪闪图片用
            IsLastCall,        //是否最后一次请求数据
            MatchDate,         //比赛时间
            Status,            //状态(开始或者还没开始)
            CurMinute,         //比赛进行了多少时间
            IsInetBet,         //允许或不允许成员下注
            HasHdp,            //是否有hdp
            HdpOdds,           //分出HomeHdpOdds，AwayHdpOdds
            IsHomeGive,        //是否主队让球
            HomeRank,          //主队排名
            Home,              //主队名
            RCHome,            //主队红牌数
            RTSMatchId,        //RTS livecast 图标所用
            AwayRank,          //客队排名
            Away,              //客队名
            RCAway,            //客队红牌数
            Hdp,               //hdp让球数
            HomeHdpOdds,       //主队hdp赔率
            AwayHdpOdds,       //客队hdp赔率
            HasOU,             //是否有ou
            OU,                //大小球
            RunHomeScore,      //滚球主队的分数
            RunAwayScore,      //滚球客队的分数
            OverOdds,          //over赔率
            UnderOdds,         //under赔率
            OUOdds,            //从中分出OverOdds，UnderOdds
            HasHdp_FH,         //半场是否有hdp
            Hdp_FH,            //半场hdp让球
            IsHomeGive_FH,     //半场是否主队让球
            HomeHdpOdds_FH,    //半场主队hdp赔率
            AwayHdpOdds_FH,    //半场客队hdp赔率
            HdpOdds_FH,        //分出HomeHdpOdds，AwayHdpOdds
            IsInetBet_FH,      //半场允许或不允许成员下注
            HasOU_FH,          //半场是否有ou
            OU_FH,             //半场大小球
            RunHomeScore_FH,   //半场滚球主队的分数
            RunAwayScore_FH,   //半场滚球客队的分数
            OverOdds_FH,       //半场over赔率
            UnderOdds_FH,      //半场under赔率
            OUOdds_FH,         //分出OverOdds_FH，UnderOdds_FH
            StatsId,           //- statistic id
            WorkingDate,       //比赛开始的时间
            PreSocOddsId,
            IsHideMM,
            MMHdpPct,
            MMOUPct,
            MMHdp,
            MMIsHomeGive,
            MMHdpOdds,
            MMOU,
            MMOUOdds,
            HasX12;

    String isHdpNew="0"; String isOUNew="0";

    @Override
    public String getIsHdpNew() {
        return isHdpNew;
    }

    @Override
    public void setIsHdpNew(String isHdpNew) {
        this.isHdpNew = isHdpNew;
    }

    @Override
    public String getIsOUNew() {
        return isOUNew;
    }

    @Override
    public void setIsOUNew(String isOUNew) {
        this.isOUNew = isOUNew;
    }

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

    public String getIsHideMM() {
        return IsHideMM;
    }

    public void setIsHideMM(String isHideMM) {
        IsHideMM = isHideMM;
    }

    public String getMMHdpPct() {
        return MMHdpPct;
    }

    public void setMMHdpPct(String MMHdpPct) {
        this.MMHdpPct = MMHdpPct;
    }

    public String getMMOUPct() {
        return MMOUPct;
    }

    public void setMMOUPct(String MMOUPct) {
        this.MMOUPct = MMOUPct;
    }

    public String getMMHdp() {
        return MMHdp;
    }

    public void setMMHdp(String MMHdp) {
        this.MMHdp = MMHdp;
    }

    public String getMMIsHomeGive() {
        return MMIsHomeGive;
    }

    public void setMMIsHomeGive(String MMIsHomeGive) {
        this.MMIsHomeGive = MMIsHomeGive;
    }

    public String getMMHdpOdds() {
        return MMHdpOdds;
    }

    public void setMMHdpOdds(String MMHdpOdds) {
        this.MMHdpOdds = MMHdpOdds;
    }

    public String getMMOU() {
        return MMOU;
    }

    public void setMMOU(String MMOU) {
        this.MMOU = MMOU;
    }

    public String getMMOUOdds() {
        return MMOUOdds;
    }

    public void setMMOUOdds(String MMOUOdds) {
        this.MMOUOdds = MMOUOdds;
    }

    @Override
    public String getHasX12() {
        return HasX12;
    }

    @Override
    public void setHasX12(String hasX12) {
        HasX12 = hasX12;
    }

    public static class ParseInfo {

        public static int
        SocOddsId=0,         //全场ID
        SocOddsId_FH=1,      //半场ID
        Live=2,              //是否显示live
        HomeId=3,            //主队id
        AwayId=4,            //客队id
        IsInFavourite=5,     //是否加入我的最爱
        ScoreNew=6,          //IsScoreNew;		//有没有更新比数， 作闪闪图片用
        IsLastCall=7,        //是否最后一次请求数据
        MatchDate=8,         //比赛时间
        Status=9,            //状态(开始或者还没开始)
        CurMinute=10,         //比赛进行了多少时间
        IsInetBet=11,         //允许或不允许成员下注
        HasHdp=12,            //是否有hdp
        HdpOdds=13,           //分出HomeHdpOdds，AwayHdpOdds
        IsHomeGive=14,        //是否主队让球
        HomeRank=15,          //主队排名
        Home=16,              //主队名
        RCHome=17,            //主队红牌数
        RTSMatchId=18,        //RTS livecast 图标所用
        AwayRank=19,          //客队排名
        Away=20,              //客队名
        RCAway=21,            //客队红牌数
        Hdp=22,               //hdp让球数
        HomeHdpOdds=23,       //主队hdp赔率
        AwayHdpOdds=24,       //客队hdp赔率
        HasOU=25,             //是否有ou
        OU=26,                //大小球
        RunHomeScore=27,      //滚球主队的分数
        RunAwayScore=28,      //滚球客队的分数
        OverOdds=29,          //over赔率
        UnderOdds=30,         //under赔率
        OUOdds=31,            //从中分出OverOdds，UnderOdds
        HasHdp_FH=32,         //半场是否有hdp
        Hdp_FH=33,            //半场hdp让球
        IsHomeGive_FH=34,     //半场是否主队让球
        HomeHdpOdds_FH=35,    //半场主队hdp赔率
        AwayHdpOdds_FH=36,    //半场客队hdp赔率
        HdpOdds_FH=37,        //分出HomeHdpOdds，AwayHdpOdds
        IsInetBet_FH=38,      //半场允许或不允许成员下注
        HasOU_FH=39,          //半场是否有ou
        OU_FH=40,             //半场大小球
        RunHomeScore_FH=41,   //半场滚球主队的分数
        RunAwayScore_FH=42,   //半场滚球客队的分数
        OverOdds_FH=43,       //半场over赔率
        UnderOdds_FH=44,      //半场under赔率
        OUOdds_FH=45,         //分出OverOdds_FH，UnderOdds_FH
        StatsId=46,           //- statistic id
        WorkingDate=47,       //比赛开始的时间
        PreSocOddsId=48,
        IsHideMM=49,
        MMHdpPct=50,
        MMOUPct=51,
        MMHdp=52,
        MMIsHomeGive=53,
        MMHdpOdds=54,
        MMOU=55,
        MMOUOdds=56,
        HasX12=57;
    }


}
