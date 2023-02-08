package nanyang.com.dig88.Util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;


import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nanyang.com.dig88.R;

public class AutoScrollViewPager extends ViewPager {

    LinearLayout layout;
    List<View> viewList;
    float firstX, lastX;
    private int trueItemCount;
    public OnPageChangeListener listener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (viewList != null && viewList.size() > 0) {
                for (int i = 0; i < trueItemCount; i++) {
                    if (i == position % trueItemCount) {
                        viewList.get(i).setBackgroundResource(R.drawable.indicator_selected);
                    } else {
                        viewList.get(i).setBackgroundResource(R.drawable.indicator_selecte_no);
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message arg0) {
            setCurrentItem(arg0.arg1, true);
            return false;
        }
    });
    private int index;
    private Timer timer = new Timer();
    private TimerTask task;

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

    public void setAdapter(ViewPagerAdapter arg0) {
        super.setAdapter(arg0);
        trueItemCount = arg0.itemTrueAmount;
        if (trueItemCount > 1) {
            startTask();
        }
        layout = arg0.layout;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
        params.leftMargin = 7;
        viewList = new ArrayList<>();
        viewList.clear();
        index = trueItemCount * 10;
        setCurrentItem(index);
        for (int i = 0; i < trueItemCount; i++) {
            View view = new View(getContext());
            if (i == index % trueItemCount) {
                view.setBackgroundResource(R.drawable.indicator_selected);
            } else {
                view.setBackgroundResource(R.drawable.indicator_selecte_no);
            }
            view.setLayoutParams(params);
            viewList.add(view);
            layout.addView(view);
        }
        setOnPageChangeListener(listener);
    }

    private void startTask() {
        if (task == null) {
            task = new TimerTask() {

                @Override
                public void run() {
                    handler.sendMessage(handler.obtainMessage(0, index, 0));
                    index++;
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
                firstX = getScrollX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastX = getScrollX();
                int currentIndex = getCurrentItem();
                if (firstX - lastX <= 0) {
                    index = currentIndex + 1;
                } else {
                    index = currentIndex - 1;
                }
                startTask();
                break;
        }
        return super.onTouchEvent(arg0);
    }

    public void stopTask() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}