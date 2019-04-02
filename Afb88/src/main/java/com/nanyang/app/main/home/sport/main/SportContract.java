package com.nanyang.app.main.home.sport.main;

import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IObtainDataState;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.List;

public interface SportContract {
    interface View<B extends SportInfo> extends BetView<List<B>> {
        void setAdapter(BaseRecyclerAdapter<B> baseRecyclerAdapter);
        void switchState(IObtainDataState state);
        void clickItemAdd(android.view.View v, B item, int position);
        ScrollLayout onSetScrollHeader();
        void checkMix(boolean mix);
        void reLoginPrompt(String str,CallBack back);
        void onWebShow(int nextNotRepeat, int position, IRTMatchInfo item, android.view.View v);
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

    public interface CallBack {
        void clickCancel(android.view.View v);
    }
}