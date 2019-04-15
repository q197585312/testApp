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
import com.unkonw.testapp.libs.utils.LogUtil;

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
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds, int oid, String sc) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type, odds, v, isHf, sc);
                LogUtil.d("xxxx", "点击的Item：" + item.toString() + ",odds=" + odds, ",v=" + v, ",isHf=" + isHf, ",sc=" + sc);
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

    //http://a8197c.a36588.com/_Bet/JRecPanel.aspx?gt=s&b=under&oId=12159615&oId_fh=12159616&isFH=true&isRun=true&odds=4.70


    protected abstract SoccerCommonAdapterHelper onSetCommonAdapterHelper();


}
