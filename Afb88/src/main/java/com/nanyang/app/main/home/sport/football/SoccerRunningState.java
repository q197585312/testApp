package com.nanyang.app.main.home.sport.football;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.BetGoalWindowUtils;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import java.util.List;

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
        return AppConstant.getInstance().URL_FOOTBALL_RUNNING;
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
        getBaseView().onWebShow(nextNotRepeat, position, item, v);
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
    protected void updateAllDate(List<TableSportInfo<BallInfo>> allData) {

        super.updateAllDate(allData);
      /*  List<TableSportInfo<BallInfo>> noRepeatAllData = handleRepeatData(allData);
        filterData(noRepeatAllData);*/
        Activity activity = getBaseView().getIBaseContext().getBaseActivity();
        for (int i = 0; i < allData.size(); i++) {
            TableSportInfo<BallInfo> ballInfoTableSportInfo = allData.get(i);
            List<BallInfo> rows = ballInfoTableSportInfo.getRows();
            for (int j = 0; j < rows.size(); j++) {
                BallInfo item = rows.get(j);
                int homeTextColor;
                int awayTextColor;
                String isHomeGive = item.getIsHomeGive();
                if (isHomeGive.equals("1")) {
                    homeTextColor = Color.RED;
                    awayTextColor = Color.BLACK;
                } else {
                    homeTextColor = Color.BLACK;
                    awayTextColor = Color.RED;
                }
                String sHome = item.getRunHomeScore();
                String sAway = item.getRunAwayScore();
                if (item.isHomeScoreBigger()) {
                    SoccerRunningGoalManager.getInstance().putHomeGoal(item, true);
                    BetGoalWindowUtils.showGoalWindow(activity, item.getModuleTitle(), item.getHome(), homeTextColor, item.getAway(), awayTextColor, sHome, sAway, 0);
                    item.setHomeScoreBigger(false);


                }
                if (item.isAwayScoreBigger()) {
                    SoccerRunningGoalManager.getInstance().putAwayGoal(item, true);

                    BetGoalWindowUtils.showGoalWindow(activity, item.getModuleTitle(), item.getHome(), homeTextColor, item.getAway(), awayTextColor, sHome, sAway, 1);
                    item.setAwayScoreBigger(false);

                }
            }
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        SoccerRunningGoalManager.getInstance().clear();
    }
}
