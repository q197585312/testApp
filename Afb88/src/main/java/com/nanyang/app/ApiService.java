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



    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1537757816&LID=a01c99aa6487a0b5&_=1486482785410
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1537757816&wd=&ia=0&LID=a7ebaf34bf9ffc7f&_=1486482785411

    //http://www.jan88.net/Default1.aspx?lang=eng
   /* lstLang	Default.aspx?lang=EN-US
    txtUserName	demoafbai1
    password_clear	PASSWORD
    password_password	123123aa
    btnSignIn	Login*/
    @FormUrlEncoded
    @POST("http://www.jan88.net/Default1.aspx?lang=eng")
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
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1819037080&LID=&_=1486867176438
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1819037080&LID=79cd1df6b9ec83ba&_=1486867176440
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1819037080&LID=7c2b8abe85bb3831&_=1486867176524
    Flowable<String> goRefresh();
//http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1819037080&LID=&_=1486867176438
//    http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1763152739&LID=&_=1486534793736
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1763152739&wd=&ia=0&LID=&_=1486534793737
    //http://a8197c.a36588.com/pgajaxS.axd?T=CHKST&P=100
    //http://a8197c.a36588.com/pgajaxS.axd?T=CHKST&P=001
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1763152739&LID=5e4900fc4fa7c7c&_=1486534793738
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1763152739&wd=&ia=0&LID=33771458aceb4a03&_=1486534793739
    //#		a8197c.a36588.com	/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1763152739&LID=20bff9e1091626e7&_=1486534793740
    //http://a8197c.a36588.com/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=1763152739&LID=dd28a82f31f912d&_=1486534793743

}
