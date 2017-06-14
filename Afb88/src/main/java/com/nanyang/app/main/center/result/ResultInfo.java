package com.nanyang.app.main.center.result;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/13.
 */

public class ResultInfo implements Serializable {

    /**
     * ModuleId : 484103
     * ModuleOrder : 50
     * ModuleTitle : WORLD CUP 2018 OCEANIA QUALIFIERS
     * ColorCode : 12566463
     * MatchDate : 03:00PM
     * MatchCode : WORL192LQ
     * WorkingDate : 13/06/2017 00:00:00
     * HomeId : 27287
     * Home : Papua New Guinea
     * AwayId : 17874
     * Away : Solomon Islands
     * Score :
     * HTScore :
     * IsCancel : false
     * Res1 : 255
     * IsCancelFH : false
     * TodayDate : 13/06/2017 15:00:00
     */
    /**  "ModuleId": 483868,
     "MatchDate": "29/05 10:00PM",
     "ModuleTitle": "[ 29/05/2017 ] BASKETBALL -- NBA CHAMPIONSHIP 2016/2017 - WINNING CONFERENCE",
     "Home": "Western Conference",
     "TodayDate": "29/05/2017 22:00:00",
     "ModuleOrder": 1010,
     "MatchCode": "BA11"*/
    /**
     *  {
     "ModuleId": 481054,
     "ModuleOrder": 10000,
     "ModuleTitle": "MUAY THAI - RAJADAMNERN BOXING STADIUM",
     "ColorCode": 12566463,
     "MatchDate": "07:30PM",
     "MatchCode": "M51",
     "WorkingDate": "12/06/2017 00:00:00",
     "HomeId": 1165257,
     "Home": "Kongranong Mor.Puwana",
     "AwayId": 1165258,
     "Away": "Kongphet Sitsan",
     "Score": "0 - 5",
     "HTScore": "-",
     "IsCancel": false,
     "Res1": 255,
     "IsCancelFH": false,
     "Winner": "Kongphet Sitsan",
     "TodayDate": "12/06/2017 19:30:00"
     },
     */

    private int ModuleId;
    private int ModuleOrder;
    private String ModuleTitle;
    private int ColorCode;
    private String MatchDate;
    private String MatchCode;
    private String WorkingDate;
    private int HomeId;
    private String Home;
    private int AwayId;
    private String Away;
    private String Score;
    private String HTScore;
    private boolean IsCancel;
    private int Res1;
    private boolean IsCancelFH;
    private String TodayDate;
    private String Winner;

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public int getModuleId() {
        return ModuleId;
    }

    public void setModuleId(int ModuleId) {
        this.ModuleId = ModuleId;
    }

    public int getModuleOrder() {
        return ModuleOrder;
    }

    public void setModuleOrder(int ModuleOrder) {
        this.ModuleOrder = ModuleOrder;
    }

    public String getModuleTitle() {
        return ModuleTitle;
    }

    public void setModuleTitle(String ModuleTitle) {
        this.ModuleTitle = ModuleTitle;
    }

    public int getColorCode() {
        return ColorCode;
    }

    public void setColorCode(int ColorCode) {
        this.ColorCode = ColorCode;
    }

    public String getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(String MatchDate) {
        this.MatchDate = MatchDate;
    }

    public String getMatchCode() {
        return MatchCode;
    }

    public void setMatchCode(String MatchCode) {
        this.MatchCode = MatchCode;
    }

    public String getWorkingDate() {
        return WorkingDate;
    }

    public void setWorkingDate(String WorkingDate) {
        this.WorkingDate = WorkingDate;
    }

    public int getHomeId() {
        return HomeId;
    }

    public void setHomeId(int HomeId) {
        this.HomeId = HomeId;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String Home) {
        this.Home = Home;
    }

    public int getAwayId() {
        return AwayId;
    }

    public void setAwayId(int AwayId) {
        this.AwayId = AwayId;
    }

    public String getAway() {
        return Away;
    }

    public void setAway(String Away) {
        this.Away = Away;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String Score) {
        this.Score = Score;
    }

    public String getHTScore() {
        return HTScore;
    }

    public void setHTScore(String HTScore) {
        this.HTScore = HTScore;
    }

    public boolean isIsCancel() {
        return IsCancel;
    }

    public void setIsCancel(boolean IsCancel) {
        this.IsCancel = IsCancel;
    }

    public int getRes1() {
        return Res1;
    }

    public void setRes1(int Res1) {
        this.Res1 = Res1;
    }

    public boolean isIsCancelFH() {
        return IsCancelFH;
    }

    public void setIsCancelFH(boolean IsCancelFH) {
        this.IsCancelFH = IsCancelFH;
    }

    public String getTodayDate() {
        return TodayDate;
    }

    public void setTodayDate(String TodayDate) {
        this.TodayDate = TodayDate;
    }
}
