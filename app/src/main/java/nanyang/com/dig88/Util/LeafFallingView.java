package nanyang.com.dig88.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nanyang.com.dig88.R;

/**
 * 自定义落叶view
 *
 * @author zhangyu
 * @date 2016-4-1
 * @描述：
 */
public class LeafFallingView extends View {

    private static final String TAG = "LeafFallingView";
    private Context context;
    private Bitmap leafBitmap;
    private Paint bitmapPaint, orangePaint;
    private int viewWidth, viewHeight, leafWidth, leafHeight;
    private Handler handler;
    private List<leafPosition> leafs;
    private Random random = new Random(333);
    private int setCount = 0;        //记录设置次数

    public LeafFallingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LeafFallingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LeafFallingView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        leafBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.red_envelopes);
        leafWidth = leafBitmap.getWidth();
        leafHeight = leafBitmap.getHeight();
        initPaint();
        handler = new Handler();
        leafs = new ArrayList<leafPosition>();
    }

    private void initPaint() {
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        bitmapPaint.setDither(true);
        bitmapPaint.setFilterBitmap(true);

        orangePaint = new Paint();
        orangePaint.setAntiAlias(true);
        orangePaint.setColor(Color.parseColor("#00000000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawOrangeBackground(canvas);
        drawLeaf(canvas);

        super.onDraw(canvas);
    }

    /**
     * 画叶子
     *
     * @param canvas
     */
    private void drawLeaf(Canvas canvas) {
        for (int i = 0; i < leafs.size(); i++) {
            leafPosition leaf = leafs.get(i);
            int x = leaf.x;
            int y = leaf.y;
            int roate = leaf.roate;

            int nowX = 0, nowY = 0;
            canvas.save();
            Matrix matrix = new Matrix();
            matrix.postTranslate(x, y);
            nowX = nowX + x;
            nowY = nowY + y;
            matrix.postRotate(roate % 360, nowX + leafWidth / 2, nowY + leafHeight / 2);
            canvas.drawBitmap(leafBitmap, matrix, bitmapPaint);
            canvas.restore();
        }
    }

    /**
     * 画背景
     *
     * @param canvas
     */
    private void drawOrangeBackground(Canvas canvas) {
        canvas.drawRect(0, 0, viewWidth, viewHeight, orangePaint);
    }

    /**
     * 设置和改变叶子位置和角度参数
     */
    public void setParams() {
        Log.d(TAG, "setParams.. setCount = " + setCount);
        setCount++;

        handler.postDelayed(new Runnable() {
            public void run() {

                long nowTime = System.currentTimeMillis();
                nowTime = (nowTime + setCount) / 3;

                if (setCount % 10 == 0) {

                    float r = random.nextFloat();
                    Log.d(TAG, "r = " + r + ",((r % 100f) / 100f) = " + ((r % 100f) / 100f));
                    // 根据当前时间随机创建 位置、旋转角度不同的叶子
                    leafs.add(new leafPosition((int) (((r * 1000 % 100f) / 100f) * viewWidth), 0, (int) nowTime % 360));
                }

                if (leafs.size() > 300)
                    leafs.remove(0);
                Log.d(TAG, "leafs.size = " + leafs.size());

                for (int i = 0; i < leafs.size(); i++) {
                    leafPosition leaf = leafs.get(i);

                    leaf.y += 8;//y方向每次移动3个像素
                    //根据叶子在集合中的排序位置来产生一个旋转角度(大小方向不完全相同)，并且记录下这个角度下次继续执行，保证叶子转向一致，不来回摆动
                    if (leaf.roateChange == 0)
                        leaf.roateChange = getRoate(i);
                    leaf.roate += leaf.roateChange;
                }
                invalidate();
                if (setCount <= 10000) {// 设置10000次，当然也可以不设置次数无限递归下去
                    setParams();
                } else
                    setCount = 0;
            }
        }, 20);

    }

    /**
     * 返回几种旋转的值
     *
     * @param nowTime
     * @return
     */
    protected int getRoate(long nowTime) {
        switch ((int) (nowTime % 5)) {
            case 0:
                return 3;
            case 1:
                return -3;
            case 2:
                return 4;
            case 3:
                return -5;
            case 4:
                return -4;
            default:
                return 3;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        viewWidth = getWidth();
        viewHeight = getHeight();
        invalidate();
        super.onWindowFocusChanged(hasWindowFocus);
    }

    public void start() {
        setParams();
    }

    public void stop() {
        if (this != null && handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 叶子位置
     *
     * @author zhangyu
     * @date 2016-3-28
     * @描述：
     */
    private class leafPosition {
        public int x = 0;        //位置x坐标信息
        public int y = 0;        //位置y坐标信息
        public int roate = 0;        //角度信息
        public int roateChange = 0;        //角度变化值

        public leafPosition(int x, int y, int roate) {
            this.x = x;
            this.y = y;
            this.roate = roate;
        }
    }
}