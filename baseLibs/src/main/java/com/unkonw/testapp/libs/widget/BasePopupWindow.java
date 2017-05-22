package com.unkonw.testapp.libs.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import butterknife.ButterKnife;

/**
 *
 *
 */
public abstract class BasePopupWindow {
    protected Context context;
    private LayoutInflater inflater;
    private PopupWindow popWindow;
    protected int width;
    protected int height;

    protected View v;
    protected View view;

    public void setTrans(float trans) {
        this.trans = trans;
    }

    private float trans=0.3f;


    public BasePopupWindow(Context context, View v) {
        this(context, v, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
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
            return popWindow.isShowing();
        } else {
            return false;
        }
    }


    protected abstract int onSetLayoutRes();

    private void initPop() {
        int  layoutId= onSetLayoutRes();
        view = inflater.inflate(layoutId, null);
        ButterKnife.bind(this, view);
        initView(view);

        popWindow = new PopupWindow(view, width, height, true);
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

    private void setBackgroundAttr(float f) {
        WindowManager.LayoutParams params= ((Activity)context).getWindow().getAttributes();
        params.alpha=f;
        ((Activity)context).getWindow().setAttributes(params);
    }

    protected void initView(View view) {

    }
    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     * @author cg
     *
     */
    class popDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            setBackgroundAttr(1f);
            onClose();
        }

    }

    protected void onClose() {

    }

    public void showPopupDownWindow() {
       showPopupDownWindow(0,0);
    }
    public void showPopupDownWindow(int x,int y) {
        closePopupWindow();

        setBackgroundAttr(trans);
        popWindow.showAsDropDown(v,x,y);
    }

    public void showPopupGravityWindow(int gravity,int offsetX,int offsetY) {
        closePopupWindow();

        setBackgroundAttr(trans);
        popWindow.showAtLocation(v,gravity, offsetX, offsetY);
    }
    public  void showAtLocation(int gravity,int offsetX,int offsetY){
        closePopupWindow();
        setBackgroundAttr(trans);
        popWindow.showAtLocation(v,gravity, offsetX, offsetY);
    }
    public void showPopupCenterWindow() {
        closePopupWindow();

        setBackgroundAttr(trans);
        if(context!=null&&!((Activity)context).isFinishing()) {
            popWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        }
    }

    /**
     * 关闭popupwindow
     */
    public void closePopupWindow() {
        if (popWindow != null && popWindow.isShowing()) {
            ButterKnife.unbind(view);
            popWindow.dismiss();
        }
    }
    public void showPopupAtLocation(int x,int y) {
        closePopupWindow();
        setBackgroundAttr(trans);
        popWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y);

    }
    public int  getHeight(){
        return view.getHeight();
    }
}
