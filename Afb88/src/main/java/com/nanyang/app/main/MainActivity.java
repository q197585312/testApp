package com.nanyang.app.main;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.Pop.HomePopupWindow;
import com.nanyang.app.R;
import com.nanyang.app.Utils.MyGoHomeBroadcastReceiver;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.data.HomePopItemBeen;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.BetCenter.BetCenterFragment;
import com.nanyang.app.main.home.HomeFragmentT;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseToolbarActivity<MainPresenter> implements ILanguageView<String> {
    @BindView(R.id.fl_menu_home)
    FrameLayout flMenuHome;
    @BindView(R.id.fl_menu_center)
    FrameLayout flContact;
    @BindView(R.id.fl_menu_login_out)
    FrameLayout flLoginOut;
    @BindView(R.id.home_pop)
    TextView homePop;
    @BindView(R.id.tv_tab_home)
    TextView tv_tab_home;
    @BindView(R.id.tv_tab_statement)
    TextView tv_tab_statement;
    @BindView(R.id.tv_tab_center)
    TextView tv_tab_center;
    @BindView(R.id.tv_tab_login_out)
    TextView tv_tab_login_out;
    @BindView(R.id.ll_tab_menu_bottom)
    LinearLayout ll_tab_menu_bottom;
    @BindView(R.id.view_line)
    View view_line;
    @BindView(R.id.img_message)
    ImageView img_message;
    @BindView(R.id.tv_menu)
    TextView tv_menu;
    @Nullable
    protected
    @BindView(R.id.drawer_more)
    DrawerLayout drawerLayout;
    private AfbDrawerViewHolder afbDrawerViewHolder;

    @Nullable

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lag = AfbUtils.getLanguage(mContext);
        AfbUtils.switchLanguage(lag, mContext);
        setContentView(R.layout.activity_main_tab);
        createPresenter(new MainPresenter(this));
        toolbar.setNavigationIcon(null);
        afbDrawerViewHolder = new AfbDrawerViewHolder(drawerLayout, this, R.id.fl_main_content);
        afbDrawerViewHolder.initDefaultFragment(homeFragment);
        afbDrawerViewHolder.switchFragment(homeFragment);
        int intExtra = getIntent().getIntExtra(AppConstant.KEY_INT, 0);
        myGoHomeBroadcastReceiver = new MyGoHomeBroadcastReceiver(getApp());
        registerReceiver(myGoHomeBroadcastReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        if (intExtra > 0) {
            String string = getString(R.string.app_name);
            goWebActivity(BuildConfig.PC_URL, string, true);
        } else {
            if (AppConstant.IS_AGENT) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        defaultSkip("SportBook");
                        finish();

                    }
                }, 100);
            }
        }
        if (BuildConfig.FLAVOR.equals("ez2888")) {
            view_line.setVisibility(View.GONE);
            tv_tab_home.setTextColor(Color.WHITE);
            tv_tab_statement.setTextColor(Color.WHITE);
            tv_tab_center.setTextColor(Color.WHITE);
            tv_tab_login_out.setTextColor(Color.WHITE);
            img_message.setVisibility(View.GONE);
        } else if (BuildConfig.FLAVOR.equals("usun")) {
            img_message.setVisibility(View.GONE);
            tv_menu.setBackgroundResource(R.drawable.usun_title);
            ll_tab_menu_bottom.setBackgroundResource(R.drawable.usun_bottom_bg);
        }
      String where=  getIntent().getStringExtra("fromWhere");
        if(where!=null&&where.contains("LoginActivity")){
            defaultSkip("SportBook");
        }
    }

    private BaseSwitchFragment homeFragment = new HomeFragmentT();
    MyGoHomeBroadcastReceiver myGoHomeBroadcastReceiver;

    @Override
    protected void updateBalanceTv(String allData) {
        tvToolbarRight.setText(getString(R.string.welcome) + " " + getApp().getUser().getLoginName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myGoHomeBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.FLAVOR.equals("ez2888")) {
            tv_tab_home.setText(R.string.bet);
            tv_tab_statement.setText(R.string.balances);
            tv_tab_center.setText(R.string.statement);
            tv_tab_login_out.setText(R.string.menu);
            tv_tab_home.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_bet, 0, 0);
            tv_tab_statement.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.main_balance, 0, 0);
            tv_tab_center.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_botton_teb_statement, 0, 0);
        } else if (BuildConfig.FLAVOR.equals("usun")) {
            tv_tab_home.setText(R.string.balances);
            tv_tab_statement.setText(R.string.statement);
            tv_tab_center.setText(R.string.Result);
            tv_tab_login_out.setText(R.string.menu);
            tv_tab_center.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.home_result, 0, 0);
        } else {
            tv_tab_home.setText(R.string.balances);
            tv_tab_statement.setText(R.string.statement);
            tv_tab_center.setText(R.string.contact);
            tv_tab_login_out.setText(R.string.menu);
        }
        presenter.oddsType();

        Log.d("shangpeisheng", "isGoHome: " + getApp().isGoHome());
        if (getApp().isGoHome()) {
            getApp().setGoHome(false);
            Log.d("shangpeisheng", "isGoHome: " + getApp().isGoHome());
            presenter.login(new LoginInfo(getApp().getUser().getLoginName(), getApp().getUser().getPassword()), new BaseConsumer<String>(this) {
                @Override
                protected void onBaseGetData(String data) {
                    onLanguageSwitchSucceed(data);
                }
            });
        }
    }

    @OnClick({R.id.fl_menu_home, R.id.fl_menu_center, R.id.fl_menu_statemente, R.id.fl_menu_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_menu_home:
                if (!BuildConfig.FLAVOR.equals("ez2888")) {
                    clickHome();
                }
                break;
            case R.id.fl_menu_center:
                if (BuildConfig.FLAVOR.equals("ez2888")) {
                    clickStatement();
                } else if (BuildConfig.FLAVOR.equals("usun")) {
                    clickResult();
                } else {
                    clickCenter();
                }
                break;
            case R.id.fl_menu_statemente:
                if (BuildConfig.FLAVOR.equals("ez2888")) {
                    clickHome();
                } else {
                    clickStatement();
                }
                break;
            case R.id.fl_menu_login_out:
                clickLogout();
                break;
        }
    }

    private void clickHome() {
        HomePopupWindow<HomePopItemBeen> pop = new HomePopupWindow<HomePopItemBeen>(mContext, ll_tab_menu_bottom, LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            public void initItem(MyRecyclerViewHolder holder, int position, HomePopItemBeen item) {
                TextView name = holder.getTextView(R.id.tv_type_name);
                TextView data = holder.getTextView(R.id.tv_type_data);

                if (item.getData().startsWith("-")) {
                    data.setTextColor(Color.RED);
                } else {
                    data.setTextColor(Color.BLACK);
                }
                data.setText(item.getData());
                name.setText(item.getName());

            }

            @Override
            public List<HomePopItemBeen> getCurrentData() {
                PersonalInfo info = getApp().getUser();
                List<HomePopItemBeen> dataList = new ArrayList<>();

                dataList.add(new HomePopItemBeen(getString(R.string.home_user_name), info.getLoginName()));
                dataList.add(new HomePopItemBeen(getString(R.string.home_currency), info.getCurCode2().replace("MYR", getString(R.string.MYR))));
                if (!AppConstant.IS_AGENT)
                    dataList.add(new HomePopItemBeen(getString(R.string.cash_balance), AfbUtils.scientificCountingToString(info.getBalances().trim().replaceAll(",", ""))));
                dataList.add(new HomePopItemBeen(getString(R.string.home_not_standing), info.getEtotalstanding()));
                dataList.add(new HomePopItemBeen(getString(R.string.home_min_bet), info.getMinLimit()));
                if (!AppConstant.IS_AGENT)
                    dataList.add(new HomePopItemBeen(getString(R.string.home_bet_credit), AfbUtils.addComma(info.getCredit2().trim().replaceAll(",", ""), tvTime)));
                dataList.add(new HomePopItemBeen(getString(R.string.home_given_credit), AfbUtils.addComma(info.getTotalCredit().trim().replaceAll(",", ""), tvTime)));
                return dataList;
            }
        };
        int windowPos[] = new int[2];
        ll_tab_menu_bottom.getLocationOnScreen(windowPos);
        int viewY = windowPos[1];
        pop.showAtLocation(Gravity.NO_GRAVITY, 0, viewY - (AfbUtils.dp2px(mContext, 40)) * 7);
    }

    private void clickCenter() {
        afbDrawerViewHolder.switchFragment(afbDrawerViewHolder.getContactFragment());
    }

    private void clickResult() {
        afbDrawerViewHolder.getStatementFragment().setSwitchTypeIndex(BetCenterFragment.grade);
        afbDrawerViewHolder.switchFragment(afbDrawerViewHolder.getStatementFragment());
    }

    private void clickStatement() {
        afbDrawerViewHolder.getStatementFragment().setSwitchTypeIndex(BetCenterFragment.statementNew);
        afbDrawerViewHolder.switchFragment(afbDrawerViewHolder.getStatementFragment());
    }

    private void clickLogout() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return afbDrawerViewHolder.onKeyDown(keyCode, event);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
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

    @Override
    public void onBackCLick(View v) {
        afbDrawerViewHolder.isBack(false);
    }

    @Override
    public void onGetData(String data) {

    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onLanguageSwitchSucceed(String str) {

    }

    public void loadingUrlPics(MainPresenter.CallBack<AllBannerImagesBean> callBack) {
        (presenter).loadAllImages(callBack);
    }

}
