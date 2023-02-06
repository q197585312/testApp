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
import android.view.SurfaceView;
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

import butterknife.Bind;
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
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;
import xs.com.mylibrary.allinone.util.DecimalUtils;
import xs.com.mylibrary.allinone.util.ScreenUtil;

/**
 * Created by Administrator on 2015/11/16. (轮盘)
 */
public class LiveFDigBetActivity extends GameBaseActivity{
    public static final int SHOW_RESULTS = 2;
    public static final int SHOW_BET_SECCESS = 11;
    public static final int SHOW_BET_ERROR = 12;
    private static final int OPEN_VIDEO_FAIL = 21;
    private static final int SHOWTIMER = 3;
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
    Thread m_sThread;
    boolean m_res = false;
    boolean m_res_video = false;
    boolean bActivityExist = true;
    @Bind(R.id.b_imageView_video)
    IjkVideoView view;
    @Bind(R.id.b_text_video)
    TextView text_Video;
    @Bind(R.id.videoErrorMesseg)
    TextView videoErrorMesseg;
    @Bind(R.id.b_btn_video_reload)
    Button btn_Video;
    @Bind(R.id.b_voice)
    Button btn_Mute;
    //视频结束
    //第一行
    @Bind(R.id.ll_tv3)
    LinearLayout ll_tv3;
    @Bind(R.id.ll_tv6)
    LinearLayout ll_tv6;
    @Bind(R.id.ll_tv9)
    LinearLayout ll_tv9;
    @Bind(R.id.ll_tv12)
    LinearLayout ll_tv12;
    @Bind(R.id.ll_tv15)
    LinearLayout ll_tv15;
    @Bind(R.id.ll_tv18)
    LinearLayout ll_tv18;
    @Bind(R.id.ll_tv21)
    LinearLayout ll_tv21;
    @Bind(R.id.ll_tv24)
    LinearLayout ll_tv24;
    @Bind(R.id.ll_tv27)
    LinearLayout ll_tv27;
    @Bind(R.id.ll_tv30)
    LinearLayout ll_tv30;
    @Bind(R.id.ll_tv33)
    LinearLayout ll_tv33;
    @Bind(R.id.ll_tv36)
    LinearLayout ll_tv36;
    @Bind(R.id.ll_tv2to1)
    LinearLayout ll_tv2to1;
    //第二行
    @Bind(R.id.ll_tv2)
    LinearLayout ll_tv2;
    @Bind(R.id.ll_tv5)
    LinearLayout ll_tv5;
    @Bind(R.id.ll_tv8)
    LinearLayout ll_tv8;
    @Bind(R.id.ll_tv11)
    LinearLayout ll_tv11;
    @Bind(R.id.ll_tv14)
    LinearLayout ll_tv14;
    @Bind(R.id.ll_tv17)
    LinearLayout ll_tv17;
    @Bind(R.id.ll_tv20)
    LinearLayout ll_tv20;
    @Bind(R.id.ll_tv23)
    LinearLayout ll_tv23;
    @Bind(R.id.ll_tv26)
    LinearLayout ll_tv26;
    @Bind(R.id.ll_tv29)
    LinearLayout ll_tv29;
    @Bind(R.id.ll_tv32)
    LinearLayout ll_tv32;
    @Bind(R.id.ll_tv35)
    LinearLayout ll_tv35;
    @Bind(R.id.ll_tv2to11)
    LinearLayout ll_tv2to11;
    //第三行
    @Bind(R.id.ll_tv1)
    LinearLayout ll_tv1;
    @Bind(R.id.ll_tv4)
    LinearLayout ll_tv4;
    @Bind(R.id.ll_tv7)
    LinearLayout ll_tv7;
    @Bind(R.id.ll_tv10)
    LinearLayout ll_tv10;
    @Bind(R.id.ll_tv13)
    LinearLayout ll_tv13;
    @Bind(R.id.ll_tv16)
    LinearLayout ll_tv16;
    @Bind(R.id.ll_tv19)
    LinearLayout ll_tv19;
    @Bind(R.id.ll_tv22)
    LinearLayout ll_tv22;
    @Bind(R.id.ll_tv25)
    LinearLayout ll_tv25;
    @Bind(R.id.ll_tv28)
    LinearLayout ll_tv28;
    @Bind(R.id.ll_tv31)
    LinearLayout ll_tv31;
    @Bind(R.id.ll_tv34)
    LinearLayout ll_tv34;
    @Bind(R.id.ll_tv2to22)
    LinearLayout ll_tv2to22;
    //倒数第二行
    @Bind(R.id.ll_tv1st12)
    LinearLayout ll_tv1st12;
    @Bind(R.id.ll_tv2nd12)
    LinearLayout ll_tv2nd12;
    @Bind(R.id.ll_tv3rd12)
    LinearLayout ll_tv3rd12;
    //倒数第一行
    @Bind(R.id.ll_tv1to18)
    LinearLayout ll_tv1to18;
    @Bind(R.id.ll_tveven)
    LinearLayout ll_tveven;
    @Bind(R.id.ll_tvred)
    LinearLayout ll_tvred;
    @Bind(R.id.ll_tvblack)
    LinearLayout ll_tvblack;
    @Bind(R.id.ll_tvodd)
    LinearLayout ll_tvodd;
    @Bind(R.id.ll_tv19to36)
    LinearLayout ll_tv19to36;
    @Bind(R.id.tv_titletop)
    TextView tv_titletop;
    @Bind(R.id.back)
    ImageView back1;
    @Bind(R.id.six_black_tv)
    TextView six_black_tv;
    @Bind(R.id.six_red_tv)
    TextView six_red_tv;
    @Bind(R.id.six_odd_tv)
    TextView six_odd_tv;
    @Bind(R.id.six_even_tv)
    TextView six_even_tv;
    String account;
    NyVolleyJsonThreadHandler<DigGameBetBean> diggameThread;
    @Bind(R.id.tv_nodata)
    TextView tv_nodata;
    @Bind(R.id.lv_ball_result)
    ListView lv_ball_result;
    @Bind(R.id.tv_cycle)
    TextView tv_cycle;
    @Bind(R.id.tv_ball_result)
    TextView tv_ball_result;
    @Bind(R.id.tv_time)
    TextView tv_time;
    int tag = 0;
    int tag1; //区别是下注还是获取列表
    BroadcastReceiver mItemViewListClickReceiver;
    int timeend;
    //开奖
    @Bind(R.id.rl_dialog1)
    RelativeLayout rl_dialog;
    @Bind(R.id.tv_toumingdu)
    TextView tv_toumingdu;
    @Bind(R.id.loading)
    ProgressBar loading;
    @Bind(R.id.progress_dialog_tv)
    TextView progress_dialog_tv;
    @Bind(R.id.fragment_game_hall_one)
    FrameLayout fragment_game_hall_one;
    //Scoll balls
    Double gettotalbetscoll;
    Double tototalbetscoll=0.0;
    Double number_maxbet;
    Double number_mixbet;
    Double evenodd_maxbet;
    Double evenodd_mixbet;
    Double redblack_maxbet;
    Double redblack_mixbet;
    Double com_maxbet;
    Double com_mixbet;
    Double bigsmall_maxbet;
    Double bigsmall_mixbet;
    String playtype;
    String bettype;
    Long cutdown;
    String strperiodrou;
    //获取倒计时
    SharedPreferences sharedPreferencesdaojishi;
    String dao;
    @Bind(R.id.btn_login)
    Button btn_login;
    double yue=0.0;
    @Bind(R.id.tv_more)
    TextView tv_more;
    int huyin;
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
    private boolean bTimer = false;
    private boolean bShowTimer = false;
    private int iTimer = 0;
    private boolean bStatus = false;
    private Thread threadStatus = null;
    private Timer mTimerError;
    private String strbetnum;
    private int input_bet_num;
    private long reducemiaoAnInt; //时间差
    private SimpleDateFormat simpleDateFormat;
    private boolean isPlay;
    private NyVolleyJsonThreadHandler<GameBalanceBean> yueThread;  //余额接口调用
    //LANJIAN
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
                    Toast.makeText(LiveFDigBetActivity.this, R.string.xiazhusuccess, Toast.LENGTH_LONG).show();

