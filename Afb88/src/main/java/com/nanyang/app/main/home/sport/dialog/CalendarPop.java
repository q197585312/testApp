package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/4/18.
 */

public class CalendarPop extends BasePopupWindow {
    private CalendarView calendarView;
    private TextView tvClear;
    private TextView tvNow;
    private TextView tvConfirm;
    private TextView tvContent;


    public CalendarPop(Context context, View v) {
        super(context, v, DeviceUtils.dip2px(context, 300), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public CalendarPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.calendar_pop;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        calendarView = view.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (tvContent != null) {
                    tvContent.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
                closePopupWindow();
            }
        });
        tvClear = view.findViewById(R.id.tv_clear);
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvContent != null) {
                    tvContent.setText("");
                }
                closePopupWindow();
            }
        });
        tvNow = view.findViewById(R.id.tv_now);
        tvNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvContent != null) {
                    tvContent.setText(DateUtils.getCurrentDate("dd/MM/yyyy"));
                }
                closePopupWindow();
            }
        });
        tvConfirm = view.findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }
}
