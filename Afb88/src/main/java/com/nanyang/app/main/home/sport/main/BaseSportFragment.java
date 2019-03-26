package com.nanyang.app.main.home.sport.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.dialog.WebPop;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.IObtainDataState;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseSportFragment extends BaseFragment<SportPresenter> implements SportContract.View<SportInfo> {


    @Bind(R.id.swipe_target)
    protected RecyclerView rvContent;

    @Bind(R.id.tv_mix_parlay_order)
    protected TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    protected LinearLayout llMixParlayOrder;
    @Bind(R.id.sl_header)
    protected ScrollLayout slHeader;


    @Bind(R.id.tv_aos)
    protected TextView tvAos;
    @Bind(R.id.swipeToLoadLayout)
    protected SwipeToLoadLayout swipeToLoadLayout;
    private boolean isFirstIn;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private boolean isInit = false;
    private TextView ivAllAdd;


    @Override
    public void onWebShow(int nextNotRepeat, int position, IRTMatchInfo item, View view) {
        View v = rvContent.getChildAt(nextNotRepeat);
        if (v == null) {
            v = view;
        }

        showLoadingDialog();
        int heightPixels = DeviceUtils.getScreenPix(mContext).heightPixels;
        int[] location = new int[2];

        v.getLocationOnScreen(location);
        WebPop pop;
        if (location[1] < heightPixels / 2) {
            int popWidth = DeviceUtils.dip2px(mContext, 350);
            int popHeight = heightPixels - location[1] - v.getHeight();
            pop = new WebPop(mContext, v, popWidth, popHeight);
            pop.getWebView().setWebViewClient(new DigWebViewClient());
            pop.setTrans(1);
            String lag = AfbUtils.getLanguage(mContext);
            String l = "eng";
            if (lag.equals("zh")) {
                l = "eng";
            } else {
                l = "EN-US";
            }

            String gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + item.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(item.getHome()) + "&Away=" + StringUtils.URLEncode(item.getAway()) + "&L=" + l;
            pop.setUrl(gameUrl);
            int x = (location[0] + v.getWidth() / 2) - popWidth / 2;
            int y = location[1] + v.getHeight();
            pop.showPopupAtLocation(x, y);

        } else {
            v = rvContent.getChildAt(position);
            if (v == null) {
                v = view;
            }
            v.getLocationOnScreen(location);
            int popWidth = DeviceUtils.dip2px(mContext, 350);
            int popHeight = location[1];
            pop = new WebPop(mContext, v, popWidth, popHeight);
            pop.getWebView().setWebViewClient(new DigWebViewClient());
            pop.setTrans(1);
            String lag = AfbUtils.getLanguage(mContext);
            String l = "eng";
            if (lag.equals("zh")) {
                l = "eng";
            } else {
                l = "EN-US";
            }

            String gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + item.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(item.getHome()) + "&Away=" + StringUtils.URLEncode(item.getAway()) + "&L=" + l;
            pop.setUrl(gameUrl);
            int x = (location[0] + v.getWidth() / 2) - popWidth / 2;
            int y = 0;
            pop.showPopupAtLocation(x, y);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoadingDialog();
            }
        }, 5000);

    }

    private class DigWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            hideLoadingDialog();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            hideLoadingDialog();
        }
    }


    @Override
    public void initData() {
        super.initData();
        ivAllAdd = ((SportActivity) getActivity()).getIvAllAdd();
        createPresenter(new SportPresenter(this));
        isInit = true;
        isFirstIn = true;
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getStateHelper().onPrevious(swipeToLoadLayout);
            }
        });
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.getStateHelper().onNext(swipeToLoadLayout);
            }
        });
        rememberLastOdds();
        slHeader.setTouchAble(false);
        slHeader.setIndexChangeListener(new ScrollLayout.IndexChangeCallBack() {
            @Override
            public void changePosition(final int index) {
                View childAt = slHeader.getChildAt(index);
                ImageView nextView = (ImageView) childAt.findViewById(R.id.iv_next);
                ImageView previousView = (ImageView) childAt.findViewById(R.id.iv_previous);
                if (slHeader.canMovable(index + 1) && slHeader.canMovable(index - 1)) {
                    nextView.setVisibility(View.VISIBLE);
                    previousInit(index, previousView);
                } else if (slHeader.canMovable(index + 1)) {
                    nextView.setVisibility(View.GONE);
                    previousView.setVisibility(View.VISIBLE);
                    previousView.setImageResource(R.mipmap.arrow_right_white_double);
                    previousView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            moveToIndex(index + 1);
                        }
                    });
                } else if (slHeader.canMovable(index - 1)) {
                    nextView.setVisibility(View.GONE);
                    previousInit(index, previousView);
                } else {
                    nextView.setVisibility(View.GONE);
                    previousView.setVisibility(View.GONE);
                }
            }
        });
        for (int i = 0; i < slHeader.getChildCount(); i++) {
            View childAt = slHeader.getChildAt(i);
            View nextView = childAt.findViewById(R.id.iv_next);
            View previousView = childAt.findViewById(R.id.iv_previous);
            if (nextView != null && previousView != null) {

                nextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int targetIndex = slHeader.getTargetIndex() + 1;

                        moveToIndex(targetIndex);
                    }
                });
                previousView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int targetIndex = slHeader.getTargetIndex() - 1;
                        moveToIndex(targetIndex);
                    }
                });
            }

        }
