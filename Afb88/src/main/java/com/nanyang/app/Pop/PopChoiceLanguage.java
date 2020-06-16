package com.nanyang.app.Pop;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/4/3.
 */

public abstract class PopChoiceLanguage extends BasePopupWindow {
    public PopChoiceLanguage(Context context, View v, int width, int height) {
        super(context, v, width, height);
        initData();
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.layout_base_recycler_view;
    }

    @Bind(R.id.base_rv)
    RecyclerView recyclerView;

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
    }

    BaseRecyclerAdapter<MenuItemInfo> adapter;

    private void initData() {
        adapter = new BaseRecyclerAdapter<MenuItemInfo>(context, new ArrayList<MenuItemInfo>(), R.layout.register_base_test) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                onConvert(holder, position, item);
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                onClickItem(item, position);
                closePopupWindow();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    public void setData(List<MenuItemInfo> data) {
        adapter.addAllAndClear(data);
    }

    public abstract void onConvert(MyRecyclerViewHolder holder, int position, MenuItemInfo item);

    public abstract void onClickItem(MenuItemInfo item, int position);
}
