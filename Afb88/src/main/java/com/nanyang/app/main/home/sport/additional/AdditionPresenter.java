package com.nanyang.app.main.home.sport.additional;


import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

import org.json.JSONException;

import java.util.Timer;
import java.util.TimerTask;

import static com.unkonw.testapp.libs.api.Api.getService;

public class AdditionPresenter extends BaseRetrofitPresenter<IBaseContext> implements IBasePresenter {
    private IRTMatchInfo bean;

    private String dbid;
    private String oddsType;
    private IAdded iAdded;

    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    public AdditionPresenter(IBaseContext view) {
        super(view);
    }


    public void addition(AddedParamsInfo info, IAdded iAdded) {

        this.bean = info.getBean();
        this.dbid = info.getDbid();
        this.oddsType = info.getOddsType();
        this.iAdded = iAdded;
        startUpdate();
    }

    @NonNull
    private String getUrl() {
//        https://www.afb1188.com/pgajaxS.axd?T=MB2&dbid=1&oId=770823&isMobile=1

        String url = AppConstant.getInstance().HOST + "/pgajaxS.axd?T=MB2&dbid=" + dbid + "&oId=" + bean.getSocOddsId() + "&isMobile=1&accType=" + oddsType;
        return url;
    }


    public void stopUpdate() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        if (task != null) {
            task.cancel();
            task = null;
        }

        baseContext.hideLoadingDialog();
    }

    public void startUpdate() {
        stopUpdate();
        if (bean == null || StringUtils.isNull(dbid) || dbid.equals("0"))
            return;
        baseContext.showLoadingDialog();
        getAddedData();// 打开定时器，执行操作
    }


    public void getAddedData() {
        if (task == null) {
            task = new TimerTask() {
                @Override
                public void run() {
                    if (bean == null || StringUtils.isNull(dbid) || dbid.equals("0"))
                        return;
                    doRetrofitApiOnUiThread(getService(ApiService.class).getAdditionData(getUrl()), new BaseConsumer<String>(baseContext) {
                        @Override
                        protected void onBaseGetData(String data) throws JSONException {
                            Gson gson = new Gson();
                            AddMBean addMBean = gson.fromJson(data, AddMBean.class);
                            if (iAdded != null)
                                iAdded.onAdded(addMBean, bean);
                            baseContext.hideLoadingDialog();
                        }

                        @Override
                        protected void onAccept() {
                        }
                    });

                }
            };
            timer.schedule(task, 0, 6000);
        }
    }

    private Timer timer = new Timer();
    private TimerTask task;

}