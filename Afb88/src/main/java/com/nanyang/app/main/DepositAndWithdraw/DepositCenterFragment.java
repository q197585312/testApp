package com.nanyang.app.main.DepositAndWithdraw;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;

import com.nanyang.app.R;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.BaseSwitchPresenter;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class DepositCenterFragment extends BaseMoreFragment {
    @BindView(R.id.rg_deposit_center)
    RadioGroup rgDepositCenter;
    @BindView(R.id.rb_auto_deposit)
    RadioButton rbAutoDeposit;
    @BindView(R.id.rb_deposit)
    RadioButton rbDeposit;
    @BindView(R.id.rb_deposit_history)
    RadioButton rbDepositHistory;

    private BaseFragment indexFragment;
    List<BaseFragment> baseFragmentList;
    BaseFragment autoDepositFragment = new AutoDepositFragment();
    BaseFragment depositFragment = new DepositFragment();
    BaseFragment depositHistoryFragment = new DepositHistory();

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_deposit_center;
    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new BaseSwitchPresenter(this));
    }

    @Override
    public void initView() {
        super.initView();
        baseFragmentList = Arrays.asList(autoDepositFragment, depositFragment, depositHistoryFragment);
        switchFragment();
        rgDepositCenter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_auto_deposit:
                        currentIndex = 0;
                        break;
                    case R.id.rb_deposit:
                        currentIndex = 1;
                        break;
                    case R.id.rb_deposit_history:
                        currentIndex = 2;
                        break;
                }
                switchFragment();
            }
        });
    }

    private int currentIndex = 1;

    public void switchFragment() {
        if (currentIndex == 0) {
            rbAutoDeposit.setChecked(true);
        } else if (currentIndex == 1) {
            rbDeposit.setChecked(true);
        } else {
            rbDepositHistory.setChecked(true);
        }
        BaseFragment fragment = baseFragmentList.get(currentIndex);
        if (indexFragment == null || (fragment != null && !fragment.equals(indexFragment))) {
            getBaseActivity().showFragmentToActivity(fragment, R.id.fl_depositCenter_content);
            if (indexFragment != null) {
                getBaseActivity().hideFragmentToActivity(indexFragment);
            }
            indexFragment = fragment;
        }
    }

    @Override
    public void showContent() {
        super.showContent();
        setBackTitle(getString(R.string.deposit));
    }
}
