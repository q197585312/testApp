package nanyang.com.dig88.Util;


import java.util.Map;

import io.reactivex.Flowable;
import nanyang.com.dig88.Entity.UpdateAppInfoBean;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;


/**
 */
public interface ApiService {


    @POST()
    @FormUrlEncoded
    Flowable<UpdateAppInfoBean> checkVersion(@Url String url, @FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST
    Flowable<String> doPostMap(@Url String url, @FieldMap Map<String, String> info);

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST
    Flowable<String> doPostJson(@Url String url, @Body RequestBody info);

    @GET
    Flowable<String> getData(@Url String url);

}
