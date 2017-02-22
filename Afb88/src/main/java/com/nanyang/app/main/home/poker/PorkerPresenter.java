package com.nanyang.app.main.home.poker;

import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

class PorkerPresenter extends BaseRetrofitPresenter implements PorkerContract.Presenter{
    public PorkerPresenter(IBaseView view) {
        super(view);
    }

    @Override
    public ApiPoker createRetrofitApi() {
        return new ApiPoker();
    }

}
