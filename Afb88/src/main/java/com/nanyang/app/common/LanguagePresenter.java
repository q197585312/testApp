package com.nanyang.app.common;

import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

/**
 * Created by Administrator on 2017/4/19.
 */

public class LanguagePresenter extends BaseRetrofitPresenter<String, ILanguageView<String>> {
    SwitchLanguage switchLanguage;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public LanguagePresenter(ILanguageView<String> view) {
        super(view);
        switchLanguage = new SwitchLanguage(view, mCompositeSubscription);
    }

    public void switchLanguage(String lang) {
        switchLanguage.switchLanguage(lang);
    }
}
