package com.nanyang.app.Utils;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/11/16.
 */

public class ChildPressdLinearLayout extends LinearLayout {
    public ChildPressdLinearLayout(Context context) {
        super(context);
    }

    public ChildPressdLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildPressdLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ChildPressdLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
