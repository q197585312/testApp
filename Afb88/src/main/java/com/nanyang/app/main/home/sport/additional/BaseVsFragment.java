package com.nanyang.app.main.home.sport.additional;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/4.
 */

public abstract class BaseVsFragment<T> extends BaseFragment {
    @Bind(R.id.base_rv)
    RecyclerView baseRc;
    BaseRecyclerAdapter<T> adapter;
    List<T> list;
    @Bind(R.id.tv_mix_bet_count)
    TextView tvMixBetCount;
    protected boolean isMix;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_vs;
    }

    @Override
    public void initData() {
        super.initData();
        isMix=((VsActivity)getActivity()).getMixParlay();
        if(isMix){
            tvMixBetCount.setVisibility(View.VISIBLE);
        }else{
            tvMixBetCount.setVisibility(View.GONE);
        }
        baseRc.setLayoutManager(new LinearLayoutManager(mContext));
        list = new ArrayList<>();
        adapter = new BaseRecyclerAdapter<T>(mContext, list, onSetItemLayoutId()) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                convertItem(holder, position, item);
            }
        };
        baseRc.setAdapter(adapter);
    }

    protected abstract void convertItem(MyRecyclerViewHolder holder, int position, T item);

    protected abstract int onSetItemLayoutId();

    protected void setData(List<T> list) {
        this.list = list;
        adapter.addAllAndClear(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.addAllAndClear(list);
    }
}
