package com.nanyang.app.main.home.sport.muayThai;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OtherDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.financial.FinancialState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class MuayThaiState extends FinancialState{

    public MuayThaiState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new MuayThaiBetHelper(getBaseView());
    }

    @Override
    public IAdapterHelper<BasketballMixInfo> onSetAdapterHelper() {
        return new OtherDoubleAdapterHelper(getBaseView().getContextActivity());
    }
    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Running), "Running"));
        return types;
    }
}
