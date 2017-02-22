package com.nanyang.app.main.home;


import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.api.Api;

import io.reactivex.Flowable;

/**
 * Api类的包装
 */
public class ApiHome extends Api {


    Flowable<String> getBallUrl() {

        return applySchedulers(getService(ApiService.class).main());
    }
}