package com.nanyang.app.main;


import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.api.Api;

import io.reactivex.Flowable;

/**
 * Api类的包装
 */
public class ApiMain extends Api {


    Flowable<String> goMain() {

        return applySchedulers(getService(ApiService.class).main());
    }
}