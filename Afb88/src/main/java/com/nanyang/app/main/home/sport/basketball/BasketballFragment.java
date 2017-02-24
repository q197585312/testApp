package com.nanyang.app.main.home.sport.basketball;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.SportPresenter;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class BasketballFragment extends BaseSportFragment<SportPresenter> implements SportContract.View<String> {
    //http://a0096f.panda88.org/_view/All_WFHGen.ashx?g=9&ot=t&update=true&r=127579615&LID=&_=1487924667399
    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, String pageData,String type) {

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
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }



    @Override
    public void onGetData(String data) {

    }

    @Override
    public String getTitle() {
        return getString(R.string.Basketball);
    }


    @Override
    public void toolbarRightClick(View v) {

    }
}
