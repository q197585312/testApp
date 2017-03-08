package com.nanyang.app.main.home.sport.thaiboxing;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportActivity;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.adapter.VpBallAdapter;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/3.
 */

public class ThaiBoxingFragment extends BaseSportFragment<ThaiBoxingPresenter> implements SportContract.View<List<MatchBean>>{
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
        createPresenter(new ThaiBoxingPresenter(this));
        presenter.setType(((SportActivity) getActivity()).getType());
        presenter.refresh(((SportActivity) getActivity()).getType());
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);

        rvContent.setLayoutManager(mLayoutManager);
        baseRecyclerAdapter = new VpBallAdapter(mContext, new ArrayList<MatchBean>(), R.layout.sport_match_item);
        baseRecyclerAdapter.setVpHeader(vpHeader);
        baseRecyclerAdapter.setPresenter(presenter);
        rvContent.setAdapter(baseRecyclerAdapter);

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onPrevious(swipeToLoadLayout);
            }
        });
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.onNext(swipeToLoadLayout);
            }
        });

        rvContent.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }

    @Override
    public void onFailed(String error) {
        
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
        return getString(R.string.Muay_Thai);
    }

    @Override
    public void onGetData(List<MatchBean> data) {

    }
}
