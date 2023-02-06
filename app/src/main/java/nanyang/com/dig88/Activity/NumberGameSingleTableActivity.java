package nanyang.com.dig88.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.myview.GroupView.MyViewGroup;
import nanyang.com.dig88.Adapter.DigGameResultAdapter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.DigGameBean;
import nanyang.com.dig88.Entity.DigGameBetBean;
import nanyang.com.dig88.Entity.DigGameResultBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by lanjian on 2016/02/26. home person game bet(数字游戏A主页下注) 42balls
 */
public class NumberGameSingleTableActivity extends GameBaseActivity{
    public static final int SHOW_TIMER = 0;
    public static final int SHOW_BET_SECCESS = 1;
    public static final int SHOW_BET_ERROR = 2;
    public static final int SHOW_RESULTS = 3;
    public static final int SHOW_RESULTS_ANIMATION = 4;
    public SimpleDateFormat format;
    public Dialog dialogshow;
    public  List<DigGameResultBean> gameResultsList=new ArrayList<DigGameResultBean>();
    public DigGameResultAdapter gameResultsAdapter;
    @BindView(R.id.mvg_ball)
    MyViewGroup ballMvg;
    @BindView(R.id.selected_ball_tv)
    TextView selectedBallTv;
    @BindView(R.id.group_line_v)
    View groupLineV;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_cycle)
    TextView tv_cycle;
    @BindView(R.id.tv_ball_result)
    TextView tv_ball_result;
    //1-42
    @BindView(R.id.lv_ball_result)
    ListView lv_ball_result;
    //号码 赔率
    @BindView(R.id.tv_bet_pop_odds)
    TextView tv_odds;
    //****
    @BindView(R.id.btn_big)
    Button btn_big;
    @BindView(R.id.btn_small)
    Button btn_small;
    @BindView(R.id.btn_double)
    Button btn_even;
    @BindView(R.id.btn_simgle)
    Button btn_odd;
    //全部
    @BindView(R.id.tv_all)
    Button tv_all;
    //清除
    @BindView(R.id.tv_delete)
    Button tv_delete;
    //投注
    @BindView(R.id.tv_bet_pop_number)
    Button btn_bet;
    @BindView(R.id.tv_big_odds)
    TextView tv_big_odds;
    @BindView(R.id.tv_small_odds)
    TextView tv_small_odds;
    @BindView(R.id.tv_double_odds)
    TextView tv_double_odds;
    @BindView(R.id.tv_single_odds)
    TextView tv_single_odds;
    @BindView(R.id.tv_small_double_odds)
    TextView tv_small_double_odds;
    @BindView(R.id.tv_small_single_odds)
    TextView tv_small_single_odds;
    @BindView(R.id.tv_big_single_odds)
    TextView tv_big_single_odds;
    @BindView(R.id.tv_big_double_odds)
    TextView tv_big_double_odds;
    //点击
    @BindView(R.id.rl_big)
    LinearLayout rl_big;
    @BindView(R.id.rl_small)
    LinearLayout rl_small;
    @BindView(R.id.rl_double)
    LinearLayout rl_double;
    @BindView(R.id.rl_single)
    LinearLayout rl_single;
    @BindView(R.id.rl_small_double)
    LinearLayout rl_small_double;
    @BindView(R.id.rl_small_single)
    LinearLayout rl_small_single;
    @BindView(R.id.rl_big_single)
    LinearLayout rl_big_single;
    @BindView(R.id.rl_big_double)
    LinearLayout rl_big_double;
    @BindView(R.id.rl_red)
    RelativeLayout rl_red;
    @BindView(R.id.rl_yellow)
    RelativeLayout rl_yellow;
    @BindView(R.id.rl_blue)
    RelativeLayout rl_blue;
    @BindView(R.id.tv_red)
    TextView tv_red;
    @BindView(R.id.tv_yellow)
    TextView tv_yellow;
    @BindView(R.id.tv_blue)
    TextView tv_blue;
    @BindView(R.id.tv_titletop)
    TextView tv_titletop;
    @BindView(R.id.back)
    ImageView back;
    //赔率
    @BindView(R.id.tv_red_odds)
    TextView tv_red_odds;
    @BindView(R.id.tv_yelllow_odds)
    TextView tv_yelllow_odds;
    @BindView(R.id.tv_blue_odds)
    TextView tv_blue_odds;
    //翻译
    @BindView(R.id.tv_more)
    TextView tv_more;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.red)
    TextView red;
    @BindView(R.id.yellow)
    TextView yellow;
    @BindView(R.id.blue)
    TextView blue;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.refresh_list)
    ImageView refresh_list;
    @BindView(R.id.tv_toumingdu)
    TextView betBackGround;
    @BindView(R.id.rl_dialog1)
    RelativeLayout noBetLoading;
    ///////////////////lanjian design
    @BindView(R.id.tv_nodata)
    TextView tv_nodata;
    boolean bGetNumberStatus = true;
    boolean bTimerStatus = true;
    //线程，拿结果
