package com.unkonw.testapp.libs.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unkonw.testapp.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 */

public abstract class HomePopupWindow<T>  extends BasePopupWindow{

    TextView textView;
    BaseRecyclerAdapter<T> adapter;
    RecyclerView recyclerView;
    private List<T> data;
    public HomePopupWindow(Context context, View v, int width, int height, TextView view) {
        super(context, v, width, height);
        this.textView = view;
    }

    @Override
    protected int onSetLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        recyclerView = (RecyclerView) view.findViewById(getRecyclerViewId());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new BaseRecyclerAdapter<T>(context, new ArrayList<T>(), getItemLayoutId()) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                onConvert(holder,position,item);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public abstract int getRecyclerViewId();

    public int getItemLayoutId(){
        return R.id.item_home_text_tv;

    }

    public void onConvert(MyRecyclerViewHolder holder, int position, T item) {
        RecyclerView tv = holder.getView(R.id.item_home_text_tv);
        convertTv(tv,item);
    }

    protected  void convertTv(RecyclerView tv, T item){}

    public void setData( List<T> data){
        this.data=data;
        adapter.addAllAndClear(data);
    }
}
