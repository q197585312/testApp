package com.unkonw.testapp.libs.widget

import android.content.Context
import android.view.View
import com.unkonw.testapp.R


open class PopOneBtn(context: Context?, v: View) : BaseYseNoChoosePopupWindow(context, v) {
    override fun initView(view: View) {
        super.initView(view)
        //点击空白处时，隐藏掉pop窗口

        chooseCancelTv.visibility = View.GONE
        lineCenter.visibility = View.GONE
        view.setBackgroundResource(R.color.white)
    }


}