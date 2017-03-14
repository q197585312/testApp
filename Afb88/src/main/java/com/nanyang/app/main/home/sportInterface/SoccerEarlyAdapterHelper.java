package com.nanyang.app.main.home.sportInterface;

import android.content.Context;

import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SoccerEarlyAdapterHelper extends SoccerCommonAdapterHelper {
    public SoccerEarlyAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final SoccerCommonInfo item) {
        super.onConvert(helper, position, item);

    }


}
