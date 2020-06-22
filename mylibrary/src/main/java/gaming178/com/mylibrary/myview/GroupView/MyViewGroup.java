package gaming178.com.mylibrary.myview.GroupView;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MyViewGroup extends ViewGroup {
    private  Context context;
    private int mCenterX;
    private int mCenterY;
    private Map<Integer,Boolean> childInitMap=new HashMap<>();

    public MyViewGroup(Context context) {
        this(context, null);
        this.context=context;
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

    }

    public boolean isShake() {
        return isShake;
    }

    public void setShake(boolean shake) {
        if(isShake!=shake) {
            isShake = shake;
            for(int i=0;i<getChildCount();i++){
                childInitMap.put(i,false);
            }
        }
    }

    boolean isShake=false;
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        Log.d("CircleView", "onSizeChanged: w=" + w + ", h=" + h);
//        updateCenter(w, h);
//    }

    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     */

// abstract method in viewgroup
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        mCenterX=getLeft()+getWidth()/2-ScreenUtil.dip2px(context,5);
//        mCenterY=getTop()+getHeight()/2- ScreenUtil.dip2px(context,20);
        mCenterX=getWidth()/2;
        mCenterY=getHeight()/2;
        int rParent=getWidth()/2;
        int h=getHeight()/2;
        if(h<rParent){
            rParent=h;
        }
        int cCount = getChildCount();
        /**
         * 遍历所有childView根据其宽和高，
         */
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);

//            计算最低高度
            int rChild= childView.getMeasuredWidth()/2;
            int ch=childView.getMeasuredHeight()/2;
            if(ch>rChild){
                rChild=ch;
            }
            initBall(i, rParent, childView, rChild, isShake);
        }
    }


    private void initBall(int childIndex,int rParent, View childView,  int rChild,boolean isShake) {
        double newX = 0;
        double newY = 0;
        int r=rParent-rChild;
        Random ra = new Random();
        int angle = ra.nextInt(80) + 140;
        if(isShake)
            angle = ra.nextInt(360);
        if(angle==0){
            newX=mCenterX;
            newY=mCenterY-r;
        }
        else if(angle==90){
            newX=mCenterX+r;
            newY=mCenterY;
        }
        else if(angle==180){
            newX=mCenterX;
            newY=mCenterY+r;
        }
        else if(angle==270){
            newX=mCenterX-r;
            newY=mCenterY;
        }
        else if(angle==360){
            newX=mCenterX;
            newY=mCenterY-r;
        }
        if(isShake) {
            /**
             * 0-90的变化规律
             */

             if(angle>0&&angle<90){
                newX = mCenterX+ (ra.nextInt(r)*Math.sin(angle*Math.PI/180));
                newY = mCenterY-(ra.nextInt(r)*Math.cos(angle*Math.PI/180));
            }

            /**
             * 90-180的变化规律
             */
            else if(angle>90&&angle<180){
                int newAngle1=180-angle;
                newX=mCenterX+ (ra.nextInt(r)*Math.sin(newAngle1*Math.PI/180));
                newY=mCenterY+(ra.nextInt(r)*Math.cos(newAngle1*Math.PI/180));
            }

            /**
             * 180-270的变化规律
             */
            else if(angle>180&&angle<270){
                int newAngle1=270-angle;
                newX=mCenterX- (ra.nextInt(r)*Math.cos(newAngle1*Math.PI/180));
                newY=mCenterY+(ra.nextInt(r)*Math.sin(newAngle1*Math.PI/180));
            }

            /**
             * 270-360的变化规律
             */
            else if(angle>270&&angle<360){
                int newAngle1=360-angle;
                newX=mCenterX- (ra.nextInt(r)*Math.sin(newAngle1*Math.PI/180));
                newY=mCenterY-(ra.nextInt(r)*Math.cos(newAngle1*Math.PI/180));
            }
            int leftC= (int)(newX -rChild);
            int topC= (int) (newY- rChild);
            int rightC=(int)(leftC+rChild*2);
            int bottpmC=(int)(topC+rChild*2);
            childView.layout(leftC , topC,rightC, bottpmC);
        }
        else{
            int minY=mCenterY+rParent/2;
            if (angle < 180) {
                int newAngle1 = 180 - angle;
                newX = mCenterX + (r * Math.sin(newAngle1 * Math.PI / 180));
                newY = mCenterY+ (r  * Math.cos(newAngle1 * Math.PI / 180));

            } else if (angle > 180) {
                int newAngle1 = 270 - angle;
                newX = mCenterX - (r * Math.cos(newAngle1 * Math.PI / 180));
                newY = mCenterY + (r * Math.sin(newAngle1 * Math.PI / 180));
            }
            newY= ra.nextInt((int) (newY - minY))+minY;
            int leftC= (int)(newX -rChild);
            int topC= (int) (newY- rChild);
            int rightC=(int)(leftC+rChild*2);
            int bottpmC=(int)(topC+rChild*2);
            if(childInitMap.containsKey(childIndex)&&childInitMap.get(childIndex)) {
            }else {
                childView.layout(leftC, topC, rightC, bottpmC);
                childInitMap.put(childIndex,true);
            }
        }


    }
    Runnable update = new Runnable() {
        @Override
        public void run() {
            requestLayout();
            handler.postDelayed(this, 150);// 50是延时时长
        }
    };
    Handler handler=new Handler();
    public void updateLayout() {
        setShake(true);
        handler.removeCallbacks(update);
        handler.post(update);// 打开定时器，执行操作
    }
    public void initLayout(){
        setShake(false);
        handler.removeCallbacks(update);
        for(int i=0;i<getChildCount();i++){
            childInitMap.put(i,false);
        }
        requestLayout();

    }
}
