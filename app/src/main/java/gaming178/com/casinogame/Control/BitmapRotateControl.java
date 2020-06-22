package gaming178.com.casinogame.Control;

import android.content.Context;
import android.view.MotionEvent;

import com.almeros.android.multitouch.RotateGestureDetector;


/**
 * Created by Administrator on 2016/8/24.
 */
public class BitmapRotateControl {
    private RotateCallBack back;

    public float getmRotationDegrees() {
        return mRotationDegrees;
    }
    private float mRotationDegrees = 0.f;

    private RotateGestureDetector mRotateDetector;

    public BitmapRotateControl(Context context ,RotateCallBack back) {
        mRotateDetector = new RotateGestureDetector(context, new RotateListener());
        this.back=back;
    }

    public void setRotateEvent(MotionEvent event){
        mRotateDetector.onTouchEvent(event);

    }
    private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener {
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            mRotationDegrees -= detector.getRotationDegreesDelta();
            if(back!=null)
                back.onRotate(mRotationDegrees);
            return true;
        }
    }
    interface  RotateCallBack{
        void onRotate(float mRotationDegrees);
    }

}
