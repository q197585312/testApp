package com.nanyang.app.Utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nanyang.app.AfbUtils;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<Integer> lists;
    private List<ImageView> views;
    private Resources resources;
    public LinearLayout layout;
    private int index;
    public int itemTrueAmount;

    public ViewPagerAdapter(Resources resources, List<ImageView> views, List<Integer> lists, LinearLayout layout) {
        super();
        this.views = views;
        this.lists = lists;
        this.resources = resources;
        this.layout = layout;
        itemTrueAmount = lists.size();
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
        index = position % lists.size();
        ImageView view = views.get(index);
        if(view.getParent()!=null)
            ((ViewGroup)view.getParent()).removeView(view);
        container.addView(view);
        Bitmap b = AfbUtils.decodeSampledBitmapFromResource(resources, lists.get(index), 250, 250);
        view.setImageBitmap(b);
        if (b != null && b.isRecycled()) {
            b.recycle();
            b = null;
            System.gc();
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        index = position % lists.size();
        View view = views.get(index);
        container.removeView(view);
    }
}
