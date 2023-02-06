package nanyang.com.dig88.Table.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/10/30.
 */
public class TableDataBean implements Serializable {

    /**
     * RId : 2425
     * OldRId : 0
     * IsRedraw : true
     * Betable : true
     * Param : 'RMOdds1Gen.aspx?ot=t&ov=0&mt=0&tf=-1&update=true&r=2131949515', 45
     * Ot : t
     * Mt : 0
     * Wd :
     * Ia : 0
     * Pn : 1
     * Delay : 0
     * MouseOverColor : #fff6cf
     * MouseOutColor : #bbdede
     * AltMouseOutColor : #ccddff
     * RunMouseOutColor : #FFDDCC
     * AltRunMouseOutColor : #FFDDCC
     * JSOdds_EM : []
     * JSOdds : []
     * JSHidMod : []
     * tf : -1
     * tfDate : 2015/10/29 23:00
     * RMStatus : 0
     * RMOrder : 1
     * ORId : 2
     * Key : 3
     * MKey : 4
     * SocOddsId : 5
     * SocOddsId_FH : 6
     * ModuleId : 7
     * ModuleTitle : 8
     * GameType2 : 9
     * IsScoreNew : 10
     * Channel : 11
     * RTSMatchId : 12
     * HasRunning : 13
     * IsRun : 14
     * Live : 15
     * MatchDate : 16      //比赛时间 ≈"10:00AM",
     * HomeId : 17
     * AwayId : 18
     * IsHomeGive : 19
     * IsHomeGive_FH : 20
     * IsInetBet : 21
     * IsInetBet_FH : 22
     * WorkingDate : 23   //比赛开始的时间
     * IsLastCall : 24
     * Status : 25
     * CurMinute : 26
     * IsInFavourite : 27
     * FavId : 28
     * HomeRank : 29
     * AwayRank : 30
     * Home : 31
     * Away : 32
     * HasHdp : 33
     * HdpOdds : 34
     * Hdp : 35
     * RCHome : 36
     * RCAway : 37
     * IsHdpNew : 38
     * HomeHdpOdds : 39
     * AwayHdpOdds : 40
     * IsOUNew : 41
     * HasOU : 42
     * OU : 43
     * RunHomeScore : 44
     * RunAwayScore : 45
     * OverOdds : 46
     * UnderOdds : 47
     * OUOdds : 48
     * IsHdpNew_FH : 49
     * HasHdp_FH : 50
     * Hdp_FH : 51
     * HdpOdds_FH : 52
     * HomeHdpOdds_FH : 53
     * AwayHdpOdds_FH : 54
     * IsOUNew_FH : 55
     * HasOU_FH : 56
     * OU_FH : 57
     * RunHomeScore_FH : 58
     * RunAwayScore_FH : 59
     * OverOdds_FH : 60
     * UnderOdds_FH : 61
     * OUOdds_FH : 62
     * StatsId : 63
     * ServerHome : 64
     * ServerAway : 65
     * ServerModuleTitle : 66
     * Lang : 67
     * TodayDate : 68
     * EventKey : 69
     * PrevKey : 70
     * JSStatus : 71
     */

    @SerializedName("HasX12")
    private int HasX12;
    @SerializedName("IsX12New")
    private int IsX12New;
    @SerializedName("X12_1Odds")
    private int X121Odds;
    @SerializedName("X12_2Odds")
    private int X122Odds;
    @SerializedName("X12_XOdds")
    private int X12XOdds;
    @SerializedName("HasOE")
    private int HasOE;
    @SerializedName("IsOENew")
    private int IsOENew;
    @SerializedName("OEOdds")
    private int OEOdds;
    @SerializedName("OddOdds")
    private int OddOdds;
    @SerializedName("EvenOdds")
    private int EvenOdds;
    @SerializedName("HasX12_FH")
    private int HasX12FH;
    @SerializedName("IsX12New_FH")
    private int IsX12NewFH;
    @SerializedName("X12_1Odds_FH")
    private int X121OddsFH;
    @SerializedName("X12_2Odds_FH")
    private int X122OddsFH;
    @SerializedName("X12_XOdds_FH")
    private int X12XOddsFH;


