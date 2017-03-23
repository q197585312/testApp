package com.nanyang.app.main.home.sport.myanmarOdds;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/3/21.
 */

public class MyanmarRunningState extends MyanmarState {
    public MyanmarRunningState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Running),"Running",getBaseView().getContextActivity().getString(R.string.Myanmar_Odds));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_SOCCER_MYANMAR_RUNNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new MyanmarEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new MyanmarRunningState(getBaseView()));
                break;
        }
    }

    @Override
    public IAdapterHelper<MyanmarInfo> onSetAdapterHelper() {
        return new MyanmarRunningAdapterHelper(getBaseView().getContextActivity());
    }

    @Override
    public void setScrollHeaderContent(ScrollLayout slHeader, TextView tvAos) {
        super.setScrollHeaderContent(slHeader, tvAos);
        tvAos.setVisibility(View.GONE);
    }
}
