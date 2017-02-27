package com.nanyang.app.main.home.sport;

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
import com.nanyang.app.main.home.sport.basketball.BasketballFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        assert tvToolbarTitle != null;
        tvToolbarTitle.setText(ballType);
        currentTag = getString(R.string.Football);
        mapFragment = new HashMap<>();
        mapFragment.put(getString(R.string.Football), footballFragment);
        mapFragment.put(getString(R.string.Basketball), basketballFragment);
        mapFragment.put(getString(R.string.Volleyball), volleyballFragment);


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
                ((SportPresenter) (currentFragment.presenter)).refresh("");
                break;
            case R.id.tv_collection:
                currentFragment.collectionClick(tvCollection);
                break;
            case R.id.tv_menu:
                break;
            case R.id.tv_mix:

                if (currentFragment.mixParlayCLick(tvMix))
                    tvCollection.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_star_black, 0, 0);
                break;
            case R.id.iv_add:
                createPopupWindow(new BasePopupWindow(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, 400) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                        rv.setLayoutManager(new GridLayoutManager(mContext, 3));
                        List<MenuItemInfo> list = new ArrayList<>();
                        list.add(new MenuItemInfo(0, getString(R.string.Football)));
                        list.add(new MenuItemInfo(0, getString(R.string.Basketball)));
                        list.add(new MenuItemInfo(0, getString(R.string.Volleyball)));
                        list.add(new MenuItemInfo(0, getString(R.string.Thai_word)));
                        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, list, R.layout.text_base) {
                            @Override
                            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                                TextView tv = holder.getView(R.id.item_text_tv);
                                tv.setText(item.getText());
                                tv.setBackgroundResource(R.color.white);
                                tv.setTextColor(getResources().getColor(R.color.black_grey));
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
                popWindow.showPopupDownWindow();
                break;
        }
    }


}
