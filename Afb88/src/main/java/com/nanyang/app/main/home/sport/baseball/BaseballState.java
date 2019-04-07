package com.nanyang.app.main.home.sport.baseball;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballCommonAdapterHelper;
import com.nanyang.app.main.home.sport.basketball.BasketballCommonState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class BaseballState extends BasketballCommonState {

    public BaseballState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new BaseballBetHelper(getBaseView());
    }

    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new BaseballAdapter(getBaseView().getIBaseContext().getBaseActivity());

    }
    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> texts = new ArrayList<>();
        List<String> items0 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FULL_1X2)
                , getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FULL_H_A)
                , getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FULL_O_U)
        ));
        List<String> items1 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FULL_O_E)
                , getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FIRST5_H_A)
                , getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FIRST5_O_U)
        ));
        texts.add(items0);
        texts.add(items1);
        return texts;
    }
    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running"));
//        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }
    @Override
    protected BasketballCommonAdapterHelper onSetCommonAdapterHelper() {
        return null;
    }
    @Override
    public boolean mix() {
        return false;
    }

}
