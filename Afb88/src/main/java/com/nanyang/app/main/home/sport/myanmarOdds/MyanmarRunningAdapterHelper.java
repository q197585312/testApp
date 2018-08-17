package com.nanyang.app.main.home.sport.myanmarOdds;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;

import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MyanmarRunningAdapterHelper extends MyanmarAdapterHelper {
    public MyanmarRunningAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final MyanmarInfo item) {
        super.onConvert(helper, position, item);
        TextView dateTv=helper.getView(R.id.module_match_date_tv);
        TextView dateTv1=helper.getView(R.id.module_match_date_tv1);
        TextView liveTv=helper.getView(R.id.module_match_live_iv);
        TextView liveTv1=helper.getView(R.id.module_match_live_iv1);
        TextView timeTv=helper.getView(R.id.module_match_time_tv);
        TextView timeTv1=helper.getView(R.id.module_match_time_tv1);
        dateTv.setTextAppearance(context, R.style.text_bold);
        dateTv1.setTextAppearance(context, R.style.text_bold);
        View viewRight = helper.getView(R.id.module_right_mark_tv);
        View viewRight1 = helper.getView(R.id.fl_right);
        viewRight.setVisibility(View.GONE);
        viewRight1.setVisibility(View.GONE);
        dateTv.setPadding(0, 0, 10, 0);
        dateTv1.setPadding(0, 0, 10, 0);
        liveTv.setVisibility(View.GONE);
        liveTv1.setVisibility(View.GONE);
        if (item.getRunHomeScore() != null && item.getRunAwayScore() != null && !item.getRunAwayScore().equals("") && !item.getRunHomeScore().equals("")) {
            String sHome = item.getRunHomeScore();
            String sAway = item.getRunAwayScore();
            dateTv.setText(sHome + "-" + sAway);
            dateTv1.setText(sHome + "-" + sAway);

        } else {
            dateTv.setText("");
            dateTv1.setText("");
        }
        if (item.getLive().contains("HT")) {
            timeTv.setText("HT");
            timeTv1.setText("HT");
        } else {
            int min;
            int start;
            try {

                switch (item.getStatus()) {
                    case "0":
                        timeTv.setText(context.getString(R.string.not_started));
                        timeTv1.setText(context.getString(R.string.not_started));
                        break;
                    case "2":
                        min = Integer.valueOf(item.getCurMinute());
                        start = 45;
                        min = min + start;
                        if (min < 130 && min > 0) {
                            timeTv.setText(min + context.getString(R.string.min));
                            timeTv1.setText(min + context.getString(R.string.min));
                        } else {
                            timeTv.setText("");
                            timeTv1.setText("");
                        }
                        break;
                    default:
                        min = Integer.valueOf(item.getCurMinute());
                        if (min < 130 && min > 0) {
                            timeTv.setText(min + context.getString(R.string.min));
                            timeTv1.setText(min + context.getString(R.string.min));
                        } else {
                            timeTv.setText("");
                            timeTv1.setText("");
                        }
                        break;
                }


            } catch (Exception e) {
                e.printStackTrace();
                timeTv.setText("");
                timeTv1.setText("");
            }
        }
        dateTv.setTextColor(context.getResources().getColor(R.color.red_title));
        dateTv1.setTextColor(context.getResources().getColor(R.color.red_title));
        timeTv.setTextColor(context.getResources().getColor(R.color.red_title));
        timeTv1.setTextColor(context.getResources().getColor(R.color.red_title));
    }


}
