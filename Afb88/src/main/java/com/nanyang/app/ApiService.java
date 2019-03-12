package com.nanyang.app;


import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.center.model.StatementStakeDetailsListBean;
import com.nanyang.app.main.center.model.StatementStakeListBean;
import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.nanyang.app.main.home.huayThai.HuayDrawDateInfo;
import com.nanyang.app.main.home.huayThai.ResultBean;
import com.nanyang.app.main.home.keno.bean.KenoBetLimitBean;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.unkonw.testapp.login.DataBean;
import com.unkonw.testapp.login.ResBaseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    @GET(AppConstant.CHECK_VERSION)
    Flowable<String> checkVersion();

    @GET(AppConstant.DOWNLOAD_APP)
    Flowable<ResponseBody> updateVersion();

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
    @POST
    Flowable<String> doPostMap(@Url String url, @FieldMap Map<String, String> info);

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST
    Flowable<String> doPostJson(@Url String url, @Body RequestBody info);

    @FormUrlEncoded
    @POST
    Flowable<ResultBean> doHuayMap(@Url String url, @FieldMap Map<String, String> info);

    @GET
    Flowable<String> getData(@Url String url);

    @GET
    Flowable<BettingPromptBean> getBetData(@Url String url);


    @GET
    Flowable<BettingParPromptBean> updateMixParlayBet(@Url String s);


    //注册时检测用户帐号
    @FormUrlEncoded
    @POST("http://www.afbasia88.com/_view/Register1.aspx")
    Flowable<String> checkUserName(@FieldMap Map<String, String> info);

    //注册
    @FormUrlEncoded
    @POST("http://www.afbasia88.com/_view/Register1.aspx")
    Flowable<String> Register(@FieldMap Map<String, String> info);


    @GET
    Flowable<List<StatementStakeListBean>> statementStake(@Url String url);

    @GET
    Flowable<List<StatementStakeDetailsListBean>> statementStakeDetails(@Url String url);


    @GET("http://appgd88.com/images/afb88.php?app=afb88")
    Flowable<List<String>> getBannerUrl();

    @FormUrlEncoded
    @POST
    Flowable<String> changePasswrod(@Url String url, @FieldMap Map<String, String> map);

    //转账
    @GET
    Flowable<TransferMoneyBean> getTransferMoneyData(@Url String url);

    @POST
    @FormUrlEncoded
    Flowable<String> gamesECashOutMoney(@Url String url, @Field("EGBalance") String eGBalance);

    @POST
    @FormUrlEncoded
    Flowable<String> gamesETransferMoney(@Url String url, @Field("EgLimit") String EgLimit);

    @POST
    @FormUrlEncoded
    Flowable<String> gamesGDCashOutMoney(@Url String url, @Field("LDBalance") String lDBalance);

    @POST
    @FormUrlEncoded
    Flowable<String> gamesGDTransferMoney(@Url String url, @Field("LDLimit") String EgLimit);

    @POST
    @FormUrlEncoded
    Flowable<String> games855CashOutMoney(@Url String url, @Field("LDCBalance") String lDCBalance);

    @POST
    @FormUrlEncoded
    Flowable<String> games855TransferMoney(@Url String url, @Field("LDCLimit") String EgLimit);

    @POST
    @FormUrlEncoded
    Flowable<String> gamesW88CashOutMoney(@Url String url, @Field("LDDBalance") String lDDBalance);

    @POST
    @FormUrlEncoded
    Flowable<String> gamesW88TransferMoney(@Url String url, @Field("LDDLimit") String EgLimit);

    //选择语言
    @GET
    Flowable<String> switchLanguage(@Url String url, @Query("lang") String lang);

    @GET
    Flowable<HuayDrawDateInfo> getHuyaDrawDate(@Url String url);

    @GET
    Flowable<KenoDataBean> getKenoData(@Url String url);

    @GET
    Flowable<KenoBetLimitBean> getKenoBetStatusData(@Url String url);

    @GET
    Flowable<AllBannerImagesBean> getAllImagesData(@Url String url);

}
