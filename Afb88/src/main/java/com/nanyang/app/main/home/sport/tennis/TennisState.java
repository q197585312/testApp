package com.nanyang.app.main.home.sport.tennis;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.basketball.BasketballMixState;
import com.nanyang.app.main.home.sport.main.OtherAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class TennisState extends BasketballMixState{


    public TennisState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected void onTypeClick(MenuItemInfo item) {

    }
    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Running), "Running"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }
    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public boolean isMix() {
        return false;
    }

    @Override
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new TennisBetHelper(getBaseView());
    }

    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> lists = super.initHeaderList();
        lists.get(0).set(0,getBaseView().getContextActivity().getString(R.string.HANDICAP));
        lists.get(0).set(1,getBaseView().getContextActivity().getString(R.string.OVER_UNDER));
        lists.get(1).set(0,getBaseView().getContextActivity().getString(R.string.TO_WIN));
        lists.get(1).set(1,getBaseView().getContextActivity().getString(R.string.ODD_EVEN));
        return lists;
    }

    @Override
    public void setScrollHeaderContent(ScrollLayout slHeader, TextView tvAos) {
        super.setScrollHeaderContent(slHeader, tvAos);
        tvAos.setVisibility(View.GONE);
    }
    @Override
    public IAdapterHelper<BasketballMixInfo> onSetAdapterHelper() {
        return new OtherAdapterHelper(getBaseView().getContextActivity());
    }
}
