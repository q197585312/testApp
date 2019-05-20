package com.nanyang.app.main.home.sport.basketball;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.europe.BallState;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class BasketballMixState extends BallState {

    private boolean isCollection;


    public BasketballMixState(SportContract.View baseView) {
        super(baseView);
    }

    public boolean isCollection() {
        return isCollection;
    }

    public boolean collection() {
        isCollection = !isCollection;
        initAllData(allData);
        return isCollection;
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new BasketballMixBetHelper(getBaseView());
    }

    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new IAdapterHelper<BallInfo>() {
            @Override
            public void onConvert(MyRecyclerViewHolder holder, int position, BallInfo item) {

            }

            @Override
            public int onSetAdapterItemLayout() {
                return 0;
            }
        };
//        return new BasketballAdapterHelper(getBaseView().getIBaseContext());
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BallInfo>(baseRecyclerAdapter) {


            @Override
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds,int oid,String sc,boolean hasPar) {
                IBetHelper<BallInfo> helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item,oid, type, odds, v, isHf, "", hasPar);
            }

            @Override
            public void clickView(View v, BallInfo item, int position) {

            }

        };
    }



    @Override
    public boolean mix() {
        clearMix();
        return false;
    }

    @Override
    public boolean isMix() {
        return true;
    }

    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> lists = super.initHeaderList();
        lists.get(1).set(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.TO_WIN));
        lists.get(1).set(1, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FULL_O_E));
        return lists;
    }

}
