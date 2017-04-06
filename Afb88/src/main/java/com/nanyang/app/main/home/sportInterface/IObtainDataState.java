package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/3/10.
 */

public interface IObtainDataState {
    void refresh();

    void startUpdateData();

    void stopUpdateData();

    boolean collection();

    boolean menu(TextView tvMenu);

    void onPrevious(SwipeToLoadLayout swipeToLoadLayout);

    void onNext(SwipeToLoadLayout swipeToLoadLayout);

    BaseRecyclerAdapter switchTypeAdapter();

    <I extends IAdapterHelper> I onSetAdapterHelper();

    MenuItemInfo getStateType();

    void unSubscribe();

    void notifyDataChanged();

    void switchOddsType(String oddsType);

    void setScrollHeaderContent(ScrollLayout slHeader, TextView tvAos);

    boolean isMix();

    boolean mix();

    void clearMix();
}
