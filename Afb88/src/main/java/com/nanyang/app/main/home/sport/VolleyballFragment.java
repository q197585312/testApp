package com.nanyang.app.main.home.sport;


import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class VolleyballFragment extends BaseSportFragment<FootballPresenter> implements SportContract.View<String>  {
    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, String pageData,String type) {

    }

    @Override
    public void onAddMixSucceed(BettingParPromptBean allData, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {

    }

    @Override
    public void onAddMixFailed(String message) {

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
        return getString(R.string.Volleyball);
    }

    @Override
    public void toolbarRightClick(View v) {

    }
}
