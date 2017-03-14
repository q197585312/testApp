package com.nanyang.app.main.home.sportInterface;

import android.content.Context;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SoccerMixAdapterHelper extends BallAdapterHelper<SoccerMixInfo> {
    public SoccerMixAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final SoccerMixInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        markAdd.setVisibility(View.VISIBLE);
        tvCollection.setVisibility(View.GONE);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        View hfView = scrollChild(item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH());
        sl.addView(hfView,SoccerHeaderContent.layoutParams);
    }


}
