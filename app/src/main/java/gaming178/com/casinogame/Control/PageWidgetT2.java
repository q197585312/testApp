package gaming178.com.casinogame.Control;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Scroller;

import gaming178.com.casinogame.Util.Rotate3dAnimation;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;


public class PageWidgetT2 extends ImageView {
    private static final int MODE_ROTATE = 0x000;// 旋转模式
    private static final int MODE_FLIP = 0x001;// 默认的触摸模式
    private static final int MODE_NONE = 0x002;// 默认的触摸模式
    private static final String TAG = "hmg";
    private static final float PART_PICTURE_HEIGHT = 18 / 32F;
    private static final float PART_PICTURE_WIDTH = 10F / 34F;
    private static final float PART_PADING_LEFT = 1F / 2F;
    private static final float PART_PADING_RIGHT = 1 - PART_PADING_LEFT;
    private static final float PART_PADING_TOP = 14F / 15F;
    private static final float PART_PADING_BOTTOM = 1 - PART_PADING_TOP;
    Bitmap mCurPageBitmap = null; // 当前页
    Bitmap mNextPageBitmap = null;
    PointF mTouch = new PointF(); // 拖拽点
    PointF pointH = new PointF();
    PointF pointE = new PointF(); // 贝塞尔曲线控制点
    /**
     * 中间点x
     */
    float gX;
    /**
     * 中间点y
     */
    float gY;
    float mDegrees;
    float mTouchToCornerDis;
    Paint paint;
    Paint mPaint;
    float mDX1;
    float mDY1;
    float mDX0;
    float mDY0;
    float mLX0;
    float mLY0;
    float mLX1;
    float mLY1;
    float mDX;
    float mDY;
    float mLX;
    float mLY;
    long time1 = 0;
    long time0 = 0;
    private Context context;
    private int currentMode = MODE_ROTATE;//当前的触摸模式
    private Matrix mMatrix = new Matrix();
    private float previousPadingLeft;
    private float previousPadingTop;
    private boolean isRotate = false;
    private RectF mPicDropRectF;
    private float picCenterY;
    private float picCenterX;
    /*PointF pointC = new PointF(); // 贝塞尔曲线起始点
    PointF pointD = new PointF(); // 贝塞尔曲线顶点
	PointF pointB = new PointF(); // 贝塞尔曲线结束点
	PointF pointJ = new PointF(); // 另一条贝塞尔曲线
	PointF pointI = new PointF();
	PointF pointK = new PointF();*/
    private Rotate3dAnimation rotation;
    //    private BitmapRotateControl controlRotate;
    private float mRotationDegrees = 0.f;
    private int currentAngle;
    private int beginAngle;
    private int distanceMin;
    private int pointerId = 1;
    private long minTime = 200;
    private flipCallBack flipCallBack;
    private Scroller mScroller;
    private int mWidth = 400;
    private int mHeight = 800;
    private float mCornerX0 = 0; // 拖拽点对应的页脚
    private float mCornerY0 = 0;
    private float mCornerX1 = 0; // 拖拽点对应的页脚
    private float mCornerY1 = 0;
    private float mCornerX = 0;
    private float mCornerY = 0;
    /**
     * 背面和下页
     */
    private Path mBackAndNextPath0;
    /**
     * 下页
     */
    private Path mNextPath1;
    private Bitmap mBitmap;
    private Paint mBitmapPaint;
    private int mPicWidth = 480;
    private int mPicHeight = 800;
    private RectF mPicRectF;
    private float picPadingLeft;
    private float picPadingRight;
    private float picPadingTop;
    private float picPadingBottom;
    private boolean isSlide = false;
    private boolean isFlipY = false;
    private boolean isFlipX = false;
    private Bitmap tempCurPageBitmap;
    private Bitmap tempNextPageBitmap;
    private boolean canNotFlip = true;
    private boolean hasFlip = false;
    private SideCenter side = SideCenter.None;
    public PageWidgetT2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    public PageWidgetT2(Context context) {
        super(context);
        this.context = context;
        // TODO Auto-generated constructor stub
        init();
    }

    public void setFlipCallBack(gaming178.com.casinogame.Control.flipCallBack flipCallBack) {
        this.flipCallBack = flipCallBack;
    }

    public boolean isCanNotFlip() {
        return canNotFlip;
    }

    public void setCanNotFlip(boolean canNotFlip) {
        this.canNotFlip = canNotFlip;
    }

    public boolean isHasFlip() {
        return hasFlip;
    }

