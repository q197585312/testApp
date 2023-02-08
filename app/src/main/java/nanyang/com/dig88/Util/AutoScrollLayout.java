package nanyang.com.dig88.Util;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/1/16.
 */

public class AutoScrollLayout extends LinearLayout {
    private TextView contentTv;
    private String content;
    private int screenWidth;
    private double baseMoveWidth = 50;
    private ObjectAnimator animator;
    private int time = 3000;
    public AutoScrollLayout(Context context) {
        super(context);
        init();
    }

    public AutoScrollLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private void init() {
        setOrientation(HORIZONTAL);
        contentTv = new TextView(getContext());
        contentTv.setTextColor(getResources().getColor(R.color.white));
        TextPaint paint = contentTv.getPaint();
        paint.setFakeBoldText(true);
        addView(contentTv);
        contentTv.setVisibility(INVISIBLE);
    }

    private void countTime() {
        contentTv.measure(0, 0);
        int measuredWidth = contentTv.getMeasuredWidth();
        double totalTime = screenWidth / baseMoveWidth * 1000;
        if (measuredWidth > screenWidth) {
            totalTime = measuredWidth / baseMoveWidth * 1000;
        }
        setTime((int) totalTime);
    }

    public void startAnimation() {
        if (!TextUtils.isEmpty(content)) {
            contentTv.setText(getContent());
        } else {
            contentTv.setText("Welcome");
        }
        countTime();
        post(new Runnable() {
            @Override
            public void run() {
                if (animator == null) {
                    initContentTvIndex();
                    animator = ObjectAnimator.ofFloat(contentTv, "translationX", getWidth() + contentTv.getMeasuredWidth(), 0);
                    animator.setDuration(time);
                    animator.setRepeatCount(ValueAnimator.INFINITE);
                }
                animator.start();
                contentTv.setVisibility(VISIBLE);
            }
        });
    }

    public void stopAnimation() {
        contentTv.setVisibility(INVISIBLE);
        if (animator != null) {
            animator.cancel();
        }
    }

    private void initContentTvIndex() {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        contentTv.measure(0, 0);
        params.leftMargin = -contentTv.getMeasuredWidth();
        contentTv.setLayoutParams(params);
    }
}
