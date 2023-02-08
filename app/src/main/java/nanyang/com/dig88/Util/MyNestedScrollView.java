package nanyang.com.dig88.Util;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.core.widget.NestedScrollView;

/**
 * Created by Administrator on 2019/12/5.
 */

public class MyNestedScrollView extends NestedScrollView {


    public MyNestedScrollView(Context context) {
        super(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        //禁止scrollView内布局变化后自动滚动
        return 0;
    }

}
