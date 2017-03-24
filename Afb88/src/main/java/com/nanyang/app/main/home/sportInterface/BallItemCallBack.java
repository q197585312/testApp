package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;

public abstract class BallItemCallBack<I> implements SportAdapterHelper.ItemCallBack<I>{
    BaseRecyclerAdapter<I> adapter;

    public BallItemCallBack(BaseRecyclerAdapter<I> adapter) {
        this.adapter = adapter;
    }

    public abstract boolean isItemCollection(I item);

    @Override
    public I getItem(int position) {
        return adapter.getItem(position);
    }
}