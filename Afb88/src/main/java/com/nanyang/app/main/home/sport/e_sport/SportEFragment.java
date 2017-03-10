package com.nanyang.app.main.home.sport.e_sport;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;

import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/3.
 */

public class SportEFragment extends BaseSportFragment<SportEPresenter> implements SportContract.View<List<MatchBean>>{

    @Bind(R.id.tv_total_match)
    TextView tvTotalMatch;
    @Bind(R.id.tv_odds_type)
    TextView tvOddsType;

    @Bind(R.id.tv_mix_parlay_order)
    TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    LinearLayout llMixParlayOrder;

    @Override
    public void initData() {
        super.initData();

        initAdapter();
    }

    @Override
    protected SportEPresenter getPresenter() {
        return new SportEPresenter(this);
    }

    private void initAdapter() {

    }

    @Override
    public void onFailed(String error) {
        
    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData, String type) {
        baseRecyclerAdapter.addAllAndClear(pageData);
        String size = pageData.size() + "";
        tvTotalMatch.setText(size);
        ((BaseToolbarActivity) getActivity()).getTvToolbarTitle().setText(type);
    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean allData, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {

    }

    @Override
    public void onAddMixFailed(String message) {

    }




    @Override
    public void onRightMarkClick(Bundle b) {

    }

    @Override
    public void onCountBet() {

    }


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }

    @Override
    public String getTitle() {
        return getString(R.string.E_Sport);
    }

    @Override
    public void onGetData(List<MatchBean> data) {

    }
}
