package com.unkonw.testapp.libs.base

import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.os.IBinder
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.unkonw.testapp.libs.utils.LogUtil
import java.lang.reflect.ParameterizedType

/**
 *
 *
 */
abstract class BasePopupWindow<DB : ViewDataBinding> @JvmOverloads constructor(
    open val context: Context,
    var v: View,
    var widthPop: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
    var heightPop: Int = LinearLayout.LayoutParams.WRAP_CONTENT
) {
    public var mBinding: DB? = null
    private val windowManager: WindowManager
    private val inflater: LayoutInflater
    lateinit var popWindow: PopupWindow
    private var maskView: View? = null
    protected lateinit var contentView: View
    fun setTrans(trans: Float) {
        this.trans = trans
    }

    private var trans = 0.3f

    val isShowing: Boolean
        get() = run {
            Log.d(
                TAG,
                "!= null,isShowing: " + popWindow.isShowing
            )
            popWindow.isShowing
        }

    protected abstract fun onSetLayoutRes(): Int
    private fun initPop() {
        val layoutId = onSetLayoutRes()

        val cls =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        if (ViewDataBinding::class.java != cls && ViewDataBinding::class.java.isAssignableFrom(cls)) {
            mBinding = DataBindingUtil.inflate(inflater,layoutId,null,false )
            contentView= mBinding!!.root
        } else
            contentView = inflater.inflate(layoutId, null)
        initView(contentView)
        popWindow = PopupWindow(contentView, widthPop, heightPop, true)
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popWindow.inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
        popWindow.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        //点击空白处时，隐藏掉pop窗口
        popWindow.isFocusable = true
        popWindow.isOutsideTouchable = true
        popWindow.setBackgroundDrawable(BitmapDrawable())
        popWindow.setOnDismissListener(popDismissListener())
    }

    fun setSoftInputMode(softInputMode: Int) {
        popWindow.softInputMode = softInputMode
    }

    private fun setBackgroundAttr(f: Float) {
        if (f < 1f) {
            addMask(v.windowToken)
        } else {
            removeMask()
        }
        /*     WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
        params.alpha = f;
        ((Activity) context).getWindow().setAttributes(params);*/
    }

    private fun addMask(token: IBinder) {
        val wl = WindowManager.LayoutParams()
        wl.width = WindowManager.LayoutParams.MATCH_PARENT
        wl.height = WindowManager.LayoutParams.MATCH_PARENT
        wl.format = PixelFormat.TRANSLUCENT //不设置这个弹出框的透明遮罩显示为黑色
        wl.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL //该Type描述的是形成的窗口的层级关系
        wl.token = token //获取当前Activity中的View中的token,来依附Activity
        maskView = View(context)
        maskView!!.setBackgroundColor(0x7f000000)
        maskView!!.fitsSystemWindows = false
        maskView!!.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                removeMask()
                return@OnKeyListener true
            }
            false
        })
        /**
         * 通过WindowManager的addView方法创建View，产生出来的View根据WindowManager.LayoutParams属性不同，效果也就不同了。
         * 比如创建系统顶级窗口，实现悬浮窗口效果！
         */
        windowManager.addView(maskView, wl)
    }

    private fun removeMask() {
        if (null != maskView) {
            windowManager.removeViewImmediate(maskView)
            maskView = null
        }
    }

    protected open fun initView(view: View) {

    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    internal inner class popDismissListener : PopupWindow.OnDismissListener {
        override fun onDismiss() {
            setBackgroundAttr(1f)
            onClose()
        }
    }

    protected fun onClose() {}

    @JvmOverloads
    fun showPopupDownWindow(x: Int = 0, y: Int = 0) {
        closePopupWindow()
        setBackgroundAttr(trans)
        popWindow.showAsDropDown(v, x, y)
        /*
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int y1 = location[1]+y;
        popWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y1 + v.getHeight());*/
    }

    fun showPopupDownWindowWihte(x: Int, y: Int) {
        closePopupWindow()
        popWindow.showAsDropDown(v, x, y)
        /* int[] location = new int[2];
        v.getLocationOnScreen(location);
        int y1 = location[1]+y;
        popWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y1 + v.getHeight());*/
    }

    fun showPopupGravityWindow(gravity: Int, offsetX: Int, offsetY: Int) {
        closePopupWindow()
        setBackgroundAttr(trans)
        popWindow.showAtLocation(v, gravity, offsetX, offsetY)
    }

    fun showAtLocation(gravity: Int, offsetX: Int, offsetY: Int) {
        closePopupWindow()
        setBackgroundAttr(trans)
        popWindow.showAtLocation(v, gravity, offsetX, offsetY)
    }

    fun showPopupCenterWindow() {
        setBackgroundAttr(trans)
        if ( !(context as Activity).isFinishing) {
            popWindow.showAtLocation(v, Gravity.CENTER, 0, 0)
        }
    }

    fun showPopupWindowUpCenter(
        view: View,
        popupHeight: Int,
        popupWidth: Int
    ) {
        //获取自身的长宽高
/*        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = contentView.getMeasuredHeight();
        int popupWidth = contentView.getMeasuredWidth();*/
//获取需要在其上方显示的控件的位置信息
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        //在控件上方显示    向上移动y轴是负数
        setBackgroundAttr(trans)
        popWindow.showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            location[0] + view.width / 2 - popupWidth / 2,
            location[1] - popupHeight
        )
    }

    fun showPopupWindowUpCenter(view: View) {
        //获取自身的长宽高
        contentView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        val popupHeight = contentView.measuredHeight
        val popupWidth = contentView.measuredWidth
        //获取需要在其上方显示的控件的位置信息
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        //在控件上方显示    向上移动y轴是负数
        setBackgroundAttr(trans)
        popWindow.showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            location[0] + view.width / 2 - popupWidth / 2,
            location[1] - popupHeight
        )
    }

    /**
     * 关闭popupwindow
     */
    fun closePopupWindow() {
        LogUtil.d("BetPop", "closePopupWindow----noShowRts:")
        if (popWindow.isShowing) {
            popWindow.dismiss()
        }
    }

    fun showPopupAtLocation(x: Int, y: Int) {
        closePopupWindow()
        setBackgroundAttr(trans)
        popWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y)
    }

    fun getHeight(): Int {
        return contentView.height
    }

    companion object {
        private const val TAG = "BasePopupWindow"
    }

    init {

        inflater = LayoutInflater.from(context)
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        initPop()
    }
}