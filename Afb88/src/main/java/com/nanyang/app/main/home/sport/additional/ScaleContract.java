package com.nanyang.app.main.home.sport.additional;

import com.nanyang.app.main.home.sport.BetPresenter;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

public interface ScaleContract {
    interface View extends IBaseView<BettingParPromptBean> {
       void onFailed(String error);
    }

    interface Presenter extends IBasePresenter,BetPresenter {


    }
}