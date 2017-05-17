package com.nanyang.app.main.home.sport.football;

import android.view.Gravity;
import android.view.View;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.dialog.WebPop;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import cn.finalteam.toolsfinal.DeviceUtils;

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
        return AppConstant.URL_FOOTBALL_RUNNING + param.getType();
    }

    @Override
    protected String getAllOddsUrl() {
        return AppConstant.HOST + "_view/OddsPageSetting.aspx?ot=r&ov=0&wd=&tf=-1&isPageSingDouble=RMOdds1&m=save";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
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
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Running), "Running", getBaseView().getContextActivity().getString(R.string.football));
    }


    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerRunningAdapterHelper(getBaseView().getContextActivity());
    }

    @Override
    protected IBetHelper onSetBetHelper() {
        return new SoccerRunningBetHelper(getBaseView());
    }

    protected void clickHallBtn(View v, final SoccerCommonInfo item) {
        WebPop pop = new WebPop(getBaseView().getContextActivity(), v);
        pop.setTrans(1);
        String lag = AfbUtils.getLanguage(getBaseView().getContextActivity());
        String l = "eng";
        if (lag.equals("zh")) {
            l = "eng";
        } else {
            l = "EN-US";
        }

        String gameUrl = AppConstant.URL_RUNNING_MATCH_WEB + "?Id=" + item.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(item.getHome()) + "&Away=" + StringUtils.URLEncode(item.getAway()) + "&L=" + l;
        pop.setUrl(gameUrl);

        int heightPixels = DeviceUtils.getScreenPix(getBaseView().getContextActivity()).heightPixels;
        int widthPixels = DeviceUtils.getScreenPix(getBaseView().getContextActivity()).widthPixels;
        int[] location = new int[2];
        v.getLocationOnScreen(location);

        if(location[1]<heightPixels/2){
            getBaseView().onPopupWindowCreated(pop, -2);
        }
        else{
            showUp( v, pop,location, widthPixels,1300);
        }

    }

    public void showUp(View v,WebPop pop,  int[] location, int popupWidth , int popupHeight) {

        //在控件上方显示
        pop.showAtLocation(Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }
}
