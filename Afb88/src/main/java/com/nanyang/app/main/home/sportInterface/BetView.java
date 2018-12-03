package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.unkonw.testapp.libs.view.IBaseView;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

public interface BetView<B> extends IBaseView<B> {

    void onUpdateMixSucceed(AfbClickResponseBean bean);

    void onPopupWindowCreated(BasePopupWindow pop, int center);

    void onBetSuccess(String betResult);
}