                    break;
                case SHOW_BET_ERROR:
                    blockDialog.dismiss();
                    dialogshow.hide();
                    Toast.makeText(LiveFDigBetActivity.this, BetErrorMessage, Toast.LENGTH_LONG).show();
                    break;
                case SHOW_RESULTS:
                    if (gameResultsAdapter ==  null){

                        gameResultsAdapter = new PersonGameResultAdapter(gameResultsList,LiveFDigBetActivity.this);
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
            if (digGameBean.getCount_down() <= 0 || digGameBean.getCount_down() >= Min || (splite[0].equals("00")&&splite[1].equals("00")))  {
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

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(this, "");
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blockDialog = new BlockDialog(LiveFDigBetActivity.this, getString(R.string.xiazhuzhong));
        simpleDateFormat = new SimpleDateFormat("mm:ss");
        videoUrl = WebSiteUrl.LiveGameVideoUrl_Roulette;
        gameName = getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA);
        digGameBean = getApp().getLivegame_Scollballs();
        six_even_tv.setText(getString(R.string.even_lottery));
        six_odd_tv.setText(getString(R.string.odd_lottery));
        six_black_tv.setText(getString(R.string.hei));
        six_red_tv.setText(getString(R.string.hong));
        setTitle(getString(R.string.lunpan));
        progress_dialog_tv.setText(R.string.kaijiangzhong);
        onclickevent();


        tv_more.setText(getString(R.string.gengduo));

        tv_ball_result.setText(getString(R.string.lunpan)+" "+getString(R.string.jieguolist));
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAllData = true;
                back();
            }
        });
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle=new Bundle();
//                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA,digGameBean.getGame_name());
//                AppTool.activiyJump(LiveFDigBetActivity.this, PersonResultListActivity.class,bundle);

            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        tv_nodata.setText(R.string.nadata);

        gameResultsParams  = "web_id="+0 + "&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()
                +"&type1="+7+"&type2=" + digGameBean.getGame_type();
        StartUpdateGameStatus();
        startNewVideo();
    }

    // 视频启动
    public void startNewVideo() {
        view.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        view.setVideoURI(Uri.parse(videoUrl));
        view.start();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_person_home_game_hall_six;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onclickevent(){
        //第一行
        ll_tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","03");
            }
        });
        ll_tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","06");
            }
        });
        ll_tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","09");
            }
        });
        ll_tv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","12");
            }
        });
        ll_tv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","15");
            }
        });
        ll_tv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","18");
            }
        });
        ll_tv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","21");
            }
        });
        ll_tv24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","24");
            }
        });
        ll_tv27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","27");
            }
        });
        ll_tv30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","30");
            }
        });
        ll_tv33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","33");
            }
        });
        ll_tv36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","36");
            }
        });

        ll_tv2to1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","4");
            }
        });
       //第二行
        ll_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","02");
            }
        });
        ll_tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","05");
            }
        });
        ll_tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","08");
            }
        });
        ll_tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","11");
            }
        });
        ll_tv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","14");
            }
        });
        ll_tv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","17");
            }
        });
        ll_tv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","20");
            }
        });
        ll_tv23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","23");
            }
        });
        ll_tv26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","26");
            }
        });
        ll_tv29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","29");
            }
        });
        ll_tv32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","32");
            }
        });
        ll_tv35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","35");
            }
        });
        ll_tv2to11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","5");
            }
        });
        //第三行
        ll_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","01");
            }
        });
        ll_tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","04");
            }
        });
        ll_tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","07");
            }
        });
        ll_tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","10");
            }
        });
        ll_tv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","13");
            }
        });
        ll_tv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","16");
            }
        });
        ll_tv19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","19");
            }
        });
        ll_tv22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","22");
            }
        });
        ll_tv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","25");
            }
        });
        ll_tv28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","28");
            }
        });
        ll_tv31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","31");
            }
        });
        ll_tv34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","34");
            }
        });
        ll_tv2to22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","6");
            }
        });
        //倒数第二行
        ll_tv1st12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","1");
            }
        });
        ll_tv2nd12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","2");
            }
        });
        ll_tv3rd12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","3");
            }
        });
        //倒数第一行
        ll_tv1to18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"small","0");
            }
        });
        ll_tveven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"even","0");
            }
        });
        ll_tvred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"red","1");
            }
        });

        ll_tvblack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"black","0");
            }
        });
        ll_tvodd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"odd","1");
            }
        });
        ll_tv19to36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"big","1");
            }
        });
    }

    public void ShowBetDialog(final LiveGameBean digGameBean,final String betType,final String number)
    {
        if(digGameBean.getCount_down() <=0 || digGameBean.getCount_down() >3600000)
            return;
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(LiveFDigBetActivity.this);
        View view = LayoutInflater.from(LiveFDigBetActivity.this).inflate(R.layout.betting_dialog, null);
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
                tv_odds.setText(digGameBean.getOddeven_factor());
                break;
            case "small":
                tv_title.setText(R.string.big_small);
                tv_betType.setText(R.string.small); //双 小
                tv_odds.setText(digGameBean.getBigsmall_factor());
                break;

            case "big":
                tv_title.setText(R.string.big_small);
                tv_betType.setText(R.string.big);
                tv_odds.setText(digGameBean.getBigsmall_factor());
                break;
            case "odd":
                tv_title.setText(R.string.even_odd);
                tv_betType.setText(R.string.odd_lottery);
                tv_odds.setText(digGameBean.getOddeven_factor());
                break;
            case "number":
                tv_title.setText(R.string.number_lottery);
                tv_betType.setText(number);
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case "red":
                tv_title.setText(R.string.honghei);
                tv_betType.setText(R.string.hong);
                tv_odds.setText(digGameBean.getRedblack_factor());
                break;
            case "black":
                tv_title.setText(R.string.honghei);
                tv_betType.setText(R.string.hei);
                tv_odds.setText(digGameBean.getRedblack_factor());
                break;
            case "combination":
                tv_title.setText(R.string.zhuhe);
                tv_betType.setText(this.getString(R.string.zhuhe)+ " " +number);
                tv_odds.setText(digGameBean.getCombination_factor());
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
                                              NumberGameBet(tv_bet_num.getText().toString().trim(), digGameBean,betType,number);

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
                Toast.makeText(LiveFDigBetActivity.this, R.string.qingchashumu, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(LiveFDigBetActivity.this, R.string.yuebuzhu,Toast.LENGTH_LONG).show();
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
                    case "number":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getNumber_mixbet(),digGameBean.getNumber_maxbet(),digGameBean.getNumber_total(),digGameBean.getNumber_bet_total());
                        break;
                    case "combination":
                        postPlayType = playType;
                        bBet = LimitBet(d_betMoney,digGameBean.getCombination_mixbet(),digGameBean.getCombination_maxbet(),digGameBean.getCombination_total(),digGameBean.getCombination_bet_total());
                        break;
                    case "red":

                    case "black":
                        postPlayType = "color";
                        bBet = LimitBet(d_betMoney,digGameBean.getRedblack_mixbet(),digGameBean.getRedblack_maxbet(),digGameBean.getRedblack_total(),digGameBean.getRedblack_bet_total());
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
        bActivityExist = true;
        if (view!=null){
            view.stopPlayback();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    public class UpdateGameStatus implements Runnable {
        public void run() {
            while (bGetNumberStatus)
            {
                try {
                    //   Log.i("LanjianTest","UpdateGameStatus");
                    getApp().GetLiveGameStatus(updateAllData,format,"LiveFDigBetActivity");
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
               //     Log.i("LanjianTest","UpdateResult="+digGameBean.getCount_down());
                    //局数不同，并且开奖的时候才能去拿结果
                    if(isAttached&&bGetResults || (!gameNumber.equals(digGameBean.getGame_perid())&&tv_time!=null && tv_time.getText().equals("00:00")))
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

                betUrl = WebSiteUrl.LiveGameBetRoulette;

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
                            digGameBean.setBigsmall_bet_total(digGameBean.getBigsmall_bet_total()+
                                    Double.parseDouble(betMoney));
                            break;

                        case "evenodd":
                            digGameBean.setOddeven_bet_total(digGameBean.getOddeven_bet_total()+
                                    Double.parseDouble(betMoney));
                            break;
                        case "number":
                            digGameBean.setNumber_bet_total(digGameBean.getNumber_bet_total()+
                                    Double.parseDouble(betMoney));
                            break;
                        case "color":
                            digGameBean.setRedblack_bet_total(digGameBean.getRedblack_bet_total()+
                                    Double.parseDouble(betMoney));
                            break;
                        case "combination":
                            digGameBean.setCombination_bet_total(digGameBean.getCombination_bet_total()+
                                    Double.parseDouble(betMoney));
                            break;

                    }
                    handlerTimer.sendEmptyMessage(SHOW_BET_SECCESS);
                }else{
                    switch (orgData.getMsg())
                    {
                        case "-1":
                            BetErrorMessage = LiveFDigBetActivity.this.getString(R.string.xiazhucuowu);
                            break;
                        case "-2":
                            BetErrorMessage = LiveFDigBetActivity.this.getString(R.string.xiazhuzuixiao);
                            break;
                        case "-3":
                            BetErrorMessage = LiveFDigBetActivity.this.getString(R.string.xiazhuzuida);
                            break;
                        case "-4":
                            BetErrorMessage = LiveFDigBetActivity.this.getString(R.string.xiazhucaoguo);
                            break;
                        case "-5":
                            BetErrorMessage = LiveFDigBetActivity.this.getString(R.string.betshibai);
                            break;
                        case "-6":
                            BetErrorMessage = LiveFDigBetActivity.this.getString(R.string.daojishiend);
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
