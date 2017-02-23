package com.nanyang.app.load.register;


import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.unkonw.testapp.libs.api.Api.getService;

public class RegisterPresenter extends BaseRetrofitPresenter<String, RegisterContract.View> implements RegisterContract.Presenter {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public ApiRegister createRetrofitApi() {
        return new ApiRegister();
    }

    /* @Override
     public void login(Map<String,String> info) {


         Call<String> call = mApiWrapper.getData( info);
         call.enqueue(new Callback<String>() {//异步请求
             @Override
             public void onResponse(Call<String> call, final Response<String> response) {
                 if (response.isSuccessful() ) {
                     baseView.onGetData(response.body());
                 } else {
                     baseView.onGetData("失败");
                 }

             }

             @Override
             public void onFailure(Call<String> call, final Throwable t) {
                 baseView.onGetData(t.getMessage());
             }
         });
     }*/
    public Map<String, String> currencyMap;
    public List<String> currencyList;
    public List<String> bankList;

    @Override
    public void register(RegisterInfo info) {
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).Register(info.getRegisterMap()))
//                mApiWrapper.getPersonalInfo(info)
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
                        baseView.onGetData(Str);
                        baseView.hideLoadingDialog();
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

    @Override
    public void checkUserName(RegisterInfo info) {
        Disposable disposable = mApiWrapper.applySchedulers(getService(ApiService.class).checkUserName(info.getCheckUserMap()))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseView.onGetData(s);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.toString());
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Long.MAX_VALUE);
                        baseView.showLoadingDialog();
                    }
                });
        mCompositeSubscription.add(disposable);
    }

    @Override
    public void initBank() {
        bankList = new ArrayList<>();
        bankList.add("MONEYBOOKERS");
        bankList.add("NGUYEN KHAC HOANG PHUONG");
        bankList.add("ACB");
        bankList.add("BANGKOK BANK");
        bankList.add("BIDV");
        bankList.add("DONG A");
        bankList.add("Eximbank");
        bankList.add("KASIKORN BANK");
        bankList.add("KRUNG THAI BANK");
        bankList.add("PAYPAL");
        bankList.add("SACOM");
        bankList.add("SIAM COMERCIAL BANK");
        bankList.add("TECHCOM");
        bankList.add("VIETIN");
        bankList.add("Vietcombank");
        bankList.add("VP Bank");
    }

    @Override
    public void initCurrency() {
        currencyList = new ArrayList<>();
        currencyMap = new HashMap<>();
        currencyList.add("BDT");
        currencyMap.put("BDT", "33");
        currencyList.add("CAD");
        currencyMap.put("CAD", "29");
        currencyList.add("CNY");
        currencyMap.put("CNY", "28");
        currencyList.add("EUR");
        currencyMap.put("EUR", "22");
        currencyList.add("GBT");
        currencyMap.put("GBT", "18");
        currencyList.add("HKD");
        currencyMap.put("HKD", "17");
        currencyList.add("ILS");
        currencyMap.put("ILS", "24");
        currencyList.add("IRS");
        currencyMap.put("IRS", "32");
        currencyList.add("JPY");
        currencyMap.put("JPY", "19");
        currencyList.add("KRW");
        currencyMap.put("KRW", "20");
        currencyList.add("MMK");
        currencyMap.put("MMK", "27");
        currencyList.add("MRG");
        currencyMap.put("MRG", "11");
        currencyList.add("MYR");
        currencyMap.put("MYR", "3");
        currencyList.add("NTD");
        currencyMap.put("NTD", "21");
        currencyList.add("PHP");
        currencyMap.put("PHP", "25");
        currencyList.add("PTS");
        currencyMap.put("PTS", "23");
        currencyList.add("RMB");
        currencyMap.put("RMB", "12");
        currencyList.add("SGD");
        currencyMap.put("SGD", "10");
        currencyList.add("THB");
        currencyMap.put("THB", "15");
        currencyList.add("TRY");
        currencyMap.put("TRY", "31");
        currencyList.add("USD");
        currencyMap.put("USD", "13");
        currencyList.add("VND");
        currencyMap.put("VND", "14");
    }

}