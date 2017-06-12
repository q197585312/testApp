package com.nanyang.app.main.home.sport.superCombo;

import android.view.View;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.football.SoccerMixState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class SuperComboState extends SoccerMixState{

    public SuperComboState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    protected IBetHelper<SoccerMixInfo> onSetBetHelper() {
        return new SuperComboBetHelper(getBaseView());
    }
    @Override
    protected void clickAdd(View v, SoccerMixInfo item) {
        getBaseView().clickItemAdd(v,item,"common");
    }
    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Running), "Running"));
        return types;
    }
    @Override
    public boolean isMix() {
        return false;
    }
    @Override
    public boolean mix() {
        return false;
    }
}
