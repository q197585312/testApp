package com.nanyang.app.common;

import com.unkonw.testapp.libs.base.IBaseView;

/**
 * Created by Administrator on 2017/4/19.
 */

public interface ILanguageView<T> extends IBaseView<T> {
    void onLanguageSwitchSucceed(String str);

    //    void getMoneyMsg(TransferMoneyBean transferMoneyBean, String data);
    void onGetTransferMoneyData(int type, String getBackStr, String data);

    void onLoginAgainFinish(String gameType);
}
