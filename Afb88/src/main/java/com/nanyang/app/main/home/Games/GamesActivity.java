package com.nanyang.app.main.home.Games;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.Games.thaiThousand.ThaiThousandFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.OnClick;

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
        tvToolbarTitle.setText(getString(R.string.Thai_game1));
        currentTag = "Thai";
        BaseGamesFragment thaiThousandFragment = new ThaiThousandFragment();
        showFragmentToActivity(thaiThousandFragment, R.id.fl_content, getString(R.string.Football));
        currentFragment = thaiThousandFragment;
        mapFragment = new HashMap<>();
        mapFragment.put("Thai", thaiThousandFragment);
    }

    private void selectFragmentTag(String tag) {
        if (!currentTag.equals(tag)) {
//            tvTitle.setText(tag);
            hideFragmentToActivity(mapFragment.get(currentTag));
            showFragmentToActivity(mapFragment.get(tag), R.id.fl_content, tag);
            currentTag = tag;
            currentFragment = mapFragment.get(currentTag);
        }
    }

    @OnClick({R.id.iv_add})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                createPopupWindow(new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, 400) {
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
                        list.add(new MenuItemInfo(0, "Thai"));
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
