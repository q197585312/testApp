package gaming178.com.casinogame.load

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import gaming178.com.baccaratgame.R


import gaming178.com.casinogame.base.BaseApiViewModel
import gaming178.com.casinogame.login.MenuItemInfo

class LanguageModel : BaseApiViewModel() {
/*    var itemBinding =
        ItemBinding.of<MenuItemInfo<String>>(BR.itemBean, R.layout.gd_item_language_selected_br)
            .bindExtra(BR.itemModel, this)*/
    var items = ObservableArrayList<MenuItemInfo<String>>()
    var languageType = MutableLiveData<String>()
    val resSelectedRes = R.mipmap.oval_blue_point_12

}

