package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.training.ScrollLayout;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/3/10.
 */

public interface IObtainDataState {
    Disposable refresh();
    Disposable startUpdateData();

    void stopUpdateData();

    boolean collection();

    boolean menu(TextView tvMenu);

    boolean mix();

    void onPrevious(SwipeToLoadLayout swipeToLoadLayout);

    void onNext(SwipeToLoadLayout swipeToLoadLayout);

    BaseRecyclerAdapter switchTypeAdapter();

    <I extends IAdapterHelper> I onSetAdapterHelper();

    void setHeaderContent(ScrollLayout slHeader);

    MenuItemInfo getStateType();

    void unSubscribe();

    void notifyDataChanged();
    void switchOddsType(String oddsType);

}
