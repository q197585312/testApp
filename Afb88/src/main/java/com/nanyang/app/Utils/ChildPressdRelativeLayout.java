package com.nanyang.app.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/11/16.
 */

public class ChildPressdRelativeLayout extends RelativeLayout {
    public ChildPressdRelativeLayout(Context context) {
        super(context);
    }

    public ChildPressdRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildPressdRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ChildPressdRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < getChildCount(); i++) {
                    View v = getChildAt(i);
                    v.setPressed(true);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                for (int i = 0; i < getChildCount(); i++) {
                    View v = getChildAt(i);
                    v.setPressed(false);
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
