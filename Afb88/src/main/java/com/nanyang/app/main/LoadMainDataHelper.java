package com.nanyang.app.main;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.DepositAndWithdraw.Bean.UserCashBean;
import com.nanyang.app.main.Setting.RefreshDataBean;
import com.unkonw.testapp.libs.api.ApiManager;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ASUS on 2019/4/16.
 */

public class LoadMainDataHelper<T extends LoginInfo.LanguageWfBean> {
    private CompositeDisposable mCompositeSubscription;
    private BaseActivity baseContext;
    private ApiManager mApiWrapper;

    public LoadMainDataHelper(ApiManager mApiWrapper, BaseActivity baseContext, CompositeDisposable mCompositeSubscription) {
        this.mApiWrapper = mApiWrapper;
        this.baseContext = baseContext;
        this.mCompositeSubscription = mCompositeSubscription;
    }

    public Disposable doRetrofitApiOnUiThread(T languageWfBean, final MainPresenter.CallBack<String> back) {
        return doRetrofitApiOnUiThread(languageWfBean, back, "");
    }


    public Disposable doRetrofitApiOnUiThread(T languageWfBean, final MainPresenter.CallBack<String> back, final String matches) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson();

        Log.d("doRetrofitApiOnUiThread", "doRetrofitApiOnUiThread: " + p);
        String authorization = ((BaseToolbarActivity) (baseContext.getBaseActivity())).getApp().getAuthorization();
        Map<String, String> headers = new HashMap<>();
//        headers.put("isios", "true");
        if (!TextUtils.isEmpty(authorization)){
            headers.put("authorization", authorization);
        }
        Disposable disposable = mApiWrapper.applyDisposable(ApiServiceKt.Companion.getInstance().getData(p,headers), new BaseConsumer<String>(baseContext) {
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
                            /*CreatWS(\"ws://ws2.afb1188.net:8888/FnChat\");*/
                            /*"^.*\"(http[^\"]+)\",.*$"*/
                            String ws = StringUtils.findGroup(s2, "^.*CreatWS\\(\"([^\"]+)\\\".*$", 1);
                            JSONArray settlTypeCashArr = jsonArray.getJSONArray(3);
                            String settlTypeCashStr = settlTypeCashArr.getJSONObject(0).toString();
                            UserCashBean userCashBean = new Gson().fromJson(settlTypeCashStr, UserCashBean.class);
                            ((BaseToolbarActivity) (baseContext.getBaseActivity())).getApp().setUserCashBean(userCashBean);
                            if (!StringUtils.isNull(ws) && ws.startsWith("ws")) {
                                AppConstant.getInstance().WebSocket_HOST = ws;
                            }
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
                }
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
        mCompositeSubscription.add(disposable);
        return disposable;
    }

    public void doRetrofitApiOnUiThreadHTML(T languageWfBean, final MainPresenter.CallBack<String> back) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson();

        Log.d("doRetrofitApiOnUiThread", "doRetrofitApiOnUiThread: " + p);
        Disposable disposable = mApiWrapper.applyDisposable(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                Log.d("doRetrofitApiOnUiThread", "data: " + data);
                JSONArray jsonArray = new JSONArray(data);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData3 = jsonArray.getJSONArray(3);
                    back.onBack(jsonArrayData3.get(0).toString());
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
