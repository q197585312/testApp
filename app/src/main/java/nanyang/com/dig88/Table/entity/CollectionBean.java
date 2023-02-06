package nanyang.com.dig88.Table.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public class CollectionBean {

    /**
     * Betable : true
     * Param : 'JSOddsFav1Gen.aspx?ot=e&wd=2015-11-26&ia=0&update=true&r=1735578271', 45
     * Ot : e
     * Cnt : 2
     * Wd : 2015-11-26
     * Ia : 0
     * Pn : 1
     * JSOdds_EM : [{"EM_url1":"JSOddsFav1.aspx?ot=e&pn=1&update=false&r=1735578271&wd=2015-11-26&ia=0","EM_d1":"2015-11-26","EM_wd":"2015-11-26"},{"EM_url1":"JSOddsFav1.aspx?ot=e&pn=1&update=false&r=1735578271&wd=2015-11-27&ia=0","EM_d1":"2015-11-27","EM_wd":"2015-11-26"},{"EM_url1":"JSOddsFav1.aspx?ot=e&pn=1&update=false&r=1735578271&wd=2015-11-28&ia=0","EM_d1":"2015-11-28","EM_wd":"2015-11-26"},{"EM_url1":"JSOddsFav1.aspx?ot=e&pn=1&update=false&r=1735578271&wd=2015-11-29&ia=0","EM_d1":"2015-11-29","EM_wd":"2015-11-26"},{"EM_url1":"JSOddsFav1.aspx?ot=e&pn=1&update=false&r=1735578271&wd=2015-11-30&ia=0","EM_d1":"2015-11-30","EM_wd":"2015-11-26"},{"EM_url2":"JSOddsFav1.aspx?ot=e&pn=1&update=false&r=1735578271&wd=2015-12-01&ia=1","EM_d2":"2015-12-01","EM_wd":"2015-11-26"}]
     * JSOdds : [[3,"","-1","-1","55","AUSTRALIA HYUNDAI A LEAGUE",false,"#fff6cf","#bbdede","#ccddff","#FFDDCC","#FFDDCC","",false,"","","","",false,"",
     * "","<SPAN class='Take'><\/SPAN>&nbsp;","<SPAN class='Give'><\/SPAN>",0,"<span><\/span>",false,"","",false,"&nbsp;","","",false,"<span><\/span>","
     * ","",false,"","","",0,"","","AUSTRALIA+HYUNDAI+A+LEAGUE","eng","55||","55|||False"],[3,"55,4720,48624","9063719","9063721","55","AUSTRALIA HYUNDAI A LEAGUE",
     * false,"#fff6cf","#bbdede","#ccddff","#FFDDCC","#FFDDCC","false",false,"","28/11 02:15PM","4720","48624",false,
     * "11/28/2015 12:00:00 AM","","<a class='Take' ><span class='HomeRank'>[5]<\/span>&nbsp;Newcastle Jets<\/a>&nbsp;",
     * "<a class='Give' ><span class='AwayRank'>[2]<\/span>&nbsp;Brisbane Roar<\/a>",0,"<span><SPAN class='bold'>0.5<\/SPAN><\/span>",
     * false,"<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=home&oId=9063719&odds=9.8', 0);
     * \">0.98<\/a>","<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=away&oId=9063719&odds=9.5', 0);
     * \">0.95<\/a>",false,"<SPAN class='bold'>2.5<\/SPAN>","<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=over&oId=9063719&odds=8.6', 0);
     * \">0.86<\/a>","<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=under&oId=9063719&odds=10.5', 0);
     * \">1.05<\/a>",false,"<span><SPAN class='bold'>0-0.5<\/SPAN><\/span>","<a style='cursor:pointer'
     * class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=home&oId=9063719&oId_fh=9063721&odds=8.39999999701977&isFH=true', 0);
     * \">0.84<\/a>","<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=away&oId=9063719&oId_fh=9063721&odds=10.5&isFH=true', 0);
     * \">1.05<\/a>",false,"<SPAN class='bold'>1<\/SPAN>",
     * "<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=over&oId=9063719&oId_fh=9063721&odds=8.20000000298023&isFH=true', 0);
     * \">0.82<\/a>","<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=under&oId=9063719&oId_fh=9063721&odds=10.7&isFH=true'); \",
     * 0>1.07<\/a>",0,"Newcastle+Jets","Brisbane+Roar","AUSTRALIA+HYUNDAI+A+LEAGUE","eng","55|4720|48624","55|4720|48624|False"],[3,"55,4720,48624","9068434","",
     * "55","AUSTRALIA HYUNDAI A LEAGUE",false,"#fff6cf","#bbdede","#ccddff","#FFDDCC","#FFDDCC","false",false,"","28/11 02:15PM","4720",
     * "48624",false,"11/28/2015 12:00:00 AM","","<a class='Take' ><span class='HomeRank'>[5]<\/span>&nbsp;Newcastle Jets<\/a>&nbsp;",
     * "<a class='Give' ><span class='AwayRank'>[2]<\/span>&nbsp;Brisbane Roar<\/a>",0,"<span><SPAN class='bold'>0-0.5<\/SPAN><\/span>",
     * false,"<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=home&oId=9068434&odds=12.6', 0); \">1.26<\/a>",
     * "<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=away&oId=9068434&odds=7.2', 0);
     * \">0.72<\/a>",false,"<SPAN class='bold'>2.5-3<\/SPAN>","<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=over&oId=9068434&odds=11.4'
     * , 0); \">1.14<\/a>","<a style='cursor:pointer' class='PosOdds' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=under&oId=9068434&odds=7.8', 0);
     * \">0.78<\/a>",false,"<span><\/span>","","",false,"","","",0,"Newcastle+Jets","Brisbane+Roar","AUSTRALIA+HYUNDAI+A+LEAGUE","eng","55|4720|48624","55|4720|48624|False"]]
     * TotalCnt : 2
     * FavCnt : 0
     * FavId : 1
     * SocOddsId : 2
     * SocOddsId_FH : 3
     * ModuleId : 4
     * ModuleTitle : 5
     * IsScoreNew : 6
     * MouseOverColor : 7
     * MouseOutColor : 8
     * AltMouseOutColor : 9
     * RunMouseOutColor : 10
     * AltRunMouseOutColor : 11
     * HasRunning : 12
     * IsRun : 13
     * Live : 14
     * MatchDate : 15
     * HomeId : 16
     * AwayId : 17
     * IsHomeGive : 18
     * WorkingDate : 19
     * CurMinute : 20
     * HomeEvent : 21
     * AwayEvent : 22
     * RTSMatchId : 23
     * StrHDP : 24
     * IsHdpNew : 25
     * HomeOdds : 26
     * AwayOdds : 27
     * IsOUNew : 28
     * StrOu : 29
     * OverOdds : 30
     * UnderOdds : 31
     * IsHdpNew_FH : 32
     * StrHDP_FH : 33
     * HomeOdds_FH : 34
     * AwayOdds_FH : 35
     * IsOUNew_FH : 36
     * StrOu_FH : 37
     * OverOdds_FH : 38
     * UnderOdds_FH : 39
     * StatsId : 40
     * ServerHome : 41
     * ServerAway : 42
     * ServerModuleTitle : 43
     * Lang : 44
     * EventId : 45
     * EventKey : 46
     * JSCaller : table.append(gT(jsRec, jsOdds, 0));table.append(gT(jsRec, jsOdds, 1));table.append(gT(jsRec, jsOdds, 2));
     * IsInetBet
     IsInetBet_FH
     ｈｏｍｅＳｃｏｒｅ
     　ＡｗａｙＳｃｏｒｅ
     */
    private int Status;
    private boolean Betable;
    private String Param;
    private String Ot;
    private int Cnt;
    private String Wd;
    private String Ia;
    private int Pn;
    private int TotalCnt;
    private int FavCnt;
    private int FavId;
    private int SocOddsId;
    private int SocOddsId_FH;
    private int ModuleId;
    private int ModuleTitle;
    private int IsScoreNew;
    private int MouseOverColor;
    private int MouseOutColor;
    private int AltMouseOutColor;
    private int RunMouseOutColor;
    private int AltRunMouseOutColor;
    private int HasRunning;
    private int IsRun;
    private int Live;
    private int MatchDate;
    private int HomeId;
    private int AwayId;
    private int IsHomeGive;
    private int WorkingDate;
    private int CurMinute;
    private int HomeEvent;
    private int AwayEvent;
    private int RTSMatchId;
    private int StrHDP;
    private int IsHdpNew;
    private int HomeOdds;
    private int AwayOdds;
    private int IsOUNew;
    private int StrOu;
    private int OverOdds;
    private int UnderOdds;
    private int IsHdpNew_FH;
    private int StrHDP_FH;
    private int HomeOdds_FH;
    private int AwayOdds_FH;
    private int IsOUNew_FH;
    private int StrOu_FH;
    private int OverOdds_FH;
    private int UnderOdds_FH;
    private int StatsId;
    private int ServerHome;
    private int ServerAway;
    private int ServerModuleTitle;
    private int Lang;
    private int EventId;
    private int EventKey;
    private String JSCaller;
    private int IsInetBet;
    private int IsInetBet_FH;
    private int isHomeGive_FH;
    private int RCHome;
    private int RCAway;
    private int RunHomeScore;
    private int RunAwayScore;
    /**
     * EM_url1 : JSOddsFav1.aspx?ot=e&pn=1&update=false&r=1735578271&wd=2015-11-26&ia=0
     * EM_d1 : 2015-11-26
     * EM_wd : 2015-11-26
     */

    private List<JSOddsEMEntity> JSOdds_EM;
    private List<List<String>> JSOdds;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getRunHomeScore() {
        return RunHomeScore;
    }

    public void setRunHomeScore(int runHomeScore) {
        RunHomeScore = runHomeScore;
    }

    public int getRunAwayScore() {
        return RunAwayScore;
    }

    public void setRunAwayScore(int runAwayScore) {
        RunAwayScore = runAwayScore;
    }

    public int getIsHomeGive_FH() {
        return isHomeGive_FH;
    }

    public void setIsHomeGive_FH(int isHomeGive_FH) {
        this.isHomeGive_FH = isHomeGive_FH;
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

    public int getCnt() {
        return Cnt;
    }

    public void setCnt(int Cnt) {
        this.Cnt = Cnt;
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

    public int getTotalCnt() {
        return TotalCnt;
    }

    public void setTotalCnt(int TotalCnt) {
        this.TotalCnt = TotalCnt;
    }

    public int getFavCnt() {
        return FavCnt;
    }

    public void setFavCnt(int FavCnt) {
        this.FavCnt = FavCnt;
    }

    public int getFavId() {
        return FavId;
    }

    public void setFavId(int FavId) {
        this.FavId = FavId;
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

    public int getIsScoreNew() {
        return IsScoreNew;
    }

    public void setIsScoreNew(int IsScoreNew) {
        this.IsScoreNew = IsScoreNew;
    }

    public int getMouseOverColor() {
        return MouseOverColor;
    }

    public void setMouseOverColor(int MouseOverColor) {
        this.MouseOverColor = MouseOverColor;
    }

    public int getMouseOutColor() {
        return MouseOutColor;
    }

    public void setMouseOutColor(int MouseOutColor) {
        this.MouseOutColor = MouseOutColor;
    }

    public int getAltMouseOutColor() {
        return AltMouseOutColor;
    }

    public void setAltMouseOutColor(int AltMouseOutColor) {
        this.AltMouseOutColor = AltMouseOutColor;
    }

    public int getRunMouseOutColor() {
        return RunMouseOutColor;
    }

    public void setRunMouseOutColor(int RunMouseOutColor) {
        this.RunMouseOutColor = RunMouseOutColor;
    }

    public int getAltRunMouseOutColor() {
        return AltRunMouseOutColor;
    }

    public void setAltRunMouseOutColor(int AltRunMouseOutColor) {
        this.AltRunMouseOutColor = AltRunMouseOutColor;
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

    public int getWorkingDate() {
        return WorkingDate;
    }

    public void setWorkingDate(int WorkingDate) {
        this.WorkingDate = WorkingDate;
    }

    public int getCurMinute() {
        return CurMinute;
    }

    public void setCurMinute(int CurMinute) {
        this.CurMinute = CurMinute;
    }

    public int getHomeEvent() {
        return HomeEvent;
    }

    public void setHomeEvent(int HomeEvent) {
        this.HomeEvent = HomeEvent;
    }

    public int getAwayEvent() {
        return AwayEvent;
    }

    public void setAwayEvent(int AwayEvent) {
        this.AwayEvent = AwayEvent;
    }

    public int getRTSMatchId() {
        return RTSMatchId;
    }

    public void setRTSMatchId(int RTSMatchId) {
        this.RTSMatchId = RTSMatchId;
    }

    public int getStrHDP() {
        return StrHDP;
    }

    public void setStrHDP(int StrHDP) {
        this.StrHDP = StrHDP;
    }

    public int getIsHdpNew() {
        return IsHdpNew;
    }

    public void setIsHdpNew(int IsHdpNew) {
        this.IsHdpNew = IsHdpNew;
    }

    public int getHomeOdds() {
        return HomeOdds;
    }

    public void setHomeOdds(int HomeOdds) {
        this.HomeOdds = HomeOdds;
    }

    public int getAwayOdds() {
        return AwayOdds;
    }

    public void setAwayOdds(int AwayOdds) {
        this.AwayOdds = AwayOdds;
    }

    public int getIsOUNew() {
        return IsOUNew;
    }

    public void setIsOUNew(int IsOUNew) {
        this.IsOUNew = IsOUNew;
    }

    public int getStrOu() {
        return StrOu;
    }

    public void setStrOu(int StrOu) {
        this.StrOu = StrOu;
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

    public int getIsHdpNew_FH() {
        return IsHdpNew_FH;
    }

    public void setIsHdpNew_FH(int IsHdpNew_FH) {
        this.IsHdpNew_FH = IsHdpNew_FH;
    }

    public int getStrHDP_FH() {
        return StrHDP_FH;
    }

    public void setStrHDP_FH(int StrHDP_FH) {
        this.StrHDP_FH = StrHDP_FH;
    }

    public int getHomeOdds_FH() {
        return HomeOdds_FH;
    }

    public void setHomeOdds_FH(int HomeOdds_FH) {
        this.HomeOdds_FH = HomeOdds_FH;
    }

    public int getAwayOdds_FH() {
        return AwayOdds_FH;
    }

    public void setAwayOdds_FH(int AwayOdds_FH) {
        this.AwayOdds_FH = AwayOdds_FH;
    }

    public int getIsOUNew_FH() {
        return IsOUNew_FH;
    }

    public void setIsOUNew_FH(int IsOUNew_FH) {
        this.IsOUNew_FH = IsOUNew_FH;
    }

    public int getStrOu_FH() {
        return StrOu_FH;
    }

    public void setStrOu_FH(int StrOu_FH) {
        this.StrOu_FH = StrOu_FH;
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

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int EventId) {
        this.EventId = EventId;
    }

    public int getEventKey() {
        return EventKey;
    }

    public void setEventKey(int EventKey) {
        this.EventKey = EventKey;
    }

    public String getJSCaller() {
        return JSCaller;
    }

    public void setJSCaller(String JSCaller) {
        this.JSCaller = JSCaller;
    }

    public List<JSOddsEMEntity> getJSOdds_EM() {
        return JSOdds_EM;
    }

    public void setJSOdds_EM(List<JSOddsEMEntity> JSOdds_EM) {
        this.JSOdds_EM = JSOdds_EM;
    }

    public List<List<String>> getJSOdds() {
        return JSOdds;
    }

    public void setJSOdds(List<List<String>> JSOdds) {
        this.JSOdds = JSOdds;
    }

    public int getIsInetBet() {
        return IsInetBet;
    }

    public void setIsInetBet(int isInetBet) {
        IsInetBet = isInetBet;
    }

    public int getIsInetBet_FH() {
        return IsInetBet_FH;
    }

    public void setIsInetBet_FH(int isInetBet_FH) {
        IsInetBet_FH = isInetBet_FH;
    }

    public static class JSOddsEMEntity {
        private String EM_url1;
        private String EM_d1;
        private String EM_wd;

        public String getEM_url1() {
            return EM_url1;
        }

        public void setEM_url1(String EM_url1) {
            this.EM_url1 = EM_url1;
        }

        public String getEM_d1() {
            return EM_d1;
        }

        public void setEM_d1(String EM_d1) {
            this.EM_d1 = EM_d1;
        }

        public String getEM_wd() {
            return EM_wd;
        }

        public void setEM_wd(String EM_wd) {
            this.EM_wd = EM_wd;
        }
    }
}
