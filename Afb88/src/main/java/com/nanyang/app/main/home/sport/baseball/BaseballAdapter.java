package com.nanyang.app.main.home.sport.baseball;

import android.content.Context;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballCommonInfo;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/6/6.
 */

public class BaseballAdapter extends BallAdapterHelper<BasketballCommonInfo> {
    public BaseballAdapter(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, BasketballCommonInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        markAdd.setVisibility(View.GONE);
        tvCollection.setVisibility(View.GONE);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        scrollChild(sl.getChildAt(0), false, item, item.getIsHomeGive(), item.getHasHdp(), item.getHdp(), item.getHasOU(), item.getOU(), item.getIsHdpNew(), item.getIsOUNew(), item.getUnderOdds(), item.getOverOdds(), item.getHomeHdpOdds(), item.getAwayHdpOdds(), "home", "away", "over", "under",
                true, true, true, item.getHasOE(), item.getIsOENew(), item.getOddOdds(), item.getEvenOdds(), "odd", "even"
        );
        scrollChild(sl.getChildAt(1), true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH()
                , "home", "away", "over", "under",
                true, true, true,
                item.getHasOE_FH(), item.getIsOENew_FH(), item.getOddOdds_FH(), item.getEvenOdds_FH()
                , "odd", "even"
        );
    }
}
