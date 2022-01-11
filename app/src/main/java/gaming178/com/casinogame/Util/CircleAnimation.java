package gaming178.com.casinogame.Util;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircleAnimation extends Animation {

    private int radii;

    public CircleAnimation(int radii) {

        this.radii = radii;

    }

    @Override

    protected void applyTransformation(float interpolatedTime, Transformation t) {

        float d = 360 * interpolatedTime ;

        if (d > 360) {

                d = d - 360;

        }

        int[] ps = getNewLocation((int) d, radii);//

        t.getMatrix().setTranslate(ps[0], ps[1]);

    }

    public int[] getNewLocation(int newAngle, int r) {

        int newAngle1;

        int newX = 0, newY = 0;

        if (newAngle >= 0 && newAngle <= 90) {

                newX = (int) (r * Math.sin(newAngle * Math.PI / 180));

            newY = (int) ( - (r * Math.cos(newAngle * Math.PI / 180)));

        } else if (newAngle >= 90 && newAngle <= 180) {// 90-180

            newAngle1 = 180 - newAngle;

            newX = (int) (r * Math.sin(newAngle1 * Math.PI / 180));

            newY = (int) (r * Math.cos(newAngle1 * Math.PI / 180));

        } else if (newAngle >= 180 && newAngle <= 270) {//180-270

            newAngle1 = 270 - newAngle;

            newX = (int) ( - (r * Math.cos(newAngle1 * Math.PI / 180)));

            newY = (int) (r * Math.sin(newAngle1 * Math.PI / 180));

        } else if (newAngle >= 270 && newAngle <= 360) {//270-360

            newAngle1 = 360 - newAngle;

            newX = (int) ( - (r * Math.sin(newAngle1 * Math.PI / 180)));

            newY = (int) ( - (r * Math.cos(newAngle1 * Math.PI / 180)));

        }

        return new int[]{newX, newY};

    }

}
