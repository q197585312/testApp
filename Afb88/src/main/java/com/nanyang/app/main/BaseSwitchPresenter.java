package com.nanyang.app.main;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.BetCenter.Bean.Contact;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import static com.unkonw.testapp.libs.api.Api.getService;

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
        doRetrofitApiOnUiThread(getService(ApiService.class).getContactData("http://www.appgd88.com/api/afb1188.php?app=afb1188"), bc);
    }

    public void getStatementData(BaseConsumer<String> bc) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(AppConstant.getInstance().URL_STAEMENT), bc);
    }

    public void confirmBlance(BaseConsumer<String> bc, String url) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(url), bc);
    }
}
