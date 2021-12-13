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

public class WithdrawCenterFragment extends BaseMoreFragment {
    @BindView(R.id.rg_withdraw_center)
    RadioGroup rgWithdrawCenter;
    @BindView(R.id.rb_withdraw)
    RadioButton rbWithdraw;
    @BindView(R.id.rb_withdraw_history)
    RadioButton rbWithdrawHistory;

    private BaseFragment indexFragment;
    List<BaseFragment> baseFragmentList;
    BaseFragment withdrawFragment = new WithdrawFragment();
    BaseFragment withdrawHistoryFragment = new WithdrawHistoryFragment();

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_withdraw_center;
    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new BaseSwitchPresenter(this));
    }

    @Override
    public void initView() {
        super.initView();
        baseFragmentList = Arrays.asList(withdrawFragment, withdrawHistoryFragment);
        switchFragment();
        rgWithdrawCenter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_withdraw:
                        currentIndex = 0;
                        break;
                    case R.id.rb_withdraw_history:
                        currentIndex = 1;
                        break;
                }
                switchFragment();
            }
        });
    }

    private int currentIndex = 0;

    public void switchFragment() {
        if (currentIndex == 0) {
            rbWithdraw.setChecked(true);
        } else {
            rbWithdrawHistory.setChecked(true);
        }
        BaseFragment fragment = baseFragmentList.get(currentIndex);
        if (indexFragment == null || (fragment != null && !fragment.equals(indexFragment))) {
            getBaseActivity().showFragmentToActivity(fragment, R.id.fl_withdrawCenter_content);
            if (indexFragment != null) {
                getBaseActivity().hideFragmentToActivity(indexFragment);
            }
            indexFragment = fragment;
        }
    }

    @Override
    public void showContent() {
        super.showContent();
        setBackTitle(getString(R.string.withdraw));
    }
}
