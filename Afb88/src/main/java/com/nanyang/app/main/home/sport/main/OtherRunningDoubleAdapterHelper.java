package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
    protected void handleLiveTimeTv(BallInfo item, TextView timeTv) {
        String live = item.getLive();
        Log.d("Basketball", "handleLiveTimeTv: " + live);
        if (live.contains("\n")) {
            String replace = live.replace("\n", ",");
            String[] split = replace.split(",");
            timeTv.setText(split[1]);
            timeTv.setTextColor(Color.RED);

        } else {
            timeTv.setText("");
        }
    }
}
