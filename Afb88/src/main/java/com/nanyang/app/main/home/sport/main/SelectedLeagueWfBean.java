package com.nanyang.app.main.home.sport.main;

import com.nanyang.app.load.login.LoginInfo;

/**
 * Created by Administrator on 2019/5/23.
 */
//{"ACT":"SaveNick","PT":"wfMycountH50","Nickname":"11111","pgLable":"0.6380720331956468","vsn":"4.0.12"}&_db={}
//{"ACT":"selectleague","PT":"wfSportsH50","OT":"r","Timess":"","dbid":"1","dbid2":"1","haspar":"0","pgLable":"0.17478179812837813","vsn":"4.0.12"}
class SelectedLeagueWfBean extends LoginInfo.LanguageWfBean {
    public SelectedLeagueWfBean(String ACT, String lang, String PT) {
        super(ACT, lang, PT);
    }

    String OT;
    String Timess="";
    String dbid;
    String dbid2;

    public void setOT(String OT) {
        this.OT = OT;
    }

    public void setTimess(String timess) {
        Timess = timess;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public void setDbid2(String dbid2) {
        this.dbid2 = dbid2;
    }

    public void setHaspar(String haspar) {
        this.haspar = haspar;
    }

    String haspar;


}
