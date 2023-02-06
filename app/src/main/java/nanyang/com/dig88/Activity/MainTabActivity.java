package nanyang.com.dig88.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Fragment.DepositCenterFragment;
import nanyang.com.dig88.Fragment.PromotionFragment;
import nanyang.com.dig88.Fragment.WithdrawCenterFragment;
import nanyang.com.dig88.Home.MenuContactFragment;
import nanyang.com.dig88.Home.MenuDepositFragment;
import nanyang.com.dig88.Home.MenuHomeFragment;
import nanyang.com.dig88.Home.MenuUserFragment;
import nanyang.com.dig88.Home.MenuWithdrawFragment;
import nanyang.com.dig88.Home.Presenter.MainHomePresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.Pop88gasiaContact;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2015/10/19.
 */
public class MainTabActivity extends BaseActivity<MainHomePresenter> {

    public BaseFragment depositCenterFragment;
    public BaseFragment withdrawCenterFragment;
    public BaseFragment depositFragment;
    public BaseFragment withdrawFragment;
    public BaseFragment homeFragment;
    public BaseFragment contactFragment;
    public BaseFragment userFragment;
    public BaseFragment promotionFragment;
    public BaseFragment lastFragment;
    public BaseFragment firstFragment;
    public BaseFragment secondFragment;
    public BaseFragment currentFragment;
    @Bind(R.id.rg_home)
    RadioGroup rg_home;
    @Bind(R.id.rg_home_my2bet)
    RadioGroup rg_home_my2bet;
    @Bind(R.id.img_home)
    ImageView img_home;
    @Bind(R.id.tv_msg_count)
    TextView tvMsgCount;
    @Bind(R.id.rb_deposit)
    RadioButton rbDeposit;
    @Bind(R.id.rb_withdraw)
    RadioButton rbWithdraw;
    @Bind(R.id.rb_contact)
    RadioButton rbContact;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.img_home_my2bet)
    ImageView imgHomeMy2bet;
    @Bind(R.id.tv_msg_count_my2bet)
    TextView tvMsgCountMy2bet;
    @Bind(R.id.rl_home_normal)
    RelativeLayout rlHomeNormal;
    @Bind(R.id.rl_home_my2bet)
    RelativeLayout rlhomemy2bet;
    Pop88gasiaContact pop88gasiaContact;
    int lastIndex = -1;
    private List<BaseFragment> fragmentList;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main_tab;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new MainHomePresenter(this));
        getApp().setIsShowJokerPop(true);
        rbDeposit.setText(getString(R.string.deposit));
        rbWithdraw.setText(getString(R.string.withdraw));
        rbContact.setText(getString(R.string.contact));
        rbUser.setText(getString(R.string.user));
        getApp().setRegisterFirstIn(false);
        if (!BuildConfig.FLAVOR.equals("khmergaming") && !BuildConfig.FLAVOR.equals("kbet3") && !BuildConfig.FLAVOR.equals("ttwin168") && !BuildConfig.FLAVOR.equals("club988")) {
            presenter.getNetIp();
            presenter.handleAffiliateId();
            presenter.handleNewVersion();
        }
        depositCenterFragment = new DepositCenterFragment();
        withdrawCenterFragment = new WithdrawCenterFragment();
        depositFragment = new MenuDepositFragment();
        withdrawFragment = new MenuWithdrawFragment();
        homeFragment = new MenuHomeFragment();
        contactFragment = new MenuContactFragment();
        userFragment = new MenuUserFragment();
        promotionFragment = new PromotionFragment();
        firstFragment = depositFragment;
        secondFragment = withdrawFragment;
        if (BuildConfig.FLAVOR.equals("q2bet") || BuildConfig.FLAVOR.equals("ttwin168") || BuildConfig.FLAVOR.equals("u2bet") ||
                BuildConfig.FLAVOR.equals("mcd88") || BuildConfig.FLAVOR.equals("club988") || BuildConfig.FLAVOR.equals("afbcash") ||
                BuildConfig.FLAVOR.equals("hjlh6688") || BuildConfig.FLAVOR.equals("win3888")) {
            firstFragment = depositCenterFragment;
        }
        if (BuildConfig.FLAVOR.equals("funplay26") || BuildConfig.FLAVOR.equals("mcd88") || BuildConfig.FLAVOR.equals("onegold77") ||
                BuildConfig.FLAVOR.equals("afbcash") || BuildConfig.FLAVOR.equals("hjlh6688") || BuildConfig.FLAVOR.equals("xslot88")) {
            secondFragment = withdrawCenterFragment;
        }
        lastFragment = userFragment;
        if (BuildConfig.FLAVOR.equals("my2bet")) {
            rlHomeNormal.setVisibility(View.GONE);
            rlhomemy2bet.setVisibility(View.VISIBLE);
            fragmentList = Arrays.asList(contactFragment, homeFragment, userFragment);
            setRGCheckedChangeListener();
            switchFragment(1);
            imgHomeMy2bet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastIndex == 1) {
                        return;
                    }
                    rg_home_my2bet.clearCheck();
                    switchFragment(1);
                    initRGCheckedChangeListener(rg_home_my2bet);
                }
            });
        } else {
            fragmentList = Arrays.asList(firstFragment, secondFragment, homeFragment, contactFragment, lastFragment);
            setRGCheckedChangeListener();
            rlHomeNormal.setVisibility(View.VISIBLE);
            rlhomemy2bet.setVisibility(View.GONE);
            img_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastIndex == 2) {
                        return;
                    }
                    if (BuildConfig.FLAVOR.equals("gasia88")) {
                        showPop();
                    }
                    rg_home.clearCheck();
                    switchFragment(2);
                    initRGCheckedChangeListener(rg_home);
                }
            });
            switchFragment(2);

        }
    }

    private void showPop() {
        rbContact.post(new Runnable() {
            @Override
            public void run() {
                if (pop88gasiaContact == null) {
                    pop88gasiaContact = new Pop88gasiaContact(mContext, rbContact, screenWidth / 8 * 7, ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                pop88gasiaContact.showPopupCenterWindow();
            }
        });
    }

    public void setMainLogo(Drawable logo1, Drawable logo2, Drawable logo3) {
        logo1.setBounds(0, 0, logo1.getMinimumWidth(), logo1.getMinimumHeight());
        logo2.setBounds(0, 0, logo2.getMinimumWidth(), logo2.getMinimumHeight());
        logo3.setBounds(0, 0, logo2.getMinimumWidth(), logo2.getMinimumHeight());
        rbDeposit.setCompoundDrawables(null, logo1, null, null);
        rbWithdraw.setCompoundDrawables(null, logo2, null, null);
        rbUser.setCompoundDrawables(null, logo3, null, null);
    }

    public void setMainName(String main1, String main2, String main3) {
        rbDeposit.setText(main1);
        rbWithdraw.setText(main2);
        rbUser.setText(main3);
    }

    private void initRGCheckedChangeListener(RadioGroup radioGroup) {
        int childCount = radioGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RadioButton childAt = (RadioButton) radioGroup.getChildAt(i);
            childAt.setChecked(false);
        }
        setRGCheckedChangeListener();
    }

    private void setRGCheckedChangeListener() {
        if (BuildConfig.FLAVOR.equals("my2bet")) {
            rg_home_my2bet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (!hasLoginInfo() && checkedId != R.id.rb_contact_my2bet && checkedId != -1) {
                        initRGCheckedChangeListener(rg_home_my2bet);
                        ToastUtils.showShort(getString(R.string.please_login));
                        return;
                    }
                    switch (checkedId) {
                        case R.id.rb_contact_my2bet:
                            switchFragment(0);
                            break;
                        case R.id.rb_user_my2bet:
                            switchFragment(2);
                            break;
                    }
                }
            });
        } else {
            rg_home.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (!hasLoginInfo() && checkedId != R.id.rb_contact && checkedId != R.id.rb_user && checkedId != -1) {
                        initRGCheckedChangeListener(rg_home);
                        if (checkedId == R.id.rb_deposit && BuildConfig.FLAVOR.equals("ibet567")) {
                            presenter.openUrl("http://m.ibet567.com");
                        } else if (checkedId == R.id.rb_withdraw && BuildConfig.FLAVOR.equals("ibet567")) {
                            presenter.openUrl("https://www.ibet567.com/index.php?mobile=1");
                        } else {
                            ToastUtils.showShort(getString(R.string.please_login));
                        }
                        return;
                    }
                    switch (checkedId) {
                        case R.id.rb_deposit:
                            if (BuildConfig.FLAVOR.equals("k9th") && getCurrency().equals("THB")) {
                                firstFragment = depositCenterFragment;
                                fragmentList.set(0, firstFragment);
                            }
                            switchFragment(0);
                            break;
                        case R.id.rb_withdraw:
                            switchFragment(1);
                            break;
                        case R.id.rb_contact:
                            switchFragment(3);
                            break;
                        case R.id.rb_user:
                            if (BuildConfig.FLAVOR.equals("ibet567")) {
                                switchFragment(4);
                            } else {
                                if (!hasLoginInfo()) {
                                    initRGCheckedChangeListener(rg_home);
                                    ToastUtils.showShort(getString(R.string.please_login));
                                } else {
                                    switchFragment(4);
                                }
                            }
                            break;
                    }
                }
            });
        }
    }

    private void switchFragment(int index) {
        if (lastIndex == index) {
            return;
        }
        BaseFragment baseFragment = fragmentList.get(index);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!baseFragment.isAdded()) {
            fragmentTransaction.add(R.id.fl_main_content, baseFragment);
        } else {
            fragmentTransaction.show(baseFragment);
        }
        currentFragment = baseFragment;
        if (lastIndex != -1) {
            fragmentTransaction.hide(fragmentList.get(lastIndex));
        }
        lastIndex = index;
        fragmentTransaction.commit();
    }

    @Override
    protected void leftClick() {
        onBackPressed();
    }

    @Override
    public void finish() {
        if (AppTool.isShort2Click(1000)) {
            super.finish();
        } else {
            if (shouldShowExit)
                Toast.makeText(mContext, getString(R.string.double_click_exit_application), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void refreshMoney(String money) {
        super.refreshMoney(money);
        homeFragment.refreshMoney(money);
        if (userFragment != null) {
            userFragment.refreshMoney(money);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDigitalGamesMoney();
        if (BuildConfig.FLAVOR.equals("gasia88") && currentFragment.equals(homeFragment)) {
            showPop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeDigitalMoneyUpdate();
    }

    @Override
    public void onGetMsgBoxCount(String count) {
        super.onGetMsgBoxCount(count);
        if (BuildConfig.FLAVOR.equals("ibet567")) {
            return;
        }
        showMsgCount(count);
    }

    public void showMsgCount(final String count) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(count)) {
                    if (tvMsgCount != null && tvMsgCountMy2bet != null) {
                        if (!count.equals("0")) {
                            if (BuildConfig.FLAVOR.equals("my2bet")) {
                                tvMsgCountMy2bet.setVisibility(View.VISIBLE);
                                tvMsgCountMy2bet.setText(count);
                            } else {
                                tvMsgCount.setVisibility(View.VISIBLE);
                                tvMsgCount.setText(count);
                            }
                        } else {
                            if (BuildConfig.FLAVOR.equals("my2bet")) {
                                tvMsgCountMy2bet.setVisibility(View.GONE);
                            } else {
                                tvMsgCount.setVisibility(View.GONE);
                            }
                        }
                    }
                    if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        if (homeFragment != null) {
                            homeFragment.setMsgCount(count);
                            homeFragment.showMsgCount(count);
                        }
                    }
                    if (contactFragment != null) {
                        contactFragment.setMsgCount(count);
                        if (contactFragment.isAdded() && !contactFragment.isHidden()) {
                            contactFragment.showMsgCount(count);
                        }
                    }
                }
            }
        });
    }

    public void setLastFragment(BaseFragment lastFragment) {
        this.lastFragment = lastFragment;
        fragmentList = Arrays.asList(firstFragment, secondFragment, homeFragment, contactFragment, lastFragment);
    }

    public void switchDepositUi() {
        rbDeposit.setChecked(true);
    }
}
