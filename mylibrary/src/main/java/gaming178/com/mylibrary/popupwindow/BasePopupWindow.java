package gaming178.com.mylibrary.popupwindow;

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
import gaming178.com.mylibrary.R;

/**
 *
 *
 */
public abstract class BasePopupWindow {
    protected Context context;
    protected LayoutInflater inflater;
    protected PopupWindow popoWindow;
    protected int width;
    protected int height;

    protected View v;

    public View getView() {
        return view;
    }

    protected View view;
    int screenWidth;
    int screenHeight;
    public void setOutsideTouchable(boolean outsideTouchable) {
        isOutsideTouchable = outsideTouchable;
    }

    private boolean isOutsideTouchable=true;


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
        if (popoWindow != null) {
            return popoWindow.isShowing();
        } else {
            return false;
        }
    }

    protected abstract int getContentViewLayoutRes();

    private void initPop() {
        int  layoutId=getContentViewLayoutRes();
        view = inflater.inflate(layoutId, null);
        ButterKnife.bind(this, view);
        initView(view);
        popoWindow = new PopupWindow(view, width, height, true);
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
        WindowManager windowManager = ((Activity)context).getWindowManager();
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
        popoWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popoWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //点击空白处时，隐藏掉pop窗口
        popoWindow.setFocusable(true);
        popoWindow.setOutsideTouchable(isOutsideTouchable);
        popoWindow.setBackgroundDrawable(new BitmapDrawable());
        popoWindow.setOnDismissListener(new poponDismissListener());
        
    }

    private void setBackgroundAttr(float f) {
//        WindowManager.LayoutParams params= ((Activity)context).getWindow().getAttributes();
//        params.alpha=f;
//        ((Activity)context).getWindow().setAttributes(params);
    }

    protected void initView(View view) {

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
        popoWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     * @author cg
     *
     */
    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            setBackgroundAttr(1f);
            onCloose();
        }

    }

    protected void onCloose() {

    }

    public void showPopupDownWindow() {
       showPopupDownWindow(0,0);
    }
    public void showPopupDownWindow(int x,int y) {
        closePopupWindow();
        popoWindow.setAnimationStyle(R.anim.abc_popup_enter);
        setBackgroundAttr(0.7f);
        popoWindow.showAsDropDown(v,x,y);
    }

    public void showPopupGravityWindow(int gravity,int offsetX,int offsetY) {
        closePopupWindow();
        popoWindow.setAnimationStyle(R.anim.abc_popup_enter);
        setBackgroundAttr(0.7f);
        popoWindow.showAtLocation(v,gravity, offsetX, offsetY);
    }
    public void showPopupCenterWindow() {
        closePopupWindow();
        popoWindow.setAnimationStyle(R.anim.abc_popup_enter);
        setBackgroundAttr(0.7f);
        if(context!=null&&!((Activity)context).isFinishing()) {
            popoWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        }
    }
    public void showPopupTopWindow() {
        closePopupWindow();
        popoWindow.setAnimationStyle(R.anim.abc_popup_enter);
        setBackgroundAttr(0.7f);
        if(context!=null&&!((Activity)context).isFinishing()) {
            popoWindow.showAtLocation(v, Gravity.TOP, 0, screenHeight/6);
        }
    }
    public void showPopupSicboResultWindow() {
        closePopupWindow();
        popoWindow.setAnimationStyle(R.anim.abc_popup_enter);
        setBackgroundAttr(0.7f);
        if(context!=null&&!((Activity)context).isFinishing()) {
            popoWindow.showAtLocation(v, Gravity.TOP, 0, (screenHeight/7)*4);
        }
    }
    /**
     * 关闭popupwindow
     */
    public void closePopupWindow() {
        if (popoWindow != null && popoWindow.isShowing()) {
            popoWindow.setAnimationStyle(R.anim.abc_popup_exit);
            popoWindow.dismiss();
        }
    }
    public void showPopupAtLocation(int x,int y) {
        closePopupWindow();
        popoWindow.setAnimationStyle(R.anim.abc_popup_enter);
        setBackgroundAttr(0.7f);
        popoWindow.showAtLocation(v,Gravity.NO_GRAVITY, x, y);

    }
    public int  getHeight(){
        return view.getHeight();
    }

}
