package com.nanyang.app.main.home;

import android.util.Log;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.unkonw.testapp.libs.api.ApiManager;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;

import org.json.JSONException;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by ASUS on 2019/4/16.
 */

public class LoadPCasinoDataHelper<T extends LoginInfo.LanguageWfBean> {
    private CompositeDisposable mCompositeSubscription;
    private BaseActivity baseContext;
    private ApiManager mApiWrapper;
    private String url;

    public LoadPCasinoDataHelper(ApiManager mApiWrapper, BaseActivity baseContext, CompositeDisposable mCompositeSubscription) {
        this.mApiWrapper = mApiWrapper;
        this.baseContext = baseContext;
        this.mCompositeSubscription = mCompositeSubscription;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public void doRetrofitApiOnUiThreadBackGet(String url, T languageWfBean, final MainPresenter.CallBackError<String> back, final String matches) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson();
        if (!StringUtils.isNull(url)) {
            p = url + languageWfBean.getJson();
        }

        Disposable disposable = mApiWrapper.applyDisposable(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                Log.d("doRetrofitApiOnUiThread", "data: " + data);
                String updateString = AfbUtils.delHTMLTag(data);
                if (!StringUtils.isNull(matches)) {
                    String group = StringUtils.findGroup(updateString, matches, 1);
                    if (!StringUtils.isNull(group)) {
                        back.onBack(group);
                        return;
                    }
                }
                back.onError(updateString);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
        mCompositeSubscription.add(disposable);
    }

    public void doRetrofitApiOnUiThreadBackPost(String url, T languageWfBean, final MainPresenter.CallBackError<String> back, final String matches) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd";
        if (!StringUtils.isNull(url)) {
            p = url;
        }
        HashMap<String, String> map = languageWfBean.getMap();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), languageWfBean.getJson());
        Disposable disposable = mApiWrapper.applyDisposable(ApiServiceKt.Companion.getInstance().doPostJson(p, body), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                onSuccessPost(data, matches, back);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
        mCompositeSubscription.add(disposable);
    }

    public void doRetrofitApiOnUiThreadBackPostJson(String url, T languageWfBean, final MainPresenter.CallBackError<String> back, final String matches) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd";
        if (!StringUtils.isNull(url)) {
            p = url;
        }
        String json = languageWfBean.getJson();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Disposable disposable = mApiWrapper.applyDisposable(ApiServiceKt.Companion.getInstance().doPostJson(p, body), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                onSuccessPost(data, matches, back);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
        mCompositeSubscription.add(disposable);
    }

    private void onSuccessPost(String data, String matches, MainPresenter.CallBackError<String> back) throws JSONException {
        Log.d("doRetrofitApiOnUiThread", "data: " + data);
        String updateString = AfbUtils.delHTMLTag(data);
        if (!StringUtils.isNull(matches)) {
            String group = StringUtils.findGroup(updateString, matches, 1);
            if (!StringUtils.isNull(group)) {
                back.onBack(group);
                return;
            }
        } else {
            back.onBack(updateString);
            return;
        }
        back.onError(updateString);
    }
}
