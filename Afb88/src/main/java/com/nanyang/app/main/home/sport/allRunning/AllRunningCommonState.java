package com.nanyang.app.main.home.sport.allRunning;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.main.AfbParseHelper;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by ASUS on 2019/4/26.
 */

public class AllRunningCommonState extends OutRightState {

    public AllRunningCommonState(SportContract.View baseView) {
        super(baseView);
    }

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
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds, int oid, String sc, boolean hasPar) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
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
                        getBaseView().clickItemAdd(v, item, position);
                        break;
                    case R.id.iv_hall_btn:
                        clickHallBtn(v, item, position);
                        break;
                }

            }
        };

    }

    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.all_running);
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                setStateItemOt("e");
                getBaseView().switchState(this);
                break;
            case "Today":
                setStateItemOt("t");
                getBaseView().switchState(this);
                break;
            case "Running":
                setStateItemOt("r");
                getBaseView().switchState(this);
                break;

        }
    }

    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getRefreshUrl() {
        return null;
    }

    /*    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new BallAdapterHelper<BallInfo>();
    }*/
    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new BallBetHelper<BallInfo, BetView>(getBaseView()) {
            @Override
            public String getBallG() {
                return fragment.currentIdBean.getId();
            }
        };
    }

    @Override
    public String getDbId() {
        return fragment.currentIdBean.getDbid();
    }



    @Override
    public MenuItemInfo getStateType() {
        if (StringUtils.isNull(text))
            text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today);
        return new MenuItemInfo<String>(0, text, "AllRunning", getSportName());
    }

    public void setStateItemOt(String ot) {
        switch (ot) {
            case "e":
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early);
                this.ot = "e";
                break;
            case "r":
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running);
                this.ot = "r";
                break;
            default:
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today);
                this.ot = "t";
                break;
        }


    }

    @Override
    protected BallInfo parseMatch(JSONArray matchArray, boolean notify) throws JSONException {
        AfbParseHelper helper = new AfbParseHelper();
        return helper.parseJsonArray(matchArray, notify);
    }

}
