package gaming178.com.mylibrary.myview.GroupView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/3/4.
 */
public class CustView  extends View{
    public  final int R1 = 250;
    public  final int R2 = 50;

    //每一格 cos y的差距


    private  float width;
    private  float height;


    private   int newAngle=30;  //新的角度 开始时30
    private  int newAngle1;  //新的角度 开始时30

    public  double newX;
    public   double newY;   //给Main提供位置 空间移动到这个位置上面去

    private Paint mPaint2;
    private boolean flag=false;
    private Paint mPaint3;
    private Paint mPaint;
    private Context mContext;
    private double newAngle11;

    public CustView(Context context) {
        this(context, null);
    }

    public CustView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext=context;
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint2.setColor(Color.BLUE);


        mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint3.setStrokeWidth(3);
        mPaint3.setTextSize(80);
        mPaint3.setColor(Color.CYAN);
        mPaint3.setColor(Color.RED);
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        mPaint3.setTextAlign(Paint.Align.CENTER);


        DisplayMetrics metric = new DisplayMetrics();



        width = 200;
        height = 200;

        System.out.println(width+"---"+height);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 绘制圆环
        canvas.drawCircle(width, height, R1, mPaint);

        //在圆环30的相对于360 的位置 顺时针画一个默认小机器人

        System.out.println("现在角度 "+newAngle);

        canvas.drawText(newAngle+"",width, height,mPaint3);
        // canvas.drawText(newAngle+"", targetRect.centerX(), baseline, mPaint3);


        getNewLocation(); //根据判断 来移动小球 获得新的位置

        //确定Main中控件的位置

        System.out.println("newX " +newX +"---------- newY "+newY);

        if(newAngle==90){
            mPaint2.setColor(Color.BLACK);
        }else if(newAngle==180){
            mPaint2.setColor(Color.RED);
        }else if(newAngle==270){
            mPaint2.setColor(Color.DKGRAY);
        }else if(newAngle==360){
            mPaint2.setColor(Color.MAGENTA);
        }
        canvas.drawCircle((float)newX, (float)newY, R2, mPaint2);
    }

    public  void getNewLocation() {
        /**
         * 0-90的变化规律
         */
        if(newAngle==0){
            newX=width;
            newY=height-R1;
        }
        else if(newAngle==90){
            newX=width+R1;
            newY=height;
        }
        else if(newAngle==180){
            newX=width;
            newY=height+R1;
        }
        else if(newAngle==270){
            newX=width-R1;
            newY=height;
        }
        else if(newAngle==360){
            newX=width;
            newY=height-R1;
        }
        else if(newAngle>360){
            newAngle=360;
            newX=width;
            newY=height-R1;
        }
        else if(newAngle>0&&newAngle<90){
            newX = width+ (R1*Math.sin(newAngle*Math.PI/180));
            newY = height-(R1*Math.cos(newAngle*Math.PI/180));
        }

        /**
         * 90-180的变化规律
         */
        else if(newAngle>90&&newAngle<180){
            newAngle1=180-newAngle;
            newX=width+ (R1*Math.sin(newAngle1*Math.PI/180));
            newY=height+(R1*Math.cos(newAngle1*Math.PI/180));
        }

        /**
         * 180-270的变化规律
         */
        else if(newAngle>180&&newAngle<270){
            newAngle1=270-newAngle;
            newX=width- (R1*Math.cos(newAngle1*Math.PI/180));
            newY=height+(R1*Math.sin(newAngle1*Math.PI/180));
        }

        /**
         * 270-360的变化规律
         */
        else if(newAngle>270&&newAngle<360){
            newAngle1=360-newAngle;
            newX=width- (R1*Math.sin(newAngle1*Math.PI/180));
            newY=height-(R1*Math.cos(newAngle1*Math.PI/180));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                flag=false;  //每次按下都是重新开始判断
                float action_x=event.getX();
                float action_y=event.getY();

                System.out.println("点击位置 ： x "+action_x+"   y "+action_y);

                //只有当点击的在那个小圆上面才可以被进入移动的条件
                // x>=newX-R2 && x<=newX+R2 y 也一样
                System.out.println("x 范围: "+(newX-R2)+"-----"+(newX+R2));
                System.out.println("y 范围: "+(newY-R2)+"-----"+(newY+R2));

                if((action_x>=newX-R2 && action_x<=newX+R2)&&(action_y>=newY-R2&&action_y<=newY+R2)){
                    flag=true;  //触碰到小球的情况
                }

                break;
            case MotionEvent.ACTION_MOVE:


                if(flag){
                    //根据判断 让小球一格一的移动
                    //分为两种情况 左边和右边   右边就是   就newAngle++
                    float action_newY=event.getY();
                    float action_newX=event.getX();

                    if(newAngle==0){
                        if(action_newY<newY){
                            newAngle++;
                        }
                    }
                    else if(newAngle==90){
                        if(action_newY>newY){
                            newAngle++;
                        }else{
                            newAngle--;
                        }
                    }
                    else if(newAngle==180){
                        if(action_newX>newX){   //180 右边
                            newAngle--;
                        }else{
                            newAngle++;
                        }
                    }
                    else if(newAngle==270){
                        if(action_newY<newY){
                            newAngle++;
                        }
                        else{
                            newAngle--;
                        }
                    }
                    else if(newAngle==360){
                        if(action_newX>newX){  //先不让他超过360
                            //newAngle++;
                        }else{
                            newAngle--;
                        }
                    }

                    else if(newAngle>0&&newAngle<90){
                        double x=action_newX-width;
                        double y=height-action_newY;
                        System.out.println("x: "+x +"  y : "+y+"------------            "+(Math.atan(x/y)*180/Math.PI));
                        if(y<0){
                            newAngle=90;
                        }
                        else if(x<0){
                            newAngle=0;
                        }
                        else{
                            newAngle=(int) (Math.atan(x/y)*180/Math.PI);
                        }
                    }
                    else if(newAngle>90&&newAngle<180){
                        double x=action_newX-width;
                        double y=action_newY-height;

                        System.out.println("x: "+x +"  y : "+y+"------------"+(180-Math.atan(x/y)*180/Math.PI));

                        if(y<0){
                            newAngle=90;
                        }
                        else if(x<0){
                            newAngle=180;
                        }
                        else
                            newAngle=(int) (180-Math.atan(x/y)*180/Math.PI);

                    }
                    else if(newAngle>180&&newAngle<270){
                        double x=width-action_newX;
                        double y=action_newY-height;

                        System.out.println("x: "+x +"  y : "+y+"------------"+(270-Math.atan(x/y)*180/Math.PI));

                        if(x<0){
                            newAngle=180;
                        }
                        else if(y<0){
                            newAngle=270;
                        }
                        else
                            newAngle=(int) (270- Math.atan(y/x)*180/Math.PI);
                    }
                    else if(newAngle>270&&newAngle<360){
                        double x=width-action_newX;
                        double y=height-action_newY;

                        System.out.println("x: "+x +"  y : "+y+"------------"+(360-Math.atan(x/y)*180/Math.PI));

                        if(y<0){
                            newAngle=270;
                        }
                        else if(x<0){
                            newAngle=360;
                        }
                        else
                            newAngle=(int)(360- Math.atan(x/y)*180/Math.PI);
                    }

                    System.out.println(newAngle+"---角度");

                    invalidate();
                    requestLayout();
                }
                break;

            case MotionEvent.ACTION_UP:

                break;

            default:
                break;
        }

        return true;
    }
}
