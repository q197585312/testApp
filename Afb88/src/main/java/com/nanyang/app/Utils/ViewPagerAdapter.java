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

    public ViewPagerAdapter(Resources resources, List<ImageView> views, List<Integer> lists, LinearLayout layout) {
        super();
        this.views = views;
        this.lists = lists;
        this.resources = resources;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = views.get(position);
        container.addView(view);
        Bitmap b = AfbUtils.decodeSampledBitmapFromResource(resources, lists.get(position), 250, 250);
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
        View view = views.get(position);
        container.removeView(view);
    }
}
