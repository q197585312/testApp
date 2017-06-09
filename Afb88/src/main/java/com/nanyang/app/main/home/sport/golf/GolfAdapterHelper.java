package com.nanyang.app.main.home.sport.golf;

import android.content.Context;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */



public class GolfAdapterHelper extends BallAdapterHelper<BasketballMixInfo> {


    public GolfAdapterHelper(Context context) {
        super(context);

    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final BasketballMixInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        markAdd.setVisibility(View.GONE);
        tvCollection.setVisibility(View.GONE);
    }



}