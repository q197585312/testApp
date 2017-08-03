package com.example.dongnao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/8/2.
 */

public class PaintView extends View {
    private Paint mPaint;
    private String str="sdsar32sdj周末还问速度加速。。。，，，,,,&3250596(^"
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);//反锯齿 画图片不用 会损失性能
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //view 会绘制多次 每次需要重置
        mPaint.reset();
      /*  mPaint.setColor(Color.RED);
        mPaint.setAlpha(255);
        mPaint.setStyle(Paint.Style.FILL);//填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//填充和秒边
        mPaint.setStyle(Paint.Style.STROKE);//填充和秒边
        mPaint.setStrokeWidth(sds);
        //设置笔尖
        mPaint.setStrokeCap(Paint.Cap.BUTT);//线 帽 没有
        mPaint.setStrokeCap(Paint.Cap.ROUND);//线 帽 圆形
        mPaint.setStrokeCap(Paint.Cap.SQUARE);//线 帽 方形
        //设置线段连接交汇的样式
        mPaint.setStrokeJoin(Paint.Join.ROUND);//锐角
        mPaint.setStrokeJoin(Paint.Join.BEVEL);//圆弧
        mPaint.setStrokeJoin(Paint.Join.MITER);//直线

        *//*上面是设置画笔属性*//*
        canvas.drawCircle();
        Path path=new Path();
        path.moveTo();
        path.lineTo();
        canvas.drawPath(path,mPaint);

        //2，文字绘制
        mPaint.getFontSpacing();//获取字符行间距
//        获取字符之间的间距
        mPaint.getLetterSpacing();
        mPaint.setLetterSpacing(11);
        //设置文章删除线
        mPaint.setStrikeThruText(true);
        //设置文字下划线
        mPaint.setUnderlineText(true);
        //
        mPaint.setTextSize(11);
        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        mPaint.setTextSkewX(-0.5f);//设置文章倾斜
        mPaint.setTextAlign(Paint.Align.LEFT);//文本对其方式
        //计算制定字符的长度
        mPaint.breakText()*/
        mPaint.setTextSize(12);
        float[] ints = new float[10];
        int i = mPaint.breakText(str, true, Integer.MAX_VALUE, ints);
        //i 是中文宽度的 字符个数 ints[0]总共字符的长度
        Log.d("XS","str.length()="+str.length()+""+ints[0]);
        Rect rect=new Rect();
        mPaint.getTextBounds(str,1,12,rect);//获取指定文字的矩形区域
        float v = mPaint.measureText(str);//获取文本宽度 得到一个粗略结果
        mPaint.getTextWidths()//获取文本宽度 一个精准的结果

        //字符基线
         canvas.drawText(str,0,0,mPaint);//是文字的基线跟y对其
    }
}
