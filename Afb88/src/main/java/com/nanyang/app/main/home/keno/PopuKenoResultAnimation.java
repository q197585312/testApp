package com.nanyang.app.main.home.keno;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class PopuKenoResultAnimation extends BasePopupWindow {
    private long animationTime = 900;
    private Handler handler = new Handler();
    private List<String> animationDataList;
    private AnimationRunable animationRunable;
    private List<AnimatorSet> animatorSetList;

    public void startAction(List<String> animationDataList) {
        animatorSetList.clear();
        this.animationDataList = animationDataList;
        animationRunable = new AnimationRunable();
        handler.postDelayed(animationRunable, 100);
    }

    public PopuKenoResultAnimation(Context context, View v, int width, int height) {
        super(context, v, width, height);
        popWindow.setFocusable(false);
        popWindow.setOutsideTouchable(false);
        animatorSetList = new ArrayList<>();
    }

    RecyclerView rc;
    List<String> dataList;

    @Override
    protected void initView(View view) {
        super.initView(view);
        initData();
        rc = (RecyclerView) view.findViewById(R.id.keno_result_animation_rc);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 8);
        rc.setLayoutManager(layoutManager);
        BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(context, dataList, R.layout.item_keno_result_animation) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv = holder.getView(R.id.tv_content);
                tv.setText(item);
            }
        };
        rc.setAdapter(adapter);
    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            dataList.add(i + "");
        }
    }

    @Override
    protected void onClose() {
        super.onClose();
        handler.removeCallbacks(animationRunable);
    }

    class AnimationRunable implements Runnable {
        @Override
        public void run() {
            if (animationDataList != null && animationDataList.size() != 0) {
                for (int i = 0; i < animationDataList.size(); i++) {
                    String animationStr = animationDataList.get(i);
                    A:
                    for (int j = 0; j < rc.getChildCount(); j++) {
                        View v = rc.getChildAt(j);
                        TextView tv_content = (TextView) v.findViewById(R.id.tv_content);
                        if (animationStr.equals((j + 1) + "")) {
                            startAnimation(tv_content, animationTime * i, animationStr);
                            break A;
                        }
                    }
                }
            }
        }
    }

    public void stopAnimation() {
        for (int i = 0; i < animatorSetList.size(); i++) {
            AnimatorSet animatorSet = animatorSetList.get(i);
            if (animatorSet != null) {
                animatorSet.cancel();
                animatorSet = null;
            }
        }
        animatorSetList.clear();
    }

    private void startAnimation(final TextView view, final long delayedTime, final String animationStr) {
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        animatorSetList.add(animatorSet);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.3f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.3f, 1f);
        animatorSet.setStartDelay(delayedTime);
        animatorSet.setDuration(animationTime);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);//两个动画同时开始
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setBackgroundResource(R.mipmap.keno_animation_yellow_ball);
                        view.setTextColor(0xffA15E00);
                    }
                }, delayedTime);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animationStr.equals(animationDataList.get(animationDataList.size() - 1))) {
                    for (int j = 0; j < rc.getChildCount(); j++) {
                        View v = rc.getChildAt(j);
                        TextView tv = (TextView) v.findViewById(R.id.tv_content);
                        tv.setBackgroundResource(R.mipmap.keno_animation_green_ball);
                        tv.setTextColor(0xff167732);
                    }
                    closePopupWindow();
                    if (resultAnimationFinish != null) {
                        resultAnimationFinish.OnResultAnimationFinish();
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animatorSet.start();
    }

    private ResultAnimationFinish resultAnimationFinish;

    public void setResultAnimationFinish(ResultAnimationFinish resultAnimationFinish) {
        this.resultAnimationFinish = resultAnimationFinish;
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popu_keno_resule_animation;
    }

    interface ResultAnimationFinish {
        void OnResultAnimationFinish();
    }
}
