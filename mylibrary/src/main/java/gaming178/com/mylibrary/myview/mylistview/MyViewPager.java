package gaming178.com.mylibrary.myview.mylistview;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/11/16.
 */
public class MyViewPager extends ViewPager {
    private float mDownX;
    private float mDownY;
    private boolean isHorizontal=false;
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() > 1 && mIsDisallowIntercept) {
            requestDisallowInterceptTouchEvent(false);
            boolean handled = super.dispatchTouchEvent(ev);
            requestDisallowInterceptTouchEvent(true);
            return handled;
        }
        switch (ev.getAction()){

            case  MotionEvent.ACTION_DOWN:
                mDownX=ev.getX();
                mDownY=ev.getY();
                isHorizontal=false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(ev.getX()-mDownX)>Math.abs(ev.getY()-mDownY)) {
                    isHorizontal = true;
                }
                else
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
    private boolean mIsDisallowIntercept = false;
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // keep the info about if the innerViews do
        // requestDisallowInterceptTouchEvent
        mIsDisallowIntercept = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }
}
