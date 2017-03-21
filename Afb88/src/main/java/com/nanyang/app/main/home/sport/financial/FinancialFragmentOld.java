package com.nanyang.app.main.home.sport.financial;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.adapter.VpBallAdapter;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class FinancialFragmentOld extends BaseSportFragment<FinancialPresenterOld> implements SportContract.View<List<MatchBean>> {
    @Bind(R.id.swipe_target)
    RecyclerView rvContent;
    @Bind(R.id.tv_total_match)
    TextView tvTotalMatch;
    @Bind(R.id.tv_odds_type)
    TextView tvOddsType;
    @Bind(R.id.vp_header)
    ViewPager vpHeader;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @Bind(R.id.tv_mix_parlay_order)
    TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    LinearLayout llMixParlayOrder;
    private VpBallAdapter baseRecyclerAdapter;

    @Override
    public void initData() {
        super.initData();

        initAdapter();

    }

    @Override
    protected FinancialPresenterOld getPresenter() {
        return    new FinancialPresenterOld(this);
    }

    private void initAdapter() {

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
        return getString(R.string.Financial);
    }




    @Override
    public void onGetData(List<MatchBean> data) {

    }

    @OnClick(R.id.tv_odds_type)
    public void onClick(View v) {
        clickOddsType(v);
    }
}
