package com.unkonw.testapp.libs.api;


import com.unkonw.testapp.login.DataBean;
import com.unkonw.testapp.login.ResBaseBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


/**
 * Created by Sunflower on 2015/11/4.
 */
public interface ApiService {


    /**
     * 获取个人信息
     */
    @POST("login.jsp")
    @FormUrlEncoded
    Flowable<String> getPersonalInfo(@FieldMap Map<String,String> mLoginParams);

    @FormUrlEncoded
    @POST("login.jsp")
    Flowable<String> getUserInfo(@Field("txtLang")String txtLang, @Field("txtAcctid")String txtAcctid, @Field("txtPwd")String txtPwd, @Field("osType")String osType, @Field("osVersion")String osVersion);
    @GET
    public Call<ResBaseBean<DataBean>> checkVersion(@Url String url) ;


    @POST("login.jsp")
    @FormUrlEncoded
    Call<String> getData(@FieldMap Map<String,String> map );
}
