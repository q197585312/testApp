package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.MenuItemInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/10.
 */

public interface IObtainDataState {
    void refresh();

    boolean collection();

    void onPrevious(SwipeToLoadLayout swipeToLoadLayout);

    void onNext(SwipeToLoadLayout swipeToLoadLayout);

    BaseRecyclerAdapter switchTypeAdapter(JSONObject jsonObjectNum);

    <I extends IAdapterHelper> I onSetAdapterHelper();

    MenuItemInfo getStateType();

    void unSubscribe();

    void notifyDataChanged();

    void switchOddsType(String oddsType);


    boolean isMix();

    boolean mix();

    void clearMix();

    /* AfbParseHelper<B> afbParseHelper = new AfbParseHelper<B>();
            B ballInfo = afbParseHelper.parseJsonArray(matchArray);
            return ballInfo;*/
    IBetHelper getBetHelper();
}
