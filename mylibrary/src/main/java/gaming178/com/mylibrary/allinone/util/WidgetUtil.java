package gaming178.com.mylibrary.allinone.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

public class WidgetUtil {
    /**
     * 动态设置ListView的高度 Item 必须是LinearLayout
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null)
            return;

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void coverView(View child, View cover) {
        ViewParent parent = child.getParent();
        if (cover.getParent() != null)
            if (cover.getParent() instanceof LinearLayout) {
                LinearLayout cP = (LinearLayout) cover.getParent();
                cP.removeView(cover);
            } else if (cover.getParent() instanceof RelativeLayout) {
                RelativeLayout cP = (RelativeLayout) cover.getParent();
                cP.removeView(cover);
            } else if (cover.getParent() instanceof FrameLayout) {
                FrameLayout cP = (FrameLayout) cover.getParent();
                cP.removeView(cover);
            }

        if (parent instanceof LinearLayout) {
            LinearLayout p = (LinearLayout) parent;
            p.addView(cover, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));
        } else if (parent instanceof RelativeLayout) {
            RelativeLayout p = (RelativeLayout) parent;
            p.addView(cover, new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.FILL_PARENT,
                    RelativeLayout.LayoutParams.FILL_PARENT));
        } else if (parent instanceof FrameLayout) {
            FrameLayout p = (FrameLayout) parent;
            p.addView(cover, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.FILL_PARENT,
                    FrameLayout.LayoutParams.FILL_PARENT));
        }
        child.setVisibility(View.GONE);
    }

    public static int getViewWidth(View view) {
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return view.getMeasuredWidth();
    }

    //高
    public static int getViewHeight(View view) {
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return view.getMeasuredHeight();
    }

    public static void showAnimation(View v, boolean able, int g) {
        TranslateAnimation mShowAction = null;
        TranslateAnimation mHiddenAction = null;
        if (able) {
            if (g == Gravity.TOP) {
                mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mShowAction.setDuration(500);
            }
            if (g == Gravity.BOTTOM) {
                mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mShowAction.setDuration(500);
            }
            v.startAnimation(mShowAction);
            v.setVisibility(View.VISIBLE);

        } else {
            if (g == Gravity.TOP) {
                mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        -1.0f);
            } else if (g == Gravity.BOTTOM) {
                mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        1.0f);
            }
            mHiddenAction.setDuration(500);
            v.startAnimation(mHiddenAction);
            v.setVisibility(View.GONE);
        }
    }

    public static void translateAnimation(final View v, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, long durationMillis) {
        TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        animation.setDuration(durationMillis);
        animation.setFillAfter(false);
        v.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Activity activity = (Activity) v.getContext();
                if (v != null&&activity.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "rotationX", 0, 12);
                    animator1.setDuration(0).start();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void chipTranslateAnimation(View view, int from, int to, Animator.AnimatorListener listener) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", from, to);
        objectAnimator.setDuration(500);
        objectAnimator.addListener(listener);
        objectAnimator.start();
    }

    public static void chipPortraitTranslateAnimation(View view, int from, int to, Animator.AnimatorListener listener) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", from, to);
        objectAnimator.setDuration(700);
        if (listener != null) {
            objectAnimator.addListener(listener);
        }
        objectAnimator.start();
    }

    public ObjectAnimator startAlphaAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1, (float) 0.5, 1);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setDuration(1000);
        animator.start();
        return animator;
    }

    private static DisplayMetrics outMetrics;
    private static DisplayMetrics portraitOutMetrics;

    private static void createScreen(Activity activity) {
        if (outMetrics == null) {
            outMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        }
    }

    private static void createPortraitScreen(Activity activity) {
        if (portraitOutMetrics == null) {
            portraitOutMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(portraitOutMetrics);
        }
    }

    public static int getScreenHeight(Activity activity) {
        createScreen(activity);
        return outMetrics.heightPixels;

    }

    public static int getPortraitScreenHeight(Activity activity) {
        createPortraitScreen(activity);
        return portraitOutMetrics.heightPixels;

    }

    public static int getScreenWidth(Activity activity) {
        createScreen(activity);
        return outMetrics.widthPixels;

    }

    public static int getPortraitScreenWidth(Activity activity) {
        createPortraitScreen(activity);
        return portraitOutMetrics.widthPixels;

    }

    public static void shrinkAnimation(final View view, int from, int to) {
        ValueAnimator animator = ValueAnimator.ofFloat(from, to);
        animator.setDuration(350);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) currentValue;
                view.requestLayout();
            }
        });
        animator.start();
    }
    /**
     * 判断程序是否在后台运行
     *
     * @param activity
     * @return true 表示在后台运行
     */

    public static boolean isRunBackground(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = activity.getApplicationContext().getPackageName();
        //获取Android设备中所有正在运行的App
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return true;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return false;
            }
        }
        return true;
    }

}
