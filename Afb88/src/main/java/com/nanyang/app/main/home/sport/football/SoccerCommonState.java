package com.nanyang.app.main.home.sport.football;

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

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class SoccerCommonState extends BallState {
    public SoccerCommonState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        SoccerCommonAdapterHelper adapterHelper = onSetCommonAdapterHelper();
        return adapterHelper;
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BallInfo>(baseRecyclerAdapter) {
            @Override
            public boolean isItemCollection(BallInfo item) {
                return isItemCollectionCommon(item);
            }

            @Override
            public boolean isLeagueCollection(BallInfo item) {
                return isLeagueCollectionCommon(item);
            }

            @Override
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds, int oid, String sc,boolean hasPar) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
//                (B itemData, int oid, String type, String odds, TextView v, boolean isHf, String params ,boolean hasPar);
                helper.clickOdds(item, oid, type, odds, v, isHf, sc, hasPar);
            }

            @Override
            public void clickView(View v, BallInfo item, int position) {
                switch (v.getId()) {
                    case R.id.module_match_collection_tv:
                        collectionItemCommon(item);
                        break;
                    case R.id.module_League_collection_tv:
                        collectionLeagueCommon(item);
                        break;
                    case R.id.module_right_mark_tv:
                        clickAdd(v, item, position);
                        break;
                    case R.id.iv_hall_btn:
                        clickHallBtn(v, item, position);
                        break;
                }

            }
        };
    }

    protected void clickHallBtn(View v, BallInfo item, int position) {

    }

    private void clickAdd(View v, BallInfo item, int position) {
        getBaseView().clickItemAdd(v, item, position);
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new SoccerCommonBetHelper(getBaseView());
    }

    protected abstract SoccerCommonAdapterHelper onSetCommonAdapterHelper();

}
