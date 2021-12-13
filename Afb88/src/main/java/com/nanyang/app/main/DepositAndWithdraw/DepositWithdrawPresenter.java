package com.nanyang.app.main.DepositAndWithdraw;

import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

public class DepositWithdrawPresenter extends BaseRetrofitPresenter<DepositWithdrawBaseFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */

    private DepositWithdrawBaseFragment depositWithdrawBaseFragment;

    public DepositWithdrawPresenter(DepositWithdrawBaseFragment iBaseContext) {
        super(iBaseContext);
        depositWithdrawBaseFragment = iBaseContext;
    }
}