//    private UpdateResults updateResults = null;
//    private Thread threadResults = null;
//    boolean bTimerResults = true;
    boolean bGetResults = true;
    private Button btn01;
    private Button btn02;
    private Button btn03;
    private Button btn04;
    private Button btn05;
    private Button btn06;
    private Button btn07;
    private Button btn08;
    private Button btn09;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;
    private Button btn15;
    private Button btn16;
    private Button btn17;
    private Button btn18;
    private Button btn19;
    private Button btn20;
    private Button btn21;
    private Button btn22;
    private Button btn23;
    private Button btn24;
    private Button btn25;
    private Button btn26;
    private Button btn27;
    private Button btn28;
    private Button btn29;
    private Button btn30;
    private Button btn31;
    private Button btn32;
    private Button btn33;
    private Button btn34;
    private Button btn35;
    private Button btn36;
    private Button btn37;
    private Button btn38;
    private Button btn39;
    private Button btn40;
    private Button btn41;
    private Button btn42;
    private LinearLayout linearLayout ;
    private TextView progress_dialog_tv;
    private DigGameBean digGameBean = null;
    private BlockDialog blockDialog;
    private String gameName;
    private InputMethodManager manager=null;
    private int clickBig = 0;
    private int clickSmall = 0;
    private int clickEven = 0;
    private int clickOdd = 0;
    private int clickClear = 0;
    private String BetErrorMessage = "";
    public Handler handlerTimer = new Handler() {

        @Override
        public void handleMessage(Message msg) {


            switch(msg.what)
            {
                case SHOW_TIMER:

                    UpdateInterface(false);
                    break;
                case SHOW_BET_SECCESS:
                    blockDialog.dismiss();
                    Toast.makeText(NumberGameSingleTableActivity.this, R.string.xiazhusuccess, Toast.LENGTH_LONG).show();

                    break;
                case SHOW_BET_ERROR:
                    blockDialog.dismiss();
                    dialogshow.hide();
                    Toast.makeText(NumberGameSingleTableActivity.this, BetErrorMessage, Toast.LENGTH_LONG).show();
                    break;
                case SHOW_RESULTS_ANIMATION:
                    if (gameResultsAdapter==null){
                        gameResultsAdapter=new DigGameResultAdapter(gameResultsList,NumberGameSingleTableActivity.this);
                        lv_ball_result.setAdapter(gameResultsAdapter);
                        Log.i("LanjianTest", "列表=====" + gameResultsList.get(0).getResult());
                    }else{
                        gameResultsAdapter.notifyDataSetChanged();
                    }
                    if(gameResultsList.size() == 0){
                        tv_nodata.setVisibility(View.VISIBLE);
                        lv_ball_result.setVisibility(View.GONE);
                    }else{
                        showBallAnimation(gameResultsList.get(0).getResult());
                    }
                case SHOW_RESULTS:

                    if (gameResultsAdapter==null){
                        gameResultsAdapter=new DigGameResultAdapter(gameResultsList,NumberGameSingleTableActivity.this);
                        lv_ball_result.setAdapter(gameResultsAdapter);
                        Log.i("LanjianTest", "列表=====" + gameResultsList.get(0).getResult());
                    }else{
                        gameResultsAdapter.notifyDataSetChanged();
                    }
                    if(gameResultsList.size() == 0){
                        tv_nodata.setVisibility(View.VISIBLE);
                        lv_ball_result.setVisibility(View.GONE);
                    }

                    break;

            }
            //

        }
    };
    private String numberBig;
    private String numberSmall;
    private String numberEven;
    private String numberOdd;
    private String numberBigEven;
    private String numberBigOdd;
    private String numberSmallEven;
    private String numberSmallOdd;
    private String numberAll;
    private int multipleBig;
    private int multipleSmall;
    private int multipleEven;
    private int multipleOdd;
    private int multipleBigEven;
    private int multipleBigOdd;
    private int multipleSmallEven;
    private int multipleSmallOdd;
    private int multipleAll;
    private boolean updateAllData = false;
    private String NumberGameResultsParams = "";
    private String NumberGameResultsMessage ="";
    private String gameNumber="";
    //线程，定时拿状态信息
    private UpdateGameStatus updateGameStatus = null;
    private Thread threadGameStatus = null;
    //线程，定时更新界面状态
    private UpdateTimer updateTimer = null;
    private Thread threadTimer = null;

    public void StartUpdateGameStatus()
    {
        Log.i("LanjianTest", "StartUpdateGameStatus()");
        bGetNumberStatus = true;
        if(updateGameStatus == null)
        {
            Log.i("LanjianTest", "StartUpdateGameStatus() Start");
            updateGameStatus = new UpdateGameStatus();
            threadGameStatus = new Thread(updateGameStatus);
            threadGameStatus.start();
        }

        bTimerStatus = true;
        if(updateTimer == null)
        {
            updateTimer = new UpdateTimer();
            threadTimer = new Thread(updateTimer);
            threadTimer.start();
        }

    }

    public void StopUpdateGameStatus()
    {
        bGetNumberStatus = false;
        if(updateGameStatus != null)
            updateGameStatus = null;
        if(threadGameStatus != null)
            threadGameStatus = null;
        bTimerStatus = false;
        if(updateTimer != null)
            updateTimer = null;
        if(threadTimer != null)
            threadTimer = null;


    }

    public void getResultsData(String gameNumber,boolean bInit)
    {
        try {
            HttpClient httpClient = getApp().getHttpClient();
            if(httpClient == null)
                return ;
            Log.i("LanjianTest","+++"+NumberGameResultsParams);
            NumberGameResultsMessage = httpClient.sendPost(WebSiteUrl.GetNumberGameResults,NumberGameResultsParams);
            Log.i("LanjianTest","---"+NumberGameResultsMessage);
            Gson gson = new Gson();
            NyBaseResponse<List<DigGameResultBean>> orgData ;
            orgData = gson.fromJson(NumberGameResultsMessage, new TypeToken<NyBaseResponse<List<DigGameResultBean>>>() {
            }.getType());
            List<DigGameResultBean> data = orgData.getData();
            if(data == null)
                return;
            /////////
            gameResultsList.clear();
            for (int i=0;i<data.size();i++){
                gameResultsList.addAll(data);
            }
            if(data != null &&data.size() > 0  ) {

                if (data.get(0).getPeriod().equals(gameNumber)) {
                    digGameBean.setbGetResults(false);
                    handlerTimer.sendEmptyMessage(SHOW_RESULTS_ANIMATION);
                    Log.i("LanjianTestBet","GetNumberGameResults="+data.get(0).getResult() +",GameNumber="+data.get(0).getPeriod());
                }
                //初次进入游戏只初始化结果列表
                if(bInit){
                    handlerTimer.sendEmptyMessage(SHOW_RESULTS);
                }
            }


            data = null;
            orgData = null;
            gson =  null;
            bGetResults = false;

            //  Log.i("LanjianTest",NumberGameStatusMessage);
        } catch (Exception e) {
            Log.i("LanjianTest","getResultsData="+ e.toString());
            e.printStackTrace();
        }
    }

    ////////////////////
    public void ClearClick(String name){

        if(clickClear == 1 )
        {
            btn_big.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
        }

        if(clickClear == 1 && clickBig == 0 && clickSmall == 0 && clickEven == 0 && clickOdd == 0 )
            return;


        btn01.setBackgroundResource(R.drawable.btn_nochoice);
        btn02.setBackgroundResource(R.drawable.btn_nochoice);
        btn03.setBackgroundResource(R.drawable.btn_nochoice);
        btn04.setBackgroundResource(R.drawable.btn_nochoice);
        btn05.setBackgroundResource(R.drawable.btn_nochoice);
        btn06.setBackgroundResource(R.drawable.btn_nochoice);
        btn07.setBackgroundResource(R.drawable.btn_nochoice);
        btn08.setBackgroundResource(R.drawable.btn_nochoice);
        btn09.setBackgroundResource(R.drawable.btn_nochoice);
        btn10.setBackgroundResource(R.drawable.btn_nochoice);
        btn11.setBackgroundResource(R.drawable.btn_nochoice);
        btn12.setBackgroundResource(R.drawable.btn_nochoice);
        if(!name.equals("12")) {
            btn13.setBackgroundResource(R.drawable.btn_nochoice);
            btn14.setBackgroundResource(R.drawable.btn_nochoice);
            btn15.setBackgroundResource(R.drawable.btn_nochoice);
            btn16.setBackgroundResource(R.drawable.btn_nochoice);
            btn17.setBackgroundResource(R.drawable.btn_nochoice);
            btn18.setBackgroundResource(R.drawable.btn_nochoice);
        }
        if(!name.equals("12") && !name.equals("18")) {
            btn19.setBackgroundResource(R.drawable.btn_nochoice);
            btn20.setBackgroundResource(R.drawable.btn_nochoice);
            btn21.setBackgroundResource(R.drawable.btn_nochoice);
            btn22.setBackgroundResource(R.drawable.btn_nochoice);
            btn23.setBackgroundResource(R.drawable.btn_nochoice);
            btn24.setBackgroundResource(R.drawable.btn_nochoice);
        }
        if(!name.equals("12") && !name.equals("18") && !name.equals("24")) {
            btn25.setBackgroundResource(R.drawable.btn_nochoice);
            btn26.setBackgroundResource(R.drawable.btn_nochoice);
            btn27.setBackgroundResource(R.drawable.btn_nochoice);
            btn28.setBackgroundResource(R.drawable.btn_nochoice);
            btn29.setBackgroundResource(R.drawable.btn_nochoice);
            btn30.setBackgroundResource(R.drawable.btn_nochoice);
        }
        if(!name.equals("12") && !name.equals("18") && !name.equals("24")&& !name.equals("30")) {
            btn31.setBackgroundResource(R.drawable.btn_nochoice);
            btn32.setBackgroundResource(R.drawable.btn_nochoice);
            btn33.setBackgroundResource(R.drawable.btn_nochoice);
            btn34.setBackgroundResource(R.drawable.btn_nochoice);
            btn35.setBackgroundResource(R.drawable.btn_nochoice);
            btn36.setBackgroundResource(R.drawable.btn_nochoice);
        }
        if(name.equals("42")) {
            btn37.setBackgroundResource(R.drawable.btn_nochoice);
            btn38.setBackgroundResource(R.drawable.btn_nochoice);
            btn39.setBackgroundResource(R.drawable.btn_nochoice);
            btn40.setBackgroundResource(R.drawable.btn_nochoice);
            btn41.setBackgroundResource(R.drawable.btn_nochoice);
            btn42.setBackgroundResource(R.drawable.btn_nochoice);
        }
    }

    public void SetNumberBackGround(int big ,int small,int even,int odd)
    {
        ClearClick(gameName);
        if(clickClear == 1)
            return;
        if(big == 1 && small == 0 && even == 0 && odd == 0){//大
            btn_big.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            switch (gameName)
            {
                case "42":
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    btn31.setBackgroundResource(R.drawable.btn_choice);
                    btn32.setBackgroundResource(R.drawable.btn_choice);
                    btn33.setBackgroundResource(R.drawable.btn_choice);
                    btn34.setBackgroundResource(R.drawable.btn_choice);
                    btn35.setBackgroundResource(R.drawable.btn_choice);
                    btn36.setBackgroundResource(R.drawable.btn_choice);
                    btn37.setBackgroundResource(R.drawable.btn_choice);
                    btn38.setBackgroundResource(R.drawable.btn_choice);
                    btn39.setBackgroundResource(R.drawable.btn_choice);
                    btn40.setBackgroundResource(R.drawable.btn_choice);
                    btn41.setBackgroundResource(R.drawable.btn_choice);
                    btn42.setBackgroundResource(R.drawable.btn_choice);
                    break;
                case "36":
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    btn31.setBackgroundResource(R.drawable.btn_choice);
                    btn32.setBackgroundResource(R.drawable.btn_choice);
                    btn33.setBackgroundResource(R.drawable.btn_choice);
                    btn34.setBackgroundResource(R.drawable.btn_choice);
                    btn35.setBackgroundResource(R.drawable.btn_choice);
                    btn36.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "24":
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "12":
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "18":
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    break;
                case "30":
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    break;
            }

        }else if(big == 0 && small == 1 && even == 0 && odd == 0){
            btn_big.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            switch (gameName)
            {
                case "42":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    break;
                case "36":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "24":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "12":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "18":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    break;
                case "30":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    break;
            }

        }else if(big == 0 && small == 0 && even == 1 && odd == 0){
            btn_big.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));

            switch (gameName)
            {
                case "42":

                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    btn32.setBackgroundResource(R.drawable.btn_choice);
                    btn34.setBackgroundResource(R.drawable.btn_choice);
                    btn36.setBackgroundResource(R.drawable.btn_choice);
                    btn38.setBackgroundResource(R.drawable.btn_choice);
                    btn40.setBackgroundResource(R.drawable.btn_choice);
                    btn42.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "36":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    btn32.setBackgroundResource(R.drawable.btn_choice);
                    btn34.setBackgroundResource(R.drawable.btn_choice);
                    btn36.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "24":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "12":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "18":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "30":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);

                    break;
            }

        }else if(big == 0 && small == 0 && even == 0 && odd == 1){
            btn_big.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            switch (gameName)
            {
                case "42":

                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn31.setBackgroundResource(R.drawable.btn_choice);
                    btn33.setBackgroundResource(R.drawable.btn_choice);
                    btn35.setBackgroundResource(R.drawable.btn_choice);
                    btn37.setBackgroundResource(R.drawable.btn_choice);
                    btn39.setBackgroundResource(R.drawable.btn_choice);
                    btn41.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "36":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn31.setBackgroundResource(R.drawable.btn_choice);
                    btn33.setBackgroundResource(R.drawable.btn_choice);
                    btn35.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "24":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "12":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "18":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "30":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);


                    break;
            }

        }else if(big == 1 && small == 0 && even == 1 && odd == 0){//大双
            btn_big.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            switch (gameName)
            {
                case "42":


                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    btn32.setBackgroundResource(R.drawable.btn_choice);
                    btn34.setBackgroundResource(R.drawable.btn_choice);
                    btn36.setBackgroundResource(R.drawable.btn_choice);
                    btn38.setBackgroundResource(R.drawable.btn_choice);
                    btn40.setBackgroundResource(R.drawable.btn_choice);
                    btn42.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "36":

                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    btn32.setBackgroundResource(R.drawable.btn_choice);
                    btn34.setBackgroundResource(R.drawable.btn_choice);
                    btn36.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "24":

                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "12":

                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "18":

                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "30":

                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);

                    break;
            }

        }else if(big == 1 && small == 0 && even == 0 && odd == 1){//大单
            btn_big.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            switch (gameName)
            {
                case "42":


                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn31.setBackgroundResource(R.drawable.btn_choice);
                    btn33.setBackgroundResource(R.drawable.btn_choice);
                    btn35.setBackgroundResource(R.drawable.btn_choice);
                    btn37.setBackgroundResource(R.drawable.btn_choice);
                    btn39.setBackgroundResource(R.drawable.btn_choice);
                    btn41.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "36":

                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn31.setBackgroundResource(R.drawable.btn_choice);
                    btn33.setBackgroundResource(R.drawable.btn_choice);
                    btn35.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "24":

                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "12":

                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "18":

                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "30":

                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);


                    break;
            }

        }else if(big == 0 && small == 1 && even == 1 && odd == 0){//小双
            btn_big.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            switch (gameName)
            {
                case "42":

                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "36":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "24":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "12":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "18":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "30":
                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);


                    break;
            }

        }else if(big == 0 && small == 1 && even == 0 && odd == 1){//小单
            btn_big.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.xuanzhong));
            switch (gameName)
            {
                case "42":

                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);


                    break;
                case "36":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);




                    break;
                case "24":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "12":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);




                    break;
                case "18":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "30":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);



                    break;
            }

        }else if(clickBig == 1 && clickSmall == 1 && clickEven == 1 && clickOdd == 1)
        {
            btn_big.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_small.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_even.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            btn_odd.setBackgroundColor(getResources().getColor(R.color.noxuanzhong));
            switch (gameName)
            {
                case "42":

                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn31.setBackgroundResource(R.drawable.btn_choice);
                    btn33.setBackgroundResource(R.drawable.btn_choice);
                    btn35.setBackgroundResource(R.drawable.btn_choice);
                    btn37.setBackgroundResource(R.drawable.btn_choice);
                    btn39.setBackgroundResource(R.drawable.btn_choice);
                    btn41.setBackgroundResource(R.drawable.btn_choice);

                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    btn32.setBackgroundResource(R.drawable.btn_choice);
                    btn34.setBackgroundResource(R.drawable.btn_choice);
                    btn36.setBackgroundResource(R.drawable.btn_choice);
                    btn38.setBackgroundResource(R.drawable.btn_choice);
                    btn40.setBackgroundResource(R.drawable.btn_choice);
                    btn42.setBackgroundResource(R.drawable.btn_choice);

                    break;
                case "36":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);
                    btn31.setBackgroundResource(R.drawable.btn_choice);
                    btn33.setBackgroundResource(R.drawable.btn_choice);
                    btn35.setBackgroundResource(R.drawable.btn_choice);


                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);
                    btn32.setBackgroundResource(R.drawable.btn_choice);
                    btn34.setBackgroundResource(R.drawable.btn_choice);
                    btn36.setBackgroundResource(R.drawable.btn_choice);




                    break;
                case "24":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);

                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);




                    break;
                case "12":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);


                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);




                    break;
                case "18":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);


                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);



                    break;
                case "30":
                    btn01.setBackgroundResource(R.drawable.btn_choice);
                    btn03.setBackgroundResource(R.drawable.btn_choice);
                    btn05.setBackgroundResource(R.drawable.btn_choice);
                    btn07.setBackgroundResource(R.drawable.btn_choice);
                    btn09.setBackgroundResource(R.drawable.btn_choice);
                    btn11.setBackgroundResource(R.drawable.btn_choice);
                    btn13.setBackgroundResource(R.drawable.btn_choice);
                    btn15.setBackgroundResource(R.drawable.btn_choice);
                    btn17.setBackgroundResource(R.drawable.btn_choice);
                    btn19.setBackgroundResource(R.drawable.btn_choice);
                    btn21.setBackgroundResource(R.drawable.btn_choice);
                    btn23.setBackgroundResource(R.drawable.btn_choice);
                    btn25.setBackgroundResource(R.drawable.btn_choice);
                    btn27.setBackgroundResource(R.drawable.btn_choice);
                    btn29.setBackgroundResource(R.drawable.btn_choice);


                    btn02.setBackgroundResource(R.drawable.btn_choice);
                    btn04.setBackgroundResource(R.drawable.btn_choice);
                    btn06.setBackgroundResource(R.drawable.btn_choice);
                    btn08.setBackgroundResource(R.drawable.btn_choice);
                    btn10.setBackgroundResource(R.drawable.btn_choice);
                    btn12.setBackgroundResource(R.drawable.btn_choice);
                    btn14.setBackgroundResource(R.drawable.btn_choice);
                    btn16.setBackgroundResource(R.drawable.btn_choice);
                    btn18.setBackgroundResource(R.drawable.btn_choice);
                    btn20.setBackgroundResource(R.drawable.btn_choice);
                    btn22.setBackgroundResource(R.drawable.btn_choice);
                    btn24.setBackgroundResource(R.drawable.btn_choice);
                    btn26.setBackgroundResource(R.drawable.btn_choice);
                    btn28.setBackgroundResource(R.drawable.btn_choice);
                    btn30.setBackgroundResource(R.drawable.btn_choice);



                    break;
            }
        }

    }

    public void InitButtonClick()
    {
        tv_more.setOnClickListener(new ButtonClick());
        btn_big.setOnClickListener(new ButtonClick());
        btn_small.setOnClickListener(new ButtonClick());
        btn_even.setOnClickListener(new ButtonClick());
        btn_odd.setOnClickListener(new ButtonClick());
        tv_all.setOnClickListener(new ButtonClick());
        tv_delete.setOnClickListener(new ButtonClick());
        btn_bet.setOnClickListener(new ButtonClick());
        rl_red.setOnClickListener(new ButtonClick());
        rl_yellow.setOnClickListener(new ButtonClick());
        rl_blue.setOnClickListener(new ButtonClick());
        rl_big.setOnClickListener(new ButtonClick());
        rl_small.setOnClickListener(new ButtonClick());
        rl_double.setOnClickListener(new ButtonClick());
        rl_single.setOnClickListener(new ButtonClick());
        rl_small_double.setOnClickListener(new ButtonClick());
        rl_small_single.setOnClickListener(new ButtonClick());
        rl_big_double.setOnClickListener(new ButtonClick());
        rl_big_single.setOnClickListener(new ButtonClick());
        btn01.setOnClickListener(new ButtonClick());
        btn02.setOnClickListener(new ButtonClick());
        btn03.setOnClickListener(new ButtonClick());
        btn04.setOnClickListener(new ButtonClick());
        btn05.setOnClickListener(new ButtonClick());
        btn06.setOnClickListener(new ButtonClick());
        btn07.setOnClickListener(new ButtonClick());
        btn08.setOnClickListener(new ButtonClick());
        btn09.setOnClickListener(new ButtonClick());
        btn10.setOnClickListener(new ButtonClick());
        btn11.setOnClickListener(new ButtonClick());
        btn12.setOnClickListener(new ButtonClick());
        if(!gameName.equals("12")) {
            btn13.setOnClickListener(new ButtonClick());
            btn14.setOnClickListener(new ButtonClick());
            btn15.setOnClickListener(new ButtonClick());
            btn16.setOnClickListener(new ButtonClick());
            btn17.setOnClickListener(new ButtonClick());
            btn18.setOnClickListener(new ButtonClick());
        }
        if(!gameName.equals("12") && !gameName.equals("18")) {
            btn19.setOnClickListener(new ButtonClick());
            btn20.setOnClickListener(new ButtonClick());
            btn21.setOnClickListener(new ButtonClick());
            btn22.setOnClickListener(new ButtonClick());
            btn23.setOnClickListener(new ButtonClick());
            btn24.setOnClickListener(new ButtonClick());
        }
        if(!gameName.equals("12") && !gameName.equals("18") && !gameName.equals("24")) {
            btn25.setOnClickListener(new ButtonClick());
            btn26.setOnClickListener(new ButtonClick());
            btn27.setOnClickListener(new ButtonClick());
            btn28.setOnClickListener(new ButtonClick());
            btn29.setOnClickListener(new ButtonClick());
            btn30.setOnClickListener(new ButtonClick());
        }
        if(!gameName.equals("12") && !gameName.equals("18") && !gameName.equals("24")&& !gameName.equals("30")) {
            btn31.setOnClickListener(new ButtonClick());
            btn32.setOnClickListener(new ButtonClick());
            btn33.setOnClickListener(new ButtonClick());
            btn34.setOnClickListener(new ButtonClick());
            btn35.setOnClickListener(new ButtonClick());
            btn36.setOnClickListener(new ButtonClick());
        }
        if(gameName.equals("42")) {
            btn37.setOnClickListener(new ButtonClick());
            btn38.setOnClickListener(new ButtonClick());
            btn39.setOnClickListener(new ButtonClick());
            btn40.setOnClickListener(new ButtonClick());
            btn41.setOnClickListener(new ButtonClick());
            btn42.setOnClickListener(new ButtonClick());
        }


    }

    public  void ClearBigSmallEvenOdd()
    {
        if(clickBig == 1 && clickSmall == 1 && clickEven == 1 && clickOdd == 1){
            clickBig = 0;
            clickSmall = 0;
            clickEven = 0;
            clickOdd = 0;
        }
    }

    public void CombinationBet()
    {
        if(clickBig == 0 && clickSmall == 0 && clickEven == 0 && clickOdd == 0){
            Toast.makeText(NumberGameSingleTableActivity.this, R.string.sorrynohaoma, Toast.LENGTH_LONG).show();
            return;
        }
        int betType = 0;
        if(clickBig == 1 && clickSmall == 0 && clickEven == 0 && clickOdd == 0){
            betType = 12;
        }else if(clickBig == 0 && clickSmall == 1 && clickEven == 0 && clickOdd == 0)
        {
            betType = 13;

        }else if(clickBig == 0 && clickSmall == 0 && clickEven == 1 && clickOdd == 0)
        {
            betType = 14;
        }else if(clickBig == 0 && clickSmall == 0 && clickEven == 0 && clickOdd == 1)
        {
            betType = 15;
        }else if(clickBig == 1 && clickSmall == 0 && clickEven == 1 && clickOdd == 0)
        {
            betType = 16;
        }else if(clickBig == 1 && clickSmall == 0 && clickEven == 0 && clickOdd == 1)
        {
            betType = 17;
        }else if(clickBig == 0 && clickSmall == 1 && clickEven == 1 && clickOdd == 0)
        {
            betType = 18;
        }else if(clickBig == 0 && clickSmall == 1 && clickEven == 0 && clickOdd == 1)
        {
            betType = 19;
        }else if(clickBig == 1 && clickSmall == 1 && clickEven == 1 && clickOdd == 1)
        {
            betType = 20;
        }
        showBetDialog(digGameBean,betType);

    }

    //betType,1红，2黄，3蓝，4，大，5小，6双，7单，8小双，9小单，10大双，11大单，12号码大，13号码小
    //betType,14号码双，15号码单，16号码大双，17号码大单，18号码小双，19号码小单，20全部
    public void showBetDialog(final DigGameBean digGameBean,final int betType)
    {

        if(digGameBean.getCount_down() <=0 || digGameBean.getCount_down() >(digGameBean.getOpenUrl()*1000*60))
            return;

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(NumberGameSingleTableActivity.this);
        View view = LayoutInflater.from(NumberGameSingleTableActivity.this).inflate(R.layout.betting_dialog, null);
        ImageView iv_hide = (ImageView) view.findViewById(R.id.iv_hide);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_bet_pop_title);  //大小
        TextView tv_betType = (TextView) view.findViewById(R.id.tv_bet_pop_number);  //投资号码
        TextView tv_odds = (TextView) view.findViewById(R.id.tv_bet_pop_odds);  //赔率
        final EditText tv_bet_num = (EditText) view.findViewById(R.id.edt_bet_pop_amount);  //投注数目
        final TextView tv_act_bet = (TextView) view.findViewById(R.id.tv_bet_pop_actual_amount);  //实际投注
        Button btn_confim = (Button) view.findViewById(R.id.btn_bet_pop_sure); //确定
        Button btn_cancel = (Button) view.findViewById(R.id.btn_bet_pop_cancel); //取消
        //Init
        switch(betType)
        {
            case 1:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.hong);
                tv_odds.setText(digGameBean.getColor_factor());
                break;
            case 2:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.huang);
                tv_odds.setText(digGameBean.getColor_factor());
                break;
            case 3:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.lan);
                tv_odds.setText(digGameBean.getColor_factor());
                break;
            case 4:
                tv_title.setText(R.string.big_small);
                tv_betType.setText(R.string.big);
                tv_odds.setText(digGameBean.getBigsmall_factor());
                break;
            case 5:
                tv_title.setText(R.string.big_small);
                tv_betType.setText(R.string.small);
                tv_odds.setText(digGameBean.getBigsmall_factor());
                break;
            case 6:
                tv_title.setText(R.string.even_odd);
                tv_betType.setText(R.string.even_lottery);
                tv_odds.setText(digGameBean.getOddeven_factor());
                break;
            case 7:
                tv_title.setText(R.string.even_odd);
                tv_betType.setText(R.string.odd_lottery);
                tv_odds.setText(digGameBean.getOddeven_factor());
                break;
            case 8:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.small)+" "+ getString(R.string.even_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 9:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.small) +" "+  getString(R.string.odd_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 10:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.big)+" "+ getString(R.string.even_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 11:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.big)+" "+ getString(R.string.odd_lottery));//qdadan
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 12:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberBig.replace("_"," ")); //大
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case 13:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberSmall.replace("_"," "));   //小
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case 14:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberEven.replace("_"," "));   //双
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case 15:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberOdd.replace("_"," "));   //单
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case 16:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberBigEven.replace("_"," "));   //大双
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case 17:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberBigOdd.replace("_"," "));   //大单
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case 18:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberSmallEven.replace("_"," "));   //小双
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case 19:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberSmallOdd.replace("_"," "));   //小单
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case 20:
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(numberAll.replace("_"," "));   //全部
                tv_odds.setText(digGameBean.getNumber_factor());
                break;

            default:
                tv_title.setText(R.string.number_lottery);
                if(betType-20 < 10){
                    tv_betType.setText("0"+(betType-20));
                }else
                    tv_betType.setText(""+(betType-20));
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
        }

        tv_bet_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    String strNumber = editable.toString().trim();
                    double input_bet_num = Double.parseDouble(strNumber);
                    switch (betType)
                    {
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                            input_bet_num = input_bet_num * multipleBig;
                            break;
                        case 16:
                            input_bet_num = input_bet_num * multipleBigEven;
                            break;
                        case 17:
                            input_bet_num = input_bet_num * multipleBigOdd;
                            break;
                        case 18:
                            input_bet_num = input_bet_num * multipleSmallEven;
                            break;
                        case 19:
                            input_bet_num = input_bet_num * multipleSmallOdd;
                            break;
                        case 20:
                            input_bet_num = input_bet_num * multipleAll;
                            break;
                    }
                    DecimalFormat df = new DecimalFormat("#.00");
                    tv_act_bet.setText(df.format(input_bet_num));
                } catch (Exception e) {
                }
            }
        });
        //bet
        iv_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogshow.hide();
            }
        });
        btn_confim.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              NumberGameBet(tv_bet_num.getText().toString().trim(),tv_act_bet.getText().toString().trim(), digGameBean,betType);

                                          }
                                      }
        );
        btn_cancel.setOnClickListener(new View.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View view) {
                                              dialogshow.hide();
                                          }
                                      }

        );
        alertdialog.setView(view);
        dialogshow = alertdialog.show();
        dialogshow.setCanceledOnTouchOutside(true);
        Window window = dialogshow.getWindow();
        window.setWindowAnimations(R.style.DialogAnimation);
        dialogshow.setOnDismissListener(new
                                                DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        dialogshow = null;
                                                    }
                                                });

    }

    public void NumberGameBet(String betMoney,String netAmount1,DigGameBean digGameBean,int betType)
    {
        try{
            if (betMoney == null || betMoney.length() == 0||netAmount1.isEmpty()) {
                Toast.makeText(NumberGameSingleTableActivity.this, R.string.qingchashumu, Toast.LENGTH_LONG).show();
                return;
            }
            double netAmount = Double.parseDouble(netAmount1);
            if (!"".equals(getApp().getUserInfo().getMoneyBalance().getBalance())||
                    (getApp().getUserInfo().getMoneyBalance().getBalance()!=null))
            {
                String balance = getApp().getUserInfo().getMoneyBalance().getBalance();
                double b_balance=Double.valueOf(balance);
                double d_betMoney = Double.valueOf(betMoney);
                if(b_balance < d_betMoney)
                {
                    dialogshow.hide();
                    Toast.makeText(NumberGameSingleTableActivity.this, R.string.yuebuzhu,Toast.LENGTH_LONG).show();
                    return ;
                }
                boolean bBet = true;
                switch (betType)
                {
                    case 1://red
                    case 2:
                    case 3:

                        bBet = LimitBet(d_betMoney,netAmount,digGameBean.getColor_minbet(),digGameBean.getColor_maxbet(),digGameBean.getColor_total(),digGameBean.getColor_bet_total());
                        break;
                    case 4:
                    case 5:

                        bBet = LimitBet(d_betMoney,netAmount,digGameBean.getBigsmall_minbet(),digGameBean.getBigsmall_maxbet(),digGameBean.getBigsmall_total(),digGameBean.getBigsmall_bet_total());
                        break;
                    case 6:
                    case 7:
                        bBet = LimitBet(d_betMoney,netAmount,digGameBean.getOddeven_mixbet(),digGameBean.getOddeven_maxbet(),digGameBean.getOddeven_total(),digGameBean.getOddeven_bet_total());

                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        bBet = LimitBet(d_betMoney,netAmount,digGameBean.getCombination_mixbet(),digGameBean.getCombination_maxbet(),digGameBean.getCombination_total(),digGameBean.getCombination_bet_total());

                        break;
                    default:
                        bBet = LimitBet(d_betMoney,netAmount,digGameBean.getNumber_mixbet(),digGameBean.getNumber_maxbet(),digGameBean.getNumber_total(),digGameBean.getNumber_bet_total());

                        break;
                }
                //
                if(bBet)
                {
                    dialogshow.hide();
                    blockDialog.show();
                    BetNumberGame betNumberGame = new BetNumberGame();
                    betNumberGame.setBetType(betType);
                    betNumberGame.setBetMoney(betMoney);
                    betNumberGame.setNetMoney(netAmount);
                    betNumberGame.setDigGameBean(digGameBean);
                    Thread threadBetGame = new Thread(betNumberGame);
                    threadBetGame.start();
                }

            }
            else//ti shi yu e you wen ti
            {

            }
        }catch (Exception e){
        }
    }

    public void InitNumberBetString(String name){
        switch(name)
        {
            case "42":
                tv_red.setText("01,04,07,10,13,16,19\n22,25,28,31,34,37,40");
                tv_yellow.setText("02,05,08,11,14,17,20\n23,26,29,32,35,38,41");
                tv_blue.setText("03,06,09,12,15,18,21\n24,27,30,33,36,39,42");
                multipleAll = 42;
                multipleBig = multipleSmall = multipleEven = multipleOdd = 21;
                multipleBigEven = 11;
                multipleBigOdd = 10;
                multipleSmallEven = 10;
                multipleSmallOdd = 11;
                numberBig = "22_23_24_25_26_27_28_29_30_31_32_33_34_35_36_37_38_39_40_41_42";
                numberSmall = "01_02_03_04_05_06_07_08_09_10_11_12_13_14_15_16_17_18_19_20_21";
                numberEven = "02_04_06_08_10_12_14_16_18_20_22_24_26_28_30_32_34_36_38_40_42";
                numberOdd = "01_03_05_07_09_11_13_15_17_19_21_23_25_27_29_31_33_35_37_39_41";
                numberBigEven = "22_24_26_28_30_32_34_36_38_40_42";
                numberBigOdd = "23_25_27_29_31_33_35_37_39_41";
                numberSmallEven = "02_04_06_08_10_12_14_16_18_20";
                numberSmallOdd = "01_03_05_07_09_11_13_15_17_19_21";
                numberAll = "01_02_03_04_05_06_07_08_09_10_11_12_13_14_15_16_17_18_19_20_21_22_23_24_25_26_27_28_29_30_31_32_33_34_35_36_37_38_39_40_41_42";
                break;
            case "36":
                tv_red.setText("01,04,07,10,13,16\n19,22,25,28,31,34");
                tv_yellow.setText("02,05,08,11,14,17\n20,23,26,29,32,35");
                tv_blue.setText("03,06,09,12,15,18\n21,24,27,30,33,36");
                multipleAll = 36;
                multipleBig = multipleSmall = multipleEven = multipleOdd = 18;
                multipleBigEven = 9;
                multipleBigOdd = 9;
                multipleSmallEven = 9;
                multipleSmallOdd = 9;
                numberBig = "19_20_21_22_23_24_25_26_27_28_29_30_31_32_33_34_35_36";
                numberSmall = "01_02_03_04_05_06_07_08_09_10_11_12_13_14_15_16_17_18";
                numberEven = "02_04_06_08_10_12_14_16_18_20_22_24_26_28_30_32_34_36";
                numberOdd = "01_03_05_07_09_11_13_15_17_19_21_23_25_27_29_31_33_35";
                numberBigEven = "20_22_24_26_28_30_32_34_36";
                numberBigOdd = "19_21_23_25_27_29_31_33_35";
                numberSmallEven = "02_04_06_08_10_12_14_16_18";
                numberSmallOdd = "01_03_05_07_09_11_13_15_17";
                numberAll = "01_02_03_04_05_06_07_08_09_10_11_12_13_14_15_16_17_18_19_20_21_22_23_24_25_26_27_28_29_30_31_32_33_34_35_36";
                break;
            case "24":
                tv_red.setText("01,04,07,10\n13,16,19,22");
                tv_yellow.setText("02,05,08,11\n14,17,20,23");
                tv_blue.setText("03,06,09,12\n15,18,21,24");
                multipleAll = 24;
                multipleBig = multipleSmall = multipleEven = multipleOdd = 12;
                multipleBigEven = 6;
                multipleBigOdd = 6;
                multipleSmallEven = 6;
                multipleSmallOdd = 6;
                numberBig = "13_14_15_16_17_18_19_20_21_22_23_24";
                numberSmall = "01_02_03_04_05_06_07_08_09_10_11_12";
                numberEven = "02_04_06_08_10_12_14_16_18_20_22_24";
                numberOdd = "01_03_05_07_09_11_13_15_17_19_21_23";
                numberBigEven = "14_16_18_20_22_24";
                numberBigOdd = "13_15_17_19_21_23";
                numberSmallEven = "02_04_06_08_10_12";
                numberSmallOdd = "01_03_05_07_09_11";
                numberAll = "01_02_03_04_05_06_07_08_09_10_11_12_13_14_15_16_17_18_19_20_21_22_23_24";
                break;
            case "12":
                tv_red.setText("01,04,07,10");
                tv_yellow.setText("02,05,08,11");
                tv_blue.setText("03,06,09,12");
                multipleAll = 12;
                multipleBig = multipleSmall = multipleEven = multipleOdd = 6;
                multipleBigEven = 3;
                multipleBigOdd = 3;
                multipleSmallEven = 3;
                multipleSmallOdd = 3;
                numberBig = "07_08_09_10_11_12";
                numberSmall = "01_02_03_04_05_06";
                numberEven = "02_04_06_08_10_12";
                numberOdd = "01_03_05_07_09_11";
                numberBigEven = "08_10_12";
                numberBigOdd = "07_09_11";
                numberSmallEven = "02_04_06";
                numberSmallOdd = "01_03_05";
                numberAll = "01_02_03_04_05_06_07_08_09_10_11_12";
                break;
            case "18":
                tv_red.setText("01,04,07\n10,13,16");
                tv_yellow.setText("02,05,08\n11,14,17");
                tv_blue.setText("03,06,09\n12,15,18");
                multipleAll = 18;
                multipleBig = multipleSmall = multipleEven = multipleOdd = 9;
                multipleBigEven = 5;
                multipleBigOdd = 4;
                multipleSmallEven = 4;
                multipleSmallOdd = 5;
                numberBig = "10_11_12_13_14_15_16_17_18";
                numberSmall = "01_02_03_04_05_06_07_08_09";
                numberEven = "02_04_06_08_10_12_14_16_18";
                numberOdd = "01_03_05_07_09_11_13_15_17";
                numberBigEven = "10_12_14_16_18";
                numberBigOdd = "11_13_15_17";
                numberSmallEven = "02_04_06_08";
                numberSmallOdd = "01_03_05_07_09";
                numberAll = "01_02_03_04_05_06_07_08_09_10_11_12_13_14_15_16_17_18";
                break;
            case "30":
                tv_red.setText("01,04,07,10,13\n16,19,22,25,28");
                tv_yellow.setText("02,05,08,11,14\n17,20,23,26,29");
                tv_blue.setText("03,06,09,12,15\n18,21,24,27,30");
                multipleAll = 30;
                multipleBig = multipleSmall = multipleEven = multipleOdd = 15;
                multipleBigEven = 8;
                multipleBigOdd = 7;
                multipleSmallEven = 7;
                multipleSmallOdd = 8;
                numberBig = "16_17_18_19_20_21_22_23_24_25_26_27_28_29_30";
                numberSmall = "01_02_03_04_05_06_07_08_09_10_11_12_13_14_15";
                numberEven = "02_04_06_08_10_12_14_16_18_20_22_24_26_28_30";
                numberOdd = "01_03_05_07_09_11_13_15_17_19_21_23_25_27_29";
                numberBigEven = "16_18_20_22_24_26_28_30";
                numberBigOdd = "17_19_21_23_25_27_29";
                numberSmallEven = "02_04_06_08_10_12_14";
                numberSmallOdd = "01_03_05_07_09_11_13_15";
                numberAll = "01_02_03_04_05_06_07_08_09_10_11_12_13_14_15_16_17_18_19_20_21_22_23_24_25_26_27_28_29_30";
                break;
        }
        tv_more.setText(getString(R.string.gengduo));
        tv_all.setText(getString(R.string.all));
        tv_delete.setText(getString(R.string.qingchu));
        btn_bet.setText(getString(R.string.touzhu));
        btn_big.setText(getString(R.string.big));
        btn_small.setText(getString(R.string.small));
        btn_even.setText(getString(R.string.even_lottery));
        btn_odd.setText(getString(R.string.odd_lottery));
        tv1.setText(getString(R.string.big));
        tv2.setText(getString(R.string.small));
        tv3.setText(getString(R.string.even_lottery));
        tv4.setText(getString(R.string.odd_lottery));
        tv5.setText(getString(R.string.small_even));
        tv6.setText(getString(R.string.small_odd));
        tv7.setText(getString(R.string.big_odd));
        tv8.setText(getString(R.string.big_even));
        red.setText(getString(R.string.hong));
        yellow.setText(getString(R.string.huang));
        blue.setText(getString(R.string.lan));
        progress_dialog_tv=(TextView)findViewById(R.id.progress_dialog_tv);
        progress_dialog_tv.setText(R.string.kaijiangzhong);
    }

    public void InitButton()
    {
        btn01 = (Button) findViewById(R.id.btn_bt1);
        btn02 = (Button) findViewById(R.id.btn_bt2);
        btn03 = (Button) findViewById(R.id.btn_bt3);
        btn04 = (Button) findViewById(R.id.btn_bt4);
        btn05 = (Button) findViewById(R.id.btn_bt5);
        btn06 = (Button) findViewById(R.id.btn_bt6);
        btn07 = (Button) findViewById(R.id.btn_bt7);
        btn08 = (Button) findViewById(R.id.btn_bt8);
        btn09 = (Button) findViewById(R.id.btn_bt9);
        btn10 = (Button) findViewById(R.id.btn_bt10);
        btn11 = (Button) findViewById(R.id.btn_bt11);
        btn12 = (Button) findViewById(R.id.btn_bt12);
        btn13 = (Button) findViewById(R.id.btn_bt13);
        btn14 = (Button) findViewById(R.id.btn_bt14);
        btn15 = (Button) findViewById(R.id.btn_bt15);
        btn16 = (Button) findViewById(R.id.btn_bt16);
        btn17 = (Button) findViewById(R.id.btn_bt17);
        btn18 = (Button) findViewById(R.id.btn_bt18);
        btn19 = (Button) findViewById(R.id.btn_bt19);
        btn20 = (Button) findViewById(R.id.btn_bt20);
        btn21 = (Button) findViewById(R.id.btn_bt21);
        btn22 = (Button) findViewById(R.id.btn_bt22);
        btn23 = (Button) findViewById(R.id.btn_bt23);
        btn24 = (Button) findViewById(R.id.btn_bt24);
        btn25 = (Button) findViewById(R.id.btn_bt25);
        btn26 = (Button) findViewById(R.id.btn_bt26);
        btn27 = (Button) findViewById(R.id.btn_bt27);
        btn28 = (Button) findViewById(R.id.btn_bt28);
        btn29 = (Button) findViewById(R.id.btn_bt29);
        btn30 = (Button) findViewById(R.id.btn_bt30);
        btn31 = (Button) findViewById(R.id.btn_bt31);
        btn32 = (Button) findViewById(R.id.btn_bt32);
        btn33 = (Button) findViewById(R.id.btn_bt33);
        btn34 = (Button) findViewById(R.id.btn_bt34);
        btn35 = (Button) findViewById(R.id.btn_bt35);
        btn36 = (Button) findViewById(R.id.btn_bt36);
        btn37 = (Button) findViewById(R.id.btn_bt37);
        btn38 = (Button) findViewById(R.id.btn_bt38);
        btn39 = (Button) findViewById(R.id.btn_bt39);
        btn40 = (Button) findViewById(R.id.btn_bt40);
        btn41 = (Button) findViewById(R.id.btn_bt41);
        btn42 = (Button) findViewById(R.id.btn_bt42);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(this, "");
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blockDialog = new BlockDialog(NumberGameSingleTableActivity.this, getString(R.string.xiazhuzhong));

        gameName = getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA);
        Log.i("LanjianTest","-------------"+gameName);
        linearLayout = (LinearLayout) findViewById(R.id.number_parent);
        switch(gameName)
        {
            case "42_Balls":
                gameName = "42";
                digGameBean = getApp().getDignumbergame_42ball();
                linearLayout.addView(LayoutInflater.from(this).inflate(R.layout.number_game_42,null));
                break;
            case "36_Balls":
                gameName = "36";
                digGameBean = getApp().getDignumbergame_36ball();
                linearLayout.addView(LayoutInflater.from(this).inflate(R.layout.number_game_36,null));
                break;
            case "24_Balls":
                gameName = "24";
                digGameBean = getApp().getDignumbergame_24ball();
                linearLayout.addView(LayoutInflater.from(this).inflate(R.layout.number_game_24,null));
                break;
            case "12_Balls":
                gameName = "12";
                digGameBean = getApp().getDignumbergame_12ball();
                linearLayout.addView(LayoutInflater.from(this).inflate(R.layout.number_game_12,null));
                break;
            case "18_Balls":
                gameName = "18";
                digGameBean = getApp().getDignumbergame_18ball();
                linearLayout.addView(LayoutInflater.from(this).inflate(R.layout.number_game_18,null));
                break;
            case "30_Balls":
                gameName = "30";
                digGameBean = getApp().getDignumbergame_30ball();
                linearLayout.addView(LayoutInflater.from(this).inflate(R.layout.number_game_30,null));
                break;
        }
        InitNumberBetString(gameName);
        InitButton();
        InitButtonClick();
        UpdateInterface(true);
        //那结果的参数
        NumberGameResultsParams = "web_id="+0 + "&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()
                +"&type1="+8+"&type2=" + digGameBean.getGame_type();
        StartUpdateGameStatus();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAllData = true;
                finish();
            }
        });
        //初始化结果列表,球
        UpdateResults updateResults = new UpdateResults();
        updateResults.setGameNumber(digGameBean.getGame_perid());
        updateResults.setDigGameBean(digGameBean);
        updateResults.setbInit(true);
        Thread threadResult = new Thread(updateResults);
        threadResult.start();
        Log.i("LanjianTest", "InitResults=" + digGameBean.getGame_perid());
        Log.w("Life","initDate");
        switch (digGameBean.getGame_name())
        {
            case "42_Balls":
                addBalls(42);
                break;
            case "36_Balls":
                addBalls(36);
                break;
            case "24_Balls":
                addBalls(24);
                break;
            case "12_Balls":
                addBalls(12);
                break;
            case "18_Balls":
                addBalls(18);
                break;
            case "30_Balls":
                addBalls(30);
                break;
        }
        selectedBallTv.setVisibility(View.GONE);
    }

    public void UpdateCountDownTime()
    {
        Long count_downtime = digGameBean.getCount_down();
        if(count_downtime > 0 )
        {

            count_downtime=count_downtime-1000;
            digGameBean.setCount_down(count_downtime);
        }
    }

    private void addBalls(int total) {
        Log.w("Life","addBalls");
        ballMvg.removeAllViews();
        for(int i=1;i<=total;i++){
            TextView textView=new TextView(mContext);
            textView.setText(""+i);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(10);
            textView.setTextColor(getResources().getColor(R.color.black));
            TextPaint tp = textView .getPaint();
            tp.setFakeBoldText(true);
            textView.setBackgroundResource(R.drawable.oval_white_graystroke_radius0);
            ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ScreenUtil.dip2px(mContext,14),ScreenUtil.dip2px(mContext,14));
            ballMvg.addView(textView,layoutParams);

        }
    }

    private void showBallAnimation(final String result) {
        switch (digGameBean.getGame_name())
        {
            case "42_Balls":
                addBalls(42);
                break;
            case "36_Balls":
                addBalls(36);
                break;
            case "24_Balls":
                addBalls(24);
                break;
            case "12_Balls":
                addBalls(12);
                break;
            case "18_Balls":
                addBalls(18);
                break;
            case "30_Balls":
                addBalls(30);
                break;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotating10);
        //设置动画时间

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                selectedBallTv.setVisibility(View.GONE);
                ballMvg.updateLayout();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                slideView(selectedBallTv,0.22f,result);
                ballMvg.initLayout();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        groupLineV.startAnimation(animation);
    }

    public void slideView(final TextView view , final float xMul, String number) {

        ballMvg.removeViewAt(Integer.valueOf(number)-1);//移除
        view.setText(number);
        view.setVisibility(View.VISIBLE);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(1000);
        //设置透明度渐变动画的持续时间
        TranslateAnimation animationY = new TranslateAnimation(0, 0, -view.getHeight(), 0);
        animationY.setInterpolator(new OvershootInterpolator());
        animationY.setDuration(1000);
        AnimationSet set=new AnimationSet(true);    //创建动画集对象
        set.addAnimation(alphaAnimation);       //添加位置变化动画
        set.addAnimation(animationY);           //添加透明度渐变动画
        set.setFillAfter(true);                 //停留在最后的位置
        set.setFillEnabled(true);
        //设置动画
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TranslateAnimation animationX = new TranslateAnimation(0,ScreenUtil.getScreenWidthPix(mContext)*xMul, 0, 0);
                animationX.setInterpolator(new LinearInterpolator());
                animationX.setFillAfter(true);                 //停留在最后的位置
                animationX.setFillEnabled(true);
                animationX.setDuration(3000);
                view.startAnimation(animationX);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(set);
        set.startNow();
    }

    public void UpdateInterface(boolean bInit)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        if (digGameBean.isGame_status()){
            if(bInit){//初始化界面上的文字

                setTitle(gameName+" "+getString(R.string.qiu));
                tv_ball_result.setText(gameName + " " + getString(R.string.qiu) + " " + getString(R.string.jieguolist));
                tv_big_odds.setText(digGameBean.getBigsmall_factor());
                tv_small_odds.setText(digGameBean.getBigsmall_factor());
                tv_double_odds.setText(digGameBean.getOddeven_factor());
                tv_single_odds.setText(digGameBean.getOddeven_factor());
                tv_small_double_odds.setText(digGameBean.getCombination_factor());
                tv_small_single_odds.setText(digGameBean.getCombination_factor());
                tv_big_single_odds.setText(digGameBean.getCombination_factor());
                tv_big_double_odds.setText(digGameBean.getCombination_factor());
                tv_red_odds.setText("1:"+digGameBean.getColor_factor());
                tv_yelllow_odds.setText("1:"+digGameBean.getColor_factor());
                tv_blue_odds.setText("1:" +digGameBean.getColor_factor());
                tv_odds.setText(getString(R.string.haoma1) + 1 + ":" + digGameBean.getNumber_factor());

            }
            tv_cycle.setText(digGameBean.getGame_perid());
            Date date=new Date(digGameBean.getCount_down());
            String splite[]=simpleDateFormat.format(date).split(":");
        //    int Min =Integer.valueOf(splite[0]).intValue();
            long Min = digGameBean.getOpenUrl()*60*1000;
            if (digGameBean.getCount_down() <= 0 || digGameBean.getCount_down() >= Min || (splite[0].equals("00")&&splite[1].equals("00"))) {
                tv_time.setText("00:00");
                if(isAttached&&dialogshow!=null&&dialogshow.isShowing()){
                    dialogshow.hide();
                }
                if(View.GONE ==betBackGround.getVisibility())
                {
                    betBackGround.setVisibility(View.VISIBLE);
                    noBetLoading.setVisibility(View.VISIBLE);
                    digGameBean.setbGetResults(true);
                    UpdateResults updateResults = new UpdateResults();
                    updateResults.setGameNumber(digGameBean.getGame_perid());
                    updateResults.setDigGameBean(digGameBean);
                    Log.i("LanjianTest","======="+digGameBean.getGame_perid());
                    updateResults.setbInit(false);
                    Thread threadResult = new Thread(updateResults);
                    threadResult.start();
                }


              //  if(digGameBean.getGame_name().equals("42_Balls") )
               //     Log.i("LanjianTest42","=="+digGameBean.getGame_perid());


            }else{
             //   if(digGameBean.getGame_name().equals("42_Balls") )
              //      Log.i("LanjianTest42",simpleDateFormat.format(date));
                tv_time.setText(simpleDateFormat.format(date));
                if(View.VISIBLE == betBackGround.getVisibility()) {
                    betBackGround.setVisibility(View.GONE);
                    noBetLoading.setVisibility(View.GONE);
                    digGameBean.setbGetResults(false);
                }


            }
            date = null;
        }
        simpleDateFormat = null;

    }

    @Override
    protected void onDestroy()  {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "onResume()");
        updateAllData = true;
        StartUpdateGameStatus();
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "onPause()");
        StopUpdateGameStatus();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "onRestart()");
        updateAllData = true;
        getApp().setHttpClient(null);
        StartUpdateGameStatus();
      //  InitInterface();
        //初始化界面所有数据

        super.onRestart();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null
                    && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected int getLayoutRes()         {


        return R.layout.activity_home_game_hall_one;
    }

    //bet limit
    public boolean  LimitBet(double betMoney,double netAmount,double minBet,double maxBet,double maxTotalBet,double totalBet){
        Log.i("BetTest","betMoney="+betMoney+",totalBet="+totalBet+"，minBet="+minBet+",maxBet="+maxBet+",maxTotalBet="+maxTotalBet);
        boolean returnValue = true;
        if(betMoney < minBet || betMoney > maxBet)
        {
            returnValue = false;
            Toast.makeText(this,this.getString(R.string.xiane)+
                            "["+(new Double(minBet)).intValue()+
                            "-" +(new Double(maxBet)).intValue()+"]"
                    ,Toast.LENGTH_LONG).show();

        }else if (netAmount + totalBet > maxTotalBet){
            returnValue = false;
            Toast.makeText(this,this.getString(R.string.caoguobet)
                    ,Toast.LENGTH_LONG).show();
        }
        return returnValue;
    }

    public class UpdateGameStatus implements Runnable {
        public void run() {
            while (bGetNumberStatus)
            {
                try {
                    //   Log.i("LanjianTest","UpdateGameStatus");
                    getApp().GetGameStatus(updateAllData,format,"NumberGameSingleTableActivity");
                    updateAllData = false;//如果按了HOME键再切换回APP，界面上的状态要有所更新
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public class UpdateTimer implements Runnable {
        public void run() {
            while (bTimerStatus)
            {
                try {
                //    Log.i("LanjianTest","UpdateTimer");
                    UpdateCountDownTime();
                    handlerTimer.sendEmptyMessage(SHOW_TIMER);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public class UpdateResults implements Runnable {
        private String gameNumber;
        private boolean bInit;
        private DigGameBean digGameBean;

        public void setbInit(boolean bInit) {
            this.bInit = bInit;
        }

        public void setGameNumber(String gameNumber) {
            this.gameNumber = gameNumber;
        }

        public void setDigGameBean(DigGameBean digGameBean) {
            this.digGameBean = digGameBean;
        }
        public void run() {
            while (bInit || digGameBean.isbGetResults())
            {
                try {
                    Log.i("LanjianTest","UpdateResult="+digGameBean.getCount_down());
                    //局数不同，并且开奖的时候才能去拿结果
//                    if(bGetResults || (!gameNumber.equals(digGameBean.getGame_perid()) && tv_time.getText().equals("00:00")))
//                    {
//                        Log.i("LanjianTest","=============GetResults");
//                        gameNumber = digGameBean.getGame_perid();
//                        getResultsData();//拿结果
//
//                    }
                    getResultsData(gameNumber,bInit);//拿结果
                    bInit = false;
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    class ButtonClick implements View.OnClickListener {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.tv_more://显示结果
                    Bundle bundle=new Bundle();
                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA,digGameBean.getGame_name());
                    AppTool.activiyJump(NumberGameSingleTableActivity.this, ResultListActivity.class,bundle);
                    break;
                case R.id.btn_big:
                    ClearBigSmallEvenOdd();
                    clickBig = 1;
                    clickSmall = 0;
                    clickClear = 0;

                //    Log.i("LanjianTest","clickBig="+clickBig+",clickSmall="+clickSmall+",clickEven="+clickEven+",clickOdd="+clickOdd);
                    SetNumberBackGround(clickBig,clickSmall,clickEven,clickOdd);
                    break;
                case R.id.btn_small:
                    ClearBigSmallEvenOdd();
                    clickBig = 0;
                    clickSmall = 1;
                    clickClear = 0;

                 //   Log.i("LanjianTest","clickBig="+clickBig+",clickSmall="+clickSmall+",clickEven="+clickEven+",clickOdd="+clickOdd);
                    SetNumberBackGround(clickBig,clickSmall,clickEven,clickOdd);
                    break;
                case R.id.btn_double:
                    ClearBigSmallEvenOdd();
                    clickEven = 1;
                    clickOdd = 0;
                    clickClear = 0;

                 //   Log.i("LanjianTest","clickBig="+clickBig+",clickSmall="+clickSmall+",clickEven="+clickEven+",clickOdd="+clickOdd);
                    SetNumberBackGround(clickBig,clickSmall,clickEven,clickOdd);
                    break;
                case R.id.btn_simgle:
                    ClearBigSmallEvenOdd();
                    clickEven = 0;
                    clickOdd = 1;
                    clickClear = 0;

                  //  Log.i("LanjianTest","clickBig="+clickBig+",clickSmall="+clickSmall+",clickEven="+clickEven+",clickOdd="+clickOdd);
                    SetNumberBackGround(clickBig,clickSmall,clickEven,clickOdd);
                    break;
                case R.id.tv_all:
                    clickEven = 1;
                    clickOdd = 1;
                    clickBig = 1;
                    clickSmall = 1;
                    clickClear = 0;
                   // Log.i("LanjianTest","clickBig="+clickBig+",clickSmall="+clickSmall+",clickEven="+clickEven+",clickOdd="+clickOdd);
                    SetNumberBackGround(clickBig,clickSmall,clickEven,clickOdd);
                    break;
                case R.id.tv_delete:
                  //  Log.i("LanjianTest","clickBig="+clickBig+",clickSmall="+clickSmall+",clickEven="+clickEven+",clickOdd="+clickOdd);
                    clickClear = 1;
                    SetNumberBackGround(clickBig,clickSmall,clickEven,clickOdd);
                    clickEven = 0;
                    clickOdd = 0;
                    clickBig = 0;
                    clickSmall = 0;


                    break;
                case R.id.tv_bet_pop_number:
                    CombinationBet();//大小双单组合投注
                    break;
                case R.id.rl_red:
                    showBetDialog( digGameBean, 1);
                    break;
                case R.id.rl_yellow:
                    showBetDialog( digGameBean, 2);
                    break;
                case R.id.rl_blue:
                    showBetDialog( digGameBean, 3);
                    break;
                case R.id.rl_big:
                    showBetDialog( digGameBean, 4);
                    break;
                case R.id.rl_small:
                    showBetDialog( digGameBean, 5);
                    break;
                case R.id.rl_double:
                    showBetDialog( digGameBean, 6);
                    break;
                case R.id.rl_single:
                    showBetDialog( digGameBean, 7);
                    break;
                case R.id.rl_small_double:
                    showBetDialog( digGameBean, 8);
                    break;
                case R.id.rl_small_single:
                    showBetDialog( digGameBean, 9);
                    break;
                case R.id.rl_big_double:
                    showBetDialog( digGameBean, 10);
                    break;
                case R.id.rl_big_single:
                    showBetDialog( digGameBean, 11);
                    break;
                case R.id.btn_bt1:
                    showBetDialog( digGameBean, 21);
                    break;
                case R.id.btn_bt2:
                    showBetDialog( digGameBean, 22);
                    break;
                case R.id.btn_bt3:
                    showBetDialog( digGameBean, 23);
                    break;
                case R.id.btn_bt4:
                    showBetDialog( digGameBean, 24);
                    break;
                case R.id.btn_bt5:
                    showBetDialog( digGameBean, 25);
                    break;
                case R.id.btn_bt6:
                    showBetDialog( digGameBean, 26);
                    break;
                case R.id.btn_bt7:
                    showBetDialog( digGameBean, 27);
                    break;
                case R.id.btn_bt8:
                    showBetDialog( digGameBean, 28);
                    break;
                case R.id.btn_bt9:
                    showBetDialog( digGameBean, 29);
                    break;
                case R.id.btn_bt10:
                    showBetDialog( digGameBean, 30);
                    break;
                case R.id.btn_bt11:
                    showBetDialog( digGameBean, 31);
                    break;
                case R.id.btn_bt12:
                    showBetDialog( digGameBean, 32);
                    break;
                case R.id.btn_bt13:
                    showBetDialog( digGameBean, 33);
                    break;
                case R.id.btn_bt14:
                    showBetDialog( digGameBean, 34);
                    break;
                case R.id.btn_bt15:
                    showBetDialog( digGameBean, 35);
                    break;
                case R.id.btn_bt16:
                    showBetDialog( digGameBean, 36);
                    break;
                case R.id.btn_bt17:
                    showBetDialog( digGameBean, 37);
                    break;
                case R.id.btn_bt18:
                    showBetDialog( digGameBean, 38);
                    break;
                case R.id.btn_bt19:
                    showBetDialog( digGameBean, 39);
                    break;
                case R.id.btn_bt20:
                    showBetDialog( digGameBean, 40);
                    break;
                case R.id.btn_bt21:
                    showBetDialog( digGameBean, 41);
                    break;
                case R.id.btn_bt22:
                    showBetDialog( digGameBean, 42);
                    break;
                case R.id.btn_bt23:
                    showBetDialog( digGameBean, 43);
                    break;
                case R.id.btn_bt24:
                    showBetDialog( digGameBean, 44);
                    break;
                case R.id.btn_bt25:
                    showBetDialog( digGameBean, 45);
                    break;
                case R.id.btn_bt26:
                    showBetDialog( digGameBean, 46);
                    break;
                case R.id.btn_bt27:
                    showBetDialog( digGameBean, 47);
                    break;
                case R.id.btn_bt28:
                    showBetDialog( digGameBean, 48);
                    break;
                case R.id.btn_bt29:
                    showBetDialog( digGameBean, 49);
                    break;
                case R.id.btn_bt30:
                    showBetDialog( digGameBean, 50);
                    break;
                case R.id.btn_bt31:
                    showBetDialog( digGameBean, 51);
                    break;
                case R.id.btn_bt32:
                    showBetDialog( digGameBean, 52);
                    break;
                case R.id.btn_bt33:
                    showBetDialog( digGameBean, 53);
                    break;
                case R.id.btn_bt34:
                    showBetDialog( digGameBean, 54);
                    break;
                case R.id.btn_bt35:
                    showBetDialog( digGameBean, 55);
                    break;
                case R.id.btn_bt36:
                    showBetDialog( digGameBean, 56);
                    break;
                case R.id.btn_bt37:
                    showBetDialog( digGameBean, 57);
                    break;
                case R.id.btn_bt38:
                    showBetDialog( digGameBean, 58);
                    break;
                case R.id.btn_bt39:
                    showBetDialog( digGameBean, 59);
                    break;
                case R.id.btn_bt40:
                    showBetDialog( digGameBean, 60);
                    break;
                case R.id.btn_bt41:
                    showBetDialog( digGameBean, 61);
                    break;
                case R.id.btn_bt42:
                    showBetDialog( digGameBean, 62);
                    break;
                default:

                    break;

            }
        }
    }

    public class BetNumberGame implements Runnable {
        DigGameBean digGameBean;
        private int betType;
        private String betMoney;
        private double netMoney;

        public void setNetMoney(double netMoney) {
            this.netMoney = netMoney;
        }

        public void setBetMoney(String betMoney) {
            this.betMoney = betMoney;
        }

        public void setDigGameBean(DigGameBean digGameBean) {
            this.digGameBean = digGameBean;
        }

        public void setBetType(int betType) {
            this.betType = betType;
        }

        public void run() {
            try{
                String playType="";
                String type = "";
                String betUrl = "";
                Log.i("LanjianTestBet","betType="+betType+" betMoney="+betMoney);
                String params = "web_id="+ WebSiteUrl.WebId+"&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()+"&from=App";
                switch(betType)
                {
                    case 1:
                        playType = "color";
                        type = "0";
                        break;
                    case 2:
                        playType = "color";
                        type = "1";
                        break;
                    case 3:
                        playType = "color";
                        type = "2";
                        break;
                    case 4:
                        playType = "bigsmall";
                        type = "1";
                        break;
                    case 5:
                        playType = "bigsmall";
                        type = "0";
                        break;
                    case 6:
                        playType = "evenodd";
                        type = "0";
                        break;
                    case 7:
                        playType = "evenodd";
                        type = "1";
                        break;
                    case 8:
                        playType = "combination";
                        type = "0";
                        break;
                    case 9:
                        playType = "combination";
                        type = "1";
                        break;
                    case 10:
                        playType = "combination";
                        type = "2";
                        break;
                    case 11:
                        playType = "combination";
                        type = "3";
                        break;
                    case 12:
                        playType = "numbers";
                        type = numberBig;
                        break;
                    case 13:
                        playType = "numbers";
                        type = numberSmall;
                        break;
                    case 14:
                        playType = "numbers";
                        type = numberEven;
                        break;
                    case 15:
                        playType = "numbers";
                        type = numberOdd;
                        break;
                    case 16:
                        playType = "numbers";
                        type = numberBigEven;
                        break;
                    case 17:
                        playType = "numbers";
                        type = numberBigOdd;
                        break;
                    case 18:
                        playType = "numbers";
                        type = numberSmallEven;
                        break;
                    case 19:
                        playType = "numbers";
                        type = numberSmallOdd;
                        break;
                    case 20:
                        playType = "numbers";
                        type = numberAll;
                        break;
                    default:
                        playType = "numbers";
                        if(betType-20 < 10){
                            type = "0"+(betType-20);
                        }else
                            type = ""+(betType-20);
                        break;
                }
                params += "&get_bet="+playType+"#"+type;
                String postBetMoney = String.format("%.3f",Double.parseDouble(betMoney));
                params += "#" +postBetMoney;
                switch (digGameBean.getGame_name())
                {
                    case "42_Balls":
                        betUrl = WebSiteUrl.NumberGameBet42;
                        break;
                    case "36_Balls":
                        betUrl = WebSiteUrl.NumberGameBet36;
                        break;
                    case "24_Balls":
                        betUrl = WebSiteUrl.NumberGameBet24;
                        break;
                    case "12_Balls":
                        betUrl = WebSiteUrl.NumberGameBet12;
                        break;
                    case "18_Balls":
                        betUrl = WebSiteUrl.NumberGameBet18;
                        break;
                    case "30_Balls":
                        betUrl = WebSiteUrl.NumberGameBet30;
                        break;
                    default:
                        break;
                }
                Log.i("LanjianTestBet","betUrl="+betUrl+"?"+params);
                String betResults = getApp().getHttpClient().sendPost(betUrl,params);
                Log.i("LanjianTestBet",betResults);
                Gson gson = new Gson();
                NyBaseResponse<DigGameBetBean> orgData ;
                orgData = gson.fromJson(betResults, new TypeToken<NyBaseResponse<DigGameBetBean>>() {
                }.getType());
                //   DigGameBetBean data = orgData.getData();
                if(orgData.getMsg().equals("1"))
                {
                    switch(betType)
                    {
                        case 1:
                        case 2:
                        case 3:
                            digGameBean.setColor_bet_total(digGameBean.getColor_bet_total()+Double.parseDouble(postBetMoney));
                            break;
                        case 4:
                        case 5:
                            digGameBean.setBigsmall_bet_total(digGameBean.getBigsmall_bet_total()+Double.parseDouble(postBetMoney));
                            break;
                        case 6:
                        case 7:
                            digGameBean.setOddeven_bet_total(digGameBean.getOddeven_bet_total()+Double.parseDouble(postBetMoney));
                            break;
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                            digGameBean.setCombination_bet_total(digGameBean.getCombination_bet_total()+Double.parseDouble(postBetMoney));
                            break;
                        default:
                            digGameBean.setNumber_bet_total(digGameBean.getNumber_bet_total()+netMoney);
                            break;
                    }
                    handlerTimer.sendEmptyMessage(SHOW_BET_SECCESS);
                }else{
                    switch (orgData.getMsg())
                    {
                        case "-1":
                            BetErrorMessage = NumberGameSingleTableActivity.this.getString(R.string.xiazhucuowu);
                            break;
                        case "-2":
                            BetErrorMessage = NumberGameSingleTableActivity.this.getString(R.string.xiazhuzuixiao);
                            break;
                        case "-3":
                            BetErrorMessage = NumberGameSingleTableActivity.this.getString(R.string.xiazhuzuida);
                            break;
                        case "-4":
                            BetErrorMessage = NumberGameSingleTableActivity.this.getString(R.string.xiazhucaoguo);
                            break;
                        case "-5":
                            BetErrorMessage = NumberGameSingleTableActivity.this.getString(R.string.betshibai);
                            break;
                        case "-6":
                            BetErrorMessage = NumberGameSingleTableActivity.this.getString(R.string.daojishiend);
                            break;
                        default:
                            break;
                    }
                    handlerTimer.sendEmptyMessage(SHOW_BET_ERROR);
                }
                orgData = null;
                gson = null;




            }catch (Exception e)
            {
                BetErrorMessage = "NetWorkError_001";
                handlerTimer.sendEmptyMessage(SHOW_BET_ERROR);
            }



        }

    }
}
