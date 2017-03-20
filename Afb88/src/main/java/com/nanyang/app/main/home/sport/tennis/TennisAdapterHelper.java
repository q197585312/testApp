package com.nanyang.app.main.home.sport.tennis;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.BallAdapterHelper;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */

public class TennisAdapterHelper extends BallAdapterHelper<TennisInfo> {
    public TennisAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final TennisInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);
        TextView tvRightMark = helper.getView(R.id.module_right_mark_tv);

        View llLeft1 = helper.getView(R.id.module_match_left1_ll);
        View llLeft2 = helper.getView(R.id.module_match_left2_ll);
        tvCollection.setVisibility(View.VISIBLE);
        tvRightMark.setVisibility(View.VISIBLE);

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
            awayTv.setText("");
            homeTv.setText("");
            tvRightMark.setVisibility(View.INVISIBLE);
            llLeft1.setVisibility(View.INVISIBLE);
            llLeft2.setVisibility(View.INVISIBLE);
            tvCollection.setVisibility(View.INVISIBLE);
            item.setHasOE("0");
        } else {
            String away = item.getAway();
            String home = item.getHome();
            homeTv.setText(home);
            awayTv.setText(away);
            tvRightMark.setVisibility(View.VISIBLE);
            tvCollection.setVisibility(View.VISIBLE);
            llLeft1.setVisibility(View.VISIBLE);
            llLeft2.setVisibility(View.VISIBLE);
        }

    }

}
