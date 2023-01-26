package com.nanyang.app.main.home

import android.view.View

interface OnItemClickListener<T> {

    fun onItemClick(m: T)
}
interface OnViewClickListener {

    fun onClick(v: View)
}