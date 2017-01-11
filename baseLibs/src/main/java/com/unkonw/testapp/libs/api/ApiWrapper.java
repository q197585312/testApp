package com.unkonw.testapp.libs.api;

import com.unkonw.testapp.login.DataBean;
import com.unkonw.testapp.login.LoginInfo;
import com.unkonw.testapp.login.ResBaseBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;

/**
 *  Api类的包装
 */
public class ApiWrapper extends Api {
/**
 *    String loginParams = "txtLang=0&txtAcctid="+afbApp.getUser().getName()+"&txtPwd="+afbApp.getUser().getPassword()+"&OsType=Android"+"&OsVersion="+version;
 strRes = afbApp.getHttpClient().sendPost(WebSiteUrl.LOGIN_URL, loginParams);
 * */
    public Flowable<String> getPersonalInfo(LoginInfo mLoginParams) {
        return applySchedulers(getService().getPersonalInfo(mLoginParams.getMap()));
    }
    public Flowable<String> getData(LoginInfo mLoginParams) {
        return applySchedulers(getService().getUserInfo(mLoginParams.getTxtLang()
                ,mLoginParams.getTxtAcctid()
                ,mLoginParams.getTxtPwd()
                ,mLoginParams.getOsType()
                ,mLoginParams.getOsVersion()
        ));
    }
    public Call<ResBaseBean<DataBean>> checkVersion(String url) {
        return getService().checkVersion(url);

    }
    public Call<String> getData(Map<String,String> params) {
        return getService().getData(params);

    }
}