package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.nanyang.app.SportIdBean;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.additional.AddedParamsInfo;
import com.nanyang.app.main.home.sport.additional.AdditionPresenter;
import com.nanyang.app.main.home.sport.additional.IAdded;
import com.nanyang.app.main.home.sport.live.LiveParamsInfo;
import com.nanyang.app.main.home.sport.live.LiveWebActivity;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorEarlyState;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorRunningState;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorTodayState;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseSportFragment extends BaseSwitchFragment<SportPresenter> implements SportContract.View<SportInfo> {


    private static final String TAG = "updateTableDate";
    @Bind(R.id.swipe_target)
    protected RecyclerView rvContent;
    @Bind(R.id.tv_no_games)
    protected TextView tvNoGames;

    //    @Bind(R.id.tv_mix_parlay_order)
//    protected TextView tvMixParlayOrder;
//    @Bind(R.id.ll_mix_parlay_order)
//    protected LinearLayout llMixParlayOrder;
    protected abstract String getBallDbid();

    @Bind(R.id.swipeToLoadLayout)
    protected SwipeToLoadLayout swipeToLoadLayout;
    private boolean isFirstIn;
    public BaseRecyclerAdapter baseRecyclerAdapter;
    private boolean isInit = false;
    private TextView ivAllAdd;
    private boolean isMajor;
    private AdditionPresenter additionPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: " + getClass().getSimpleName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: " + getClass().getSimpleName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: " + getClass().getSimpleName());
    }


    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示
