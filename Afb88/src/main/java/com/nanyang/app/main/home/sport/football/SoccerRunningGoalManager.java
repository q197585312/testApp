package com.nanyang.app.main.home.sport.football;

import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.nanyang.app.main.home.sport.model.BallInfo;

import java.util.HashMap;

import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by ASUS on 2019/7/1.
 */

public class SoccerRunningGoalManager {

    private static SoccerRunningGoalManager instance;
    HashMap<String, Boolean> homeGoal = new HashMap<>();
    HashMap<String, Boolean> awayGoal = new HashMap<>();


    private SoccerRunningGoalManager() {

    }

    public static SoccerRunningGoalManager getInstance() {
        if (instance == null) {
            instance = new SoccerRunningGoalManager();
        }
        return instance;
    }

    public void runScoreStyle(String socOddsId, TextView tvScore, String hScore, String aScore, String score, String isHomeGoal) {
        if (!StringUtils.isEmpty(hScore) && !StringUtils.isEmpty(aScore)) {
            SpannableString spanString = new SpannableString(hScore + " - " + aScore + " ");
            //构造一个改变字体颜色的Span
            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
            if (isHomeGoal(socOddsId, hScore, aScore, isHomeGoal)) {
                spanString.setSpan(span, 0, hScore.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            } else if (isAwayGoal(socOddsId, hScore, aScore, isHomeGoal)) {
                spanString.setSpan(span, hScore.length() + 3, spanString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            tvScore.setText(spanString);
        } else {
            tvScore.setText(score + " ");
        }
    }


    public boolean isHomeGoal(BallInfo item) {
      /*  Boolean isHomeGoal = homeGoal.get(item.getSocOddsId());
        if (isHomeGoal != null)
            return isHomeGoal;
        String runHomeScore = item.getRunHomeScore();
        String runAwayScore = item.getRunAwayScore();
        if (runHomeScore.equals("0") && runAwayScore.equals("0"))
            return false;
        if (runAwayScore.equals("0")) {
            return true;
        }
        if (item.getIsHomeGoal().equals("1"))
            return true;
        return false;*/
        return isHomeGoal(item.getSocOddsId(), item.getRunHomeScore(), item.getRunAwayScore(), item.getIsHomeGoal());
    }

    public boolean isHomeGoal(String sid, String runHomeScore, String runAwayScore, String HomeGoal) {
        Boolean isHomeGoal = homeGoal.get(sid);
        if (isHomeGoal != null)
            return isHomeGoal;

        if (runHomeScore.equals("0") && runAwayScore.equals("0"))
            return false;
        if (runAwayScore.equals("0")) {
            return true;
        }
        if (HomeGoal.equals("1") || HomeGoal.equals("True"))
            return true;
        return false;
    }

    public boolean isAwayGoal(BallInfo item) {
        return isAwayGoal(item.getSocOddsId(), item.getRunHomeScore(), item.getRunAwayScore(), item.getIsHomeGoal());
    }

    public boolean isAwayGoal(String sid, String runHomeScore, String runAwayScore, String HomeGoal) {
        Boolean isHomeGoal = awayGoal.get(sid);
        if (isHomeGoal != null)
            return isHomeGoal;

        if (runHomeScore.equals("0") && runAwayScore.equals("0"))
            return false;
        if (runHomeScore.equals("0")) {
            return true;
        }
        if (HomeGoal.equals("0") || HomeGoal.equals("False"))
            return true;
        return false;
    }

    public void putHomeGoal(BallInfo item, boolean isGoal) {
        homeGoal.put(item.getSocOddsId(), isGoal);
        if (isGoal)
            awayGoal.put(item.getSocOddsId(), false);

    }

    public void putAwayGoal(BallInfo item, boolean isGoal) {
        awayGoal.put(item.getSocOddsId(), isGoal);
        if (isGoal)
            homeGoal.put(item.getSocOddsId(), false);

    }

    public void clear() {
        homeGoal.clear();
        awayGoal.clear();
    }

    public void handleGoalStyle(BallInfo item, TextView homeScoreTv, TextView awayScoreTv) {
        if (item.getRunHomeScore() != null && item.getRunAwayScore() != null && !item.getRunAwayScore().equals("") && !item.getRunHomeScore().equals("")) {
            String sHome = item.getRunHomeScore();
            String sAway = item.getRunAwayScore();
            awayScoreTv.setText(sAway);
            homeScoreTv.setText(sHome);
            if (isHomeGoal(item)) {
                homeScoreTv.setTextColor(Color.RED);
            } else {
                homeScoreTv.setTextColor(Color.BLACK);
            }
            if (isAwayGoal(item)) {
                awayScoreTv.setTextColor(Color.RED);
            } else {
                awayScoreTv.setTextColor(Color.BLACK);
            }

        } else {
            awayScoreTv.setText("");
            homeScoreTv.setText("");
        }
    }

    public void runTime(TextView timeTv, String mExtraTime30, String teamStatus28, String curMinute29) {
        timeTv.setText("");
        int min;
        try {
            String mExtraTime = mExtraTime30;
            String timeStr;
            switch (teamStatus28) {
                case "0":
                    break;
                case "2":
                    min = Integer.valueOf(curMinute29);
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
                    min = Integer.valueOf(curMinute29);
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

    public void runTimeStyle(TextView tv_running_status, String mExtraTime30, String teamStatus28, String curMinute29, String live31) {
        //0 - 0<br><font color=red>PEN</font>
        runTimeStyleColor(tv_running_status, mExtraTime30, teamStatus28, curMinute29, live31, Color.BLACK);
    }

    public void runTimeStyleColor(TextView tv_running_status, String mExtraTime30, String teamStatus28, String curMinute29, String live31, int color) {
        //0 - 0<br><font color=red>PEN</font>
        String spanned = live31;
        if (live31.contains("br") || live31.contains("BR") || live31.contains("Br")) {
            spanned = Html.fromHtml(live31).toString();
        }
        if (spanned.contains("\n")) {
            String[] split = spanned.split("\\n");
            tv_running_status.setText(split[1]);
            tv_running_status.setTextColor(Color.RED);
        } else {
            SoccerRunningGoalManager.getInstance().runTime(tv_running_status, mExtraTime30, teamStatus28, curMinute29);
            tv_running_status.setTextColor(color);
        }
    }
}