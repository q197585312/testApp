package com.nanyang.app.main;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.center.PersonalCenterFragment;
import com.nanyang.app.main.home.HomeFragment;
import com.nanyang.app.main.more.DiscountFragment;
import com.nanyang.app.main.order.OrderFragment;
import com.unkonw.testapp.libs.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseToolbarActivity {

    @Bind(R.id.fl_menu_home)
    FrameLayout flMenuHome;

    FrameLayout flCurrentMenu;

    BaseFragment homeFragment=new HomeFragment();
    BaseFragment centerFragment=new PersonalCenterFragment();
    BaseFragment orderFragment=new OrderFragment();
    BaseFragment discountFragment=new DiscountFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        flCurrentMenu=flMenuHome;
        showFragmentToActivity(homeFragment,R.id.fl_main_content);

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
                    hideFragmentToActivity(homeFragment);
                    break;
                case R.id.tv_tab_center:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_user,0,0);
                    hideFragmentToActivity(centerFragment);
                    break;
                case R.id.tv_tab_order:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_order,0,0);
                    hideFragmentToActivity(orderFragment);
                    break;
                case R.id.tv_tab_more:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_more,0,0);
                    hideFragmentToActivity(discountFragment);
                    break;
            }
            TextView tvMenu= (TextView) fl.getChildAt(0);
            tvMenu.setTextColor(getResources().getColor(R.color.green900));
            switch (tvMenu.getId()){
                case R.id.tv_tab_home:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_a_hover,0,0);
                    showFragmentToActivity(homeFragment,R.id.fl_main_content);
                    break;
                case R.id.tv_tab_center:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_user_hover,0,0);
                    showFragmentToActivity(centerFragment,R.id.fl_main_content);
                    break;
                case R.id.tv_tab_order:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_order_hover,0,0);
                    showFragmentToActivity(orderFragment,R.id.fl_main_content);
                    break;
                case R.id.tv_tab_more:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.main_menu_more_hover,0,0);
                    showFragmentToActivity(discountFragment,R.id.fl_main_content);
                    break;
            }
            flCurrentMenu=fl;
        }

    }
}
