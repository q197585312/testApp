package com.nanyang.app.main.home.sport;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.adapter.VpBallAdapter;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.MenuListInfo;
import com.nanyang.app.main.home.sport.model.OutRightMatchBean;
import com.nanyang.app.myView.LinkedViewPager.MyPagerAdapter;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.utils.ViewHolder;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public abstract class BaseSportFragment<T extends SportPresenter> extends BaseFragment<T> {
    protected TextView tvMenu;
    private BetBasePop betPop;
    @Bind(R.id.swipe_target)
    protected RecyclerView rvContent;
    @Bind(R.id.vp_header)
    protected ViewPager vpHeader;

    protected BaseRecyclerAdapter<OutRightMatchBean> outRightAdapter;
    protected VpBallAdapter baseRecyclerAdapter;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @Override
    public void initData() {
        super.initData();
        outRightAdapter= new BaseRecyclerAdapter<OutRightMatchBean>(mContext, new ArrayList<OutRightMatchBean>(), R.layout.sport_out_right_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, OutRightMatchBean item) {
                TextView matchTitleTv = holder.getView(R.id.out_right_title_tv);
                View headV = holder.getView(R.id.v_out_right_header_space);
                TextView homeTv = holder.getView(R.id.out_right_home_tv);

                TextView markTv = holder.getView(R.id.out_right_mark_tv);
                homeTv.setText(item.getHome());
                markTv.setText(item.getX12_1Odds());

                if (item.getType() == MatchBean.Type.ITME) {
                    matchTitleTv.setVisibility(View.GONE);
                    headV.setVisibility(View.GONE);

                } else {
                    matchTitleTv.setVisibility(View.VISIBLE);
                    headV.setVisibility(View.VISIBLE);
                    matchTitleTv.setText(item.getModuleTitle());
                    if (position == 0) {
                        headV.setVisibility(View.GONE);
                    }

                }
            }
        };
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(mLayoutManager);
        baseRecyclerAdapter = new VpBallAdapter(mContext, new ArrayList<MatchBean>(), R.layout.sport_match_item);
        createPresenter(getPresenter());
        presenter.setType(((SportActivity) getActivity()).getType());
        presenter.refresh(((SportActivity) getActivity()).getType());
        baseRecyclerAdapter.setPresenter(presenter);
        baseRecyclerAdapter.setVpHeader(vpHeader);
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

    protected abstract T getPresenter();

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
        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base_item) {
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

    public AfbApplication getApp() {
        return (AfbApplication) getActivity().getApplication();
    }

    public boolean mixParlayCLick(TextView buttonView) {
        return false;
    }

    public boolean collectionClick(TextView tvCollection) {
        return false;
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {// 不在最前端界面显示
            presenter.stopUpdate();
        } else {// 重新显示到最前端中
            presenter.refresh("");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.refresh("");
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stopUpdate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.stopUpdate();
    }

    //盘口选择
    protected void clickOddsType(final View textView) {

        createPopupWindow(new BasePopupWindow(mContext, textView, LinearLayout.LayoutParams.MATCH_PARENT, 350) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                rv.setPadding(0, 0, 0, 0);
                rv.setLayoutManager(new LinearLayoutManager(mContext));
                List<MenuItemInfo> list = new ArrayList<>();
                list.add(new MenuItemInfo(0, getString(R.string.HK_ODDS), "HK"));//accType=
                list.add(new MenuItemInfo(0, getString(R.string.MY_ODDS), "MY"));
                list.add(new MenuItemInfo(0, getString(R.string.ID_ODDS), "ID"));
                list.add(new MenuItemInfo(0, getString(R.string.EU_ODDS), "EU"));
                BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, list, R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setPadding(0, 0, 0, 0);
                        tv.setText(item.getText());
                        tv.setBackgroundResource(R.color.black_grey);
                    }

                };
                rv.setAdapter(baseRecyclerAdapter);
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        switchedOddsType(item.getType());
                        closePopupWindow();
                        ((TextView) textView).setText(item.getText());
                    }
                });
            }
        });
        popWindow.showPopupDownWindow();

    }

    private void switchedOddsType(String type) {
        presenter.switchedOddsType(type);
    }

    public void menuClick(TextView tvMenu) {
        this.tvMenu = tvMenu;
        createPopupWindow(
                new BasePopupWindow(mContext, tvMenu, LinearLayout.LayoutParams.MATCH_PARENT, 150) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_bottom;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
                        setBottomMenuAdapter(rv_list);
                    }
                });
        popWindow.setTrans(1f);
        popWindow.showPopupDownWindow();
    }

    private void setBottomMenuAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new GridLayoutManager(mContext, 4));
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(R.mipmap.menu_group_oval_white, getString(R.string.Choose), "Choose"));
        types.add(new MenuItemInfo(R.mipmap.menu_error_white, getString(R.string.not_settled), "Not settled"));
        types.add(new MenuItemInfo(R.mipmap.menu_right_oval_white, getString(R.string.settled), "Settled"));

        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setCompoundDrawablesWithIntrinsicBounds(0, item.getRes(), 0, 0);
                tv.setTextSize(10);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                popWindow.closePopupWindow();
            }
        });
        rv_list.setAdapter(baseRecyclerAdapter);
    }

    @NonNull
    protected MyPagerAdapter<MenuListInfo> headerAdapter() {
        return new MyPagerAdapter<MenuListInfo>(mContext) {
            @Override
            protected void convert(ViewHolder helper, MenuListInfo list, int position) {
                LinearLayout ll_head_parent = helper.getView(R.id.ll_head_parent);
                for (int i = 0; i < list.getList().size(); i++) {
                    MenuItemInfo item = list.getList().get(i);
                    TextView tv = (TextView) ll_head_parent.getChildAt(i);
                    tv.setText(item.getText());
                    if (item.getRes() == 0)
                        tv.setVisibility(View.GONE);
                    else
                        tv.setVisibility(View.VISIBLE);
                }

            }

            @Override
            protected int getPageLayoutRes() {
                return R.layout.sport_head_vp_item;
            }
        };
    }


    public void onGetBetSucceed(BettingPromptBean allData) {
        betPop.setBetData(allData, presenter);
        betPop.showPopupCenterWindow();
    }



    public void onCreatePopupWindow(BetBasePop betPop) {
        this.betPop = betPop;
        createPopupWindow(betPop);
    }
    public void onBetSucceed(String allData) {
        ToastUtils.showShort(allData);
        betPop.closePopupWindow();
    }

    public void onOutRightData(int page, List<OutRightMatchBean> outRightMatchBeen, String type) {
            rvContent.setAdapter(outRightAdapter);
            outRightAdapter.addAllAndClear(outRightMatchBeen);

    }

}
