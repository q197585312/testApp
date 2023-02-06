package com.nanyang.app.main.home.sport.main

import androidx.annotation.DrawableRes
import com.unkonw.testapp.libs.base.BaseVMActivity

open class MenuItemInfo(
    var keyName: String = "",
    var keyValue: String = "",
    var moreHint: String = "",
    var clickToDo: ((context: BaseVMActivity<*, *>) -> Unit)? = null,//item 点击事件

    var imgUrl: String = "",
    @DrawableRes
    var leftImgRes: Int = 0,      //左边文字arrow
    @DrawableRes
    var rightImgRes: Int = 0,
    @DrawableRes
    var itemRes: Int = 0,       //item 图标

    var type: Int = 0,    //跳转类型
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as MenuItemInfo
        if (keyName != other.keyName && type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        return keyName.hashCode() + type.hashCode()
    }
}