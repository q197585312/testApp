package com.unkonw.testapp.libs.base

import android.graphics.drawable.Drawable
import android.text.Html
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.unkonw.testapp.libs.utils.AutoUtils
import com.unkonw.testapp.libs.view.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapterUtil {
    @JvmStatic
    @BindingAdapter("visibleUnless")
    fun bindVisibleUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("goneUnless")
    fun bindGoneUnless(view: View, gone: Boolean) {
        view.visibility = if (gone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["android:background"], requireAll = false)
    fun setBackgroundResource(view: View, resource: Int) {
        view.setBackgroundResource(resource);
    }

    @JvmStatic
    @BindingAdapter(value = ["android:src"], requireAll = false)
    fun setImageViewResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource);
    }

    @JvmStatic
    @BindingAdapter(value = ["imgUrl"], requireAll = true)
    fun setImgUrl(imageView: ImageView, url: String?) {

        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    /**
     * 由时间戳转日期格式
     */
    @JvmStatic
    @BindingAdapter(value = ["timestamp"], requireAll = false)
    fun timeStamp2Date(textView: TextView, timestamp: Long) {
        var sdf = SimpleDateFormat("YYYY/mm/dd")
        val date = Date(timestamp)
        textView.setText(sdf.format(date) + " 发布：")
    }


    @JvmStatic
    @BindingAdapter(value = ["borderWidth"], requireAll = false)
    fun borderWidth(imageView: CircleImageView, width: Int) {
        imageView.setBorder(width)
//        imageView.invalidate()
    }
    @JvmStatic
    @BindingAdapter(value = ["layoutHeight"], requireAll = false)
    fun layoutHeight(view:View, height: Float) {
        view.layoutParams.height=AutoUtils.dp2px(view.context,height)
//        view.invalidate()
    }
//    android:layout_height="@{heightLeft}"
}



