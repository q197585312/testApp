package com.nanyang.app.main.home.sport.mixparlayList;


import com.google.gson.Gson;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.unkonw.testapp.libs.api.Api.getService;

class MixOrderListPresenter extends BaseRetrofitPresenter<String, MixOrderListContract.View<String>> implements MixOrderListContract.Presenter {

    private ClearanceBetAmountBean selectedBean;

    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    MixOrderListPresenter(MixOrderListContract.View view) {
        super(view);
    }


    public void obtainListData() {
        baseView.obtainListData(((BaseToolbarActivity) (baseView)).getApp().getBetParList());
    }

    public void showBottomSelectedList() {
        if (((BaseToolbarActivity) (baseView)).getApp().getBetParList() == null) {
            baseView.obtainBottomData(Arrays.asList(new ClearanceBetAmountBean(1, "  X  1")));
            selectedBean = new ClearanceBetAmountBean(1, "");
            return;
        }
        List<BettingParPromptBean.BetParBean> betPar = ((BaseToolbarActivity) (baseView)).getApp().getBetParList().getBetPar();
        if (((BaseToolbarActivity) (baseView)).getApp().getBetParList() != null && betPar != null && betPar.size() > 2) {
            if (selectedBean == null || selectedBean.getTitle().equals(""))
                selectedBean = new ClearanceBetAmountBean(1, betPar.size() + "  X  1");
            int size = betPar.size();
            if (size == 3) {
                baseView.obtainBottomData(Arrays.asList(new ClearanceBetAmountBean(1, "3  X  1"), new ClearanceBetAmountBean(3, "3  X  3"), new ClearanceBetAmountBean(4, "3  X  4")));
            } else if (size == 4) {
                baseView.obtainBottomData(Arrays.asList(new ClearanceBetAmountBean(1, "4  X  1"), new ClearanceBetAmountBean(4, "4  X  4"), new ClearanceBetAmountBean(5, "4  X  5"), new ClearanceBetAmountBean(6, "4  X  6")));
            } else if (size == 5) {
                baseView.obtainBottomData(Arrays.asList(new ClearanceBetAmountBean(1, "5  X  1"), new ClearanceBetAmountBean(5, "5  X  5"), new ClearanceBetAmountBean(6, "5  X  6"), new ClearanceBetAmountBean(10, "5  X  10")
                        , new ClearanceBetAmountBean(16, "5  X  16"), new ClearanceBetAmountBean(20, "5  X  20"), new ClearanceBetAmountBean(26, "5  X  26")));
            } else if (size == 6) {
                baseView.obtainBottomData(Arrays.asList(new ClearanceBetAmountBean(1, "6  X  1"), new ClearanceBetAmountBean(6, "6  X  6"), new ClearanceBetAmountBean(7, "6  X  7"), new ClearanceBetAmountBean(15, "6  X  15"),
                        new ClearanceBetAmountBean(20, "6  X  20"), new ClearanceBetAmountBean(35, "6  X  35"), new ClearanceBetAmountBean(42, "6  X  42"), new ClearanceBetAmountBean(50, "6  X  50"), new ClearanceBetAmountBean(57, "6  X  57")));
            } else if (size == 7) {
                baseView.obtainBottomData(Arrays.asList(new ClearanceBetAmountBean(1, "7  X  1"), new ClearanceBetAmountBean(7, "7  X  7"), new ClearanceBetAmountBean(8, "7  X  8"), new ClearanceBetAmountBean(21, "7  X  21"), new ClearanceBetAmountBean(28, "7  X  28"),
                        new ClearanceBetAmountBean(29, "7  X  29"), new ClearanceBetAmountBean(35, "7  X  35"), new ClearanceBetAmountBean(56, "7  X  56"), new ClearanceBetAmountBean(70, "7  X  70"), new ClearanceBetAmountBean(91, "7  X  91"),
                        new ClearanceBetAmountBean(98, "7  X  98"), new ClearanceBetAmountBean(99, "7  X  99"), new ClearanceBetAmountBean(112, "7  X  112"), new ClearanceBetAmountBean(119, "7  X  119"), new ClearanceBetAmountBean(120, "7  X  120")));
            } else {
                baseView.obtainBottomData(Arrays.asList(new ClearanceBetAmountBean(1, betPar.size() + "  X  1")));
            }
        } else {
            baseView.obtainBottomData(Arrays.asList(new ClearanceBetAmountBean(1, "  X  1")));
            selectedBean = new ClearanceBetAmountBean(1, "");
        }

    }

    //http://a0096f.panda88.org/_Bet/ParRemove.aspx
//    http://a0096f.panda88.org/_bet/JRecPanel.aspx?g=2&b=even_par&oId=12225781&odds=18.2&isBP=1&RemoveId=12225358
    public void removeBetItem(BettingParPromptBean.BetParBean item) {

        String ParUrl = item.getParUrl();
        String url = ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId();

        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).getData(url))
//                    mApiWrapper.goMain()
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {
                        if (str.length() > 0) {
                            BettingParPromptBean bean = new Gson().fromJson(str, BettingParPromptBean.class);
                            baseView.obtainListData(bean);
                        } else {
                            baseView.obtainListData(null);
                        }

                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void removeAll() {
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).getData(AppConstant.getInstance().URL_SOCCER_REMOVE_MIX))
//                    mApiWrapper.goMain()
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
                        baseView.obtainListData(null);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}