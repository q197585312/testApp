package com.unkonw.testapp.base;

import com.unkonw.testapp.LoginInfo;

import io.reactivex.Flowable;

/**
 *  Api类的包装
 */
public class ApiWrapper extends Api {
/**
 *    String loginParams = "txtLang=0&txtAcctid="+afbApp.getUser().getName()+"&txtPwd="+afbApp.getUser().getPassword()+"&OsType=Android"+"&OsVersion="+version;
 strRes = afbApp.getHttpClient().sendPost(WebSiteUrl.LOGIN_URL, loginParams);
 * */
    public Flowable<String> getUerInfo(LoginInfo mLoginParams) {
        return applySchedulers(getService().getUserInfo(mLoginParams.getTxtLang()
                ,mLoginParams.getTxtAcctid()
                ,mLoginParams.getTxtPwd()
                ,mLoginParams.getOsType()
                ,mLoginParams.getOsVersion()
                ));
    }


}