package com.nanyang.app.main.home.sport.allRunning;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;
import com.nanyang.app.ApiService;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.utils.NetWorkUtil;

import org.json.JSONException;
import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by ASUS on 2019/4/26.
 */

public class AllRunningCommonState extends OutRightState {
    public AllRunningCommonState(SportContract.View baseView) {
        super(baseView);
    }

    protected  SportAdapterHelper.ItemCallBack onSetItemCallBack(){
        return new BallItemCallBack<BallInfo>(baseRecyclerAdapter) {
            @Override
            public boolean isItemCollection(BallInfo item) {
                return isItemCollectionCommon(item);
            }

            @Override
            public boolean isLeagueCollection(BallInfo item) {
                return isLeagueCollectionCommon(item);
            }

            @Override
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds, int oid, String sc,boolean hasPar) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item,oid, type, odds, v, isHf, sc,hasPar);
            }

            @Override
            public void clickView(View v, BallInfo item, int position) {
                switch (v.getId()) {
                    case R.id.module_match_collection_tv:
                        collectionItemCommon(item);
                        break;
                    case R.id.module_League_collection_tv:
                        collectionLeagueCommon(item);
                        break;
                    case R.id.module_right_mark_tv:
                        getBaseView().clickItemAdd(v, item, position);
                        break;
                    case R.id.iv_hall_btn:
                        break;
                }

            }
        };

    }

    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.all_running);
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                setStateItemOt("e");
                getBaseView().switchState(this);
                break;
            case "Today":
                setStateItemOt("t");
                getBaseView().switchState(this);
                break;
            case "Running":
                setStateItemOt("r");
                getBaseView().switchState(this);
                break;

        }
    }

    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getRefreshUrl() {
        return null;
    }

    /*    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new BallAdapterHelper<BallInfo>();
    }*/
    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new BallBetHelper<BallInfo, BetView>(getBaseView()) {
            @Override
            protected String getBallG() {
                return fragment.currentIdBean.getId();
            }
        };
    }

    @Override
    public void refresh() {
        if (isHide)
            return;
        if (!NetWorkUtil.isNetConnected(getBaseView().getIBaseContext().getBaseActivity())) {
            baseView.reLoginPrompt(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.failed_to_connect), new SportContract.CallBack() {
                @Override
                public void clickCancel(View v) {
                    refresh();
                }
            });
            return;
        }
        if (webSocketBase != null && webSocketBase.isOpen()) {
            webSocketBase.close();
        }
        String dbId = fragment.currentIdBean.getDbid();
        webSocketRefresh(dbId);

    }

    protected void webSocketRefresh(String dbId) {
        String mt = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getMarketType().getType();
        String accType = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getOddsType().getType();
        int ov = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getSortType();
        LoginInfo.AllRunningWfBean allRunningWfBean = new LoginInfo.AllRunningWfBean(ot, dbId, accType);
        Disposable subscription = getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + allRunningWfBean.toJson()).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String s) throws Exception {

                        //'ws://ws.afb1188.com:8888/fnOddsGen?wst=wsSocAllGen&g=31&ot=t&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-04-20&LangCol=&accType=EU&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080',

                        int start = s.indexOf("ws://");
                        String substring = s.substring(start);
                        int end2 = substring.indexOf("',");
                        String url = substring.substring(0, end2);
                        Log.d(TAG, "accept:ws:: " + url);
                        if (StringUtils.isNull(url))
                            return;
                        AsyncHttpClient.getDefaultInstance().websocket(url, null, new AsyncHttpClient.WebSocketConnectCallback() {
                            @Override
                            public void onCompleted(Exception ex, final WebSocket webSocket) {

                                if (ex != null) {
                                    Log.e(TAG, "Exception----------------" + ex.getLocalizedMessage());
                                    ex.printStackTrace();
                                    return;
                                }
                                webSocket.setStringCallback(new WebSocket.StringCallback() {
                                    @Override
                                    public void onStringAvailable(String s) {
                                        Log.d("Socket", "onStringAvailable-----------" + s);
                                        if (s.equals("3"))
                                            return;
                                        try {
                                            allData = getTableSportInfos(s);
                                            updateHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (baseView.getIBaseContext().getBaseActivity() != null && baseView.getIBaseContext().getBaseActivity().isHasAttached()) {
                                                        initAllData(allData);
                                                    }
                                                }
                                            });

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });

                                webSocket.setClosedCallback(new CompletedCallback() {
                                    @Override
                                    public void onCompleted(Exception ex) {
                                        if (ex != null) {
                                            Log.d("Socket", "onClosedCallback出错");
                                            return;
                                        }
                                        Log.d("Socket", "onClosedCallback");
                                    }
                                });

                                webSocket.setEndCallback(new CompletedCallback() {
                                    @Override
                                    public void onCompleted(Exception ex) {
                                        if (ex != null) {
                                            Log.d("Socket", "setEndCallback出错");
                                            return;
                                        }
                                        Log.d("Socket", "setEndCallback");
                                    }
                                });
                                webSocketBase = webSocket;
                                startUpdateData();
                                webSocket.setWriteableCallback(new WritableCallback() {
                                    @Override
                                    public void onWriteable() {
                                        Log.d("Socket", "WritableCallback");

                                    }
                                });

                            }
                        });


                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.getIBaseContext().hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseView.getIBaseContext().hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.getIBaseContext().showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        if (mCompositeSubscription != null)
            mCompositeSubscription.add(subscription);
    }

    @Override
    public MenuItemInfo getStateType() {
        if (StringUtils.isNull(text))
            text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today);
        return new MenuItemInfo<String>(0, text, "AllRunning", getSportName());
    }
    public void setStateItemOt(String ot) {
        switch (ot) {
            case "e":
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early);
                this.ot = "e";
                break;
            case "r":
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running);
                this.ot = "r";
                break;
            default:
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today);
                this.ot = "t";
                break;
        }


    }

}
