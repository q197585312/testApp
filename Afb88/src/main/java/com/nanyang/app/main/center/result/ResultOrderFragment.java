package com.nanyang.app.main.center.result;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
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
import java.util.List;
import java.util.Locale;

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
    @Bind(R.id.tv_module_itle)
    TextView tvModuleItle;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    private BaseRecyclerAdapter<ResultInfo> adapter;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_result_order;
    }

    @Override
    public void initView() {
        super.initView();
        createPresenter(new ResultPresenter(this));
        presenter.initGameDate(tvGameDate);
        presenter.initGameTypes(tvGameType);
        presenter.initMarket(tvMarket);
        presenter.initSort(tvSort);
        presenter.getResultData();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(mLayoutManager);
        adapter = new BaseRecyclerAdapter<ResultInfo>(mContext, new ArrayList<ResultInfo>(), R.layout.result_match_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, ResultInfo item) {
                TextView titleTv = holder.getView(R.id.result_match_title_tv);
                View headV = holder.getView(R.id.result_match_head_v);
                TextView timeTv = holder.getView(R.id.result_match_date_tv);
                TextView homeTv = holder.getView(R.id.result_match_home_tv);
                TextView awayTv = holder.getView(R.id.result_match_away_tv);
                TextView scoreTv = holder.getView(R.id.result_match_score_tv);
                TextView halfTv = holder.getView(R.id.result_match_half_tv);
                int oldModuleId = -1;
                if (position > 0) {
                    oldModuleId = getItem(position - 1).getModuleId();
                }

                if (item.getModuleId() == (oldModuleId) && position != 0) {
                    headV.setVisibility(View.GONE);
                    titleTv.setVisibility(View.GONE);
                } else {
                    headV.setVisibility(View.VISIBLE);
                    titleTv.setVisibility(View.VISIBLE);
                    titleTv.setText(item.getModuleTitle());
                }
                String time = item.getMatchDate();
                if (time.length() > 6) {
                    time = time.substring(time.length() - 7, time.length());
                    time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
                }
                timeTv.setText(time);
                homeTv.setText(item.getHome());
                awayTv.setText(item.getAway());
                scoreTv.setText(item.getScore());
                halfTv.setText(item.getHTScore());
            }
        };

        rvContent.setAdapter(adapter);
    }


    @Override
    public void onGetData(String data) {
        Gson gson = new Gson();
        data = Html.fromHtml(data).toString();
        String[] data1 = data.split("nyhxkj");
        ResultDicDataList stakeListBean = gson.fromJson(data1[0], ResultDicDataList.class);
        List<ResultInfo> list = stakeListBean.getDicData();
        adapter.addAllAndClear(list);
    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public Activity getContextActivity() {
        return getBaseActivity();
    }

    @Override
    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreated(pop, center);
    }

    /*GameType
gameDate
 sortBy
marketType*/
    @OnClick({R.id.tv_game_type, R.id.tv_game_date, R.id.tv_market, R.id.tv_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_game_type:
                presenter.selectedType("GameType",view);
                break;
            case R.id.tv_game_date:
                presenter.selectedType("gameDate",view);
                break;
            case R.id.tv_market:
                presenter.selectedType("marketType",view);
                break;
            case R.id.tv_sort:
                presenter.selectedType("sortBy",view);
                break;
        }
    }
}
