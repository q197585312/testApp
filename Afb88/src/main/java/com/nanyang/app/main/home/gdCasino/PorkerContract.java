package com.nanyang.app.main.home.gdCasino;

import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface PorkerContract {
    interface View<T> extends IBaseView<T> {
        void initUi();
        void getMoneyMsg(TransferMoneyBean transferMoneyBean,String data);
        void onGetTransferMoneyData(int type,String data);
    }

    interface Presenter extends IBasePresenter {
        void getTransferMoneyData(String data);
        void gamesGDTransferMonet(String egLimit,String data);
    }
}
