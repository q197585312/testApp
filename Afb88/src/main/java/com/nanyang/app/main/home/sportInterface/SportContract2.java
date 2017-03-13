package com.nanyang.app.main.home.sportInterface;

import android.app.Activity;

import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import java.util.List;

public interface SportContract2  {
    interface View<B extends SportInfo> extends IBaseView<List<B>>{
        Activity getActivityContext();
        void setAdapter(BaseRecyclerAdapter<B> baseRecyclerAdapter);
        void onFailed(String message);
        void switchState(IObtainDataState state);
    }

    interface Presenter extends IBasePresenter  {
        void refresh();
        void collection();
        void menu();
        boolean mixParlay();
        void startUpdate();
        void stopUpdate();
        void onPrevious(SwipeToLoadLayout swipeToLoadLayout);
        void onNext(SwipeToLoadLayout swipeToLoadLayout);
    }
}