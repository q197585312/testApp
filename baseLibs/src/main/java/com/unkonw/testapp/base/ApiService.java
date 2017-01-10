package com.unkonw.testapp.base;


import com.unkonw.testapp.LoginInfo;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



/**
 * Created by Sunflower on 2015/11/4.
 */
public interface ApiService {


    /**
     * 获取个人信息
     */
    @POST("login.jsp")
    Flowable<String> getPersonalInfo(@Body LoginInfo mLoginParams);

    @FormUrlEncoded
    @POST("login.jsp")
    Call<String> getUserInfo(@Field("txtLang")String txtLang, @Field("txtAcctid")String txtAcctid, @Field("txtPwd")String txtPwd, @Field("osType")String osType, @Field("osVersion")String osVersion);
}
