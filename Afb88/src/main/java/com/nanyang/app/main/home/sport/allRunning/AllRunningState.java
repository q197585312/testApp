package com.nanyang.app.main.home.sport.allRunning;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by ASUS on 2019/4/19.
 */

class AllRunningState extends AllCommonState {
    public AllRunningState(AllFragment allFragment) {
        super(allFragment);
    }

    @Override
    public <I extends IAdapterHelper> I onSetAdapterHelper() {
        return null;
    }

    @Override
    public MenuItemInfo getStateType() {
        return null;
    }

    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return null;
    }

    @Override
    protected String getRefreshUrl() {
        return null;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

    }

    @Override
    public IBetHelper onSetBetHelper() {
        return null;
    }
}
