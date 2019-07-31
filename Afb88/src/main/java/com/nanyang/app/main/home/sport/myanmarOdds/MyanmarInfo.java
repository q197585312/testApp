package com.nanyang.app.main.home.sport.myanmarOdds;

import com.nanyang.app.main.home.sport.model.BallInfo;

/**
 * Created by Administrator on 2017/3/11.
 */

public class MyanmarInfo extends BallInfo {
    String SocOddsId;         //全场ID
    String SocOddsId_FH;     //半场ID
    String Live;              //是否显示live
    String HomeId;            //主队id
    String AwayId;            //客队id
    String IsInFavourite;    //是否加入我的最爱
    String ScoreNew;          //IsScoreNew;		//有没有更新比数， 作闪闪图片用
    String IsLastCall;        //是否最后一次请求数据
    String MatchDate;         //比赛时间
    String Status;            //状态(开始或者还没开始)
    String CurMinute;         //比赛进行了多少时间
    String IsInetBet;         //允许或不允许成员下注
    String HasHdp;            //是否有hdp
    String HdpOdds;           //分出HomeHdpOdds，AOdds
    String IsHomeGive;        //是否主队让球
    String HomeRank;          //主队排名
    String Home;              //主队名
    String RCHome;            //主队红牌数
    String RTSMatchId;        //RTS livecast 图标所用
    String AwayRank;          //客队排名
    String Away;              //客队名
    String RCAway;            //客队红牌数
    String Hdp;               //hdp让球数
    String HOdds;       //主队hdp赔率
    String AOdds;       //客队hdp赔率
    String HasOU;             //是否有ou
    String OU;                //大小球
    String RunHomeScore;      //滚球主队的分数
    String RunAwayScore;      //滚球客队的分数
    String OOdds;          //over赔率
    String UOdds;         //under赔率
    String OUOdds;            //从中分出OverOdds，UOdds
    String HasHdp_FH;         //半场是否有hdp
    String Hdp_FH;            //半场hdp让球
    String IsHomeGive_FH;     //半场是否主队让球
    String HOdds_FH;    //半场主队hdp赔率
    String AOdds_FH;    //半场客队hdp赔率
    String HdpOdds_FH;        //分出HomeHdpOdds，AOdds
    String IsInetBet_FH;      //半场允许或不允许成员下注
    String HasOU_FH;          //半场是否有ou
    String OU_FH;             //半场大小球
    String RunHomeScore_FH;   //半场滚球主队的分数
    String RunAwayScore_FH;   //半场滚球客队的分数
    String OOdds_FH;       //半场over赔率
    String UOdds_FH;      //半场under赔率
    String OUOdds_FH;         //分出OverOdds_FH，UOdds_FH
    String StatsId;           //- statistic id
    String WorkingDate;       //比赛开始的时间
    String PreSocOddsId;
    String IsHideMM;
    String MMHdpPct;
    String MMOUPct;
    String MMHdp;
    String MMIsHomeGive;
    String MMHdpOdds;
    String MMOU;
    String MMOUOdds;
    String HasX12;
    String HasPar;
    String MExtraTime;

    @Override
    public String getHasPar() {
        return HasPar;
    }

    @Override
    public void setHasPar(String hasPar) {
        HasPar = hasPar;
    }

    @Override
    public String getMExtraTime() {
        return MExtraTime;
    }

    @Override
    public void setMExtraTime(String MExtraTime) {
        this.MExtraTime = MExtraTime;
    }


    String isHdpNew = "0";
    String isOUNew = "0";


    public String getIsHdpNew() {
        return isHdpNew;
    }


    public void setIsHdpNew(String isHdpNew) {
        this.isHdpNew = isHdpNew;
    }


    public String getIsOUNew() {
        return isOUNew;
    }


    public void setIsOUNew(String isOUNew) {
        this.isOUNew = isOUNew;
    }


    public String getSocOddsId() {
        return SocOddsId;
    }


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


    public String getIsInetBet() {
        return IsInetBet;
    }


    public void setIsInetBet(String isInetBet) {
        IsInetBet = isInetBet;
    }


    public void setHasHdp(String hasHdp) {
        HasHdp = hasHdp;
    }


    public String getHdpOdds() {
        return HdpOdds;
    }


    public void setHdpOdds(String hdpOdds) {
        HdpOdds = hdpOdds;
    }


    public String getIsHomeGive() {
        return IsHomeGive;
    }


