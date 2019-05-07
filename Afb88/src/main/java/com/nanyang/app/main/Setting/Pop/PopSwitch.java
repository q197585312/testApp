package com.nanyang.app.main.Setting.Pop;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/7.
 */

public abstract class PopSwitch extends BasePopupWindow {
    public PopSwitch(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_choice;
    }

    RecyclerView recyclerView;
    BaseRecyclerAdapter<String> adapter;

    @Override
    protected void initView(View view) {
        super.initView(view);
        recyclerView = view.findViewById(R.id.rv_list);
        adapter = new BaseRecyclerAdapter<String>(context, new ArrayList<String>(), R.layout.text_base_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setText(item);
                if (choiceStr.equals(item)) {
                    tv.setBackgroundColor(ContextCompat.getColor(context, R.color.gary1));
                    tv.setTextColor(ContextCompat.getColor(context, R.color.blue));
                } else {
                    tv.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    tv.setTextColor(ContextCompat.getColor(context, R.color.black));
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                onClickItem(item);
                closePopupWindow();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private String choiceStr;

    public void setData(List<String> dataList, String choiceStr) {
        this.choiceStr = choiceStr;
        adapter.setData(dataList);
    }

    public abstract void onClickItem(String item);
}
