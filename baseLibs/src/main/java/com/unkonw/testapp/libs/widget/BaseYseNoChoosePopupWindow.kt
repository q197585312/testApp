package com.unkonw.testapp.libs.widget

import android.content.Context
import android.view.View
import android.widget.TextView
import com.unkonw.testapp.R

abstract class BaseYseNoChoosePopupWindow : BasePopupWindow {
    lateinit var chooseTitleTv: TextView
    lateinit var chooseMessage: TextView
    lateinit var chooseSureTv: TextView
    lateinit var chooseCancelTv: TextView
    lateinit var lineCenter: View

    constructor(context: Context?, v: View?) : super(context, v) {}
    constructor(context: Context?, v: View?, width: Int, height: Int) : super(
        context,
        v,
        width,
        height
    ) {
    }

    override fun initView(view: View) {
        super.initView(view)
        lineCenter = view.findViewById(R.id.line_center)
        chooseTitleTv = view.findViewById<View>(R.id.choose_title_tv) as TextView
        chooseMessage = view.findViewById<View>(R.id.choose_message_tv) as TextView
        chooseSureTv = view.findViewById<View>(R.id.choose_sure_tv) as TextView
        chooseCancelTv = view.findViewById<View>(R.id.choose_cancel_tv) as TextView
        chooseCancelTv.setOnClickListener { v -> clickCancel(v) }
        chooseSureTv.setOnClickListener { v -> onClickSure(v) }
    }

    protected open fun onClickSure(v: View?) {
        clickSure(v)
        closePopupWindow()
    }

    protected open fun clickSure(v: View?) {}
    protected open fun clickCancel(v: View?) {
        closePopupWindow()
    }

    override fun onSetLayoutRes(): Int {
        return R.layout.popupwindow_base_yes_choose
    }
}