    public void init() {
        distanceMin = ScreenUtil.dip2px(context, 10);
        mBackAndNextPath0 = new Path();
        mNextPath1 = new Path();
        // ---------------------------------------
//
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        paint = new Paint();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mScroller = new Scroller(getContext());
  /*      controlRotate = new BitmapRotateControl(context, new BitmapRotateControl.RotateCallBack() {
            @Override
            public void onRotate(float mRotationDegrees) {
                PageWidgetT.this.mRotationDegrees = mRotationDegrees;
                Log.d("MotionEvent", "mRotationDegrees:---------->" + mRotationDegrees);
            }
        });*/
    }

    public void setImageRes(int curRes, int nextRes) {
        setImageCurRes(curRes);
        setImageNextRes(nextRes);

        invalidate();
    }

    public void setImageCurRes(int curRes) {
        tempCurPageBitmap = BitmapFactory.decodeResource(this.getResources(),
                curRes);
        tempCurPageBitmap = Bitmap.createScaledBitmap(tempCurPageBitmap, mPicWidth, mPicHeight, true);

        mCurPageBitmap = tempCurPageBitmap;

    }

    public void setImageNextRes(int nextRes) {
        tempNextPageBitmap = BitmapFactory.decodeResource(this.getResources(),
                nextRes);
        tempNextPageBitmap = Bitmap.createScaledBitmap(tempNextPageBitmap, mPicWidth, mPicHeight, true);
        mNextPageBitmap = tempNextPageBitmap;
    }

