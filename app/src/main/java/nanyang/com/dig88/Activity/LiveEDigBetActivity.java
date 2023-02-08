package nanyang.com.dig88.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import java.util.Timer;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Adapter.PersonGameResultAdapter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.DigGameBetBean;
import nanyang.com.dig88.Entity.GameBalanceBean;
import nanyang.com.dig88.Entity.LiveGameBean;
import nanyang.com.dig88.Entity.PersonGameResultBean;
import nanyang.com.dig88.Ijkplayer.IRenderView;
import nanyang.com.dig88.Ijkplayer.IjkVideoView;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2015/11/16. sai bao balls
 */
public class LiveEDigBetActivity extends GameBaseActivity{
    public static final int SHOW_RESULTS = 2;
    public static final int SHOW_BET_SECCESS = 11;
    public static final int SHOW_BET_ERROR = 12;
    private static final int OPEN_VIDEO_FAIL = 21;
    //LANJIAN
    private static final int SHOW_TIMER = 0;
    private static final int SHOW_BITMAP = 1;
    public static Dialog dialogshow;
    public static String ACTUIB1="time";
    //视频开始
    static int RENDER_ANDROID_OGL = 0;
    public double shurujine;
    public PersonGameResultAdapter digGameResultAdapter;
    public SimpleDateFormat format;
    public  List<PersonGameResultBean> gameResultsList=new ArrayList<PersonGameResultBean>();
    public PersonGameResultAdapter gameResultsAdapter;
    boolean m_res = false;
    boolean m_res_video = false;
    boolean bActivityExist = true;
    @BindView(R.id.b_imageView_video)
    IjkVideoView view;
    @BindView(R.id.b_text_video)
    TextView text_Video;
    @BindView(R.id.videoErrorMesseg)
    TextView videoErrorMesseg;
    @BindView(R.id.b_btn_video_reload)
    Button btn_Video;
    @BindView(R.id.b_voice)
    Button btn_Mute;
    //视频结束
    @BindView(R.id.tv_time)
    TextView tv_time;
   //第一行显示
    @BindView(R.id.tv_pairs)
    TextView tv_pairs;
    @BindView(R.id.tv_weigu)
    TextView tv_weigu;
    @BindView(R.id.tv_allweigu)
    TextView tv_allweigu;
    @BindView(R.id.tv_weigu456)
    TextView tv_weigu456;
    @BindView(R.id.tv_allpairs)
    TextView tv_allpairs;
    @BindView(R.id.sixfenone)
    TextView sixfenone;
      //第二大行
    //双 小
    @BindView(R.id.ll_tvdouble)
    LinearLayout ll_tvdouble;
    @BindView(R.id.ll_tvsmall)
    LinearLayout ll_tvsmall;
    //双1 2 3
    @BindView(R.id.ll_ivoneball)
    LinearLayout ll_ivoneball;
    @BindView(R.id.ll_ivtwoball)
    LinearLayout ll_ivtwoball;
    @BindView(R.id.ll_ivthreeball)
    LinearLayout ll_ivthreeball;
    //三 1 2 3
    @BindView(R.id.ll_iv3oneball)
    LinearLayout ll_iv3oneball;
    @BindView(R.id.ll_iv3twoball)
    LinearLayout ll_iv3twoball;
    @BindView(R.id.ll_iv3threeball)
    LinearLayout ll_iv3threeball;
    //全委
    @BindView(R.id.ll_ivallball)
    LinearLayout ll_ivallball;
    //三委四 五 六
    @BindView(R.id.ll_iv3fourball)
    LinearLayout ll_iv3fourball;
    @BindView(R.id.ll_iv3fiveball)
    LinearLayout ll_iv3fiveball;
    @BindView(R.id.ll_iv3sixball)
    LinearLayout ll_iv3sixball;
    //二 pairs 四 五 六
    @BindView(R.id.ll_iv2fourball)
    LinearLayout ll_iv2fourball;
    @BindView(R.id.ll_iv2fiveball)
    LinearLayout ll_iv2fiveball;
    @BindView(R.id.ll_iv2sixball)
    LinearLayout ll_iv2sixball;
    //单  大
    @BindView(R.id.ll_tvsimgle)
    LinearLayout ll_tvsimgle;
    @BindView(R.id.ll_tvbig)
    LinearLayout ll_tvbig;
    //4-17 点击
    @BindView(R.id.ll_iv4simgle)
    LinearLayout ll_iv4simgle;
    @BindView(R.id.ll_iv5simgle)
    LinearLayout ll_iv5simgle;
    @BindView(R.id.ll_iv6simgle)
    LinearLayout ll_iv6simgle;
    @BindView(R.id.ll_iv7simgle)
    LinearLayout ll_iv7simgle;
    @BindView(R.id.ll_iv8simgle)
    LinearLayout ll_iv8simgle;
    @BindView(R.id.ll_iv9simgle)
    LinearLayout ll_iv9simgle;
    @BindView(R.id.ll_iv10simgle)
    LinearLayout ll_iv10simgle;
    @BindView(R.id.ll_iv11simgle)
    LinearLayout ll_iv11simgle;
    @BindView(R.id.ll_iv12simgle)
    LinearLayout ll_iv12simgle;
    @BindView(R.id.ll_iv13simgle)
    LinearLayout ll_iv13simgle;
    @BindView(R.id.ll_iv14simgle)
    LinearLayout ll_iv14simgle;
    @BindView(R.id.ll_iv15simgle)
    LinearLayout ll_iv15simgle;
    @BindView(R.id.ll_iv16simgle)
    LinearLayout ll_iv16simgle;
    @BindView(R.id.ll_iv17simgle)
    LinearLayout ll_iv17simgle;
    //4-17 赔率
    @BindView(R.id.tv_4bet)
    TextView tv_4bet;
    @BindView(R.id.tv_5bet)
    TextView tv_5bet;
    @BindView(R.id.tv_6bet)
    TextView tv_6bet;
    @BindView(R.id.tv_7bet)
    TextView tv_7bet;
    @BindView(R.id.tv_8bet)
    TextView tv_8bet;
    @BindView(R.id.tv_9bet)
    TextView tv_9bet;
    @BindView(R.id.tv_10bet)
    TextView tv_10bet;
    @BindView(R.id.tv_11bet)
    TextView tv_11bet;
    @BindView(R.id.tv_12bet)
    TextView tv_12bet;
    @BindView(R.id.tv_13bet)
    TextView tv_13bet;
    @BindView(R.id.tv_14bet)
    TextView tv_14bet;
    @BindView(R.id.tv_15bet)
    TextView tv_15bet;
    @BindView(R.id.tv_16bet)
    TextView tv_16bet;
    @BindView(R.id.tv_17bet)
    TextView tv_17bet;
    //NINEWAYCARD 1，2  1,3等
    @BindView(R.id.ll_ivonetwoball)
    LinearLayout ll_ivonetwoball;
    @BindView(R.id.ll_ivonethreeball)
    LinearLayout ll_ivonethreeball;
    @BindView(R.id.ll_ivonefourball)
    LinearLayout ll_ivonefourball;
    @BindView(R.id.ll_ivonefiveball)
    LinearLayout ll_ivonefiveball;
    @BindView(R.id.ll_ivonesixball)
    LinearLayout ll_ivonesixball;
    @BindView(R.id.ll_ivtwothreeball)
    LinearLayout ll_ivtwothreeball;
    @BindView(R.id.ll_ivtwofourball)
    LinearLayout ll_ivtwofourball;
    @BindView(R.id.ll_ivtwofiveball)
    LinearLayout ll_ivtwofiveball;
    @BindView(R.id.ll_ivtwosixball)
    LinearLayout ll_ivtwosixball;
    @BindView(R.id.ll_ivthreefourball)
    LinearLayout ll_ivthreefourball;
    @BindView(R.id.ll_ivthreefiveball)
    LinearLayout ll_ivthreefiveball;
    @BindView(R.id.ll_ivthreesixball)
    LinearLayout ll_ivthreesixball;
    @BindView(R.id.ll_ivfourfiveball)
    LinearLayout ll_ivfourfiveball;
    @BindView(R.id.ll_ivfoursixball)
    LinearLayout ll_ivfoursixball;
    @BindView(R.id.ll_ivfivesixball)
    LinearLayout ll_ivfivesixball;
    //最底部 simgle
    @BindView(R.id.ll_ivbottom1ball)
    LinearLayout ll_ivbottom1ball;
    @BindView(R.id.ll_ivbottom2ball)
    LinearLayout ll_ivbottom2ball;
    @BindView(R.id.ll_ivbottom3ball)
    LinearLayout ll_ivbottom3ball;
    @BindView(R.id.ll_ivbottom4ball)
    LinearLayout ll_ivbottom4ball;
    @BindView(R.id.ll_ivbottom5ball)
    LinearLayout ll_ivbottom5ball;
    @BindView(R.id.ll_ivbottom6ball)
    LinearLayout ll_ivbottom6ball;
    @BindView(R.id.tv_nodata)
    TextView tv_nodata;
    @BindView(R.id.lv_ball_result)
    ListView lv_ball_result;
    @BindView(R.id.tv_titletop)
    TextView tv_titletop;
    @BindView(R.id.back)
    ImageView back1;
    String account;
    NyVolleyJsonThreadHandler<DigGameBetBean> diggameThread;
    @BindView(R.id.tv_cycle)
    TextView tv_cycle;
    @BindView(R.id.tv_ball_result)
    TextView tv_ball_result;
    int tag = 0;
    int tag1; //区别是下注还是获取列表
    //开奖
    @BindView(R.id.rl_dialog1)
    RelativeLayout rl_dialog;
    @BindView(R.id.tv_toumingdu)
    TextView tv_toumingdu;
     @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.progress_dialog_tv)
    TextView progress_dialog_tv;
    @BindView(R.id.fragment_game_hall_one)
    FrameLayout fragment_game_hall_one;
    String playtype;
    String bettype;
    //翻译
    @BindView(R.id.tv_even)
    TextView tv_even;
    @BindView(R.id.tv_odd)
    TextView tv_odd;
    @BindView(R.id.tv_small)
    TextView tv_small;
    @BindView(R.id.tv_big)
    TextView tv_big;
    int huyin;
    //获取倒计时
    SharedPreferences sharedPreferencesdaojishi;
    String dao;
    @BindView(R.id.btn_login)
    Button btn_login;
    double yue=0.0;
    @BindView(R.id.tv_more)
    TextView tv_more;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor1;
    Long cutdown;
    String strperiodsico;
    BroadcastReceiver mItemViewListClickReceiver;
    int timeend;
    boolean bGetNumberStatus = true;
    boolean bTimerStatus = true;
    boolean bTimerResults = true;
    boolean bGetResults = true;
    private boolean b_Pause = true;
    private boolean b_status;
    private Thread thread_video_status = null;
    private boolean bVideo;
    private Thread threadVideo = null;
    private boolean b_ClickVideo = true;
    private boolean bOpenVideo;
    //Sici balls
    private boolean bTimer = false;
    private boolean bShowTimer = false;
    private int iTimer = 0;
    private boolean bStatus = false;
    private Thread threadStatus = null;
    private Timer mTimerError;
    private String strbetnum;
    private int input_bet_num;
    private SimpleDateFormat simpleDateFormat;
    private boolean isPlay;
    private NyVolleyJsonThreadHandler<GameBalanceBean> yueThread;  //余额接口调用
    private BlockDialog blockDialog;
    private String BetErrorMessage = "";
    private String videoUrl;
    private String gameName;
    private boolean updateAllData = false;
    private LiveGameBean digGameBean = null;
    private String gameNumber="";
    private String gameResultsParams = "";
    private String gameResultsMessage ="";
    //线程，定时拿状态信息
    private UpdateGameStatus updateGameStatus = null;
    private Thread threadGameStatus = null;
    //线程，定时更新界面状态
    private UpdateTimer updateTimer = null;
    private Thread threadTimer = null;
    //线程，拿结果
    private UpdateResults updateResults = null;
    private Thread threadResults = null;
    private Handler handlerTimer = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(!isAttached)
                return;
            switch (msg.what) {

                case SHOW_TIMER:

                    UpdateInterface(false);
                    break;
                case SHOW_BET_SECCESS:
                    blockDialog.dismiss();
                    Toast.makeText(LiveEDigBetActivity.this, R.string.xiazhusuccess, Toast.LENGTH_LONG).show();

                    break;
                case SHOW_BET_ERROR:
                    blockDialog.dismiss();
                    dialogshow.hide();
                    Toast.makeText(LiveEDigBetActivity.this, BetErrorMessage, Toast.LENGTH_LONG).show();
                    break;
                case SHOW_RESULTS:
                    if (gameResultsAdapter ==  null){

                        gameResultsAdapter = new PersonGameResultAdapter(gameResultsList,LiveEDigBetActivity.this);
                        lv_ball_result.setAdapter(gameResultsAdapter);
                    }else{
                        gameResultsAdapter.notifyDataSetChanged();

                    }
                    if(gameResultsList.size() == 0)
                    {
                        tv_nodata.setVisibility(View.VISIBLE);
                        lv_ball_result.setVisibility(View.GONE);
                    }

                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void leftClick() {
        back();
    }

    private void back(){
        AppTool.activiyJump(this, LiveDigNumberActivity.class);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getResultsData()
    {
        try {
            HttpClient httpClient = getApp().getHttpClient();
            if(httpClient == null)
                return ;
            Log.i("LanjianTest","+++"+gameResultsParams);
            gameResultsMessage = httpClient.sendPost(WebSiteUrl.GetNumberGameResults,gameResultsParams);
            Log.i("LanjianTest","---"+gameResultsMessage);
            Gson gson = new Gson();
            NyBaseResponse<List<PersonGameResultBean>> orgData ;
            orgData = gson.fromJson(gameResultsMessage, new TypeToken<NyBaseResponse<List<PersonGameResultBean>>>() {
            }.getType());
            List<PersonGameResultBean> data = orgData.getData();
            /////////
            gameResultsList.clear();
            for (int i=0;i<data.size();i++){
                gameResultsList.addAll(data);
            }
            handlerTimer.sendEmptyMessage(SHOW_RESULTS);

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

    public void UpdateCountDownTime()
    {
        Long count_downtime = digGameBean.getCount_down();
        if(count_downtime!=null && count_downtime > 0 )
        {

            count_downtime=count_downtime-1000;
            digGameBean.setCount_down(count_downtime);
        }
    }

    public void UpdateInterface(boolean bInit)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        if (digGameBean.isGame_status()){
            if(bInit){//初始化界面上的文字



            }
            tv_cycle.setText(digGameBean.getGame_perid());
            Date date = null;
            if (digGameBean.getCount_down()>=0){
                date=new Date(digGameBean.getCount_down());
            }else{
                date=new Date(0);
            }
            String splite[]=simpleDateFormat.format(date).split(":");
            long Min = digGameBean.getOpenUrl()*60*1000;
            if (digGameBean.getCount_down() <= 0 || digGameBean.getCount_down() >= Min || (splite[0].equals("00")&&splite[1].equals("00"))) {
                tv_time.setText("00:00");
                if(isAttached&&dialogshow!=null&&dialogshow.isShowing()){
                    dialogshow.hide();
                }
                if(View.GONE ==tv_toumingdu.getVisibility())
                    tv_toumingdu.setVisibility(View.VISIBLE);
                if(View.GONE == rl_dialog.getVisibility())
                    rl_dialog.setVisibility(View.VISIBLE);
                //  if(digGameBean.getGame_name().equals("42_Balls") )
                //     Log.i("LanjianTest42","=="+digGameBean.getGame_perid());


            }else{
                //   if(digGameBean.getGame_name().equals("42_Balls") )
                //      Log.i("LanjianTest42",simpleDateFormat.format(date));
                tv_time.setText(simpleDateFormat.format(date));
                if(View.VISIBLE == tv_toumingdu.getVisibility())
                    tv_toumingdu.setVisibility(View.GONE);
                if(View.VISIBLE == rl_dialog.getVisibility())
                    rl_dialog.setVisibility(View.GONE);
            }
            date = null;
        }
        simpleDateFormat = null;

    }

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
        bTimerResults = true;
        if(updateResults == null)
        {
            updateResults = new UpdateResults();
            threadResults = new Thread(updateResults);
            threadResults.start();
        }
        bTimerStatus = true;
        if(updateTimer == null)
        {
            updateTimer = new UpdateTimer();
            threadTimer = new Thread(updateTimer);
            threadTimer.start();
        }
        startVideo(false);

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
        bTimerResults = false;
        if(updateResults != null)
            updateResults = null;
        if(threadResults != null)
            threadResults = null;
        if(b_Pause)
            stopVideo();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(this, "");
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blockDialog = new BlockDialog(LiveEDigBetActivity.this, getString(R.string.xiazhuzhong));
        simpleDateFormat = new SimpleDateFormat("mm:ss");
        videoUrl = WebSiteUrl.LiveGameVideoUrl_Sicbo;
        gameName = getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA);
        digGameBean = getApp().getLivegame_Sicoballs();

        setTitle(getString(R.string.saibao));

        ClickEven();

        tv_ball_result.setText(getString(R.string.saibao) + " " + getString(R.string.jieguolist));

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle=new Bundle();
//                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA,digGameBean.getGame_name());
//                AppTool.activiyJump(LiveEDigBetActivity.this, PersonResultListActivity.class,bundle);

            }
        });

        InitInterfaceText();
        gameResultsParams  = "web_id="+0 + "&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()
                +"&type1="+7+"&type2=" + digGameBean.getGame_type();
        StartUpdateGameStatus();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAllData = true;
                back();
            }
        });
        startNewVideo();
    }

    public void startNewVideo() {
        view.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        view.setVideoURI(Uri.parse(videoUrl));
        view.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     *
     * 获取列表数据
     */

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_person_home_game_hall_five;
    }

    //显示的赔率
     public void InitInterfaceText(){
        //top
        tv_pairs.setText("1:"+getApp().getLivegame_Sicoballs().getPairs_factor());//两个一样的号码 1：6
        tv_weigu.setText("1:"+getApp().getLivegame_Sicoballs().getWaidices_factor());//1：9
        tv_allweigu.setText("1:"+getApp().getLivegame_Sicoballs().getAlldice_factor());//1：151
        tv_weigu456.setText("1:"+getApp().getLivegame_Sicoballs().getWaidices_factor());//1：9
        tv_allpairs.setText("1:"+getApp().getLivegame_Sicoballs().getPairs_factor());
        tv_even.setText(getString(R.string.double1)+getApp().getLivegame_Sicoballs().getOddeven_factor());
        tv_small.setText(getString(R.string.xiao1)+(getApp().getLivegame_Sicoballs().getBigsmall_factor())+"\n"+"4 to 10");
        tv_odd.setText(getString(R.string.dandan1)+getApp().getLivegame_Sicoballs().getOddeven_factor());
        tv_big.setText(getString(R.string.big)+(getApp().getLivegame_Sicoballs().getBigsmall_factor())+"\n"+"11 to 17");
        sixfenone.setText(1+"/"+getApp().getLivegame_Sicoballs().getNinewaycard_factor()); // 1/6
        //4-17
        tv_4bet.setText(1+"/"+getApp().getLivegame_Sicoballs().getComu48_factor());
        tv_5bet.setText(1+"/"+getApp().getLivegame_Sicoballs().getComu482_factor());
        tv_6bet.setText(1+"/"+getApp().getLivegame_Sicoballs().getComu483_factor());
        tv_7bet.setText(1+"/"+getApp().getLivegame_Sicoballs().getComu484_factor());
        tv_8bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu485_factor());
        tv_9bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu486_factor());
        tv_10bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu486_factor());
        tv_11bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu486_factor());
        tv_12bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu486_factor());
        //***
        tv_13bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu485_factor());
        tv_14bet.setText(1+"/"+getApp().getLivegame_Sicoballs().getComu484_factor());
        tv_15bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu483_factor());
        tv_16bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu482_factor());
        tv_17bet.setText(1 + "/" + getApp().getLivegame_Sicoballs().getComu48_factor());

         progress_dialog_tv.setText(R.string.kaijiangzhong);
         tv_nodata.setText(R.string.nadata);
         tv_more.setText(getString(R.string.gengduo));
    }

    private void ClickEven(){
         //双小
        ll_tvdouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"even","0","0");
            }
        });
        ll_tvsmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"small","0","0");
            }
        });
        //11,22,33
        ll_ivoneball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"pairs","1","1");
            }
        });
        ll_ivtwoball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"pairs","2","2");
            }
        });
        ll_ivthreeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"pairs","3","3");
            }
        });
        //111,222,333
        ll_iv3oneball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"waidice","01","01");
            }
        });
        ll_iv3twoball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"waidice","02","02");
            }
        });
        ll_iv3threeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"waidice","03","03");
            }
        });
        //111,222,333,444,555,666,
        ll_ivallball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"alldice","0","0");
            }
        });
        //444,555,666
        ll_iv3fourball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"waidice","04","04");
            }
        });
        ll_iv3fiveball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"waidice","05","05");
            }
        });
        ll_iv3sixball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"waidice","06","06");
            }
        });
        //44,55,66
        ll_iv2fourball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"pairs","4","4");
            }
        });
        ll_iv2fiveball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"pairs","5","5");
            }
        });
        ll_iv2sixball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"pairs","6","6");
            }
        });
        //单  大
        ll_tvsimgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"odd","1","1");
            }
        });
        ll_tvbig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"big","1","1");
            }
        });
        //4-17 点击
        ll_iv4simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes1","4","4");
            }
        });
        ll_iv5simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes2","5","5");
            }
        });
        ll_iv6simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes3","6","6");
            }
        });
        ll_iv7simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes4","7","7");
            }
        });
        ll_iv8simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes5","8","8");
            }
        });
        ll_iv9simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes6","9","9");
            }
        });
        ll_iv10simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes6","10","10");
            }
        });
        ll_iv11simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes6","11","11");
            }
        });
        ll_iv12simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes6","12","12");
            }
        });
        ll_iv13simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes5","13","13");
            }
        });
        ll_iv14simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes4","14","14");
            }
        });
        ll_iv15simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes3","15","15");
            }
        });
        ll_iv16simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes2","16","16");
            }
        });

        ll_iv17simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combinationes1","17","17");
            }
        });
        //12
        ll_ivonetwoball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","1,2","0");

            }
        });
        //13
        ll_ivonethreeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","1,3","1");
            }
        });
        //14
        ll_ivonefourball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","1,4","2");
            }
        });
        ll_ivonefiveball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","1,5","3");
            }
        });
        ll_ivonesixball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","1,6","4");
            }
        });
        ll_ivtwothreeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","2,3","5");
            }
        });
        ll_ivtwofourball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","2,4","6");
            }
        });
        ll_ivtwofiveball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","2,5","7");
            }
        });
        ll_ivtwosixball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","2,6","8");
            }
        });
        ll_ivthreefourball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","3,4","9");
            }
        });
        ll_ivthreefiveball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","3,5","10");
            }
        });
        ll_ivthreesixball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","3,6","11");
            }
        });
        ll_ivfourfiveball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","4,5","12");
            }
        });
        ll_ivfoursixball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","4,6","13");
            }
        });
        ll_ivfivesixball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"ninewaycard","5,6","14");
            }
        });
        //最底部 1-6
        ll_ivbottom1ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"threeforces","1","1");
            }
        });
        ll_ivbottom2ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"threeforces","2","2");
            }
        });
        ll_ivbottom3ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"threeforces","3","3");
            }
        });
        ll_ivbottom4ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"threeforces","4","4");
            }
        });
        ll_ivbottom5ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"threeforces","5","5");
            }
        });
        ll_ivbottom6ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"threeforces","6","6");
            }
        });


    }

    //betType 1表示双，2表示小，3表示对子如11，22，33，44，55，66，5表示围骰（111，222，333）
    //5表示全围111，222，333，444，555，666
    //6表示单
    //7表示大
    //8表示4-17
    //9表示12，13，14，15，16.。。。
    //10表示1，2，3，4，5，6
    public void ShowBetDialog(final LiveGameBean digGameBean,final String betType,final String number,final  String postBetType)
    {
        if(digGameBean.getCount_down() <=0 || digGameBean.getCount_down() >3600000)
            return;

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(LiveEDigBetActivity.this);
        View view = LayoutInflater.from(LiveEDigBetActivity.this).inflate(R.layout.betting_dialog, null);
        ImageView iv_hide = (ImageView) view.findViewById(R.id.iv_hide);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_bet_pop_title);  //大小
        TextView tv_betType = (TextView) view.findViewById(R.id.tv_bet_pop_number);  //投资号码
        TextView tv_odds = (TextView) view.findViewById(R.id.tv_bet_pop_odds);  //赔率
        final EditText tv_bet_num = (EditText) view.findViewById(R.id.edt_bet_pop_amount);  //投注数目
        final TextView tv_act_bet = (TextView) view.findViewById(R.id.tv_bet_pop_actual_amount);  //实际投注
        Button btn_confim = (Button) view.findViewById(R.id.btn_bet_pop_sure); //确定
        Button btn_cancel = (Button) view.findViewById(R.id.btn_bet_pop_cancel); //取消
        switch(betType)
        {
            case "even":
                tv_title.setText(R.string.even_odd);
                tv_betType.setText(R.string.even_lottery); //双 小
                tv_odds.setText(getApp().getLivegame_Sicoballs().getOddeven_factor());
                break;
            case "small":
                tv_title.setText(R.string.big_small);
                tv_betType.setText(R.string.small); //双 小
                tv_odds.setText(getApp().getLivegame_Sicoballs().getBigsmall_factor());
                break;
            case "pairs":
                tv_title.setText("PAIRS");
                tv_betType.setText("PAIRS "+number); //双1 2 3
                tv_odds.setText(getApp().getLivegame_Sicoballs().getPairs_factor());
                break;
            case "big":
                tv_title.setText(R.string.big_small);
                tv_betType.setText(R.string.big);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getBigsmall_factor());
                break;
            case "odd":
                tv_title.setText(R.string.even_odd);
                tv_betType.setText(R.string.odd_lottery);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getOddeven_factor());
                break;
            case "waidice":
                tv_title.setText(R.string.weigu);
                tv_betType.setText("SPECIFIC TRIPLE "+number); //三 1 2 3
                tv_odds.setText(getApp().getLivegame_Sicoballs().getWaidices_factor());
                break;
            case "alldice":
                tv_title.setText(R.string.quanwei);
                tv_betType.setText("ANY TRIPLE"); //三1 2 3
                tv_odds.setText(getApp().getLivegame_Sicoballs().getAlldice_factor());
                break;
            case "combinationes1":
                tv_title.setText(R.string.zhuhe);
                tv_betType.setText("3 DICE TOTALLING "+number);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getComu48_factor());
                break;
            case "combinationes2":
                tv_title.setText(R.string.zhuhe);
                tv_betType.setText("3 DICE TOTALLING "+number);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getComu482_factor());
                break;
            case "combinationes3":
                tv_title.setText(R.string.zhuhe);
                tv_betType.setText("3 DICE TOTALLING "+number);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getComu483_factor());
                break;
            case "combinationes4":
                tv_title.setText(R.string.zhuhe);
                tv_betType.setText("3 DICE TOTALLING "+number);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getComu484_factor());
                break;
            case "combinationes5":
                tv_title.setText(R.string.zhuhe);
                tv_betType.setText("3 DICE TOTALLING "+number);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getComu485_factor());
                break;
            case "combinationes6":
                tv_title.setText(R.string.zhuhe);
                tv_betType.setText("3 DICE TOTALLING "+number);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getComu486_factor());
                break;
            case "ninewaycard":
                tv_title.setText("NINEWAYCARD");
                tv_betType.setText("SPECIFIC DOUBLE "+number);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getNinewaycard_factor());
                break;
            case "threeforces":
                tv_title.setText(R.string.sanjun);
                tv_betType.setText("SINGLE NUMBER "+number);
                tv_odds.setText(getApp().getLivegame_Sicoballs().getThreeforces_factor());
                break;

            default:
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
                                              NumberGameBet(tv_bet_num.getText().toString().trim(), digGameBean,betType,postBetType);

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

    public void NumberGameBet(String betMoney,LiveGameBean digGameBean,String playType,String betType)
    {
        try{
            String postPlayType = "";
            if (betMoney == null || betMoney.length() == 0) {
                Toast.makeText(LiveEDigBetActivity.this, R.string.qingchashumu, Toast.LENGTH_LONG).show();
                return;
            }
            if (!"".equals(getApp().getUserInfo().getMoneyBalance().getBalance())||
                    (getApp().getUserInfo().getMoneyBalance().getBalance()!=null))
            {
                String balance = getApp().getUserInfo().getMoneyBalance().getBalance();
                double b_balance=Double.valueOf(balance);
                double d_betMoney = Double.valueOf(betMoney);
                if(b_balance < d_betMoney)
                {
                    dialogshow.hide();
                    Toast.makeText(LiveEDigBetActivity.this, R.string.yuebuzhu,Toast.LENGTH_LONG).show();
                    return ;
                }
                boolean bBet = true;
                switch(playType)
                {
                    case "big":

                    case "small":
                        postPlayType = "bigsmall";
                        bBet = LimitBet(d_betMoney,digGameBean.getBigsmall_minbet(),digGameBean.getBigsmall_maxbet(),digGameBean.getBigsmall_total(),digGameBean.getBigsmall_bet_total());
                        break;
                    case "odd":

                    case "even":
                        postPlayType = "evenodd";
                        bBet = LimitBet(d_betMoney,digGameBean.getOddeven_mixbet(),digGameBean.getOddeven_maxbet(),digGameBean.getOddeven_total(),digGameBean.getOddeven_bet_total());
                        break;
                    case "pairs":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getPairs_mixbet(),digGameBean.getPairs_maxbet(),digGameBean.getPairs_total(),digGameBean.getPairs_bet_total());
                        break;
                    case "waidice":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getWaidices_mixbet(),digGameBean.getWaidices_maxbet(),digGameBean.getWaidices_total(),digGameBean.getWaidices_bet_total());
                        break;
                    case "alldice":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getAlldice_mixbet(),digGameBean.getAlldice_maxbet(),digGameBean.getAlldice_total(),digGameBean.getAlldice_bet_total());
                        break;
                    case "combinationes1":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getComu48_mixbet(),digGameBean.getComu48_maxbet(),digGameBean.getComu48_total(),digGameBean.getComu48_bet_total());
                        break;
                    case "combinationes2":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getComu482_mixbet(),digGameBean.getComu482_maxbet(),digGameBean.getComu482_total(),digGameBean.getComu482_bet_total());
                        break;
                    case "combinationes3":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getComu483_mixbet(),digGameBean.getComu483_maxbet(),digGameBean.getComu483_total(),digGameBean.getComu483_bet_total());
                        break;
                    case "combinationes4":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getComu484_mixbet(),digGameBean.getComu484_maxbet(),digGameBean.getComu484_total(),digGameBean.getComu484_bet_total());
                        break;
                    case "combinationes5":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getComu485_mixbet(),digGameBean.getComu485_maxbet(),digGameBean.getComu485_total(),digGameBean.getComu485_bet_total());
                        break;
                    case "combinationes6":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getComu486_mixbet(),digGameBean.getComu486_maxbet(),digGameBean.getComu486_total(),digGameBean.getComu486_bet_total());
                        break;
                    case "ninewaycard":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getNinewaycard_mixbet(),digGameBean.getNinewaycard_maxbet(),digGameBean.getNinewaycard_total(),digGameBean.getNinewaycard_bet_total());
                        break;
                    case "threeforces":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getThreeforces_mixbet(),digGameBean.getThreeforces_maxbet(),digGameBean.getThreeforces_total(),digGameBean.getThreeforces_bet_total());
                        break;
                }

                //
                if(bBet)
                {
                    dialogshow.hide();
                    blockDialog.show();
                    BetNumberGame betNumberGame = new BetNumberGame();
                    betNumberGame.setPlayType(postPlayType);
                    betNumberGame.setBetMoney(betMoney);
                    betNumberGame.setDigGameBean(digGameBean);
                    betNumberGame.setBetType(betType);
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

    //bet limit
    public boolean  LimitBet(double betMoney,double minBet,double maxBet,double maxTotalBet,double totalBet){
        Log.i("BetTest","betMoney="+betMoney+",totalBet="+totalBet+"，minBet="+minBet+",maxBet="+maxBet+",maxTotalBet="+maxTotalBet);
        boolean returnValue = true;
        if(betMoney < minBet || betMoney > maxBet)
        {
            returnValue = false;
            Toast.makeText(this,this.getString(R.string.xiane)+
                            "["+(new Double(minBet)).intValue()+
                            "-" +(new Double(maxBet)).intValue()+"]"
                    ,Toast.LENGTH_LONG).show();

        }else if (betMoney + totalBet > maxTotalBet){
            returnValue = false;
            Toast.makeText(this,this.getString(R.string.caoguobet)
                    ,Toast.LENGTH_LONG).show();
        }
        return returnValue;
    }

    @Override
    protected void onResume() {
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
        bActivityExist = true;
        getApp().setHttpClient(null);
        StartUpdateGameStatus();
        //  InitInterface();
        //初始化界面所有数据

        super.onRestart();
    }

    @Override
    protected void onDestroy()  {
        super.onDestroy();
        bActivityExist = false;
        if (view!=null){
            view.stopPlayback();
        }
    }

    public void OnClickVideo(View v) {
        if (b_Pause) {// 暂停视频
            text_Video.setVisibility(View.VISIBLE);
            text_Video.setText("Video Pause");
            btn_Video.setBackgroundResource(R.drawable.play_video);
            b_Pause=false;
            view.pause();
        } else {
            b_Pause = true;
            btn_Video.setBackgroundResource(R.drawable.stop_video);
            text_Video.setVisibility(View.GONE);
            view.start();
        }
    }

    // 视频停止
    public void stopVideo() {
        b_status = false;
        b_Pause = false;
        m_res_video = false;
        thread_video_status = null;
        bVideo = false;
        threadVideo = null;
        b_ClickVideo = true;
    }

    // 视频启动
    public void startVideo(boolean bStartVideo) {
        if (threadVideo == null) {
            bVideo = true;

            threadVideo = new Thread(new updateVideo());
            threadVideo.start();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    public class UpdateGameStatus implements Runnable {
        public void run() {
            while (bGetNumberStatus)
            {
                try {
                    //   Log.i("LanjianTest","UpdateGameStatus");
                    getApp().GetLiveGameStatus(updateAllData,format,"LiveEDigBetActivity");
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
        public void run() {
            while (bTimerResults)
            {
                try {
                    Log.i("LanjianTest","UpdateResult="+digGameBean.getCount_down());
                    //局数不同，并且开奖的时候才能去拿结果
                    if(isAttached&&bGetResults || (!gameNumber.equals(digGameBean.getGame_perid())&&tv_time!=null&& tv_time.getText().equals("00:00")))
                    {
                        Log.i("LanjianTest","=============GetResults");
                        gameNumber = digGameBean.getGame_perid();
                        getResultsData();//拿结果

                    }
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public class BetNumberGame implements Runnable {
        private String playType;
        private String betMoney;
        private LiveGameBean digGameBean;
        private String betType;

        public void setPlayType(String playType) {
            this.playType = playType;
        }

        public void setBetType(String betType) {
            this.betType = betType;
        }

        public void setBetMoney(String betMoney) {
            this.betMoney = betMoney;
        }

        public void setDigGameBean(LiveGameBean digGameBean) {
            this.digGameBean = digGameBean;
        }



        public void run() {
            try{


                String betUrl = "";
                Log.i("LanjianTestBet","betType="+playType+" betMoney="+betMoney+" betNumber="+betType);
                String params = "web_id="+ WebSiteUrl.WebId+"&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()+"&from=App";

                params += "&get_bet="+playType+"#"+betType;
                String postBetMoney = String.format("%.3f",Double.parseDouble(betMoney));
                params += "#" +postBetMoney;

                betUrl = WebSiteUrl.LiveGameBetSicbo;

                Log.i("LanjianTestBet","betUrl="+betUrl+"?"+params);
                String betResults = getApp().getHttpClient().sendPost(betUrl,params);
                Log.i("LanjianTestBet",betResults);
                Gson gson = new Gson();
                NyBaseResponse<DigGameBetBean> orgData ;
                orgData = gson.fromJson(betResults, new TypeToken<NyBaseResponse<DigGameBetBean>>() {
                }.getType());
                //   DigGameBetBean data = orgData.getData();
                //返回值这里还需要修改
                if(orgData.getMsg().equals("1"))
                {
                switch(playType)
                {
                    case "bigsmall":
                        getApp().getLivegame_Sicoballs().setBigsmall_bet_total(getApp().getLivegame_Sicoballs().getBigsmall_bet_total()+
                                Double.parseDouble(betMoney));
                        break;

                    case "evenodd":
                        getApp().getLivegame_Sicoballs().setOddeven_bet_total(getApp().getLivegame_Sicoballs().getOddeven_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "pairs":
                        getApp().getLivegame_Sicoballs().setPairs_bet_total(getApp().getLivegame_Sicoballs().getPairs_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "waidice":
                        getApp().getLivegame_Sicoballs().setWaidices_bet_total(getApp().getLivegame_Sicoballs().getWaidices_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "alldice":
                        getApp().getLivegame_Sicoballs().setAlldice_bet_total(getApp().getLivegame_Sicoballs().getAlldice_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "combinationes1":
                        getApp().getLivegame_Sicoballs().setComu48_bet_total(getApp().getLivegame_Sicoballs().getComu48_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "combinationes2":
                        getApp().getLivegame_Sicoballs().setComu482_bet_total(getApp().getLivegame_Sicoballs().getComu482_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "combinationes3":
                        getApp().getLivegame_Sicoballs().setComu483_bet_total(getApp().getLivegame_Sicoballs().getComu483_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "combinationes4":
                        getApp().getLivegame_Sicoballs().setComu484_bet_total(getApp().getLivegame_Sicoballs().getComu484_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "combinationes5":
                        getApp().getLivegame_Sicoballs().setComu485_bet_total(getApp().getLivegame_Sicoballs().getComu485_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "combinationes6":
                        getApp().getLivegame_Sicoballs().setComu486_bet_total(getApp().getLivegame_Sicoballs().getComu486_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "ninewaycard":
                        getApp().getLivegame_Sicoballs().setNinewaycard_bet_total(getApp().getLivegame_Sicoballs().getNinewaycard_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                    case "threeforces":
                        getApp().getLivegame_Sicoballs().setThreeforces_bet_total(getApp().getLivegame_Sicoballs().getThreeforces_bet_total()+
                                Double.parseDouble(betMoney));
                        break;
                }
                    handlerTimer.sendEmptyMessage(SHOW_BET_SECCESS);
                }else{
                    switch (orgData.getMsg())
                    {
                        case "-1":
                            BetErrorMessage = LiveEDigBetActivity.this.getString(R.string.xiazhucuowu);
                            break;
                        case "-2":
                            BetErrorMessage = LiveEDigBetActivity.this.getString(R.string.xiazhuzuixiao);
                            break;
                        case "-3":
                            BetErrorMessage = LiveEDigBetActivity.this.getString(R.string.xiazhuzuida);
                            break;
                        case "-4":
                            BetErrorMessage = LiveEDigBetActivity.this.getString(R.string.xiazhucaoguo);
                            break;
                        case "-5":
                            BetErrorMessage = LiveEDigBetActivity.this.getString(R.string.betshibai);
                            break;
                        case "-6":
                            BetErrorMessage = LiveEDigBetActivity.this.getString(R.string.daojishiend);
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

    // 更新视频
    public class updateVideo implements Runnable {
        boolean bInit = false;
        public void run() {
            bOpenVideo = false;
            if (!m_res_video) {
                handlerTimer.sendEmptyMessage(OPEN_VIDEO_FAIL);
                bOpenVideo = true;
            } else {
              //  Log.i("Avplayer","startVideo");
                if(bActivityExist ){
                    handlerTimer.sendEmptyMessage(SHOW_BITMAP);
                    bOpenVideo = true;
                    b_status = true;
                    b_Pause = true;
             //       thread_video_status = new Thread(new updateVideoStatus());
              //      thread_video_status.start();
                }
            }
        }
    }
}
