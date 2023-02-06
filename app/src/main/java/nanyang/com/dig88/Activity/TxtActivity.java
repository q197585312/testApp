package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.myview.GroupView.MyViewGroup;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;

/**
 * Created by Administrator on 2016/2/18.
 */
public class TxtActivity extends GameBaseActivity {

    @BindView(R.id.my_group)
    MyViewGroup myGroup;
    @BindView(R.id.my_line)
    View my_line;
    @BindView(R.id.selected_ball_tv)
    TextView selectedBall;
    private RotateAnimation refreshingAnimation;

    @Override
    protected int getLayoutRes() {
        return R.layout.testactivity;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

    }

    private void addBalls(int total) {
        for(int i=0;i<total;i++){
            TextView textView=new TextView(mContext);
            textView.setText(""+i);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.oval_blue_shade_bg);
            ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ScreenUtil.dip2px(mContext,30),ScreenUtil.dip2px(mContext,30));
            myGroup.addView(textView,layoutParams);

        }
    }

    public void clickRun(View v){
        myGroup.removeAllViews();
        addBalls(20);
        myGroup.updateLayout();
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotating10);
        //设置动画时间

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                selectedBall.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clickStop(new View(mContext));
                slideView(selectedBall,80,"12");
//                myGroup.selectedView(19,ScreenUtil.dip2px(mContext,80));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        my_line.startAnimation(animation);
    }
    public void clickStop(View v){
        myGroup.initLayout();
    }

    /**
     *
     * @param view 移动的小球
     * @param x  x移动的距离
     * @param number 小球的号码
     */
    public void slideView(final TextView view , final float x, String number) {

        myGroup.removeViewAt(Integer.valueOf(number));//移除
        view.setText(number);
        view.setVisibility(View.VISIBLE);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(1000);
        //设置透明度渐变动画的持续时间
        TranslateAnimation animationY = new TranslateAnimation(0, 0, -view.getHeight(), 0);
        animationY.setInterpolator(new OvershootInterpolator());
        animationY.setDuration(1000);
        AnimationSet set=new AnimationSet(true);    //创建动画集对象
        set.addAnimation(alphaAnimation);       //添加位置变化动画
        set.addAnimation(animationY);           //添加透明度渐变动画
        set.setFillAfter(true);                 //停留在最后的位置
        set.setFillEnabled(true);
               //设置动画
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TranslateAnimation animationX = new TranslateAnimation(0, x, 0, 0);
                animation.setInterpolator(new OvershootInterpolator());
                animation.setDuration(3000);
                view.startAnimation(animationX);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(set);
        set.startNow();
    }

}
