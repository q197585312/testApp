package com.nanyang.app.Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.nanyang.app.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AutoScrollViewPager extends ViewPager {

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 通过反射修改ViewPager切换页面的过度时间
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            Scroller scroller = new Scroller(getContext()) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy,
                                        int duration) {
                    super.startScroll(startX, startY, dx, dy, 1500);
                }
            };
            field.set(this, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    LinearLayout layout;
    List<View> viewList;

    public void setAdapter(ViewPagerAdapter arg0) {
        super.setAdapter(arg0);
        totalPageCount = arg0.getCount();
        startTask();
        layout = arg0.layout;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
        params.leftMargin = 7;
        viewList = new ArrayList<>();
        for (int i = 0; i < totalPageCount; i++) {
            View view = new View(getContext());
            if (i == 0) {
                view.setBackgroundResource(R.drawable.indicator_selected);
            } else {
                view.setBackgroundResource(R.drawable.indicator_selecte_no);
            }
            view.setLayoutParams(params);
            viewList.add(view);
            layout.addView(view);
        }
    }

    private int totalPageCount;
    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message arg0) {
            setCurrentItem(arg0.arg1, true);
            return false;
        }
    });
    private int index = 0;
    private Timer timer = new Timer();
    private TimerTask task;

    private void startTask() {
        if (task == null) {
            task = new TimerTask() {

                @Override
                public void run() {
                    handler.sendMessage(handler.obtainMessage(0, index, 0));
                    index++;
                    if (index >= totalPageCount) {
                        index = 0;
                    }
                }
            };
            timer.schedule(task, 2000, 3000);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        switch (arg0.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopTask();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(arg0);
    }

    private void stopTask() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public OnPageChangeListener listener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (task == null) {
                index = getCurrentItem() + 1;
                startTask();
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (task == null) {
                index = getCurrentItem() + 1;
                startTask();
            }
            for (int i = 0; i < viewList.size(); i++) {
                if (i == position) {
                    viewList.get(i).setBackgroundResource(R.drawable.indicator_selected);
                } else {
                    viewList.get(i).setBackgroundResource(R.drawable.indicator_selecte_no);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
