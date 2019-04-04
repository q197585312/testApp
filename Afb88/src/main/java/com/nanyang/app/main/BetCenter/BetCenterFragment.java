package com.nanyang.app.main.BetCenter;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.nanyang.app.R;
import com.nanyang.app.main.BaseSwitchFragment;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2019/4/4.
 */

public class BetCenterFragment extends BaseSwitchFragment {
    @Bind(R.id.rg_bet_center)
    RadioGroup rgBetCenter;
    List<BaseFragment> baseFragmentList;
    BaseFragment unsettledFragment = new UnsettledFragment();
    BaseFragment statementNewFragment = new StatementNewFragment();
    BaseFragment gradeFragment = new GradeFragment();

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_bet_center;
    }

    @Override
    public void initData() {
        super.initData();
        setCurrentFragmentTitle();
    }

    @Override
    public void initView() {
        super.initView();
        baseFragmentList = Arrays.asList(unsettledFragment, statementNewFragment, gradeFragment);
        rgBetCenter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_unsettled:
                        switchFragment(0);
                        break;
                    case R.id.rb_statement:
                        switchFragment(1);
                        break;
                    case R.id.rb_grade:
                        switchFragment(2);
                        break;
                }
            }
        });
    }

    private int lastIndex = -1;

    public void switchFragment(int currentIndex) {
        BaseFragment fragment = baseFragmentList.get(currentIndex);
        FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_content, fragment);
        } else {
            transaction.show(fragment);
        }
        if (lastIndex != -1) {
            transaction.hide(baseFragmentList.get(lastIndex));
        }
        lastIndex = currentIndex;
        transaction.commit();
    }
}
