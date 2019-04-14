package com.nanyang.app.main.home.sport.europe;

import android.content.Context;

import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by ASUS on 2019/4/13.
 */

public class EuropeAdapter extends BallAdapterHelper<EuropeInfo> {
    public EuropeAdapter(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, EuropeInfo item) {
        super.onConvert(helper, position, item);
    }
}
