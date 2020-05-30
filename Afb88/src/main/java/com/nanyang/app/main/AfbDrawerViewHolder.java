package com.nanyang.app.main;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.main.BetCenter.Bean.More;
import com.nanyang.app.main.BetCenter.BetCenterFragment;
import com.nanyang.app.main.Setting.SettingFragment;
import com.nanyang.app.main.contact.ContactFragment;
import com.nanyang.app.main.howtouse.HowToUseFragment;
import com.nanyang.app.main.message.MessageFragment;
import com.nanyang.app.main.person.PersonCenterFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import java.util.ArrayList;
import java.util.List;

public class AfbDrawerViewHolder implements IDrawerView {
    private int fragmentResId;
    private BaseToolbarActivity baseToolbarActivity;
    private RecyclerView drawerLayoutRightRc;

    public BaseSwitchFragment getHomeFragment() {
        return homeFragment;
    }

    public BaseSwitchFragment getStatementFragment() {
        return statementFragment;
    }

    public BaseSwitchFragment getContactFragment() {
        return contactFragment;
    }

    public BaseSwitchFragment getChangeLanguageFragment() {
        return changeLanguageFragment;
    }

    public BaseSwitchFragment getPersonFragment() {
        return personFragment;
    }

    public BaseSwitchFragment getHowToUseFragment() {
        return howToUseFragment;
    }

    public BaseSwitchFragment getMessageFragment() {
        return messageFragment;
    }

    private BaseSwitchFragment homeFragment;
    private BaseSwitchFragment statementFragment = new BetCenterFragment();
    private BaseSwitchFragment contactFragment = new ContactFragment();
    private BaseSwitchFragment changeLanguageFragment = new SettingFragment();
    private BaseSwitchFragment personFragment = new PersonCenterFragment();
    private BaseSwitchFragment howToUseFragment = new HowToUseFragment();
    private BaseSwitchFragment messageFragment = new MessageFragment();
    private BaseSwitchFragment indexFragment;
    private BaseSwitchFragment lastIndexFragment;

    public AfbDrawerViewHolder(DrawerLayout drawerLayout, BaseToolbarActivity context, int fragmentResId) {
        this.drawerLayout = drawerLayout;
        this.baseToolbarActivity = context;
        this.fragmentResId = fragmentResId;
        initDrawerLayout();

    }


    public void initDefaultFragment(BaseSwitchFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    DrawerLayout drawerLayout;

    @Override
    public void initDrawerLayout() {
        personFragment.setHolder(this);
        messageFragment.setHolder(this);
        statementFragment.setHolder(this);
        contactFragment.setHolder(this);
        changeLanguageFragment.setHolder(this);
        howToUseFragment.setHolder(this);
        drawerLayoutRightRc = drawerLayout.findViewById(R.id.main_more);
        More m1 = new More(R.mipmap.myacount, baseToolbarActivity.getString(R.string.my_account), 0, personFragment);
        More m2 = new More(R.mipmap.messages, baseToolbarActivity.getString(R.string.messages), R.mipmap.email_msg, messageFragment);
        More m3 = new More(R.mipmap.main_statement, baseToolbarActivity.getString(R.string.statement), 0, statementFragment, BetCenterFragment.statementNew);
        More m4 = new More(R.mipmap.result, baseToolbarActivity.getString(R.string.result), 0, statementFragment, BetCenterFragment.grade);
        More m5 = new More(R.mipmap.contactus, baseToolbarActivity.getString(R.string.contact), 0, contactFragment);
        More m6 = new More(R.mipmap.setting, baseToolbarActivity.getString(R.string.setting), 0, changeLanguageFragment);
        More m7 = new More(R.mipmap.setting, baseToolbarActivity.getString(R.string.how_to_use), 0, howToUseFragment);
        More m8 = new More(R.mipmap.logout, baseToolbarActivity.getString(R.string.logout), 0);
        List<More> dataList = new ArrayList<>();
        dataList.add(m1);
        dataList.add(m2);
        dataList.add(m3);
        dataList.add(m4);

        dataList.add(m5);
        dataList.add(m6);
        dataList.add(m7);
        if (!AppConstant.IS_AGENT)
            dataList.add(m8);
        BaseRecyclerAdapter<More> adapter = new BaseRecyclerAdapter<More>(baseToolbarActivity, dataList, R.layout.item_main_more) {

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
                if (R.mipmap.logout == (item.getImage_left())) {

                    BaseYseNoChoosePopupWindow pop = new BaseYseNoChoosePopupWindow(baseToolbarActivity, drawerLayoutRightRc) {
                        @Override
                        protected void initView(View view) {
                            AfbUtils.switchLanguage(AfbUtils.getLanguage(baseToolbarActivity), context);
                            super.initView(view);
                        }

                        @Override
                        protected void clickSure(View v) {
                            Intent intent = new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            baseToolbarActivity.startActivity(intent);
                        }
                    };
                    pop.getChooseTitleTv().setText(baseToolbarActivity.getString(R.string.confirm_or_not));
                    pop.getChooseMessage().setText(baseToolbarActivity.getString(R.string.login_out));
                    pop.getChooseSureTv().setText(baseToolbarActivity.getString(R.string.sure));
                    pop.getChooseCancelTv().setText(baseToolbarActivity.getString(R.string.Cancel));
                    baseToolbarActivity.onPopupWindowCreatedAndShow(pop, Gravity.CENTER);
                } else {
                    BaseSwitchFragment fragment = item.getFragment();
                    if (fragment != null) {
                        String switchType = item.getSwitchType();
                        if (!TextUtils.isEmpty(switchType)) {
                            fragment.setSwitchTypeIndex(item.getSwitchType());
                        }
                        if (item.getText().equals(baseToolbarActivity.getString(R.string.my_account))) {
                            baseToolbarActivity.updateBalance();
                        }
                        switchFragment(fragment);
                    }
                }
            }
        });
        drawerLayoutRightRc.setLayoutManager(new LinearLayoutManager(baseToolbarActivity));
        drawerLayoutRightRc.setAdapter(adapter);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    public BaseSwitchFragment getIndexFragment() {
        return indexFragment;
    }

    public void switchFragment(BaseSwitchFragment fragment) {

        if (fragment != indexFragment || lastIndexFragment == null) {
            indexFragment = fragment;
            baseToolbarActivity.showFragmentToActivity(fragment, fragmentResId);
            if (lastIndexFragment != null) {
                baseToolbarActivity.hideFragmentToActivity(lastIndexFragment);
            }
            lastIndexFragment = indexFragment;
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean back = false;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back = isBack(back);
        }
        return back;
    }

    public boolean isBack(boolean back) {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
            back = true;
        }
        if (indexFragment == null)
            return back;
        if (!indexFragment.checkCanBack())
            return back;
        if (indexFragment == homeFragment) {
            baseToolbarActivity.finish();
        } else {
            switchFragment(homeFragment);
        }
        return back;
    }

    public void goRecord() {
        statementFragment.setSwitchTypeIndex(BetCenterFragment.unsettled);
        switchFragment(statementFragment);
    }

}
