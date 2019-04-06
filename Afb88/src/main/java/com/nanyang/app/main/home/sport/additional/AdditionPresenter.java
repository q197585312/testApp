package com.nanyang.app.main.home.sport.additional;


import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.AdditionBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.utils.LogUtil;

import org.json.JSONException;

import static com.unkonw.testapp.libs.api.Api.getService;

public class AdditionPresenter extends BaseRetrofitPresenter<BaseSportFragment> implements IBasePresenter {
    private BallInfo bean;
    private int position;

    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    public AdditionPresenter(BaseSportFragment view) {
        super(view);
        dataUpdateRunnable = new DataRunnable();
    }


    public synchronized void addition(BallInfo item, int position) {
        this.bean = item;
        this.position = position;
        startUpdate();
    }

    //http://a8206d.a36588.com/_view/pgajaxS.axd?T=MB2&oId=12270813&home=Rochdale&away=Millwall&moduleTitle=ENGLISH%20LEAGUE%20ONE&date=03:45AM&lang=EN-US&isRun=false&_=1490092254432
    @NonNull
    private String getUrl() {

        String url = AppConstant.getInstance().HOST + "_view/MoreBet_App.aspx?oId=" + bean.getSocOddsId() /*+ "&home=" + StringUtils.URLEncode(bean.getHome()) + "&away=" + StringUtils.URLEncode(bean.getAway()) + "&moduleTitle=" + StringUtils.URLEncode(bean.getModuleTitle().toString()) + "&date=" + StringUtils.URLEncode(bean.getMatchDate()) + "&isRun=" + isRunning*/
                + "&T=MB2" + ((SportActivity) baseContext.getIBaseContext().getBaseActivity()).getAllOddsType().getType();
        url = url + "&t=" + System.currentTimeMillis();
        return url;
    }


    Handler updateHandler = new Handler();

    void stopUpdate() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
    }

    void startUpdate() {
        stopUpdate();
        dataUpdateRunnable = new DataRunnable();
        updateHandler.post(dataUpdateRunnable);// 打开定时器，执行操作
    }

    Runnable dataUpdateRunnable;

    public class DataRunnable implements Runnable {


        @Override
        public void run() {
            doRetrofitApiOnUiThread(getService(ApiService.class).getData(getUrl()), new BaseConsumer<String>(baseContext) {
                @Override
                protected void onBaseGetData(String data) throws JSONException {
                    Gson gson = new Gson();
                    LogUtil.d("Addition", "-------" + data);
                    AdditionBean additionBean = gson.fromJson(data, AdditionBean.class);
                    AdditionPresenter.this.baseContext.onAddition(additionBean, position);
                }
            });
            updateHandler.postDelayed(this, 20000);// 50是延时时长
        }
    }
}