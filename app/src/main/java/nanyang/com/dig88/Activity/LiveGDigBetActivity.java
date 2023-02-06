package nanyang.com.dig88.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.View.OnClickListener;
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

/**
 * Created by Administrator on 2015/11/16.
 */
public class LiveGDigBetActivity extends GameBaseActivity{
    public static final int SHOW_RESULTS = 2;
    public static final int SHOW_BET_SECCESS = 11;
    public static final int SHOW_BET_ERROR = 12;
    private static final int OPEN_VIDEO_FAIL = 21;
    private static final int SHOWBITMAP = 0;
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
    @Bind(R.id.ivtop1)
    TextView ivtop1;
    @Bind(R.id.ivtop2)
    TextView ivtop2;
    @Bind(R.id.ivtop3)
    TextView ivtop3;
    @Bind(R.id.ivtop4)
    TextView ivtop4;
    @Bind(R.id.ivtop5)
    TextView ivtop5;
    @Bind(R.id.ivtop6)
    TextView ivtop6;
    @Bind(R.id.ivtop7)
    TextView ivtop7;
    //第一行大小
    @Bind(R.id.ll_small1)
    LinearLayout ll_small1;
    @Bind(R.id.ll_big1)
    LinearLayout ll_big1;
    //第二行大小
    @Bind(R.id.ll_small2)
    LinearLayout ll_small2;
    @Bind(R.id.ll_big2)
    LinearLayout ll_big2;
    //图片切换
    @Bind(R.id.iv_center)
    TextView iv_center;
    @Bind(R.id.iv_bottom)
    TextView iv_bottom;
    // top （1-36  单双）
    @Bind(R.id.ll_one)
    LinearLayout ll_one;
    @Bind(R.id.ll_two)
    LinearLayout ll_two;
    @Bind(R.id.ll_three)
    LinearLayout ll_three;
    @Bind(R.id.ll_four)
    LinearLayout ll_four;
    @Bind(R.id.ll_five)
    LinearLayout ll_five;
    @Bind(R.id.ll_six)
    LinearLayout ll_six;
    @Bind(R.id.ll_seven)
    LinearLayout ll_seven;
    @Bind(R.id.ll_eghit)
    LinearLayout ll_eghit;
    @Bind(R.id.ll_nine)
    LinearLayout ll_nine;
    @Bind(R.id.ll_ten)
    LinearLayout ll_ten;
    @Bind(R.id.ll_eleven)
    LinearLayout ll_eleven;
    @Bind(R.id.ll_twlve)
    LinearLayout ll_twlve;
    @Bind(R.id.ll_thirteen)
    LinearLayout ll_thirteen;
    @Bind(R.id.ll_fourty)
    LinearLayout ll_fourty;
    @Bind(R.id.ll_fifty)
    LinearLayout ll_fifty;
    @Bind(R.id.ll_sixty)
    LinearLayout ll_sixty;
    @Bind(R.id.ll_seventy)
    LinearLayout ll_seventy;
    @Bind(R.id.ll_eghity)
    LinearLayout ll_eghity;
    @Bind(R.id.ll_ninety)
    LinearLayout ll_ninety;
    @Bind(R.id.ll_twenty)
    LinearLayout ll_twenty;
    @Bind(R.id.ll_twentyone)
    LinearLayout ll_twentyone;
    @Bind(R.id.ll_twentytwo1)
    LinearLayout ll_twentytwo1;
    @Bind(R.id.ll_twentythree1)
    LinearLayout ll_twentythree1;
    @Bind(R.id.ll_twentyfour1)
    LinearLayout ll_twentyfour1;
    @Bind(R.id.ll_twentyfive1)
    LinearLayout ll_twentyfive1;
    @Bind(R.id.ll_twentysix1)
    LinearLayout ll_twentysix1;
    @Bind(R.id.ll_twentyseven)
    LinearLayout ll_twentyseven;
    @Bind(R.id.ll_twenteghit)
    LinearLayout ll_twenteghit;
    @Bind(R.id.ll_twentynine)
    LinearLayout ll_twentynine;
    @Bind(R.id.ll_threety11)
    LinearLayout ll_threety11;
    @Bind(R.id.ll_threetyone1)
    LinearLayout ll_threetyone1;
    @Bind(R.id.ll_threetytwo1)
    LinearLayout ll_threetytwo1;
    @Bind(R.id.ll_threetythree1)
    LinearLayout ll_threetythree1;
    @Bind(R.id.ll_threetyfour1)
    LinearLayout ll_threetyfour1;
    @Bind(R.id.ll_threetyfive1)
    LinearLayout ll_threetyfive1;
    @Bind(R.id.ll_threetysix1)
    LinearLayout ll_threetysix1;
    @Bind(R.id.ll_simgle11)
    LinearLayout ll_simgle11;
    @Bind(R.id.ll_double11)
    LinearLayout ll_double11;
    @Bind(R.id.ll_smalldouble1)
    LinearLayout ll_smalldouble1;
    @Bind(R.id.ll_bigdouble11)
    LinearLayout ll_bigdouble11;
    @Bind(R.id.ll_smallsimgle1)
    LinearLayout ll_smallsimgle1;
    @Bind(R.id.ll_bigsimgle1)
    LinearLayout ll_bigsimgle1;
    // bottom （1-36  单双）
    @Bind(R.id.ll_one1)
    LinearLayout ll_one1;
    @Bind(R.id.ll_two1)
    LinearLayout ll_two1;
    @Bind(R.id.ll_three1)
    LinearLayout ll_three1;
    @Bind(R.id.ll_four1)
    LinearLayout ll_four1;
    @Bind(R.id.ll_five1)
    LinearLayout ll_five1;
    @Bind(R.id.ll_six1)
    LinearLayout ll_six1;
    @Bind(R.id.ll_seven1)
    LinearLayout ll_seven1;
    @Bind(R.id.ll_eghit1)
    LinearLayout ll_eghit1;
    @Bind(R.id.ll_nine1)
    LinearLayout ll_nine1;
    @Bind(R.id.ll_ten1)
    LinearLayout ll_ten1;
    @Bind(R.id.ll_eleven1)
    LinearLayout ll_eleven1;
    @Bind(R.id.ll_tweeve1)
    LinearLayout ll_tweeve1;
    @Bind(R.id.ll_thirteen1)
    LinearLayout ll_thirteen1;
    @Bind(R.id.ll_fourty1)
    LinearLayout ll_fourty1;
    @Bind(R.id.ll_fifty1)
    LinearLayout ll_fifty1;
    @Bind(R.id.ll_sixty1)
    LinearLayout ll_sixty1;
    @Bind(R.id.ll_seventy1)
    LinearLayout ll_seventy1;
    @Bind(R.id.ll_ehgity1)
    LinearLayout ll_ehgity1;
    @Bind(R.id.ll_ninety1)
    LinearLayout ll_ninety1;
    @Bind(R.id.ll_twenty1)
    LinearLayout ll_twenty1;
    @Bind(R.id.ll_twenty1one)
    LinearLayout ll_twenty1one;
    @Bind(R.id.ll_twenty1two)
    LinearLayout ll_twenty1two;
    @Bind(R.id.ll_twenty1three)
    LinearLayout ll_twenty1three;
    @Bind(R.id.ll_twenty1four)
    LinearLayout ll_twenty1four;
    @Bind(R.id.ll_twenty1five)
    LinearLayout ll_twenty1five;
    @Bind(R.id.ll_twenty1six)
    LinearLayout ll_twenty1six;
    @Bind(R.id.ll_twenty1seven)
    LinearLayout ll_twenty1seven;
    @Bind(R.id.ll_twenty1ehgit)
    LinearLayout ll_twenty1ehgit;
    @Bind(R.id.ll_twenty1nine)
    LinearLayout ll_twenty1nine;
    @Bind(R.id.ll_threety)
    LinearLayout ll_threety;
    @Bind(R.id.ll_threetyone)
    LinearLayout ll_threetyone;
    @Bind(R.id.ll_threetytwo)
    LinearLayout ll_threetytwo;
    @Bind(R.id.ll_threetythree)
    LinearLayout ll_threetythree;
    @Bind(R.id.ll_threetyfour)
    LinearLayout ll_threetyfour;
    @Bind(R.id.ll_threetyfive)
    LinearLayout ll_threetyfive;
    @Bind(R.id.ll_threetysix)
    LinearLayout ll_threetysix;
    //单 双
    @Bind(R.id.ll_simgle1)
    LinearLayout ll_simgle1;
    @Bind(R.id.ll_double1)
    LinearLayout ll_double1;
    @Bind(R.id.ll_smalldouble11)
    LinearLayout ll_smalldouble11;
    @Bind(R.id.ll_bigdouble1)
    LinearLayout ll_bigdouble1;
    @Bind(R.id.ll_smallsimgle11)
    LinearLayout ll_smallsimgle11;
    @Bind(R.id.ll_bigsimgle11)
    LinearLayout ll_bigsimgle11;
    @Bind(R.id.tv_time)
    TextView tv_time;
    int tagloacation = 0;
    int qiuweibiaohzi1 = 0;
    int shangxiatag = 0;
    int tag1; //区别是下注还是获取列表
    String account;
    NyVolleyJsonThreadHandler<DigGameBetBean> diggameThread;
    String bigsmallbet;
    String simglenumberbet;
    String doublesimglebet;
    String bigsmalldoublesimgle;
    String playtype=""; //游戏类型
    String bettype=""; //下注类型
    String location;
    String regularEx = ",";
    @Bind(R.id.tv_cycle)
    TextView tv_cycle;
    @Bind(R.id.tv_ball_result)
    TextView tv_ball_result;
    @Bind(R.id.tv_titletop)
    TextView tv_titletop;
    @Bind(R.id.back)
    ImageView back1;
    @Bind(R.id.tv_nodata)
    TextView tv_nodata;
    @Bind(R.id.lv_ball_result)
    ListView lv_ball_result;
    int tag = 0;
    //开奖
    RelativeLayout rl_dialog;
    TextView tv_toumingdu;
    ProgressBar loading;
    //翻译
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;
    @Bind(R.id.tv_dan1)
    TextView tv_dan1;
    @Bind(R.id.tv_shuang1)
    TextView tv_shuang1;
    @Bind(R.id.tv_xiaoshuang1)
    TextView tv_xiaoshuang1;
    @Bind(R.id.tv_dashuang1)
    TextView tv_dashuang1;
    @Bind(R.id.tv_xiaodan1)
    TextView tv_xiaodan1;
    @Bind(R.id.tv_dadan1)
    TextView tv_dadan1;
    @Bind(R.id.tv_dan2)
    TextView tv_dan2;
    @Bind(R.id.tv_shuang2)
    TextView tv_shuang2;
    @Bind(R.id.tv_xiaoshuang2)
    TextView tv_xiaoshuang2;
    @Bind(R.id.tv_dashuang2)
    TextView tv_dashuang2;
    @Bind(R.id.tv_xiaodan2)
    TextView tv_xiaodan2;
    @Bind(R.id.tv_dadan2)
    TextView tv_dadan2;
    @Bind(R.id.progress_dialog_tv)
    TextView progress_dialog_tv;
    @Bind(R.id.fragment_game_hall_one)
    FrameLayout fragment_game_hall_one;
    //获取倒计时
    SharedPreferences sharedPreferencesdaojishi;
    String dao;
    @Bind(R.id.btn_login)
    Button btn_login;
    double yue=0.0;
    @Bind(R.id.tv_more)
    TextView tv_more;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor1;
    Long cutdown;
    String strperiodmult;
    //Mult balls
    Double gettotalbetmult;
    Double tototalbetmult=0.0;
    Double bigsmall_maxbet;
    Double bigsmall_mixbet;
    Double evenodd_maxbet;
    Double evenodd_mixbet;
    Double com_maxbet;
    Double com_mixbet;
    Double number_maxbet;
    Double number_mixbet;
    boolean bGetNumberStatus = true;
    boolean bTimerStatus = true;
    boolean bTimerResults = true;
    boolean bGetResults = true;
    private boolean b_Pause = true;
    private boolean b_status;
    private Thread thread_video_status = null;
    private boolean bVideo;
//    private Thread threadVideo = null;
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
                    Toast.makeText(LiveGDigBetActivity.this, R.string.xiazhusuccess, Toast.LENGTH_LONG).show();

