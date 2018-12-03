package com.nanyang.app.main.home.sport.europe;

import com.nanyang.app.main.home.sport.main.AfbParseHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.main.SportState;
import com.nanyang.app.main.home.sport.model.BallInfo;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Administrator on 2018/12/3.
 */

public abstract class BallState extends SportState<BallInfo, SportContract.View<BallInfo>>  {
    public BallState(SportContract.View<BallInfo> baseView) {
        super(baseView);
    }

    @Override
    protected BallInfo parseMatch(JSONArray matchArray,boolean notify) throws JSONException {
        return new AfbParseHelper<>().parseJsonArray(matchArray,notify);
    }
}
