package com.nanyang.app.main;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

/**
 * Created by Administrator on 2019/4/3.
 */

public abstract class BaseMoreFragment<P extends IBasePresenter> extends BaseSwitchFragment<P> {


    public void showContent() {
        baseToolbarActivity.setToolbarVisibility(View.VISIBLE);
        baseToolbarActivity.llRight.setVisibility(View.GONE);
        baseToolbarActivity.tvToolbarLeft.setVisibility(View.GONE);
        baseToolbarActivity.tvToolbarRight.setVisibility(View.GONE);
        baseToolbarActivity.getTvToolbarTitle().setVisibility(View.GONE);
        baseToolbarActivity.getToolbar().setNavigationIcon(R.mipmap.go_back_left_white);
        if (baseToolbarActivity instanceof SportActivity) {
            ((SportActivity) baseToolbarActivity).sportHeaderLl.setVisibility(View.GONE);
            ((SportActivity) baseToolbarActivity).ll_footer_sport.setVisibility(View.GONE);
            ((SportActivity) baseToolbarActivity).llSportMenuBottom.setVisibility(View.GONE);
        }
    }

}