    /**
     * 计算拖拽点对应的拖拽脚
     */
    private void calcCornerXY(MotionEvent event) {
        Log.d(TAG, "mPicRegion" + "(" + mPicRectF.left + "," + mPicRectF.top + "," + mPicRectF.right + "," +
                mPicRectF.bottom + ")");
        if (event.getPointerCount() < 2)
            return;
        float x0 = event.getX(0);
        float y0 = event.getY(0);
        float x1 = event.getX(1);
        float y1 = event.getY(1);

        if (x0 <= picPadingLeft + mPicWidth / 2)
            mCornerX0 = mPicRectF.left;
        else
            mCornerX0 = mPicRectF.right;
        if (y0 <= picPadingTop + mPicHeight / 2)
            mCornerY0 = mPicRectF.top;
        else
            mCornerY0 = mPicRectF.bottom;
        if (x1 <= picPadingLeft + mPicWidth / 2)
            mCornerX1 = mPicRectF.left;
        else
            mCornerX1 = mPicRectF.right;
        if (y1 <= picPadingTop + mPicHeight / 2)
            mCornerY1 = mPicRectF.top;
        else
            mCornerY1 = mPicRectF.bottom;
        side = SideCenter.None;
        currentMode = MODE_FLIP;
/*        if (x > picPadingLeft + mPicWidth / 3F && x < picPadingLeft + 2 * mPicWidth / 3F) {
            if (y < picPadingTop + mPicHeight / 3F || y > picPadingTop + 2 * mPicHeight / 3F)
                side = SideCenter.Vertical;
        } else if (x < picPadingLeft + mPicWidth / 3F || x > picPadingLeft + 2 * mPicWidth / 3F)
            if (y > picPadingTop + mPicHeight / 3F && y < picPadingTop + 2 * mPicHeight / 3F)
                side = SideCenter.Horizontal;*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (isSlide || canNotFlip || hasFlip)
            return super.onTouchEvent(event);
        int action = event.getAction() & MotionEvent.ACTION_MASK;

        switch (action) {
            case MotionEvent.ACTION_DOWN:

      /*          mTouch.x = event.getX();
                mTouch.y = event.getY();*/

                if (!mPicDropRectF.contains((int) event.getX(), (int) event.getY())) {
                    return false;
                }
                beginAngle = computeCurrentAngle(event.getX(), event.getY());
                mDX = mDY = 0f;

                mLX = event.getX();
                mLY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:

                if (currentMode == MODE_FLIP) {
                    final float curX0 = event.getX(0);
                    final float curY0 = event.getY(0);
                    mDX0 += Math.abs(curX0 - mLX0);
                    mDY0 += Math.abs(curY0 - mLY0);
                    mLX0 = curX0;
                    mLY0 = curY0;

                    final float curX1 = event.getX(1);
                    final float curY1 = event.getY(1);
                    mDX1 += Math.abs(curX1 - mLX1);
                    mDY1 += Math.abs(curY1 - mLY1);
                    mLX1 = curX1;
                    mLY1 = curY1;

                    if (mCornerX == 0 && mCornerY == 0) {
                        if (mCornerY0 != mCornerY1 && mCornerX0 != mCornerX1) {
                            side = SideCenter.None;
                            if (Math.abs(mDY0) > distanceMin || Math.abs(mDX0) > distanceMin) {
                                mCornerX = mCornerX0;
                                mCornerY = mCornerY0;
                                pointerId = 0;
                            } else if (Math.abs(mDY1) > distanceMin || Math.abs(mDX1) > distanceMin) {
                                mCornerX = mCornerX1;
                                mCornerY = mCornerY1;
                                pointerId = 1;
                            } else {
                                return true;
                            }
                        } else if (mCornerY0 == mCornerY1) {
                            if (Math.abs(mDY1) <= distanceMin && Math.abs(mDY0) <= distanceMin) {
                                return true;
                            } else {
                                if (Math.abs(mDY1) > distanceMin) {
                                    if (time1 == 0) {
                                        time1 = System.currentTimeMillis();
                                    }

                                }
                                if (Math.abs(mDY0) > distanceMin) {
                                    if (time0 == 0) {
                                        time0 = System.currentTimeMillis();
                                    }
                                }
                                if (time1 != 0 && time0 == 0) {
                                    if (System.currentTimeMillis() - time1 > minTime) {
                                        side = SideCenter.None;
                                        mCornerX = mCornerX1;
                                        mCornerY = mCornerY1;
                                        pointerId = 1;
                                    } else {
                                        return true;
                                    }
                                } else if (time1 == 0 && time0 != 0) {
                                    if (System.currentTimeMillis() - time0 > minTime) {
                                        mCornerX = mCornerX0;
                                        mCornerY = mCornerY0;
                                        pointerId = 0;
                                    } else {
                                        return true;
                                    }
                                } else {
                                    side = SideCenter.Vertical;
                                    mCornerX = mCornerX0;
                                    mCornerY = mCornerY0;
                                    pointerId = 0;
                                }

                            }

                        } else if (mCornerX0 == mCornerX1) {

                            if (Math.abs(mDX1) <= distanceMin && Math.abs(mDX0) <= distanceMin) {
                                return true;
                            } else {
                                if (Math.abs(mDX1) > distanceMin) {
                                    if (time1 == 0) {
                                        time1 = System.currentTimeMillis();
                                    }

                                }
                                if (Math.abs(mDX0) > distanceMin) {
                                    if (time0 == 0) {
                                        time0 = System.currentTimeMillis();
                                    }
                                }
                                if (time1 != 0 && time0 == 0) {
                                    if (System.currentTimeMillis() - time1 > minTime) {
                                        side = SideCenter.None;
                                        mCornerX = mCornerX1;
                                        mCornerY = mCornerY1;
                                        pointerId = 1;
                                    } else {
                                        return true;
                                    }
                                } else if (time1 == 0 && time0 != 0) {
                                    if (System.currentTimeMillis() - time0 > minTime) {
                                        mCornerX = mCornerX0;
                                        mCornerY = mCornerY0;
                                        pointerId = 0;
                                    } else {
                                        return true;
                                    }
                                } else {
                                    side = SideCenter.Horizontal;
                                    mCornerX = mCornerX0;
                                    mCornerY = mCornerY0;
                                    pointerId = 0;
                                }

                            }

                        } else {
                            side = SideCenter.None;
                        }


                    }
                    if (Math.abs(event.getX(pointerId) - mCornerX) <= 2 * Math.abs(event.getX(1 - pointerId) - mCornerX)) {
                        mTouch.x = event.getX(pointerId);
                    } else {
                        mTouch.x = 2*event.getX(1 - pointerId) - mCornerX;
                    }
                    if (Math.abs(event.getY(pointerId) - mCornerY) <= 2 * Math.abs(event.getY(1 - pointerId) - mCornerY)) {
                        mTouch.y = event.getY(pointerId);

                    } else {
                        mTouch.y = 2*event.getY(1 - pointerId) - mCornerY;
                    }

                    if (side == SideCenter.Horizontal) {
                        mTouch.x = event.getX(0);

                        if (Math.abs(event.getX(1) - mCornerX) > Math.abs(event.getX(0) - mCornerX)) {
                            mTouch.x = event.getX(1);

                        }
                    } else if (side == SideCenter.Vertical) {
                        mTouch.y = event.getY(0);
                        if (Math.abs(event.getY(1) - mCornerY) > Math.abs(event.getY(0) - mCornerY)) {
                            mTouch.y = event.getY(1);
                        }
                    }


                  /*  if (side == SideCenter.Vertical) {
                        mTouch.x = mCornerX;
                    }
                    else if (side == SideCenter.Horizontal) {
                        mTouch.y = mCornerY;
                    }*/
                 /*       if (side == SideCenter.None && mTouch.y ==event.getY(0)&&mTouch.x==event.getX(0)) {
                            mTouch.y = event.getY(pointerId);
                            mTouch.x = event.getX(pointerId);
                        }
*/

                } else if (currentMode == MODE_ROTATE) {
                    final float curX = event.getX();
                    final float curY = event.getY();
                    mDX += Math.abs(curX - mLX);
                    mDY += Math.abs(curY - mLY);
                    mLX = curX;
                    mLY = curY;

               /*     mTouch.x = event.getX();
                    mTouch.y = event.getY();*/
                    if (mDX > distanceMin || mDY > distanceMin) {
                        currentAngle = computeCurrentAngle(event.getX(), event.getY());
                        mRotationDegrees = currentAngle - beginAngle;
                    }
                    Log.d("PageWidgetT", "currentAngle:" + currentAngle);
                }

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
       /*         if (!mPicDropRectF.contains((int) event.getX(1), (int) event.getY(1))) {
                    return false;
                }*/
                if (currentMode == MODE_ROTATE) {
                    correctRotationDegrees();
                    justSlide();
                }

                resetPath();
                calcCornerXY(event);
                mDX0 = mDY0 = 0f;
                mDX1 = mDY1 = 0f;
                mLX0 = event.getX(0);
                mLY0 = event.getY(0);
                mLX1 = event.getX(1);
                mLY1 = event.getY(1);
                time0 = 0;
                time1 = 0;


                break;


            case MotionEvent.ACTION_POINTER_UP:
                if (currentMode == MODE_FLIP) {
                    justSlide();
                }
                currentMode = MODE_NONE;
                resetPath();
                break;
            case MotionEvent.ACTION_UP:

                if (currentMode == MODE_ROTATE) {
                    correctRotationDegrees();

                } else if (currentMode == MODE_FLIP)
                    justSlide();
                if (!isRotate) {
                    currentMode = MODE_ROTATE;
                } else {
                    currentMode = MODE_NONE;
                }
                resetPath();
                break;

        }

