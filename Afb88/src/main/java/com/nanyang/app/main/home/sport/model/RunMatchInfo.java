package com.nanyang.app.main.home.sport.model;

/**
 * Created by Administrator on 2017/3/11.
 */

import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;


public class RunMatchInfo implements IRTMatchInfo {

    private static final String TAG = "BallInfo";

    public String getDbid() {
        return Dbid;
    }

    public void setDbid(String dbid) {
        Dbid = dbid;
    }

    private String Dbid;
    String TvPathIBC;

    public String getTvPathIBC() {
        return TvPathIBC;
    }

    public void setTvPathIBC(String tvPathIBC) {
        TvPathIBC = tvPathIBC;
    }

    @Override
    public String getRTSMatchId() {
        return RTSMatchId;
    }

    public void setRTSMatchId(String RTSMatchId) {
        this.RTSMatchId = RTSMatchId;
    }

    public String getLeague() {
        return League;
    }

    public void setLeague(String league) {
        League = league;
    }

    @Override
    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    @Override
    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public String getGameId() {
        return GameId;
    }

    public void setGameId(String gameId) {
        GameId = gameId;
    }

    @Override
    public String getSocOddsId() {
        return SocOddsId;
    }

    public void setSocOddsId(String socOddsId) {
        SocOddsId = socOddsId;
    }

    public String getIsRun() {
        return IsRun;
    }

    public void setIsRun(String isRun) {
        IsRun = isRun;
    }

    String RTSMatchId;
    String League;
    String Home;
    String Away;
    String GameId;
    String SocOddsId;
    String IsRun;

    public String getHomeSocre() {
        return HomeSocre;
    }

    public void setHomeSocre(String homeSocre) {
        HomeSocre = homeSocre;
    }

    public String getAwaySocre() {
        return AwaySocre;
    }

    public void setAwaySocre(String awaySocre) {
        AwaySocre = awaySocre;
    }

    String HomeSocre;
    String AwaySocre;

    public RunMatchInfo(String tvPathIBC, String RTSMatchId, String league, String home, String away, String gameId, String socOddsId, String isRun, String dbid, String homeSocre, String awaySocre) {
        TvPathIBC = tvPathIBC;
        this.RTSMatchId = RTSMatchId;
        League = league;
        Home = home;
        Away = away;
        GameId = gameId;
        this.IsRun = isRun;
        this.Dbid = dbid;
        SocOddsId = socOddsId;
        HomeSocre = homeSocre;
        AwaySocre = awaySocre;

    }
}
