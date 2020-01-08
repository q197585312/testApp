package com.nanyang.app.main.home.sport.live;

import com.nanyang.app.main.home.sport.additional.AddedParamsInfo;
import com.nanyang.app.main.home.sport.model.BallInfo;

/**
 * Created by Administrator on 2020/1/5.
 */

public class LiveParamsInfo extends AddedParamsInfo {
    private String gameUrl;
    private String livePlayUrlId;

    public String getBallG() {
        return ballG;
    }

    public void setBallG(String ballG) {
        this.ballG = ballG;
    }

    private String ballG;


    public LiveParamsInfo(BallInfo bean, String dbid, String oddsType) {
        super(bean, dbid, oddsType);
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public String getLivePlayUrlId() {
        return livePlayUrlId;
    }

    public void setLivePlayUrlId(String livePlayUrlId) {
        this.livePlayUrlId = livePlayUrlId;
    }
}
