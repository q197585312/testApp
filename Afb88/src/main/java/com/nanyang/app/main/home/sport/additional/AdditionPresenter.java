package com.nanyang.app.main.home.sport.additional;


import android.os.Handler;
import android.support.annotation.NonNull;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

import org.json.JSONException;

import static com.unkonw.testapp.libs.api.Api.getService;

public class AdditionPresenter extends BaseRetrofitPresenter<BaseSportFragment> implements IBasePresenter {
    private BallInfo bean;
    private int position;
    private String dbid;

    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    public AdditionPresenter(BaseSportFragment view) {
        super(view);
        dataUpdateRunnable = new DataRunnable();
    }


    public synchronized void addition(BallInfo item, int position, String dbid) {
        this.bean = item;
        this.position = position;
        this.dbid = dbid;
        startUpdate();
    }

    @NonNull
    private String getUrl() {
//        https://www.afb1188.com/pgajaxS.axd?T=MB2&dbid=1&oId=770823&isMobile=1

        String url = AppConstant.getInstance().HOST + "/pgajaxS.axd?T=MB2&dbid=" + dbid + "&oId=" + bean.getSocOddsId() + "&isMobile=1&accType=" +
                ((SportActivity) baseContext.getIBaseContext().getBaseActivity()).getOddsType().getType();

        return url;
    }


    Handler updateHandler = new Handler();

    public void stopUpdate() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
        baseContext.hideLoadingDialog();
    }

    void startUpdate() {
        stopUpdate();
        baseContext.showLoadingDialog();
        updateHandler.post(dataUpdateRunnable);// 打开定时器，执行操作
    }

    Runnable dataUpdateRunnable;

    public class DataRunnable implements Runnable {


        @Override
        public void run() {
            doRetrofitApiOnUiThread(getService(ApiService.class).getAdditionData(getUrl()), new BaseConsumer<AddMBean>(baseContext) {
                @Override
                protected void onBaseGetData(AddMBean data) throws JSONException {
                 /*   Gson gson = new Gson();
                    LogUtil.d("Addition", "-------" + data);
                    AdditionBean additionBean = gson.fromJson(data, AdditionBean.class);*/
                    AdditionPresenter.this.baseContext.onAddition(data, position);
                    baseContext.hideLoadingDialog();
                }

                @Override
                protected void onAccept() {
                }
            });
            updateHandler.postDelayed(this, 20000);// 50是延时时长
        }
    }
}