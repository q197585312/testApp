package com.nanyang.app.main.home.sport.football;

import androidx.core.content.ContextCompat;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerRunningState extends  SoccerCommonState {
    public SoccerRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getAllOddsUrl() {
        String tfDate = ((BaseToolbarActivity) getBaseView().getIBaseContext().getBaseActivity()).getApp().getUser().getTfDate();
        return AppConstant.getInstance().HOST + "_view/OddsPageSetting.aspx?ot=r&ov=0&wd=" + tfDate + "&tf=-1&isPageSingDouble=RMOdds1&m=save";
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
        return new MenuItemInfo<String>(0, (R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.football));
    }

    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerRunningAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new SoccerRunningBetHelper(getBaseView());
    }


    @Override
    public int getTitleContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content1);
    }

    @Override
    public int getNormalContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content2);
    }

    @Override
    protected void updateTableDate(List<TableSportInfo<BallInfo>> allData) {

        super.updateTableDate(allData);

    }

    @Override
    public void refresh() {
        super.refresh();
        SoccerRunningGoalManager.getInstance().clear();
    }
}
