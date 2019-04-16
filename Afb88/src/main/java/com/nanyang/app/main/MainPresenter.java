package com.nanyang.app.main;


import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.load.login.LoginInfo;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

public class MainPresenter extends BaseRetrofitPresenter<MainActivity> {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    MainPresenter(MainActivity view) {
        super(view);
    }


    public void oddsType() {
        Disposable subscription = getService(ApiService.class).getData(AppConstant.getInstance().URL_ODDS_TYPE + "MY").subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {

                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);

    }

    public interface CallBack<T> {
        void onBack(T data);
    }

    public void loadAllMainData(LoginInfo.LanguageWfBean languageWfBean, final CallBack<String> back) {
        LoadMainDataHelper helper = new LoadMainDataHelper(mApiWrapper, baseContext, mCompositeSubscription);
        helper.doRetrofitApiOnUiThread(languageWfBean, back);
      /*  doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson()), new BaseConsumer<String>(baseContext) {
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
        });*/
    }

}