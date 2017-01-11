package com.unkonw.testapp.login;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/10 0010.
 */
public class LoginInfo {
    /*String loginParams = "txtLang=0&txtAcctid="+afbApp.getUser().getName()+"&txtPwd="+afbApp.getUser().getPassword()+"&OsType=Android"+"&OsVersion="+version;
    strRes = afbApp.getHttpClient().sendPost(WebSiteUrl.LOGIN_URL, loginParams);*/
    String txtLang;
    String txtAcctid;
    String txtPwd;
    String OsType;
    String OsVersion;

    public String getTxtLang() {
        return txtLang;
    }

    public void setTxtLang(String txtLang) {
        this.txtLang = txtLang;
    }

    public String getTxtAcctid() {
        return txtAcctid;
    }

    public void setTxtAcctid(String txtAcctid) {
        this.txtAcctid = txtAcctid;
    }

    public String getTxtPwd() {
        return txtPwd;
    }

    public void setTxtPwd(String txtPwd) {
        this.txtPwd = txtPwd;
    }

    public String getOsType() {
        return OsType;
    }

    public void setOsType(String osType) {
        OsType = osType;
    }

    public String getOsVersion() {
        return OsVersion;
    }

    public void setOsVersion(String osVersion) {
        OsVersion = osVersion;
    }

    public LoginInfo(String txtLang, String txtAcctid, String txtPwd, String osType, String osVersion) {
        this.txtLang = txtLang;
        this.txtAcctid = txtAcctid;
        this.txtPwd = txtPwd;
        OsType = osType;
        OsVersion = osVersion;
    }
    public Map<String,String> getMap(){
        Map<String,String> map=new HashMap<>();
        map.put("txtLang",txtLang);
        map.put("txtAcctid",txtAcctid);
        map.put("txtPwd",txtPwd);
        map.put("OsType",OsType);
        map.put("OsVersion",OsVersion);
        return map;
    }
}
