package com.nanyang.app.load.login;

import com.google.gson.Gson;

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
        map.put("password_clear", password_clear);
        map.put("password_password", password_password);
        map.put("btnSignIn", btnSignIn);
        map.put("__VIEWSTATE", __VIEWSTATE);
        map.put("__EVENTVALIDATION", __EVENTVALIDATION);

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
    public Map<String, String> getWfmain(String ACT,String Lang) {
        Map<String, String> map = new HashMap<>();
        map.put("_fm", new LoginWfBean(ACT,Lang).getJson());
        return map;
    }
    public Map<String, String> getWfLanguage(String Lang) {
        Map<String, String> map = new HashMap<>();
        map.put("_fm", new LanguageWfBean(Lang).getJson());
        return map;
    }

    class LoginWfBean implements Serializable {
//        _fm	{"ACT":"Login","ID":"Demoafba0310","PW":"123456aa","lang":"","pgLable":"0.15504609525960888","vsn":"4.0.12","PT":"wfDefault0"}

        public LoginWfBean(String ACT, String lang) {
            this.ACT = ACT;
            this.lang = lang;
        }

        String ACT = "Login";
        String ID = txtUserName;
        String PW = password_password;
        String lang = "";
        String pgLable = "0.15504609525960888";
        String vsn = "4.0.12";
        String PT = "wfDefault0";
        String getJson(){
            return new Gson().toJson(this);
        }
    }


   public static class LanguageWfBean implements Serializable {
// {"ACT":"GetTT","lang":"EN-US","accType":"","pgLable":"0.8736397885598416","vsn":"4.0.121","PT":"wfMain0"}
//{"ACT":"GetTT","lang":"","accType":"","IsToday":"1","pgLable":"0.6229094620888556","vsn":"001","PT":"wfMain0"}
        public LanguageWfBean(String lang) {
            this.lang = lang;
        }

        String ACT = "GetTT";
        String accType = "";
        String lang = "";
        String pgLable = "0.6229094620888556";
        String vsn = "001";
        String PT = "wfMain0";
       public String getJson(){
            return new Gson().toJson(this);
        }
    }


}
