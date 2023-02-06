package nanyang.com.dig88.NewKeno;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.NewKeno.presenter.NewKenoActivityPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.SoundPlayUtils;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2018/9/11.
 */

public class NewKenoActivity extends BaseActivity<NewKenoActivityPresenter> {
    public String baseUrl = "https://newkeno.k-api.com/api/";//http://newkeno.k-api.com
    @Bind(R.id.rg)
    RadioGroup rg;
    @Bind(R.id.rb_keno4)
    RadioButton rb4;
    @Bind(R.id.rb_keno3)
    RadioButton rb3;
    @Bind(R.id.rb_keno2)
    RadioButton rb2;
    @Bind(R.id.rb_list)
    RadioButton rb_list;
    @Bind(R.id.rb_result)
    RadioButton rb_result;
    @Bind(R.id.rb_rule)
    RadioButton rb_rule;
    NewKenoBaseFragment keno4Fragment;
    NewKenoBaseFragment keno3Fragment;
    NewKenoBaseFragment keno2Fragment;
    NewKenoBaseFragment winningFragment;
    NewKenoBaseFragment resultFragment;
    NewKenoBaseFragment ruleFragment;
    NewKenoBaseFragment lastBaseFragment;
    private List<NewKenoBaseFragment> fragmentList;
    private List<RadioButton> radioButtonList;
    private String username;
    private String rule = "4";

    public String getUsername() {
        return username;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_new_keno;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new NewKenoActivityPresenter(this));
        toolbar.setBackgroundResource(0);
        setTitle(getString(R.string.new_keno));
        showMoney();
        rightTv.setVisibility(View.VISIBLE);
        rb4.setText(getString(R.string.Keno) + " 4");
        rb3.setText(getString(R.string.Keno) + " 3");
        rb2.setText(getString(R.string.Keno) + " 2");
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        username = WebSiteUrl.WebId + "s" + s.getUsername();
        presenter.getBaseUrl();
//        onGetBaseUrl(baseUrl);
    }

    public void onGetBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        presenter.loginNewKeno();
    }

    public void onLoginSuccess() {
        initGame();
    }

    public void onLoginFailed() {
        ToastUtils.showShort(getString(R.string.Login_Failed));
        finish();
    }

    private void initGame() {
        SoundPlayUtils.init(mContext);
        keno4Fragment = new Keno4Fragment();
        keno3Fragment = new Keno3Fragment();
        keno2Fragment = new Keno2Fragment();
        winningFragment = new WinningFragment();
        resultFragment = new ResultFragment();
        ruleFragment = new RuleFragment();
        fragmentList = new ArrayList<>();
        radioButtonList = new ArrayList<>();
        String keno4 = "NK-4MIN";
        String keno3 = "NK-3MIN";
        String keno2 = "NK-2MIN";
        if (WebSiteUrl.WebId.equals("33") || WebSiteUrl.WebId.equals("52") || WebSiteUrl.WebId.equals("99") || WebSiteUrl.WebId.equals("135") || WebSiteUrl.WebId.equals("194")) {
            keno4 = "web_id-NK";
            keno3 = "web_id_3MIN";
            keno2 = "web_id_2MIN";
        }
        List<String> newKenoStatusList = getApp().getNewKenoStatusList();
        if (newKenoStatusList != null) {
            if (newKenoStatusList.size() > 0) {
                addKeno(newKenoStatusList, keno4Fragment, rb4, keno4);
                addKeno(newKenoStatusList, keno3Fragment, rb3, keno3);
                addKeno(newKenoStatusList, keno2Fragment, rb2, keno2);
            }
        }
        fragmentList.add(winningFragment);
        radioButtonList.add(rb_list);
        fragmentList.add(resultFragment);
        radioButtonList.add(rb_result);
        fragmentList.add(ruleFragment);
        radioButtonList.add(rb_rule);
        radioButtonList.get(0).setChecked(true);
        switchFragment(fragmentList.get(0));
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_keno4:
                        rule = "4";
                        switchFragment(keno4Fragment);
                        break;
                    case R.id.rb_keno3:
                        rule = "3";
                        switchFragment(keno3Fragment);
                        break;
                    case R.id.rb_keno2:
                        rule = "2";
                        switchFragment(keno2Fragment);
                        break;
                    case R.id.rb_list:
                        switchFragment(winningFragment);
                        break;
                    case R.id.rb_result:
                        switchFragment(resultFragment);
                        break;
                    case R.id.rb_rule:
                        switchFragment(ruleFragment);
                        break;
                }
            }
        });
    }

    private void addKeno(List<String> newKenoStatusList, NewKenoBaseFragment BaseFragment, RadioButton radioButton, String keno) {
        for (int i = 0; i < newKenoStatusList.size(); i++) {
            String state = newKenoStatusList.get(i);
            if (state.equals(keno)) {
                fragmentList.add(BaseFragment);
                radioButton.setVisibility(View.VISIBLE);
                radioButtonList.add(radioButton);
                break;
            }
        }
    }

    private void switchFragment(NewKenoBaseFragment newKenoBaseFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!newKenoBaseFragment.isAdded()) {
            fragmentTransaction.add(R.id.fl_content, newKenoBaseFragment);
        } else {
            fragmentTransaction.show(newKenoBaseFragment);
        }
        if (lastBaseFragment != null) {
            fragmentTransaction.hide(lastBaseFragment);
        }
        lastBaseFragment = newKenoBaseFragment;
        fragmentTransaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            back();
            return true;
        }
        return false;
    }

    @Override
    protected void leftClick() {
        back();
    }

    private void back() {
        finish();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getRule() {
        return rule;
    }
    //for check status close menu rule 2,3,4

//if web_id = [33,52,99,135,194]
//    you need filter
//            provider = web_id_2MIN
//    rule = 2
//    provider = web_id_3MIN
//            rule = 3
//    provider = web_id-NK
//            rule = 4
//
//else
//
//    provider = NK-2MIN
//            rule = 2
//
//    provider = NK-3MIN
//            rule = 3
//
//    provider = NK-4MIN
//
//            rule = 4
}
