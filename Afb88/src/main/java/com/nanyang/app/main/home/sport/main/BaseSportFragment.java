package com.nanyang.app.main.home.sport.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.IObtainDataState;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseSportFragment extends BaseFragment<SportPresenter> implements SportContract.View<SportInfo> {

    @Bind(R.id.tv_total_match)
    TextView tvTotalMatch;
    @Bind(R.id.tv_odds_type)
    TextView tvOddsType;
    @Bind(R.id.swipe_target)
    protected RecyclerView rvContent;

    @Bind(R.id.tv_mix_parlay_order)
    TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    LinearLayout llMixParlayOrder;
    @Bind(R.id.sl_header)
    ScrollLayout slHeader;
    @Bind(R.id.tv_aos)
    TextView tvAos;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private boolean isFirstIn;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private boolean isInit = false;

    @Override
    public void initData() {
        super.initData();
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
    }


    public void refresh() {
        presenter.getStateHelper().refresh();
    }

    public void collection(TextView tvCollection) {
        checkBg(tvCollection, presenter.getStateHelper().collection(), R.mipmap.sport_star_green, R.mipmap.sport_star_black);
    }

    public void menu(TextView tvMenu) {
        presenter.getStateHelper().menu(tvMenu);
    }

    public boolean mix(TextView tvMix) {
        boolean isMix = presenter.getStateHelper().mix();
        checkBg(tvMix, isMix, R.mipmap.sport_oval_u_green, R.mipmap.sport_oval_u_black);
        return isMix;
    }

    private void checkBg(TextView tvMix, boolean isMix, int sport_oval_u_green, int sport_oval_u_black) {
        if (isMix) {
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, sport_oval_u_green, 0, 0);
        } else {
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, sport_oval_u_black, 0, 0);
        }
    }

    @Override
    public void checkMix(boolean isMix) {
        checkBg(((SportActivity) getActivity()).tvMix, isMix, R.mipmap.sport_oval_u_green, R.mipmap.sport_oval_u_black);
    }

    @Override
    public void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter) {
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(baseRecyclerAdapter);
        this.baseRecyclerAdapter = baseRecyclerAdapter;
    }


    @Override
    public void onFailed(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void onGetData(List<SportInfo> data) {
        if (tvTotalMatch != null)
            tvTotalMatch.setText(data.size() + "");
    }

    public AfbApplication getApp() {
        return (AfbApplication) getActivity().getApplication();
    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean bean) {
        getApp().setBetParList(bean);
        updateMixOrderCount();
        if (presenter.getStateHelper().isMix())
            baseRecyclerAdapter.notifyDataSetChanged();

    }

    private void updateMixOrderCount() {
        if (getApp().getBetParList() != null && getApp().getBetParList().getBetPar() != null && getApp().getBetParList().getBetPar().size() > 0) {
            tvMixParlayOrder.setText("" + getApp().getBetParList().getBetPar().size());
            llMixParlayOrder.setVisibility(View.VISIBLE);

        } else {
            tvMixParlayOrder.setText("0");
            llMixParlayOrder.setVisibility(View.GONE);
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
        getContextActivity().getTvToolbarTitle().setText(state.getStateType().getText());
        presenter.getStateHelper().refresh();
        if (popWindow != null)
            popWindow.closePopupWindow();

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_ball;
    }


    @Override
    public BaseToolbarActivity getContextActivity() {
        return (BaseToolbarActivity) getBaseActivity();
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

        }
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


    @OnClick({R.id.ll_odds_type, R.id.ll_mix_parlay_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_odds_type:
                clickOddsType(view);
                break;
            case R.id.ll_mix_parlay_order:
                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConstant.KEY_DATA, presenter.getStateHelper().getStateType());
                skipAct(MixOrderListActivity.class, bundle);
                break;
        }
    }

    private void clickOddsType(View view) {
        createPopupWindow(new BasePopupWindow(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT) {
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
        getContextActivity().onBetSuccess(betResult);
    }

    @Override
    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        getContextActivity().onPopupWindowCreated(pop, center);
    }

    public void switchParentType(MenuItemInfo stateType) {
        if (isInit)
            switchType(stateType.getType());


    }

    public abstract void switchType(String type);

    @Override
    public void reLoginPrompt(String str, SportContract.CallBack back) {
        if(mContext instanceof  BaseToolbarActivity){
            ((BaseToolbarActivity)mContext).reLoginPrompt(str,back);
        }
    }
}
