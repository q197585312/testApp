package nanyang.com.dig88.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2016/3/4.
 */
public class TestAnimationActivity extends Activity {
    private ImageView imgView1, imgView2, imgView3, imgView4, imgView5, imgView6, imgView7;
    private Button btnStart;
    private int baseTimeMillis = 100;
    private int timeMillis = 1500;
    private Random random = new Random();
    private int xoffset = 150;

    private int screenHeight = 0;
    private int screenWidth = 0;
    private float baseDegrees = 1800f;
    private int baseDegreesRand = 1000;

    private int[] niuDanImageArr = new int[] { R.drawable.nd_1, R.drawable.nd_2, R.drawable.nd_3, R.drawable.nd_4, R.drawable.nd_5, R.drawable.nd_6, R.drawable.nd_7 };

    // private List<Integer> niuDanImageArr = new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bak);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new NiuDanAnimationListener());

        // llNiuDanJi = (LinearLayout) findViewById(R.id.ll_ndj);

        imgView1 = (ImageView) findViewById(R.id.imgView_1);
        imgView2 = (ImageView) findViewById(R.id.imgView_2);
        imgView3 = (ImageView) findViewById(R.id.imgView_3);
        imgView4 = (ImageView) findViewById(R.id.imgView_4);
        imgView5 = (ImageView) findViewById(R.id.imgView_5);
        imgView6 = (ImageView) findViewById(R.id.imgView_6);
        imgView7 = (ImageView) findViewById(R.id.imgView_7);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        screenHeight = windowManager.getDefaultDisplay().getHeight();
        screenWidth = windowManager.getDefaultDisplay().getWidth();

        Toast.makeText(this, "屏幕高宽，screenWidth=" + screenWidth + ",screenHeight=" + screenHeight, Toast.LENGTH_SHORT).show();

    }

    private void startAnimation(View view, float toDegrees, float xstart, float xend)
    {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(timeMillis);
        animationSet.setFillAfter(true);

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        Toast.makeText(this, "扭蛋坐标，x=" + x + ",y=" + y, Toast.LENGTH_SHORT).show();
        if (x + xstart + xoffset > screenWidth)
        {
            xstart = -xstart;
        }

        if (x + xend + xoffset > screenWidth)
        {
            Toast.makeText(this, "扭蛋坐标，x=" + x + ",y=" + y, Toast.LENGTH_SHORT).show();
            xend = -xend - xoffset;
        }

        TranslateAnimation translateAnimation = new TranslateAnimation(xstart, xend, random.nextInt(10), -random.nextInt(60));
        translateAnimation.setDuration(baseTimeMillis);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        // view.setAnimation(translateAnimation);
        // translateAnimation.start();
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);

        // RotateAnimation rotateAnimation = new RotateAnimation(0f, 900f, 0.5f, 0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0f, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(baseTimeMillis);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setFillAfter(true);
        animationSet.addAnimation(rotateAnimation);

        view.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener()
        {

            @Override
            public void onAnimationStart(Animation animation)
            {
                List<Integer> tempList = new ArrayList<Integer>();
                for (Integer res : niuDanImageArr)
                {
                    tempList.add(res);
                }
                imgView1.setImageResource(tempList.remove(random.nextInt(7)));
                imgView2.setImageResource(tempList.remove(random.nextInt(6)));
                imgView3.setImageResource(tempList.remove(random.nextInt(5)));
                imgView4.setImageResource(tempList.remove(random.nextInt(4)));
                imgView5.setImageResource(tempList.remove(random.nextInt(3)));
                imgView6.setImageResource(tempList.remove(random.nextInt(2)));
                imgView7.setImageResource(tempList.remove(random.nextInt(1)));
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {

            }
        });
    }

    public void add(View childView, View parentView){

    }

    // class NiuDanAnimationListener implements OnClickListener
    // {
    // @Override
    // public void onClick(View v)
    // {
    // startAnimation(imgView1, baseDegrees + random.nextInt(baseDegreesRand));
    // startAnimation(imgView2, -(baseDegrees + random.nextInt(baseDegreesRand)));
    // startAnimation(imgView3, baseDegrees + random.nextInt(baseDegreesRand));
    // startAnimation(imgView4, -(baseDegrees + random.nextInt(baseDegreesRand)));
    // startAnimation(imgView5, baseDegrees + random.nextInt(baseDegreesRand));
    // startAnimation(imgView6, -(baseDegrees + random.nextInt(baseDegreesRand)));
    // startAnimation(imgView7, baseDegrees + random.nextInt(baseDegreesRand));
    // }
    // }
    //
    // private void startAnimation(View view, float toDegrees)
    // {
    // AnimationSet animationSet = new AnimationSet(true);
    // animationSet.setDuration(timeMillis);
    // animationSet.setFillAfter(true);
    //
    // int[] location = new int[2];
    // view.getLocationOnScreen(location);
    // int x = location[0];
    // int y = location[1];
    //
    // float xstart = random.nextInt(100);
    // float xend = random.nextInt(100);
    //
    // Toast.makeText(this, "扭蛋坐标，x=" + x + ",y=" + y, Toast.LENGTH_SHORT).show();
    // if (x + xstart + xoffset > screenWidth)
    // {
    // xstart = -xstart;
    // }
    //
    // if (x + xend + xoffset > screenWidth)
    // {
    // Toast.makeText(this, "扭蛋坐标，x=" + x + ",y=" + y, Toast.LENGTH_SHORT).show();
    // xend = -xend - xoffset;
    // }
    //
    // TranslateAnimation translateAnimation = new TranslateAnimation(xstart, xend, random.nextInt(10) + random.nextInt(20), -random.nextInt(100));
    // translateAnimation.setDuration(baseTimeMillis);
    // translateAnimation.setRepeatCount(1);
    // translateAnimation.setRepeatMode(Animation.REVERSE);
    // // view.setAnimation(translateAnimation);
    // // translateAnimation.start();
    // translateAnimation.setFillAfter(true);
    // animationSet.addAnimation(translateAnimation);
    //
    // // RotateAnimation rotateAnimation = new RotateAnimation(0f, 900f, 0.5f, 0.5f);
    // RotateAnimation rotateAnimation = new RotateAnimation(0f, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    // rotateAnimation.setDuration(baseTimeMillis);
    // rotateAnimation.setRepeatCount(1);
    // rotateAnimation.setRepeatMode(Animation.REVERSE);
    // rotateAnimation.setFillAfter(true);
    // animationSet.addAnimation(rotateAnimation);
    //
    // view.startAnimation(animationSet);
    // }
    class NiuDanAnimationListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            startAnimation(imgView1, baseDegrees + random.nextInt(baseDegreesRand), 0, 50f);
            startAnimation(imgView2, -(baseDegrees + random.nextInt(baseDegreesRand)), 0, 30f);
            startAnimation(imgView3, baseDegrees + random.nextInt(baseDegreesRand), 0, -50f);
            startAnimation(imgView4, -(baseDegrees + random.nextInt(baseDegreesRand)), 0, 50f);
            startAnimation(imgView5, baseDegrees + random.nextInt(baseDegreesRand), 0, 30f);
            startAnimation(imgView6, -(baseDegrees + random.nextInt(baseDegreesRand)), 0, -20f);
            startAnimation(imgView7, baseDegrees + random.nextInt(baseDegreesRand), 0, -50f);
        }
    }
}
