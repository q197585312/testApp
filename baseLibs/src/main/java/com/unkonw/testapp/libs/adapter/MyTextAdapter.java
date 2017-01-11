package com.unkonw.testapp.libs.adapter;

import android.content.Context;

import com.unkonw.testapp.R;

import java.util.List;

/**
 * Created by anzhuo002 on 2016/7/5.
 */

public class MyTextAdapter extends BaseRecylerAdapter<String>{
    public MyTextAdapter(Context context, List<String> mDatas) {
        super(context, mDatas, android.R.layout.browser_link_context_header);
    }

    /**
     * 这个方法 设置数据到item 中
     * @param holder
     * @param position
     */
    @Override
    public void convert(MyRecylerViewHolder holder, int position) {
        holder.setText(R.id.title, "youxin***" + position);
    }
}
