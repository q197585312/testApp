package com.nanyang.app.Utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BallInfo;

/**
 * Created by Administrator on 2019/4/17.
 */

public class BetGoalWindowUtils {
    private static Handler handler;
    private static LinearLayout llContent;

    private static void initLayout(Activity activity) {
        if (llContent == null) {
            FrameLayout view = (FrameLayout) activity.getWindow().getDecorView();
            llContent = new LinearLayout(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            llContent.setLayoutParams(params);
            llContent.setOrientation(LinearLayout.VERTICAL);
            llContent.setPadding(AfbUtils.dp2px(activity, 10), AfbUtils.dp2px(activity, 10), AfbUtils.dp2px(activity, 10), AfbUtils.dp2px(activity, 10));
            view.addView(llContent);
            llContent.setClickable(false);
        }
        if (handler == null) {
            handler = new Handler();
        }
    }

    private static BallInfo lastBallInfo;

    public static void showGoalWindow(Activity activity, BallInfo ballInfo, String match, String homeTeam, int homeTextColor, String awayTeam, int awayTextColor, String homeScore, String awayScore, int type) {
//        if (lastBallInfo != null) {
//            if (lastBallInfo.equals(ballInfo)) {
//                return;
//            }
//        }
//        lastBallInfo = ballInfo;
        initLayout(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AfbUtils.dp2px(activity, 90));
        params.bottomMargin = AfbUtils.dp2px(activity, 10);
        final View view = LayoutInflater.from(activity).inflate(R.layout.item_goal_window, null);
        final ImageView imgClose = view.findViewById(R.id.img_close);
        final TextView tvCount = view.findViewById(R.id.tv_count);
        TextView tvMatch = view.findViewById(R.id.tv_match);
        tvMatch.setText(match);
        TextView tvHome = view.findViewById(R.id.tv_home);
        tvHome.setText(homeTeam);
        tvHome.setTextColor(homeTextColor);
        TextView tvAway = view.findViewById(R.id.tv_away);
        tvAway.setText(awayTeam);
        tvAway.setTextColor(awayTextColor);
        TextView tvHomeScore = view.findViewById(R.id.tv_home_score);
        tvHomeScore.setText(homeScore);
        TextView tvAwayScore = view.findViewById(R.id.tv_away_score);
        tvAwayScore.setText(awayScore);
        if (type == 0) {//0是主队进球 1是客队进球
            tvHomeScore.setTextColor(Color.RED);
        } else {
            tvAwayScore.setTextColor(Color.RED);
        }
        view.setLayoutParams(params);
        llContent.addView(view);
        for (int i = 0; i < llContent.getChildCount(); i++) {
            llContent.getChildAt(i).measure(0, 0);
        }
        SoundPlayUtils.play(SoundPlayUtils.GOAL);
        view.setTag(4);
        handler.post(new Runnable() {
            @Override
            public void run() {
                int tag = (int) view.getTag();
                tvCount.setText(tag + "");
                if (tag == 4) {
                    imgClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            llContent.removeView(view);
                        }
                    });
                }
                if (tag == 0) {
                    handler.removeCallbacks(this);
                    llContent.removeView(view);
                } else {
                    handler.postDelayed(this, 1000);
                }
                tag--;
                view.setTag(tag);
            }
        });
    }

    public static void clear() {
        if (llContent != null) {
            llContent.removeAllViews();
        }
        llContent = null;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        handler = null;
    }
}
