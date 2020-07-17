package com.nanyang.app.load.login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Utils.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class LoginInfo {
    public String get__VIEWSTATE() {
        return __VIEWSTATE;
    }

    public void set__VIEWSTATE(String __VIEWSTATE) {
        this.__VIEWSTATE = __VIEWSTATE;
    }

    public String get__EVENTVALIDATION() {
        return __EVENTVALIDATION;
    }

    public void set__EVENTVALIDATION(String __EVENTVALIDATION) {
        this.__EVENTVALIDATION = __EVENTVALIDATION;
    }


    /* __VIEWSTATE	/wEPDwUKLTE5MDg0OTk2OA9kFgJmD2QWBAIBDxYCHgVzdHlsZQUxYmFja2dyb3VuZDp1cmwoaW1hZ2VzL2xvZ2luX0VOLVVTLnBuZykgbm8tcmVwZWF0OxYCZg9kFgICBw8PFgIeBFRleHQFBUxvZ2luZGQCAg8WAh8ABTliYWNrZ3JvdW5kOnVybChpbWFnZXMvbGlzdF9FTi1VUy5wbmcpIG5vLXJlcGVhdCBjZW50ZXIgMDtkZNRh3MZ0akiK/k7BU0DrdOa2KqSQAkPJBUPNRKepAPLW
        __EVENTVALIDATION	/wEdAAYfMKBnGzoFoip6aJJE6fpaiHrx0gRh57qNYHb+gYJUfmN6ZYJNGAQHn0c9zMgZU3A/F856zktixbVekFwCZlBeynuqL3cQJfeeYQ4/gSeOva+f28GNV31+z65DbDqxMS5lXRgroCj8wpuoKkde4QtUV2EKHgwjnQ91GIjhKi6heQ==
        lstLang	Default.aspx?lang=EN-US
        txtUserName	demoafbai1
        password_clear	PASSWORD
        password_password	123123aa
        btnSignIn	Login*/
    private String __VIEWSTATE = "/wEPDwULLTE1NzgzODQwNTIPZBYCZg9kFgRmDxYCHgVzdHlsZQUxYmFja2dyb3VuZDp1cmwoaW1hZ2VzL2xvZ2luX0VOLVVTLnBuZykgbm8tcmVwZWF0OxYCZg9kFgICCQ8PFgIeBFRleHQFBUxvZ2luZGQCAQ8WAh8ABTliYWNrZ3JvdW5kOnVybChpbWFnZXMvbGlzdF9FTi1VUy5wbmcpIG5vLXJlcGVhdCBjZW50ZXIgMDtkZFQZd7i8stdYFQuPOPwiVWF56AvyxMUH0QGpyPjYhlg5";
    private String __EVENTVALIDATION = "/wEdAAYpPXKEnSgwS5OM83/1znbNY3plgk0YBAefRz3MyBlTcD8XznrOS2LFtV6QXAJmUF7Ke6ovdxAl955hDj+BJ469iHrx0gRh57qNYHb+gYJUfq+f28GNV31+z65DbDqxMS7eqPw3s2V7Bn4zHu2v1nVEy/P4mxqMWRPvxWg3VzRGSA==";

    private String lstLang = "Default.aspx?lang=EN-US";
    private String txtUserName;
    private String password_clear = "PASSWORD";
    private String password_password;
    private String btnSignIn = "Login";

    public String getLstLang() {
        return lstLang;
    }

    public void setLstLang(String lstLang) {
        this.lstLang = lstLang;
    }

    public String getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName = txtUserName;
    }

    public String getPassword_clear() {
        return password_clear;
    }

    public void setPassword_clear(String password_clear) {
        this.password_clear = password_clear;
    }

    public String getPassword_password() {
        return password_password;
    }

    public void setPassword_password(String password_password) {
        this.password_password = password_password;
    }

    public String getBtnSignIn() {
        return btnSignIn;
    }

    public void setBtnSignIn(String btnSignIn) {
        this.btnSignIn = btnSignIn;
    }

    public LoginInfo(String txtUserName, String password_password) {
        this.txtUserName = txtUserName;
        this.password_password = password_password;
    }

    public LoginInfo() {
    }

    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("lstLang", lstLang);
        map.put("txtUserName", txtUserName);
//        map.put("password_clear", password_clear);
        map.put("password_password", password_password);
        map.put("btnSignIn", btnSignIn);
        map.put("__VIEWSTATE", __VIEWSTATE);
        map.put("__EVENTVALIDATION", __EVENTVALIDATION);
