package com.nanyang.app.main.home.sport.live;

import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

/**
 * Created by Administrator on 2019/11/28.
 */

class LiveWebPresenter extends BaseRetrofitPresenter<IBaseContext> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public LiveWebPresenter(IBaseContext iBaseContext) {
        super(iBaseContext);
    }
}
