package com.unkonw.testapp.libs.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.unkonw.testapp.libs.utils.LogUtil;

import butterknife.ButterKnife;

/**
 *
 *
 */
public abstract class BasePopupWindow {
    private static final String TAG = "BasePopupWindow";
    protected Context context;
    private LayoutInflater inflater;
    public PopupWindow popWindow;

    public void setWidth(int width) {
        this.width = width;
    }

    protected int width;
    protected int height;

    public void setV(View v) {
        this.v = v;
    }

    protected View v;
    protected View contentView;

    public void setTrans(float trans) {
        this.trans = trans;
    }

    private float trans = 0.3f;


    public BasePopupWindow(Context context, View v) {
        this(context, v, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public int getWidth() {
        return width;
    }

    public BasePopupWindow(Context context, View v, int width, int height) {

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.v = v;
        this.width = width;
        this.height = height;
        initPop();
    }

    public boolean isShowing() {
        if (popWindow != null) {
            Log.d(TAG, "!= null,isShowing: " + popWindow.isShowing());
            return popWindow.isShowing();

        } else {
            Log.d(TAG, "== null,isShowing: " + false);
            return false;
        }
    }


    protected abstract int onSetLayoutRes();

    private void initPop() {
        int layoutId = onSetLayoutRes();
        contentView = inflater.inflate(layoutId, null);
        ButterKnife.bind(this, contentView);
        initView(contentView);

        popWindow = new PopupWindow(contentView, width, height, true);
        // 设置动画效果
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                PixelFormat.TRANSLUCENT);
//        params.gravity=Gravity.CENTER|Gravity.CENTER;
//        params.x=0;
//        params.y=0;
//

        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //点击空白处时，隐藏掉pop窗口
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOnDismissListener(new popDismissListener());
    }

    public void setSoftInputMode(int softInputMode) {
        if (popWindow != null) {
            popWindow.setSoftInputMode(softInputMode);
        }
    }

    private void setBackgroundAttr(float f) {
        WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
        params.alpha = f;
        ((Activity) context).getWindow().setAttributes(params);
    }

    protected void initView(View view) {

    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class popDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            setBackgroundAttr(1f);
            onClose();
        }

    }

    protected void onClose() {

    }

    public void showPopupDownWindow() {
        showPopupDownWindow(0, 0);
    }

    public void showPopupDownWindow(int x, int y) {
        closePopupWindow();

        setBackgroundAttr(trans);

        popWindow.showAsDropDown(v, x, y);
    }

    public void showPopupDownWindowWihte(int x, int y) {
        closePopupWindow();
        popWindow.showAsDropDown(v, x, y);
    }

    public void showPopupGravityWindow(int gravity, int offsetX, int offsetY) {
        closePopupWindow();

        setBackgroundAttr(trans);
        popWindow.showAtLocation(v, gravity, offsetX, offsetY);
    }

    public void showAtLocation(int gravity, int offsetX, int offsetY) {
        closePopupWindow();
        setBackgroundAttr(trans);
        popWindow.showAtLocation(v, gravity, offsetX, offsetY);
    }

    public void showPopupCenterWindow() {
        setBackgroundAttr(trans);
        if (context != null && !((Activity) context).isFinishing()) {
            popWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        }
    }

    public void showPopupWindowUpCenter(View view, int popupHeight, int popupWidth) {
        //获取自身的长宽高
/*        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = contentView.getMeasuredHeight();
        int popupWidth = contentView.getMeasuredWidth();*/
//获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        view.getLocationOnScreen(location);
//在控件上方显示    向上移动y轴是负数
        setBackgroundAttr(trans);
        popWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }

    public void showPopupWindowUpCenter(View view) {
        //获取自身的长宽高
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = contentView.getMeasuredHeight();
        int popupWidth = contentView.getMeasuredWidth();
//获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        view.getLocationOnScreen(location);
//在控件上方显示    向上移动y轴是负数
        setBackgroundAttr(trans);
        popWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }

    /**
     * 关闭popupwindow
     */
    public void closePopupWindow() {
        LogUtil.d("BetPop", "closePopupWindow----noShowRts:");
        if ((context != null) && popWindow != null && popWindow.isShowing()) {
            popWindow.dismiss();
            ButterKnife.unbind(contentView);

        }
    }

    public void showPopupAtLocation(int x, int y) {
        closePopupWindow();
        setBackgroundAttr(trans);
        popWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y);

    }

    public int getHeight() {
        return contentView.getHeight();
    }
}
