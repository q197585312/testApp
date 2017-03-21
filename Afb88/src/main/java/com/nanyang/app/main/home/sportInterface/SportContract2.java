package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.List;

public interface SportContract2  {
    interface View<B extends SportInfo> extends BetView<List<B>>{
        void setAdapter(BaseRecyclerAdapter<B> baseRecyclerAdapter);
        void switchState(IObtainDataState state);
        void onGetFollowers(List<ScrollLayout> followers);
        void clickItemAdd(android.view.View v, B item, String type);

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