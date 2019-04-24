package com.nanyang.app.main;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ASUS on 2019/4/16.
 */

public class LoadMainDataHelper {
    private CompositeDisposable mCompositeSubscription;
    private BaseActivity baseContext;
    private Api mApiWrapper;

    public LoadMainDataHelper(Api mApiWrapper, BaseActivity baseContext, CompositeDisposable mCompositeSubscription) {
        this.mApiWrapper = mApiWrapper;
        this.baseContext = baseContext;
        this.mCompositeSubscription = mCompositeSubscription;
    }

    public void doRetrofitApiOnUiThread(LoginInfo.LanguageWfBean languageWfBean, final LanguagePresenter.CallBack<String> back) {

        Disposable disposable = mApiWrapper.applyDisposable(Api.getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    if (jsonArrayData.length() > 0) {//  [1,'c0d90d91d4ca5b3d','t',0,0,1,0,1,-1,'eng']
                        JSONObject jsonObject = jsonArrayData.getJSONObject(0);
                        back.onBack(jsonObject.toString());

                    }
                }
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
        mCompositeSubscription.add(disposable);
    }
}
