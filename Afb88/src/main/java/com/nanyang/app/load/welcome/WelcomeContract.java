package com.nanyang.app.load.welcome;

import com.nanyang.app.common.ILanguageView;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

import java.io.File;

public interface WelcomeContract {
    interface View extends ILanguageView<String> {
       void onFailed(String error);
        void onLoadingApk(int len, long contentLength);
        void onLoadError(String error);
        void onLoadEnd(File file);

    }

    interface Presenter extends IBasePresenter {
        void checkVersion(String versionName);
        void updateVersion(String version);
    }
}