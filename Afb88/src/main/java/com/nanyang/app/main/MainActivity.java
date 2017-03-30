package com.nanyang.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.center.PersonalCenterFragment;
import com.nanyang.app.main.center.Stake.StakeFragment;
import com.nanyang.app.main.center.Statement.StatementFragment;
import com.nanyang.app.main.home.HomeFragment;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseToolbarActivity<MainPresenter> implements MainContract.View {

    @Bind(R.id.fl_menu_home)
    FrameLayout flMenuHome;

    FrameLayout flCurrentMenu;

    BaseFragment homeFragment = new HomeFragment();
    BaseFragment centerFragment = new PersonalCenterFragment();
    BaseFragment statementFragment = new StatementFragment();
    BaseFragment stakeFragment = new StakeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        flCurrentMenu = flMenuHome;
        showFragmentToActivity(homeFragment, R.id.fl_main_content);
        createPresenter(new MainPresenter(this));
        String lag = AfbUtils.getLanguage(mContext);
        if (lag.equals("zh")) {
            switchLanguage("ZH-CN");
        } else if (lag.equals("en")) {
            switchLanguage("EN-US");
        }

    }

    @OnClick({R.id.fl_menu_home, R.id.fl_menu_center, R.id.fl_menu_statemente, R.id.fl_menu_stake})
    public void onClick(View view) {
        clickTabMenu((FrameLayout) view);
    }

    private void clickTabMenu(FrameLayout fl) {
        if (flCurrentMenu != fl) {
            TextView tvOld = (TextView) flCurrentMenu.getChildAt(0);
            tvOld.setTextColor(getResources().getColor(R.color.black_grey));
            switch (tvOld.getId()) {
                case R.id.tv_tab_home:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_menu_a, 0, 0);
                    hideFragmentToActivity(homeFragment);
                    break;
                case R.id.tv_tab_center:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_menu_user, 0, 0);
                    hideFragmentToActivity(centerFragment);
                    break;
                case R.id.tv_tab_statement:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_menu_order, 0, 0);
                    hideFragmentToActivity(statementFragment);
                    break;
                case R.id.tv_tab_stake:
                    tvOld.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_menu_more, 0, 0);
                    hideFragmentToActivity(stakeFragment);
                    break;
            }
            TextView tvMenu = (TextView) fl.getChildAt(0);
            tvMenu.setTextColor(getResources().getColor(R.color.green900));
            switch (tvMenu.getId()) {
                case R.id.tv_tab_home:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_menu_a_hover, 0, 0);
                    showFragmentToActivity(homeFragment, R.id.fl_main_content);
                    break;
                case R.id.tv_tab_center:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_menu_user_hover, 0, 0);
                    showFragmentToActivity(centerFragment, R.id.fl_main_content);
                    break;
                case R.id.tv_tab_statement:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_menu_order_hover, 0, 0);
                    showFragmentToActivity(statementFragment, R.id.fl_main_content);
                    break;
                case R.id.tv_tab_stake:
                    tvMenu.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_menu_more_hover, 0, 0);
                    showFragmentToActivity(stakeFragment, R.id.fl_main_content);
                    break;
            }
            flCurrentMenu = fl;
        }

    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onGetData(String data) {
        ToastUtils.showShort(data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            centerFragment.initHead();
        }
    }

    @Override
    public void onBackPressed() {
        if (isTwoFinish()) {
            finish();
        } else {
            ToastUtils.showShort(getString(R.string.double_click_exit_application));
        }
    }

    private boolean isFinish = true;

    private boolean isTwoFinish() {
        Timer timer = new Timer();
        if (isFinish) {
            isFinish = false;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isFinish = true;
                }
            }, 2000);
            return false;
        } else {
            return true;
        }
    }

    private void switchLanguage(String lang) {
        presenter.switchLanguage(lang);
    }
}
