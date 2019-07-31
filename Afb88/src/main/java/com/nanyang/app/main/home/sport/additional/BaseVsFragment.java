package com.nanyang.app.main.home.sport.additional;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/4.
 */

public abstract class BaseVsFragment<T> extends BaseFragment {
    @Bind(R.id.base_rv)
    RecyclerView baseRc;

    public BaseRecyclerAdapter<T> getAdapter() {
        return adapter;
    }

    BaseRecyclerAdapter<T> adapter;
    List<T> list;


    public IBetHelper betHelper;
    protected BallInfo itemData;
    @Bind(R.id.tv_vs_header)
    LinearLayout tvVsHeader;
    protected String childParam = "";

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_vs;
    }

    @Override
    public void initData() {
        super.initData();
        list = new ArrayList<>();

        setAdapter(baseRc);
        itemData = (BallInfo) ((VsActivity) getActivity()).getItem();
        betHelper = ((VsActivity) getActivity()).getHelper();
        if (list != null && adapter != null)
            adapter.addAllAndClear(list);
        childParam = ((VsActivity) getActivity()).getChildParam();

    }

    protected void setAdapter(RecyclerView baseRc) {
        adapter = new BaseRecyclerAdapter<T>(mContext, list, onSetItemLayoutId()) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                convertItem(holder, position, item);
            }
        };
        baseRc.setLayoutManager(new LinearLayoutManager(mContext));
        baseRc.setAdapter(adapter);
    }

    protected abstract void convertItem(MyRecyclerViewHolder holder, int position, T item);

    protected abstract int onSetItemLayoutId();

    protected void setData(List<T> list) {
        this.list = list;
        if (adapter != null)
            adapter.addAllAndClear(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.addAllAndClear(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected void setOddsTextColor(String odds, TextView v) {
        if (odds != null && !odds.trim().isEmpty() && Float.valueOf(odds) < 0) {
            v.setTextColor(getResources().getColor(R.color.red_title));
        } else {
            v.setTextColor(getResources().getColor(R.color.black_grey));
        }
    }
}
