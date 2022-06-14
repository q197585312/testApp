package com.nanyang.app.main.home.sport.main;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.additional.AddedParamsInfo;
import com.nanyang.app.main.home.sport.additional.AdditionPresenter;
import com.nanyang.app.main.home.sport.additional.IAdded;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorEarlyState;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorRunningState;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorTodayState;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.RunMatchInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.common.ActivityPageManager;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.libs.widget.PopOneBtn;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.DeviceUtils;

import static com.unkonw.testapp.libs.utils.LogUtil.getMethodName;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseSportFragment extends BaseSwitchFragment<SportPresenter> implements SportContract.View<SportInfo> {


    private static final String TAG = "updateTableDate";
    @BindView(R.id.swipe_target)
    protected RecyclerView rvContent;
    @BindView(R.id.tv_no_games)
    protected TextView tvNoGames;
    public String type = "";

    //    @BindView(R.id.tv_mix_parlay_order)
//    protected TextView tvMixParlayOrder;
//    @BindView(R.id.ll_mix_parlay_order)
//    protected LinearLayout llMixParlayOrder;
    protected abstract String getBallDbid();

    @BindView(R.id.swipeToLoadLayout)
    protected SwipeToLoadLayout swipeToLoadLayout;
    private boolean isFirstIn;
    public BaseRecyclerAdapter baseRecyclerAdapter;
    private boolean isInit = false;
    private TextView ivAllAdd;

    public boolean isTop() {
        return isTop;
    }

    private boolean isTop;
    private AdditionPresenter additionPresenter;

    public void refreshType() {
        switchType(((SportActivity) getActivity()).getType());
    }

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
            if (getBaseActivity().getBetContent().v.getVisibility() == View.VISIBLE)
                getBaseActivity().getBetContent().stopUpdateOdds();
            presenter.getStateHelper().setIsHide(true, additionPresenter);
        } else {// 重新显示到最前端中
            showContent();
            rememberLastOdds();
            presenter.getStateHelper().setIsHide(false, additionPresenter);
            String type = ((SportActivity) getActivity()).getType();
            switchType(type);
            if (getBaseActivity().getBetContent().v.getVisibility() == View.VISIBLE)
                getBaseActivity().getBetContent().updateOdds(0);
//            presenter.getStateHelper().refresh();
        }
        Log.d(TAG, "onHiddenChanged: " + hidden + "," + getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isHidden()) {
            showContent();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.getStateHelper().setIsHide(false, additionPresenter);
            if (!isFirstIn) {
                presenter.getStateHelper().refresh();
                updateMixOrderCount();
            }
        }
        isFirstIn = false;


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
    public void onWebShow(int nextNotRepeat, int position, BallInfo item, View view) {
        if (presenter.getStateHelper().getStateType().getType().toLowerCase().startsWith("r")) {
            if (!getBaseActivity().hasBet) {
                PopOneBtn popOneBtn = new PopOneBtn(getBaseActivity(), view) {
                    @Override
                    protected void initView(@NotNull View view) {
                        super.initView(view);
                        chooseMessage.setText(R.string.placing_a_bet);
                    }

                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_base_one_btn;

                    }
                };
                popOneBtn.showPopupCenterWindow();
            } else {
                getBaseActivity().clickRunMatchPlay(item, position, true);
            }
//            getBaseActivity().clickRunMatchPlay(item, position, true);
        }
    }


    public void checkTop(TextView tx, TextView tvOval) {

        setTopLog(tx, tvOval, !isTop);
        MenuItemInfo stateType = presenter.getStateHelper().getStateType();
        if (isTop) {
            switchMajorType(stateType.getType());
        } else {
            switchType(stateType.getType());
        }
    }


    public void setTopLog(TextView tx, TextView tvOval, boolean isTop) {
        this.isTop = isTop;
        if (this.isTop) {
            tx.setTextColor(ContextCompat.getColor(mContext, R.color.yellow_button));
            tvOval.setVisibility(View.VISIBLE);
        } else {
            tx.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            tvOval.setVisibility(View.GONE);
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

    public void collection(ImageView tvCollection) {
        checkBg(tvCollection, presenter.getStateHelper().collection(), R.mipmap.sport_game_star_yellow_open, R.mipmap.sport_game_star_white);
    }


    public void checkBg(ImageView tvMix, boolean isCollection, int sport_oval_u_green, int sport_oval_u_black) {
        if (isCollection)
            tvMix.setImageResource(sport_oval_u_green);
        else
            tvMix.setImageResource(sport_oval_u_black);
    }

    private void checkBgTop(TextView tvMix, boolean isMix, int mixRes, int noMixRes) {

        if (isMix)
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, mixRes, 0, 0);
        else
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, noMixRes, 0, 0);
    }


    @Override
    public void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter) {
        if (rvContent != null) {
            rvContent.setLayoutManager(new LinearLayoutManager(mContext));
            rvContent.setAdapter(baseRecyclerAdapter);
        }
        this.baseRecyclerAdapter = baseRecyclerAdapter;
    }

    @Override
    public void onFailed(final String message) {
        if (message != null && message.contains("Guest")) {
            reLoginPrompt(getBaseActivity().getString(R.string.failed_to_connect), new SportContract.CallBack() {
                @Override
                public void clickCancel(View v) {
                    refresh();
                }
            });
        }

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
        if (getBaseActivity().liveMatchHelper != null && getBaseActivity().itemBall != null && getBaseActivity().itemBall instanceof RunMatchInfo && ((RunMatchInfo) getBaseActivity().itemBall).getDbid().equals(getBallDbid())) {
            LogUtil.d("liveMatchHelper", "000000000");
            getBaseActivity().liveMatchHelper.openRunMatch(getBaseActivity().itemBall, getBaseActivity().presenter);
            BallAdapterHelper adapterHelper = (BallAdapterHelper) (presenter.getStateHelper()).getAdapterHelper();
            adapterHelper.additionMap.put(true, "");
            clickItemAdd(getBaseActivity().fl_top_video, getBaseActivity().itemBall, getBaseActivity().dateClickPosition);

            getBaseActivity().itemBall = null;
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
        getBaseActivity().initMatchTypePop();
        getBaseActivity().loadTypeData();
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
        Activity act = this.getActivity();
        if (act == null) {
            act = ActivityPageManager.getInstance().currentActivity();
        }
        return (SportActivity) act;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        presenter.getStateHelper().stopUpdateData();
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
        createPopupWindow(new BasePopupWindow(mContext, tvOddsType, tvOddsType.getWidth() + DeviceUtils.dip2px(getActivity(), 20), LinearLayout.LayoutParams.WRAP_CONTENT) {
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
//        ToastUtils.showShort(betResult);
    }

    @Override
    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        getBaseActivity().onPopupWindowCreatedAndShow(pop, center);
    }


    public void switchType(String type) {
        if (this.type == null || !this.type.equals(type)) {
            this.type = type;
        }
    }

    @Override
    public void reLoginPrompt(String str, SportContract.CallBack back) {
        if (mContext instanceof BaseToolbarActivity) {
            ((BaseToolbarActivity) mContext).reLoginPrompt(str, back);
        }
    }

    @Override
    public void clickItemAdd(View v, IRTMatchInfo item, int position) {
        String dbid = getSportDbid();

/*        if (presenter.getStateHelper().getStateType().getType().toLowerCase().startsWith("r")) {
            ((SportActivity) getBaseActivity()).clickRunMatchPlay(item, position, true, false);
        }*/
        if ((presenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
            String type = getBaseActivity().getOtType();
            AddedParamsInfo info = new AddedParamsInfo(item, dbid, type);
            additionPresenter.addition(info, new IAdded() {
                @Override
                public void onAdded(AddMBean addMBean, IRTMatchInfo ballInfo) {
                    onAddition(addMBean, ballInfo);
                }
            });
            if ((presenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
                boolean isRun = presenter.getStateHelper().getStateType().getType().toLowerCase().startsWith("r");
                boolean visible = (presenter.getStateHelper().checkLivePlayVisible(item) || presenter.getStateHelper().checkWebRtsVisible(item));
                BallAdapterHelper adapterHelper = (BallAdapterHelper) (presenter.getStateHelper()).getAdapterHelper();
                adapterHelper.changeAdded(item, isRun && visible);
            }

        }
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
            SportIdBean sportIdBean = getApp().getSportByG(ballG);
            if (sportIdBean != null)
                dbid = sportIdBean.getDbid();
        }
        return dbid;
    }

    public void onAddition(AddMBean data, IRTMatchInfo item) {
        if ((presenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
            BallAdapterHelper adapterHelper = (BallAdapterHelper) (presenter.getStateHelper()).getAdapterHelper();
            adapterHelper.notifyPositionAdded(data, item);
        }
    }

    public void showContent() {
        super.showContent();
        getMethodName();
        LogUtil.d("getMethodName", getClass().getSimpleName() + ",onlyShowAdded:" + getBaseActivity().onlyShowOne);
        getBaseActivity().setToolbarVisibility(View.GONE);
        getBaseActivity().cl_sport_head.setVisibility(View.VISIBLE);
        if (!BuildConfig.FLAVOR.equals("ez2888")) {
            getBaseActivity().list_top.setVisibility(View.VISIBLE);
        }
        getBaseActivity().ll_footer_sport.setVisibility(View.VISIBLE);
        getBaseActivity().llSportMenuBottom.setVisibility(View.VISIBLE);
        if (getBaseActivity().fl_top_video.getVisibility() == View.GONE) {
            getBaseActivity().ll_line1.setVisibility(View.VISIBLE);
            if (!BuildConfig.FLAVOR.equals("ez2888")) {
                getBaseActivity().list_top.setVisibility(View.VISIBLE);
            }
            getBaseActivity().ll_line2.setVisibility(View.VISIBLE);
        } else {
            getBaseActivity().liveMatchHelper.onResumePlay();
            if (getBaseActivity().onlyShowOne) {

                presenter.getStateHelper().getAdapterHelper().getBaseRecyclerAdapter().notifyDataSetChanged();
                getBaseActivity().llSportMenuBottom.setVisibility(View.GONE);
                getBaseActivity().ll_footer_sport.setVisibility(View.GONE);
            }
        }

    }


}
