package com.nanyang.app.main.home.Games;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.Games.thaiThousand.ThaiThousandFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/27.
 */

public class GamesActivity extends BaseToolbarActivity {

    private String currentTag;
    private HashMap<String, BaseGamesFragment> mapFragment;
    private BaseGamesFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        tvToolbarRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.sport_list_layer, 0);
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment.toolbarRightClick(v);
            }
        });
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void initData() {
        super.initData();
        setTitle(getString(R.string.Thai_game1));
        currentTag = "Thai";
        BaseGamesFragment thaiThousandFragment = new ThaiThousandFragment();
        showFragmentToActivity(thaiThousandFragment, R.id.fl_content, getString(R.string.Soccer));
        currentFragment = thaiThousandFragment;
        mapFragment = new HashMap<>();
        mapFragment.put("Thai", thaiThousandFragment);
    }
    public void setTitle(String title) {
        tvToolbarTitle.setText(title);
    }
}
