package com.nanyang.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.Been.HomePopItemBeen;
import com.nanyang.app.Pop.HomePopupWindow;
import com.nanyang.app.R;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.BetCenter.BetCenterFragment;
import com.nanyang.app.main.home.HomeFragment;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseToolbarActivity<LanguagePresenter> implements ILanguageView<String> {
    @Bind(R.id.fl_menu_home)
    FrameLayout flMenuHome;
    @Bind(R.id.fl_menu_login_out)
    FrameLayout flLoginOut;
    @Bind(R.id.home_pop)
    TextView homePop;
    @Bind(R.id.ll_tab_menu_bottom)
    LinearLayout ll_tab_menu_bottom;
    @Nullable
    protected
    @Bind(R.id.drawer_more)
    DrawerLayout drawerLayout;
    private AfbDrawerViewHolder afbDrawerViewHolder;

    @Nullable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lag = AfbUtils.getLanguage(mContext);
        AfbUtils.switchLanguage(lag, mContext);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        createPresenter(new LanguagePresenter(this));
        toolbar.setNavigationIcon(null);
        initUserData();
        afbDrawerViewHolder = new AfbDrawerViewHolder(drawerLayout, this, R.id.fl_main_content);
        afbDrawerViewHolder.initDefaultFragment(homeFragment);
        afbDrawerViewHolder.switchFragment(homeFragment);
    }

    private BaseSwitchFragment homeFragment = new HomeFragment();


    @Override
    protected void updateBalanceTv(String allData) {
        tvToolbarRight.setText(getString(R.string.welcome) + " " + getApp().getUser().getLoginName());
    }

    private void initUserData() {
        ((AfbApplication) mContext.getApplication()).getUser().getBalances();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("shangpeisheng", "MainActivity: onResume");
        Log.d("shangpeisheng", "MainActivity--isRunForeground: " + AfbUtils.isRunForeground(getApplicationContext()));
        Log.d("shangpeisheng", "MainActivity--isRunBackground: " + AfbUtils.isRunBackground(getApplicationContext()));
        presenter.oddsType();
        String language = new LanguageHelper(mContext).getLanguage();
        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("AppGetDate", language, "wfMainH50"), new LanguagePresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                PersonalInfo personalInfo = new Gson().fromJson(data, PersonalInfo.class);
                personalInfo.setPassword(getApp().getUser().getPassword());
                getApp().setUser(personalInfo);
            }
        });

    }

    @OnClick({R.id.fl_menu_home, R.id.fl_menu_center, R.id.fl_menu_statemente, R.id.fl_menu_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_menu_home:
                HomePopupWindow<HomePopItemBeen> pop = new HomePopupWindow<HomePopItemBeen>(mContext, ll_tab_menu_bottom, LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public void initItem(MyRecyclerViewHolder holder, int position, HomePopItemBeen item) {
                        TextView name = holder.getTextView(R.id.tv_type_name);
                        TextView data = holder.getTextView(R.id.tv_type_data);
                        name.setText(item.getName());
                        data.setText(item.getData());
                    }

                    @Override
                    public List<HomePopItemBeen> getCurrentData() {
                        PersonalInfo info = getApp().getUser();
                        List<HomePopItemBeen> dataList = new ArrayList<>();
                        dataList.add(new HomePopItemBeen(getString(R.string.home_user_name), info.getLoginName()));
                        dataList.add(new HomePopItemBeen(getString(R.string.home_currency), info.getCurCode2()));
                        dataList.add(new HomePopItemBeen(getString(R.string.home_cash_balance), info.getBalances()));
                        dataList.add(new HomePopItemBeen(getString(R.string.home_not_standing), info.getEtotalstanding()));
                        dataList.add(new HomePopItemBeen(getString(R.string.home_min_bet), info.getMinLimit()));
                        dataList.add(new HomePopItemBeen(getString(R.string.home_bet_credit), info.getCredit2()));
                        dataList.add(new HomePopItemBeen(getString(R.string.home_given_credit), info.getTotalCredit()));
                        return dataList;
                    }
                };
                int windowPos[] = new int[2];
                ll_tab_menu_bottom.getLocationOnScreen(windowPos);
                int viewY = windowPos[1];
                pop.showAtLocation(Gravity.NO_GRAVITY, 0, viewY - (AfbUtils.dp2px(mContext, 40)) * 7);
                break;
            case R.id.fl_menu_center:
                afbDrawerViewHolder.switchFragment(afbDrawerViewHolder.getContactFragment());
                break;
            case R.id.fl_menu_statemente:
                afbDrawerViewHolder.getStatementFragment().setSwitchType(BetCenterFragment.statementNew);
                afbDrawerViewHolder.switchFragment(afbDrawerViewHolder.getStatementFragment());
                break;
            case R.id.fl_menu_login_out:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return afbDrawerViewHolder.onKeyDown(keyCode, event);
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

    @Override
    public void onGetTransferMoneyData(int type, String getBackStr, String data) {

    }

    @Override
    public void onLoginAgainFinish(String gameType) {

    }
}
