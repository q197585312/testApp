package com.nanyang.app.main.home.sportInterface;

import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12 0012.
 */

public interface IAdapterHelper<I> {
    void onConvert(MyRecyclerViewHolder holder, int position, I item);
    int onSetAdapterItemLayout();
    List<ScrollLayout> getFollowers();
}