    private int RId;
    private int OldRId;
    private boolean IsRedraw;
    private boolean Betable;
    private String Param;
    private String Ot;
    private int Mt;
    private String Wd;
    private String Ia;
    private int Pn;
    private int Delay;
    private String MouseOverColor;
    private String MouseOutColor;
    private String AltMouseOutColor;
    private String RunMouseOutColor;
    private String AltRunMouseOutColor;
    private int tf;
    private String tfDate;
    private int RMStatus;
    private int RMOrder;
    private int ORId;
    private int Key;
    private int MKey;
    private int SocOddsId;
    private int SocOddsId_FH;
    private int ModuleId;
    private int ModuleTitle;
    private int GameType2;
    private int IsScoreNew;
    private int Channel;
    private int RTSMatchId;
    private int HasRunning;
    private int IsRun;
    private int Live;
    private int MatchDate;
    private int HomeId;
    private int AwayId;
    private int IsHomeGive;
    private int IsHomeGive_FH;
    private int IsInetBet;
    private int IsInetBet_FH;
    private int WorkingDate;
    private int IsLastCall;
    private int Status;
    private int CurMinute;
    private int IsInFavourite;
    private int FavId;
    private int HomeRank;
    private int AwayRank;
    private int Home;
    private int Away;
    private int HasHdp;
    private int HdpOdds;
    private int Hdp;
    private int RCHome;
    private int RCAway;
    private int IsHdpNew;
    private int HomeHdpOdds;
    private int AwayHdpOdds;
    private int IsOUNew;
    private int HasOU;
    private int OU;
    private int RunHomeScore;
    private int RunAwayScore;
    private int OverOdds;
    private int UnderOdds;
    private int OUOdds;
    private int IsHdpNew_FH;
    private int HasHdp_FH;
    private int Hdp_FH;
    private int HdpOdds_FH;
    private int HomeHdpOdds_FH;
    private int AwayHdpOdds_FH;
    private int IsOUNew_FH;
    private int HasOU_FH;
    private int OU_FH;
    private int RunHomeScore_FH;
    private int RunAwayScore_FH;
    private int OverOdds_FH;
    private int UnderOdds_FH;
    private int OUOdds_FH;
    private int StatsId;
    private int ServerHome;
    private int ServerAway;
    private int ServerModuleTitle;
    private int Lang;
    private int TodayDate;
    private int EventKey;
    private int PrevKey;
    private int JSStatus;
    private List<List<String>> JSOdds;

    public int getEvenOdds() {
        return EvenOdds;
    }

    public void setEvenOdds(int evenOdds) {
        EvenOdds = evenOdds;
    }

    public int getX12XOddsFH() {
        return X12XOddsFH;
    }

    public void setX12XOddsFH(int x12XOddsFH) {
        X12XOddsFH = x12XOddsFH;
    }

    public int getHasOE() {
        return HasOE;
    }

    public void setHasOE(int hasOE) {
        HasOE = hasOE;
    }

    public int getHasX12() {
        return HasX12;
    }

    public void setHasX12(int hasX12) {
        HasX12 = hasX12;
    }

    public int getHasX12FH() {
        return HasX12FH;
    }

    public void setHasX12FH(int hasX12FH) {
        HasX12FH = hasX12FH;
    }

    public int getIsOENew() {
        return IsOENew;
    }

    public void setIsOENew(int isOENew) {
        IsOENew = isOENew;
    }

    public boolean isRedraw() {
        return IsRedraw;
    }

    public void setRedraw(boolean redraw) {
        IsRedraw = redraw;
    }

    public int getIsX12New() {
        return IsX12New;
    }

    public void setIsX12New(int isX12New) {
        IsX12New = isX12New;
    }

