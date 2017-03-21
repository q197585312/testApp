package com.nanyang.app.main.home.sport.basketball;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.adapter.VpBallAdapter;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.MenuListInfo;
import com.nanyang.app.myView.LinkedViewPager.MyPagerAdapter;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class BasketballFragmentOld extends BaseSportFragment<BasketballPresenterOld> implements SportContract.View<List<MatchBean>> {
    @Bind(R.id.swipe_target)
    RecyclerView rvContent;
    @Bind(R.id.tv_total_match)
    TextView tvTotalMatch;
    @Bind(R.id.tv_odds_type)
    TextView tvOddsType;

    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @Bind(R.id.tv_mix_parlay_order)
    TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    LinearLayout llMixParlayOrder;
    @Bind(R.id.tv_aos)
    TextView tvAos;

    private VpBallAdapter baseRecyclerAdapter;


    @Override
    public void initData() {
        super.initData();
        initAdapter();

    }

    @Override
    protected BasketballPresenterOld getPresenter() {
        return new BasketballPresenterOld(this);
    }

    private void initAdapter() {
        ViewGroup.LayoutParams layoutParams = vpHeader.getLayoutParams();
        tvAos.setVisibility(View.GONE);
        layoutParams.width = DeviceUtils.dip2px(mContext, 210);
        MyPagerAdapter<MenuListInfo> headerAdapter = headerAdapter();
        List<MenuListInfo> listInfos=new ArrayList<>();
        MenuListInfo list1=new MenuListInfo();
        MenuListInfo list2=new MenuListInfo();
        list1.setList(Arrays.asList(new MenuItemInfo(1,"FULL   H/A"),new MenuItemInfo(1,"FULL    O/U"),new MenuItemInfo(1,"FULL    O/E")));
        list2.setList(Arrays.asList(new MenuItemInfo(1,"HALF   H/A"),new MenuItemInfo(1,"HALF    O/U"),new MenuItemInfo(1,"HALF    O/E")));
        listInfos.add(list1);
        listInfos.add(list2);
        headerAdapter.setDatas(listInfos);
        vpHeader.setAdapter(headerAdapter);


    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData, String type) {
        baseRecyclerAdapter.addAllAndClear(pageData);
        String size = pageData.size() + "";
        tvTotalMatch.setText(size);
        ((BaseToolbarActivity) getActivity()).getTvToolbarTitle().setText(type);
        if (presenter.isMixParlay()) {
            llMixParlayOrder.setVisibility(View.VISIBLE);
        } else {
            llMixParlayOrder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean allData, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {

    }

    @Override
    public void onAddMixFailed(String message) {

    }




    @Override
    public void onRightMarkClick(Bundle b) {

    }

    @Override
    public void onCountBet() {

    }



    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }


    @Override
    public String getTitle() {
        return getString(R.string.Basketball);
    }


    @Override
    public void onGetData(List<MatchBean> data) {

    }

    @OnClick(R.id.tv_odds_type)
    public void onClick(View v) {
        clickOddsType(v);
    }

    @Override
    public boolean mixParlayCLick(TextView tvMix) {
        presenter.mixParlay();
        if (presenter.isMixParlay())
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_oval_u_green, 0, 0);
        else
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_oval_u_black, 0, 0);
        return presenter.isMixParlay();
    }
}
