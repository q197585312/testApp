package com.nanyang.app.main.home.sport.football;

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
        Boolean isHomeGoal = homeGoal.get(item.ModuleId + "-" + item.getHomeId() + "-" + item.getAwayId());
        if (isHomeGoal != null)
            return isHomeGoal;
        return false;
    }

    public boolean isAwayGoal(BallInfo item) {
        Boolean isHomeGoal = awayGoal.get(item.ModuleId + "-" + item.getHomeId() + "-" + item.getAwayId());
        if (isHomeGoal != null)
            return isHomeGoal;
        return false;
    }

    public void putHomeGoal(BallInfo item, boolean isGoal) {
        homeGoal.put(item.ModuleId + "-" + item.getHomeId() + "-" + item.getAwayId(), isGoal);
        if (isGoal)
            awayGoal.put(item.ModuleId + "-" + item.getHomeId() + "-" + item.getAwayId(), false);

    }

    public void putAwayGoal(BallInfo item, boolean isGoal) {
        awayGoal.put(item.ModuleId + "-" + item.getHomeId() + "-" + item.getAwayId(), isGoal);
        if (isGoal)
            homeGoal.put(item.ModuleId + "-" + item.getHomeId() + "-" + item.getAwayId(), false);

    }
    public void clear(){
        homeGoal.clear();
        awayGoal.clear();
    }


}
