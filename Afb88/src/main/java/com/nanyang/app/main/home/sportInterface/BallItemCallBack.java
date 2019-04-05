package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;

public abstract class BallItemCallBack<I> implements SportAdapterHelper.ItemCallBack<I> {
    BaseRecyclerAdapter<I> adapter;

    public BallItemCallBack(BaseRecyclerAdapter<I> adapter) {
        this.adapter = adapter;
    }

    public boolean isItemCollection(I item) {
        return false;
    }

    public boolean isLeagueCollection(I item) {
        return false;
    }

    @Override
    public I getItem(int position) {
        return adapter.getItem(position);
    }


}