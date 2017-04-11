package com.nanyang.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.main.center.PersonalCenterFragment;
import com.nanyang.app.main.center.Statement.StatementFragment;
import com.nanyang.app.main.home.HomeFragment;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseToolbarActivity<MainPresenter> implements MainContract.View {

    @Bind(R.id.fl_menu_home)
    FrameLayout flMenuHome;
    @Bind(R.id.fl_menu_login_out)
    FrameLayout flLoginOut;

    FrameLayout flCurrentMenu;

    BaseFragment homeFragment = new HomeFragment();
    BaseFragment centerFragment = new PersonalCenterFragment();
    BaseFragment statementFragment = new StatementFragment();

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
        AfbUtils.switchLanguage(lag, mContext);
        toolbar.setNavigationIcon(R.mipmap.home_menu_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.oddsType();

    }

    @OnClick({R.id.fl_menu_home, R.id.fl_menu_center, R.id.fl_menu_statemente, R.id.fl_menu_login_out})
    public void onClick(View view) {
        if (view.getId() == R.id.fl_menu_login_out) {
            BaseYseNoChoosePopupWindow pop = new BaseYseNoChoosePopupWindow(mContext, new View(mContext)) {
                @Override
                protected void clickSure(View v) {
                    Intent intent = new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            };
            pop.getChooseTitleTv().setText(getString(R.string.confirm_or_not));
            pop.getChooseMessage().setText(getString(R.string.login_out));
            pop.getChooseSureTv().setText(getString(R.string.sure));
            pop.getChooseCancelTv().setText(getString(R.string.cancel));
            onPopupWindowCreated(pop, Gravity.CENTER);
        } else {
            clickTabMenu((FrameLayout) view);
        }
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
            }
            TextView tvMenu = (TextView) fl.getChildAt(0);
            tvMenu.setTextColor(getResources().getColor(R.color.green_black_word));
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
