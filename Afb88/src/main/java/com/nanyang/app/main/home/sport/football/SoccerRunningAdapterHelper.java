package com.nanyang.app.main.home.sport.football;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.Utils.BetGoalWindowUtils;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SoccerRunningAdapterHelper extends SoccerCommonAdapterHelper {

    public SoccerRunningAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final BallInfo item) {
        super.onConvert(helper, position, item);
        setRunningItemBg(helper, item);
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        TextView liveTv = helper.getView(R.id.module_match_live_iv);
        TextView timeTv = helper.getView(R.id.module_match_time_tv);
        TextView homeScoreTv = helper.getView(R.id.module_match_home_score_tv);
        TextView awayScoreTv = helper.getView(R.id.module_match_away_score_tv);
        liveTv.setVisibility(View.GONE);
        dateTv.setVisibility(View.GONE);
        if (item.getRunHomeScore() != null && item.getRunAwayScore() != null && !item.getRunAwayScore().equals("") && !item.getRunHomeScore().equals("")) {
            String sHome = item.getRunHomeScore();
            String sAway = item.getRunAwayScore();
            awayScoreTv.setText(sAway);
            homeScoreTv.setText(sHome);
            homeScoreTv.setTextColor(item.getHomeScoreTextColor());
            awayScoreTv.setTextColor(item.getAwayScoreTextColor());
        } else {
            awayScoreTv.setText("");
            homeScoreTv.setText("");
        }
        if (item.getLive().contains("HT")) {
            timeTv.setText("HT");
        } else {
            int min;
            try {
                String mExtraTime = item.getMExtraTime();
                String timeStr;
                switch (item.getStatus()) {
                    case "0":
                        timeTv.setText("LIVE");
                        break;
                    case "2":
                        min = Integer.valueOf(item.getCurMinute());
                        if (min < 130 && min > 0) {
                            timeStr = "2H " + min + "'";
                            if (!TextUtils.isEmpty(mExtraTime) && !mExtraTime.equals("0")) {
                                timeStr += "+" + mExtraTime;
                            }
                        } else {
                            timeStr = "";
                        }
                        timeTv.setText(timeStr);
                        break;
                    default:
                        min = Integer.valueOf(item.getCurMinute());
                        if (min < 130 && min > 0) {
                            timeStr = "1H " + min + "'";
                            if (!TextUtils.isEmpty(mExtraTime) && !mExtraTime.equals("0")) {
                                timeStr += "+" + mExtraTime;
                            }
                        } else {
                            timeStr = "";
                        }
                        timeTv.setText(timeStr);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                timeTv.setText("");
            }
        }
        dateTv.setTextColor(Color.RED);
//        timeTv.setTextColor(context.getResources().getColor(R.color.red_title));
    }
}
