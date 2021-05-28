package com.nanyang.app.load.register;


import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.load.UserInfo;
import com.unkonw.testapp.libs.api.ApiManager;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;

/**
 * Api类的包装
 */
public class ApiRegister extends ApiManager {
    /**
     * String loginParams = "txtLang=0&txtAcctid="+getApp().getUser().getName()+"&txtPwd="+getApp().getUser().getPassword()+"&OsType=Android"+"&OsVersion="+version;
     * strRes = getApp().getHttpClient().sendPost(WebSiteUrl.URL_LOGIN, loginParams);
     */
    public Flowable<String> getPersonalInfo(UserInfo mLoginParams) {
        return applySchedulers(ApiServiceKt.Companion.getInstance().getPersonalInfo(mLoginParams.getMap()));
    }

    public Flowable<String> getData(UserInfo mLoginParams) {
        return applySchedulers(ApiServiceKt.Companion.getInstance().getUserInfo(mLoginParams.getTxtLang()
                , mLoginParams.getTxtAcctid()
                , mLoginParams.getTxtPwd()
                , mLoginParams.getOsType()
                , mLoginParams.getOsVersion()
        ));
    }

    public Call<String> getData(Map<String, String> params) {
        return ApiServiceKt.Companion.getInstance().getData(params);

    }
}