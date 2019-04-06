package com.nanyang.app.main.BetCenter;

import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.main.BetCenter.Bean.DataInfoBean;
import com.nanyang.app.main.BetCenter.Bean.GradeAllMatchBean;
import com.nanyang.app.main.BetCenter.Presenter.GradePresenter;
import com.nanyang.app.main.BetCenter.pop.PopGradeSwitchType;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
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
    @Bind(R.id.ll_all_match)
    LinearLayout llAllMatch;
    @Bind(R.id.rg_type)
    RadioGroup rgType;
    private int currentRequestType = 1;//1是一般2是冠军
    private PopGradeSwitchType popSports;
    private PopGradeSwitchType popDate;
    private PopGradeSwitchType popFootball;
    private PopGradeSwitchType popAllMatch;
    private String gameType;
    private String date;
    private String leagueType;
    private BaseRecyclerAdapter<GradeAllMatchBean> normalAdapter;


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

                        break;
                    case R.id.rb_champion:
                        currentRequestType = 2;
                        break;
                }
                getGradeContentData();
            }
        });
        createPresenter(new GradePresenter(this));
        initAdapter();
//        getGradeData();
        gameType = "S,S,p1,g1";
        date = DateUtils.getCurrentDate("yyyy-MM-dd");
        leagueType = "0";
        getAllMatchDataList();
    }

    private void initAdapter() {
        normalAdapter = new BaseRecyclerAdapter<GradeAllMatchBean>(mContext, new ArrayList<GradeAllMatchBean>(), R.layout.item_normal_content) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, GradeAllMatchBean item) {
                LinearLayout llContent = holder.getView(R.id.ll_content);
                TextView tvContent = holder.getView(R.id.tv_content);
                tvContent.setText(item.getIndex1());
                if (position % 2 == 0) {
                    llContent.setBackgroundColor(0xffEFEFEF);
                } else {
                    llContent.setBackgroundColor(0xffD6DBD7);
                }
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(normalAdapter);
    }

    @Override
    public void initView() {
        super.initView();
    }

    public void onGetAllMatchDataList(List<DataInfoBean> list) {
        if (popAllMatch == null) {
            popAllMatch = createPop(llAllMatch, tvAllMatch, 4);
            popAllMatch.setDataList(list);
        }
    }

    public void onGetNormalGradeData(List<GradeAllMatchBean> list) {
        normalAdapter.setData(list);
    }

    public void onGeChampionGradeData() {

    }

    private void getAllMatchDataList() {
        presenter.getAllMatchDataList(gameType, date, leagueType);
    }

    private void getGradeContentData() {
        if (currentRequestType == 1) {
            presenter.getNormalGradeData(gameType, date, leagueType);
        } else {
            presenter.getChampionGradeData();
        }
    }

    @OnClick({R.id.ll_sports, R.id.ll_date, R.id.ll_football, R.id.ll_all_match})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_sports:
                if (popSports == null) {
                    popSports = createPop(v, tvSports, 1);
                    popSports.setDataList(presenter.getSportsDataList());
                }
                popSports.showPopupDownWindow();
                break;
            case R.id.ll_date:
                if (popDate == null) {
                    popDate = createPop(v, tvDate, 2);
                    popDate.setDataList(presenter.getDateDataList());
                }
                popDate.showPopupDownWindow();
                break;
            case R.id.ll_football:
                if (popFootball == null) {
                    popFootball = createPop(v, tvFootball, 3);
                    popFootball.setDataList(presenter.getFootballDataList());
                }
                popFootball.showPopupDownWindow();
                break;
            case R.id.ll_all_match:
                if (popAllMatch == null) {
                    popAllMatch = createPop(v, tvAllMatch, 4);
                }
                popAllMatch.showPopupDownWindow();
                break;
        }
    }

    private PopGradeSwitchType createPop(View v, final TextView tv, final int type) {
        int popHeight = AfbUtils.getScreenHeight(mContext) / 2;
        if (type == 1 || type == 2) {
            popHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        PopGradeSwitchType popGradeSwitchType = new PopGradeSwitchType(mContext, v, v.getWidth(), popHeight) {
            @Override
            public void onClickItem(DataInfoBean item) {
                tv.setText(item.getName());
                switch (type) {
                    case 1:
                        break;
                    case 2:
                        date = item.getType();
                        break;
                    case 3:
                        gameType = item.getType();
                        break;
                    case 4:
                        leagueType = item.getType();
                        getGradeContentData();
                        break;
                }
            }
        };
        return popGradeSwitchType;
    }
}
