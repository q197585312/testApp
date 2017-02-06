package com.nanyang.app.load.welcome;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public interface WelcomeContract {
    interface View extends IBaseView<String> {
       void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        void checkVersion(String versionName);
        Flowable<ResponseBody> updateVersion();
    }
}