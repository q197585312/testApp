package com.nanyang.app.main.BetCenter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.BetCenter.Bean.DataInfoBean;
import com.nanyang.app.main.BetCenter.Presenter.GradePresenter;
import com.nanyang.app.main.BetCenter.pop.PopGradeSwitchType;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/4/4.
 */

public class GradeFragment extends BaseFragment<GradePresenter> {
    @Bind(R.id.rc_content)
    RecyclerView rcContent;
    @Bind(R.id.tv_sports)
    TextView tvSports;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_football)
    TextView tvFootball;
    @Bind(R.id.tv_all_match)
    TextView tvAllMatch;
    @Bind(R.id.rg_type)
    RadioGroup rgType;
    private int currentRequestType = 1;//1是一般2是冠军
    private PopGradeSwitchType popSports;
    private PopGradeSwitchType popDate;
    private PopGradeSwitchType popFootball;
    private PopGradeSwitchType popAllMatch;


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_grade;
    }

    @Override
    public void initData() {
        super.initData();
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_normal:
                        currentRequestType = 1;
                        getGradeData();
                        break;
                    case R.id.rb_champion:
                        currentRequestType = 2;
                        getGradeData();
                        break;
                }
            }
        });
        createPresenter(new GradePresenter(this));
        initAdapter();
        getGradeData();
    }

    private void initAdapter() {

    }

    @Override
    public void initView() {
        super.initView();
    }

    public void onGetNormalGradeData() {

    }

    public void onGeChampionGradeData() {

    }

    private void getGradeData() {
        if (currentRequestType == 1) {
            presenter.getNormalGradeData();
        } else {
            presenter.getChampionGradeData();
        }
    }

    @OnClick({R.id.ll_sports, R.id.ll_date, R.id.ll_football, R.id.ll_all_match})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_sports:
                if (popSports == null) {
                    popSports = createPop(v, presenter.getSportsDataList(), tvSports);
                }
                popSports.showPopupDownWindow();
                break;
            case R.id.ll_date:
                if (popDate == null) {
                    popDate = createPop(v, presenter.getDateDataList(), tvDate);
                }
                popDate.showPopupDownWindow();
                break;
            case R.id.ll_football:
                if (popFootball == null) {
                    popFootball = createPop(v, presenter.getFootballDataList(), tvFootball);
                }
                popFootball.showPopupDownWindow();
                break;
            case R.id.ll_all_match:
                if (popAllMatch == null) {
                    popAllMatch = createPop(v, presenter.getAllMatchDataList(), tvAllMatch);
                }
                popAllMatch.showPopupDownWindow();
                break;
        }
    }

    private PopGradeSwitchType createPop(View v, List<DataInfoBean> dataList, final TextView tv) {
        PopGradeSwitchType popGradeSwitchType = new PopGradeSwitchType(mContext, v, v.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT, dataList) {
            @Override
            public void onClickItem(DataInfoBean item) {
                tv.setText(item.getName());
            }
        };
        return popGradeSwitchType;
    }
}