    public int getIsX12NewFH() {
        return IsX12NewFH;
    }

    public void setIsX12NewFH(int isX12NewFH) {
        IsX12NewFH = isX12NewFH;
    }

    public int getOddOdds() {
        return OddOdds;
    }

    public void setOddOdds(int oddOdds) {
        OddOdds = oddOdds;
    }

    public int getOEOdds() {
        return OEOdds;
    }

    public void setOEOdds(int OEOdds) {
        this.OEOdds = OEOdds;
    }

    public int getX121Odds() {
        return X121Odds;
    }

    public void setX121Odds(int x121Odds) {
        X121Odds = x121Odds;
    }

    public int getX121OddsFH() {
        return X121OddsFH;
    }

    public void setX121OddsFH(int x121OddsFH) {
        X121OddsFH = x121OddsFH;
    }

    public int getX122Odds() {
        return X122Odds;
    }

    public void setX122Odds(int x122Odds) {
        X122Odds = x122Odds;
    }

    public int getX122OddsFH() {
        return X122OddsFH;
    }

    public void setX122OddsFH(int x122OddsFH) {
        X122OddsFH = x122OddsFH;
    }

    public int getX12XOdds() {
        return X12XOdds;
    }

    public void setX12XOdds(int x12XOdds) {
        X12XOdds = x12XOdds;
    }

    public int getRId() {
        return RId;
    }

    public void setRId(int RId) {
        this.RId = RId;
    }

    public int getOldRId() {
        return OldRId;
    }

    public void setOldRId(int OldRId) {
        this.OldRId = OldRId;
    }

    public boolean isIsRedraw() {
        return IsRedraw;
    }

    public void setIsRedraw(boolean IsRedraw) {
        this.IsRedraw = IsRedraw;
    }

    public boolean isBetable() {
        return Betable;
    }

    public void setBetable(boolean Betable) {
        this.Betable = Betable;
    }

    public String getParam() {
        return Param;
    }

    public void setParam(String Param) {
        this.Param = Param;
    }

    public String getOt() {
        return Ot;
    }

    public void setOt(String Ot) {
        this.Ot = Ot;
    }

    public int getMt() {
        return Mt;
    }

    public void setMt(int Mt) {
        this.Mt = Mt;
    }

    public String getWd() {
        return Wd;
    }

    public void setWd(String Wd) {
        this.Wd = Wd;
    }

    public String getIa() {
        return Ia;
    }

    public void setIa(String Ia) {
        this.Ia = Ia;
    }

    public int getPn() {
        return Pn;
    }

    public void setPn(int Pn) {
        this.Pn = Pn;
    }

    public int getDelay() {
        return Delay;
    }

    public void setDelay(int Delay) {
        this.Delay = Delay;
    }

    public String getMouseOverColor() {
        return MouseOverColor;
    }

    public void setMouseOverColor(String MouseOverColor) {
        this.MouseOverColor = MouseOverColor;
    }

    public String getMouseOutColor() {
        return MouseOutColor;
    }

    public void setMouseOutColor(String MouseOutColor) {
        this.MouseOutColor = MouseOutColor;
    }

    public String getAltMouseOutColor() {
        return AltMouseOutColor;
    }

    public void setAltMouseOutColor(String AltMouseOutColor) {
        this.AltMouseOutColor = AltMouseOutColor;
    }

    public String getRunMouseOutColor() {
        return RunMouseOutColor;
    }

    public void setRunMouseOutColor(String RunMouseOutColor) {
        this.RunMouseOutColor = RunMouseOutColor;
    }

    public String getAltRunMouseOutColor() {
        return AltRunMouseOutColor;
    }

