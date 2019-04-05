package com.nanyang.app.main.BetCenter.Presenter;

import com.nanyang.app.ApiService;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.BetCenter.Bean.BaseParamBean;
import com.nanyang.app.main.BetCenter.StatementNewFragment;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/4/4.
 */

public class StatementNewPresenter extends BaseRetrofitPresenter<StatementNewFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public StatementNewPresenter(StatementNewFragment iBaseContext) {
        super(iBaseContext);
    }

    public void getStatementDate(BaseConsumer<String> baseConsumer) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap("https://www.afb1188.com/H50/Pub/pcode.axd", new BaseParamBean().getFmMp("GetTT", "wfStatement2H50")), baseConsumer);
    }
}
