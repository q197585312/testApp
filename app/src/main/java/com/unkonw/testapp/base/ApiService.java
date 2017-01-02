package com.unkonw.testapp.base;


import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;



/**
 * Created by Sunflower on 2015/11/4.
 */
public interface ApiService {


    /**
     * 获取个人信息
     */
    @POST("account/v1/login")
    Flowable<Login> getPersonalInfo(@Body LoginParams mLoginParams);


}
