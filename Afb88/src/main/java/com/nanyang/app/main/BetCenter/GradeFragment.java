package com.nanyang.app.main.BetCenter;

import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.BetCenter.Bean.DataInfoBean;
import com.nanyang.app.main.BetCenter.Bean.GradeAllMatchBean;
import com.nanyang.app.main.BetCenter.Bean.GradeOpenDataBean;
import com.nanyang.app.main.BetCenter.Presenter.GradePresenter;
import com.nanyang.app.main.BetCenter.pop.PopGradeSwitchType;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<Integer, Boolean> normalStatusMap;
    private int clickPosition;
    private LayoutInflater layoutInflater;


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_grade;
    }

    @Override
    public void initData() {
        super.initData();
        normalStatusMap = new HashMap<>();
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
                initBaseData();
                getAllMatchDataList();
            }
        });
        createPresenter(new GradePresenter(this));
        layoutInflater = LayoutInflater.from(mContext);
        initAdapter();
        initBaseData();
        getAllMatchDataList();
    }

    private void initBaseData() {
        List<DataInfoBean> footballDataList = presenter.getFootballDataList(currentRequestType);
        gameType = footballDataList.get(0).getType();
        date = presenter.getDateDataList().get(0).getType();
        tvDate.setText(presenter.getDateDataList().get(0).getName());
        leagueType = "0";
        tvFootball.post(new Runnable() {
            @Override
            public void run() {
                popFootball = createPop(tvFootball, tvFootball, 3);
                popFootball.setDataList(presenter.getFootballDataList(currentRequestType));
            }
        });
        tvFootball.setText(footballDataList.get(0).getName());
        tvAllMatch.setText(getString(R.string.all_match));
    }

    private void initAdapter() {
        normalAdapter = new BaseRecyclerAdapter<GradeAllMatchBean>(mContext, new ArrayList<GradeAllMatchBean>(), R.layout.item_normal_content) {
            @Override
            public void convert(MyRecyclerViewHolder holder, final int position, GradeAllMatchBean item) {
                LinearLayout llContent = holder.getView(R.id.ll_content);
                LinearLayout llAddView = holder.getView(R.id.ll_addView);
                TextView tvContent = holder.getView(R.id.tv_content);
                tvContent.setText(item.getIndex2());
                if (position % 2 == 0) {
                    llContent.setBackgroundColor(0xffEFEFEF);
                } else {
                    llContent.setBackgroundColor(0xffD6DBD7);
                }
                llAddView.removeAllViews();
                if (item.getGradeOpenDataBeanlist() != null) {
                    boolean status = normalStatusMap.get(position);
                    if (status) {
                        addNormalView(item.getGradeOpenDataBeanlist(), llAddView);
                        llAddView.setVisibility(View.VISIBLE);
                    } else {
                        llAddView.setVisibility(View.GONE);
                    }
                } else {
                    llAddView.setVisibility(View.GONE);
                }
            }
        };
        normalAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<GradeAllMatchBean>() {
            @Override
            public void onItemClick(View view, GradeAllMatchBean item, int position) {
                boolean status = normalStatusMap.get(position);
                normalStatusMap.put(position, !status);
                clickPosition = position;
                LinearLayout addView = view.findViewById(R.id.ll_addView);
                leagueType = normalAdapter.getItem(position).getIndex0() + "";
                if (addView.getChildCount() < 1) {
                    getGradeContentOpenData();
                } else {
                    int visibility = addView.getVisibility();
                    if (visibility == View.VISIBLE) {
                        addView.setVisibility(View.GONE);
                    } else {
                        addView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(normalAdapter);
    }

    public void onGetAllMatchDataList(List<DataInfoBean> list) {
        if (popAllMatch == null) {
            popAllMatch = createPop(llAllMatch, tvAllMatch, 4);
        }
        popAllMatch.setDataList(list);
        getGradeContentData();
    }


    public void onGetGradeData(List<GradeAllMatchBean> list) {
        normalStatusMap.clear();
        for (int i = 0; i < list.size(); i++) {
            normalStatusMap.put(i, false);
        }
        normalAdapter.setData(list);
    }

    public void onGradeContentOpenData(List<GradeOpenDataBean> list) {
        GradeAllMatchBean item = normalAdapter.getItem(clickPosition);
        item.setGradeOpenDataBeanlist(list);
        normalAdapter.notifyDataSetChanged();
    }

    private void addNormalView(List<GradeOpenDataBean> list, LinearLayout llAddView) {
        for (int i = 0; i < list.size(); i++) {
            GradeOpenDataBean bean = list.get(i);
            View view = layoutInflater.inflate(R.layout.item_grade_open, null);
            TextView tvDate = view.findViewById(R.id.tv_date);
            TextView tvTeamName1 = view.findViewById(R.id.tv_team_name1);
            TextView tvFh1 = view.findViewById(R.id.tv_fh1);
            TextView tvFt1 = view.findViewById(R.id.tv_ft1);
            TextView tvTeamName2 = view.findViewById(R.id.tv_team_name2);
            TextView tvFh2 = view.findViewById(R.id.tv_fh2);
            TextView tvFt2 = view.findViewById(R.id.tv_ft2);
            TextView tvHomeF = view.findViewById(R.id.tv_home_f);
            TextView tvHomeL = view.findViewById(R.id.tv_home_l);
            TextView tvAwayF = view.findViewById(R.id.tv_away_f);
            TextView tvAwayL = view.findViewById(R.id.tv_away_l);
                //  dataFormat = ;
            TimeUtils.dateFormat(bean.getIndex17(),"dd/MM/yyyy hh:mm");
            tvDate.setText(bean.getIndex17());
            tvTeamName1.setText(bean.getIndex8());
            int index14 = bean.getIndex14();
            String homeFOrL1 = getFOrL(index14, true, true);
            String homeFOrL2 = getFOrL(index14, true, false);
            String awayFOrL1 = getFOrL(index14, false, true);
            String awayFOrL2 = getFOrL(index14, false, false);
            if (!TextUtils.isEmpty(homeFOrL1)) {
                tvHomeF.setText(homeFOrL1);
                tvHomeF.setVisibility(View.VISIBLE);
            } else {
                tvHomeF.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(homeFOrL2)) {
                tvHomeL.setText(homeFOrL2);
                tvHomeL.setVisibility(View.VISIBLE);
            } else {
                tvHomeL.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(awayFOrL1)) {
                tvAwayF.setText(awayFOrL1);
                tvAwayF.setVisibility(View.VISIBLE);
            } else {
                tvAwayF.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(awayFOrL2)) {
                tvAwayL.setText(awayFOrL2);
                tvAwayL.setVisibility(View.VISIBLE);
            } else {
                tvAwayL.setVisibility(View.GONE);
            }
            if (currentRequestType == 1) {
                tvTeamName2.setText(bean.getIndex10());
                String index12 = bean.getIndex12();
                if (index12.contains("-")) {
                    String[] fhArr = index12.split("-");
                    tvFh1.setText(fhArr[0].trim());
                    tvFh2.setText(fhArr[1].trim());
                } else if(index12.contains("Cancel")){
                    tvFh1.setText("C");
                    tvFh2.setText("");
                }
                else {
                    tvFh1.setText(index12);
                    tvFh2.setText("");
                }
                String index11 = bean.getIndex11();
                if (index11.contains("-")) {
                    String[] ftArr = index11.split("-");
                    tvFt1.setText(ftArr[0].trim());
                    tvFt2.setText(ftArr[1].trim());
                }else if(index11.contains("Cancel")){
                    tvFt1.setText("C");
                    tvFt2.setText("");
                }
                else {
                    tvFt1.setText(index11);
                    tvFt2.setText("");
                }
            } else {
                TextView tvFhTitle = view.findViewById(R.id.tv_fh_title);
                TextView tvFtTitle = view.findViewById(R.id.tv_ft_title);
                tvFhTitle.setVisibility(View.GONE);
                tvFtTitle.setText(bean.getIndex11());
                tvFh1.setVisibility(View.GONE);
                tvFt1.setVisibility(View.GONE);
                tvTeamName2.setVisibility(View.GONE);
                tvFh2.setVisibility(View.GONE);
                tvFt2.setVisibility(View.GONE);
            }
            llAddView.addView(view);
        }
    }

    private String getFOrL(int type, boolean isHome, boolean isFG) {
        String s = "";
        if (type != 0) {
            if (type == 11) {
                if (isHome && isFG) {
                    s = "F";
                } else if (isHome && !isFG) {
                    s = "L";
                }
            } else if (type == 12) {
                if (isHome && isFG) {
                    s = "F";
                } else if (!isHome && !isFG) {
                    s = "L";
                }
            } else if (type == 21) {
                if (!isHome && isFG) {
                    s = "F";
                } else if (isHome && !isFG) {
                    s = "L";
                }
            } else if (type == 22) {
                if (!isHome && isFG) {
                    s = "F";
                } else if (!isHome && !isFG) {
                    s = "L";
                }
            }
        }
        return s;
    }

    private void getAllMatchDataList() {
        presenter.getAllMatchDataList(gameType, date, leagueType);
    }

    private void getGradeContentData() {
        presenter.getGradeData(gameType, date, leagueType);
    }

    private void getGradeContentOpenData() {
        presenter.getGradeContentOpenData(gameType, date, leagueType);
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

    private PopGradeSwitchType createPop(View v, TextView tv, final int type) {
        int popHeight = AfbUtils.getScreenHeight(mContext) / 2;
        if (type == 1 || type == 2) {
            popHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        PopGradeSwitchType popGradeSwitchType = new PopGradeSwitchType(mContext, v, v.getWidth(), popHeight) {
            @Override
            public void onClickItem(DataInfoBean item) {
                switch (type) {
                    case 1:
                        tvSports.setText(item.getName());
                        leagueType = "0";
                        tvAllMatch.setText(getString(R.string.all_match));
                        getAllMatchDataList();
                        break;
                    case 2:
                        tvDate.setText(item.getName());
                        leagueType = "0";
                        tvAllMatch.setText(getString(R.string.all_match));
                        date = item.getType();
                        getAllMatchDataList();
                        break;
                    case 3:
                        tvFootball.setText(item.getName());
                        leagueType = "0";
                        tvAllMatch.setText(getString(R.string.all_match));
                        gameType = item.getType();
                        getAllMatchDataList();
                        break;
                    case 4:
                        tvAllMatch.setText(item.getName());
                        leagueType = item.getType();
                        getGradeContentData();
                        break;
                }
            }
        };
        return popGradeSwitchType;
    }
}
