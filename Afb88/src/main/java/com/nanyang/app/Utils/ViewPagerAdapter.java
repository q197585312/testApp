package com.nanyang.app.Utils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> reusableImgViews = new ArrayList<>();
    private List<Integer> lists;
    public LinearLayout layout;
    public int itemTrueAmount;
    private Context context;

    public ViewPagerAdapter(List<Integer> lists, LinearLayout layout, Context context) {
        super();
        this.lists = lists;
        this.layout = layout;
        itemTrueAmount = lists.size();
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgView;
        if (reusableImgViews.size() == 0) {
            imgView = new ImageView(context);
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imgView = reusableImgViews.remove(reusableImgViews.size() - 1);
        }

        Integer url = lists.get(getBannerIndexOfPosition(position));
        imgView.setImageResource(url);
        container.addView(imgView);
        return imgView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        reusableImgViews.add((ImageView) object);
    }

    private int getBannerIndexOfPosition(int position) {
        return position % lists.size();
    }


}