    public void setIsHomeGive(String isHomeGive) {
        IsHomeGive = isHomeGive;
    }

    public String getHomeRank() {
        return HomeRank;
    }

    public void setHomeRank(String homeRank) {
        HomeRank = homeRank;
    }


    public String getHome() {
        return Home;
    }


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


    public String getAway() {
        return Away;
    }


    public void setAway(String away) {
        Away = away;
    }

    public String getRCAway() {
        return RCAway;
    }

    public void setRCAway(String RCAway) {
        this.RCAway = RCAway;
    }


    public String getHdp() {
        return Hdp;
    }


    public void setHdp(String hdp) {
        Hdp = hdp;
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


    public void setHasOU(String hasOU) {
        HasOU = hasOU;
    }


    public String getOU() {
        return OU;
    }


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


    public String getOUOdds() {
        return OUOdds;
    }


    public void setOUOdds(String OUOdds) {
        this.OUOdds = OUOdds;
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


    public String getPreSocOddsId() {
        return PreSocOddsId;
    }


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


    public void setHasX12(String hasX12) {
        HasX12 = hasX12;
    }

    public static class ParseInfo {

        public static int
                SocOddsId = 0,         //全场ID
                SocOddsId_FH = 1,      //半场ID
                Live = 2,              //是否显示live
                HomeId = 3,            //主队id
                AwayId = 4,            //客队id
                IsInFavourite = 5,     //是否加入我的最爱
                ScoreNew = 6,          //IsScoreNew;		//有没有更新比数， 作闪闪图片用
                IsLastCall = 7,        //是否最后一次请求数据
                MatchDate = 8,         //比赛时间
                Status = 9,            //状态(开始或者还没开始)
                CurMinute = 10,         //比赛进行了多少时间
                IsInetBet = 11,         //允许或不允许成员下注
                HasHdp = 12,            //是否有hdp
                HdpOdds = 13,           //分出HomeHdpOdds，AOdds
                IsHomeGive = 14,        //是否主队让球
                HomeRank = 15,          //主队排名
                Home = 16,              //主队名
                RCHome = 17,            //主队红牌数
                RTSMatchId = 18,        //RTS livecast 图标所用
                AwayRank = 19,          //客队排名
                Away = 20,              //客队名
                RCAway = 21,            //客队红牌数
                Hdp = 22,               //hdp让球数
                HomeHdpOdds = 23,       //主队hdp赔率
                AwayHdpOdds = 24,       //客队hdp赔率
                HasOU = 25,             //是否有ou
                OU = 26,                //大小球
                RunHomeScore = 27,      //滚球主队的分数
                RunAwayScore = 28,      //滚球客队的分数
                OverOdds = 29,          //over赔率
                UnderOdds = 30,         //under赔率
                OUOdds = 31,            //从中分出OverOdds，UOdds
                HasHdp_FH = 32,         //半场是否有hdp
                Hdp_FH = 33,            //半场hdp让球
                IsHomeGive_FH = 34,     //半场是否主队让球
                HomeHdpOdds_FH = 35,    //半场主队hdp赔率
                AwayHdpOdds_FH = 36,    //半场客队hdp赔率
                HdpOdds_FH = 37,        //分出HomeHdpOdds，AOdds
                IsInetBet_FH = 38,      //半场允许或不允许成员下注
                HasOU_FH = 39,          //半场是否有ou
                OU_FH = 40,             //半场大小球
                RunHomeScore_FH = 41,   //半场滚球主队的分数
                RunAwayScore_FH = 42,   //半场滚球客队的分数
                OverOdds_FH = 43,       //半场over赔率
                UnderOdds_FH = 44,      //半场under赔率
                OUOdds_FH = 45,         //分出OverOdds_FH，UOdds_FH
                StatsId = 46,           //- statistic id
                WorkingDate = 47,       //比赛开始的时间
                PreSocOddsId = 48,
                IsHideMM = 49,
                MMHdpPct = 50,
                MMOUPct = 51,
                MMHdp = 52,
                MMIsHomeGive = 53,
                MMHdpOdds = 54,
                MMOU = 55,
                MMOUOdds = 56,
                HasX12 = 57;
    }

    @Override
    public void setValue(int i, String s) {
        switch (i) {
            case 0:
                setSocOddsId(s);
                break;//全场ID
            case 1:
                setSocOddsId_FH(s);
                break;//半场ID
            case 2:
                setLive(s);
                break;//是否显示live
            case 3:
                setHomeId(s);
                break;//主队id
            case 4:
                setAwayId(s);
                break;//客队id
            case 5:
                setIsInFavourite(s);
                break;//是否加入我的最爱
            case 6:
                setScoreNew(s);
                break;//IsScoreNew;		//有没有更新比数， 作闪闪图片用
            case 7:
                setIsLastCall(s);
                break;//是否最后一次请求数据
            case 8:
                setMatchDate(s);
                break;//比赛时间
            case 9:
                setStatus(s);
                break;//状态(开始或者还没开始)
            case 10:
                setCurMinute(s);
                break;//比赛进行了多少时间
            case 11:
                setIsInetBet(s);
                break;//允许或不允许成员下注
            case 12:
                setHasHdp(s);
                break;//是否有hdp
            case 13:
                setHdpOdds(s);
                break;//分出HomeHdpOdds，AOdds
            case 14:
                setIsHomeGive(s);
                break;//是否主队让球
            case 15:
                setHomeRank(s);
                break;//主队排名
            case 16:
                setHome(s);
                break;//主队名
            case 17:
                setRCHome(s);
                break;//主队红牌数
            case 18:
                setRTSMatchId(s);
                break;//RTS livecast 图标所用
            case 19:
                setAwayRank(s);
                break;//客队排名
            case 20:
                setAway(s);
                break;//客队名
            case 21:
                setRCAway(s);
                break;//客队红牌数
            case 22:
                setHdp(s);
                break;//hdp让球数
            case 23:
                setHOdds(s);
                setIsHdpNew("1");
                break;//主队hdp赔率
            case 24:
                setAOdds(s);
                setIsHdpNew("1");
                break;//客队hdp赔率
            case 25:
                setHasOU(s);
                break;//是否有ou
            case 26:
                setOU(s);
                break;//大小球
            case 27:
                setRunHomeScore(s);
                break;//滚球主队的分数
            case 28:
                setRunAwayScore(s);
                break;//滚球客队的分数
            case 29:
                setOOdds(s);
                setIsOUNew("1");
                break;//over赔率
            case 30:
                setUOdds(s);
                setIsOUNew("1");
                break;//under赔率
            case 31:
                setOUOdds(s);
                break;//从中分出OverOdds，UOdds
            case 32:
                setHasHdp_FH(s);
                break;//半场是否有hdp
            case 33:
                setHdp_FH(s);
                break;//半场hdp让球
            case 34:
                setIsHomeGive_FH(s);
                break;//半场是否主队让球
            case 35:
                setHOdds_FH(s);
                setIsHdpNew_FH("1");
                break;//半场主队hdp赔率
            case 36:
                setAOdds_FH(s);
                setIsHdpNew_FH("1");
                break;//半场客队hdp赔率
            case 37:
                setHdpOdds_FH(s);
                break;//分出HomeHdpOdds，AOdds
            case 38:
                setIsInetBet_FH(s);
                break;//半场允许或不允许成员下注
            case 39:
                setHasOU_FH(s);
                break;//半场是否有ou
            case 40:
                setOU_FH(s);
                break;//半场大小球
            case 41:
                setRunHomeScore_FH(s);
                break;//半场滚球主队的分数
            case 42:
                setRunAwayScore_FH(s);
                break;//半场滚球客队的分数
            case 43:
                setOOdds_FH(s);
                setIsOUNew_FH("1");
                break;//半场over赔率
            case 44:
                setUOdds_FH(s);
                setIsOUNew_FH("1");
                break;//半场under赔率
            case 45:
                setOUOdds_FH(s);
                break;//分出OverOdds_FH，UOdds_FH
            case 46:
                setStatsId(s);
                break;//- statistic id
            case 47:
                setWorkingDate(s);
                break;//比赛开始的时间
            case 48:
                setPreSocOddsId(s);
                break;
            case 49:
                setIsHideMM(s);
                break;
            case 50:
                setMMHdpPct(s);
                break;
            case 51:
                setMMOUPct(s);
                break;
            case 52:
                setMMHdp(s);
                break;
            case 53:
                setMMIsHomeGive(s);
                break;
            case 54:
                setMMHdpOdds(s);
                break;
            case 55:
                setMMOU(s);
                break;
            case 56:
                setMMOUOdds(s);
                break;
            case 57:
                setHasX12(s);
                break;
            case 58:
                setHasPar(s);
                break;
            case 59:
                setMExtraTime(s);
                break;
        }
    }


}
