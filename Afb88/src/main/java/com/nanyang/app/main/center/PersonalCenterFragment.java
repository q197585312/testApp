package com.nanyang.app.main.center;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.MenuItemInfo;
import com.unkonw.testapp.libs.adapter.BaseRecylerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecylerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class PersonalCenterFragment extends BaseFragment {


    @Bind(R.id.iv_banner)
    ImageView ivBanner;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;

    @Override
    public int onSetLayoutId() {
        return R.layout.f;
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext,  LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(mLayoutManager);
        List<MenuItemInfo> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo(R.mipmap.home_sports, getString(R.string.Sportbook)));
        dataList.add(new MenuItemInfo(R.mipmap.home_live, getString(R.string.Live_Casino)));
        dataList.add(new MenuItemInfo(R.mipmap.home_games, getString(R.string.Games)));
        dataList.add(new MenuItemInfo(R.mipmap.home_keno, getString(R.string.Keno)));
        dataList.add(new MenuItemInfo(R.mipmap.home_poker, getString(R.string.Poker)));
        dataList.add(new MenuItemInfo(R.mipmap.home_lottery, getString(R.string.Lottery)));
        dataList.add(new MenuItemInfo(R.mipmap.home_roulette, getString(R.string.Roulette)));
        dataList.add(new MenuItemInfo(R.mipmap.home_casino, getString(R.string.Casino)));
        dataList.add(new MenuItemInfo(R.mipmap.home_discount, getString(R.string.Discount)));
        BaseRecylerAdapter adapter = new BaseRecylerAdapter<MenuItemInfo>(mContext, dataList, R.layout.item_image_text) {
            @Override
            public void convert(MyRecylerViewHolder holder, int position, MenuItemInfo item) {
                ImageView iv = holder.getView(R.id.iv_pic);
                TextView tv = holder.getView(R.id.tv_text);
                iv.setImageResource(item.getRes());
                tv.setText(item.getText());
            }
        };
        rvContent.setAdapter(adapter);
    }


}
