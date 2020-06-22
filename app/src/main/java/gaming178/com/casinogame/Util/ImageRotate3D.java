package gaming178.com.casinogame.Util;

import android.graphics.drawable.BitmapDrawable;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;

import gaming178.com.mylibrary.allinone.util.BitmapTool;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ImageRotate3D {
    private int degr = 0;
    private float picCenterY;
    private float picCenterX;
    ImageView img;
    int endRes;

    public ImageRotate3D(ImageView img, int endRes, int degr) {
        this.img = img;
        this.endRes = endRes;
        this.degr = degr;
        picCenterX = img.getWidth() / 2f;
        picCenterY = img.getHeight() / 2f;
    }

    public ImageRotate3D(ImageView img, int endRes) {
        this.img = img;
        this.endRes = endRes;
        this.degr = 0;
        picCenterX = img.getWidth() / 2f;
        picCenterY = img.getHeight() / 2f;
    }

    public void setAnimation3D() {

        // 构建3D旋转动画对象，旋转角度为0到90度，这使得View将会从可见变为不可见
        final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 90, picCenterX, picCenterY,
                310.0f, true);
        // 动画持续时间500毫秒
        rotation.setDuration(300);
        // 动画完成后保持完成的状态
        rotation.setFillAfter(false);
        rotation.setInterpolator(new AccelerateInterpolator());
        // 设置动画的监听器
        rotation.setAnimationListener(new TurnToNext());
        img.startAnimation(rotation);
    }

    class TurnToNext implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ListView的动画完成后，还需要再启动ImageView的动画，让ImageView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {

            // 构建3D旋转动画对象，旋转角度为270到360度，这使得ImageView将会从不可见变为可见
            if (degr == 0)
                img.setImageResource(endRes);
            else {
                img.setImageResource(endRes);
                BitmapDrawable draw = (BitmapDrawable) img.getDrawable();
                if (draw != null)
                    img.setImageBitmap(BitmapTool.toturn(draw.getBitmap(), 90));
            }
            final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, picCenterX, picCenterY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(300);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(false);
            rotation.setInterpolator(new AccelerateInterpolator());
            img.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }
}
