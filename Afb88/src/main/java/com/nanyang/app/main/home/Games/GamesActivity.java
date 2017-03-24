package com.nanyang.app.main.home.Games;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.Games.thaiThousand.ThaiThousandFragment;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/27.
 */

public class GamesActivity extends BaseToolbarActivity {

    private String currentTag;
    private HashMap<String, BaseFragment> mapFragment;
    private BaseFragment currentFragment;
    public String thaiType;

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
        thaiType = getMsgIntent("thai_thousand");
        setTitle(thaiType);
        currentTag = "Thai";
        BaseFragment thaiThousandFragment = new ThaiThousandFragment();
        currentFragment = thaiThousandFragment;
        mapFragment = new HashMap<>();
        mapFragment.put("Thai", thaiThousandFragment);
        showFragmentToActivity(thaiThousandFragment, R.id.game_fl, "Thai");
    }

    public void setTitle(String title) {
        tvToolbarTitle.setText(title);
    }
}
