package gaming178.com.mylibrary.myview.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import gaming178.com.mylibrary.R;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;

public class DiagonalView extends View {
    private Context context;
    private int lineColor;
    private float statCornerX;
    private float endCornerX;
    private float statCornerY;
    private float endCornerY;
    private Paint rimPaint;

    public DiagonalView(Context context) {
        this(context, null);
    }

    public DiagonalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public DiagonalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DiagonalView,
                defStyleAttr, 0);
        lineColor = a.getColor(R.styleable.DiagonalView_line_color, getResources().getColor(R.color.white));
        statCornerX = a.getFloat(R.styleable.DiagonalView_line_corner_startX, 0);//线开始的角X
        endCornerX = a.getFloat(R.styleable.DiagonalView_line_corner_endX, 0);//结束的角X
        statCornerY = a.getFloat(R.styleable.DiagonalView_line_corner_startY, 0);//线开始的角Y
        endCornerY = a.getFloat(R.styleable.DiagonalView_line_corner_endY, 0);//结束的角Y
       /* Drawable background = a.getDrawable(R.styleable.CirclePageIndicator_android_background);
        if (background != null) {
            setBackgroundDrawable(background);
        }*/
        initView();
        a.recycle();


    }

    private void initView() {
        rimPaint = new Paint();
        rimPaint.setStyle(Paint.Style.FILL);
        rimPaint.setStrokeWidth(ScreenUtil.dip2px(context,2));
        rimPaint.setColor(lineColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        //画棋盘的竖线
        //注意第三个参数采用相互约束的方法

            canvas.drawLine( statCornerX*width, statCornerY*height,endCornerX*width, endCornerY*height , rimPaint);



    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //不管三七二十一先获取这些数据，由此可见onMeasure（）这个函数的参数包含
        //尺寸和模式两种信息，尺寸就是父容器剩余给我们的最大空间，也就是match_parent的尺寸
        //模式是父容器希望我们的排版模式
        //为了保证是正方形我们将长度跟宽度设置成一样，而且是最小的

        int wMode=MeasureSpec.getMode(widthMeasureSpec);
        int wSize=MeasureSpec.getSize(widthMeasureSpec);
        int hMode=MeasureSpec.getMode(heightMeasureSpec);
        int hSize=MeasureSpec.getSize(heightMeasureSpec);
        int endSize=wSize<=hSize? wSize : hSize;
        //这里的模式最常用的就是两种
        //一是AT_MOST,对应布局文件中的wrap_content,需要我们计算大小
        //一种是EXACTLY,对应布局中的match_parent
        switch (wMode)
        {
            case (MeasureSpec.EXACTLY):
                break;
            case (MeasureSpec.AT_MOST):
                wSize=endSize;
                break;
        }
        switch (hMode)
        {
            case(MeasureSpec.EXACTLY):
                break;
            case(MeasureSpec.AT_MOST):
                hSize=endSize;
                break;
        }
        //注意参数的类型
        //更不要忘记进行数据的更新
        setMeasuredDimension(wSize,hSize);
    }
}