                    break;
                case SHOW_BET_ERROR:
                    blockDialog.dismiss();
                    dialogshow.hide();
                    Toast.makeText(LiveGDigBetActivity.this, BetErrorMessage, Toast.LENGTH_LONG).show();
                    break;
                case SHOW_RESULTS:
                    if (gameResultsAdapter ==  null){

                        gameResultsAdapter = new PersonGameResultAdapter(gameResultsList,LiveGDigBetActivity.this);
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
//        startVideo(false);

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

    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(this, "");


        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blockDialog = new BlockDialog(LiveGDigBetActivity.this, getString(R.string.xiazhuzhong));
        simpleDateFormat = new SimpleDateFormat("mm:ss");
        videoUrl = WebSiteUrl.LiveNumberGameVideoUrl_Multiple_36;
        gameName = getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA);
        digGameBean = getApp().getLivegame_MULTIPLEballs();

        rl_dialog=(RelativeLayout)findViewById(R.id.rl_dialog1);
        tv_toumingdu=(TextView)findViewById(R.id.tv_toumingdu);
        loading= (ProgressBar) findViewById(R.id.loading);
        progress_dialog_tv=(TextView)findViewById(R.id.progress_dialog_tv);
        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAllData = true;
                back();
            }
        });
        InitInterfaceText();

        setTitle(getString(R.string.zhuhe36qiu));

        SetNumberClickListener();
        SetBetButtonClickListener();

        tv_ball_result.setText(getString(R.string.zhuhe36qiu)+" "+getString(R.string.jieguolist));
        back1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               back();
            }
        });
        tv_more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle=new Bundle();
