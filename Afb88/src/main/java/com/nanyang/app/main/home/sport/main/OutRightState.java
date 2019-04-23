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


    private String dbId;

    public String getOt() {
        return ot;
    }

    private String ot;
    private String text;

    public OutRightState(SportContract.View baseView) {
        super(baseView);
        this.dbId = ((BaseSportFragment) baseView).getBallDbid();
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
    public SportAdapterHelper<SportInfo> onSetAdapterHelper() {
        return new SportAdapterHelper<SportInfo>() {
            @Override
            public void onConvert(MyRecyclerViewHolder holder, final int position, final SportInfo item) {
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
                        back.clickOdds(markTv, item, "1", false, item.getX12_1Odds(), Integer.valueOf(item.getSocOddsId()), "");
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
        if (webSocket != null && webSocket.isOpen()) {
            webSocket.close();
        }
        //   https://www.afb1188.com/H50/Pub/pcode.axd?_fm={"ACT":"LOS","DBID":999,"ot":"t","tf":-1,"OUTDBID":"2_11","timess":"","accType":"EU","ov":0,"mt":0,"pgLable":"0.6073571478712172","vsn":"4.0.12","PT":"wfMainH50"}&_db={}
//new LoginInfo.LanguageWfBean("AppGetDate", language, "wfMainH50")
        String mt = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getAllOddsType().getType();
        String accType = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getOddsType().getType();
        int ov = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getSortType();

        LoginInfo.OutRightWfBean outRightWfBean = new LoginInfo.OutRightWfBean(ot, dbId + "_11", accType, mt, ov);

        Disposable subscription = getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + outRightWfBean.toJson()).subscribeOn(Schedulers.io())
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
//                                        baseView.checkMix(isMix());
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
                                        refresh();
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
                                OutRightState.this.webSocket = webSocket;
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
            public void clickOdds(TextView v, SportInfo item, String type, boolean isHf, String odds, int oid, String sc) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type, odds, v, isHf, sc);
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

    protected abstract String getSportName();

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

    MenuItemInfo<String> stateItem;

/*small wen, [20.04.19 10:53]
for (int i = 0, len = arrDbIds.Length; i < len; i++)
            {
                switch (arrDbIds[i])
                {
                    case "1":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf,"1");
                        break;
                    case "1_3":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "4");
                        break;
                    case "1_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "2") ;
                        break;
                    case "2":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "9");
                        break;
                    case "2_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "10");
                        break;
                    case "2_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "31");
                        break;
                    case "3":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "21");
                        break;
                    case "3_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "36");
                        break;
                    case "4":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "7");
                        break;
                    case "5":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "6");
                        break;
                    case "33":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "th");
                        break;
                    case "35":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "108");
                        break;
                    case "34":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "106");
                        break;
                    case "34_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "107");
                        break;
                    case "7":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "85");
                        break;
                    case "7_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "86");
                        break;
                    case "8":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "12");
                        break;
                    case "8_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "13");
                        break;
                    case "8_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "30");
                        break;
                    case "9":
                    case "9_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "29");
                        break;
                    case "9_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "39");
                        break;
                    case "10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "14");
                        break;
                    case "10_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "15");
                        break;
                    case "10_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "33");
                        break;
                    case "11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "11");
                        break;
                    case "11_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "32");
                        break;
                    case "12":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "17");
                        break;
                    case "12_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "18");
                        break;
                    case "12_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "34");
                        break;
                    case "13":

small wen, [20.04.19 10:53]
OddsUrl = OddsUrl + GetParam_H50(ot, tf, "19");
                        break;
                    case "13_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "20");
                        break;
                    case "13_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "35");
                        break;
                    case "14":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "16");
                        break;
                    case "14_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "92");
                        break;
                    case "15":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "25");
                        break;
                    case "15_13":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "26");
                        break;
                    case "15_16":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "27");
                        break;
                    case "16_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "46");
                        break;
                    case "16":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "49");
                        break;
                    case "17":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "22");
                        break;
                    case "17_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "37");
                        break;
                    case "19":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "28");
                        break;
                    case "19_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "40");
                        break;
                    case "19_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "63");
                        break;
                    case "20":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "51");
                        break;
                    case "20_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "52");
                        break;
                    case "21":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "53");
                        break;
                    case "21_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "54");
                        break;
                    case "22":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "57");
                        break;
                    case "22_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "58");
                        break;
                    case "23":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "44");
                        break;
                    case "23_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "61");
                        break;
                    case "24":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "23");
                        break;
                    case "24_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "62");
                        break;
                    case "25":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "41");
                        break;
                    case "25_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "42");
                        break;
                    case "25_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "43");
                        break;
                    case "26_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "64");
                        break;
                    case "26":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "65");
                        break;
                    case "27":

small wen, [20.04.19 10:53]
OddsUrl = OddsUrl + GetParam_H50(ot, tf, "67");
                        break;
                    case "27_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "68");
                        break;
                    case "27_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "69");
                        break;
                    case "28_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "91");
                        break;
                    case "29":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "101");
                        break;
                    case "29_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "102");
                        break;
                    case "30":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "103");
                        break;
                    case "30_11":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "104");
                        break;
                    case "31":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "105");
                        break;
                    case "32":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "99");
                        break;
                    case "36":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "182");
                        break;
                    case "36_10":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "183");
                        break;
                    case "666":
                        OddsUrl = OddsUrl + GetParam_H50(ot, tf, "200");
                        break;
                    default:
                        OddsUrl = "";
                        break;
                }
                if (OddsUrl != "")
                {
                    OddsUrl = OddsUrl + "&um=" + um;
                    _dicForm.AddExecJs("LinkWS('" + arrDbIds[i] + "_" + ot + "','" + OddsUrl + "'," + (ot == "e" ? Config.OddsRefresh : Config.RunRefresh) + ");");
                    OddsUrl = Config.WebSocketUrl + "?";
                }
            }*/
}
