package com.nanyang.app.main.home.sport.basketball;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportActivity;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.VpBallAdapter;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class BasketballFragment extends BaseSportFragment<BasketballPresenter> implements SportContract.View<List<MatchBean>> {
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
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    private VpBallAdapter baseRecyclerAdapter;


    @Override
    public void initData() {
        super.initData();

        createPresenter(new BasketballPresenter(this));
        presenter.setType(((SportActivity) getActivity()).getType());
        presenter.refresh(((SportActivity) getActivity()).getType());
        initAdapter();

    }

    private void initAdapter() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);

        swipeTarget.setLayoutManager(mLayoutManager);
        baseRecyclerAdapter = new VpBallAdapter(mContext, new ArrayList<MatchBean>(), R.layout.sport_match_item);
        baseRecyclerAdapter.setVpHeader(vpHeader);
        baseRecyclerAdapter.setPresenter(presenter);
        swipeTarget.setAdapter(baseRecyclerAdapter);

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

        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
    public void onGetBetSucceed(BettingPromptBean allData) {

    }

    @Override
    public void onBetSucceed(String allData) {

    }

    @Override
    public void onRightMarkClick(Bundle b) {

    }

    @Override
    public void onCountBet() {

    }

    @Override
    public void onCreatePopupWindow(BetBasePop betPop) {

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
    public void toolbarRightClick(View v) {

    }

    @Override
    public void onGetData(List<MatchBean> data) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
