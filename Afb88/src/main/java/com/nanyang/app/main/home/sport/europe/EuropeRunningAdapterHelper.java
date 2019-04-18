package com.nanyang.app.main.home.sport.europe;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */

public class EuropeRunningAdapterHelper extends EuropeCommonAdapter {
    public EuropeRunningAdapterHelper(Context context) {
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
            if (item.isHomeScoreBigger()) {
                homeScoreTv.setTextColor(Color.RED);
            } else {
                homeScoreTv.setTextColor(Color.BLACK);
            }
            if (item.isAwayScoreBigger()) {
                awayScoreTv.setTextColor(Color.RED);
            } else {
                awayScoreTv.setTextColor(Color.BLACK);
            }
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
