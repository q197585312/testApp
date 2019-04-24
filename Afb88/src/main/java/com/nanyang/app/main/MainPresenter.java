package com.nanyang.app.main;


import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Been.AppVersionBean;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.load.login.LoginInfo;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Response;

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

    public void getSkipGd88Data() {
        Disposable subscription = getService(ApiService.class).getResponse(BuildConfig.HOST_AFB + "_View/LiveDealerGDC.aspx").subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response responseBodyResponse) throws Exception {
                        okhttp3.Headers headers = responseBodyResponse.headers();
                        String s = headers.get("Location");
                        Log.d("LiveDealerGDC", "响应头 two " + responseBodyResponse.toString());
                        Log.d("LiveDealerGDC", "响应头 two " + responseBodyResponse.headers().toString());
                        String message = responseBodyResponse.message();
                        Log.d("LiveDealerGDC", "响应头 message " + message);
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
//        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "_View/LiveDealerGDC.aspx"), new BaseConsumer<String>(baseContext) {
//            @Override
//            protected void onBaseGetData(String data) {
//                MainActivity mainActivity = (MainActivity) baseContext;
//                if (data.contains("Goto Live Dealer GD Casino")) {
//                    if (ApkUtils.isAvilible(mainActivity, "gaming178.com.baccaratgame")) {
//                        Intent intent = new Intent();
//                        ComponentName comp = new ComponentName("gaming178.com.baccaratgame", "gaming178.com.casinogame.Activity.WelcomeActivity");
//                        intent.setComponent(comp);
//                        PersonalInfo info = mainActivity.getApp().getUser();
//                        intent.putExtra("username", info.getLoginName());
//                        intent.putExtra("password", info.getPassword());
//                        intent.putExtra("language", "en");
//                        intent.putExtra("web_id", "-1");
//                        intent.putExtra("gameType", 5);
//                        intent.putExtra("balance", info.getCredit2());
//                        baseContext.getBaseActivity().startActivity(intent);
//                    } else {
//                        downloadGd88();
//                    }
//                } else {
//                    ToastUtils.showShort("Login Error");
//                }
//            }
//        });
    }

    public void downloadGd88() {
        String url = "http://www.appgd88.com/api/gd88AndroidVersion.php?Labelid=48";
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(url), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                AppVersionBean appVersionBean = gson.fromJson(data, AppVersionBean.class);
                String gd88DownloadUrl = appVersionBean.getData().getUrl();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(gd88DownloadUrl);
                intent.setData(content_url);
                baseContext.getBaseActivity().startActivity(intent);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }
}