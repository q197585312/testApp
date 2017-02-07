package com.nanyang.app;


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
    Call<ResBaseBean<DataBean>> checkVersion(@Url String url) ;


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
    //http://www.jan88.net/Default1.aspx?lang=eng
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1537757816&LID=a01c99aa6487a0b5&_=1486482785410
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1537757816&wd=&ia=0&LID=a7ebaf34bf9ffc7f&_=1486482785411
    @FormUrlEncoded
    @POST("http://www.doo88.org/Default1.aspx")
    Flowable<String> doLogin(@FieldMap Map<String,String> info) ;

}
