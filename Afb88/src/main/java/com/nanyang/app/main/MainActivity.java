package com.nanyang.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.Been.HomePopItemBeen;
import com.nanyang.app.Pop.HomePopupWindow;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.center.Statement.StatementFragment;
import com.nanyang.app.main.center.model.More;
import com.nanyang.app.main.home.HomeFragment;
import com.nanyang.app.main.home.contact.ContactFragment;
import com.nanyang.app.main.home.person.PersonCenterFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

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
    @Bind(R.id.drawer_more)
    DrawerLayout drawerLayout;
    @Bind(R.id.home_pop)
    TextView homePop;
    @Bind(R.id.ll_tab_menu_bottom)
    LinearLayout ll_tab_menu_bottom;
    FrameLayout flCurrentMenu;
    @Bind(R.id.main_more)
    RecyclerView reContent;

    public BaseSwitchFragment homeFragment = new HomeFragment();
    public BaseSwitchFragment statementFragment = new StatementFragment();
    public BaseSwitchFragment contactFragment = new ContactFragment();
    public BaseSwitchFragment personFragment = new PersonCenterFragment();
    public BaseSwitchFragment indexFragment;
    public BaseSwitchFragment lastIndexFragment;
    private List<More> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lag = AfbUtils.getLanguage(mContext);
        AfbUtils.switchLanguage(lag, mContext);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        flCurrentMenu = flMenuHome;
        indexFragment = homeFragment;
        switchFragment(homeFragment);
        createPresenter(new MainPresenter(this));
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        reContent.setLayoutManager(llm);
        dataList = new ArrayList<>();
        More m1 = new More(R.mipmap.myacount, getString(R.string.my_account), 0);
        More m2 = new More(R.mipmap.messages, getString(R.string.messages), R.mipmap.message);
        More m3 = new More(R.mipmap.statement, getString(R.string.statement), 0);
        More m4 = new More(R.mipmap.result, getString(R.string.result), 0);
        More m5 = new More(R.mipmap.phone, getString(R.string.contact), 0);
        More m6 = new More(R.mipmap.setting, getString(R.string.setting), 0);
        More m7 = new More(R.mipmap.setting, getString(R.string.how_to_use), 0);
        More m8 = new More(R.mipmap.logout, getString(R.string.logout), 0);
        dataList.add(m1);
        dataList.add(m2);
        dataList.add(m3);
        dataList.add(m4);
        dataList.add(m5);
        dataList.add(m6);
        dataList.add(m7);
        dataList.add(m8);
        BaseRecyclerAdapter<More> adapter = new BaseRecyclerAdapter<More>(mContext, dataList, R.layout.item_main_more) {

            @Override
            public void convert(MyRecyclerViewHolder holder, int position, More item) {
                ImageView ivLeft = holder.getImageView(R.id.more_left_img);
                ImageView ivRight = holder.getImageView(R.id.more_img_right);
                ivLeft.setImageResource(item.getImage_left());
                if (item.getImage_right() != 0) {
                    ivRight.setImageResource(item.getImage_right());
                }
                TextView tv = holder.getTextView(R.id.more_text);
                tv.setText(item.getText());
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<More>() {
            @Override
            public void onItemClick(View view, More item, int position) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
                if (getString(R.string.my_account).equals(item.getText())) {
                    switchFragment(personFragment);
                } else if (getString(R.string.messages).equals(item.getText())) {

                } else if (getString(R.string.statement).equals(item.getText())) {

                } else if (getString(R.string.result).equals(item.getText())) {

                } else if (getString(R.string.contact).equals(item.getText())) {

                } else if (getString(R.string.setting).equals(item.getText())) {

                } else {
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
                }


            }
        });
        reContent.setAdapter(adapter);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvToolbarLeft.setVisibility(View.VISIBLE);
        tvToolbarLeft.setBackgroundResource(R.mipmap.left_logo);
        initUserData();
    }

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
        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("AppGetDate", language, "wfMainH50"));
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
                switchFragment(contactFragment);
                break;
            case R.id.fl_menu_statemente:
                switchFragment(statementFragment);
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

    public void switchFragment(BaseSwitchFragment fragment) {
        if (fragment == indexFragment && lastIndexFragment != null) {
            return;
        }
        indexFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_main_content, fragment);
        } else {
            transaction.show(fragment);
        }
        if (lastIndexFragment != null) {
            transaction.hide(lastIndexFragment);
        }
        lastIndexFragment = indexFragment;
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (indexFragment == homeFragment) {
                if (isTwoFinish()) {
                    finish();
                } else {
                    ToastUtils.showShort(getString(R.string.double_click_exit_application));
                }
            } else {
                indexFragment.back();
            }
        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        if (isTwoFinish()) {
//            finish();
//        } else {
//            ToastUtils.showShort(getString(R.string.double_click_exit_application));
//        }
//    }

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

}
