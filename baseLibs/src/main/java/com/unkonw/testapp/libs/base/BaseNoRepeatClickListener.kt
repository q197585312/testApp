package com.unkonw.testapp.libs.base

import android.view.View
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter

var lastActClickTime = 0L
fun checkClick(action: () -> Unit) {
    val currentTime = System.currentTimeMillis()
    if (lastActClickTime != 0L && (currentTime - lastActClickTime < 200)) {
        return
    }
    lastActClickTime = currentTime
    action.invoke()
}

abstract class BaseNoRepeatClickListener<T> : OnItemClickListener<T> {


    abstract fun noRepeatClick(v: View, data: T)

    override fun onItemClick(v: View, m: T) {
        checkClick {
            noRepeatClick(v, m)
        }
    }

}