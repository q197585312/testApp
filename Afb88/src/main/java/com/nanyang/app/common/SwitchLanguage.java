package com.nanyang.app.common;

import android.support.annotation.NonNull;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.load.login.LoginInfo;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/4/19.
 */

public class SwitchLanguage<V extends IBaseContext> extends BaseRetrofitPresenter<V> {
    ILanguageView<String> baseView;
    CompositeDisposable mCompositeSubscription;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param IBaseActivity
     */
    public SwitchLanguage(V IBaseActivity) {
        super(IBaseActivity);
    }


    @NonNull
    private String getLanguage() {
        LanguageHelper helper = new LanguageHelper(baseContext.getBaseActivity());
        return helper.getLanguage();
    }


    public Flowable<String> switchLanguage(String lang) {
//     http://main55.afb88.com/Main.aspx?lang=EN-US
//         {"ACT":"GetTT","lang":"EN-US","accType":"","pgLable":"0.8736397885598416","vsn":"4.0.121","PT":"wfMain0"}
        if (BuildConfig.FLAVOR.equals("afb1188")) {
            return getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, new LoginInfo().getWfLanguage(getLanguage()));
        }
        return Api.getService(ApiService.class).switchLanguage(AppConstant.getInstance().URL_CHANGE_LANGUAGE, lang).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    public Flowable<String> switchLanguage(String lang, String accType) {
//     http://main55.afb88.com/Main.aspx?lang=EN-US
//         {"ACT":"GetTT","lang":"EN-US","accType":"","pgLable":"0.8736397885598416","vsn":"4.0.121","PT":"wfMain0"}
        return getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, new LoginInfo().getWfLanguage(lang, accType));

    }
}
