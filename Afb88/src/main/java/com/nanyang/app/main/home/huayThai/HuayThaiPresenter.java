package com.nanyang.app.main.home.huayThai;

import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HuayThaiPresenter extends BaseRetrofitPresenter<HuayThaiFragment> {


    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param IBaseActivity
     */
    public HuayThaiPresenter(HuayThaiFragment IBaseActivity) {
        super(IBaseActivity);
    }


    public void refresh(String url, BaseConsumer<HuayDrawDateInfo> baseConsumer) {
        doRetrofitApiOnUiThread(Api.getService(ApiService.class).getHuyaDrawDate(url), baseConsumer);
    }


    public void submitBet(String url, HuayThaiBetSubmitBean info, BaseConsumer<ResultBean> baseConsumer) {
        doRetrofitApiOnUiThread(Api.getService(ApiService.class).doHuayMap(url, info.getThaiThousandBetSubmitMap()), baseConsumer);
    }

}
