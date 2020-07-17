package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */

/**
 * {
 * "SocOddsId": 12219288,
 * "Home": "AS摩纳哥",
 * "Away": "曼城",
 * "IsHomeGive": false,
 * "IsBetHome": false,
 * "ParFullTimeId": "12219287",
 * "TransType": "HDP",
 * "IsOddsChange": false,
 * "ParUrl": "http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=away_par&oId=12219288&odds=19",
 * "BetHdp": "0",
 * "BetOu": "0",
 * "BetOdds": "1.90",
 * "Test2": "testing2"
 * }
 */
public class SoccerMixAdapterHelper extends BallAdapterHelper<BallInfo> {


    public SoccerMixAdapterHelper(Context context) {
        super(context);

    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final BallInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);

        tvCollection.setVisibility(View.GONE);
        FrameLayout sl = helper.getView(R.id.module_center_sl);
         getBaseRecyclerAdapter().getItem(position).setIsHdpNew_FH("0");
        getBaseRecyclerAdapter().getItem(position).setIsOUNew_FH("0");
        String itemFullSocOddsId = item.getSocOddsId();

        BaseMixStyleHandler handler = new BaseMixStyleHandler((BaseToolbarActivity) context);
        OddsClickBean mixItem = handler.getMixItem(itemFullSocOddsId);

        int index = 0;

        if (mixItem != null ) {
            handler.parseMixBackground(mixItem, index, sl, "home", "away", "over", "under", "odd", "even");
        } else {
            handler.parseCommonBackground(0, sl);
            handler.parseCommonBackground(1, sl);
        }

    }

}
