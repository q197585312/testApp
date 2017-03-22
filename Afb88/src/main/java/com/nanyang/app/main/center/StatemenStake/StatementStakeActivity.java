package com.nanyang.app.main.center.StatemenStake;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.StatementListBean;
import com.nanyang.app.main.center.model.StatementStakeListBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/22.
 */

public class StatementStakeActivity extends BaseActivity<StatementStakePresenter> implements StatementStakeContact.View {
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
    public void onGetData(List<StatementStakeListBean> data) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(mLayoutManager);
        BaseRecyclerAdapter<StatementStakeListBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StatementStakeListBean>(mContext, data, R.layout.item_stake) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, final StatementStakeListBean item) {
                TextView date = holder.getView(R.id.tv_stake_data);
                TextView math = holder.getView(R.id.tv_stake_math);
                TextView amount = holder.getView(R.id.tv_stake_state);
                TextView wl = holder.getView(R.id.tv_stake_money);
                TextView com = holder.getView(R.id.tv_stake_com);
                date.setText(item.getDate());
                math.setText(item.getEvent());
                amount.setText(item.getAmount());
                wl.setText(item.getWinLose());
                com.setText(item.getCom());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<StatementListBean>() {
            @Override
            public void onItemClick(View view, StatementListBean item, int position) {

            }

        });
        rc.setAdapter(baseRecyclerAdapter);
    }

    @Override
    public void onFailed(String error) {

    }
}
