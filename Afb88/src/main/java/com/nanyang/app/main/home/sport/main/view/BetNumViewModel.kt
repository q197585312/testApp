package com.nanyang.app.main.home.sport.main.view


import androidx.recyclerview.widget.GridLayoutManager
import com.nanyang.app.R
import com.unkonw.testapp.libs.base.BasePageViewModel
import com.nanyang.app.main.home.sport.main.MenuItemInfo
import com.unkonw.testapp.libs.base.BaseApplication


class BetNumViewModel:BasePageViewModel<MenuItemInfo>() {
    fun setData(list:List<MenuItemInfo> ) {
        items.clear()
        items.addAll(list)
    }
    var layoutManager=GridLayoutManager(BaseApplication.getInstance(),6)

    override fun getItemLayoutId()= R.layout.layout_grid_recycle_content_item
}