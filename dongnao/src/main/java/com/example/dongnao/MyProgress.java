package com.example.dongnao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/6.
 */

public class MyProgress extends View {
    private final Paint mPaint;

    public MyProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
