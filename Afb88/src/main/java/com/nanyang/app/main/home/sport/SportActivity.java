package com.nanyang.app.main.home.sport;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    @Bind(R.id.tv_mix)
    TextView tvMix;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_sport_menu_bottom)
    LinearLayout llSportMenuBottom;
    boolean isMix = false;

    private String currentTag;
    private HashMap<String, BaseSportFragment> mapFragmnet;
    private BaseSportFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
        tvToolbarRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.sport_list_layer, 0);
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment.toolbarRightClick(v);
            }
        });

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type = "";

    @Override
    public void initData() {
        super.initData();
        type = getIntent().getStringExtra(AppConstant.KEY_STRING);
        showFragmentToActivity(footballFragment, R.id.fl_content, getString(R.string.Football));
        currentFragment = footballFragment;
        String ballType = getIntent().getStringExtra(AppConstant.KEY_STRING);
        tvToolbarTitle.setText(ballType);
        currentTag = getString(R.string.Football);
        mapFragmnet = new HashMap<>();
        mapFragmnet.put(getString(R.string.Football), footballFragment);
        mapFragmnet.put(getString(R.string.Basketball), basketballFragment);
        mapFragmnet.put(getString(R.string.Volleyball), volleyballFragment);


    }

    private void selectFragmentTag(String tag) {
        if (!currentTag.equals(tag)) {
            tvTitle.setText(tag);
            hideFragmentToActivity(mapFragmnet.get(currentTag));
            showFragmentToActivity(mapFragmnet.get(tag), R.id.fl_content, tag);
            currentTag = tag;
            currentFragment = mapFragmnet.get(currentTag);
        }
    }

    @OnClick({R.id.tv_refresh, R.id.tv_collection, R.id.tv_menu, R.id.tv_mix})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_refresh:
                ((SportPresenter) (currentFragment.presenter)).refresh("");
                break;
            case R.id.tv_collection:
                break;
            case R.id.tv_menu:
                break;
            case R.id.tv_mix:
                tvCollection.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_star_black, 0, 0);
                currentFragment.mixParlayCLick(view);
                isMix = !isMix;
                if (isMix)
                    tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_oval_u_green, 0, 0);
                else
                    tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_oval_u_black, 0, 0);
                break;
        }
    }
}
