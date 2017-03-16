package com.nanyang.app.main.home.sport.football;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.MenuListInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BaseSportFragment;
import com.nanyang.app.main.home.sportInterface.SoccerEarlyState;
import com.nanyang.app.main.home.sportInterface.SoccerOutRightState;
import com.nanyang.app.main.home.sportInterface.SoccerRunningState;
import com.nanyang.app.main.home.sportInterface.SoccerTodayState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SoccerFragment extends BaseSportFragment<SoccerPresenter> {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switch (type) {
            case "Running":
                switchState(new SoccerRunningState(this));
                break;
            case "Today":
                switchState(new SoccerTodayState(this));
                break;
            case "Early":
                switchState(new SoccerEarlyState(this));
                break;
            case "OutRight":
                switchState(new SoccerOutRightState(this));
                break;
        }

    }

    private void initAdapter() {
        List<MenuListInfo> listInfos = new ArrayList<>();
        MenuListInfo list1 = new MenuListInfo();
        MenuListInfo list2 = new MenuListInfo();
        list1.setList(Arrays.asList(new MenuItemInfo(1, "FULL   H/A"), new MenuItemInfo(1, "FULL    O/U")));
        list2.setList(Arrays.asList(new MenuItemInfo(1, "HALF   H/A"), new MenuItemInfo(1, "HALF    O/U")));
        listInfos.add(list1);
        listInfos.add(list2);
    }

    @Override
    protected SoccerPresenter onCreatePresenter() {
        return new SoccerPresenter(this);
    }

    @Override
    public String getTitle() {
        return getString(R.string.Soccer);
    }


    @Override
    public void clickAdd(View v, SportInfo item, String type) {
        Bundle b=new Bundle();
        b.putString(AppConstant.KEY_STRING,type);
        b.putSerializable(AppConstant.KEY_DATA,item);
        skipAct(VsActivity.class,b);
    }

}
