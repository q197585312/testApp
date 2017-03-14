package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerTodayState extends SoccerCommonState {
    public SoccerTodayState(SportContract2.View baseView) {
        super(baseView);
    }

    @Override
    public boolean menu() {
        return false;
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerTodayMixState(getBaseView()));
        return true;
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FOOTBALL_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Running":
                getBaseView().switchState(new SoccerRunningState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new SoccerEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new SoccerOutRightState(getBaseView()));
                break;
        }

    }

    @Override
    public int getTypeNameRes() {
        return R.string.Today;
    }

    @Override
    public IAdapterHelper<SoccerCommonInfo> onSetAdapterHelper() {
        SoccerTodayAdapterHelper soccerTodayAdapterHelper = new SoccerTodayAdapterHelper(getBaseView().getContextActivity());
        soccerTodayAdapterHelper.setBallItemCallBack(new BallAdapterHelper.BallItemCallBack<SoccerCommonInfo>() {
            @Override
            public boolean isItemCollection(SoccerCommonInfo item) {
                return isItemCollectionCommon(item);
            }

            @Override
            public void collectionItem(SoccerCommonInfo item) {
                collectionItemCommon(item);
            }

            @Override
            public SoccerCommonInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }
        });
        return soccerTodayAdapterHelper;
    }
}
