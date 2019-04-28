package com.nanyang.app.main.home.sport.basketball;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.football.SoccerRunningAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BasketballRunningState extends BasketballCommonState {
    public BasketballRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_BASKETBALL_RUNNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new BasketballEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new BasketballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new BasketballOutRightState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Basketball));
    }


    @Override
    protected IAdapterHelper<BallInfo> onSetCommonAdapterHelper() {
        return new SoccerRunningAdapterHelper(getBaseView().getIBaseContext().getBaseActivity()) {
            @Override
            protected void handleLiveTimeTv(BallInfo item, TextView timeTv) {
                String live = item.getLive();
                if (live.contains("HT") || live.contains("PEN") || live.contains("LIVE")) {
                    String replace = live.replace("\n", ",");
                    String[] split = replace.split(",");
                    timeTv.setText(split[1]);
                    if (live.contains("HT")) {
                        timeTv.setTextColor(Color.BLUE);
                    } else {
                        timeTv.setTextColor(Color.RED);
                    }
                } else {
                    timeTv.setText("");
                }
            }
        };
    /*    return new BasketballCommonAdapterHelper(getBaseView().getIBaseContext().getBaseActivity()) {
            @Override
            public void onConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {
                super.onConvert(helper, position, item);
                setRunningItemBg(helper, item);
            }
        };*/
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new BasketballRunningBetHelper(getBaseView());
    }

    @Override
    public int getTitleContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content1);
    }

    @Override
    public int getNormalContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content2);
    }
}
