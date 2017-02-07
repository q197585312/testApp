package com.nanyang.app.load.login;


import com.nanyang.app.ApiService;
import com.nanyang.app.load.UserInfo;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.login.DataBean;
import com.unkonw.testapp.login.ResBaseBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;

/**
 * Api类的包装
 */
public class ApiLogin extends Api {
    /**
     * String loginParams = "txtLang=0&txtAcctid="+afbApp.getUser().getName()+"&txtPwd="+afbApp.getUser().getPassword()+"&OsType=Android"+"&OsVersion="+version;
     * strRes = afbApp.getHttpClient().sendPost(WebSiteUrl.LOGIN_URL, loginParams);
     */
    Flowable<String> getPersonalInfo(UserInfo mLoginParams) {
        return applySchedulers(getService(ApiService.class).getPersonalInfo(mLoginParams.getMap()));
    }

    Flowable<String> getData(UserInfo mLoginParams) {
        return applySchedulers(getService(ApiService.class).getUserInfo(mLoginParams.getTxtLang()
                , mLoginParams.getTxtAcctid()
                , mLoginParams.getTxtPwd()
                , mLoginParams.getOsType()
                , mLoginParams.getOsVersion()
        ));
    }

    Call<ResBaseBean<DataBean>> checkVersion(String url) {
        return getService(ApiService.class).checkVersion(url);

    }

    Call<String> getData(Map<String, String> params) {
        return getService(ApiService.class).getData(params);

    }

    Flowable<String> doLogin(LoginInfo info) {

        return applySchedulers(getService(ApiService.class).doLogin(info.getMap()));
    }
}