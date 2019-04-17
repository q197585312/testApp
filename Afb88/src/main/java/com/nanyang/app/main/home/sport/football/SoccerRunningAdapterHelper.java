package com.nanyang.app.main.home.sport.football;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        titleContentColor = R.color.green_content1;
        normalContentColor = R.color.green_content2;
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
            String isHomeGive = item.getIsHomeGive();
            int homeTextColor;
            int awayTextColor;
            if (isHomeGive.equals("1")) {
                homeTextColor = Color.RED;
                awayTextColor = Color.BLACK;
            } else {
                homeTextColor = Color.BLACK;
                awayTextColor = Color.RED;
            }
            Activity activity = (Activity) context;
            if (item.isHomeScoreBigger()) {
                item.setHomeScoreTextColor(Color.RED);
                BetGoalWindowUtils.showGoalWindow(activity, item.getModuleTitle(), item.getHome(), homeTextColor, item.getAway(), awayTextColor, sHome, sAway, 0);
                item.setHomeScoreBigger(false);
            }
            homeScoreTv.setTextColor(item.getHomeScoreTextColor());
            if (item.isAwayScoreBigger()) {
                item.setAwayScoreTextColor(Color.RED);
                BetGoalWindowUtils.showGoalWindow(activity, item.getModuleTitle(), item.getHome(), homeTextColor, item.getAway(), awayTextColor, sHome, sAway, 1);
                item.setAwayScoreBigger(false);
            }
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

                switch (item.getStatus()) {
                    case "0":
                        timeTv.setText("LIVE");
                        break;
                    case "2":
                        min = Integer.valueOf(item.getCurMinute());

                        if (min < 130 && min > 0) {
                            timeTv.setText("2H " + min + "'");
                        } else {
                            timeTv.setText("");
                        }
                        break;
                    default:
                        min = Integer.valueOf(item.getCurMinute());
                        if (min < 130 && min > 0) {
                            timeTv.setText("1H " + min + "'");
                        } else {
                            timeTv.setText("");
                        }
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
