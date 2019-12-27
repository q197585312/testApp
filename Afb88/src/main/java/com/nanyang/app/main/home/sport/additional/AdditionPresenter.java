package com.nanyang.app.main.home.sport.additional;


import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Utils.StringUtils;
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

    private String dbid;
    public boolean isLiveOpen;

    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    public AdditionPresenter(BaseSportFragment view) {
        super(view);
        dataUpdateRunnable = new DataRunnable();
    }


    public synchronized void addition(BallInfo item, String dbid) {
        this.bean = item;
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

    public void startUpdate() {
        stopUpdate();
        if (bean == null || StringUtils.isNull(dbid) || dbid.equals("0"))
            return;
        baseContext.showLoadingDialog();
        updateHandler.post(dataUpdateRunnable);// 打开定时器，执行操作
    }

    Runnable dataUpdateRunnable;

    public void setIsLiveOpen(boolean b) {
        this.isLiveOpen=b;
    }

    public class DataRunnable implements Runnable {


        @Override
        public void run() {
            if (bean == null || StringUtils.isNull(dbid) || dbid.equals("0"))
                return;
            doRetrofitApiOnUiThread(getService(ApiService.class).getAdditionData(getUrl()), new BaseConsumer<String>(baseContext) {
                @Override
                protected void onBaseGetData(String data) throws JSONException {
                    Gson gson = new Gson();
                    AddMBean addMBean = gson.fromJson(data, AddMBean.class);
                    AdditionPresenter.this.baseContext.onAddition(addMBean, bean);
                    baseContext.hideLoadingDialog();
                }

                @Override
                protected void onAccept() {
                }
            });
            updateHandler.postDelayed(this, 6000);// 50是延时时长
        }
    }
}