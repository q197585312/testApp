package com.nanyang.app.main.DepositAndWithdraw.Pop;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nanyang.app.R;
import com.nanyang.app.main.DepositAndWithdraw.Bean.AutoDepositBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;


public abstract class PopAutoBankName extends BasePopupWindow {

    RecyclerView rcContent;
    BaseRecyclerAdapter<AutoDepositBean> adapter;
    private String currentBank;

    public PopAutoBankName(Context context, View v) {
        super(context, v, v.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.pop_list;
    }

    @Override
    protected void initView(@NonNull View view) {
        super.initView(view);
        rcContent = view.findViewById(R.id.rc_content);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rcContent.setLayoutManager(manager);
        adapter = new BaseRecyclerAdapter<AutoDepositBean>(context, new ArrayList<AutoDepositBean>(), R.layout.item_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, AutoDepositBean item) {
                TextView tvContent = holder.getTextView(R.id.tv_content);
                tvContent.setText(item.getBankName());
                if (currentBank.equals(item.getBankName())) {
                    tvContent.setTextColor(Color.WHITE);
                    tvContent.setBackgroundColor(Color.parseColor("#767676"));
                } else {
                    tvContent.setTextColor(Color.BLACK);
                    tvContent.setBackgroundColor(Color.WHITE);
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<AutoDepositBean>() {
            @Override
            public void onItemClick(View view, AutoDepositBean item, int position) {
                setBankData(item);
                closePopupWindow();
            }
        });
        rcContent.setAdapter(adapter);
    }

    public void setData(List<AutoDepositBean> list, String currentBank) {
        adapter.setData(list);
        this.currentBank = currentBank;
    }

    public abstract void setBankData(AutoDepositBean bean);
}
