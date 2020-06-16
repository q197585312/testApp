package com.nanyang.app.Utils;

import androidx.viewpager.widget.PagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private List<RecyclerView> rcList;

    public MyViewPagerAdapter(List<RecyclerView> rcList) {
        this.rcList = rcList;
    }

    @Override
    public int getCount() {
        return rcList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(rcList.get(position));//删除页卡
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        container.addView(rcList.get(position));//添加页卡
        return rcList.get(position);
    }

}