//            presenter.getStateHelper().stopUpdateData();
            presenter.getStateHelper().setIsHide(true, additionPresenter);
            getBaseActivity().closeTv(tvNoGames);
        } else {// 重新显示到最前端中
            showContent();
            rememberLastOdds();
            presenter.getStateHelper().setIsHide(false, additionPresenter);
            String type = ((SportActivity) getActivity()).getType();
            switchType(type);
//            presenter.getStateHelper().refresh();
        }
        Log.d(TAG, "onHiddenChanged: " + hidden + "," + getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getStateHelper().setIsHide(false, additionPresenter);
        if (!isFirstIn) {
            presenter.getStateHelper().refresh();
            updateMixOrderCount();
        }
        isFirstIn = false;

        showContent();

        Log.d(TAG, "onResumePlay: " + getClass().getSimpleName());

    }


    @Override
    public void onPause() {
        super.onPause();
//        presenter.getStateHelper().stopUpdateData();
        presenter.getStateHelper().setIsHide(true, additionPresenter);
        Log.d(TAG, "onPause: " + getClass().getSimpleName());
        tvNoGames.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + getClass().getSimpleName());
    }


    @Override
    public void onWebShow(int nextNotRepeat, int position, IRTMatchInfo item, View view) {
        if (presenter.getStateHelper().getStateType().getType().toLowerCase().startsWith("r")) {
            View v = rvContent.getChildAt(nextNotRepeat);
            if (v == null) {
                v = view;
            }
            showLiveSelect(v, (BallInfo) item, position);
        }


    }

    private void showLiveSelect(View v, final BallInfo itemBall, final int positionBall) {
        if (!StringUtils.isNull(itemBall.getTvPathIBC()) && !itemBall.getTvPathIBC().equals("0")) {
            BaseListPopupWindow<MenuItemInfo> popu = new BaseListPopupWindow<MenuItemInfo>(mContext, v, AfbUtils.dp2px(mContext, 100), AfbUtils.dp2px(mContext, 60)) {
                @Override
                protected void convertTv(TextView tv, MenuItemInfo item) {
                    tv.setText(item.getText());
                }

                @Override
                public int getItemLayoutRes() {
                    return R.layout.item_base_live;
                }

                @Override
                public int getRecyclerViewId() {
                    return super.getRecyclerViewId();
                }

                @Override
                protected void clickItem(TextView tv, MenuItemInfo item) {
                    if (item.getType().equals("Single")) {
                        getBaseActivity().chooseSingle(itemBall);
                    } else {
                        clickLivePop(v, itemBall, positionBall);
                    }
                }

                @Override
                protected int onSetLayoutRes() {
                    return R.layout.layout_base_recycler_live;
                }

                @Override
                protected void initView(View view) {
                    super.initView(view);
                    view.setBackgroundResource(R.color.white);
                }
            };
            ArrayList<MenuItemInfo> menuItemInfos = new ArrayList<>();
            menuItemInfos.add(new MenuItemInfo(R.mipmap.tv_play, (R.string.Single_TV), "Single"));
            menuItemInfos.add(new MenuItemInfo(R.mipmap.tv_play, (R.string.MATCH_TV), "Match"));
            popu.setData(menuItemInfos);
            popu.showPopupCenterWindow();
        } else {
            clickLivePop(v, itemBall, positionBall);
        }

    }


    private void showWebLivePop(int position, IRTMatchInfo item, View view, View v) {
        showLoadingDialog();
     /*   int heightPixels = DeviceUtils.getScreenPix(mContext).heightPixels;
        int[] location = new int[2];

        v.getLocationOnScreen(location);
        WebPop betPop;
        if (location[1] < heightPixels / 2) {
            int popWidth = DeviceUtils.dip2px(mContext, 350);
            int popHeight = heightPixels - location[1] - v.getHeight();
            betPop = new WebPop(mContext, v, popWidth, popHeight);
            betPop.getWebView().setWebViewClient(new DigWebViewClient());
            betPop.setTrans(1);
            String lag = AfbUtils.getLanguage(mContext);
            String l = "eng";
            if (lag.equals("zh")) {
                l = "en";
            } else {
                l = "en";
            }

            String gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + item.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(item.getHome()) + "&Away=" + StringUtils.URLEncode(item.getAway()) + "&L=" + l;
            Log.d(TAG, "onWebShow: " + gameUrl);
            betPop.setUrl(gameUrl);
            int x = (location[0] + v.getWidth() / 2) - popWidth / 2;
            int y = location[1] + v.getHeight();
            betPop.showPopupAtLocation(x, y);

        } else {
            v = rvContent.getChildAt(position);
            if (v == null) {
                v = view;
            }
            v.getLocationOnScreen(location);
            int popWidth = DeviceUtils.dip2px(mContext, 350);
            int popHeight = location[1];
            betPop = new WebPop(mContext, v, popWidth, popHeight);
            betPop.getWebView().setWebViewClient(new DigWebViewClient());
            betPop.setTrans(1);
            String lag = AfbUtils.getLanguage(mContext);
            String l = "eng";
            if (lag.equals("zh")) {
                l = "eng";
            } else {
                l = "EN-US";
            }

            String gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + item.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(item.getHome()) + "&Away=" + StringUtils.URLEncode(item.getAway()) + "&L=" + l;
            betPop.setUrl(gameUrl);
            int x = (location[0] + v.getWidth() / 2) - popWidth / 2;
            int y = 0;
            betPop.showPopupAtLocation(x, y);
        }*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoadingDialog();
            }
        }, 5000);
    }

    public void checkMajor(TextView tx) {
        isMajor = !isMajor;
        if (isMajor)
            tx.setTextColor(ContextCompat.getColor(mContext, R.color.yellow_button));
        else
            tx.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        MenuItemInfo stateType = presenter.getStateHelper().getStateType();
        if (isMajor) {
            switchMajorType(stateType.getType());
        } else {
            switchType(stateType.getType());
        }
    }


    private void switchMajorType(String type) {
        switch (type) {
            case "Running":
                switchState(new FiveMajorRunningState(this));
                break;
            case "Today":
                switchState(new FiveMajorTodayState(this));
                break;
            case "Early":
                switchState(new FiveMajorEarlyState(this));
                break;
            default:
                switchState(new FiveMajorTodayState(this));
                break;
        }
    }

    public void searchMatch(boolean isSearch, String s) {
        presenter.getStateHelper().setSearch(isSearch, s);
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
        if (swipeToLoadLayout != null) {
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
        }
        rememberLastOdds();
        setTitle(getTitle());
        if (AppConstant.getInstance().IS_AGENT) {
            initAgent();
        }
        additionPresenter = new AdditionPresenter(this);
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
        ((BallAdapterHelper) (presenter.getStateHelper()).getAdapterHelper()).setSlIndex(targetIndex);
    }


    public void refresh() {

    }

    public void collection(View tvCollection) {
        checkBg(tvCollection, presenter.getStateHelper().collection(), R.mipmap.sport_game_star_yellow_open, R.mipmap.sport_game_star_yellow);
    }

    public boolean mix(TextView tvMix) {
        boolean isMix = presenter.getStateHelper().mix();
        return isMix;
    }

    public void checkBg(View tvMix, boolean isCollection, int sport_oval_u_green, int sport_oval_u_black) {
        if (isCollection)
            tvMix.setBackgroundResource(sport_oval_u_green);
        else
            tvMix.setBackgroundResource(sport_oval_u_black);
    }

    private void checkBgTop(TextView tvMix, boolean isMix, int mixRes, int noMixRes) {

        if (isMix)
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, mixRes, 0, 0);
        else
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, noMixRes, 0, 0);
    }


    @Override
    public void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter) {
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(baseRecyclerAdapter);
        this.baseRecyclerAdapter = baseRecyclerAdapter;
    }

    @Override
    public void onFailed(final String message) {

//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                ToastUtils.showShort(message);
//            }
//        });

    }

    @Override
    public void onGetData(List<SportInfo> data) {
        if (data.size() == 0) {
            tvNoGames.setVisibility(View.VISIBLE);
        } else {
            tvNoGames.setVisibility(View.GONE);
        }
    }

    public AfbApplication getApp() {
        return getBaseActivity().getApp();
    }

    @Override
    public void onUpdateMixSucceed(AfbClickResponseBean bean) {
        if (getBaseActivity() == null)
            return;
        LogUtil.d("BetPop", "setBetAfbList:onUpdateMixSucceed:" + bean);
        getApp().setBetAfbList(bean);
        if (getBaseActivity().isHasAttached()) {
            updateMixOrderCount();
            baseRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private void updateMixOrderCount() {
        getBaseActivity().updateMixOrder();
    }

    public void toolbarRightClick(View view) {

    }


    @Override
    public void switchState(SportState state) {
        SportState stateHelper = presenter.getStateHelper();
        if (stateHelper != null)
            stateHelper.setIsHide(true, additionPresenter);
        state.setIsHide(false, additionPresenter);
        presenter.setStateHelper(state);
        (presenter.getStateHelper()).initAllOdds(ivAllAdd);
        presenter.getStateHelper().refresh();
//        presenter.getStateHelper().startUpdateData();
        if (popWindow != null)
            popWindow.closePopupWindow();

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_ball;
    }


    private void rememberLastOdds() {
        getBaseActivity().rememberLastOdds();
    }


    public SportActivity getBaseActivity() {
        return (SportActivity) this.getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        presenter.getStateHelper().stopUpdateData();
        ButterKnife.unbind(this);
        isInit = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment contentView
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


//    @OnClick({R.id.ll_mix_parlay_order})
//    public void onClick(View view) {
//        switch (view.getId()) {
//
//            case R.id.ll_mix_parlay_order:
//                clickOrder();
//                break;
//
//        }
//    }

    public void clickOrder() {
        if (getApp().getMixBetList() == null || getApp().getMixBetList().size() < 1)
            return;
        String refreshOddsUrl = getApp().getRefreshMixOddsUrl();
        presenter.getStateHelper().getBetHelper().getRefreshOdds(refreshOddsUrl);
    }

    public void clickOddsType(final TextView tvOddsType) {
        createPopupWindow(new BasePopupWindow(mContext, tvOddsType, tvOddsType.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = view.findViewById(R.id.rv_list);
                rv.setPadding(0, 0, 0, 0);
                rv.setLayoutManager(new LinearLayoutManager(mContext));
                String curCode = "";
                if (getApp().getSettingAllDataBean() != null)
                    curCode = getApp().getSettingAllDataBean().getCurCode();
                List<MenuItemInfo> list = AfbUtils.getOddsTypeList(mContext, curCode);
                BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, list, R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setText(item.getText());
                        if (getBaseActivity().getOddsType().getType().equals(item.getType())) {
                            tv.setBackgroundColor(ContextCompat.getColor(context, R.color.gary1));
                            tv.setTextColor(ContextCompat.getColor(context, R.color.blue));
                        } else {
                            tv.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                            tv.setTextColor(ContextCompat.getColor(context, R.color.black));
                        }
                    }

                };
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {

                        getBaseActivity().setOddsType(item);
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
    public void onBetSuccess(String betResult) {
        getBaseActivity().onBetSuccess(betResult);
        updateMixOrderCount();
        baseRecyclerAdapter.notifyDataSetChanged();
//        ToastUtils.showShort(betResult);
    }

    @Override
    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        getBaseActivity().onPopupWindowCreatedAndShow(pop, center);
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
    public void clickItemAdd(View v, SportInfo item, int position) {
        String dbid = getSportDbid();

        if ((presenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
            Log.e(TAG, "clickItemAdd: 点击的位置-------" + position);
            String type = getBaseActivity().getOtType();
            AddedParamsInfo info = new AddedParamsInfo((BallInfo) item, dbid, type);
            additionPresenter.addition(info, new IAdded() {
                @Override
                public void onAdded(AddMBean addMBean, BallInfo ballInfo) {
                    onAddition(addMBean, ballInfo);
                }
            });
            BallAdapterHelper adapterHelper = (BallAdapterHelper) (presenter.getStateHelper()).getAdapterHelper();
            adapterHelper.changeAdded((BallInfo) item);

        }
    }


    public void clickLivePop(View v, BallInfo item, int position) {
        MenuItemInfo stateType = getPresenter().getStateHelper().getStateType();
        LogUtil.d("clickLivePop", "stateType:" + stateType.getType() + "item.getRTSMatchId():" + item.getRTSMatchId() + ",item.getTvPathIBC():" + item.getTvPathIBC());
        if (!stateType.getType().equals("Running") || (item.getRTSMatchId().equals("0") && StringUtils.isNull(item.getTvPathIBC())))
            return;
        String lag = AfbUtils.getLanguage(mContext);
        String l = "eng";
        if (lag.equals("zh")) {
            l = "en";
        } else {
            l = "en";
        }
        String gameUrl = "";
        if (!StringUtils.isNull(item.getRTSMatchId()) && !item.getRTSMatchId().equals("0"))
            gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + item.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(item.getHome()) + "&Away=" + StringUtils.URLEncode(item.getAway()) + "&L=" + l;
        Log.d(TAG, "onWebShow: " + gameUrl);
        String dbid = getSportDbid();
        String type = getBaseActivity().getOddsType().getType();
        LiveParamsInfo info = new LiveParamsInfo(item, dbid, type);
        info.setGameUrl(gameUrl);
        info.setLivePlayUrlId(item.getTvPathIBC());
        info.setBallG(presenter.getStateHelper().getBetHelper().getBallG());
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.KEY_DATA, info);
        getBaseActivity().skipAct(LiveWebActivity.class, bundle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        AfbUtils.switchLanguage(AfbUtils.getLanguage(mContext), mContext);
        super.onConfigurationChanged(newConfig);

    }

    private String getSportDbid() {
        IBetHelper betHelper = presenter.getStateHelper().getBetHelper();
        String dbid = "1";
        if (betHelper instanceof BallBetHelper) {
            String ballG = (betHelper).getBallG();
            SportIdBean sportIdBean = AfbUtils.getSportByG(ballG);
            if (sportIdBean != null)
                dbid = sportIdBean.getDbid();
        }
        return dbid;
    }

    public void onAddition(AddMBean data, BallInfo item) {
        if ((presenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
            BallAdapterHelper adapterHelper = (BallAdapterHelper) (presenter.getStateHelper()).getAdapterHelper();
            adapterHelper.notifyPositionAdded(data, item);
        }
    }

    public void showContent() {
        super.showContent();
        getBaseActivity().setToolbarVisibility(View.GONE);
        getBaseActivity().sportHeaderLl.setVisibility(View.VISIBLE);
        getBaseActivity().ll_footer_sport.setVisibility(View.VISIBLE);
        getBaseActivity().llSportMenuBottom.setVisibility(View.VISIBLE);
    }

}
