package com.nanyang.app.main.home.sport;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.utils.ToastUtils;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class FootballFragment extends BaseSportFragment<FootballPresenter> implements SportContract.View<Object>  {
    @Override
    public void initData() {
        super.initData();
        createPresenter(new FootballPresenter(this));
        presenter.refresh();
    }

    @Override
    public void onFailed(String error) {

    }


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }



    @Override
    public void onGetData(Object data) {
        ToastUtils.showShort(data.toString());

    }

    @Override
    public String getTitle() {
        return getString(R.string.Football);
    }
}
