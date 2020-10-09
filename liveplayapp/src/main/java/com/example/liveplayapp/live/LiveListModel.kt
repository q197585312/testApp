package com.example.liveplayapp.live

import androidx.databinding.ObservableArrayList
import com.example.liveplayapp.BR
import com.example.liveplayapp.BuildConfig


import com.example.liveplayapp.R
import com.unkonw.testapp.login.DataBean
import gaming178.com.casinogame.base.BaseApiViewModel
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.CoroutineScope
import me.tatarka.bindingcollectionadapter2.ItemBinding
import okhttp3.MediaType
import okhttp3.RequestBody

public class LiveListModel : BaseApiViewModel() {
    fun initListLive( updateList:(MutableList<Cartoon>) -> Unit ) {
        launchGo(block = {
            val agentUrl = "${BuildConfig.API_HOST}api/pgGetTVID"
            var body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                LiveDataRequestBean().toJson()
            )
//            val liga365AgentBean = api.doPostLiveJson(agentUrl,body)
            val str = api.doPostJson(agentUrl, body)
            var data1 = Data(
                Away = "奥地利克拉根福",
                SocOddsId = 897789,
                TVUrl = "",
                GameId = 1,
                Home = "前进斯太尔",
                IsRun = "False",
                League = "奥地利乙组联赛",
                OddsNum = 1,
                RTSUrl = "https://13.112.86.40/Videos/APILiveCast.aspx?quer=5947383e24af803dacc5f3593605bd607e6c4bb5a85fb508596397fbb5eb44bcee7ea5cec0d0c28cd0bc0bb09c3060e8b911ffa5e2f1185bc61d442cbdf39bc060071b0a247e4088, SocOddsId=2913268, TVUrl=https://13.112.86.40/Videos/APIPlayVideo.html?id=39050ae01aff9154da65819ff3927b5afc9e5bad75d784f924cc6a83155e66d090c782f6a0163c8a633e079a94252943584cb444a118bb4caa2f6bc37c25d039"
            )
            var data2 = Data(
                Away = "sdhasdih",
                SocOddsId = 897789,
                TVUrl = "",
                GameId = 1,
                Home = "nxxxx",
                IsRun = "False",
                League = "奥地利乙组联赛",
                OddsNum = 1,
                RTSUrl = "https://13.112.86.40/Videos/APILiveCast.aspx?quer=5947383e24af803dacc5f3593605bd607e6c4bb5a85fb508596397fbb5eb44bcee7ea5cec0d0c28cd0bc0bb09c3060e8b911ffa5e2f1185bc61d442cbdf39bc060071b0a247e4088, SocOddsId=2913268, TVUrl=https://13.112.86.40/Videos/APIPlayVideo.html?id=39050ae01aff9154da65819ff3927b5afc9e5bad75d784f924cc6a83155e66d090c782f6a0163c8a633e079a94252943584cb444a118bb4caa2f6bc37c25d039"

            )


            println(str)
            var cartoon1 = Cartoon(data1)
            var cartoon2 = Cartoon(data2)

            val cartoonList = mutableListOf<Cartoon>()

            cartoonList.add(cartoon1)
            cartoonList.add(cartoon2)

        },
            error = {
                defUI.toastEvent.postValue(
                    "no agent !$it"
                )
            }
        )

    }

    val items = ObservableArrayList<Data>()
    val aaa =
        ItemBinding.of<Data>(BR.itemModel, R.layout.live_play_item)
//            .bindExtra(BR.itemModel, this)

}
