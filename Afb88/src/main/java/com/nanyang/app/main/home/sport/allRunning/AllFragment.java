package com.nanyang.app.main.home.sport.allRunning;

import com.nanyang.app.R;

import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class AllFragment extends BaseSportFragment {

    private BaseSportFragment currentFragment;


    @Override
    public void initData() {
        super.initData();
        String type =((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.all_running));
        presenter.getStateHelper().getAdapterHelper();
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
