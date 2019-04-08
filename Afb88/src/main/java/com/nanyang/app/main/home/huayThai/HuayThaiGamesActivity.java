package com.nanyang.app.main.home.huayThai;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.BaseSwitchFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class HuayThaiGamesActivity extends BaseToolbarActivity {


    private HashMap<String, BaseFragment<HuayThaiPresenter>> mapFragment;

    protected MenuItemInfo<String> info;

    private List<String> typeList;
    private BasePopupWindow popupWindow;
    private BaseFragment<HuayThaiPresenter> thaiThousandFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        tvToolbarRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.sport_list_layer, 0);
    }

    @Override
    protected void updateBalanceTv(String allData) {

    }

    @Override
    public int getDrawerLayoutId() {
        return 0;
    }

    @Override
    public BaseSwitchFragment getFirstShowFragment() {
        return null;
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
        thaiThousandFragment = new HuayThaiFragment();
        mapFragment = new HashMap<>();
        mapFragment.put("Thai", thaiThousandFragment);
        showFragmentToActivity(thaiThousandFragment, R.id.game_fl, "Thai");
        initBetTypeList();
    }

    private void initBetTypeList() {
        typeList = new ArrayList<>();
        typeList.add(getString(R.string.game1d));
        typeList.add(getString(R.string.game2d));
        typeList.add(getString(R.string.game3d));
        popupWindow = new BasePopupWindow(mContext, tvToolbarRight, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice_ball_type;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(mContext));
                BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(mContext, typeList, R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, String item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setPadding(0, 0, 0, 0);
                        tv.setText(item);
                        tv.setBackgroundResource(R.color.black_grey);
                    }

                };
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
                    @Override
                    public void onItemClick(View view, String item, int position) {
                        setTitle(item);
                        thaiThousandFragment.refreshData(item);
                        popupWindow.closePopupWindow();
                    }
                });
                rv_list.setAdapter(adapter);
            }
        };
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.closePopupWindow();
                popupWindow.setTrans(1f);
                popupWindow.showPopupDownWindow();
            }
        });
    }

    public void setTitle(String title) {
        tvToolbarTitle.setBackgroundResource(0);
        tvToolbarTitle.setText(title);
    }
}
