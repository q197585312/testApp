package nanyang.com.dig88.Lottery4D.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/11/16.
 */

public class RadioImageButton extends RadioButton {

    int img;

    public RadioImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        getData(context, attrs);
    }

    public RadioImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getData(context, attrs);
    }

    private void getData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RadioImageButton);
        img = typedArray.getResourceId(R.styleable.RadioImageButton_img, R.mipmap.imgpool_1);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        Bitmap image = getBitmap();
        if (image != null) {
            Paint pt = new Paint();
            pt.setAntiAlias(true);
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int width = getWidth();
            int height = getHeight();
            int imageX = (width - imageWidth) / 2;
            int imageY = (height - imageHeight) / 2;
            canvas.drawBitmap(image, imageX, imageY, pt);
            image.recycle();
            image = null;
            System.gc();
        }
    }

    private Bitmap getBitmap() {
        BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
        factoryOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), img, factoryOptions);
        int imageWidth = factoryOptions.outWidth;
        int imageHeight = factoryOptions.outHeight;
        float width = getWidth();
        float height = getHeight() / 5 * 4;
        factoryOptions.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), img);
        int w = (int) (imageWidth / (imageHeight / height));
        Bitmap thumbImgNow = Bitmap.createScaledBitmap(bitmap, w, (int) height, true);
        return thumbImgNow;
    }
}
