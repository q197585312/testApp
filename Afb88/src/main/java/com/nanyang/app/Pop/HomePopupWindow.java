package com.nanyang.app.Pop;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 */

public abstract class HomePopupWindow<T> extends BasePopupWindow {

    BaseRecyclerAdapter<T> adapter;
    RecyclerView recyclerView;

    public HomePopupWindow(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.pop_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        recyclerView = view.findViewById(R.id.item_home_text_tv);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new BaseRecyclerAdapter<T>(context, getCurrentData(), R.layout.home_pop_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                initItem(holder, position, item);
            }
        };
        recyclerView.setAdapter(adapter);
    }


    public abstract void initItem(MyRecyclerViewHolder holder, int position, T item);

    public abstract List<T> getCurrentData();

}
