package com.nanyang.app.main.DepositAndWithdraw;

import com.nanyang.app.main.DepositAndWithdraw.Bean.AutoDepositBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.DepositDataBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.UploadImgBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawDataBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawHistoryBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawTakeAwayBean;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.List;

public abstract class DepositWithdrawBaseFragment extends BaseFragment<DepositWithdrawPresenter> {
    @Override
    public void initData() {
        super.initData();
        createPresenter(new DepositWithdrawPresenter(this));
    }

    public void onGetWithdrawData(WithdrawDataBean bean) {

    }

    public void onGetSubmitWithdrawData(WithdrawTakeAwayBean bean) {

    }

    public void onGetWithdrawHistoryData(List<WithdrawHistoryBean> list) {

    }

    public void onGetUploadImgData(UploadImgBean bean) {

    }

    public void onGetDepositData(List<DepositDataBean> list) {

    }

    public void onGetSubmitDepositData(String msg) {

    }

    public void onGetDepositHistoryData(List<WithdrawHistoryBean> list) {

    }

    public void onGetAutoDepositData(List<AutoDepositBean> list) {

    }

    public void onGetSubmitAutoDepositData(String msg) {

    }

}
