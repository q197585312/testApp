package com.nanyang.app.main.home.sportInterface;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class SportAdapterHelper<B extends SportInfo> implements IAdapterHelper<B> {

    public ItemCallBack<B> getBack() {
        return back;
    }

    protected ItemCallBack<B> back;

    @Override
    public void onConvert(MyRecyclerViewHolder holder, int position, B item) {

    }


    public interface ItemCallBack<B> {
        B getItem(int position);

        void clickOdds(TextView v, B item, String type, boolean isHf, String odds);

        void clickView(View v, B item);
    }

    public <I extends ItemCallBack<B>> void setItemCallBack(I back) {
        this.back = back;
    }
}
