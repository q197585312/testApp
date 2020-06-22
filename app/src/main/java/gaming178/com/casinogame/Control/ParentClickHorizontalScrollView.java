package gaming178.com.casinogame.Control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * 兼容父容器点击事件的HorizontalScrollView
 * <p>
 * created by yc on 2018-12-27
 */
public class ParentClickHorizontalScrollView extends HorizontalScrollView {

    private View parentView;

    public ParentClickHorizontalScrollView(Context context) {
        super(context);
    }

    public ParentClickHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentClickHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public View getParentView() {
        return parentView;
    }

    public void setParentView(View parentView) {
        this.parentView = parentView;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //注意 getParent() 是 ViewParent 但不一定是 View
        if (parentView == null
                && getParent() != null
                && getParent() instanceof View) {
            parentView = (View) getParent();
        }

        if (parentView != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    parentView.onTouchEvent(ev);  //使父容器也能响应本次按下事件
                    break;

                case MotionEvent.ACTION_MOVE: //当触发滑动时，将父容器的按下响应失效
                    //修改动作为ACTION_CANCEL
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    parentView.onTouchEvent(ev);
                    //父容器响应后恢复事件原动作
                    ev.setAction(MotionEvent.ACTION_MOVE);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    parentView.onTouchEvent(ev);  //将松手事件，先行传递给父容器响应
                    break;
            }
        }
        return super.onTouchEvent(ev);  //无论如何 本身都响应事件
    }

}