package com.unkonw.testapp.libs.base

import android.view.View

interface OnItemClickListener<T> {

    fun onItemClick(v: View,m: T)
}
interface OnViewClickListener {

    fun onClick(v: View)
}

