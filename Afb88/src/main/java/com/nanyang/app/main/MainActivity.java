package com.nanyang.app.main;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.HomeFragment;
import com.unkonw.testapp.libs.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/8.
 */

public class MainActivity extends BaseToolbarActivity {

    @Bind(R.id.fl_menu_home)
    FrameLayout flMenuHome;

    FrameLayout flCurrentMenu;

    BaseFragment homeFragment=new HomeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        flCurrentMenu=flMenuHome;
        addFragmentToActivity(homeFragment,R.id.fl_main_content);

    }

    @OnClick({R.id.fl_menu_home, R.id.fl_menu_center, R.id.fl_menu_order, R.id.fl_menu_more})
    public void onClick(View view) {
        clickTabMenu((FrameLayout)view );

    }

    private void clickTabMenu(FrameLayout fl) {
        if(flCurrentMenu!=fl){
            TextView tvOld = (TextView) flCurrentMenu.getChildAt(0);
            tvOld.setTextColor(getResources().getColor(R.color.black_grey));
            switch (tvOld.getId()){
                case R.id.tv_tab_home:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_a,0,0);
                    break;
                case R.id.tv_tab_center:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_user,0,0);
                    break;
                case R.id.tv_tab_order:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_order,0,0);
                    break;
                case R.id.tv_tab_more:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_more,0,0);
                    break;
            }
            TextView tvMenu= (TextView) fl.getChildAt(0);
            tvMenu.setTextColor(getResources().getColor(R.color.green900));
            switch (tvMenu.getId()){
                case R.id.tv_tab_home:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_a_hover,0,0);
                    break;
                case R.id.tv_tab_center:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_user_hover,0,0);
                    break;
                case R.id.tv_tab_order:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_order_hover,0,0);
                    break;
                case R.id.tv_tab_more:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_more_hover,0,0);
                    break;
            }
            flCurrentMenu=fl;
        }

    }
}
