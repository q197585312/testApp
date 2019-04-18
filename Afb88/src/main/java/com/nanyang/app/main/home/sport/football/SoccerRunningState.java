package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerRunningState extends SoccerCommonState {
    public SoccerRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FOOTBALL_RUNNING + param.getType();
    }

    @Override
    protected String getAllOddsUrl() {
        return AppConstant.getInstance().HOST+ "_view/OddsPageSetting.aspx?ot=r&ov=0&wd=&tf=-1&isPageSingDouble=RMOdds1&m=save";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new SoccerEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new SoccerTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new SoccerOutRightState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.football));
    }

    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerRunningAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new SoccerRunningBetHelper(getBaseView());
    }

    protected void clickHallBtn(View v, BallInfo item, int position) {
        int nextNotRepeat = ((SoccerRunningAdapterHelper) getAdapterHelper()).getNextNotRepeat(position);
        getBaseView().onWebShow(nextNotRepeat,position,item,v);
    }

    @Override
    public int getTitleContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(),R.color.green_content1);
    }

    @Override
    public int getNormalContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(),R.color.green_content2);
    }
}
