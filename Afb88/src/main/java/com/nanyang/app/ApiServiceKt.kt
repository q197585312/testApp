package com.nanyang.app

import android.content.res.AssetFileDescriptor
import com.nanyang.app.data.CheckVersionBean
import com.nanyang.app.data.GamesData
import com.nanyang.app.load.welcome.AllBannerImagesBean
import com.nanyang.app.main.BetCenter.Bean.Contact
import com.nanyang.app.main.home.huayThai.HuayDrawDateInfo
import com.nanyang.app.main.home.huayThai.ResultBean
import com.nanyang.app.main.home.keno.bean.KenoBetLimitBean
import com.nanyang.app.main.home.keno.bean.KenoDataBean
import com.nanyang.app.main.home.sport.model.BettingParPromptBean
import com.nanyang.app.main.home.sport.model.BettingPromptBean
import com.unkonw.testapp.libs.api.ApiManager
import gaming178.com.casinogame.load.Liga365Data
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceKt {
    companion object {
        val instance = ApiManager.getService(ApiServiceKt::class.java)

        fun invoke(): ApiServiceKt {
            return instance
        }
    }

    /**
     * 玩安卓轮播图
     */
    @GET
    suspend fun getData(@Url url: String): String

    @GET
    suspend fun getSiteMap(@Url url: String): Liga365Data

    @GET
    suspend fun getAllImage(@Url url: String): GamesData

    /**
     * 获取个人信息
     */
    @POST("login.jsp")
    @FormUrlEncoded
    fun getPersonalInfo(@FieldMap mLoginParams: Map<String?, String?>?): Flowable<String?>?

    @FormUrlEncoded
    @POST("login.jsp")
    fun getUserInfo(
        @Field("txtLang") txtLang: String?,
        @Field("txtAcctid") txtAcctid: String?,
        @Field("txtPwd") txtPwd: String?,
        @Field("osType") osType: String?,
        @Field("osVersion") osVersion: String?
    ): Flowable<String?>?

    @GET
    fun checkVersion(@Url url: String?): Flowable<CheckVersionBean?>?


    @POST("login.jsp")
    @FormUrlEncoded
    fun getData(@FieldMap map: Map<String?, String?>?): Call<String?>?

    @Streaming //添加这个注解用来下载大文件
    @GET
    fun updateVersion(@Url url: String?): Flowable<ResponseBody?>?

    /*login*/


    //http://www.jan88.net/Default1.aspx?lang=eng
    /* lstLang	Default.aspx?lang=EN-US
    txtUserName	demoafbai1
    password_clear	PASSWORD
    password_password	123123aa
    btnSignIn	Login*/

    /*login*/ //http://www.jan88.net/Default1.aspx?lang=eng
    /* lstLang	Default.aspx?lang=EN-US
    txtUserName	demoafbai1
    password_clear	PASSWORD
    password_password	123123aa
    btnSignIn	Login*/
    @FormUrlEncoded
    @POST
    fun doPostMap(@Url url: String?, @FieldMap info: Map<String?, String?>?): Flowable<String?>?

    @Headers("Content-Type: application/json", "Accept: application/json") //需要添加头
    @POST
    fun doPostJson(@Url url: String?, @Body info: RequestBody?): Flowable<String?>?


    @POST
    fun getDataHeader(
        @Url url: String?,
        @HeaderMap headers: Map<String?, String?>?
    ): Flowable<String?>?

    @GET
    fun getData(@Url url: String?): Flowable<String?>?

    @GET
    fun getData(@Url url: String?, @HeaderMap headers: Map<String, String>): Flowable<String?>?

    @FormUrlEncoded
    @POST
    fun doHuayMap(@Url url: String?, @FieldMap info: Map<String?, String?>?): Flowable<ResultBean?>?

    /*                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

    addHeader("Content-Type","text/html; charset=gb2312")
                .

    addHeader("Content-Type","text/html; charset=UTF-8")*/

    /*                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

    addHeader("Content-Type","text/html; charset=gb2312")
                .

    addHeader("Content-Type","text/html; charset=UTF-8")*/
    @Headers(
        "Accept: text/html,application/xhtml+xml,application/xml",
        "Content-Type: text/html; charset=utf-8",
        "Connection: keep-alive",
        "Host: " + BuildConfig.HOST
    )
    @GET
    fun getResponse(@Url url: String?): Flowable<Response<String?>?>?

    @GET
    fun getContactData(@Url url: String?): Flowable<Contact?>?

    @GET
    fun getBetData(@Url url: String?): Flowable<BettingPromptBean?>?


    @GET
    fun updateMixParlayBet(@Url s: String?): Flowable<BettingParPromptBean?>?


    //注册时检测用户帐号
    @FormUrlEncoded
    @POST("http://www.afbasia88.com/_view/Register1.aspx")
    fun checkUserName(@FieldMap info: Map<String?, String?>?): Flowable<String?>?

    //注册
    @FormUrlEncoded
    @POST("http://www.afbasia88.com/_view/Register1.aspx")
    fun Register(@FieldMap info: Map<String?, String?>?): Flowable<String?>?

    @GET("http://appgd88.com/images/afb88.php?app=afb88")
    fun getBannerUrl(): Flowable<List<String?>?>?

    @FormUrlEncoded
    @POST
    fun changePasswrod(@Url url: String?, @FieldMap map: Map<String?, String?>?): Flowable<String?>?

    @POST
    @FormUrlEncoded
    fun gamesECashOutMoney(
        @Url url: String?,
        @Field("EGBalance") eGBalance: String?
    ): Flowable<String?>?

    @POST
    @FormUrlEncoded
    fun gamesETransferMoney(
        @Url url: String?,
        @Field("EgLimit") EgLimit: String?
    ): Flowable<String?>?

    @POST
    @FormUrlEncoded
    fun gamesGDCashOutMoney(
        @Url url: String?,
        @Field("LDBalance") lDBalance: String?
    ): Flowable<String?>?

    @POST
    @FormUrlEncoded
    fun gamesGDTransferMoney(
        @Url url: String?,
        @Field("LDLimit") EgLimit: String?
    ): Flowable<String?>?

    @POST
    @FormUrlEncoded
    fun games855CashOutMoney(
        @Url url: String?,
        @Field("LDCBalance") lDCBalance: String?
    ): Flowable<String?>?

    @POST
    @FormUrlEncoded
    fun games855TransferMoney(
        @Url url: String?,
        @Field("LDCLimit") EgLimit: String?
    ): Flowable<String?>?

    @POST
    @FormUrlEncoded
    fun gamesW88CashOutMoney(
        @Url url: String?,
        @Field("LDDBalance") lDDBalance: String?
    ): Flowable<String?>?

    @POST
    @FormUrlEncoded
    fun gamesW88TransferMoney(
        @Url url: String?,
        @Field("LDDLimit") EgLimit: String?
    ): Flowable<String?>?

    //选择语言
    @GET
    fun switchLanguage(@Url url: String?, @Query("lang") lang: String?): Flowable<String?>?


    @GET
    fun getKenoData(@Url url: String?): Flowable<KenoDataBean?>?

    @GET
    fun getKenoBetStatusData(@Url url: String?): Flowable<KenoBetLimitBean?>?

    @GET
    fun getAllImagesData(@Url url: String?): Flowable<AllBannerImagesBean?>?

    @GET
    fun getAdditionData(@Url url: String?): Flowable<String?>?

    @Multipart
    @POST
    fun upload(@Url url: String, @Part file: MultipartBody.Part?): Flowable<String?>?
}