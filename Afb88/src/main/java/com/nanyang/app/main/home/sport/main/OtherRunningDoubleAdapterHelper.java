package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/5/11.
 */

public class OtherRunningDoubleAdapterHelper extends OtherDoubleAdapterHelper {
    public OtherRunningDoubleAdapterHelper(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, BasketballMixInfo item) {
        super.onConvert(helper, position, item);
        helper.getView(R.id.module_match_time_tv).setVisibility(View.INVISIBLE);
    }
}
