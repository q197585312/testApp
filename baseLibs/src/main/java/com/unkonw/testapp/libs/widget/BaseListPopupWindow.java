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

public abstract class BaseListPopupWindow<T> extends BasePopupWindow {
    BaseRecyclerAdapter<T> adapter;
    RecyclerView recyclerView;

    private TextView tv;
    public TextView tv1;
    private List<T> data;

    public BaseListPopupWindow(Context context, View v, int width, int height, TextView tv) {
        super(context, v, width, height);
        this.tv = tv;

    }

    public BaseListPopupWindow(Context context, View v, int width, int height, TextView tv, TextView tv1) {
        super(context, v, width, height);
        this.tv = tv;
        this.tv1 = tv1;

    }

    public BaseListPopupWindow(Context context, View v, int wrapContent, int content) {
        super(context, v);

    }

    public int getRecyclerViewId() {
        return R.id.base_rv;
    }

    public void setData(List<T> data) {
        this.data = data;
        adapter.addAllAndClear(data);
    }

    public int getItemLayoutRes() {
        return R.layout.register_base_test;

    }

    public void onConvert(MyRecyclerViewHolder holder, int position, T item) {
        TextView tv = holder.getView(R.id.item_regist_text_tv);
        convertTv(tv, item);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        recyclerView = (RecyclerView) view.findViewById(getRecyclerViewId());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new BaseRecyclerAdapter<T>(context, new ArrayList<T>(), getItemLayoutRes()) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                onConvert(holder, position, item);
            }
        };

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<T>() {
            @Override
            public void onItemClick(View view, T item, int position) {
                if (tv1 != null) {
                    tv1.setText("OK");
                    tv1.setVisibility(View.VISIBLE);
                }else{
                    clickItem(tv, item);
                }
                closePopupWindow();

            }
        });
        recyclerView.setAdapter(adapter);
    }

    protected void clickItem(TextView tv, T item) {
    }

    protected void convertTv(TextView tv, T item) {
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.layout_base_recycler_view;
    }
}
