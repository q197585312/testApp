package com.nanyang.app.main.person;

import com.nanyang.app.load.login.LoginInfo;

/**
 * Created by Administrator on 2019/5/23.
 */
//{"ACT":"SaveNick","PT":"wfMycountH50","Nickname":"11111","pgLable":"0.6380720331956468","vsn":"4.0.12"}&_db={}
class SetNameWfBean extends LoginInfo.LanguageWfBean {
    public SetNameWfBean(String ACT, String lang, String PT) {
        super(ACT, lang, PT);
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    String Nickname;

}
