package com.nanyang.app.main.home.sport.europe;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.e_sport.ESportRunningState;
import com.nanyang.app.main.home.sport.e_sport.ESportTodayState;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */
public abstract class EuropeState extends BallState {
    public EuropeState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new EuropeCommonAdapter(getBaseView().getIBaseContext().getBaseActivity());
    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new ESportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new ESportRunningState(getBaseView()));
                break;
        }
    }


    @Override
    public boolean isMix() {
        return false;
    }


    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new EuropeBetHelper(getBaseView());
    }


    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean collection() {
        return false;
    }



    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BallInfo>(baseRecyclerAdapter) {
            @Override
            public BallInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }


            @Override
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds, int oid, String sc, boolean hasPar) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, oid, type, odds, v, isHf, sc, hasPar);

            }

            @Override
            public void clickView(View v, final BallInfo item, int position) {
                switch (v.getId()) {
                    case R.id.module_right_mark_tv:
                        getBaseView().clickItemAdd(v, item, position);
                        break;
                    case R.id.iv_hall_btn:
                        clickHallBtn(v, item, position);
                        break;
                }
            }

        };
    }


    public String getParentText() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Europe_View);
    }


}
