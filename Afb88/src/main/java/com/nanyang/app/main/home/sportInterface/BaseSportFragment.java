package com.nanyang.app.main.home.sportInterface;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseSportFragment<P extends SportPresenter2> extends BaseFragment<P> implements SportContract2.View<SportInfo> {

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

    @Override
    public void initData() {
        super.initData();
        createPresenter(onCreatePresenter());
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

    protected abstract P onCreatePresenter();

    public void refresh() {
        presenter.getStateHelper().refresh();
    }

    public void collection(TextView tvCollection) {
        presenter.getStateHelper().collection();
    }

    public void menu(TextView tvMenu) {
        presenter.getStateHelper().menu();
    }

    public boolean mix(TextView tvMix) {
        return presenter.getStateHelper().mix();
    }


    @Override
    public void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter) {
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(baseRecyclerAdapter);
    }

    @Override
    public void onGetFollowers(List<ScrollLayout> followers) {
        presenter.getStateHelper().setHeaderContent(slHeader);
        if (followers != null)
            followers.add(slHeader);
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
        presenter.getStateHelper().notifyDataChanged();
    }
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
        rv_list.setAdapter(presenter.getStateHelper().switchTypeAdapter());
    }

    @Override
    public void switchState(IObtainDataState state) {
        presenter.setStateHelper(state);
        getContextActivity().getTvToolbarTitle().setText(state.getTypeNameRes());
        presenter.getStateHelper().refresh();
        if (popWindow != null)
            popWindow.closePopupWindow();
        onGetFollowers(presenter.getStateHelper().onSetAdapterHelper().getFollowers());


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
            presenter.getStateHelper().refresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstIn) {
            presenter.getStateHelper().refresh();
        }
        isFirstIn = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.getStateHelper().stopUpdateData();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
