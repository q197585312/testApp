package com.unkonw.testapp.libs.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.unkonw.testapp.R;


public abstract class BaseYseNoChoosePopupwindow extends BasePopupWindow {
    TextView chooseTitleTv;
    TextView chooseMessage;
    TextView chooseSureTv;
    TextView chooseCancelTv;

    public BaseYseNoChoosePopupwindow(Context context, View v) {
        super(context, v);
    }

    public BaseYseNoChoosePopupwindow(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        chooseTitleTv = (TextView) view.findViewById(R.id.choose_title_tv);
        chooseMessage = (TextView) view.findViewById(R.id.choose_message_tv);
        chooseSureTv = (TextView) view.findViewById(R.id.choose_sure_tv);
        chooseCancelTv = (TextView) view.findViewById(R.id.choose_cancel_tv);
        chooseCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCancel(v);
            }
        });
        chooseSureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSure(v);
                closePopupWindow();
            }
        });
    }

    protected abstract void clickSure(View v);

    protected void clickCancel(View v) {
        closePopupWindow();
    }

    public TextView getChooseCancelTv() {
        return chooseCancelTv;
    }


    public TextView getChooseMessage() {
        return chooseMessage;
    }

    public TextView getChooseSureTv() {
        return chooseSureTv;
    }


    public TextView getChooseTitleTv() {
        return chooseTitleTv;
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_base_yes_choose;
    }
}

