package com.nanyang.app.main.BetCenter;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nanyang.app.R;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.BaseSwitchPresenter;
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
    @Bind(R.id.rb_unsettled)
    RadioButton rbUnsettled;
    @Bind(R.id.rb_statement)
    RadioButton rbStatement;
    @Bind(R.id.rb_grade)
    RadioButton rbGrade;
    @Bind(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    List<BaseFragment> baseFragmentList;
    BaseFragment unsettledFragment = new UnsettledFragment();
    BaseFragment statementNewFragment = new StatementNewFragment();
    BaseFragment gradeFragment = new GradeFragment();
    public static String unsettled = "unsettled";
    public static String statementNew = "statementNew";
    public static String grade = "grade";

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_bet_center;
    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new BaseSwitchPresenter(this));
        setCurrentFragmentTitle();
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                baseFragmentList.get(currentIndex).refreshData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
                        currentIndex = 0;
                        break;
                    case R.id.rb_statement:
                        currentIndex = 1;
                        break;
                    case R.id.rb_grade:
                        currentIndex = 2;
                        break;
                }
                switchFragment();
            }
        });
        showContent();
    }

    private int lastIndex = -1;
    private int currentIndex = 0;

    public void switchFragment() {
        if (rbUnsettled == null) {
            return;
        }
        if (currentIndex == 0) {
            rbUnsettled.setChecked(true);
        } else if (currentIndex == 1) {
            rbStatement.setChecked(true);
        } else {
            rbGrade.setChecked(true);
        }
        if (lastIndex == currentIndex) {
            return;
        }
        BaseFragment fragment = baseFragmentList.get(currentIndex);
        FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_main_content, fragment);
        } else {
            transaction.show(fragment);
        }
        if (lastIndex != -1) {
            transaction.hide(baseFragmentList.get(lastIndex));
        }
        lastIndex = currentIndex;
        transaction.commit();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        showContent();
    }

    @Override
    public void showContent() {
        if (switchType.equals(unsettled)) {
            currentIndex = 0;
        } else if (switchType.equals(statementNew)) {
            currentIndex = 1;
        } else {
            currentIndex = 2;
        }
        switchFragment();
    }
}
