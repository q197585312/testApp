package com.nanyang.app.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.home.sport.main.SportContract;
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

public class LoadMainDataHelper<T extends LoginInfo.LanguageWfBean> {
    private CompositeDisposable mCompositeSubscription;
    private BaseActivity baseContext;
    private Api mApiWrapper;

    public LoadMainDataHelper(Api mApiWrapper, BaseActivity baseContext, CompositeDisposable mCompositeSubscription) {
        this.mApiWrapper = mApiWrapper;
        this.baseContext = baseContext;
        this.mCompositeSubscription = mCompositeSubscription;
    }

    public void doRetrofitApiOnUiThread(T languageWfBean, final LanguagePresenter.CallBack<String> back) {
        String p = BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson();

        Log.d("doRetrofitApiOnUiThread", "doRetrofitApiOnUiThread: " + p);
        Disposable disposable = mApiWrapper.applyDisposable(Api.getService(ApiService.class).getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                Log.d("doRetrofitApiOnUiThread", "data: " + data);
                if (data.contains("Maintenance")) {
                    ((BaseToolbarActivity) baseContext).reLoginPrompt(baseContext.getBaseActivity().getString(R.string.System_maintenance), new SportContract.CallBack() {
                        @Override
                        public void clickCancel(View v) {
                            Intent intent = new Intent(baseContext.getBaseActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            baseContext.getBaseActivity().startActivity(intent);
                        }
                    });
                    return;
                }
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
