package com.nanyang.app.main.BetCenter.pop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.BetCenter.Bean.DataInfoBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/4/6.
 */

public abstract class PopGradeSwitchType extends BasePopupWindow {
    public PopGradeSwitchType(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.pop_grade_switch_type;
    }

    @Bind(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<DataInfoBean> adapter;

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        adapter = new BaseRecyclerAdapter<DataInfoBean>(context, new ArrayList<DataInfoBean>(), R.layout.item_text) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, DataInfoBean item) {
                TextView tvContent = holder.getView(R.id.tv_content);
                tvContent.setText(item.getName());
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(context));
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<DataInfoBean>() {
            @Override
            public void onItemClick(View view, DataInfoBean item, int position) {
                onClickItem(item);
                closePopupWindow();
            }
        });
        rcContent.setAdapter(adapter);
    }

    public void setDataList(List<DataInfoBean> dataList) {
        adapter.setData(dataList);
    }

    public abstract void onClickItem(DataInfoBean item);
}
