package com.nanyang.app.main.home.sport;

import android.content.Context;
import android.os.Bundle;

import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.OutRightMatchBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

import java.util.List;
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

        void onOutRightData(int page, List<OutRightMatchBean> outRightMatchBeen, String type);

        Context getContext();

        <I extends SportInfo> void setAdapter(BaseRecyclerAdapter<I> baseRecyclerAdapter);
    }

    interface Presenter extends IBasePresenter ,BetPresenter {
        void refresh(String type);
        void collection();
        void menu();
        void mixParlay();

    }
}