package com.nanyang.app.main.home.sport.football;

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
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportActivity;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.VpBallAdapter;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    public void toolbarRightClick(View v) {

        createPopupWindow(
                new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, 300) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_ball_type;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
                        setChooseTypeAdapter(rv_list);
                    }
                });
        popWindow.showPopupDownWindow();
    }

    private void setChooseTypeAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getString(R.string.Today), "Today"));
        if (!presenter.isMixParlay()) {
            types.add(new MenuItemInfo(0, getString(R.string.Running), "Running"));
        }
        types.add(new MenuItemInfo(0, getString(R.string.Early), "Early"));
        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                presenter.refresh(item.getType());
                popWindow.closePopupWindow();
            }
        });
        rv_list.setAdapter(baseRecyclerAdapter);
    }

    private void initAdapter() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

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
                        skipAct(MixOrderListActivity.class);
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
