package com.nanyang.app.main.home.sport;

import com.unkonw.testapp.libs.base.BaseFragment;


/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class FootballFragment extends BaseFragment<FootballPresenter> implements SportContract.View<String>  {
    @Override
    public void onFailed(String error) {

    }


    @Override
    public int onSetLayoutId() {
        return 0;
    }



    @Override
    public void onGetData(String data) {

    }
}
