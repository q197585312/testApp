package com.nanyang.app.main.home.sport.basketball;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.BallAdapterHelper;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.SoccerHeaderContent;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/3/14.
 */

public class BasketballCommonAdapterHelper extends BallAdapterHelper<BasketballCommonInfo> {
    public BasketballCommonAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final BasketballCommonInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);
        TextView tvRightMark = helper.getView(R.id.module_right_mark_tv);
        View vp = helper.getView(R.id.module_center_sl);
        vp.getLayoutParams().width = DeviceUtils.dip2px(context, 210);
        tvCollection.setVisibility(View.VISIBLE);
        tvRightMark.setVisibility(View.GONE);

        if (((BallItemCallBack) back).isItemCollection(item))
            tvCollection.setBackgroundResource(R.mipmap.collection_star_yellow_soild);
        else
            tvCollection.setBackgroundResource(R.mipmap.collection_star_yellow_not_soild);

        tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.clickView(v, item);
            }
        });
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        sl.removeAllViews();
        View view = scrollChild(false, item, item.getIsHomeGive(), item.getHasHdp(), item.getHdp(), item.getHasOU(), item.getOU(), item.getIsHdpNew(), item.getIsOUNew(), item.getUnderOdds(), item.getOverOdds(), item.getHomeHdpOdds(), item.getAwayHdpOdds(),
        true,item.getHasOE(),item.getIsOENew(),item.getOddOdds(),item.getEvenOdds()
        );
        View hfView = scrollChild(true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH()
        ,  true,item.getHasOE_FH(),item.getIsOENew_FH(),item.getOddOdds_FH(),item.getEvenOdds_FH());

        sl.addView(view, SoccerHeaderContent.layoutParams);
        sl.addView(hfView, SoccerHeaderContent.layoutParams);

        String oldHomeName = "";
        String oldAwayName = "";
        String oldHomeGive = "";
        String oldModuleTitle = "";
        if (position > 0) {
            oldHomeName = back.getItem(position - 1).getHome();
            oldAwayName = back.getItem(position - 1).getAway();
            oldHomeGive = back.getItem(position - 1).getIsHomeGive();
            oldModuleTitle = back.getItem(position - 1).getModuleTitle();
        }

        if (item.getModuleTitle().equals(oldModuleTitle) && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getIsHomeGive())) {
            item.setHasOE("0");
        }
        String away = item.getAway();
        String home = item.getHome();
        homeTv.setText(home);
        awayTv.setText(away);

    }



}
