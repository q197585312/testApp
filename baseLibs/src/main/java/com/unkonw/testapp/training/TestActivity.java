package com.unkonw.testapp.training;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.unkonw.testapp.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.AutoUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/14 0014.
 */

public class TestActivity extends BaseActivity {
    //    @Bind(R.id.btn_test)
    Button btnTest;
//        @Bind(R.id.ll_parent)
    LinearLayout llParent;
    //    @Bind(R.id.btn_test2)
    Button btnTest2;

    private GestureManager manager;
    private ScrollLayout s1;
    private ScrollLayout s2;
    private List<ScrollLayout> sls=new ArrayList<>();
    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_btn);
        ButterKnife.bind(this);
        testGesture();
        RecyclerView rv = (RecyclerView) findViewById(R.id.base_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new BaseRecyclerAdapter<String>(this,Arrays.asList("","",""),R.layout.text) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                final ScrollLayout  sl = holder.getView(R.id.sl_2);

                if(!sls.contains(sl))
                    sls.add(sl);
                sl.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(sl.getFollowScrolls()==null){
                            sl.setFollowScrolls(sls);
                        }
                        return false;
                    }
                });
            }

        });
    }

    private void testGesture() {
        //手势监听
        manager = new GestureManager(mContext);
        final GestureDetector gestureDetector = new GestureDetector(mContext, new
                GestureL());
      /*  btnTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);

                testVelocityTracker(motionEvent);
                return false;
                //按GestureL.onDown  (GestureL.java:15)-> GestureL.onShowPress--> (GestureL.java:21)║-->GestureL.onLongPress  (GestureL.java:38)
                //滑动    GestureL.onDown  (GestureL.java:15)   GestureL.onShowPress  (GestureL.java:21)    GestureL.onScroll  (GestureL.java:32)  GestureL.onFling  (GestureL.java:43)//抛出就不执行后面的

            }
        });*/
    }

    private void testVelocityTracker(MotionEvent motionEvent) {

        int action = motionEvent.getAction() & MotionEvent.ACTION_MASK;
        manager.startVelocityTracker(motionEvent);//所有的event都要addMovement 进去观察
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                manager.getScrollVelocity();
                break;
            case MotionEvent.ACTION_UP:
                manager.stopVelocityTracker();
                break;
            case MotionEvent.ACTION_CANCEL:
                manager.stopVelocityTracker();
                break;


        }
    }

    //    @OnClick(R.id.btn_test)
    public void onClick(View v) {
        llParent.scrollBy(-AutoUtils.getDisplayWidthValue(360), -AutoUtils.getDisplayWidthValue(640));
    }


    //    @OnClick(R.id.btn_test2)
    public void onClick() {
        llParent.scrollTo(-AutoUtils.getDisplayWidthValue(360), -AutoUtils.getDisplayWidthValue(640));
    }

    public void clickB1(View view) {
        ToastUtils.showShort("B1");
    }
}
