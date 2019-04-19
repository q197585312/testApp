package com.nanyang.app.main.home.sport.allRunning;

import android.view.LayoutInflater;

import com.nanyang.app.R;

import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;


public class AllFragment extends BaseSportFragment {

    private BaseSportFragment currentFragment;
/*SoccerFragment soccerFragment = new SoccerFragment();
    BasketballFragment basketballFragment = new BasketballFragment();
    TennisFragment tennisFragment = new TennisFragment();
    BaseballFragment baseballFragment = new BaseballFragment();
    IceHockeyFragment iceHockeyFragment = new IceHockeyFragment();
    EuropeFragment europeFragment = new EuropeFragment();*/

    @Override
    public void initData() {
        super.initData();
        String type =((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.all_running));
        SportAdapterHelper adapterHelper = presenter.getStateHelper().getAdapterHelper();
        BaseRecyclerAdapter baseRecyclerAdapter = adapterHelper.getBaseRecyclerAdapter();
        LayoutInflater.from(mContext).inflate(R.layout.sport_selected_head_foot_layout,null);

//        baseRecyclerAdapter.addHeader();
    }

    public void clickSelectedSport(BaseSportFragment currentFragment) {
        this.currentFragment = currentFragment;
        switchType(((SportActivity) getActivity()).getType());
    }

    @Override
    public void switchType(String type) {
        currentFragment.switchType(type);
    }


    @Override
    public String getTitle() {
        return getString(R.string.all_running);
    }


}
