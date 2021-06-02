package com.nanyang.app.main.home

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.nanyang.app.ApiServiceKt
import com.nanyang.app.BR
import com.nanyang.app.BuildConfig
import com.nanyang.app.data.GamesData
import com.nanyang.app.data.Left
import com.nanyang.app.data.Main

import com.nanyang.app.R
import com.unkonw.testapp.libs.base.BaseApplication
import com.unkonw.testapp.libs.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

public class HomeViewModel : BaseViewModel() {
    val headerBgUrl = BuildConfig.ImgHeader_URL
    private lateinit var onItemClick: OnItemClickListener<Main>
    private lateinit var onLeftItemClick: OnItemClickListener<Left>
    var heightLeft = 90f
    fun setItemClick(onItemClick: OnItemClickListener<Main>) {
        this.onItemClick = onItemClick
    }

    fun setLeftItemClick(onItemClick: OnItemClickListener<Left>) {
        this.onLeftItemClick = onItemClick
    }


    val headerBind: OnItemBind<Main> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.itemBean, R.layout.item_home_image)
            .bindExtra(BR.itemClick, onItemClick)

    }
    var headerItemBinding = ItemBinding.of(headerBind)

    var headers = ObservableArrayList<Main>()

    val leftBind: OnItemBind<Left> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.itemBean, R.layout.item_left_image)
            .bindExtra(BR.itemClick, onLeftItemClick)
            .bindExtra(BR.heightLeft, heightLeft)
    }
    var leftItemBinding = ItemBinding.of(leftBind)
    var left = ObservableArrayList<Left>()


    val mainBind: OnItemBind<Main> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.itemBean, R.layout.item_main_image)
            .bindExtra(BR.itemClick, onItemClick)
    }
    var mainItemBinding = ItemBinding.of(mainBind)
    var mainContent = ObservableArrayList<Main>()
    var mainList: List<Main> = arrayListOf()
    var selectedType: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = "sport"
    }


    fun loadAllPic(loadAllUi: (m: GamesData) -> Unit) {
        launchGo(block = {
            val agentUrl = BuildConfig.ImgConfig_URL
            val allImage = ApiServiceKt.instance.getAllImage(agentUrl)
            println("allImage.status:" + allImage.status)
            if (allImage.status == "0") {
                withContext(Dispatchers.Main) {
                    left.clear()
                    headers.clear()

                    loadAllUi(allImage)
                    println("allImage.left:" + allImage.left)
                    println("allImage.header:" + allImage.header)
                    allImage.left.map { itMap ->
                        when (itMap.type) {
                            "sport" -> itMap.text = "SportBook"
                            "casino" -> itMap.text = "Casino"
                            "slot" -> itMap.text = "slot"
                            "egame" -> itMap.text = "E-Game"
                            else -> itMap.text = "Lottery And Keno"
                        }
                    }
                    left.addAll(allImage.left)
                    if (allImage.header.isNullOrEmpty())
                        headers.addAll(allImage.header)
                    selectedType.value = "sport"
                    mainList = allImage.main
                    loadMainGame(selectedType.value!!)
                }
            }


        },
            error = {
                defUI.toastEvent.postValue(
                    "no agent !$it"
                )
            }
        )
    }

    public fun loadMainGame(type: String) {

        var temp = arrayListOf<Main>()
        mainList.forEach {
            if (type.equals(it.type)) {
                temp.add(it)
            }
        }
        mainContent.clear()
        mainContent.addAll(temp)
        println("type:${type}  ,mainContent:${mainContent.size}$mainContent")

    }

}