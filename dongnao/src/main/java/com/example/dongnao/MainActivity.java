package com.example.dongnao;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/7/18.
 */

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        View viewById = findViewById(R.id.btn);
        View ll = findViewById(R.id.ll);
        viewById.setOnTouchListener(this);
        ll.setOnTouchListener(this);
        ll.setOnClickListener(this);
        viewById.setOnClickListener(this);

    }
    /**先触发 onTouch再触发 onClick
     * 如果 onTouch 返回true 则只触发onTouch，只有返回false 才会传递 onCLick事件
     * 依据 view事件分发
     * */

    //一，view 事件分发
    /**
     * 1、dispatchTouchEvent（）分发
     * 2、 onTouchEvent view的回调  设置onTouchListener  后进入----》  方法 onTouch（）
     * r如果设置了O你TouchListener里面的onTouch返回了 true  则里面子view的touch 不执行 且自身的 onTouchEvent不执行
     * dispatchTouchEvent--->onTouchListener --->onTouch----返回false 不会消耗次事件，执行自身onTouchEvent
     *  * 如果view为disEnable 则 onTouchListener 不会执行----> 自身onTouchEvent
     * 返回 true 消耗此事件 down， 但是不up  不响应onClickListener
     *

     * 3、 onClick----》OnClickListener
     * onClick 在 自身的onTouchEvent方法里面 再up事件中响应  设置了onClickListener return true else return false
     *
     * 4 super必须要执行
     * */
//    二，ViewGroup 事件分发
    /** 1，先触发的事件是父容器  中间多一个interceptTouchEvent 拦截事件 默认不拦截
     * 顺序是：dispatch----intercept---touch 正常情况下 父容器不响应 onTouchEvent  给被触摸到的子view响应
    *  如果 interceptTouchEvent  再action down 的时候判断  返回true  就调用super。dispatchTouchEvent  false 不打断  如果child 为空 调用自身的 dis else 调用child。dispatchEvemt

     *  1，第一次 down 把mFirstTouchTarget 执空
     *  2，所有的viewGroup都是默认不拦截的（拦截就是 自身的down 和以后的后续事件都不传递 拦截下来 给自己的ouTouch用使用，不拦截 自身的touch不响应）
     *  3，dispatchTouchEvent down事件 传递  只要找到某一个子控件消耗事件 就返回true，没有就返回false（再 dispatchTransformedTouchEvent（）找消耗的view）
     *  dispatchTouchEvent中 第一次 down  会给 mFirstTouchTarget制空，--》interceptTouchEvent true----》 dispatchTransformedTouchEvent（）把child传空----》super.dispatchTouchEvent-->view.dis--view.onTouchEvent
     *                                                                    interceptTouchEvent false---> 一个多手指触摸多个的view 的单向链表 ----》for 循环   dispatchTransformedTouchEvent 把链表中的child view传进去 -->child 的onTouchEvent 为true的时候 给 group的mFirstTouchTarget 赋值
     *                                                                    child.dispatchTouchEvent;  然后 递归到最后层的view的dispatchTouchEvent---》o'n'TouchEvent ---》true 本身的dispatchTouchEvent就会true 那么ViewGroup dispatchTransformedTouchEvent 就为true 直接就 给 group的mFirstTouchTarget 赋值
     *                                                                    false 的时候  group的mFirstTouchTarget 为空 调用super.dispatchTouchEvent--->view.disptchTouchevent
     *
     *
     *
     *
     *  */
//    三 ，事件分发的应用
     /** 再dispatchTouchEvent 里面*/
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("test","Touch;action---"+motionEvent.getAction()+"----view:"+view);
        return false;
    }

    @Override
    public void onClick(View view) {
        Log.i("test","click;view"+view);
    }
}
