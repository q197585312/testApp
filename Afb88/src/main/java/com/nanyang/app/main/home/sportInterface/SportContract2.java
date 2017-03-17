package com.nanyang.app.main.home.sportInterface;

import android.app.Activity;

import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.List;

public interface SportContract2  {
    interface View<B extends SportInfo> extends IBaseView<List<B>>{
        Activity getContextActivity();
        void setAdapter(BaseRecyclerAdapter<B> baseRecyclerAdapter);
        void onFailed(String message);
        void switchState(IObtainDataState state);
        void onGetFollowers(List<ScrollLayout> followers);
        void onUpdateMixSucceed(BettingParPromptBean bean);
        void clickAdd(android.view.View v, B item, String type);
        void onPopupWindowCreated(BasePopupWindow pop, int center);
    }

    interface Presenter extends IBasePresenter  {
//        void refresh();
//        void collection();
//        void menu();
//        boolean mixParlay();
//        void startUpdate();
//        void stopUpdate();
//        void onPrevious(SwipeToLoadLayout swipeToLoadLayout);
//        void onNext(SwipeToLoadLayout swipeToLoadLayout);
    }
}