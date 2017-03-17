package com.nanyang.app.main.home.sport.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.nanyang.app.main.home.sportInterface.BaseSportFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SportActivity extends BaseToolbarActivity {
    BaseSportFragment soccerFragment = new SoccerFragment();
  /*  BaseSportFragment basketballFragment = new BasketballFragment();
    BaseSportFragment tennisFragment = new TennisFragment();
    BaseSportFragment financialFragment = new FinancialFragment();
    BaseSportFragment special4dFragment = new Game4dFragment();
    BaseSportFragment thaiboxingFragment = new ThaiBoxingFragment();
    BaseSportFragment sportEFragment = new SportEFragment();*/

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

    private String currentTag;
    private HashMap<String, BaseSportFragment> mapFragment;
    private BaseSportFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
        assert tvToolbarRight != null;
        tvToolbarRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.sport_list_layer, 0);
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment.toolbarRightClick(v);
            }
        });
        tvToolbarTitle.setBackgroundResource(0);
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
        MenuItemInfo item= (MenuItemInfo) getIntent().getSerializableExtra(AppConstant.KEY_DATA);
        if(item!=null) {
            type = item.getType();
            assert tvToolbarTitle != null;
            tvToolbarTitle.setText(item.getText());
        }
        showFragmentToActivity(soccerFragment, R.id.fl_content, getString(R.string.Soccer));
        currentFragment = soccerFragment;
        currentTag = getString(R.string.Soccer);
        tvTitle.setText(currentTag);
        mapFragment = new HashMap<>();
        mapFragment.put(getString(R.string.Soccer), soccerFragment);
     /*   mapFragment.put(getString(R.string.Basketball), basketballFragment);
        mapFragment.put(getString(R.string.Tennis), tennisFragment);
        mapFragment.put(getString(R.string.Muay_Thai), thaiboxingFragment);
        mapFragment.put(getString(R.string.Financial), financialFragment);
        mapFragment.put(getString(R.string.E_Sport), sportEFragment);
        mapFragment.put(getString(R.string.Specials_4D), special4dFragment);*/

        getApp().setBetParList(null);

    }

    private void selectFragmentTag(String tag) {
        if (!currentTag.equals(tag)) {
            tvTitle.setText(tag);
            hideFragmentToActivity(mapFragment.get(currentTag));
            showFragmentToActivity(mapFragment.get(tag), R.id.fl_content, tag);
            currentTag = tag;
            currentFragment = mapFragment.get(currentTag);
        }
    }

    @OnClick({R.id.tv_refresh, R.id.tv_collection, R.id.tv_menu, R.id.tv_mix, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_refresh:
                currentFragment.refresh();
                break;
            case R.id.tv_collection:
                currentFragment.collection(tvCollection);
                break;
            case R.id.tv_menu:
                currentFragment.menu(tvMenu);
                break;
            case R.id.tv_mix:
                if (currentFragment.mix(tvMix))
                    tvCollection.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_star_black, 0, 0);
                break;
            case R.id.iv_add:
                createPopupWindow(new BasePopupWindow(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, 380) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_game;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                        rv.setLayoutManager(new GridLayoutManager(mContext, 3));
                        List<MenuItemInfo> list = new ArrayList<>();
                        list.add(new MenuItemInfo(0, getString(R.string.Soccer)));
                        list.add(new MenuItemInfo(0, getString(R.string.Basketball)));
                        list.add(new MenuItemInfo(0, getString(R.string.Tennis)));
                        list.add(new MenuItemInfo(0, getString(R.string.Financial)));
                        list.add(new MenuItemInfo(0, getString(R.string.Specials_4D)));
                        list.add(new MenuItemInfo(0, getString(R.string.Muay_Thai)));
                        list.add(new MenuItemInfo(0, getString(R.string.E_Sport)));
                        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, list, R.layout.text_base) {
                            @Override
                            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                                TextView tv = holder.getView(R.id.item_text_tv);
                                tv.setBackgroundResource(R.color.google_green);
                                tv.setText(item.getText());
                                tv.setTextColor(getResources().getColor(R.color.white));
                            }


                        };

                        rv.setAdapter(baseRecyclerAdapter);
                        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                            @Override
                            public void onItemClick(View view, MenuItemInfo item, int position) {
                                selectFragmentTag(item.getText());
                                closePopupWindow();
                            }
                        });
                    }
                });
                popWindow.setTrans(1f);
                popWindow.showPopupDownWindow();
                break;

        }
    }


}
