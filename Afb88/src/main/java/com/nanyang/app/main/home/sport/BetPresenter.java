package com.nanyang.app.main.home.sport;

import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.MatchBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/5 0005.
 */

public interface BetPresenter {
    void bet(String url);
    void addMixParlayBet(BettingInfoBean info, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item);
}
