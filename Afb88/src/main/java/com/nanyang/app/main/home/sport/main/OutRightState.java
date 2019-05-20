package com.nanyang.app.main.home.sport.main;

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
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.NetWorkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class OutRightState extends SportState<BallInfo, SportContract.View<BallInfo>> {

    protected BaseAllFragment fragment;

    public String getOt() {
        return ot;
    }

    protected String ot;
    protected String text;

    public OutRightState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public void setBaseView(SportContract.View mBaseView) {
        super.setBaseView(mBaseView);
        fragment = (BaseAllFragment) mBaseView;
        fragment.rvContent.setNestedScrollingEnabled(false);
        fragment.rvContent.setFocusableInTouchMode(false);
    }

    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight);
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
                setStateItemOt("t");
                getBaseView().switchState(this);
                break;

        }
    }

    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean collection() {
        return false;
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new SportAdapterHelper<BallInfo>() {
            @Override
            public void onConvert(MyRecyclerViewHolder holder, final int position, final BallInfo item) {

                TextView matchTitleTv = holder.getView(R.id.out_right_title_tv);
                View headV = holder.getView(R.id.module_match_head_v);
                TextView homeTv = holder.getView(R.id.out_right_home_tv);
                View contentParentLl = holder.getView(R.id.ll_match_content);
                contentParentLl.setBackgroundColor(item.getContentColor());
                final TextView markTv = holder.getView(R.id.out_right_mark_tv);
                homeTv.setText(item.getHome());
                markTv.setText(item.getX12_1Odds());
                markTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        back.clickOdds(markTv, item, "1", false, item.getX12_1Odds(), Integer.valueOf(item.getSocOddsId()), "", item.getHasPar() != null && item.getHasPar().equals("1"));
                    }
                });
                if (item.getType() == SportInfo.Type.ITME) {
                    matchTitleTv.setVisibility(View.GONE);
                    headV.setVisibility(View.GONE);

                } else {
                    matchTitleTv.setVisibility(View.VISIBLE);
                    headV.setVisibility(View.VISIBLE);
                    matchTitleTv.setText(item.getModuleTitle());
                    if (position == 0) {
                        headV.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public int onSetAdapterItemLayout() {
                return R.layout.sport_out_right_item;
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
        //   https://www.afb1188.com/H50/Pub/pcode.axd?_fm={"ACT":"LOS","DBID":"36","ot":"r","tf":"-1","timess":"","accType":"EU","pgLable":"0.5393305075227944","vsn":"4.0.12","PT":"wfMainH50"}&_db={}
        //   https://www.afb1188.com/H50/Pub/pcode.axd?_fm={"ACT":"LOS","DBID":999,"ot":"t","tf":-1,"OUTDBID":"2_11","timess":"","accType":"EU","ov":0,"mt":0,"pgLable":"0.6073571478712172","vsn":"4.0.12","PT":"wfMainH50"}&_db={}
//new LoginInfo.LanguageWfBean("AppGetDate", language, "wfMainH50")
        String dbId = fragment.currentIdBean.getDbid();
        webSocketRefresh(dbId);

    }

    protected void webSocketRefresh(String dbId) {
        String mt = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getMarketType().getType();
        String accType = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getOddsType().getType();
        int ov = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getSortType();
        LoginInfo.OutRightWfBean allRunningWfBean = new LoginInfo.OutRightWfBean(ot, dbId + "_11", accType, mt, ov);
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
                                OutRightState.this.webSocketBase = webSocket;
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

    //[623803,
// 623799,
// 0,"" ,0,"07/07 10:00PM" ,0,0,1,0,0,12170,12170,"Thailand [w]" ,"Thailand [w]" ,"","",0,0,0,0,3,0,0,0,0,0,0,0,0,201,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"0","2019/7/7 0:00:00",0,""]
    @Override
    protected BallInfo parseMatch(JSONArray matchArray, boolean notify) throws JSONException {
        BallInfo ballInfo = new AfbParseHelper<>().parseJsonArray(matchArray, notify);
        return ballInfo;
    }


    @Override
    protected List<TableSportInfo<BallInfo>> filterChildData(List<TableSportInfo<BallInfo>> allData) {
        return allData;
    }


    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new SportAdapterHelper.ItemCallBack<SportInfo>() {
            @Override
            public SportInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }

            @Override
            public void clickOdds(TextView v, SportInfo item, String type, boolean isHf, String odds, int oid, String sc,boolean hasPar) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, oid,type, odds, v, isHf, sc,hasPar);
            }

            @Override
            public void clickView(View v, SportInfo item, int position) {

            }

        };
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new OutRightBetHelper(getBaseView());
    }

    @Override
    public MenuItemInfo getStateType() {
        if (StringUtils.isNull(text))
            text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today);
        return new MenuItemInfo<String>(0, text, "OutRight", getSportName());

    }

    public void setStateItemOt(String ot) {
        switch (ot) {
            case "e":
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early);
                this.ot = "e";
                break;
            default:
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today);
                this.ot = "t";
                break;
        }

    }

    @Override
    protected List<TableSportInfo<BallInfo>> pageData(List<TableSportInfo<BallInfo>> filterData) {
        return filterData;
    }
}