//        map.put("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR);

        return map;
    }

    @Override
    public String toString() {
        return
                "__VIEWSTATE=" + __VIEWSTATE +
                        "&__EVENTVALIDATION=" + __EVENTVALIDATION +
                        "&lstLang=" + lstLang +
                        "&txtUserName=" + txtUserName +
                        "&password_clear=" + password_clear +
                        "&password_password=" + password_password +
                        "&btnSignIn=" + btnSignIn
                ;
    }

    /*_db	{}
    _fm	{"ACT":"Login","ID":"zmb2","PW":"12345678","lang":"","pgLable":"0.8980293281634196","vsn":"4.0.121","PT":"wfDefault0"}*/
    public Map<String, String> getWfmain(String ACT, String Lang) {
        Map<String, String> map = new HashMap<>();
        map.put("_fm", new LoginWfBean(ACT, Lang).getJson());
        return map;
    }

    public String getWfmainJson(String ACT, String Lang) {

        return new LoginWfBean(ACT, Lang).getJson();

    }

    public Map<String, String> getWfLanguage(String Lang) {
        Map<String, String> map = new HashMap<>();
        map.put("_fm", new LanguageWfBean(Lang).getJson());
        return map;
    }

    public Map<String, String> getWfLanguage(String Lang, String acctype) {
        Map<String, String> map = new HashMap<>();
        LanguageWfBean languageWfBean = new LanguageWfBean(Lang);
        languageWfBean.setAccType(acctype);
        map.put("_fm", languageWfBean.getJson());
        return map;
    }

    public class LoginWfBean implements Serializable {
//        _fm	{"ACT":"Login","ID":"Demoafba0310","PW":"123456aa","lang":"","pgLable":"0.15504609525960888","vsn":"4.0.12","PT":"wfDefault0"}

        public LoginWfBean(String ACT, String lang) {
            this.ACT = ACT;
            this.lang = lang;
        }

        public LoginWfBean(String ACT, String ID, String PW, String lang, String pgLable, String vsn, String PT) {
            this.ACT = ACT;
            this.ID = ID;
            this.PW = PW;
            this.lang = lang;
            this.PT = PT;
        }

        String ACT = "Login";
        String ID = txtUserName;
        String PW = password_password;
        String lang = "";
        String pgLable = "0.15504609525960888";
        String vsn = "4.0.12";
        String PT = "wfLoginH50";

        String getJson() {
            return new Gson().toJson(this);
        }
    }


    public static class LanguageWfBean implements Serializable {
        public LanguageWfBean(String lang) {
            this.lang = lang;
        }
        // {"ACT":"selectleague","PT":"wfSportsH50","OT":"r","Timess":"","dbid":"1","dbid2":"1","haspar":"0","pgLable":"0.17478179812837813","vsn":"4.0.12"}

        String ACT = "GetTT";

        public String getAccType() {
            return accType;
        }

        public void setAccType(String accType) {
            this.accType = accType;
        }

        String accType = "";
        String lang = "";
        String pgLable = "0.6398654664343417";
        String vsn = "4.0.12";
        String PT = AppConstant.wfMain;

        public LanguageWfBean(String ACT, String lang, String PT) {
            this.ACT = ACT;
            this.lang = lang;
            this.PT = PT;
        }

        public String getJson() {
            return new Gson().toJson(this);
        }
        public HashMap<String,String> getMap() {
            return new Gson().fromJson(getJson(), new TypeToken<HashMap<String,String>>(){}.getType());
        }


    }

    public static class HuayDataWfBean extends LanguageWfBean {


        public HuayDataWfBean(String ACT, String lang, String PT) {
            super(ACT, lang, PT);
        }

        String type;

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class HuayBetWfBean extends LanguageWfBean {


        public HuayBetWfBean(String ACT, String lang, String PT) {
            super(ACT, lang, PT);
        }

        String typed;
        String txtNumber1;
        String txtAmt;

        public void setTyped(String typed) {
            this.typed = typed;
        }

        public void setTxtNumber1(String txtNumber1) {
            this.txtNumber1 = txtNumber1;
        }

        public void setTxtAmt(String txtAmt) {
            this.txtAmt = txtAmt;
        }

        public void setSocOddsId(String socOddsId) {
            this.socOddsId = socOddsId;
        }

        String socOddsId;
    }
    /*{"ACT":"Savesort",
    "PT":"wfSettingH50",
    "MarketTyped":"0",
    "DefaultSortingd":"0",
    "ScoreSoundd":"1",
    "lang":"EN-US",
    "accType":"ID",
    "AmtS":"0",
    "BetterOdds":"1",
    "ChipsList":"",
    "pgLable":"0.5134303879466173","vsn":"4.0.12"}*/

    public static class SettingWfBean extends LanguageWfBean {


        public SettingWfBean(String ACT, String lang, String PT) {
            super(ACT, lang, PT);
        }

        String MarketTyped;
        String DefaultSortingd;
        String ScoreSoundd;

        String AmtS;
        String BetterOdds;
        String ChipsList;

        public void setMarketTyped(String marketTyped) {
            MarketTyped = marketTyped;
        }

        public void setDefaultSortingd(String defaultSortingd) {
            DefaultSortingd = defaultSortingd;
        }

        public void setScoreSoundd(String scoreSoundd) {
            ScoreSoundd = scoreSoundd;
        }


        public void setAmtS(String amtS) {
            AmtS = amtS;
        }

        public void setBetterOdds(String betterOdds) {
            BetterOdds = betterOdds;
        }

        public void setChipsList(String chipsList) {
            ChipsList = chipsList;
        }
    }

    public static class OutRightWfBean {

        /**
         * ACT : LOS
         * DBID : 999
         * ot : t
         * tf : -1
         * OUTDBID : 2_11
         * timess :
         * accType : EU
         * ov : 0
         * mt : 0
         * pgLable : 0.6073571478712172
         * vsn : 4.0.12
         * PT : wfMainH50
         */

        private String ACT = "LOS";
        private int DBID = 999;
        private String ot;
        private int tf = -1;
        private String OUTDBID = "1_11";
        private String timess = "";
        private String accType;
        private int ov = 0;
        private String mt;
        private String PT = AppConstant.wfMain;

        public OutRightWfBean(String ot, String OUTDBID, String accType, String mt, int ov) {
            this.ot = ot;
            if (!StringUtils.isNull(OUTDBID))
                this.OUTDBID = OUTDBID;
            this.accType = accType;
            this.mt = mt;
            this.ov = ov;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }

        public String getACT() {
            return ACT;
        }

        public void setACT(String ACT) {
            this.ACT = ACT;
        }

        public int getDBID() {
            return DBID;
        }

        public void setDBID(int DBID) {
            this.DBID = DBID;
        }

        public String getOt() {
            return ot;
        }

        public void setOt(String ot) {
            this.ot = ot;
        }

        public int getTf() {
            return tf;
        }

        public void setTf(int tf) {
            this.tf = tf;
        }

        public String getOUTDBID() {
            return OUTDBID;
        }

        public void setOUTDBID(String OUTDBID) {
            this.OUTDBID = OUTDBID;
        }

        public String getTimess() {
            return timess;
        }

        public void setTimess(String timess) {
            this.timess = timess;
        }

        public String getAccType() {
            return accType;
        }

        public void setAccType(String accType) {
            this.accType = accType;
        }

        public int getOv() {
            return ov;
        }

        public void setOv(int ov) {
            this.ov = ov;
        }

        public String getMt() {
            return mt;
        }

        public void setMt(String mt) {
            this.mt = mt;
        }

        public String getPT() {
            return PT;
        }

        public void setPT(String PT) {
            this.PT = PT;
        }
    }


    public static class AllRunningWfBean {

        /**
         * {"ACT":"LOS","DBID":"36","ot":"r","tf":"-1","timess":"","accType":"EU","pgLable":"0.5393305075227944","vsn":"4.0.12","PT":"wfMainH50"}&_db={}
         */

        private String ACT = "LOS";
        private String DBID = "1";
        private String ot;
        private int tf = -1;
        private String timess = "";
        private String accType;
        private String PT = AppConstant.wfMain;

        public AllRunningWfBean(String ot, String DBID, String accType) {
            this.ot = ot;
            if (!StringUtils.isNull(DBID))
                this.DBID = DBID;
            this.accType = accType;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }

        public String getACT() {
            return ACT;
        }

        public void setACT(String ACT) {
            this.ACT = ACT;
        }

        public String getDBID() {
            return DBID;
        }

        public void setDBID(String DBID) {
            this.DBID = DBID;
        }

        public String getOt() {
            return ot;
        }

        public void setOt(String ot) {
            this.ot = ot;
        }

        public int getTf() {
            return tf;
        }

        public void setTf(int tf) {
            this.tf = tf;
        }

        public String getTimess() {
            return timess;
        }

        public void setTimess(String timess) {
            this.timess = timess;
        }

        public String getAccType() {
            return accType;
        }

        public void setAccType(String accType) {
            this.accType = accType;
        }

        public String getPT() {
            return PT;
        }

        public void setPT(String PT) {
            this.PT = PT;
        }
    }
}
