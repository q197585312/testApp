package com.example.dongnao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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
    /**view 事件分发
     * 1、dispatchTouchEvent（）分发
     * 2、 onTouchEvent view的回调  设置onTouchListener  后进入----》  方法 onTouch（）
     * r如果设置了O你TouchListener里面的onTouch返回了 true  则里面子view的touch 不执行 且自身的 onTouchEvent不执行
     * dispatchTouchEvent--->onTouchListener --->onTouch----返回false----> 执行自身onTouchEvent
     * 如果view为disEnable 则 onTouchListener 不会执行----> 自身onTouchEvent
     * 3、 onClick----》OnClickListener
     * onClick 在 自身的onTouchEvent方法里面
     *
     * */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("test","Touch;action---"+motionEvent.getAction()+"----view:"+view);
        View
        return true;
    }

    @Override
    public void onClick(View view) {
        Log.i("test","click;view"+view);
    }
}
