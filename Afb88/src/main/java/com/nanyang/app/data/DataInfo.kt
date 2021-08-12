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
