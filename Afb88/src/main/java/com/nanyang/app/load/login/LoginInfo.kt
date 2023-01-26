package com.nanyang.app.load.login

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nanyang.app.AppConstant
import com.nanyang.app.Utils.StringUtils
import java.io.Serializable
import java.util.*

/**
 *
 */
class LoginInfo {
    fun get__VIEWSTATE(): String {
        return __VIEWSTATE
    }

    fun set__VIEWSTATE(__VIEWSTATE: String) {
        this.__VIEWSTATE = __VIEWSTATE
    }

    fun get__EVENTVALIDATION(): String {
        return __EVENTVALIDATION
    }

    fun set__EVENTVALIDATION(__EVENTVALIDATION: String) {
        this.__EVENTVALIDATION = __EVENTVALIDATION
    }

    /* __VIEWSTATE	/wEPDwUKLTE5MDg0OTk2OA9kFgJmD2QWBAIBDxYCHgVzdHlsZQUxYmFja2dyb3VuZDp1cmwoaW1hZ2VzL2xvZ2luX0VOLVVTLnBuZykgbm8tcmVwZWF0OxYCZg9kFgICBw8PFgIeBFRleHQFBUxvZ2luZGQCAg8WAh8ABTliYWNrZ3JvdW5kOnVybChpbWFnZXMvbGlzdF9FTi1VUy5wbmcpIG5vLXJlcGVhdCBjZW50ZXIgMDtkZNRh3MZ0akiK/k7BU0DrdOa2KqSQAkPJBUPNRKepAPLW
        __EVENTVALIDATION	/wEdAAYfMKBnGzoFoip6aJJE6fpaiHrx0gRh57qNYHb+gYJUfmN6ZYJNGAQHn0c9zMgZU3A/F856zktixbVekFwCZlBeynuqL3cQJfeeYQ4/gSeOva+f28GNV31+z65DbDqxMS5lXRgroCj8wpuoKkde4QtUV2EKHgwjnQ91GIjhKi6heQ==
        lstLang	Default.aspx?lang=EN-US
        txtUserName	demoafbai1
        password_clear	PASSWORD
        password_password	123123aa
        btnSignIn	Login*/
    private var __VIEWSTATE =
        "/wEPDwULLTE1NzgzODQwNTIPZBYCZg9kFgRmDxYCHgVzdHlsZQUxYmFja2dyb3VuZDp1cmwoaW1hZ2VzL2xvZ2luX0VOLVVTLnBuZykgbm8tcmVwZWF0OxYCZg9kFgICCQ8PFgIeBFRleHQFBUxvZ2luZGQCAQ8WAh8ABTliYWNrZ3JvdW5kOnVybChpbWFnZXMvbGlzdF9FTi1VUy5wbmcpIG5vLXJlcGVhdCBjZW50ZXIgMDtkZFQZd7i8stdYFQuPOPwiVWF56AvyxMUH0QGpyPjYhlg5"
    private var __EVENTVALIDATION =
        "/wEdAAYpPXKEnSgwS5OM83/1znbNY3plgk0YBAefRz3MyBlTcD8XznrOS2LFtV6QXAJmUF7Ke6ovdxAl955hDj+BJ469iHrx0gRh57qNYHb+gYJUfq+f28GNV31+z65DbDqxMS7eqPw3s2V7Bn4zHu2v1nVEy/P4mxqMWRPvxWg3VzRGSA=="
    var lstLang = "Default.aspx?lang=EN-US"
    var txtUserName: String? = null
    var password_clear = "PASSWORD"
    var password_password: String? = null
    var btnSignIn = "Login"

    constructor(txtUserName: String?, password_password: String?) {
        this.txtUserName = txtUserName
        this.password_password = password_password
    }

    constructor() {}

    //        map.put("password_clear", password_clear);
    //        map.put("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR);
    val map: Map<String, String?>
        get() {
            val map: MutableMap<String, String?> = HashMap()
            map["lstLang"] = lstLang
            map["txtUserName"] = txtUserName
            //        map.put("password_clear", password_clear);
            map["password_password"] = password_password
            map["btnSignIn"] = btnSignIn
            map["__VIEWSTATE"] = __VIEWSTATE
            map["__EVENTVALIDATION"] = __EVENTVALIDATION
            //        map.put("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR);
            return map
        }

    override fun toString(): String {
        return "__VIEWSTATE=" + __VIEWSTATE +
                "&__EVENTVALIDATION=" + __EVENTVALIDATION +
                "&lstLang=" + lstLang +
                "&txtUserName=" + txtUserName +
                "&password_clear=" + password_clear +
                "&password_password=" + password_password +
                "&btnSignIn=" + btnSignIn
    }

