package com.example.dongnao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/2.
 */

public class PaintView extends View {
    private Paint mPaint;

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //view 会绘制多次 每次需要重置
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(255);
        mPaint.setStyle(Paint.Style.FILL);//填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//填充和秒边
        mPaint.setStyle(Paint.Style.STROKE);//填充和秒边
        mPaint.setStrokeWidth(sds);
        //设置笔尖
        mPaint.setStrokeCap(Paint.Cap.BUTT);//线 帽 没有
        mPaint.setStrokeCap(Paint.Cap.ROUND);//线 帽 圆形
        mPaint.setStrokeCap(Paint.Cap.SQUARE);//线 帽 方形
        //设置交汇
        mPaint.setStrokeJoin(Paint.Join.ROUND);//锐角
        mPaint.setStrokeJoin(Paint.Join.BEVEL);//圆弧
        mPaint.setStrokeJoin(Paint.Join.MITER);//直线

        /*上面是设置画笔属性*/
        canvas.drawCircle();
        Path path=new Path();
        path.moveTo();
        path.lineTo();
        canvas.drawPath(path,mPaint);


    }
}
