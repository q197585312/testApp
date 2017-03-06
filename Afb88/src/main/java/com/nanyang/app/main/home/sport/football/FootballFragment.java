package com.nanyang.app.main.home.sport.football;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportActivity;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.adapter.VpBallAdapter;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.LinkedViewPager.MyPagerAdapter;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.utils.ViewHolder;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

//import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;


public class FootballFragment extends BaseSportFragment<FootballPresenter> implements SportContract.View<List<MatchBean>> {

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


    VpBallAdapter baseRecyclerAdapter;
    @Bind(R.id.tv_mix_parlay_order)
    TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    LinearLayout llMixParlayOrder;
    private BetBasePop betPop;


    @Override
    public void initData() {
        super.initData();
        createPresenter(new FootballPresenter(this));
        presenter.setType(((SportActivity) getActivity()).getType());
        presenter.refresh(((SportActivity) getActivity()).getType());
        initAdapter();
    }


    private void initAdapter() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        rvContent.setLayoutManager(mLayoutManager);
        baseRecyclerAdapter = new VpBallAdapter(mContext, new ArrayList<MatchBean>(), R.layout.sport_match_item);
        MyPagerAdapter<MenuItemInfo> headerAdapter = headerAdapter();
        headerAdapter.setDatas(new ArrayList<>(Arrays.asList(new MenuItemInfo(0,"FULL   H/A","FULL    O/U"),new MenuItemInfo(0,"HALF   H/A","HALF    O/U"))));
        vpHeader.setAdapter(headerAdapter);
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

    @NonNull
    private MyPagerAdapter<MenuItemInfo> headerAdapter() {
        return new MyPagerAdapter<MenuItemInfo>(mContext) {
            @Override
            protected void convert(ViewHolder helper, MenuItemInfo o, int position) {
                TextView left = helper.getView(R.id.tv_head_left);
                TextView right = helper.getView(R.id.tv_head_right);
                left.setText(o.getText());
                right.setText(o.getType());
            }

            @Override
            protected int getPageLayoutRes() {
                return R.layout.sport_head_vp_item;
            }
        };
    }


    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData, String modelType) {
        baseRecyclerAdapter.addAllAndClear(pageData);
        String size = pageData.size() + "";
        tvTotalMatch.setText(size);
        ((BaseToolbarActivity) getActivity()).getTvToolbarTitle().setText(modelType);
        if (presenter.isMixParlay()) {
            llMixParlayOrder.setVisibility(View.VISIBLE);
        } else {
            llMixParlayOrder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean result, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {
        if (result != null)
            getApp().setBetParList(result);
        saveBetMap(keyMap, item);
        onCountBet();
    }

    @Override
    public void onAddMixFailed(String message) {
        onCountBet();
        baseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetBetSucceed(BettingPromptBean allData) {
        betPop.setBetData(allData, presenter);
        betPop.showPopupCenterWindow();
    }

    @Override
    public void onBetSucceed(String allData) {
        ToastUtils.showShort(allData);
        betPop.closePopupWindow();
    }

    @Override
    public void onRightMarkClick(Bundle b) {
        skipAct(VsActivity.class, b);
    }

    @Override
    public void onCountBet() {
        llMixParlayOrder.setVisibility(View.VISIBLE);
        Map<String, Map<String, Map<Integer, BettingInfoBean>>> result = getApp().getBetDetail();
        if (result != null) {
            if (result.size() == 0) {
                llMixParlayOrder.setVisibility(View.GONE);
            } else {
                tvMixParlayOrder.setText(result.size() + "");
                llMixParlayOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b=new Bundle();
                        b.putString(AppConstant.KEY_STRING,"Soccer");
                        skipAct(MixOrderListActivity.class,b);
                    }
                });
            }
        } else
            llMixParlayOrder.setVisibility(View.GONE);
    }

    @Override
    public void onCreatePopupWindow(BetBasePop betPop) {
        this.betPop = betPop;
        createPopupWindow(betPop);
    }


    private void saveBetMap(Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {
        if (item == null) {
            getApp().setBetDetail(null);
        } else {
            getApp().getBetDetail().put(item.getHome() + "+" + item.getAway(), keyMap);
            baseRecyclerAdapter.notifyDataSetChanged();
        }
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

    @Override
    public boolean collectionClick(TextView tvCollection) {
        presenter.collection();
        return presenter.isCollection();
    }

    @Override
    public void onGetData(List<MatchBean> data) {
        ToastUtils.showShort(data.toString());
    }

    @Override
    public String getTitle() {
        return getString(R.string.Football);
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }




    @OnClick(R.id.tv_odds_type)
    public void onClick(View v) {
        clickOddsType(v);
    }
}
