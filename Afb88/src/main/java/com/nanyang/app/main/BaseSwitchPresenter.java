package com.nanyang.app.main;

import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.main.BetCenter.Bean.Contact;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

/**
 * Created by Administrator on 2019/4/3.
 */

public class BaseSwitchPresenter extends BaseRetrofitPresenter<IBaseContext> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public BaseSwitchPresenter(IBaseContext iBaseContext) {
        super(iBaseContext);
    }

    public void getContactData(BaseConsumer<Contact> bc) {
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getContactData(BuildConfig.Contact_URL), bc);
    }

}
