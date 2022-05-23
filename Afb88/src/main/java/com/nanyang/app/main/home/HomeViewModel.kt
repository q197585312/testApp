package com.nanyang.app.main.home

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.nanyang.app.*
import com.nanyang.app.data.GamesData
import com.nanyang.app.data.Left
import com.nanyang.app.data.Main
import com.unkonw.testapp.libs.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

public class HomeViewModel : BaseViewModel() {
    lateinit var application: Application
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
                            "all" -> itMap.text = "ALL GAME"
                            "sport" -> itMap.text = "SportBook"
                            "casino" -> itMap.text = "Casino"
                            "slot" -> itMap.text = "slot"
                            "egame" -> itMap.text = "E-Game"
                            else -> itMap.text = "Lottery And Keno"
                        }
                    }
                    left.addAll(allImage.left)
                    if (!allImage.header.isNullOrEmpty())
                        headers.addAll(allImage.header)
                    selectedType.value = "all"
                    if (BuildConfig.FLAVOR == "ez2888") {
                        selectedType.value = "sport"
                    }
                    mainList = allImage.main
                    setGameName(mainList)
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

    private fun setGameName(list: List<Main>) {
        for (main in list) {
            var g = main.g
            var img = main.img
            if (g == "1") {
                if (img.contains("soccer")) {
                    main.gameName = application.getString(R.string.Soccer)
                } else if (img.contains("Keno")) {
                    main.gameName = application.getString(R.string.Keno)
                } else if (img.contains("Fishing")) {
                    main.gameName = "Fishing"
                }
            } else if (g == "Casino") {
                main.gameName = application.getString(R.string.gd88_casino)
            } else if (g == "SA CASINO") {
                main.gameName = application.getString(R.string.SA_CASINO)
            } else if (g == "WM CASINO") {
                main.gameName = application.getString(R.string.WM_Cashio)
            } else if (g == "DG CASINO") {
                main.gameName = application.getString(R.string.DG_Cashio)
            } else if (g == "SEXY CASINO") {
                main.gameName = application.getString(R.string.SEXY_CASINO)
            } else if (g == "PRAGMATIC CASINO") {
                main.gameName = "Game"
            }
        }
    }
}