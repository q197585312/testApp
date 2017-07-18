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
/**先触发 onTouch再触发 onClick
 * 如果 onTouch 返回true 则只触发onTouch，只有返回false 才会传递 onCLick事件
 * 依据 view的时间分发
 * */
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("test","Touch;action---"+motionEvent.getAction()+"----view:"+view);
        return true;
    }

    @Override
    public void onClick(View view) {
        Log.i("test","click;view"+view);
    }
}
