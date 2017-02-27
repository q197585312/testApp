package com.nanyang.app.main.home.sport.thaiThousand;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/24.
 */

public class ThaiThousandFragment extends BaseSportFragment<ThaiThousandPresenter> implements SportContract.View<List<MatchBean>> {

    @Override
    public void initData() {
        super.initData();
        createPresenter(new ThaiThousandPresenter(this));
    }

    @Override
    public void toolbarRightClick(View v) {

    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData, String type) {

    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean allData, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {

    }

    @Override
    public void onAddMixFailed(String message) {

    }

    @Override
    public void onGetBetSucceed(BettingPromptBean allData) {

    }

    @Override
    public void onBetSucceed(String allData) {

    }

    @Override
    public void onRightMarkClick(Bundle b) {

    }

    @Override
    public void onCountBet() {

    }

    @Override
    public void onCreatePopupWindow(BetBasePop betPop) {

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_thai_thousand;
    }

    @Override
    public void onGetData(List<MatchBean> data) {

    }
}
