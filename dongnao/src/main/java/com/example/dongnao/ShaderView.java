package com.example.dongnao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2017/8/6.
 * 位图渲染
 */

public class ShaderView extends View {
    private final Paint mPaint;

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        Bitmap drawable = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher_round)).getBitmap();
    }

    //渲染 Shader
//    BitmapShader
//    LinearShader


}
