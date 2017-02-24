package com.nanyang.app;


import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.unkonw.testapp.login.DataBean;
import com.unkonw.testapp.login.ResBaseBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


/**
 */
public interface ApiService {


    /**
     * 获取个人信息
     */
    @POST("login.jsp")
    @FormUrlEncoded
    Flowable<String> getPersonalInfo(@FieldMap Map<String, String> mLoginParams);

    @FormUrlEncoded
    @POST("login.jsp")
    Flowable<String> getUserInfo(@Field("txtLang") String txtLang, @Field("txtAcctid") String txtAcctid, @Field("txtPwd") String txtPwd, @Field("osType") String osType, @Field("osVersion") String osVersion);
    @GET
    Call<ResBaseBean<DataBean>> checkVersion(@Url String url);


    @POST("login.jsp")
    @FormUrlEncoded
    Call<String> getData(@FieldMap Map<String, String> map);

    /*Load模块*/
    /*welcome*/
    @GET("http://www.appgd88.com/afb88version.php?app=android")
    Flowable<String> checkVersion() ;

    @GET("http://appgd88.com/afb88/download/android/afb88.apk")
    Flowable<ResponseBody> updateVersion() ;

    /*login*/



    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1537757816&LID=a01c99aa6487a0b5&_=1486482785410
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1537757816&wd=&ia=0&LID=a7ebaf34bf9ffc7f&_=1486482785411

    //http://www.jan88.net/Default1.aspx?lang=eng
   /* lstLang	Default.aspx?lang=EN-US
    txtUserName	demoafbai1
    password_clear	PASSWORD
    password_password	123123aa
    btnSignIn	Login*/
    @FormUrlEncoded
    @POST("http://www.afbasia88.com/Default1.aspx")
    Flowable<String> doLogin(@FieldMap Map<String,String> info) ;

    //
    @GET("http://a8197c.a36588.com/_view/BettingRules1.aspx?lang=eng")
    Flowable<String> bettingRules1() ;

    //http://a8197c.a36588.com/Alert.aspx
    @GET("http://a8197c.a36588.com/Alert.aspx")
    Flowable<String> alert() ;

    //http://a8197c.a36588.com/main.aspx
    @GET("http://a8197c.a36588.com/main.aspx")
    Flowable<String> main() ;


    @GET
    Flowable<String> getData(@Url String url);
    @GET
    Flowable<BettingPromptBean> getBetData(@Url String url);

    @GET("http://a8197c.a36588.com/_view/RMOdds2.aspx")
    Flowable<String> goRefresh();
    @GET
    Flowable<String> timerRun2(@Url  String url);
    @GET
    Flowable<String> goFootball(@Url  String url);

    @GET
    Flowable<BettingParPromptBean> addMixParlayBet(@Url String s);
    @GET
    BettingParPromptBean removeMixOrder(@Url String url);
    //注册时检测用户帐号
    @FormUrlEncoded
    @POST("http://www.afbasia88.com/_view/Register1.aspx")
    Flowable<String> checkUserName(@FieldMap Map<String,String> info);
    //注册
    @FormUrlEncoded
    @POST("http://www.afbasia88.com/_view/Register1.aspx")
    Flowable<String> Register(@FieldMap Map<String,String> info);
}