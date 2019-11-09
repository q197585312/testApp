package com.unkonw.testapp.libs.api;

//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.unkonw.testapp.libs.base.BaseApplication;
import com.unkonw.testapp.libs.utils.ToStringConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Sunflower on 2015/11/4.
 */
public class Api {

    /**
     * 服务器地址
     */
    // 请求公共部分
    private static final String BASE_URL = "http://main55.afb88.com/";

    // 消息头
    private static final String HEADER_X_HB_Client_Type = "X-HB-Client-Type";
    private static final String FROM_ANDROID = "ayb-android";


    private static Retrofit retrofit;
    private static Object service;

    public static <T> T getService(Class<T> cls) {
        if (service == null) {
            service = getRetrofit().create(cls);
        }
        return (T) service;
    }

    /**
     * 拦截器  给所有的请求添加消息头
     */
    private static Interceptor mInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")

//                    .addHeader("Cookie", "add cookies here")
                    .build();
            return chain.proceed(request);
        }
    };

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            // log拦截器  打印所有的log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            //设置 请求的缓存
            File cacheFile = new File(BaseApplication.getInstance().getCacheDir(), "cache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //10Mb


/*
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
//                    .addInterceptor(mInterceptor)
                    .cache(cache)
                    .build();*/
            OkHttpClient client = new OkHttpClient.Builder()
                    .followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addNetworkInterceptor(
                            interceptor)
                    .cookieJar(new CookieManger(BaseApplication.getInstance()))
                    .cache(cache)
//                    .addInterceptor( mInterceptor)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(new ToStringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * 对 Observable<T> 做统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     *
     * @param responseObservable
     * @param <T>
     * @return
     */
    public <T> Flowable<T> applySchedulers(Flowable<T> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<T, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(T tResponse) throws Exception {
                        return flatResponse(tResponse);
                    }
                })
                ;
    }


    /**
     * 对网络接口返回的Response进行分割操作 对于jasn 解析错误以及返回的 响应实体为空的情况
     *
     * @param response
     * @return
     */
    public <T> Flowable<T> flatResponse(final T response) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> subscriber) throws Exception {
                if (response != null) {
                    if (!subscriber.isCancelled()) {
                        subscriber.onNext(response);
                    }
                } else {
                    if (!subscriber.isCancelled()) {
                        subscriber.onError(new APIException("101", "Error Data"));
                    }
                    return;
                }
                if (!subscriber.isCancelled()) {
                    subscriber.onComplete();
                }
            }
        }, BackpressureStrategy.BUFFER);

    }

    /**
     *
     */
    public static class APIException extends Exception {
        public String code;
        public String message;

        public APIException(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

    }


}
