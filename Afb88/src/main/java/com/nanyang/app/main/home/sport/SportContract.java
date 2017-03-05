package com.nanyang.app.main.home.sport;

import android.os.Bundle;

import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

import java.util.Map;

public interface SportContract {
    interface View<T> extends IBaseView<T> {
       void onFailed(String error);
        void onPageData(int page, T pageData,String type);
        void onUpdateMixSucceed(BettingParPromptBean allData, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item);
        void onAddMixFailed(String message);
        void onGetBetSucceed(BettingPromptBean allData);
        void onBetSucceed(String allData);
        void onRightMarkClick(Bundle b);
        void onCountBet();

        void onCreatePopupWindow(BetBasePop betPop);
    }

    interface Presenter extends IBasePresenter ,BetPresenter {
        void refresh(String type);
        void collection();
        void menu();
        void mixParlay();

    }
}