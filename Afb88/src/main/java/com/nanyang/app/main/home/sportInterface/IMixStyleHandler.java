package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import com.nanyang.app.main.home.sport.model.BettingParPromptBean;

/**
 * Created by Administrator on 2017/4/20.
 */

public interface IMixStyleHandler {
    BettingParPromptBean.BetParBean  getMixItem(String proId);

    void setCommonBackground(TextView view);

    void setMixBackground(TextView view);
}