    /*_db	{}
    _fm	{"ACT":"Login","ID":"zmb2","PW":"12345678","lang":"","pgLable":"0.8980293281634196","vsn":"4.0.121","PT":"wfDefault0"}*/
    fun getWfmain(ACT: String?, Lang: String?): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        map["_fm"] = LoginWfBean(ACT, Lang).json
        return map
    }

    fun getWfmainJson(ACT: String?, Lang: String?): String {
        return LoginWfBean(ACT, Lang).json
    }

    inner class LoginWfBean : Serializable {
        //        _fm	{"ACT":"Login","ID":"Demoafba0310","PW":"123456aa","lang":"","pgLable":"0.15504609525960888","vsn":"4.0.12","PT":"wfDefault0"}
        constructor(ACT: String?, lang: String?) {
            this.ACT = ACT
            this.lang = lang
        }

        constructor(
            ACT: String?,
            ID: String?,
            PW: String?,
            lang: String?,
            pgLable: String?,
            vsn: String?,
            PT: String
        ) {
            this.ACT = ACT
            this.ID = ID
            this.PW = PW
            this.lang = lang
            this.PT = PT
        }

        var ACT: String? = "Login"
        var ID = txtUserName
        var PW = password_password
        var lang: String? = ""
        var pgLable = "0.15504609525960888"
        var vsn = "4.0.12"
        var ISPC = "0"
        var AppType = "AndroidApp"
        var PT = "wfMainH50"//wfMainH50
        val json: String
            get() = Gson().toJson(this)
    }

    open class LanguageWfBean : Serializable {
        constructor(lang: String) {
            this.lang = lang
        }

        // {"ACT":"selectleague","PT":"wfSportsH50","OT":"r","Timess":"","dbid":"1","dbid2":"1","haspar":"0","pgLable":"0.17478179812837813","vsn":"4.0.12"}
        open var ACT = "GetTT"

        constructor() {}

        open var Acc = ""
        open var accType = ""
        open var lang = ""
        open var pgLable = "0.6398654664343417"
        open var vsn = "4.0.12"
        open var IsMobile = "1"
        open var PT = AppConstant.wfMain

        constructor(ACT: String, lang: String, PT: String) {
            this.ACT = ACT
            this.lang = lang
            this.PT = PT
        }

        val json: String
            get() = Gson().toJson(this)
        val map: HashMap<String, String>
            get() = Gson().fromJson(
                json, object : TypeToken<HashMap<String?, String?>?>() {}.type
            )
    }

    class Lucky361WfBean(ACT: String, lang: String, PT: String) : LanguageWfBean(ACT, lang, PT) {
        var ProviderName: String = "AFBGaming"
        var IsAFBGame: String = "false"

    }

    class AfbGameWfBean(ACT: String, lang: String, PT: String) : LanguageWfBean(ACT, lang, PT) {
        var ProviderName: String = "AFBGaming"
        var IsAFBGame: String = "true"

    }
    class JILIGameWfBean(ACT: String, lang: String, PT: String) : LanguageWfBean(ACT, lang, PT) {
        var ProviderName: String = "JILI"
    }

    class HuayDataWfBean(override var ACT: String,override var lang: String,override var PT: String) :
        LanguageWfBean(ACT, lang, PT) {
        open var type: String=""

    }

    class HuayBetWfBean(ACT: String, lang: String, PT: String) : LanguageWfBean(ACT, lang, PT) {
        var typed: String = ""
        var txtNumber1: String = ""
        var txtAmt: String = ""
        var socOddsId: String = ""
    }

    /*{"ACT":"Savesort",
    "PT":"wfSettingH50",
    "MarketTyped":"0",
    "DefaultSortingd":"0",
    "ScoreSoundd":"1",
    "lang":"EN-US",
    "accType":"ID",
    "AmtS":"0",
    "BetterOdds":"1",
    "ChipsList":"",
    "pgLable":"0.5134303879466173","vsn":"4.0.12"}*/
    class SettingWfBean(ACT: String, lang: String, PT: String) : LanguageWfBean(ACT, lang, PT) {
        var MarketTyped: String = ""
        var DefaultSortingd: String = ""
        var ScoreSoundd: String = ""
        var AmtS: String = ""
        var MixParAmt: String = ""
        var ParAmt: String = ""
        var BetterOdds: String = ""
        var ChipsList: String = ""
        var HideChip: String = ""
        var LiveCentre: String = ""
        var H5MainChoose: String = ""

    }

    class OutRightWfBean(var ot: String, OUTDBID: String, accType: String, mt: String, ov: Int) {
        /**
         * ACT : LOS
         * DBID : 999
         * ot : t
         * tf : -1
         * OUTDBID : 2_11
         * timess :
         * accType : EU
         * ov : 0
         * mt : 0
         * pgLable : 0.6073571478712172
         * vsn : 4.0.12
         * PT : wfMainH50
         */
        var aCT = "LOS"
        var dBID = 999
        var tf = -1
        var oUTDBID = "1_11"
        var timess = ""
        var accType: String
        var ov = 0
        var mt: String
        var pT = AppConstant.wfMain
        fun toJson(): String {
            return Gson().toJson(this)
        }

        init {
            if (!StringUtils.isNull(OUTDBID)) oUTDBID = OUTDBID
            this.accType = accType
            this.mt = mt
            this.ov = ov
        }
    }

    class AllRunningWfBean(var ot: String, DBID: String, accType: String) {
        /**
         * {"ACT":"LOS","DBID":"36","ot":"r","tf":"-1","timess":"","accType":"EU","pgLable":"0.5393305075227944","vsn":"4.0.12","PT":"wfMainH50"}&_db={}
         */
        var aCT = "LOS"
        var dBID = "1"
        var tf = -1
        var timess = ""
        var accType: String
        var pT = AppConstant.wfMain
        fun toJson(): String {
            return Gson().toJson(this)
        }

        init {
            if (!StringUtils.isNull(DBID)) dBID = DBID
            this.accType = accType
        }
    }
}
open class JsonBaseBean:Serializable{
   open fun toJson(): String {
        return Gson().toJson(this)
    }
}
open class RequestBaseBean(open val ACT: String): JsonBaseBean()
class LiveTvBeanGL(ACT: String, val GLiveId:String ):RequestBaseBean(ACT)
class LiveTvBeanIMG(ACT: String, val IMGId:String ):RequestBaseBean(ACT)
