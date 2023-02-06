package nanyang.com.dig88.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Fragment.Hjlh6688OnlineWithdrawFragment;
import nanyang.com.dig88.Home.Bean.LoginStatusBean;
import nanyang.com.dig88.Home.MenuDepositFragment;
import nanyang.com.dig88.Home.MenuWithdrawFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2019/3/22.
 */

public class ActivityFragmentShow extends GameBaseActivity {
    private BaseFragment currentFragment;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fragment_show;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        toolbar.setBackgroundResource(0);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment fragment = (BaseFragment) createInstance(type);
        currentFragment = fragment;
        int contentType = intent.getIntExtra("contentType", -1);
        fragment.setContentType(contentType);
        if (type.equals(MenuDepositFragment.class.getName()) || type.equals(MenuWithdrawFragment.class.getName()) || type.equals(Hjlh6688OnlineWithdrawFragment.class.getName())) {
            toolbar.setVisibility(View.GONE);
            fragment.isNeedShowBack = true;
        }
        if (fragment != null) {
            AppTool.setFragment(fragmentManager, fragment, R.id.fl_content, false);
        }
    }

    public Object createInstance(String className) {
        try {
            Class clz = Class.forName(className);
            Object obj = clz.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().postSticky(new LoginStatusBean("7"));
    }

    @Override
    protected void leftClick() {
        currentFragment.back();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            currentFragment.back();
            return true;
        }
        return false;
    }
}
