package com.nanyang.app.main;

import android.util.Log;

import com.google.gson.Gson;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.Setting.RefreshDataBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;

import org.json.JSONArray;
import org.json.JSONException;

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

    public void doRetrofitApiOnUiThread(T languageWfBean, final MainPresenter.CallBack<String> back) {
        doRetrofitApiOnUiThread(languageWfBean, back, "");
    }

    public void doRetrofitApiOnUiThread(T languageWfBean, final MainPresenter.CallBack<String> back, final String matches) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson();

        Log.d("doRetrofitApiOnUiThread", "doRetrofitApiOnUiThread: " + p);
        Disposable disposable = mApiWrapper.applyDisposable(Api.getService(ApiService.class).getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                Log.d("doRetrofitApiOnUiThread", "data: " + data);
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    String s2 = jsonArray.getString(2);
                    if (!StringUtils.isNull(matches)) {
                        String group = StringUtils.findGroup(s2, matches, 1);
                        if (!StringUtils.isNull(group)) {
                            RefreshDataBean refreshDataBean = new Gson().fromJson(group, RefreshDataBean.class);
                            refreshDataBean.setACT("LOS");
                            refreshDataBean.setFAV("");
                            refreshDataBean.setSL("");
                            refreshDataBean.setTf("-1");
                            refreshDataBean.setTransMax("50");

                            refreshDataBean.setTp("1");
                            refreshDataBean.setFh(false);
                            refreshDataBean.setToday(false);
                            ((AfbApplication) baseContext.getBaseActivity().getApplication()).setRefreshDataBean(refreshDataBean);
                        }

                    }
                    JSONArray jsonArrayData3 = jsonArray.getJSONArray(3);
                    back.onBack(jsonArrayData3.get(0).toString());
                  /*  if (jsonArrayData3.length() > 0) {//  [1,'c0d90d91d4ca5b3d','t',0,0,1,0,1,-1,'eng']
//                        JSONObject jsonObject = jsonArrayData3.getJSONObject(0);
                        back.onBack(jsonArrayData3.get(0).toString());

                    }*/
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
