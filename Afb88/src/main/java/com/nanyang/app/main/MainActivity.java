package com.nanyang.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
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
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.BetCenter.BetCenterFragment;
import com.nanyang.app.main.home.HomeFragment;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
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
        createPresenter(new MainPresenter(this));
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvToolbarLeft.setVisibility(View.VISIBLE);
        tvToolbarLeft.setBackgroundResource(R.mipmap.left_logo);
        initUserData();
        afbDrawerViewHolder = new AfbDrawerViewHolder(drawerLayout, this, R.id.fl_main_content);
        afbDrawerViewHolder.initDefaultFragment(homeFragment);
        afbDrawerViewHolder.switchFragment(homeFragment);
    }

    private BaseSwitchFragment homeFragment = new HomeFragment();

    @Override
    public boolean isNeedUpdateTime() {
        return true;
    }

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
        presenter.oddsType();
        String language = new LanguageHelper(mContext).getLanguage();
        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("AppGetDate", language, "wfMainH50"), new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                PersonalInfo personalInfo = new Gson().fromJson(data, PersonalInfo.class);
                personalInfo.setPassword(getApp().getUser().getPassword());
                getApp().setUser(personalInfo);
            }
        });

        /*{
		"M_TAm36": 76,
		"M_RAm36": 12,
		"M_EAm36": 373,
		"M_TAm1": 200,
		"M_RAm1": 35,
		"M_EAm1": 545,
		"M_TAm2": 55,
		"M_TAm3": 38,
		"M_TAm4": 22,
		"M_EAm4": 104,
		"M_EAm5": 91,
		"M_Name33_18": "1D 游戏",
		"M_TAm33_18": 5,
		"M_RAm33_18": 0,
		"M_EAm33_18": 5,
		"M_Name33_19": "2D 游戏",
		"M_TAm33_19": 3,
		"M_RAm33_19": 0,
		"M_EAm33_19": 3,
		"M_Name33_20": "3D 游戏",
		"M_TAm33_20": 4,
		"M_RAm33_20": 0,
		"M_EAm33_20": 4,
		"M_TAm9": 22,
		"M_TAm10": 21,
		"M_EAm10": 4,
		"M_TAm11": 21,
		"M_EAm12": 12,
		"M_EAm15": 3,
		"M_EAm16": 3,
		"M_TAm24": 9,
		"M_TAm25": 9,
		"M_EAm26": 3,
		"M_TAm32": 584,
		"M_RAm32": 39,
		"M_EAm32": 2519,
		"M_PAm32": 623,
		"M_RAm0": 86,
		"M_EAm999": 110,
		"M_RAm66": 35,
		"M_TAm66": 200,
		"M_EAm66": 545
	}*/

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
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onGetData(String data) {
        ToastUtils.showShort(data);
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

}
