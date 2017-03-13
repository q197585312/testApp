package com.nanyang.app.main.home.sportInterface;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseSportFragment<P extends SportPresenter2> extends BaseFragment<P> implements SportContract2.View<SportInfo>{

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

    @Override
    public void initData() {
        super.initData();
        createPresenter(onCreatePresenter());
    }
    protected abstract P onCreatePresenter();

    public void refresh(){
        presenter.refresh();
    }
    public  void collection(TextView tvCollection){
        presenter.collection();
    }
    public void menu(TextView tvMenu){
        presenter.menu();
    }
    public boolean mix(TextView tvMix){
        return presenter.mixParlay();
    }


    @Override
    public void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter) {
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(baseRecyclerAdapter);
    }

    @Override
    public void onFailed(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void onGetData(List<SportInfo> data) {
        tvTotalMatch.setText(data.size()+"");
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
        rv_list.setAdapter(presenter.switchTypeAdapter());
    }
    @Override
    public void switchState(IObtainDataState state) {
        presenter.setStateHelper(state);
        presenter.refresh();
    }
    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }

    @Override
    public Activity getActivityContext() {
        return getActivity();
    }
}
