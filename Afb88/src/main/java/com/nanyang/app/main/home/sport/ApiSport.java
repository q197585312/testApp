package com.nanyang.app.main.home.sport;


import com.unkonw.testapp.libs.api.Api;

import io.reactivex.Flowable;

/**
 * Api类的包装
 */
public abstract class ApiSport extends Api {
    public abstract Flowable<String> getData(String url);//每个sport界面都有刷新的网络请求 ，每个fragment new 出这个api的时候 实现各自的请求
}