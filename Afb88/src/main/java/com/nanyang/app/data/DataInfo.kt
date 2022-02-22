package com.nanyang.app.main.home

import com.nanyang.app.load.login.LoginInfo

data class HeaderInfo(var dbid: String, var g: String, var img: String)


/*        public MainBannersBean(String dbid, String g, String img) {
            this.dbid = dbid;
            this.g = g;
            this.img = img;
        }*/

class PasswordWfBean(
    ACT: String,
    lang: String,
    PT: String,
    var NewPass: String,
    var ConPass: String
) : LoginInfo.LanguageWfBean(ACT,lang,PT)

data class LoginResultData(
    var Authorization: String,
    /*{
        AFB = 0,
        I1BET99 = 2 ,
        NONE = 256,
        LIGA365 = 105,
        AU789 = 112,
        mbet666 = 113,
        Afbsports=3,
        EZ2888= 111,
        USUN= 114
    }*/
    var CompType: String,
    var LoginStatus: String,
    var Message: String
)
