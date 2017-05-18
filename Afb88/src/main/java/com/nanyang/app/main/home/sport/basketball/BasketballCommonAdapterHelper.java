package com.nanyang.app.main.home.sport.basketball;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
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
    public void onConvert(MyRecyclerViewHolder helper, final int position, final BasketballCommonInfo item) {
       if(!item.getEvenOdds().equals(item.getOddOdds())||item.getOddOdds().isEmpty()||item.getEvenOdds().isEmpty()||Float.valueOf(item.getOddOdds())==0f||Float.valueOf(item.getEvenOdds())==0f){
           item.setHasOE("0");
       }
        if(!item.getEvenOdds_FH().equals(item.getOddOdds_FH())||item.getOddOdds_FH().isEmpty()||item.getEvenOdds_FH().isEmpty()||Float.valueOf(item.getOddOdds_FH())==0f||Float.valueOf(item.getEvenOdds_FH())==0f){
            item.setHasOE_FH("0");
        }

        if(item.getOU().equals("0")||item.getOU().equals("")){
            item.setHasOU("0");
        }
        if(item.getOU_FH().equals("0")||item.getOU_FH().equals("")){
            item.setHasOU_FH("0");
        }
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);
        View tvRightMark = helper.getView(R.id.module_right_mark_tv);
        View vp = helper.getView(R.id.module_center_sl);
        vp.getLayoutParams().width = DeviceUtils.dip2px(context, 210);
        tvCollection.setVisibility(View.VISIBLE);
        tvRightMark.setVisibility(View.GONE);
        helper.getView(R.id.module_match_time_tv).setVisibility(View.INVISIBLE);
        if (((BallItemCallBack) back).isItemCollection(item))
            tvCollection.setBackgroundResource(R.mipmap.star_red_solid);
        else
            tvCollection.setBackgroundResource(R.mipmap.star_red_not_solid);

        tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.clickView(v, item,position);
            }
        });
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
        getBaseRecyclerAdapter().getItem(position).setIsHdpNew("0");
        getBaseRecyclerAdapter().getItem(position).setIsOUNew("0");
        getBaseRecyclerAdapter().getItem(position).setIsOENew("0");
        getBaseRecyclerAdapter().getItem(position).setIsHdpNew_FH("0");
        getBaseRecyclerAdapter().getItem(position).setIsOUNew_FH("0");
        getBaseRecyclerAdapter().getItem(position).setIsOENew_FH("0");
        String oldHomeName = "";
        String oldAwayName = "";
        String oldHomeGive = "";
        String oldModuleTitle = "";
        if (position > 0) {
            oldHomeName = back.getItem(position - 1).getHome();
            oldAwayName = back.getItem(position - 1).getAway();
            oldHomeGive = back.getItem(position - 1).getIsHomeGive();
            oldModuleTitle = back.getItem(position - 1).getModuleTitle().toString();
        }

        if (item.getModuleTitle().equals(oldModuleTitle) && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getIsHomeGive())) {

        }
        String away = item.getAway();
        String home = item.getHome();
        homeTv.setText(home);
        awayTv.setText(away);

    }


}
