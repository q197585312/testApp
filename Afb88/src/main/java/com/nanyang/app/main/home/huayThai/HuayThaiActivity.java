package com.nanyang.app.main.home.huayThai;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/24.
 */

public class HuayThaiActivity extends BaseToolbarActivity {
    @Bind(R.id.thai_thousand_rc)
    RecyclerView rc;
    private List<MenuItemInfo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thai_thousand);
    }

    @Override
    public void initView() {
        super.initView();
    }

    private void initRc() {
        rc.setLayoutManager(new LinearLayoutManager(mContext));
        BaseRecyclerAdapter<MenuItemInfo> adapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, data, R.layout.item_thai_thousand) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                ImageView iv = holder.getView(R.id.img_thai_thousand);
                TextView nameTv = holder.getView(R.id.tv_thai_thousand_title);
                iv.setImageResource(item.getRes());
                nameTv.setText(item.getText());
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                Bundle b = new Bundle();
                b.putSerializable("thai_thousand", item);
                skipAct(HuayThaiGamesActivity.class, b);

            }
        });
        rc.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        data = new ArrayList<>();
        data.add(new MenuItemInfo(R.mipmap.thai_thousand_1d, getString(R.string.game1d), "_view/nodds1TH_App.aspx"));
        data.add(new MenuItemInfo(R.mipmap.thai_thousand_2d, getString(R.string.game2d), "_view/nodds2TH_App.aspx"));
        data.add(new MenuItemInfo(R.mipmap.thai_thousand_3d, getString(R.string.game3d), "_view/nodds3TH_App.aspx"));
        initRc();
    }
}