        if (event.getPointerCount() > 1) {
            Log.w("PageWidgetT", "x:" + mTouch.x + ",y:" + mTouch.y);
            Log.w("PageWidgetT", "x0:" + event.getX(0) + ",y0:" + event.getY(0));
            Log.w("PageWidgetT", "x1:" + event.getX(1) + ",y1:" + event.getY(1));
            Log.w("PageWidgetT", "mCornerX:" + mCornerX + ",mCornerY:" + mCornerY);
            Log.w("PageWidgetT", "side:" + side.toString());
            Log.w("PageWidgetT", "mode:" + currentMode);
            Log.w("PageWidgetT", "pointerId:" + pointerId);
        }


        postInvalidate();
        return true;
    }

    public void setBitmapRotate() {
        mMatrix.reset();
        mMatrix.postRotate(mRotationDegrees, picCenterX, picCenterY);
        mNextPageBitmap = Bitmap.createBitmap(tempNextPageBitmap, 0, 0, tempNextPageBitmap.getWidth(), tempNextPageBitmap.getHeight(), mMatrix, true);
//        tempNextPageBitmap = Bitmap.createBitmap(tempNextPageBitmap, 0, 0, tempNextPageBitmap.getWidth(),tempNextPageBitmap.getHeight(), mMatrix, true);
        reSetSize();
        isRotate = true;

    }

    private void correctRotationDegrees() {
        Log.d("PageWidgetT", "mRotationDegrees:" + mRotationDegrees);
        if (mRotationDegrees < 0) {
            mRotationDegrees = -mRotationDegrees;
        }
        if (mRotationDegrees < 45 || mRotationDegrees >= 360 || (mRotationDegrees <= 225 && mRotationDegrees > 135))
            mRotationDegrees = 0;
        else {
            mRotationDegrees = 90;
            setBitmapRotate();
        }
        Log.d("PageWidgetT", "changeRotationDegrees:" + mRotationDegrees);
    }

    /**
     * 计算两个触摸点间的距离
     */
    private float calSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 计算两个触摸点的中点坐标
     */
    private void calMidPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * 计算旋转角度
     *
     * @param event
     * @return 角度值
     */
    private float calRotation(MotionEvent event) {
        double deltaX = (event.getX(0) - event.getX(1));
        double deltaY = (event.getY(0) - event.getY(1));
        double radius = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radius);
    }

    private void justSlide() {
        startAnimation(500);
    }

    private void startAnimation(int delayMillis) {
        isSlide = true;
        int dx = 0, dy = 0;
        // dx 水平方向滑动的距离，负值会使滚动向左滚动
        // dy 垂直方向滑动的距离，负值会使滚动向上滚动
        if (mCornerX == mPicRectF.right) {
            if (mTouch.x < mPicRectF.right && mTouch.x - mPicRectF.left <= mPicRectF.right - mTouch.x) {
                isFlipX = true;
                Log.w("hmg", "right--isFlipX--" + isFlipX);
                dx = -(int) (mTouch.x - mPicRectF.left + mPicWidth);
            } else {
                isFlipX = false;
                Log.w("hmg", "right--isFlipX--" + isFlipX);
                dx = (int) (mPicRectF.right - mTouch.x);
            }
        } else if (mCornerX == mPicRectF.left) {
            if (mTouch.x > mPicRectF.left && mPicRectF.right - mTouch.x <= mTouch.x - mPicRectF.left) {
                isFlipX = true;
                Log.w("hmg", "left--isFlipX--" + isFlipX);
                dx = (int) (mPicRectF.right - mTouch.x + mPicWidth);
            } else {
                isFlipX = false;
                Log.w("hmg", "left--isFlipX--" + isFlipX);
                dx = (int) (mPicRectF.left - mTouch.x);
            }
        }
        if (mCornerY == mPicRectF.bottom) {
            if (mTouch.y < mPicRectF.bottom && mTouch.y - mPicRectF.top <= mPicRectF.bottom - mTouch.y) {
                isFlipY = true;
                Log.w("hmg", "bottom--isFlipY--" + isFlipY);
                dy = (int) -(mTouch.y - mPicRectF.top + mPicHeight);
            } else {
                isFlipY = false;
                Log.w("hmg", "bottom--isFlipY--" + isFlipY);
                dy = (int) (mPicRectF.bottom - mTouch.y);
            }
        } else if (mCornerY == mPicRectF.top) {
            if (mTouch.y > mPicRectF.top && mPicRectF.bottom - mTouch.y <= mTouch.y - mPicRectF.top) {
                isFlipY = true;
                Log.w("hmg", "top--isFlipY--" + isFlipY);
                dy = (int) (mPicRectF.bottom - mTouch.y + mPicHeight);
            } else {
                isFlipY = false;
                Log.w("hmg", "top--isFlipY--" + isFlipY);
                dy = (int) (mPicRectF.top - mTouch.y);
            }
        }
        if (!reSetPic())
            mScroller.startScroll((int) mTouch.x, (int) mTouch.y, dx, dy,
                    delayMillis);

    }

    public void computeScroll() {
        float x = 0;
        float y = 0;
        boolean isSlideX = false;
        boolean isSlideY = false;
        if (mScroller.computeScrollOffset()) {
            if (x != mScroller.getCurrX()) {
                isSlideX = true;
                x = mScroller.getCurrX();
            }
            if (y != mScroller.getCurrY()) {
                y = mScroller.getCurrY();
                isSlideY = true;
            }
            if (isSlideY || isSlideX) {
                mTouch.x = x;
                mTouch.y = y;
                postInvalidate();

            }
        }
        isSlide = false;
    }

    private boolean reSetPic() {
        Log.w("hmg", "hasFlip--" + hasFlip);
        Log.w("hmg", "isFlipY--" + isFlipY + " isFlipX--" + isFlipX);
        if (!hasFlip) {
            if (isFlipX || isFlipY) {
                flipPic();
                return true;
            }
        }
        return false;
    }

    public void flipPic() {
        if (tempNextPageBitmap != null && !hasFlip) {
            mCurPageBitmap = mNextPageBitmap;
            hasFlip = true;
            if (flipCallBack != null)
                flipCallBack.hasFlip();
            refresh();
        }
    }

    public void flipPicAnimation3D() {
        if (tempNextPageBitmap != null && !hasFlip) {
            setAnimation3D();
        }
    }

    public void refresh() {
        resetPath();
        mRotationDegrees = 0;
        previousPadingTop = picPadingTop;
        previousPadingLeft = picPadingLeft;
        invalidate();
    }

    private void resetPath() {

        mDX0 = 0f;
        mDY0 = 0f;
        mDX1 = 0;
        mDY1 = 0;
        mDX = 0;
        mDY = 0;

        mCornerX = 0;
        mCornerY = 0;
        mTouch.x = mCornerX;
        mTouch.y = mCornerY;
        mNextPath1.reset();
        mBackAndNextPath0.reset();
    }

    public void setAnimation3D() {
        // 获取布局的中心点位置，作为旋转的中心点
        hasFlip = true;

        if (flipCallBack != null)
            flipCallBack.hasFlip();
        refresh();

        // 构建3D旋转动画对象，旋转角度为0到90度，这使得View将会从可见变为不可见
        rotation = new Rotate3dAnimation(0, 90, picCenterX, picCenterY,
                360.0f, true);
        // 动画持续时间500毫秒
        rotation.setDuration(300);
        // 动画完成后保持完成的状态
        rotation.setFillAfter(false);
//		rotation.setInterpolator(new AccelerateInterpolator());
        // 设置动画的监听器
        if (getVisibility() == View.VISIBLE)
            rotation.setAnimationListener(new TurnToNext());
        startAnimation(rotation);
    }

    public void coverPic() {
        initSize();
        if (tempCurPageBitmap != null && hasFlip) {
            mCurPageBitmap = Bitmap.createScaledBitmap(tempCurPageBitmap, mPicWidth, mPicHeight, true);
            resetPath();
            hasFlip = false;
            isFlipX = false;
            isFlipY = false;
            invalidate();
        }
    }

    /**
     * Description : 求解直线P1P2和直线P3P4的交点坐标
     */
    public PointF getCross(PointF P1, PointF P2, PointF P3, PointF P4) {
        PointF CrossP = new PointF();
        // 二元函数通式： y=ax+b
        float a1 = (P2.y - P1.y) / (P2.x - P1.x);
        float b1 = ((P1.x * P2.y) - (P2.x * P1.y)) / (P1.x - P2.x);

        float a2 = (P4.y - P3.y) / (P4.x - P3.x);
        float b2 = ((P3.x * P4.y) - (P4.x * P3.y)) / (P3.x - P4.x);
        CrossP.x = (b2 - b1) / (a1 - a2);
        CrossP.y = a1 * CrossP.x + b1;
        return CrossP;
    }

    /**
     * 各个点的位置
     */
    private void calcPoints() {
        if (mPicRectF == null)
            return;
        if (side == SideCenter.Horizontal) {
            mTouch.y = mCornerY;
        } else if (side == SideCenter.Vertical) {
            mTouch.x = mCornerX;
        }
        if (mCornerX == mPicRectF.left) {
            if (mTouch.x <= mPicRectF.left) {
                mTouch.x = mPicRectF.left + 0.1F;
            }
        } else {
            if (mTouch.x >= mPicRectF.right) {
                mTouch.x = mPicRectF.right - 0.1F;
            }
        }
        if (mCornerY == mPicRectF.top) {
            if (mTouch.y <= mPicRectF.top) {
                mTouch.y = mPicRectF.top + 0.1F;
            }
        } else {
            if (mTouch.y >= mPicRectF.bottom) {
                mTouch.y = mPicRectF.bottom - 0.1F;
            }
        }

        gX = (mTouch.x + mCornerX) / 2;
        gY = (mTouch.y + mCornerY) / 2;

        float gm = mCornerY - gY;
        float mf = mCornerX - gX;
//		if(mPicRegion.contains((int)gX,(int)gY)){
        float centerX = (mPicRectF.left + mPicRectF.right) / 2F;
        float centerY = (mPicRectF.top + mPicRectF.bottom) / 2F;
        if (mf == 0) {

            pointE.x = 2 * centerX - mCornerX;
            pointE.y = mTouch.y;
            pointH.x = 2 * centerX - mCornerX;
            pointH.y = mCornerY;
            mDegrees = (float) Math.toDegrees(Math.atan2(pointE.x
                    - mCornerX, 0));
        } else if (gm == 0) {
            pointE.x = mTouch.x;
            pointE.y = 2 * centerY - mCornerY;
            pointH.x = mCornerX;
            pointH.y = 2 * centerY - mCornerY;
            mDegrees = (float) Math.toDegrees(Math.atan2(0, pointH.y - mCornerY));

        } else {
            float em = gm * gm / mf;

            pointE.x = gX - em;
            pointE.y = mCornerY;
            pointH.x = mCornerX;
            pointH.y = gY - (mCornerX - gX)
                    * (mCornerX - gX) / (mCornerY - gY);

            mDegrees = (float) Math.toDegrees(Math.atan2(pointE.x
                    - mCornerX, pointH.y - mCornerY));

        }
        mTouchToCornerDis = (float) Math.hypot((mTouch.x - mCornerX),
                (mTouch.y - mCornerY));
//		}

    }

    private void drawCurrentPageArea(Canvas canvas, Bitmap bitmap/*, Path path*/) {

        mBackAndNextPath0.reset();
        if (currentMode == MODE_FLIP && mCornerX != 0 && mCornerY != 0) {
            mBackAndNextPath0.moveTo(mTouch.x, mTouch.y);
            mBackAndNextPath0.lineTo(pointE.x, pointE.y);
            mBackAndNextPath0.lineTo(mCornerX, mCornerY);
            mBackAndNextPath0.lineTo(pointH.x, pointH.y);
        }
        mBackAndNextPath0.close();
        canvas.save();
        if (side == SideCenter.Vertical) {
            Log.d("PageWidgetT", "------>Vertical:" + ",x:" + mTouch.x + ",y:" + mTouch.y);
        }
        Log.d("PageWidgetT", "side:" + side.toString() + ",x:" + mTouch.x + ",y:" + mTouch.y);

//        mAngle = currentAngle - beginAngle;
//    canvas.rotate(mRotationDegrees, picCenterX, picCenterY);

        if (currentMode == MODE_ROTATE) {


        } else if (currentMode == MODE_FLIP) {
            canvas.clipPath(mBackAndNextPath0, Region.Op.XOR);
        }
        Log.d("PageWidgetT", "drawRotationDegrees:" + mRotationDegrees);
        canvas.rotate(mRotationDegrees, picCenterX, picCenterY);
        canvas.drawBitmap(bitmap, previousPadingLeft, previousPadingTop, null);
        canvas.restore();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0x00000000);
        if (mPicRectF != null) {
            if (currentMode == MODE_FLIP && mTouch.x != 0 && mTouch.y != 0 && mCornerX != 0 && mCornerY != 0) {

                calcPoints();
                drawCurrentBackArea(canvas, mNextPageBitmap);
            }
            drawCurrentPageArea(canvas, mCurPageBitmap);
        }
    }

    /**
     * 翻一页背面
     */
    private void drawCurrentBackArea(Canvas canvas, Bitmap bitmap) {

        if (currentMode == MODE_FLIP) {

            mNextPath1.reset();
            mNextPath1.moveTo(mTouch.x, mTouch.y);
            mNextPath1.lineTo(pointE.x, pointE.y);
            mNextPath1.lineTo(pointH.x, pointH.y);
            mNextPath1.close();

            canvas.save();
            canvas.clipPath(mNextPath1);
            canvas.translate(mTouch.x, mTouch.y);
            if (mCornerX == mPicRectF.left && mCornerY == mPicRectF.top) {
                canvas.rotate(2 * mDegrees);
                canvas.scale(-1, 1);
            } else if (mCornerX == mPicRectF.left && mCornerY == mPicRectF.bottom) {
                canvas.rotate(2 * mDegrees);
                canvas.scale(-1, -1);
            } else if (mCornerX == mPicRectF.right && mCornerY == mPicRectF.top) {
                canvas.rotate(2 * mDegrees);
                canvas.scale(1, 1);
            } else if (mCornerX == mPicRectF.right && mCornerY == mPicRectF.bottom) {
                canvas.rotate(2 * mDegrees);
                canvas.scale(1, -1);
            }
            canvas.drawBitmap(bitmap, 0, 0, mPaint);
            canvas.restore();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w <= 0 || h <= 0)
            return;
        mWidth = w;
        mHeight = h;
        initSize();
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void initSize() {
        mPicWidth = (int) (mWidth * PART_PICTURE_WIDTH);
        mPicHeight = (int) (mHeight * PART_PICTURE_HEIGHT);

        picPadingLeft = (mWidth - mPicWidth) * PART_PADING_LEFT;
        picPadingRight = (mWidth - mPicWidth) * PART_PADING_RIGHT;
        picPadingTop = (mHeight - mPicHeight) * PART_PADING_TOP;
        picPadingBottom = (mHeight - mPicHeight) * PART_PADING_BOTTOM;

        mPicRectF = new RectF(picPadingLeft, picPadingTop, mWidth - picPadingRight, mHeight - picPadingBottom);
        mPicDropRectF = new RectF(picPadingLeft - 5F, picPadingTop - 5F, mWidth - picPadingRight + 5F, mHeight - picPadingBottom + 5F);

        previousPadingLeft = picPadingLeft;
        previousPadingTop = picPadingTop;

        picCenterX = mPicRectF.centerX();
        picCenterY = mPicRectF.centerY();

    /*	mCurPageBitmap = Bitmap.createBitmap(mPicWidth, mPicHeight, Bitmap.Config.ARGB_8888);
        mNextPageBitmap=Bitmap.createBitmap(mPicWidth, mPicHeight, Bitmap.Config.ARGB_8888);*/

        if (tempCurPageBitmap != null) {
            mCurPageBitmap = Bitmap.createScaledBitmap(tempCurPageBitmap, mPicWidth, mPicHeight, true);
            tempCurPageBitmap = Bitmap.createScaledBitmap(tempCurPageBitmap, mPicWidth, mPicHeight, true);

        } else {
            mCurPageBitmap = Bitmap.createBitmap(mPicWidth, mPicHeight, Bitmap.Config.ARGB_8888);
        }
        if (tempNextPageBitmap != null) {
            mNextPageBitmap = Bitmap.createScaledBitmap(tempNextPageBitmap, mPicWidth, mPicHeight, true);
            tempNextPageBitmap = Bitmap.createScaledBitmap(tempNextPageBitmap, mPicWidth, mPicHeight, true);
        } else
            mNextPageBitmap = Bitmap.createBitmap(mPicWidth, mPicHeight, Bitmap.Config.ARGB_8888);
        mRotationDegrees = 0;
        isRotate = false;
        currentMode = MODE_ROTATE;
    }

    private void reSetSize() {
        if (mRotationDegrees == 90.0) {
            int temp = mPicWidth;
            mPicWidth = mPicHeight;
            mPicHeight = temp;

            picPadingLeft = picCenterX - mPicWidth / 2;
            picPadingRight = mWidth - (picCenterX + mPicWidth / 2);
            picPadingTop = picCenterY - mPicHeight / 2;
            picPadingBottom = mHeight - (picCenterY + mPicHeight / 2);

            mPicRectF = new RectF(picPadingLeft, picPadingTop, mWidth - picPadingRight, mHeight - picPadingBottom);
            mPicDropRectF = new RectF(picPadingLeft - 5F, picPadingTop - 5F, mWidth - picPadingRight + 5F, mHeight - picPadingBottom + 5F);
            Log.d("FoldView", "mPicDropRectF:" + mPicDropRectF.left + "," + mPicDropRectF.top + "," + mPicDropRectF.right + "," + mPicDropRectF.bottom);
        }

    }

    // 子控件位置改变重新计算角度
    private int computeCurrentAngle(float x, float y) {
        // 根据圆心坐标计算角度
        float distance = (float) Math
                .sqrt(((x - picCenterX) * (x - picCenterX) + (y - picCenterY)
                        * (y - picCenterY)));
        int degree = (int) (Math.acos((x - picCenterX) / distance) * 180 / Math.PI);
        if (y < picCenterY) {
            degree = -degree;
        }
        if (degree < 0) {
            degree += 360;
        }

        Log.d("PageWidgetT", "x:" + x + ",y:" + y + ",degree:" + degree);
        return degree;
    }


    enum SideCenter {
        Vertical, Horizontal, None
    }

    class TurnToNext implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            if (!hasFlip) {
                clearAnimation();
//				rotation.computeDurationHint()
                refresh();
            }
        }

        /**
         * 还需要再启动ImageView的动画，让ImageView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            if (hasFlip) {
                mCurPageBitmap = Bitmap.createScaledBitmap(mNextPageBitmap, mPicWidth, mPicHeight, true);
                invalidate();
                // 获取布局的中心点位置，作为旋转的中心点
                // 构建3D旋转动画对象，旋转角度为270到360度，这使得ImageView将会从不可见变为可见
                final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, picCenterX, picCenterY,
                        360.0f, false);
                // 动画持续时间500毫秒
                rotation.setDuration(300);
                // 动画完成后保持完成的状态
                rotation.setFillAfter(false);
                rotation.setInterpolator(new AccelerateInterpolator());
                startAnimation(rotation);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }
}
