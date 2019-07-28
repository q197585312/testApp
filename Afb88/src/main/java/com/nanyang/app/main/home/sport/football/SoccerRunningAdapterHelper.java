package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
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
        setRunningItemBg(helper, item);
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        TextView liveTv = helper.getView(R.id.module_match_live_iv);
        TextView timeTv = helper.getView(R.id.module_match_time_tv);

        liveTv.setVisibility(View.GONE);
        dateTv.setVisibility(View.GONE);
        handleLiveTimeTv(item, timeTv);
        dateTv.setTextColor(Color.RED);
    }



    protected void handleLiveTimeTv(BallInfo item, TextView timeTv) {
        String live = item.getLive();
        if (live.contains("\n") || live.contains("HT") || live.contains("PEN") || live.contains("LIVE")) {
            String replace = live.replace("\n", ".");
            String[] split = replace.split(".");
            timeTv.setText(split[1]);
                timeTv.setTextColor(Color.RED);

        } else {
            String matchDate = item.getMatchDate();
            timeTv.setText(matchDate);
            int min;
            try {
                String mExtraTime = item.getMExtraTime();
                String timeStr;
                switch (item.getStatus()) {
                    case "0":
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
                        timeTv.setTextColor(Color.BLACK);
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
                        timeTv.setTextColor(Color.BLACK);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                timeTv.setText("");
            }
        }
    }
}
