package gaming178.com.casinogame.Util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import gaming178.com.baccaratgame.R;


public class CountDownView extends View {
    //圆轮颜色
    private int mRingColor;
    private int mRingColor2;
    //圆轮宽度
    private float mRingWidth;
    //圆轮进度值文本大小
    private int mRingProgessTextSize;
    //宽度
    private int mWidth;
    //高度
    private int mHeight;
    private Paint mPaint;
    //圆环的矩形区域
    private RectF mRectF;
    //
    private int mCountdownTime;
    private float mCurrentProgress;
    private OnCountDownFinishListener mListener;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        mRingColor = a.getColor(R.styleable.CountDownView_ringColor, context.getResources().getColor(R.color.colorAccent1));
        mRingColor2 = a.getColor(R.styleable.CountDownView_ringColor2, context.getResources().getColor(R.color.colorAccent2));
        mRingWidth = a.getFloat(R.styleable.CountDownView_ringWidth, 10);
        mRingProgessTextSize = a.getDimensionPixelSize(R.styleable.CountDownView_progressTextSize, sp2px(context, 20));
        mCountdownTime = a.getInteger(R.styleable.CountDownView_countdownTime, 60);
        a.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        this.setWillNotDraw(false);
    }

    public void setCountdownTime(int mCountdownTime) {
        this.mCountdownTime = mCountdownTime;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRectF = new RectF(0 + mRingWidth / 2, 0 + mRingWidth / 2,
                mWidth - mRingWidth / 2, mHeight - mRingWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         *圆环
         */
        //空心
        mPaint.setStyle(Paint.Style.STROKE);
        //宽度
        mPaint.setStrokeWidth(mRingWidth);
        mPaint.setColor(mRingColor2);
        canvas.drawArc(mRectF, 0, -360, false, mPaint);
        //颜色
        mPaint.setColor(mRingColor);
        canvas.drawArc(mRectF, -90, 360 - mCurrentProgress, false, mPaint);
    }

    private ValueAnimator getValA(long countdownTime) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(countdownTime);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(0);
        return valueAnimator;
    }

    /**
     * 开始倒计时
     */

    private boolean isRunning;

    public boolean viewIsRunning() {
        return isRunning;
    }

    ValueAnimator valueAnimator;

    public void stopCountDown() {
        isRunning = false;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        mCurrentProgress = 360;
        invalidate();
    }

    public void startCountDown() {
        setClickable(false);
        valueAnimator = getValA(mCountdownTime * 1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                isRunning = true;
                float i = Float.valueOf(String.valueOf(animation.getAnimatedValue()));
                mCurrentProgress = (int) (360 * (i / 100f));
                invalidate();
            }
        });
        valueAnimator.start();
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isRunning = false;
                //倒计时结束回调
                if (mListener != null) {
                    mListener.countDownFinished();
                }
                setClickable(false);
            }

        });
    }

    public void setAddCountDownListener(OnCountDownFinishListener mListener) {
        this.mListener = mListener;
    }

    public interface OnCountDownFinishListener {
        void countDownFinished();
    }

    public int sp2px(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue * fontScale + 0.5f);
    }

}
