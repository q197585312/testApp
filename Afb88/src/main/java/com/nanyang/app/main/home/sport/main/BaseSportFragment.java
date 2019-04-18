package com.nanyang.app.main.home.sport.main;

import android.content.Context;
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
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.home.sport.additional.AdditionPresenter;
import com.nanyang.app.main.home.sport.dialog.WebPop;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorEarlyState;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorRunningState;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorTodayState;
import com.nanyang.app.main.home.sport.model.AdditionBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseSportFragment extends BaseSwitchFragment<SportPresenter> implements SportContract.View<SportInfo> {


    private static final String TAG = "updateAllDate";
    @Bind(R.id.swipe_target)
    protected RecyclerView rvContent;

//    @Bind(R.id.tv_mix_parlay_order)
//    protected TextView tvMixParlayOrder;
//    @Bind(R.id.ll_mix_parlay_order)
//    protected LinearLayout llMixParlayOrder;


    @Bind(R.id.swipeToLoadLayout)
    protected SwipeToLoadLayout swipeToLoadLayout;
    private boolean isFirstIn;
    private BaseRecyclerAdapter baseRecyclerAdapter;
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
            presenter.getStateHelper().stopUpdateData();
            presenter.getStateHelper().setIsHide(true);
        } else {// 重新显示到最前端中
            rememberLastOdds();
            presenter.getStateHelper().setIsHide(false);
        }
        Log.d(TAG, "onHiddenChanged: " + hidden + "," + getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstIn) {
            presenter.getStateHelper().refresh();
            updateMixOrderCount();
        }
        isFirstIn = false;
        getBaseActivity().sportHeaderLl.setVisibility(View.VISIBLE);
        getBaseActivity().toolbar.setVisibility(View.GONE);
        presenter.getStateHelper().setIsHide(false);
        Log.d(TAG, "onResume: " + getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.getStateHelper().stopUpdateData();
        presenter.getStateHelper().setIsHide(true);
        Log.d(TAG, "onPause: " + getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        additionPresenter.stopUpdate();
        Log.d(TAG, "onStop: " + getClass().getSimpleName());
    }

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
        ((BallAdapterHelper) ((SportState) presenter.getStateHelper()).getAdapterHelper()).setSlIndex(targetIndex);
    }


    public void refresh() {
        presenter.getStateHelper().refresh();
    }

    public void collection(View tvCollection) {
        checkBg(tvCollection, presenter.getStateHelper().collection(), R.mipmap.sport_game_star_yellow_open, R.mipmap.sport_game_star_yellow);
    }


    public void menu(View tvMenu) {
        presenter.getStateHelper().menu(tvMenu);
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
    public void checkMix(boolean isMix) {
        checkBgTop(((SportActivity) getActivity()).tvMix, isMix, R.mipmap.sport_bottom_teb_shopping, R.mipmap.sport_bottom_teb_shopping_black);
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
        getBaseActivity().updateMixOrderCount();
    }

    public void toolbarRightClick(View view) {

    }


    @Override
    public void switchState(SportState state) {
        SportState stateHelper = presenter.getStateHelper();
        if (stateHelper != null)
            stateHelper.setIsHide(true);
        state.setIsHide(false);
        presenter.setStateHelper(state);
        ((SportActivity) getIBaseContext().getBaseActivity()).tvMatchType.setText(state.getStateType().getText());
        ((SportState) presenter.getStateHelper()).initAllOdds(ivAllAdd);
        presenter.getStateHelper().refresh();
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
        presenter.getStateHelper().stopUpdateData();
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
        if (getApp().getBetAfbList() == null || getApp().getBetAfbList().getList() == null || getApp().getBetAfbList().getList().size() < 1)
            return;
//        if (getApp().getBetAfbList().getList().size() == 1) {
//            String refreshOddsUrl = getApp().getRefreshOddsUrl();
//            presenter.getStateHelper().getBetHelper().getRefreshOdds(refreshOddsUrl);
//        } else if (getApp().getBetAfbList().getList().size() > 1) {
//            Bundle bundle = new Bundle();
//            bundle.putSerializable(AppConstant.KEY_DATA, presenter.getStateHelper().getStateType());
//            skipAct(MixOrderListActivity.class, bundle);
//        }
        String refreshOddsUrl = getApp().getRefreshOddsUrl();
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
                List<MenuItemInfo> list = new ArrayList<>();
                list.add(new MenuItemInfo(0, getString(R.string.HK_ODDS), "HK"));//accType=
                list.add(new MenuItemInfo(0, getString(R.string.MY_ODDS), "MY"));
                list.add(new MenuItemInfo(0, getString(R.string.ID_ODDS), "ID"));
                list.add(new MenuItemInfo(0, getString(R.string.EU_ODDS), "EU"));
                BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, list, R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setText(item.getText());
                        if (tvOddsType.getText().toString().equals(item.getText())) {
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
    public void onBetSuccess(String betResult) {
        ((BaseToolbarActivity) getIBaseContext().getBaseActivity()).onBetSuccess(betResult);
        updateMixOrderCount();
        baseRecyclerAdapter.notifyDataSetChanged();
//        ToastUtils.showShort(betResult);
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
    public void clickItemAdd(View v, SportInfo item, int position) {
        additionPresenter.addition((BallInfo) item, position);
        if (((SportState) presenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
            BallAdapterHelper adapterHelper = (BallAdapterHelper) ((SportState) presenter.getStateHelper()).getAdapterHelper();
            adapterHelper.changeAddition(position);
        }
    }

    public void onAddition(AdditionBean data, int position) {
        if (((SportState) presenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
            BallAdapterHelper adapterHelper = (BallAdapterHelper) ((SportState) presenter.getStateHelper()).getAdapterHelper();
            adapterHelper.notifyPositionAddition(data, position);
        }
    }

    public void showContent() {
        getBaseActivity().setToolbarVisibility(View.GONE);
        getBaseActivity().sportHeaderLl.setVisibility(View.VISIBLE);
    }
}