/*
        slHeader.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (slFollowers != null)

                        break;
                }


                return false;
            }
        });*/
        setTitle(getTitle());
        if (AppConstant.getInstance().IS_AGENT) {
            initAgent();
        }
    }

    public void initAgent() {

    }

    private void previousInit(final int index, ImageView previousView) {
        previousView.setVisibility(View.VISIBLE);
        previousView.setImageResource(R.mipmap.arrow_left_white_double);
        previousView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToIndex(index - 1);
            }
        });
    }

    private void moveToIndex(int targetIndex) {
        if (((SportState) presenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
            Set slFollowers = ((BallAdapterHelper) ((SportState) presenter.getStateHelper()).getAdapterHelper()).getSlFollowers();
            if (slFollowers != null)
                slHeader.setFollowScrolls(slFollowers);
            slHeader.moveToIndex(targetIndex);
        }
        ((BallAdapterHelper) ((SportState) presenter.getStateHelper()).getAdapterHelper()).setSlIndex(targetIndex);
    }


    public void refresh() {
        presenter.getStateHelper().refresh();
    }

    public void collection(View tvCollection) {
        checkBg(tvCollection, presenter.getStateHelper().collection(), R.mipmap.sport_star_green, R.mipmap.sport_star_black);
    }

    public void menu(View tvMenu) {
        presenter.getStateHelper().menu(tvMenu);
    }

    public boolean mix(TextView tvMix) {
        boolean isMix = presenter.getStateHelper().mix();

        return isMix;
    }

    private void checkBg(View tvMix, boolean isCollection, int sport_oval_u_green, int sport_oval_u_black) {
        if (isCollection)
            tvMix.setBackgroundResource(sport_oval_u_green);
        else
            tvMix.setBackgroundResource(sport_oval_u_black);
    }

    @Override
    public void checkMix(boolean isMix) {
        checkBg(((SportActivity) getActivity()).tvMix, isMix, R.mipmap.sport_green_shopping_cart, R.mipmap.sport_black_shopping_cart);
    }

    @Override
    public void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter) {
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(baseRecyclerAdapter);
        this.baseRecyclerAdapter = baseRecyclerAdapter;
    }

    Handler handler = new Handler();

    @Override
    public void onFailed(final String message) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShort(message);
            }
        });

    }

    @Override
    public void onGetData(List<SportInfo> data) {

    }

    public AfbApplication getApp() {
        return (AfbApplication) getActivity().getApplication();
    }

    @Override
    public void onUpdateMixSucceed(AfbClickResponseBean bean) {
        getApp().setBetParList(bean);
        updateMixOrderCount();
        baseRecyclerAdapter.notifyDataSetChanged();
    }

    private void updateMixOrderCount() {
        if (getApp().getBetParList() != null && getApp().getBetParList().getList() != null && getApp().getBetParList().getList().size() > 0) {
            tvMixParlayOrder.setText("" + getApp().getBetParList().getList().size());
            llMixParlayOrder.setVisibility(View.VISIBLE);
            checkMix(true);
            ((SportActivity) getActivity()).tvMixCount.setVisibility(View.VISIBLE);
            ((SportActivity) getActivity()).tvMixCount.setText("" + getApp().getBetParList().getList().size());

        } else {
            tvMixParlayOrder.setText("0");
            llMixParlayOrder.setVisibility(View.GONE);
            checkMix(false);
            ((SportActivity) getActivity()).tvMixCount.setVisibility(View.GONE);
            ((SportActivity) getActivity()).tvMixCount.setText("0");
        }

    }

    public void toolbarRightClick(View v) {
        createPopupWindow(
                new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
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
        popWindow.setTrans(1f);
        popWindow.showPopupDownWindow();
    }

    private void setChooseTypeAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(presenter.getStateHelper().switchTypeAdapter());
    }


    @Override
    public void switchState(IObtainDataState state) {
        presenter.setStateHelper(state);
        presenter.getStateHelper().setScrollHeaderContent(slHeader, tvAos);
        View childAt = slHeader.getChildAt(0);
        ImageView nextView = (ImageView) childAt.findViewById(R.id.iv_next);
        ImageView previousView = (ImageView) childAt.findViewById(R.id.iv_previous);
        if (slHeader.getMovableChildCount() < 2) {

            nextView.setVisibility(View.GONE);
            previousView.setVisibility(View.GONE);
        } else {
            nextView.setVisibility(View.GONE);
            previousView.setVisibility(View.VISIBLE);
            previousView.setImageResource(R.mipmap.arrow_right_white_double);
            previousView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    moveToIndex(1);
                }
            });
        }

        ((BaseToolbarActivity) getIBaseContext().getBaseActivity()).getTvToolbarTitle().setText(state.getStateType().getText());

        ((SportState) presenter.getStateHelper()).initAllOdds(ivAllAdd);
        presenter.getStateHelper().refresh();
        if (popWindow != null)
            popWindow.closePopupWindow();

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_ball;
    }


    @Override
    public void onPause() {
        super.onPause();
        presenter.getStateHelper().stopUpdateData();
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示
            presenter.getStateHelper().stopUpdateData();
        } else {// 重新显示到最前端中
            rememberLastOdds();
        }
    }

    private void rememberLastOdds() {
        getBaseActivity().rememberLastOdds();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstIn) {

            presenter.getStateHelper().refresh();
            updateMixOrderCount();
        }
        isFirstIn = false;
    }

    public SportActivity getBaseActivity() {
        return (SportActivity) this.getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.getStateHelper().stopUpdateData();
        ButterKnife.unbind(this);
        isInit = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.ll_mix_parlay_order})
    public void onClick(View view) {
        switch (view.getId()) {
     
            case R.id.ll_mix_parlay_order:
                clickOrder();
                break;

        }
    }

    public void clickOrder() {
        if (getApp().getBetAfbList() == null || getApp().getBetAfbList().getList() == null || getApp().getBetAfbList().getList().size() < 1)
            return;
        if (getApp().getBetAfbList().getList().size() == 1) {
            String refreshOddsUrl = getApp().getRefreshOddsUrl();
            presenter.getStateHelper().getBetHelper().getRefreshOdds(refreshOddsUrl);
        } else if (getApp().getBetAfbList().getList().size() > 1) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstant.KEY_DATA, presenter.getStateHelper().getStateType());
            skipAct(MixOrderListActivity.class, bundle);
        }
    }

    public void clickOddsType(final TextView tvOddsType) {
        createPopupWindow(new BasePopupWindow(mContext, tvOddsType, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
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
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        ((SportActivity) getIBaseContext().getBaseActivity()).setOddsType(item);
                        presenter.getStateHelper().switchOddsType(item.getType());
                        closePopupWindow();
                        tvOddsType.setText(item.getText());
                    }
                });
                rv.setAdapter(baseRecyclerAdapter);
            }
        });
        popWindow.setTrans(1f);
        popWindow.showPopupDownWindow();
    }

    @Override
    public ScrollLayout onSetScrollHeader() {
        return slHeader;
    }

    @Override
    public void onBetSuccess(String betResult) {
        ((BaseToolbarActivity) getIBaseContext().getBaseActivity()).onBetSuccess(betResult);
        updateMixOrderCount();
        baseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        ((BaseToolbarActivity) getIBaseContext().getBaseActivity()).onPopupWindowCreated(pop, center);
    }

    public void switchParentType(MenuItemInfo stateType) {
        if (isInit)
            switchType(stateType.getType());


    }

    public abstract void switchType(String type);

    @Override
    public void reLoginPrompt(String str, SportContract.CallBack back) {
        if (mContext instanceof BaseToolbarActivity) {
            ((BaseToolbarActivity) mContext).reLoginPrompt(str, back);
        }
    }

    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
        Bundle b = new Bundle();
        b.putSerializable(AppConstant.KEY_DATA, item);
        b.putSerializable(AppConstant.KEY_DATA2, presenter.getStateHelper().getStateType());
        MenuItemInfo oddsType = ((SportActivity) getIBaseContext().getBaseActivity()).getOddsType();
        b.putSerializable(AppConstant.KEY_DATA3, oddsType);
        skipAct(VsActivity.class, b);
    }
}
