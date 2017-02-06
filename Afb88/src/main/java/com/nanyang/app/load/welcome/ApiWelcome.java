package com.nanyang.app.load.welcome;


import com.unkonw.testapp.libs.api.Api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * Api类的包装
 */
class ApiWelcome extends Api {

    Flowable<String> checkVersion() {
        return applySchedulers(getService().checkVersion());
    }

    Flowable<ResponseBody> updateVersion() {
        return applySchedulers(getService().updateVersion());
    }
}