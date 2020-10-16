package com.example.liveplayapp

import com.example.liveplayapp.live.liveListBean
import okhttp3.RequestBody
import retrofit2.http.*

interface LiveApiService  {

    @Headers("Content-Type: application/json", "Accept: application/json") //需要添加头
    @POST
    suspend fun doPostLiveJson(
        @Url url: String,
        @Body info: RequestBody
    ): liveListBean
    @GET
    suspend fun getData(@Url url: String): String

    // helper.doRetrofitApiOnUiThreadBackPostJson(AppConstant.getInstance().HOST + "api/pgGetTVID", new SaCasinoWfBean("GETTV", "", "pgGetTVID"), n

    @POST
    suspend fun postData(@Url url: String): String

    /*login*/ //http://www.jan88.net/Default1.aspx?lang=eng
    /* lstLang	Default.aspx?lang=EN-US
    txtUserName	demoafbai1
    password_clear	PASSWORD
    password_password	123123aa
    btnSignIn	Login*/
    @FormUrlEncoded
    @POST
    suspend fun doPostMap(
        @Url url: String?,
        @FieldMap info: Map<String, String>
    ): String

    // RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), languageWfBean.getJson());
    @Headers("Content-Type: application/json", "Accept: application/json") //需要添加头
    @POST
    suspend fun doPostJson(
        @Url url: String,
        @Body info: RequestBody
    ): String
}