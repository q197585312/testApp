package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class MatchBean implements Serializable {
    private String HomeRank;
    private String AwayRank;
    private String Live;
    Type type = Type.ITME;
    String Key;
    String MatchDate;
    String WorkingDate;
    String Away;
    String Home;
    String HomeId;
    String AwayId;//客队 id
    String RCHome;
    String RCAway;
    List<HandicapBean> handicapBeans;
    VsOtherDataBean otherDataBean;
    String CurMinute;
    String RunHomeScore;
    String RunAwayScore;
    String Status;
    LeagueBean leagueBean;

    public MatchBean(String homeRank,
                     String awayRank,
                     String homeId,
                     String awayId,
                     String key,
                     String matchDate,
                     String workingDate,
                     String away,
                     String home,
                     List<HandicapBean> handicapBeans,
                     String rcHome,
                     String rcAway,
                     VsOtherDataBean otherDataBean,
                     String CurMinute,
                     String RunHomeScore,
                     String RunAwayScore,
                     String Live,
                     String Status) {
        Key = key;
        MatchDate = matchDate;
        WorkingDate = workingDate;
        Away = away;
        Home = home;
        HomeId = homeId;
        AwayId = awayId;
        this.handicapBeans = handicapBeans;
        RCHome = rcHome;
        RCAway = rcAway;
        this.otherDataBean = otherDataBean;

        this.CurMinute = CurMinute;
        this.RunAwayScore = RunAwayScore;
        this.RunHomeScore = RunHomeScore;
        this.Live = Live;
        this.Status = Status;
        this.HomeRank = homeRank;
        this.AwayRank = awayRank;
    }

    public MatchBean() {
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

    public enum Type {
        HEAD, TITLE, ITME
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
    public String toString() {
        return "MatchBean{" +
                "HomeRank='" + HomeRank + '\'' +
                ", AwayRank='" + AwayRank + '\'' +
                ", Live='" + Live + '\'' +
                ", type=" + type +
                ", Key='" + Key + '\'' +
                ", MatchDate='" + MatchDate + '\'' +
                ", WorkingDate='" + WorkingDate + '\'' +
                ", Away='" + Away + '\'' +
                ", Home='" + Home + '\'' +
                ", HomeId='" + HomeId + '\'' +
                ", AwayId='" + AwayId + '\'' +

                ", RCHome='" + RCHome + '\'' +
                ", RCAway='" + RCAway + '\'' +
                ", handicapBeans=" + handicapBeans +
                ", otherDataBean=" + otherDataBean +
                ", CurMinute='" + CurMinute + '\'' +
                ", RunHomeScore='" + RunHomeScore + '\'' +
                ", RunAwayScore='" + RunAwayScore + '\'' +
                ", Status='" + Status + '\'' +
                ", leagueBean=" + leagueBean +
                '}';
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
}
