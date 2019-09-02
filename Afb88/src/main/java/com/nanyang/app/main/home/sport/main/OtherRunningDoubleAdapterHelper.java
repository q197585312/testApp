package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.main.home.sport.football.SoccerRunningAdapterHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/5/11.
 */

public class OtherRunningDoubleAdapterHelper extends SoccerRunningAdapterHelper {
    public OtherRunningDoubleAdapterHelper(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {
        super.onConvert(helper, position, item);
        setRunningItemBg(helper, item);
    }

    @Override
    protected void noContainsLive(BallInfo item, TextView timeTv) {
        timeTv.setText("");
    }

    @Override
    protected void handleLiveTimeTv(BallInfo item, TextView timeTv) {
        super.handleLiveTimeTv(item, timeTv);
        timeTv.setVisibility(View.INVISIBLE);
    }
}
