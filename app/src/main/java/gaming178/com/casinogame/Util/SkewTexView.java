package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import gaming178.com.baccaratgame.R;

/**
 * Created by 商哥哥 on 2017/8/1.
 */

public class SkewTexView extends TextView {
    private TextView outlineTextView = null;

    public SkewTexView(Context context) {
        super(context);

        outlineTextView = new TextView(context);
        init(null);
    }

    public SkewTexView(Context context, AttributeSet attrs) {
        super(context, attrs);

        outlineTextView = new TextView(context, attrs);
        init(attrs);
    }

    public SkewTexView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        outlineTextView = new TextView(context, attrs, defStyle);
        init(attrs);
    }

    public void setSkewSize(float skewSize) {
        this.skewSize = skewSize;
    }

    @Override
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    private float skewSize = 0;
    private int textColor = 0;

    TextPaint paint;

    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SkewTexView);
            if (typedArray != null) {
                this.skewSize = typedArray.getFloat(R.styleable.SkewTexView_skew_x, 0f);
            }
            typedArray.recycle();
        }
        post(new Runnable() {
            @Override
            public void run() {
                paint = outlineTextView.getPaint();
                paint.setStyle(TextPaint.Style.STROKE);
                paint.setTextSkewX(skewSize);
                paint.setStrokeWidth(3);
                outlineTextView.setTextColor(textColor);// 描边颜色
                outlineTextView.setShadowLayer(5F, 5F, 4F, Color.BLACK);
//第一个参数为模糊半径，越大越模糊。 第二个参数是阴影离开文字的x横向距离。 第三个参数是阴影离开文字的Y横向距离。 第四个参数是阴影颜色。
                outlineTextView.setGravity(getGravity());
            }
        });
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        outlineTextView.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 设置轮廓文字
        CharSequence outlineText = getText();
        if (!TextUtils.isEmpty(outlineText)) {
//            outlineTextView.setText(outlineText);
            postInvalidate();
        }
        outlineTextView.measure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        outlineTextView.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        outlineTextView.draw(canvas);
        String text = getText().toString();
        TextPaint textPaint = getPaint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSkewX(skewSize);
        textPaint.setColor(textColor);
        canvas.drawText(text, (getWidth() - textPaint.measureText(text)) / 2,
                getBaseline(), textPaint);
        super.onDraw(canvas);
    }

}
