package com.unkonw.testapp.libs.base

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unkonw.testapp.R
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder
import java.text.FieldPosition

import java.util.*

abstract class BaseListPopupWindow<T, DB : ViewDataBinding>(
    context: Context,
    v: View,
    width: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
    height: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
    var tv: TextView? = null,
    var tv1: TextView? = null
) : BasePopupWindow<DB>(
    context,
    v,
    width,
    height
) {
    var adapter: BaseRecyclerAdapter<T?>? = null
    var recyclerView: RecyclerView? = null
    private var data: List<T>? = null
    open var recyclerViewId: Int = R.id.base_rv

    fun setData(data: List<T>?) {
        this.data = data
        adapter!!.addAllAndClear(data)
    }

    val itemLayoutRes: Int
        get() = R.layout.register_base_test

    fun onConvert(
        holder: MyRecyclerViewHolder,
        position: Int,
        item: T?
    ) {
        val tv = holder.getView<TextView>(R.id.item_regist_text_tv)
        convertTv(tv, item)
    }

    override fun initView(view: View) {
        super.initView(view)
        recyclerView =
            view.findViewById<View>(recyclerViewId) as RecyclerView
        val mLayoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        recyclerView!!.layoutManager = mLayoutManager
        adapter = object : BaseRecyclerAdapter<T?>(
            context,
            ArrayList<T?>(),
            itemLayoutRes
        ) {

            override fun convert(holder: MyRecyclerViewHolder, position: Int, item: T?) {

                onConvert(holder, position, item)
            }
        }
        (adapter as BaseRecyclerAdapter<T?>).setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener<T> { _, item, position ->
            if (tv1 != null) {
                tv1?.text = "OK"
                tv1?.visibility = View.VISIBLE
            } else {
                clickItem(tv, item)
            }
            closePopupWindow()
        })
        recyclerView!!.adapter = adapter
    }

    protected fun clickItem(tv: TextView?, item: T?) {}
    protected fun convertTv(tv: TextView?, item: T?) {}
    override fun onSetLayoutRes(): Int {
        return R.layout.layout_base_recycler_view
    }

}