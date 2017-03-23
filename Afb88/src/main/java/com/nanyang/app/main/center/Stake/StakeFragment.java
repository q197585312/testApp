package com.nanyang.app.main.center.Stake;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.center.model.StakeListBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/23.
 */

public class StakeFragment extends BaseFragment<StakePresenter> implements StakeContact.View {
    @Bind(R.id.stake_rc)
    RecyclerView rc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter(new StakePresenter(this));
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getStakeListData();
    }


    @Override
    public void onGetData(StakeListBean data) {
        initRc(data);
    }

    private void initRc(StakeListBean data) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(mLayoutManager);
        BaseRecyclerAdapter<StakeListBean.DicAllBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StakeListBean.DicAllBean>(mContext, data.getDicAll(), R.layout.item_stake) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, StakeListBean.DicAllBean bean) {
                TextView num = holder.getView(R.id.tv_num);
                num.setText(position + 1 + "");
                TextView RefNo = holder.getView(R.id.tv_RefNo);
                RefNo.setText(bean.getRefNo());
                TextView Date = holder.getView(R.id.tv_Date);
                Date.setText(bean.getTransDate());
                TextView ModuleTitle = holder.getView(R.id.tv_ModuleTitle);
                ModuleTitle.setText(bean.getModuleTitle());
                TextView Home_and_Away = holder.getView(R.id.tv_Home_and_Away);
                Home_and_Away.setText(bean.getHome() + " VS " + bean.getAway());
                TextView WorkingDate = holder.getView(R.id.tv_WorkingDate);
                WorkingDate.setText(bean.getR_DateTime());
//                TextView BetType = holder.getView(R.id.tv_BetType);
//                BetType.setText(bean.getBetType());
//                TextView HdpOdds = holder.getView(R.id.tv_HdpOdds);
//                HdpOdds.setText(bean.getHdpOdds() + "@");
//                TextView Odds = holder.getView(R.id.tv_Odds);
//                Odds.setText(bean.getOdds());
                TextView Amount = holder.getView(R.id.tv_Amount);
                Amount.setText(bean.getAmt());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<StakeListBean.DicAllBean>() {
            @Override
            public void onItemClick(View view, StakeListBean.DicAllBean item, int position) {

            }
        });
        rc.setAdapter(baseRecyclerAdapter);
    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_stake;
    }
}
