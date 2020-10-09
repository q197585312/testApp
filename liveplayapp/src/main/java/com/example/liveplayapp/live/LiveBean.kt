package com.example.liveplayapp.live

import androidx.databinding.ObservableField
import com.google.gson.Gson

internal data class LiveDataRequestBean(
    val ACT: String = "GetAllTV",
    val ip: String = "119.85.109.214",
    val CompanyKey: String = "ALL",
    val Lang: String = "zh"
){
    fun toJson():String{
        return Gson().toJson(this)
    }
}
data class liveListBean(
    val `data`: List<Data>
)

data class Data(
    val Away: String,
    val GameId: Int,
    val Home: String,
    val IsRun: String,
    val League: String,
    val OddsNum: Int,
    val RTSUrl: String,
    val SocOddsId: Int,
    val TVUrl: String
)
class Cartoon(cover: Data){
    var Away : ObservableField<String> = ObservableField()
    var Home : ObservableField<String> = ObservableField()
    var IsRun : ObservableField<String> = ObservableField()
    var League : ObservableField<String> = ObservableField()
    var RTSUrl : ObservableField<String> = ObservableField()
    var TVUrl : ObservableField<String> = ObservableField()

    var GameId : ObservableField<Int> = ObservableField()
    var OddsNum : ObservableField<Int> = ObservableField()
    var SocOddsId : ObservableField<Int> = ObservableField()

    init {
        this.Away.set(cover.Away)
        this.Home.set(cover.Home)
        this.IsRun.set(cover.IsRun)
        this.League.set(cover.League)
        this.RTSUrl.set(cover.RTSUrl)
        this.TVUrl.set(cover.TVUrl)
        this.GameId.set(cover.GameId)
        this.OddsNum.set(cover.OddsNum)
        this.SocOddsId.set(cover.SocOddsId)
    }
}