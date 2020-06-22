package gaming178.com.mylibrary.myview.GroupView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ClickableHorizontalScrollView extends HorizontalScrollView {
    public ClickableHorizontalScrollView(Context context) {
        super(context);
    }

    public ClickableHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float mDX, mDY, mLX, mLY;

    boolean isClick = true;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDX = mDY = 0f;
                mLX = ev.getX();
                mLY = ev.getY();
                Log.v("Touch", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                mDX += Math.abs(curX - mLX);
                mDY += Math.abs(curY - mLY);
                mLX = curX;
                mLY = curY;
                if (mDX < 3) {
                    isClick = true;
                } else {
                    isClick = false;
                }
                Log.v("Touch", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.v("Touch", "ACTION_UP");
                break;
        }
        Log.v("Touch", "isClick=" + isClick);
        return isClick;
    }

}
