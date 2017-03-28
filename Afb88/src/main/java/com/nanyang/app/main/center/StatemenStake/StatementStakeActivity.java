package com.nanyang.app.main.center.StatemenStake;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.center.StatementStakeDetails.StatementDetailsActivity;
import com.nanyang.app.main.center.model.StatementStakeListBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/22.
 */

public class StatementStakeActivity extends BaseToolbarActivity<StatementStakePresenter> implements StatementStakeContact.View {
    @Bind(R.id.statement_stake_rc)
    RecyclerView rc;
    private String dataUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_stake);
        createPresenter(new StatementStakePresenter(this));
        getData();
    }

    private void getData() {
        dataUrl = AppConstant.APP_HOST + "_norm/";
        String urlEnd = getMsgIntent("stake");
        presenter.getThisBet(dataUrl + urlEnd);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void onGetData(final List<StatementStakeListBean> data) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(mLayoutManager);
        BaseRecyclerAdapter<StatementStakeListBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StatementStakeListBean>(mContext, data, R.layout.item_statement_stake) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, final StatementStakeListBean item) {
                TextView date = holder.getView(R.id.tv_stake_data);
                TextView math = holder.getView(R.id.tv_stake_math);
                TextView amount = holder.getView(R.id.tv_stake_state);
                TextView wl = holder.getView(R.id.tv_stake_money);
                TextView com = holder.getView(R.id.tv_stake_com);
                date.setText(item.getDate());
                amount.setText(item.getAmount());
                wl.setText(item.getWinLose());
                com.setText(item.getCom());
                if (!TextUtils.isEmpty(item.getEvent())) {
                    math.setText(AfbUtils.handleStringColor(item.getEvent(), Color.BLUE));
                    math.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle b = new Bundle();
                            b.putString("stake_details", item.getParamsUrl());
                            skipAct(StatementDetailsActivity.class, b);
                        }
                    });
                } else {
                    math.setText(item.getEvent());
                }
                if (item.getWinLose().startsWith("-")) {
                    wl.setTextColor(Color.RED);
                }

            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<StatementStakeListBean>() {
            @Override
            public void onItemClick(View view, StatementStakeListBean item, int position) {

            }

        });
        rc.setAdapter(baseRecyclerAdapter);
    }

    @Override
    public void onFailed(String error) {

    }
}
