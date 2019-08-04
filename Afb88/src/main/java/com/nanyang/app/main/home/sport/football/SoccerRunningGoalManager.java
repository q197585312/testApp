package com.nanyang.app.main.home.sport.football;

import android.graphics.Color;
import android.widget.TextView;

import com.nanyang.app.main.home.sport.model.BallInfo;

import java.util.HashMap;

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

    public boolean isHomeGoal(BallInfo item) {
        Boolean isHomeGoal = homeGoal.get(item.getSocOddsId());
        if (isHomeGoal != null)
            return isHomeGoal;
        String runHomeScore = item.getRunHomeScore();
        String runAwayScore = item.getRunAwayScore();
        if (runHomeScore.equals("0") && runAwayScore.equals("0"))
            return false;
        if (item.getIsHomeGoal().equals("1"))
            return true;
        return false;
    }

    public boolean isAwayGoal(BallInfo item) {
        Boolean isHomeGoal = awayGoal.get(item.getSocOddsId());
        if (isHomeGoal != null)
            return isHomeGoal;
        String runHomeScore = item.getRunHomeScore();
        String runAwayScore = item.getRunAwayScore();
        if (runHomeScore.equals("0") && runAwayScore.equals("0"))
            return false;
        if (item.getIsHomeGoal().equals("0"))
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


}
