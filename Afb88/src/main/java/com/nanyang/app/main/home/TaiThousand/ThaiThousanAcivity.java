package com.nanyang.app.main.home.TaiThousand;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.Games.GamesActivity;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/24.
 */

public class ThaiThousanAcivity extends BaseToolbarActivity {
    @Bind(R.id.thai_thousand_rc)
    RecyclerView rc;
    private List<MenuItemInfo> data;
    private String thai1D;
    private String thai2D;
    private String thai3D;

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
                if (item.getText().equals(thai1D)) {
                    b.putString("thai_thousand", "Game1");
                    skipAct(GamesActivity.class, b);
                } else if (item.getText().equals(thai2D)) {
                    b.putString("thai_thousand", "Game2");
                    skipAct(GamesActivity.class, b);
                } else if (item.getText().equals(thai3D)) {
                    b.putString("thai_thousand", "Game3");
                    skipAct(GamesActivity.class, b);
                }
            }
        });
        rc.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        thai1D = "泰式千字 1D游戏";
        thai2D = "泰式千字 2D游戏";
        thai3D = "泰式千字 3D游戏";
        data = new ArrayList<>();
        data.add(new MenuItemInfo(R.mipmap.thai_thousand_1d, thai1D));
        data.add(new MenuItemInfo(R.mipmap.thai_thousand_2d, thai2D));
        data.add(new MenuItemInfo(R.mipmap.thai_thousand_3d, thai3D));
        initRc();
    }
}