    public void setAltRunMouseOutColor(String AltRunMouseOutColor) {
        this.AltRunMouseOutColor = AltRunMouseOutColor;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    public String getTfDate() {
        return tfDate;
    }

    public void setTfDate(String tfDate) {
        this.tfDate = tfDate;
    }

    public int getRMStatus() {
        return RMStatus;
    }

    public void setRMStatus(int RMStatus) {
        this.RMStatus = RMStatus;
    }

    public int getRMOrder() {
        return RMOrder;
    }

    public void setRMOrder(int RMOrder) {
        this.RMOrder = RMOrder;
    }

    public int getORId() {
        return ORId;
    }

    public void setORId(int ORId) {
        this.ORId = ORId;
    }

    public int getKey() {
        return Key;
    }

    public void setKey(int Key) {
        this.Key = Key;
    }

    public int getMKey() {
        return MKey;
    }

    public void setMKey(int MKey) {
        this.MKey = MKey;
    }

    public int getSocOddsId() {
        return SocOddsId;
    }

    public void setSocOddsId(int SocOddsId) {
        this.SocOddsId = SocOddsId;
    }

    public int getSocOddsId_FH() {
        return SocOddsId_FH;
    }

    public void setSocOddsId_FH(int SocOddsId_FH) {
        this.SocOddsId_FH = SocOddsId_FH;
    }

    public int getModuleId() {
        return ModuleId;
    }

    public void setModuleId(int ModuleId) {
        this.ModuleId = ModuleId;
    }

    public int getModuleTitle() {
        return ModuleTitle;
    }

    public void setModuleTitle(int ModuleTitle) {
        this.ModuleTitle = ModuleTitle;
    }

    public int getGameType2() {
        return GameType2;
    }

    public void setGameType2(int GameType2) {
        this.GameType2 = GameType2;
    }

    public int getIsScoreNew() {
        return IsScoreNew;
    }

    public void setIsScoreNew(int IsScoreNew) {
        this.IsScoreNew = IsScoreNew;
    }

    public int getChannel() {
        return Channel;
    }

    public void setChannel(int Channel) {
        this.Channel = Channel;
    }

    public int getRTSMatchId() {
        return RTSMatchId;
    }

    public void setRTSMatchId(int RTSMatchId) {
        this.RTSMatchId = RTSMatchId;
    }

    public int getHasRunning() {
        return HasRunning;
    }

    public void setHasRunning(int HasRunning) {
        this.HasRunning = HasRunning;
    }

    public int getIsRun() {
        return IsRun;
    }

    public void setIsRun(int IsRun) {
        this.IsRun = IsRun;
    }

    public int getLive() {
        return Live;
    }

    public void setLive(int Live) {
        this.Live = Live;
    }

    /**
     * 比赛时间 am 10:00
     */
    public int getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(int MatchDate) {
        this.MatchDate = MatchDate;
    }

    public int getHomeId() {
        return HomeId;
    }

    public void setHomeId(int HomeId) {
        this.HomeId = HomeId;
    }

    public int getAwayId() {
        return AwayId;
    }

    public void setAwayId(int AwayId) {
        this.AwayId = AwayId;
    }

    public int getIsHomeGive() {
        return IsHomeGive;
    }

    public void setIsHomeGive(int IsHomeGive) {
        this.IsHomeGive = IsHomeGive;
    }

    public int getIsHomeGive_FH() {
        return IsHomeGive_FH;
    }

    public void setIsHomeGive_FH(int IsHomeGive_FH) {
        this.IsHomeGive_FH = IsHomeGive_FH;
    }

    public int getIsInetBet() {
        return IsInetBet;
    }

    public void setIsInetBet(int IsInetBet) {
        this.IsInetBet = IsInetBet;
    }

    public int getIsInetBet_FH() {
        return IsInetBet_FH;
    }

    public void setIsInetBet_FH(int IsInetBet_FH) {
        this.IsInetBet_FH = IsInetBet_FH;
    }

    /**
     * 比赛开始的时间 10/23
     */
    public int getWorkingDate() {
        return WorkingDate;
    }

    public void setWorkingDate(int WorkingDate) {
        this.WorkingDate = WorkingDate;
    }

    public int getIsLastCall() {
        return IsLastCall;
    }

    public void setIsLastCall(int IsLastCall) {
        this.IsLastCall = IsLastCall;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getCurMinute() {
        return CurMinute;
    }

    public void setCurMinute(int CurMinute) {
        this.CurMinute = CurMinute;
    }

    public int getIsInFavourite() {
        return IsInFavourite;
    }

    public void setIsInFavourite(int IsInFavourite) {
        this.IsInFavourite = IsInFavourite;
    }

    public int getFavId() {
        return FavId;
    }

    public void setFavId(int FavId) {
        this.FavId = FavId;
    }

    public int getHomeRank() {
        return HomeRank;
    }

    public void setHomeRank(int HomeRank) {
        this.HomeRank = HomeRank;
    }

    public int getAwayRank() {
        return AwayRank;
    }

    public void setAwayRank(int AwayRank) {
        this.AwayRank = AwayRank;
    }

    public int getHome() {
        return Home;
    }

    public void setHome(int Home) {
        this.Home = Home;
    }

    public int getAway() {
        return Away;
    }

    public void setAway(int Away) {
        this.Away = Away;
    }

    public int getHasHdp() {
        return HasHdp;
    }

    public void setHasHdp(int HasHdp) {
        this.HasHdp = HasHdp;
    }

    public int getHdpOdds() {
        return HdpOdds;
    }

    public void setHdpOdds(int HdpOdds) {
        this.HdpOdds = HdpOdds;
    }

    public int getHdp() {
        return Hdp;
    }

    public void setHdp(int Hdp) {
        this.Hdp = Hdp;
    }

    public int getRCHome() {
        return RCHome;
    }

    public void setRCHome(int RCHome) {
        this.RCHome = RCHome;
    }

    public int getRCAway() {
        return RCAway;
    }

    public void setRCAway(int RCAway) {
        this.RCAway = RCAway;
    }

    public int getIsHdpNew() {
        return IsHdpNew;
    }

    public void setIsHdpNew(int IsHdpNew) {
        this.IsHdpNew = IsHdpNew;
    }

    public int getHomeHdpOdds() {
        return HomeHdpOdds;
    }

    public void setHomeHdpOdds(int HomeHdpOdds) {
        this.HomeHdpOdds = HomeHdpOdds;
    }

    public int getAwayHdpOdds() {
        return AwayHdpOdds;
    }

    public void setAwayHdpOdds(int AwayHdpOdds) {
        this.AwayHdpOdds = AwayHdpOdds;
    }

    public int getIsOUNew() {
        return IsOUNew;
    }

    public void setIsOUNew(int IsOUNew) {
        this.IsOUNew = IsOUNew;
    }

    public int getHasOU() {
        return HasOU;
    }

    public void setHasOU(int HasOU) {
        this.HasOU = HasOU;
    }

    public int getOU() {
        return OU;
    }

    public void setOU(int OU) {
        this.OU = OU;
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

    public int getOverOdds() {
        return OverOdds;
    }

    public void setOverOdds(int OverOdds) {
        this.OverOdds = OverOdds;
    }

    public int getUnderOdds() {
        return UnderOdds;
    }

    public void setUnderOdds(int UnderOdds) {
        this.UnderOdds = UnderOdds;
    }

    public int getOUOdds() {
        return OUOdds;
    }

    public void setOUOdds(int OUOdds) {
        this.OUOdds = OUOdds;
    }

    public int getIsHdpNew_FH() {
        return IsHdpNew_FH;
    }

    public void setIsHdpNew_FH(int IsHdpNew_FH) {
        this.IsHdpNew_FH = IsHdpNew_FH;
    }

    public int getHasHdp_FH() {
        return HasHdp_FH;
    }

    public void setHasHdp_FH(int HasHdp_FH) {
        this.HasHdp_FH = HasHdp_FH;
    }

    public int getHdp_FH() {
        return Hdp_FH;
    }

    public void setHdp_FH(int Hdp_FH) {
        this.Hdp_FH = Hdp_FH;
    }

    public int getHdpOdds_FH() {
        return HdpOdds_FH;
    }

    public void setHdpOdds_FH(int HdpOdds_FH) {
        this.HdpOdds_FH = HdpOdds_FH;
    }

    public int getHomeHdpOdds_FH() {
        return HomeHdpOdds_FH;
    }

    public void setHomeHdpOdds_FH(int HomeHdpOdds_FH) {
        this.HomeHdpOdds_FH = HomeHdpOdds_FH;
    }

    public int getAwayHdpOdds_FH() {
        return AwayHdpOdds_FH;
    }

    public void setAwayHdpOdds_FH(int AwayHdpOdds_FH) {
        this.AwayHdpOdds_FH = AwayHdpOdds_FH;
    }

    public int getIsOUNew_FH() {
        return IsOUNew_FH;
    }

    public void setIsOUNew_FH(int IsOUNew_FH) {
        this.IsOUNew_FH = IsOUNew_FH;
    }

    public int getHasOU_FH() {
        return HasOU_FH;
    }

    public void setHasOU_FH(int HasOU_FH) {
        this.HasOU_FH = HasOU_FH;
    }

    public int getOU_FH() {
        return OU_FH;
    }

    public void setOU_FH(int OU_FH) {
        this.OU_FH = OU_FH;
    }

    public int getRunHomeScore_FH() {
        return RunHomeScore_FH;
    }

    public void setRunHomeScore_FH(int RunHomeScore_FH) {
        this.RunHomeScore_FH = RunHomeScore_FH;
    }

    public int getRunAwayScore_FH() {
        return RunAwayScore_FH;
    }

    public void setRunAwayScore_FH(int RunAwayScore_FH) {
        this.RunAwayScore_FH = RunAwayScore_FH;
    }

    public int getOverOdds_FH() {
        return OverOdds_FH;
    }

    public void setOverOdds_FH(int OverOdds_FH) {
        this.OverOdds_FH = OverOdds_FH;
    }

    public int getUnderOdds_FH() {
        return UnderOdds_FH;
    }

    public void setUnderOdds_FH(int UnderOdds_FH) {
        this.UnderOdds_FH = UnderOdds_FH;
    }

    public int getOUOdds_FH() {
        return OUOdds_FH;
    }

    public void setOUOdds_FH(int OUOdds_FH) {
        this.OUOdds_FH = OUOdds_FH;
    }

    public int getStatsId() {
        return StatsId;
    }

    public void setStatsId(int StatsId) {
        this.StatsId = StatsId;
    }

    public int getServerHome() {
        return ServerHome;
    }

    public void setServerHome(int ServerHome) {
        this.ServerHome = ServerHome;
    }

    public int getServerAway() {
        return ServerAway;
    }

    public void setServerAway(int ServerAway) {
        this.ServerAway = ServerAway;
    }

    public int getServerModuleTitle() {
        return ServerModuleTitle;
    }

    public void setServerModuleTitle(int ServerModuleTitle) {
        this.ServerModuleTitle = ServerModuleTitle;
    }

    public int getLang() {
        return Lang;
    }

    public void setLang(int Lang) {
        this.Lang = Lang;
    }

    public int getTodayDate() {
        return TodayDate;
    }

    public void setTodayDate(int TodayDate) {
        this.TodayDate = TodayDate;
    }

    public int getEventKey() {
        return EventKey;
    }

    public void setEventKey(int EventKey) {
        this.EventKey = EventKey;
    }

    public int getPrevKey() {
        return PrevKey;
    }

    public void setPrevKey(int PrevKey) {
        this.PrevKey = PrevKey;
    }

    public int getJSStatus() {
        return JSStatus;
    }

    public void setJSStatus(int JSStatus) {
        this.JSStatus = JSStatus;
    }

    public List<List<String>> getJSOdds() {
        return JSOdds;
    }

    public void setJSOdds(List<List<String>> JSOdds) {
        this.JSOdds = JSOdds;
    }

}
