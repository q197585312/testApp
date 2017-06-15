package com.nanyang.app.main.home.sport.golf;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.financial.FinancialState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class GolfState extends FinancialState{


    public GolfState(SportContract.View baseView) {
        super(baseView);
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
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new GolfBetHelper(getBaseView());
    }

/*    @Override
    public IAdapterHelper<BasketballMixInfo> onSetAdapterHelper() {
        return new GolfAdapterHelper(getBaseView().getContextActivity());
    }*/

}