package com.nanyang.app.myView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.unkonw.testapp.libs.view.refreshLayout.Pullable;

/**
 * Created by Administrator on 2017/2/17.
 */

public class PullableRecyclerview extends RecyclerView implements Pullable {
    public PullableRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canPullDown() {
        return false;
    }

    @Override
    public boolean canPullUp() {
        return false;
    }
}
