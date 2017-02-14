package com.nanyang.app.main.center;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.MenuItemInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class PersonalCenterFragment extends BaseFragment {


    @Bind(R.id.rv_content)
    RecyclerView rvContent;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext,  LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(mLayoutManager);
        List<MenuItemInfo> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo(R.mipmap.center_info_head, getString(R.string.Edit_information)));
        dataList.add(new MenuItemInfo(R.mipmap.center_info_recharge, getString(R.string.Recharge)));
        dataList.add(new MenuItemInfo(R.mipmap.center_info_withdrawals, getString(R.string.Withdrawals)));
        dataList.add(new MenuItemInfo(R.mipmap.center_info_order, getString(R.string.My_order)));
        dataList.add(new MenuItemInfo(R.mipmap.center_info_history, getString(R.string.History_record)));
        dataList.add(new MenuItemInfo(R.mipmap.center_info_modify, getString(R.string.Modify_password)));

        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, dataList, R.layout.center_item_text) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.tv_text);
                tv.setText(item.getText());
                tv.setCompoundDrawablesWithIntrinsicBounds(item.getRes(),0,R.mipmap.arrow_right_drop_white,0);
            }
        };
        rvContent.setAdapter(adapter);
    }


}