//                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA,digGameBean.getGame_name());
//                AppTool.activiyJump(LiveGDigBetActivity.this, PersonResultListActivity.class,bundle);

            }
        });
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

    //翻译
    private void InitInterfaceText(){

        tv1.setText(getString(R.string.small));
        tv2.setText(getString(R.string.big));
        tv3.setText(getString(R.string.small));
        tv4.setText(getString(R.string.big));
        tv_more.setText(getString(R.string.gengduo));
        tv_dan1.setText(getString(R.string.odd_lottery));
        tv_shuang1.setText(getString(R.string.even_lottery));
        tv_dan2.setText(getString(R.string.odd_lottery));
        tv_shuang2.setText(getString(R.string.even_lottery));
        tv_xiaoshuang1.setText(getString(R.string.txiaoshuang));
        tv_dashuang1.setText(getString(R.string.tdashuang));
        tv_xiaodan1.setText(getString(R.string.txiaodan));
        tv_dadan1.setText(getString(R.string.tdadan));
        tv_xiaoshuang2.setText(getString(R.string.txiaoshuang));
        tv_dashuang2.setText(getString(R.string.tdashuang));
        tv_xiaodan2.setText(getString(R.string.txiaodan));
        tv_dadan2.setText(getString(R.string.tdadan));
        progress_dialog_tv.setText(getString(R.string.kaijiangzhong));
        tv_nodata.setText(R.string.nadata);
        iv_center.setText("1");
        iv_bottom.setText("2");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_person_home_game_hall_seven;
    }

    public void SetNumberClickListener() {


        ivtop1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_center.setText("1");
                iv_bottom.setText("2");

            }
        });
        ivtop2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_center.setText("2");
                iv_bottom.setText("3");

            }
        });
        ivtop3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_center.setText("3");
                iv_bottom.setText("4");

            }
        });
        ivtop4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_center.setText("4");
                iv_bottom.setText("5");

            }
        });
        ivtop5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_center.setText("5");
                iv_bottom.setText("6");

            }
        });
        ivtop6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_center.setText("6");
                iv_bottom.setText("7");

            }
        });
        ivtop7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_center.setText("7");
                iv_bottom.setText("1");

            }
        });


    }

    public void SetBetButtonClickListener() {

        //上面一节
        ll_small1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"small","0",0);
            }
        });
        ll_big1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"big","1",0);
            }
        });
        ll_one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","01",0);
            }
        });

        ll_two.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","02",0);
            }
        });
        ll_three.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","03",0);
            }
        });
        ll_four.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","04",0);
            }
        });
        ll_five.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","05",0);
            }
        });
        ll_six.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","06",0);
            }
        });
        ll_seven.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","07",0);
            }
        });
        ll_eghit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","08",0);
            }
        });
        ll_nine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","09",0);
            }
        });
        ll_ten.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","10",0);
            }
        });

        ll_eleven.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","11",0);
            }
        });

        ll_twlve.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","12",0);
            }
        });
        ll_thirteen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","13",0);
            }
        });
        ll_fourty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","14",0);
            }
        });
        ll_fifty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","15",0);
            }
        });
        ll_sixty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","16",0);
            }
        });
        ll_seventy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","17",0);
            }
        });
        ll_eghity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","18",0);
            }
        });
        ll_ninety.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","19",0);
            }
        });
        ll_twenty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","20",0);
            }
        });
        ll_twentyone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","21",0);
            }
        });
        ll_twentytwo1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","22",0);
            }
        });
        ll_twentythree1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","23",0);
            }
        });
        ll_twentyfour1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","24",0);
            }
        });
        ll_twentyfive1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","25",0);
            }
        });
        ll_twentysix1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","26",0);
            }
        });
        ll_twentyseven.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","27",0);
            }
        });
        ll_twenteghit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","28",0);
            }
        });
        ll_twentynine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","29",0);
            }
        });
        ll_threety11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","30",0);
            }
        });
        ll_threetyone1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","31",0);
            }

        });
        ll_threetytwo1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","32",0);
            }
        });
        ll_threetythree1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","33",0);
            }
        });
        ll_threetyfour1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","34",0);
            }
        });
        ll_threetyfive1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","35",0);
            }
        });
        ll_threetysix1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","36",0);
            }
        });

        //（单 双）
        //******
        ll_simgle11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"odd","1",0);
            }
        });
        ll_double11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"even","0",0);
            }
        });
        ll_smalldouble1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","0",0);
            }
        });
        ll_bigdouble11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","2",0);
            }
        });
        ll_smallsimgle1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","1",0);
            }
        });
        ll_bigsimgle1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","3",0);
            }
        });

        //下面一节
        ll_small2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"small","0",1);
            }
        });
        ll_big2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"big","1",1);
            }
        });
        ll_one1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","01",1);
            }
        });

        ll_two1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","02",1);
            }
        });
        ll_three1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","03",1);
            }
        });
        ll_four1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","04",1);
            }
        });
        ll_five1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","05",1);
            }
        });
        ll_six1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","06",1);
            }
        });
        ll_seven1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","07",1);
            }
        });
        ll_eghit1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","08",1);
            }
        });
        ll_nine1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","09",1);
            }
        });
        ll_ten1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","10",1);
            }
        });

        ll_eleven1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","11",1);
            }
        });

        ll_tweeve1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","12",1);
            }
        });
        ll_thirteen1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","13",1);
            }
        });
        ll_fourty1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","14",1);
            }
        });
        ll_fifty1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","15",1);
            }
        });
        ll_sixty1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","16",1);
            }
        });
        ll_seventy1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","17",1);
            }
        });
        ll_ehgity1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","18",1);
            }
        });
        ll_ninety1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","19",1);
            }
        });
        ll_twenty1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","20",1);
            }
        });
        ll_twenty1one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","21",1);
            }
        });
        ll_twenty1two.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","22",1);
            }
        });
        ll_twenty1three.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","23",1);
            }
        });
        ll_twenty1four.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","24",1);
            }
        });
        ll_twenty1five.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","25",1);
            }
        });
        ll_twenty1six.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","26",1);
            }
        });
        ll_twenty1seven.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","27",1);
            }
        });
        ll_twenty1ehgit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","28",1);
            }
        });
        ll_twenty1nine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","29",1);
            }
        });
        ll_threety.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","30",1);
            }
        });
        ll_threetyone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","31",1);
            }

        });
        ll_threetytwo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","32",1);
            }
        });
        ll_threetythree.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","33",1);
            }
        });
        ll_threetyfour.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","34",1);
            }
        });
        ll_threetyfive.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","35",1);
            }
        });
        ll_threetysix.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"number","36",1);
            }
        });
        //（单 双）
        //******
        ll_simgle1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"odd","1",1);
            }
        });
        ll_double1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"even","0",1);
            }
        });
        ll_smalldouble11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","0",1);
            }
        });
        ll_bigdouble1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","2",1);
            }
        });
        ll_smallsimgle11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","1",1);
            }
        });
        ll_bigsimgle11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,"combination","3",1);
            }
        });

    }

    public void ShowBetDialog(final LiveGameBean digGameBean,final String betType,final String number,final  int buttonIndex)
    {
        if(digGameBean.getCount_down() <=0 || digGameBean.getCount_down() >3600000)
            return;
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(LiveGDigBetActivity.this);
        View view = LayoutInflater.from(LiveGDigBetActivity.this).inflate(R.layout.betting_dialog, null);
        ImageView iv_hide = (ImageView) view.findViewById(R.id.iv_hide);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_bet_pop_title);  //大小
        TextView tv_betType = (TextView) view.findViewById(R.id.tv_bet_pop_number);  //投资号码
        TextView tv_odds = (TextView) view.findViewById(R.id.tv_bet_pop_odds);  //赔率
        final EditText tv_bet_num = (EditText) view.findViewById(R.id.edt_bet_pop_amount);  //投注数目
        final TextView tv_act_bet = (TextView) view.findViewById(R.id.tv_bet_pop_actual_amount);  //实际投注
        Button btn_confim = (Button) view.findViewById(R.id.btn_bet_pop_sure); //确定
        Button btn_cancel = (Button) view.findViewById(R.id.btn_bet_pop_cancel); //取消
         String firstNumber = "";
        if(buttonIndex == 0)
            firstNumber = iv_center.getText().toString();
        else
            firstNumber = iv_bottom.getText().toString();
        switch(betType)
        {
            case "even":
                tv_title.setText(this.getString(R.string.qiu) +" "+firstNumber+" "+this.getString(R.string.even_odd));
                tv_betType.setText(R.string.even_lottery);
                tv_odds.setText(digGameBean.getOddeven_factor());
                break;
            case "small":
                tv_title.setText(this.getString(R.string.qiu) +" "+firstNumber+" "+this.getString(R.string.big_small));
                tv_betType.setText(R.string.small);
                tv_odds.setText(digGameBean.getBigsmall_factor());
                break;

            case "big":
                tv_title.setText(this.getString(R.string.qiu) +" "+firstNumber+" "+this.getString(R.string.big_small));
                tv_betType.setText(R.string.big);
                tv_odds.setText(digGameBean.getBigsmall_factor());
                break;
            case "odd":
                tv_title.setText(this.getString(R.string.qiu) +" "+firstNumber+" "+this.getString(R.string.even_odd));
                tv_betType.setText(R.string.odd_lottery);
                tv_odds.setText(digGameBean.getOddeven_factor());
                break;
            case "number":
                tv_title.setText(this.getString(R.string.qiu) +" "+firstNumber+" "+this.getString(R.string.number));
                tv_betType.setText(number);
                tv_odds.setText(digGameBean.getNumber_factor());
                break;
            case "combination":
                tv_title.setText(this.getString(R.string.qiu) +" "+firstNumber+" "+this.getString(R.string.zhuhe));
                switch (number)
                {
                    case "0":
                        tv_betType.setText(this.getString(R.string.small)+" "+this.getString(R.string.even_lottery));
                        break;
                    case "1":
                        tv_betType.setText(this.getString(R.string.small)+" "+this.getString(R.string.odd_lottery));
                        break;
                    case "2":
                        tv_betType.setText(this.getString(R.string.big)+" "+this.getString(R.string.even_lottery));
                        break;
                    case "3":
                        tv_betType.setText(this.getString(R.string.big)+" "+this.getString(R.string.odd_lottery));
                        break;
                    default:
                        break;
                }

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
        iv_hide.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogshow.hide();
            }
        });
        btn_confim.setOnClickListener(new OnClickListener() {
                                          @Override
                                          public void onClick(View view) {

                                              NumberGameBet(tv_bet_num.getText().toString().trim(), digGameBean,betType,number,buttonIndex);

                                          }
                                      }
        );
        btn_cancel.setOnClickListener(new OnClickListener()
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

    public void NumberGameBet(String betMoney,LiveGameBean digGameBean,String playType,String betType,int numberIndex)
    {
        try{
            String postPlayType = "";
            if (betMoney == null || betMoney.length() == 0) {
                Toast.makeText(LiveGDigBetActivity.this, R.string.qingchashumu, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(LiveGDigBetActivity.this, R.string.yuebuzhu,Toast.LENGTH_LONG).show();
                    return ;
                }
                boolean bBet = true;
                String firstNumber = "";
                if(numberIndex == 0)
                    firstNumber = iv_center.getText().toString();
                else
                    firstNumber = iv_bottom.getText().toString();
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
                    betNumberGame.setNumberIndex(firstNumber);
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
    protected void onDestroy()  {
        super.onDestroy();
        bActivityExist = false;
        if (view!=null){
            view.stopPlayback();
        }
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

    // 视频停止
    public void stopVideo() {
        b_status = false;
        b_Pause = false;
        m_res_video = false;
        thread_video_status = null;
        bVideo = false;
        b_ClickVideo = true;
        Log.i("Avplayer","stopVideo");
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
                    if(bGetResults || (!gameNumber.equals(digGameBean.getGame_perid()) && tv_time.getText().equals("00:00")))
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
        private String numberIndex;
        private LiveGameBean digGameBean;
        private String betType;

        public void setNumberIndex(String numberIndex) {
            this.numberIndex = numberIndex;
        }

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
                Log.i("LanjianTestBet","betType="+playType+" betMoney="+betMoney+" betNumber="+betType+" firstNumber="+numberIndex);
                String params = "web_id="+ WebSiteUrl.WebId+"&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()+"&from=App";

                params += "&get_bet="+playType+"#"+betType+"#"+numberIndex;
                String postBetMoney = String.format("%.3f",Double.parseDouble(betMoney));
                params += "#" +postBetMoney;

                betUrl = WebSiteUrl.LiveGameBetMultiple;

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
                            BetErrorMessage = LiveGDigBetActivity.this.getString(R.string.xiazhucuowu);
                            break;
                        case "-2":
                            BetErrorMessage = LiveGDigBetActivity.this.getString(R.string.xiazhuzuixiao);
                            break;
                        case "-3":
                            BetErrorMessage = LiveGDigBetActivity.this.getString(R.string.xiazhuzuida);
                            break;
                        case "-4":
                            BetErrorMessage = LiveGDigBetActivity.this.getString(R.string.xiazhucaoguo);
                            break;
                        case "-5":
                            BetErrorMessage = LiveGDigBetActivity.this.getString(R.string.betshibai);
                            break;
                        case "-6":
                            BetErrorMessage = LiveGDigBetActivity.this.getString(R.string.daojishiend);
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
