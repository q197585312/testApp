package com.nanyang.app.load.login;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

 class LoginInfo {
   /* __VIEWSTATE	/wEPDwUKLTE5MDg0OTk2OA9kFgJmD2QWBAIBDxYCHgVzdHlsZQUxYmFja2dyb3VuZDp1cmwoaW1hZ2VzL2xvZ2luX0VOLVVTLnBuZykgbm8tcmVwZWF0OxYCZg9kFgICBw8PFgIeBFRleHQFBUxvZ2luZGQCAg8WAh8ABTliYWNrZ3JvdW5kOnVybChpbWFnZXMvbGlzdF9FTi1VUy5wbmcpIG5vLXJlcGVhdCBjZW50ZXIgMDtkZNRh3MZ0akiK/k7BU0DrdOa2KqSQAkPJBUPNRKepAPLW
    __EVENTVALIDATION	/wEdAAYfMKBnGzoFoip6aJJE6fpaiHrx0gRh57qNYHb+gYJUfmN6ZYJNGAQHn0c9zMgZU3A/F856zktixbVekFwCZlBeynuqL3cQJfeeYQ4/gSeOva+f28GNV31+z65DbDqxMS5lXRgroCj8wpuoKkde4QtUV2EKHgwjnQ91GIjhKi6heQ==
    lstLang	Default.aspx?lang=EN-US
    txtUserName	demoafbai1
    password_clear	PASSWORD
    password_password	123123aa
    btnSignIn	Login*/

    private String lstLang ="Default.aspx?lang=EN-US";
    private String txtUserName;
    private String password_clear="PASSWORD";
    private String password_password;
    private String btnSignIn="Login";

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
    public Map<String ,String> getMap(){
        Map<String ,String> map=new HashMap<>();
        map.put("lstLang",lstLang);
        map.put("txtUserName",txtUserName);
        map.put("password_clear",password_clear);
        map.put("password_password",password_password);
        map.put("btnSignIn",btnSignIn);

        return map;
    }
}
