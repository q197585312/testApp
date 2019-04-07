package com.nanyang.app.main.home.sport.golf;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.financial.FinancialState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class GolfState extends FinancialState{


    public GolfState(SportContract.View baseView) {
        super(baseView);
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
    public IBetHelper onSetBetHelper() {
        return new GolfBetHelper(getBaseView());
    }

    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> texts = new ArrayList<>();
        List<String> items0 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HANDICAP), getBaseView().getIBaseContext().getBaseActivity().getString(R.string.A_TIE_B)));
        texts.add(items0);
        return texts;
    }

}
