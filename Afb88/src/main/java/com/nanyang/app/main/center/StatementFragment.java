package com.nanyang.app.main.center;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.StatementListBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementFragment extends BaseFragment<StatementContact.Presenter> implements StatementContact.View {
    @Bind(R.id.statement_rc)
    RecyclerView rc;
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
        presenter.getStatementData("mb", userName);
    }

    private void initRc(final List<StatementListBean> data) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(mLayoutManager);
        BaseRecyclerAdapter<StatementListBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StatementListBean>(mContext, data, R.layout.item_statement_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, StatementListBean item) {
                TextView date = holder.getView(R.id.tv_statement_date);
                TextView stake = holder.getView(R.id.tv_statement_stake);
                TextView amount = holder.getView(R.id.tv_statement_amount);
                TextView wl = holder.getView(R.id.tv_statement_win_or_lose);
                TextView commission = holder.getView(R.id.tv_statement_commission);
                TextView settled = holder.getView(R.id.tv_statement_settled);
                TextView balance = holder.getView(R.id.tv_statement_balance);
                date.setText(item.getDate());
                stake.setText(item.getStake());
                amount.setText(item.getValid_amount());
                wl.setText(item.getWl());
                commission.setText(item.getCom());
                settled.setText(item.getSettled());
                balance.setText(item.getBalance());
                if (position == 0) {
                    date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.getThisBetHistory("mb", userName);
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
    }

    @Override
    public void onGetData(final String data) {
        initRc(presenter.parseData(data));
    }

    @Override
    public void onFailed(String error) {

    }
}
