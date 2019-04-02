package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.R;
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
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        TextView liveTv = helper.getView(R.id.module_match_live_iv);
        TextView timeTv = helper.getView(R.id.module_match_time_tv);
        TextView homeScoreTv = helper.getView(R.id.module_match_home_score_tv);
        TextView awayScoreTv = helper.getView(R.id.module_match_away_score_tv);
        dateTv.setTextAppearance(context, R.style.text_bold);
        dateTv.setPadding(0, 0, 10, 0);
        liveTv.setVisibility(View.GONE);
        if (item.getRunHomeScore() != null && item.getRunAwayScore() != null && !item.getRunAwayScore().equals("") && !item.getRunHomeScore().equals("")) {
            String sHome = item.getRunHomeScore();
            String sAway = item.getRunAwayScore();
            awayScoreTv.setText(sAway);
            homeScoreTv.setText(sHome);

        } else {
            dateTv.setText("");
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
        dateTv.setTextColor(context.getResources().getColor(R.color.red_title));
        timeTv.setTextColor(context.getResources().getColor(R.color.red_title));


    }

    @Override
    protected void onMatchNotRepeat(MyRecyclerViewHolder helper, final BallInfo item, final int position) {
        super.onMatchNotRepeat(helper, item, position);
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        String rtsMatchId = item.getRTSMatchId();
        if (rtsMatchId != null && !rtsMatchId.isEmpty() && !rtsMatchId.equals("0")) {
            ivHall.setVisibility(View.VISIBLE);
            ivHall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    back.clickView(v, item, position);
                }
            });
        } else {
            ivHall.setVisibility(View.GONE);
        }


    }

    @Override
    protected void onMatchRepeat(MyRecyclerViewHolder helper, BallInfo item, int position) {
        super.onMatchRepeat(helper, item, position);
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        ivHall.setVisibility(View.GONE);
    }
}
