package com.nanyang.app.main.home.sport;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;

import java.util.HashMap;

import butterknife.Bind;


public class SportActivity extends BaseToolbarActivity<Presenter> {
    BaseSportFragment footballFragment = new FootballFragment();
    BaseSportFragment basketballFragment = new BasketballFragment();
    BaseSportFragment volleyballFragment = new VolleyballFragment();

    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.tv_refresh)
    TextView tvRefresh;
    @Bind(R.id.tv_collection)
    TextView tvCollection;
    @Bind(R.id.tv_menu)
    TextView tvMenu;
    @Bind(R.id.cb_mix)
    CheckBox cbMix;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_sport_menu_bottom)
    LinearLayout llSportMenuBottom;


    private String currentTag;
    private HashMap<String, BaseSportFragment> mapFragmnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
    }

    @Override
    public void initData() {
        super.initData();
        showFragmentToActivity(footballFragment, R.id.fl_content,getString(R.string.Football));
        currentTag =getString(R.string.Football);
        mapFragmnet = new HashMap<>();
        mapFragmnet.put(getString(R.string.Football), footballFragment);
        mapFragmnet.put(getString(R.string.Basketball), basketballFragment);
        mapFragmnet.put(getString(R.string.Volleyball), volleyballFragment);
    }

    private void selectFragmentTag(String tag) {
        if (!currentTag.equals(tag)) {
            tvTitle.setText(tag);
            hideFragmentToActivity(mapFragmnet.get(currentTag));
            showFragmentToActivity(mapFragmnet.get(tag), R.id.fl_content,tag);
            currentTag = tag;
        }
    }

}
