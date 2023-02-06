package nanyang.com.dig88.NewKeno.presenter;

import android.util.Log;

import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.HashMap;

import nanyang.com.dig88.NewKeno.KenoFragment;
import nanyang.com.dig88.NewKeno.NewKenoBetListBean;
import nanyang.com.dig88.NewKeno.NewKenoGameSetBean;
import nanyang.com.dig88.NewKeno.NewKenoHistoryBean;
import nanyang.com.dig88.NewKeno.NewKenoStateBean;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/12/3.
 */

public class KenoFragmentPresenter extends BaseRetrofitPresenter<KenoFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    KenoFragment kenoFragment;

    public KenoFragmentPresenter(KenoFragment iBaseContext) {
        super(iBaseContext);
        kenoFragment = iBaseContext;
    }

    public void getGameSetData() {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(kenoFragment.gameSetUrl, getParam()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String s) throws JSONException {
                NewKenoGameSetBean newKenoGameSetBean = gson.fromJson(s, NewKenoGameSetBean.class);
                kenoFragment.onGetGameSetData(newKenoGameSetBean);
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
                kenoFragment.onGetDataFailed();
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public void getHistoryData() {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(kenoFragment.historyUrl, getParam()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String s) throws JSONException {
                NewKenoHistoryBean newKenoHistoryBean = gson.fromJson(s, NewKenoHistoryBean.class);
                kenoFragment.onGetHistoryData(newKenoHistoryBean);
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
                kenoFragment.onGetDataFailed();
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public void getStateData() {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(kenoFragment.stateUrl, getParam()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String s) throws JSONException {
                NewKenoStateBean newKenoStateBean = gson.fromJson(s, NewKenoStateBean.class);
                kenoFragment.onGetStateData(newKenoStateBean);
                Log.d("getStateData", "Rule:" + kenoFragment.getRule() + "----" + s);
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
                kenoFragment.onGetDataFailed();
                Log.d("getStateData", "Rule:" + kenoFragment.getRule() + "----" + throwable.toString());
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public void getBetListData() {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(kenoFragment.betListUrl, getParam()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String s) throws JSONException {
                NewKenoBetListBean newKenoBetListBean = gson.fromJson(s, NewKenoBetListBean.class);
                kenoFragment.onGetBetListData(newKenoBetListBean);
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
                kenoFragment.onGetDataFailed();
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    private HashMap<String, String> getParam() {
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("username", kenoFragment.newKenoActivity.getUsername());
        p.put("rule", kenoFragment.getRule());
        return p;
    }

    public boolean isNeedShowDrawing(int maxSecond, int currentSecond) {
        if (maxSecond - currentSecond <= 40) {
            return true;
        } else {
            return false;
        }
    }

    public int showIndex(int maxSecond, int currentSecond) {
        int index = (maxSecond - currentSecond) / 2;
        if (index == 0) {
            index = 1;
        }
        return index;
    }
}
