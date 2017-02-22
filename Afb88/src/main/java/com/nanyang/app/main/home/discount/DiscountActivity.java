package com.nanyang.app.main.home.discount;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.more.DiscountItemInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/2/16.
 */

public class DiscountActivity extends BaseToolbarActivity {
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_discount);
//        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        initUI();
    }
    public void initUI() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext,  LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(mLayoutManager);
        List<DiscountItemInfo> dataList = new ArrayList<>();
        dataList.add(new DiscountItemInfo(R.mipmap.discount_banner));
        dataList.add(new DiscountItemInfo(R.mipmap.discount_banner));
        dataList.add(new DiscountItemInfo(R.mipmap.discount_banner));

        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<DiscountItemInfo>(mContext, dataList, R.layout.discount_item_image) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, DiscountItemInfo item) {
                ImageView iv = holder.getView(R.id.iv_pic);
                iv.setImageResource(item.getRes());
            }
        };
        rvContent.setAdapter(adapter);
    }
}
