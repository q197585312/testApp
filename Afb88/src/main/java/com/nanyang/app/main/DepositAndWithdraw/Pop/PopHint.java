package com.nanyang.app.main.DepositAndWithdraw.Pop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import cn.finalteam.toolsfinal.DeviceUtils;


public class PopHint extends BasePopupWindow {

    TextView tvExit;
    TextView tvSure;


    public PopHint(Context context, View v) {
        super(context, v, DeviceUtils.dip2px(context, 310), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.pop_hint;
    }

    @Override
    protected void initView(@NonNull View view) {
        super.initView(view);
        tvExit = view.findViewById(R.id.tv_exit);
        tvSure = view.findViewById(R.id.tv_sure);
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }
}
