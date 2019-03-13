package com.nanyang.app.main.home.sport.tennis;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballCommonState;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.main.OtherDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class TennisState extends BasketballCommonState{


    public TennisState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new BallBetHelper(getBaseView()) {
            @Override
            protected String getBallG() {
                return "21";
            }
        };
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

    }
    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Running), "Running"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }
    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public boolean isMix() {
        return false;
    }



    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> texts = new ArrayList<>();
        List<String> items0 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.TO_WIN), getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HANDICAP)));
        List<String> items1 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.O_U), getBaseView().getIBaseContext().getBaseActivity().getString(R.string.ODD_EVEN)));
        texts.add(items0);
        texts.add(items1);
        return texts;
    }



    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherDoubleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }
    @Override
    public void setScrollHeaderContent(ScrollLayout slHeader, TextView tvAos) {
        super.setScrollHeaderContent(slHeader, tvAos);
        tvAos.setVisibility(View.GONE);
        slHeader.getLayoutParams().width= DeviceUtils.dip2px(getBaseView().getIBaseContext().getBaseActivity(),140);
    }
}
