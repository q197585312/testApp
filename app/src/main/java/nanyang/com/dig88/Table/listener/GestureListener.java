package nanyang.com.dig88.Table.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/11/22.
 */
public class GestureListener implements GestureDetector.OnGestureListener {

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getX()-e2.getX() > SnsConstant.getFlingMinDistance()
                && Math.abs(velocityX) > SnsConstant.getFlingMinVelocity()) {
            return true;
        } else if (e2.getX()-e1.getX() > SnsConstant.getFlingMinDistance()
                && Math.abs(velocityX) > SnsConstant.getFlingMinVelocity()) {
            return true;
        }

        return false;
    }

    public static class SnsConstant {
        private static final int FLING_MIN_DISTANCE = 50;
        private static final int FLING_MIN_VELOCITY = 0;

        public static int getFlingMinDistance() {
            return FLING_MIN_DISTANCE;
        }
        public static int getFlingMinVelocity() {
            return FLING_MIN_VELOCITY;
        }
    }

}
