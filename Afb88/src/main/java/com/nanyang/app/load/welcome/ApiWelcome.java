package com.nanyang.app.load.welcome;


import com.unkonw.testapp.libs.api.Api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 *  Api类的包装
 */
public class ApiWelcome extends Api {


    public Flowable<String> checkVersion() {
         return applySchedulers(getService().checkVersion());
    }
    public Flowable<ResponseBody> updateVersion() {
        return applySchedulers(getService().updateVersion());
    }
}