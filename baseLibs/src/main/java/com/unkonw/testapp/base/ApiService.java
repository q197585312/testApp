package com.unkonw.testapp.base;


import com.unkonw.testapp.LoginInfo;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Query;
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


    @POST("login.jsp")
    Flowable<String> getUserInfo(@Query("txtLang")String txtLang, @Query("txtAcctid")String txtAcctid, @Query("txtPwd")String txtPwd, @Query("osType")String osType, @Query("osVersion")String osVersion);
}
