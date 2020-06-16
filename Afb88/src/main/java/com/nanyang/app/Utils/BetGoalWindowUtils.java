package com.nanyang.app.Utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.main.BetCenter.Bean.StatementListDataBean;
import com.nanyang.app.main.BetCenter.HtmlTagHandler;
import com.nanyang.app.main.home.sport.football.SoccerRunningGoalManager;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/4/17.
 */

public class BetGoalWindowUtils {
    private Handler handler;
    private LinearLayout llContent;
    private LayoutInflater layoutInflater;

    private void initLayout(Activity activity) {
        if (llContent == null) {
            FrameLayout view = (FrameLayout) activity.getWindow().getDecorView();
            llContent = new LinearLayout(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llContent.setLayoutParams(params);
            llContent.setOrientation(LinearLayout.VERTICAL);
            llContent.setPadding(AfbUtils.dp2px(activity, 10), AfbUtils.dp2px(activity, 10), AfbUtils.dp2px(activity, 10), AfbUtils.dp2px(activity, 10));
            view.addView(llContent);
            llContent.setClickable(false);
        }
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(activity);
        }
        if (handler == null) {
            handler = new Handler();
        }
    }

    public void showBetWindow(String accType, String tidss, final Activity activity, final boolean isWA) {
        initLayout(activity);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("ACT", "GetTable");
        map.put("PT", "wfRunningH50");
        map.put("lang", new LanguageHelper(activity).getLanguage());
        map.put("accType", accType);
        map.put("tidss", tidss);
        String jsonParam = AfbUtils.getJsonParam(map);
        Disposable subscribe = Api.getService(ApiService.class).getData(AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + jsonParam).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new Consumer<String>() {//onNext
                            @Override
                            public void accept(String bean) throws Exception {
                                LogUtil.d("accept", bean);
                                StatementListDataBean dataBean = handleData(bean);
                                if (dataBean != null) {
                                    final View view;
                                    String index10 = dataBean.getIndex10();

                                    String index20 = dataBean.getIndex20();
                                    if (index10.equals("PAM") || index10.equals("PAR")) {
                                        view = layoutInflater.inflate(R.layout.item_bet_result_window_par, null);
                                        TextView tvTitle = view.findViewById(R.id.tv_title);
                                        TextView tvEstPayout = view.findViewById(R.id.tv_est_payout);
                                        TextView tvOdds = view.findViewById(R.id.tv_odds);
                                        TextView tvType = view.findViewById(R.id.tv_type);
                                        TextView tvAmt = view.findViewById(R.id.tv_amt);
                                        TextView tvNameEst = view.findViewById(R.id.tv_name_est);
                                        tvNameEst.setText(dataBean.getIndex1());
                                        tvTitle.setText("ID:[" + dataBean.getIndex12() + "]" + dataBean.getIndex0());
                                        tvEstPayout.setText(": " + AfbUtils.decimalValue(Float.parseFloat(dataBean.getIndex3()) * Float.parseFloat(dataBean.getIndex9()), "0.00"));
                                        tvOdds.setText(activity.getString(R.string.Odds) + ": " + dataBean.getIndex3());
                                        tvType.setText(dataBean.getIndex16());
                                        tvAmt.setText(activity.getString(R.string.Amt) + ": " + dataBean.getIndex9());
                                    } else {
                                        view = layoutInflater.inflate(R.layout.item_bet_result_window, null);
                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        params.bottomMargin = AfbUtils.dp2px(activity, 8);
                                        view.setLayoutParams(params);
                                        TextView tvTitle = view.findViewById(R.id.tv_title);
                                        View ll_vs = view.findViewById(R.id.ll_vs);
                                        TextView tvMatch = view.findViewById(R.id.tv_match);
                                        TextView tvHome = view.findViewById(R.id.tv_home);
                                        TextView tvAway = view.findViewById(R.id.tv_away);
                                        TextView tvData1 = view.findViewById(R.id.tv_data1);
                                        TextView tvData2 = view.findViewById(R.id.tv_data2);
                                        TextView tvData3 = view.findViewById(R.id.tv_data3);
                                        TextView tvData4 = view.findViewById(R.id.tv_data4);
                                        TextView tv_score = view.findViewById(R.id.tv_score);
                                        TextView tvDataContent = view.findViewById(R.id.tv_data_content);

                                        TextView tvData5 = view.findViewById(R.id.tv_data5);
                                        TextView tvData7 = view.findViewById(R.id.tv_data7);
                                        tvTitle.setText(dataBean.getIndex12() + "(" + dataBean.getIndex0() + ")");
                                        tvMatch.setText(dataBean.getIndex11());

                                        tvHome.setText(dataBean.getIndex1());
                                        tvAway.setText(dataBean.getIndex2());
                                        String index13 = dataBean.getIndex13();
                                        String transType10 = dataBean.getIndex10();
                                        boolean isOu = false;
                                        isOu = showBetType(activity,tvData1, tvData2, index13, transType10, isOu);
                                        String isRun5 = dataBean.getIndex5();
                                        String betType2 = dataBean.getIndex23();
                                        if (isOu) {
                                            ll_vs.setVisibility(View.VISIBLE);
                                        } else {
                                            ll_vs.setVisibility(View.GONE);
                                        }

                                        String gameType314 = dataBean.getIndex14();
                                        tvDataContent.setText(HtmlTagHandler.spanFontHtml(dataBean.getIndex24()));
                                        tvData3.setText(HtmlTagHandler.spanFontHtml("(" + (gameType314.equals("O") ? "Outright" : betType2) + ")"));
                                        View v_space = view.findViewById(R.id.v_space);
                                        TextView tv_time = view.findViewById(R.id.tv_time);
                                        if (isRun5.equals("True")) {
                                            tv_score.setVisibility(View.VISIBLE);
                                            v_space.setVisibility(View.VISIBLE);
                                            tv_time.setVisibility(View.VISIBLE);
                                            SoccerRunningGoalManager.getInstance().runScoreStyle(dataBean.getIndex17(), tv_score, dataBean.getIndex25(), dataBean.AwayScore26, dataBean.getIndex19(), dataBean.IsHomeGoal32);
                                            SoccerRunningGoalManager.getInstance().runTimeStyle(tv_time, dataBean.MExtraTime30, dataBean.TEAMSTATUS28, dataBean.CurMinute29, dataBean.Live31);
                                        } else {
                                            tv_score.setVisibility(View.GONE);
                                            v_space.setVisibility(View.GONE);
                                            tv_time.setVisibility(View.GONE);
                                        }
                                        tvData4.setText((isRun5.equals("False") ? "" : dataBean.getIndex19()));
                                        TextView tv_odds = view.findViewById(R.id.tv_odds);
                                        Log.d("getIndex3", "getIndex3: " + dataBean.getIndex3());
                                        tv_odds.setText(HtmlTagHandler.spanFontHtml("@" + dataBean.getIndex3() + " "));
                                        String index15 = dataBean.getIndex15();
                                        if (!TextUtils.isEmpty(index15)) {
                                            tvData5.setText("(" + index15 + ")");
                                        } else {
                                            tvData5.setVisibility(View.GONE);
                                        }
                                        tvData7.setText(dataBean.getIndex9());
                                    }
                                    final ImageView imgClose = view.findViewById(R.id.img_close);
                                    final TextView tvCount = view.findViewById(R.id.tv_count);
                                    TextView tvData6 = view.findViewById(R.id.tv_data6);
                                    showStatus(activity, index20,tvData6);
                                    String index8 = dataBean.getIndex8();
                                    String index22 = dataBean.getIndex22();
                                    String showStr;
                                    if (!TextUtils.isEmpty(index22) && !index22.equals(index8)) {
                                        showStr = " " + index22 + " / " + index8 + " ";
                                    } else {
                                        showStr = index8;
                                    }
                                    showStr = showStr.replace("&nbsp;", " ");
                                    if (showStr.contains("W") && !showStr.contains("/")) {
                                        if (activity instanceof BaseToolbarActivity) {
                                            BaseToolbarActivity sportActivity = (BaseToolbarActivity) activity;
                                            sportActivity.onAddWaiteCount(1);
                                        }
                                    }
                                    if (showStr.contains("/") && !TextUtils.isEmpty(index22)) {
                                        if (activity instanceof BaseToolbarActivity) {
                                            BaseToolbarActivity sportActivity = (BaseToolbarActivity) activity;
                                            sportActivity.onAddWaiteCount(0);
                                        }
                                        int endColor;

                                        if (index20.contains("N")||index20.contains("A")) {
                                            endColor = ContextCompat.getColor(activity, R.color.green_dark);
                                        } else {
                                            endColor = Color.RED;
                                        }
                                        SpannableStringBuilder spannableStringBuilder = AfbUtils.handleStringColor(showStr, "/", Color.YELLOW, endColor);
                                        tvData6.setText(spannableStringBuilder);
                                    }else {
                                        tvData6.setText(showStr);
                                    }
                                    Log.d("onGetRefreshMenu", "showStr: " + showStr);

                                    llContent.addView(view);
                                    for (int i = 0; i < llContent.getChildCount(); i++) {
                                        llContent.getChildAt(i).measure(0, 0);
                                    }
                                    view.setTag(5);
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            int tag = (int) view.getTag();
                                            tvCount.setText(tag + "s");
                                            if (tag == 5) {
                                                imgClose.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        llContent.removeView(view);
                                                    }
                                                });
                                            }
                                            if (tag == 0) {
                                                handler.removeCallbacks(this);
                                                llContent.removeView(view);
                                            } else {
                                                handler.postDelayed(this, 1000);
                                            }
                                            tag--;
                                            view.setTag(tag);
                                        }
                                    });
                                }
                            }
                        }, new Consumer<Throwable>() {//错误
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("showBetWindow", "Throwable: ");
                            }
                        }, new Action() {//完成
                            @Override
                            public void run() throws Exception {
                                Log.d("showBetWindow", "Action: ");
                            }
                        }, new Consumer<Subscription>() {//开始绑定
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                subscription.request(Long.MAX_VALUE);
                            }
                        });
        CompositeDisposable mCompositeSubscription = new CompositeDisposable();
        mCompositeSubscription.add(subscribe);
    }

    public boolean showBetType(Activity activity, TextView tvData1, TextView tvData2, String index13, String transType10, boolean isOu) {
        if (index13.toUpperCase().contains("HT") || index13.toUpperCase().contains("FH")) {
            tvData1.setVisibility(View.VISIBLE);
            tvData1.setText(R.string.FH_);
        } else {
            tvData1.setVisibility(View.GONE);
        }
        switch (transType10) {
            case "1":
            case "2":
            case "X":
                transType10 = activity.getString(R.string.X1X2);
                isOu=true;
                break;
            case "HDP":
            case "MMH":
                transType10 = activity.getString(R.string.HDP);
                break;
            case "OU":
            case "MMO":
                transType10 =activity.getString(R.string.O_U);
                isOu = true;
                break;
            case "OE":
                transType10 = activity.getString(R.string.OE);
                break;
            case "DC":
                transType10 = activity.getString(R.string.DC);
                break;
            case "CSR":
                transType10 = activity.getString(R.string.CS);
                break;
            case "FLG":
                transType10 = activity.getString(R.string.FGLG);
                break;
            case "TG":
                transType10 = activity.getString(R.string.TG);
                break;
            case "HFT":
                transType10 = activity.getString(R.string.HTFT);
                break;
        }

        tvData2.setText(transType10 );


        return isOu;
    }
    public void showStatus(Activity activity, String status, TextView running_Status){

        if (status.trim().equals("N") || status.trim().equals("A")) {
            running_Status.setBackgroundColor(ContextCompat.getColor(activity, R.color.transparent));
            running_Status.setTextColor(ContextCompat.getColor(activity, R.color.green500));
        } else if (status.trim().equals("D")) {
            running_Status.setBackgroundColor(ContextCompat.getColor(activity, R.color.yellow_button));
        } else {
            running_Status.setBackgroundColor(ContextCompat.getColor(activity, R.color.red));
        }
    }

    public void showGoalWindow(BaseToolbarActivity activity, String match, String homeTeam, int homeTextColor, String awayTeam, int awayTextColor, String homeScore, String awayScore, int type) {
        if (activity == null || SoundPlayUtils.getSoundIndex().getSound() == 0)
            return;
        initLayout(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AfbUtils.dp2px(activity, 90));
        params.bottomMargin = AfbUtils.dp2px(activity, 10);
        final View view = layoutInflater.inflate(R.layout.item_goal_window, null);
        final ImageView imgClose = view.findViewById(R.id.img_close);
        final TextView tvCount = view.findViewById(R.id.tv_count);
        TextView tvMatch = view.findViewById(R.id.tv_match);
        tvMatch.setText(match);
        TextView tvHome = view.findViewById(R.id.tv_home);
        tvHome.setText(homeTeam);
        tvHome.setTextColor(homeTextColor);
        TextView tvAway = view.findViewById(R.id.tv_away);
        tvAway.setText(awayTeam);
        tvAway.setTextColor(awayTextColor);
        TextView tvHomeScore = view.findViewById(R.id.tv_home_score);
        tvHomeScore.setText(homeScore);
        TextView tvAwayScore = view.findViewById(R.id.tv_away_score);
        tvAwayScore.setText(awayScore);
        if (type == 0) {//0是主队进球 1是客队进球
            tvHomeScore.setTextColor(Color.RED);
            tvAwayScore.setTextColor(Color.BLACK);
        } else {
            tvAwayScore.setTextColor(Color.RED);
            tvHomeScore.setTextColor(Color.BLACK);
        }
        view.setLayoutParams(params);
        llContent.addView(view);
        for (int i = 0; i < llContent.getChildCount(); i++) {
            llContent.getChildAt(i).measure(0, 0);
        }
        SoundPlayUtils.play();

        view.setTag(4);
        handler.post(new Runnable() {
            @Override
            public void run() {
                int tag = (int) view.getTag();
                tvCount.setText(tag + "s");
                if (tag == 4) {
                    imgClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            llContent.removeView(view);
                        }
                    });
                }
                if (tag == 0) {
                    handler.removeCallbacks(this);
                    llContent.removeView(view);
                } else {
                    handler.postDelayed(this, 1000);
                }
                tag--;
                view.setTag(tag);
            }
        });
    }

    private StatementListDataBean handleData(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONArray jsonArray1 = jsonArray.getJSONArray(3);
            JSONArray jsonArray2 = jsonArray1.getJSONArray(0);
            JSONArray jsonArray3 = jsonArray2.getJSONArray(2);
            JSONArray jsonArrayArr = jsonArray3.getJSONArray(0);
            StatementListDataBean bean = new StatementListDataBean(jsonArrayArr.getString(0), jsonArrayArr.getString(1),
                    jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                    jsonArrayArr.getString(6), jsonArrayArr.getString(7), jsonArrayArr.getString(8), jsonArrayArr.getString(9),
                    jsonArrayArr.getString(10), jsonArrayArr.getString(11), jsonArrayArr.getString(12), jsonArrayArr.getString(13),
                    jsonArrayArr.getString(14), jsonArrayArr.getString(15), jsonArrayArr.getString(16), jsonArrayArr.getString(17),
                    jsonArrayArr.getString(18), jsonArrayArr.getString(19), jsonArrayArr.getString(20), jsonArrayArr.getString(21),
                    jsonArrayArr.getString(22), jsonArrayArr.getString(23), jsonArrayArr.getString(24), jsonArrayArr.optString(25),
                    jsonArrayArr.optString(26), jsonArrayArr.optString(27), jsonArrayArr.optString(28), jsonArrayArr.optString(29),
                    jsonArrayArr.optString(30), jsonArrayArr.optString(31), jsonArrayArr.optString(32)
            );
            return bean;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clear() {
        if (llContent != null) {
            llContent.removeAllViews();
        }
        llContent = null;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        handler = null;
    }
}
