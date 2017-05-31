package com.nanyang.app.main.home.huayThai;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/27.
 */

public class HuayThaiGamesActivity extends BaseToolbarActivity {


    private HashMap<String, BaseFragment<HuayThaiPresenter>> mapFragment;

    protected MenuItemInfo<String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        tvToolbarRight.setVisibility(View.GONE);
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void initData() {
        super.initData();
        info = (MenuItemInfo<String>) getIntent().getSerializableExtra("thai_thousand");
        String thaiType = info.getText();
        setTitle(thaiType);
        BaseFragment<HuayThaiPresenter> thaiThousandFragment = new HuayThaiFragment();
        mapFragment = new HashMap<>();
        mapFragment.put("Thai", thaiThousandFragment);
        showFragmentToActivity(thaiThousandFragment, R.id.game_fl, "Thai");
    }

    public void setTitle(String title) {
        tvToolbarTitle.setBackgroundResource(0);
        tvToolbarTitle.setText(title);
    }
}
