package nanyang.com.dig88.Table.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class MatchBean implements Serializable{
    Type type=Type.ITME;
    String Key;
    String MatchDate;
    String WorkingDate;
    String Away;
    String Home;
    String HomeId;
    String AwayId;//客队 id
    String IsInFavourite;
    String RCHome;
    String RCAway;
    List<HandicapBean> handicapBeans;
    VsOtherDataBean otherDataBean;
    String IsRun;
    String CurMinute;
    String RunHomeScore;
    String RunAwayScore;
    String Status;
    LeagueBean leagueBean;
    String RTSMatchId;
    private String HomeRank;
    private String AwayRank;
    private String Live;

    public MatchBean(String homeRank,String awayRank,String homeId,String awayId,String key,String matchDate, String workingDate, String away, String home, List<HandicapBean> handicapBeans,String isInFavourite,String rcHome,String rcAway,VsOtherDataBean otherDataBean,String IsRun,String CurMinute,
                   String RunHomeScore,String RunAwayScore ,String Live, String Status,String RTSMatchId) {
        Key=key;
        MatchDate = matchDate;
        WorkingDate = workingDate;
        Away = away;
        Home = home;
        HomeId=homeId;
        AwayId=awayId;
        this.handicapBeans = handicapBeans;
        IsInFavourite=isInFavourite;
        RCHome=rcHome;
        RCAway=rcAway;
        this.otherDataBean=otherDataBean;
        this.IsRun=IsRun;
        this.CurMinute=CurMinute;
        this.RunAwayScore=RunAwayScore;
        this.RunHomeScore=RunHomeScore;
        this.Live=Live;
        this.Status=Status;
        this.HomeRank=homeRank;
        this.AwayRank=awayRank;
        this.RTSMatchId = RTSMatchId;
    }

    public MatchBean() {
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

    public String getHomeRank() {
        return HomeRank;
    }

    public void setHomeRank(String homeRank) {
        HomeRank = homeRank;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getLive() {
        return Live;
    }

    public void setLive(String live) {
        Live = live;
    }

    public VsOtherDataBean getOtherDataBean() {
        return otherDataBean;
    }

    public void setOtherDataBean(VsOtherDataBean otherDataBean) {
        this.otherDataBean = otherDataBean;
    }

    public String getIsRun() {
        return IsRun;
    }

    public void setIsRun(String isRun) {
        IsRun = isRun;
    }

    public String getCurMinute() {
        return CurMinute;
    }

    public void setCurMinute(String curMinute) {
        CurMinute = curMinute;
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

    public String getIsInFavourite() {
        return IsInFavourite;
    }

    public void setIsInFavourite(String isInFavourite) {
        IsInFavourite = isInFavourite;
    }

    public LeagueBean getLeagueBean() {
        return leagueBean;
    }

    public void setLeagueBean(LeagueBean leagueBean) {
        this.leagueBean = leagueBean;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(String matchDate) {
        MatchDate = matchDate;
    }

    public String getWorkingDate() {
        return WorkingDate;
    }

    public void setWorkingDate(String workingDate) {
        WorkingDate = workingDate;
    }

    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
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

    public List<HandicapBean> getHandicapBeans() {
        return handicapBeans;
    }

    public void setHandicapBeans(List<HandicapBean> handicapBeans) {
        this.handicapBeans = handicapBeans;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj == null) {
            result = false;
        }

        if (this == obj) {
            result = true;
        }

        if (obj instanceof MatchBean) {
            MatchBean stu = (MatchBean) obj;
            if (stu.getKey().equals(this.getKey())) {
                result = true;
            }
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }

    public enum Type{
        HEAD,TITLE,ITME
    }
}