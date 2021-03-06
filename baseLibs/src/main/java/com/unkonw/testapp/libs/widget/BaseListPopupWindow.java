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
    private TextView tv1;
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

    public BaseListPopupWindow(Context context, View v) {
        super(context, v);

    }

    public abstract int getRecyclerViewId();

    public void setData( List<T> data){
        this.data=data;
        adapter.addAllAndClear(data);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        recyclerView = (RecyclerView) view.findViewById(getRecyclerViewId());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new BaseRecyclerAdapter<T>(context, new ArrayList<T>(), R.layout.register_base_test) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                TextView tv = holder.getView(R.id.item_regist_text_tv);
                convertTv(tv,item);
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<T>() {
            @Override
            public void onItemClick(View view, T item, int position) {
                if (tv != null) {
                    convertTv(tv,item);
                }
                if (tv1 != null) {
                    tv1.setText("OK");
                    tv1.setVisibility(View.VISIBLE);
                }
                closePopupWindow();

            }
        });
        recyclerView.setAdapter(adapter);
    }

    protected abstract void convertTv(TextView tv, T item);

    @Override
    protected int onSetLayoutRes() {
        return R.layout.layout_base_recycler_view;
    }
}
