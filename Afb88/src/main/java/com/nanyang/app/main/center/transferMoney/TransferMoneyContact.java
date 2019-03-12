package com.nanyang.app.main.center.transferMoney;

import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.base.IBaseView;

/**
 * Created by Administrator on 2017/3/30.
 */

public class TransferMoneyContact {
    interface View extends IBaseView<TransferMoneyBean> {
        void onGetTransferMoneyData(String s);

        void onGetCashoutMoneyData(String s);

        void refreshEdt(int type);
    }

    interface Presenter extends IBasePresenter {
        void getTransferMoneyData();

        void gamesECashOutMonet(String money);

        void gamesETransferMonet(String egLimit);

        void gamesGDCashOutMonet(String money);

        void gamesGDTransferMonet(String egLimit);

        void games855CashOutMonet(String money);

        void games855TransferMonet(String egLimit);

        void gamesW88CashOutMonet(String money);

        void gamesW88TransferMonet(String egLimit);
    }
}
