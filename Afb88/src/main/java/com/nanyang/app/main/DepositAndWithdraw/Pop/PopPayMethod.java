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
import com.nanyang.app.main.DepositAndWithdraw.Bean.PayMethodBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;


public abstract class PopPayMethod extends BasePopupWindow {

    RecyclerView rcContent;
    BaseRecyclerAdapter<PayMethodBean> adapter;
    private String currentPayId;

    public PopPayMethod(Context context, View v) {
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
        adapter = new BaseRecyclerAdapter<PayMethodBean>(context, new ArrayList<PayMethodBean>(), R.layout.item_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, PayMethodBean item) {
                TextView tvContent = holder.getTextView(R.id.tv_content);
                tvContent.setText(item.getPayMethod());
                if (currentPayId.equals(item.getPayId())) {
                    tvContent.setTextColor(Color.WHITE);
                    tvContent.setBackgroundColor(Color.parseColor("#767676"));
                } else {
                    tvContent.setTextColor(Color.BLACK);
                    tvContent.setBackgroundColor(Color.WHITE);
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<PayMethodBean>() {
            @Override
            public void onItemClick(View view, PayMethodBean item, int position) {
                setPayId(item);
                closePopupWindow();
            }
        });
        rcContent.setAdapter(adapter);
    }

    public void setData(List<PayMethodBean> list, String currentPayId) {
        adapter.setData(list);
        this.currentPayId = currentPayId;
    }

    public abstract void setPayId(PayMethodBean bean);
}
