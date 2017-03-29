package com.nanyang.app.main.home.sportInterface;

import android.app.Activity;

import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.unkonw.testapp.libs.view.IBaseView;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

public interface BetView<B> extends IBaseView<B> {
    Activity getContextActivity();

    void onUpdateMixSucceed(BettingParPromptBean bean);

    void onPopupWindowCreated(BasePopupWindow pop, int center);

    void onBetSuccess(String betResult);
}