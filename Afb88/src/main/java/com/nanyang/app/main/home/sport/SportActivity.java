package com.nanyang.app.main.home.sport;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private BaseSportFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        tvToolbarRight.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.sport_list_layer,0);
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              createPopupWindow(new BasePopupWindow(mContext,v, LinearLayout.LayoutParams.MATCH_PARENT,300) {
                  @Override
                  protected int onSetLayoutRes() {
                      return R.layout.popupwindow_choice_ball_type;
                  }

                  @Override
                  protected void initView(View view) {
                      super.initView(view);
                      RecyclerView rv_list=(RecyclerView)view.findViewById(R.id.rv_list);
                      setChooseTypeAdapter(rv_list);
                  }
              });
                popWindow.showPopupDownWindow();
            }
        });
    }

    private void setChooseTypeAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        List<MenuItemInfo> types=new ArrayList<>();
        types.add(new MenuItemInfo(0,getString(R.string.Today),"Toady"));
        types.add(new MenuItemInfo(0,getString(R.string.Running),"Running"));
        types.add(new MenuItemInfo(0,getString(R.string.Early),"Early"));
        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                if(currentFragment.presenter!=null)
                    ((SportPresenter)currentFragment.presenter).refresh(item.getType());
                popWindow.closePopupWindow();
            }
        });
        rv_list.setAdapter(baseRecyclerAdapter);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type="";

    @Override
    public void initData() {
        super.initData();
        type = getIntent().getStringExtra(AppConstant.KEY_STRING);
        showFragmentToActivity(footballFragment, R.id.fl_content, getString(R.string.Football));
        currentFragment=footballFragment;
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
            showFragmentToActivity(mapFragmnet.get(tag), R.id.fl_content,tag);
            currentTag = tag;
            currentFragment=mapFragmnet.get(currentTag);
        }
    }

}
