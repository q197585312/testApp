package com.nanyang.app.main.home.sport.main

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.nanyang.app.*
import com.nanyang.app.data.Main
import com.nanyang.app.main.home.OnItemClickListener
import com.unkonw.testapp.libs.base.BaseApplication
import com.unkonw.testapp.libs.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

val listType = mutableListOf<String>(
    "all",
    "casino",
    "slot",
    "egame"
)

class SportOtherViewModel : BaseViewModel() {
    val showContent = MutableLiveData(false)
    val application: Application = BaseApplication.getInstance()

    lateinit var onItemClicked: (item: Main) -> Unit

    val mainBind: OnItemBind<Main> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.itemBean, R.layout.item_sport_image)
            .bindExtra(BR.itemClick, object : OnItemClickListener<Main> {
                override fun onItemClick(m: Main) {
                    onItemClicked(m)
                }

            })
    }
    var mainItemBinding = ItemBinding.of(mainBind)
    var mainContent = ObservableArrayList<Main>()
    var mainList: List<Main> = arrayListOf()

    var parentBg =
        if (BuildConfig.FLAVOR == "usun") R.mipmap.usun_background else R.drawable.rectangle_green_gradient


    fun loadOtherType(index: Int = 0) {
        println("loadOtherType:${index}")
        if (mainList.isNullOrEmpty()) {
            launchGo(block = {

                val agentUrl = BuildConfig.ImgConfig_URL
                val allImage = ApiServiceKt.instance.getAllImage(agentUrl)
                println("allImage.status:" + allImage.status)
                if (allImage.status == "0") {
                    withContext(Dispatchers.Main) {

//                    println("allImage.left:" + allImage.left)
//                    println("allImage.header:" + allImage.header)
                        allImage.left.map { itMap ->
                            when (itMap.type) {
                                "all" -> itMap.text = R.string.all_game//"ALL GAME"
                                "sport" -> itMap.text = R.string.SportBook//"SportBook"
                                "casino" -> itMap.text = R.string.Casino//"Casino"
                                "slot" -> itMap.text = R.string.slot//"slot"
                                "egame" -> itMap.text = R.string.e_sport//"E-Game"
                                else -> itMap.text = R.string.lottery_and_keno//"Lottery And Keno"
                            }
                        }

                        mainList = allImage.main
                        setGameName(mainList)
                        loadMainGame(index)
                    }

                } else {
                    loadMainGame(index)
                }
            },
                error = {
                    defUI.toastEvent.postValue(
                        "no agent !$it"
                    )
                }
            )
        } else {
            loadMainGame(index)
        }
    }

    var indexType = 0
    fun loadMainGame(index: Int) {
        if (index == 0) {
            showContent.postValue(false)
            indexType=index
            return
        }
        if (indexType == index) {
            showContent.postValue(!showContent.value!!)
        } else {
            showContent.postValue(true)
        }
        indexType=index
        val type = if (index < listType.size) listType[index] else "casino"

        var temp = arrayListOf<Main>()
        mainList.forEach {

            if (type == it.type) {
                temp.add(it)
            }
        }
        mainContent.clear()
        if (type != "sport" || (application as AfbApplication).showBall == 1) {
            mainContent.addAll(temp)
        }

        if (type != "sport" && (application as AfbApplication).H5MainChoose.length > 1) {
            val split = (application as AfbApplication).H5MainChoose.split(",")
            val filter = mainContent.filter {
                var tem = AfbUtils.changeId(it.g)
                split.contains(tem)
            }

            mainContent.clear()
            mainContent.addAll(filter)
            println("filter:${filter.size},$filter")
        }
        println("type:${type}  ,mainContent:${mainContent.size}$mainContent")

    }

    private fun setGameName(list: List<Main>) {
        for (main in list) {
            var g = main.g
            var img = main.img
            if (g == "1") {
                if (img.contains("soccer")) {
                    main.gameName = application.getString(R.string.Soccer)
                } else if (img.contains("keno")) {
                    main.gameName = application.getString(R.string.Keno)
                } else if (img.contains("fishing")) {
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