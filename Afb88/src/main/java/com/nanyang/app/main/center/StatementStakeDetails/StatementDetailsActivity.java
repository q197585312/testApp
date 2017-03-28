package com.nanyang.app.main.center.StatementStakeDetails;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.StatementStakeDetailsListBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/22.
 */

public class StatementDetailsActivity extends BaseToolbarActivity<StatementStakeDetailsPresenter> implements StatementStakeDetailsContact.View {
    @Bind(R.id.statement_stake_details_rc)
    RecyclerView rc;

    String dataUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_stake_details);
        createPresenter(new StatementStakeDetailsPresenter(this));
        getData();
    }

    private void getData() {
        dataUrl = AppConstant.APP_HOST + "_norm/";
        String urlEnd = getMsgIntent("stake_details");
        presenter.getStatementStakeDetailsData(dataUrl + urlEnd);
    }

    @Override
    public void onGetData(List<StatementStakeDetailsListBean> data) {
        initRc(data);
    }

    private void initRc(final List<StatementStakeDetailsListBean> data) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(mLayoutManager);
        BaseRecyclerAdapter<StatementStakeDetailsListBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StatementStakeDetailsListBean>(mContext, data, R.layout.item_statement_stake_details) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, StatementStakeDetailsListBean item) {
                TextView num = holder.getView(R.id.tv_num);
                num.setText(position + 1 + "");
                TextView RefNo = holder.getView(R.id.tv_RefNo);
                RefNo.setText(item.getRefNo());
                TextView Date = holder.getView(R.id.tv_Date);
                Date.setText(item.getDate());
                TextView ModuleTitle = holder.getView(R.id.tv_ModuleTitle);
                ModuleTitle.setText(item.getModuleTitle());
                TextView Home_and_Away = holder.getView(R.id.tv_Home_and_Away);
                Home_and_Away.setText(item.getHome() + " VS " + item.getAway());
                TextView WorkingDate = holder.getView(R.id.tv_WorkingDate);
                WorkingDate.setText(item.getWorkingDate());
                TextView Result = holder.getView(R.id.tv_Result);
                Result.setText(item.getResult());
                TextView BetType = holder.getView(R.id.tv_BetType);
                BetType.setText(item.getBetType());
                TextView HdpOdds = holder.getView(R.id.tv_HdpOdds);
                HdpOdds.setText(item.getHdpOdds() + "@");
                TextView Odds = holder.getView(R.id.tv_Odds);
                Odds.setText(item.getOdds());
                TextView Amount = holder.getView(R.id.tv_Amount);
                Amount.setText(item.getAmount());
                TextView ValidAmount = holder.getView(R.id.tv_ValidAmount);
                ValidAmount.setText(item.getValidAmount());
                TextView WinLose = holder.getView(R.id.tv_WinLose);
                if (item.getWinLose().startsWith("-")) {
                    WinLose.setTextColor(Color.RED);
                }
                WinLose.setText(item.getWinLose());
                TextView Com = holder.getView(R.id.tv_Com);
                Com.setText(item.getCom());
                LinearLayout dateLayout = holder.getView(R.id.layout_date);
                LinearLayout mathLayout = holder.getView(R.id.layout_math);
                LinearLayout stakeLayout = holder.getView(R.id.layout_stake);
                if (position==data.size()-1){
                    dateLayout.setVisibility(View.GONE);
                    mathLayout.setVisibility(View.GONE);
                    stakeLayout.setVisibility(View.GONE);
                }else {
                    dateLayout.setVisibility(View.VISIBLE);
                    mathLayout.setVisibility(View.VISIBLE);
                    stakeLayout.setVisibility(View.VISIBLE);
                }
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<StatementStakeDetailsListBean>() {
            @Override
            public void onItemClick(View view, StatementStakeDetailsListBean item, int position) {

            }

        });
        rc.setAdapter(baseRecyclerAdapter);
    }


    @Override
    public void onFailed(String error) {

    }
}
