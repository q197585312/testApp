package com.nanyang.app.main.home.sport.financial;

import android.content.Context;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */



public class OtherSingleAdapterHelper extends BallAdapterHelper<BallInfo> {


    public OtherSingleAdapterHelper(Context context) {
        super(context);

    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final BallInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        markAdd.setVisibility(View.GONE);
        tvCollection.setVisibility(View.GONE);
    }



}