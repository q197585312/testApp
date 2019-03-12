package com.nanyang.app.main.center.result;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ResultOrderFragment extends BaseFragment<ResultPresenter> implements ResultContact.View {


    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.tv_game_type)
    TextView tvGameType;
    @Bind(R.id.tv_game_date)
    TextView tvGameDate;
    @Bind(R.id.tv_market)
    TextView tvMarket;
    @Bind(R.id.tv_sort)
    TextView tvSort;
    @Bind(R.id.tv_league_title)
    TextView tvModuleTitle;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_time)
    TextView tvTime;

    @Bind(R.id.ll_title_parent1)
    LinearLayout llTitleParent1;
    @Bind(R.id.ll_title_parent2)
    LinearLayout llTitleParent2;
    @Bind(R.id.ll_title_parent3)
    LinearLayout llTitleParent3;

    private BaseRecyclerAdapter<ResultInfo> adapter;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_result_order;
    }

    @Override
    public void initView() {
        super.initView();
        createPresenter(new ResultPresenter(this));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(mLayoutManager);

    }

    @Override
    public void initData() {
        super.initData();
        tvGameDate.setText(TimeUtils.dateFormat(new Date(), "yyyy-MM-dd"));
        presenter.getResultData();
        adapter = new BaseRecyclerAdapter<ResultInfo>(mContext, new ArrayList<ResultInfo>(), R.layout.result_match_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, ResultInfo item) {

                TextView titleTv = holder.getView(R.id.result_match_title_tv);
                LinearLayout ll_away = holder.getView(R.id.ll_away);

                TextView timeTv = holder.getView(R.id.result_match_date_tv);
                TextView homeTv = holder.getView(R.id.result_match_home_tv);
                TextView awayTv = holder.getView(R.id.result_match_away_tv);
                TextView homeFTv = holder.getView(R.id.result_match_home_f_tv);
                TextView homeLTv = holder.getView(R.id.result_match_home_l_tv);
                TextView awayFTv = holder.getView(R.id.result_match_away_f_tv);
                TextView awayLTv = holder.getView(R.id.result_match_away_l_tv);

                TextView scoreTv = holder.getView(R.id.result_match_score_tv);
                LinearLayout scoreLl = holder.getView(R.id.result_match_score_ll);
                TextView halfTv = holder.getView(R.id.result_match_half_tv);
                ((LinearLayout.LayoutParams) halfTv.getLayoutParams()).weight = 1;
                homeLTv.setVisibility(View.GONE);
                homeFTv.setVisibility(View.GONE);
                awayLTv.setVisibility(View.GONE);
                awayFTv.setVisibility(View.GONE);
                if (item.getAway() != null) {
                    homeTv.setText(item.getHome());
                    awayTv.setText(item.getAway());
                    titleTv.setVisibility(View.VISIBLE);
                    scoreLl.setVisibility(View.VISIBLE);
                    ll_away.setVisibility(View.VISIBLE);

                    String time = item.getMatchDate();
                    timeTv.setText(time);
                    int oldModuleId = -1;
                    if (position > 0) {
                        oldModuleId = getItem(position - 1).getModuleId();
                    }

                    if (item.getModuleId() == (oldModuleId) && position != 0) {

                        titleTv.setVisibility(View.GONE);
                    } else {
                        titleTv.setVisibility(View.VISIBLE);
                        titleTv.setText(item.getModuleTitle());
                    }
                    if ((item.getWinner() == null)) {
                        scoreTv.setText(item.getScore());
                        halfTv.setText(item.getHTScore());
                        String homeF = getFGLGResult(item.getRes1(), true, true);
                        String homeL = getFGLGResult(item.getRes1(), true, false);
                        setFL(homeFTv, homeF);
                        setFL(homeLTv, homeL);
                        String awayF = getFGLGResult(item.getRes1(), false, true);
                        String awayL = getFGLGResult(item.getRes1(), false, false);
                        setFL(awayFTv, awayF);
                        setFL(awayLTv, awayL);
                        if (item.isIsCancel()) {
                            scoreTv.setText(R.string.cancel);
                            scoreTv.setTextColor(getContextActivity().getResources().getColor(R.color.red_title));
                        } else {
                            scoreTv.setTextColor(getContextActivity().getResources().getColor(R.color.black_grey));
                        }
                        if (item.isIsCancelFH()) {
                            halfTv.setText(R.string.cancel);
                            halfTv.setTextColor(getContextActivity().getResources().getColor(R.color.red_title));
                        } else {
                            halfTv.setTextColor(getContextActivity().getResources().getColor(R.color.black_grey));
                        }

                    } else {
                        ((LinearLayout.LayoutParams) halfTv.getLayoutParams()).weight = 3;
                        halfTv.setText(item.getWinner());
                        scoreTv.setText("VS");
                        scoreTv.setTextColor(getContextActivity().getResources().getColor(R.color.black_grey));
                        halfTv.setTextColor(getContextActivity().getResources().getColor(R.color.black_grey));
                    }

                } else {
                    titleTv.setVisibility(View.GONE);
                    scoreLl.setVisibility(View.GONE);
                    ll_away.setVisibility(View.GONE);
                    timeTv.setText(item.getMatchDate());
                    homeTv.setText(item.getModuleTitle());
                    halfTv.setText(item.getHome());
                }
            }

        };

        rvContent.setAdapter(adapter);
    }

    private void setFL(TextView homeFTv, String homeF) {
        if (homeF.equals("")) {
            homeFTv.setVisibility(View.GONE);
        } else {
            homeFTv.setVisibility(View.VISIBLE);
            homeFTv.setText(homeF);
        }
    }

    public String getFGLGResult(int res1, boolean isHome, boolean isFG) {
        String s = "";

        if (res1 != 0) {
            if (res1 == 11) {
                if (isHome && isFG)
                    s = "F";
                else if (isHome && !isFG)
                    s = "L";
            } else if (res1 == 12) {
                if (isHome && isFG)
                    s = "F";
                else if (!isHome && !isFG)
                    s = "L";
            } else if (res1 == 21) {
                if (!isHome && isFG)
                    s = "F";
                else if (isHome && !isFG)
                    s = "L";
            } else if (res1 == 22) {
                if (!isHome && isFG)
                    s = "F";
                else if (!isHome && !isFG)
                    s = "L";
            }
        }

        return s;
    }

    @Override
    public void onGetData(String data) {
        Gson gson = new Gson();
        data = Html.fromHtml(data).toString();
        String[] data1 = data.split("nyhxkj");
        ResultDicDataList stakeListBean = gson.fromJson(data1[0], ResultDicDataList.class);
        List<ResultInfo> list = stakeListBean.getDicData();
        adapter.addAllAndClear(list);
        tvModuleTitle.setText(R.string.all);
        presenter.listLeague(list);

        llTitleParent1.setVisibility(View.GONE);
        llTitleParent2.setVisibility(View.GONE);
        llTitleParent3.setVisibility(View.GONE);

        if (list.get(0).getAway() == null) {
            llTitleParent3.setVisibility(View.VISIBLE);
        } else {
            if (list.get(0).getWinner() == null) {
                llTitleParent1.setVisibility(View.VISIBLE);
            } else {
                llTitleParent2.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onFailed(String error) {

    }



    @Override
    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreated(pop, center);
    }

    @Override
    public void onModuleList(String item, List<ResultInfo> resultInfos) {
        adapter.addAllAndClear(resultInfos);

    }

    /*GameType
gameDate
 sortBy
marketType*/
    @OnClick({R.id.tv_game_type, R.id.tv_game_date, R.id.tv_market, R.id.tv_sort, R.id.tv_league_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_game_type:
                presenter.selectedType("GameType", view);
                break;
            case R.id.tv_game_date:
                presenter.selectedType("gameDate", view);
                break;
            case R.id.tv_market:
                presenter.selectedType("marketType", view);
                break;
            case R.id.tv_sort:
                presenter.selectedType("sortBy", view);
                break;
            case R.id.tv_league_title:
                presenter.selectedType("moduleTitle", view);
                break;
        }
    }

}
