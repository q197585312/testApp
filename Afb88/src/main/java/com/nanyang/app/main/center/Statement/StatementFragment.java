package com.nanyang.app.main.center.Statement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.R;
import com.nanyang.app.main.center.StatemenStake.StatementStakeActivity;
import com.nanyang.app.main.center.model.StatementListBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementFragment extends BaseFragment<StatementContact.Presenter> implements StatementContact.View {
    @Bind(R.id.statement_list_rc)
    RecyclerView rc;
    @Bind(R.id.tv_blance_sure)
    TextView tv_blanceSure;
    @Bind(R.id.title_view)
    View titleView;
    @Bind(R.id.item_transfer_view)
    View transferView;
    String userName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter(new StatementPresenter(this));
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_statement;
    }

    @Override
    public void initData() {
        super.initData();
        AfbApplication app = (AfbApplication) getActivity().getApplication();
        userName = app.getUserName();
        presenter.getStatementData(userName);
    }

    private void initRc(final List<StatementListBean> data) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(mLayoutManager);
        BaseRecyclerAdapter<StatementListBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StatementListBean>(mContext, data, R.layout.item_statement_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, final StatementListBean item) {
                TextView date = holder.getView(R.id.tv_statement_date);
                TextView stake = holder.getView(R.id.tv_statement_stake);
                TextView amount = holder.getView(R.id.tv_statement_amount);
                TextView wl = holder.getView(R.id.tv_statement_win_or_lose);
                TextView commission = holder.getView(R.id.tv_statement_commission);
                TextView settled = holder.getView(R.id.tv_statement_settled);
                TextView balance = holder.getView(R.id.tv_statement_balance);
                date.setText(item.getDate());
                stake.setText(item.getStake());
                amount.setText(item.getValidAmount());
                wl.setText(item.getWL());
                commission.setText(item.getCom());
                settled.setText("0.00");
                balance.setText("0.00");
                if (!item.getStake().equals("0")&&position!=data.size()-1) {
                    stake.setTextColor(Color.BLUE);
                    stake.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle b = new Bundle();
                            b.putString("stake", item.getParamsUrl());
                            skipAct(StatementStakeActivity.class, b);
                        }
                    });
                }
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
    public void initView() {
        super.initView();
        titleView.setBackgroundColor(0xff1F5E1F);
        transferView.setBackgroundColor(0xff1F5E1F);
    }

    @Override
    public void onGetData(String data) {

    }

    private void initTransferRc() {
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//        rc.setLayoutManager(mLayoutManager);
//        BaseRecyclerAdapter<StatementListBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StatementListBean>(mContext, data, R.layout.item_statement_list) {
//            @Override
//            public void convert(MyRecyclerViewHolder holder, int position, StatementListBean item) {
//
//            }
//
//        };
//        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<StatementListBean>() {
//            @Override
//            public void onItemClick(View view, StatementListBean item, int position) {
//
//            }
//
//        });
//        rc.setAdapter(baseRecyclerAdapter);
    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onGetStatementListData(List<StatementListBean> list) {
        initRc(list);
    }

    @OnClick({R.id.tv_blance_sure})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_blance_sure:
                //TODO
                break;
        }

    }
}
