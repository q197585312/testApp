package com.nanyang.app.main.home.sport;


import com.nanyang.app.R;

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
    public String getType() {
        return "";
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
}
