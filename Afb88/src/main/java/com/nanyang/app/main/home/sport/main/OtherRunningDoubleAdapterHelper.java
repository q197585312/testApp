package com.nanyang.app.main.home.sport.main;

import android.content.Context;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/5/11.
 */

public class OtherRunningDoubleAdapterHelper extends OtherDoubleAdapterHelper {
    public OtherRunningDoubleAdapterHelper(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {
        super.onConvert(helper, position, item);
        setRunningItemBg(helper, item);
    }
}
