package com.nanyang.app.main.home.sport.main;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class SportAdapterHelper<B extends SportInfo> implements IAdapterHelper<B> {

    public BaseRecyclerAdapter<B> getBaseRecyclerAdapter() {
        return baseRecyclerAdapter;
    }

    private BaseRecyclerAdapter<B> baseRecyclerAdapter;

    public ItemCallBack<B> getBack() {
        return back;
    }

    protected ItemCallBack<B> back;

    @Override
    public void onConvert(MyRecyclerViewHolder holder, int position, B item) {

    }

    public void bindAdapter(BaseRecyclerAdapter<B> baseRecyclerAdapter) {
        this.baseRecyclerAdapter=baseRecyclerAdapter;
    }


    public interface ItemCallBack<B> {
        B getItem(int position);

        /**
         *
         * @param v 点击的view
         * @param item
         * @param type 下注的类型 ou，hdp，over，under，_par
         * @param isHf 是否是半场
         * @param odds  下注赔率
         */
        void clickOdds(TextView v, B item, String type, boolean isHf, String odds,int oid,String sc);

        void clickView(View v, B item,int position);
        ScrollLayout onSetHeaderFollower();
    }

    public <I extends ItemCallBack<B>> void setItemCallBack(I back) {
        this.back = back;
    }
}
