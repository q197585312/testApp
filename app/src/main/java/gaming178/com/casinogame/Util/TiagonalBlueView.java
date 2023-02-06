package gaming178.com.casinogame.Util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import gaming178.com.baccaratgame.R;

/**
 * Created by 商哥哥 on 2017/7/29.
 */

public class TiagonalBlueView extends View {
    private Paint mPaint;

    public TiagonalBlueView(Context context) {
        super(context);
        init();
    }

    public TiagonalBlueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TiagonalBlueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TiagonalBlueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.blue_height));
        mPaint.setStrokeWidth((float) 2.5);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine((getWidth())/3*2, 0, (getWidth())/3, getHeight(), mPaint);
    }
}
