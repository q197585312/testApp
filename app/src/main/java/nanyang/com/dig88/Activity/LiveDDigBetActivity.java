package nanyang.com.dig88.Activity;

/**
 * Created by Administrator on 2015/11/12.
 * /**
 * Created by Administrator on 2015/11/12.（真人数字游戏D主页下注） 48balls
 */

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.DigGameBetBean;
import nanyang.com.dig88.Entity.LiveGameBean;
import nanyang.com.dig88.Entity.PersonGameResultBean;
import nanyang.com.dig88.Ijkplayer.IRenderView;
import nanyang.com.dig88.Ijkplayer.IjkVideoView;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

public class LiveDDigBetActivity extends GameBaseActivity{
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
    Thread m_sThread;
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
    @BindView(R.id.tv_cycle)
    TextView tv_cycle;
    @BindView(R.id.tv_ball_result)
    TextView tv_ball_result;
    @BindView(R.id.lv_ball_result)
    ListView lv_ball_result;
    @BindView(R.id.fragment_rg)
    RadioGroup fragment_rg;
    @BindView(R.id.rb_rb_1)
    RadioButton rb_rb_1;
    @BindView(R.id.rb_rb_2)
    RadioButton rb_rb_2;
    @BindView(R.id.rb_rb_3)
    RadioButton rb_rb_3;
    @BindView(R.id.rb_rb_4)
    RadioButton rb_rb_4;
    @BindView(R.id.rb_rb_5)
    RadioButton rb_rb_5;
    //英超/意甲
    @BindView(R.id.ll_yingcao)
    LinearLayout ll_yingcao;
    //西甲/德甲
    @BindView(R.id.ll_xijia)
    LinearLayout ll_xijia;

    //法甲/荷超
    @BindView(R.id.ll_fajia)
    LinearLayout ll_fajia;
    //分组竞猜
    @BindView(R.id.ll_fenzu_jingcai)
    LinearLayout ll_fenzu_jingcai;
    //大/小
    @BindView(R.id.ll_daxiao)
    LinearLayout ll_daxiao;
    String account;
    int jincaitag = 0;
    int yinyifatag=0;
    //分组竞彩选择
    @BindView(R.id.ll_england)
    LinearLayout ll_england;
    @BindView(R.id.ll_italy)
    LinearLayout ll_italy;
    @BindView(R.id.ll_spain)
    LinearLayout ll_spain;
    @BindView(R.id.ll_germany)
    LinearLayout ll_germany;
    @BindView(R.id.ll_france)
    LinearLayout ll_france;
    @BindView(R.id.ll_holland)
    LinearLayout ll_holland;
    //大小
    @BindView(R.id.ll_big)
    LinearLayout ll_big;
    @BindView(R.id.ll_small)
    LinearLayout ll_small;
    @BindView(R.id.ll_double)
    LinearLayout ll_double;
    @BindView(R.id.ll_simgle)
    LinearLayout ll_simgle;
    @BindView(R.id.ll_smalldouble)
    LinearLayout ll_smalldouble;
    @BindView(R.id.ll_smallsimgle)
    LinearLayout ll_smallsimgle;
    @BindView(R.id.ll_bigdouble)
    LinearLayout ll_bigdouble;
    @BindView(R.id.ll_bigsimgle)
    LinearLayout ll_bigsimgle;
    @BindView(R.id.ll_sliver)
    LinearLayout ll_sliver;
    @BindView(R.id.ll_golden)
    LinearLayout ll_golden;
    //大小数据
    @BindView(R.id.tv_big)
    TextView tv_big;
    @BindView(R.id.tv_small)
    TextView tv_small;
    @BindView(R.id.tv_double)
    TextView tv_double;
    @BindView(R.id.tv_simgle)
    TextView tv_simgle;
    @BindView(R.id.tv_smalldouble)
    TextView tv_smalldouble;
    @BindView(R.id.tv_smallsimgle)
    TextView tv_smallsimgle;
    @BindView(R.id.tv_bigdouble)
    TextView tv_bigdouble;
    @BindView(R.id.tv_bigsimgle)
    TextView tv_bigsimgle;
    @BindView(R.id.tv_golden)
    TextView tv_golden;
    @BindView(R.id.tv_silver)
    TextView tv_silver;
    //英超/意甲ll
    @BindView(R.id.ll_buqiexi)
    LinearLayout ll_buqiexi;
    @BindView(R.id.ll_manqie)
            LinearLayout ll_manqie;
    @BindView(R.id.ll_asenna)
    LinearLayout ll_asenna;
    @BindView(R.id.ll_manqieste)
            LinearLayout ll_manqieste;
    @BindView(R.id.ll_tuotena)
    LinearLayout ll_tuotena;
    @BindView(R.id.ll_liwufu)
            LinearLayout ll_liwufu;
    @BindView(R.id.ll_nananpdun)
    LinearLayout ll_nananpdun;
    @BindView(R.id.ll_sitewangxi)
            LinearLayout ll_sitewangxi;
    @BindView(R.id.ll_youwentusi)
    LinearLayout ll_youwentusi;
    @BindView(R.id.ll_luoma)
            LinearLayout ll_luoma;
    @BindView(R.id.ll_laqiao)
    LinearLayout ll_laqiao;
    @BindView(R.id.ll_fuluolun)
            LinearLayout ll_fuluolun;
    @BindView(R.id.ll_naboli)
    LinearLayout ll_naboli;
    @BindView(R.id.ll_renaya)
            LinearLayout ll_renaya;
    @BindView(R.id.ll_sangpuduoya)
    LinearLayout ll_sangpuduoya;
    @BindView(R.id.ll_guojimilan)
            LinearLayout ll_guojimilan;
    @BindView(R.id.ll_basaluola)
    LinearLayout ll_basaluola;
    @BindView(R.id.ll_huangjiamadeli)
    LinearLayout ll_huangjiamadeli;
    @BindView(R.id.ll_madelijingji)
    LinearLayout ll_madelijingji;
    @BindView(R.id.ll_walunxiya)
    LinearLayout ll_walunxiya;
    //西甲/德甲ll
    @BindView(R.id.ll_saiweiliya)
    LinearLayout ll_saiweiliya;
    @BindView(R.id.ll_biliyayaer)
    LinearLayout ll_biliyayaer;
    @BindView(R.id.ll_bierbae)
    LinearLayout ll_bierbae;
    @BindView(R.id.ll_saierta)
    LinearLayout ll_saierta;
    @BindView(R.id.ll_bairenmolihei)
    LinearLayout ll_bairenmolihei;
    @BindView(R.id.ll_woerfisibao)
    LinearLayout ll_woerfisibao;
    @BindView(R.id.ll_menxing)
    LinearLayout ll_menxing;
    @BindView(R.id.ll_lewokusen)
    LinearLayout ll_lewokusen;
    @BindView(R.id.ll_aogesibao)
    LinearLayout ll_aogesibao;
    @BindView(R.id.ll_saerke)
    LinearLayout ll_saerke;
    @BindView(R.id.ll_boluyaduomengde)
    LinearLayout ll_boluyaduomengde;
    @BindView(R.id.ll_huofenhaimu)
    LinearLayout ll_huofenhaimu;
    //法甲/荷超ll
    @BindView(R.id.ll_balishengrierman)
    LinearLayout ll_balishengrierman;
    @BindView(R.id.ll_liang)
    LinearLayout ll_liang;
    @BindView(R.id.ll_molage)
    LinearLayout ll_molage;
    @BindView(R.id.ll_masai)
    LinearLayout ll_masai;
    @BindView(R.id.ll_shengaidian)
    LinearLayout ll_shengaidian;
    @BindView(R.id.ll_boerduo)
    LinearLayout ll_boerduo;
    @BindView(R.id.ll_lier)
    LinearLayout ll_lier;
    @BindView(R.id.ll_mengbiliai)
    LinearLayout ll_mengbiliai;
    @BindView(R.id.ll_aiyinghuowen)
    LinearLayout ll_aiyinghuowen;
    @BindView(R.id.ll_ajisi)
    LinearLayout ll_ajisi;
    @BindView(R.id.ll_helanaermaer)
    LinearLayout ll_helanaermaer;
    @BindView(R.id.ll_feiyeruode)
    LinearLayout ll_feiyeruode;
    @BindView(R.id.ll_weitese)
    LinearLayout ll_weitese;
    @BindView(R.id.ll_zewole)
    LinearLayout ll_zewole;
    @BindView(R.id.ll_hailunweien)
    LinearLayout ll_hailunweien;
    @BindView(R.id.ll_haininggeng)
    LinearLayout ll_haininggeng;
    @BindView(R.id.tv_nodata)
    TextView tv_nodata;
    @BindView(R.id.tv_titletop)
    TextView tv_titletop;
    @BindView(R.id.back)
    ImageView back1;
    int tag = 0;
    int tag1; //区别是下注还是获取列表
    //开奖
    RelativeLayout rl_dialog;
    TextView tv_toumingdu;
    ProgressBar loading;
    TextView progress_dialog_tv;
    String playtype;
    String bettype;
    BlockDialog blockDialog;
    //翻译
    @BindView(R.id.peilv45)
    TextView peilv45;
    @BindView(R.id.peilv451)
    TextView peilv451;
    @BindView(R.id.peilv4511)
    TextView peilv4511;
    @BindView(R.id.peilv45111)
    TextView peilv45111;
    @BindView(R.id.yingcao)
    TextView yingcao;
    @BindView(R.id.qieerxi)
    TextView qieerxi;
    @BindView(R.id.manchesic)
    TextView manchesic;
    @BindView(R.id.manchesitl)
    TextView manchesitl;
    @BindView(R.id.asenna)
    TextView asenna;
    @BindView(R.id.tuotenamu)
    TextView tuotenamu;
    @BindView(R.id.nananpudun)
    TextView nananpudun;
    @BindView(R.id.sitewangxi)
    TextView sitewangxi;
    @BindView(R.id.youwentusi)
    TextView youwentusi;
    @BindView(R.id.luoma)
    TextView luoma;
    @BindView(R.id.laqiao)
    TextView laqiao;
    @BindView(R.id.fuoluolunsa)
    TextView fuoluolunsa;
    @BindView(R.id.naboli)
    TextView naboli;
    @BindView(R.id.renaya)
    TextView renaya;
    @BindView(R.id.saerke)
    TextView saerke;
    @BindView(R.id.sangpuduoya)
    TextView sangpuduoya;
    @BindView(R.id.guojimilan)
    TextView guojimilan;
    @BindView(R.id.basailuola)
    TextView basailuola;
    @BindView(R.id.huangjiamadeli)
    TextView huangjiamadeli;
    @BindView(R.id.walunxiya)
    TextView walunxiya;
    @BindView(R.id.bairenmulihei)
    TextView bairenmulihei;
    @BindView(R.id.wuerfusibao)
    TextView wuerfusibao;
    @BindView(R.id.mengxing)
    TextView mengxing;
    @BindView(R.id.lewokusen)
    TextView lewokusen;
    @BindView(R.id.saiweiliya)
    TextView saiweiliya;
    @BindView(R.id.biliyalei)
    TextView biliyalei;
    @BindView(R.id.bierbae)
    TextView bierbae;
    @BindView(R.id.saierta)
    TextView saierta;
    @BindView(R.id.aogesibao)
    TextView aogesibao;
    @BindView(R.id.boluxiya)
    TextView boluxiya;
    @BindView(R.id.huofenhaimu)
    TextView huofenhaimu;
    @BindView(R.id.balisenger)
    TextView balisenger;
    @BindView(R.id.liang)
    TextView liang;
    @BindView(R.id.molage)
    TextView molage;
    @BindView(R.id.masai)
    TextView masai;
    @BindView(R.id.aiyinhuowen)
    TextView aiyinhuowen;
    @BindView(R.id.ajitu)
    TextView ajitu;
    @BindView(R.id.helanaerke)
    TextView helanaerke;
    @BindView(R.id.feiyeruode)
    TextView feiyeruode;
    @BindView(R.id.sengaidian)
    TextView sengaidian;
    @BindView(R.id.boerduo)
    TextView boerduo;
    @BindView(R.id.lier)
    TextView lier;
    @BindView(R.id.mengbiai)
    TextView mengbiai;
    @BindView(R.id.weitese)
    TextView weitese;
    @BindView(R.id.zewole)
    TextView zewole;
    @BindView(R.id.hailunweien)
    TextView hailunweien;
    @BindView(R.id.gaoninggeng)
    TextView gaoninggeng;
    @BindView(R.id.cong6)
    TextView cong6;
    @BindView(R.id.cong)
    TextView cong;
    @BindView(R.id.cong61)
    TextView cong61;
    @BindView(R.id.cong71)
    TextView cong71;
    @BindView(R.id.dejiadeguo)
    TextView dejiadeguo;
    @BindView(R.id.madelijingji)
    TextView madelijingji;
    @BindView(R.id.yincaoyingelan)
    TextView yincaoyingelan;
    @BindView(R.id.yijiayidali1)
    TextView yijiayidali1;
    @BindView(R.id.dejiadeguo1)
    TextView dejiadeguo1;
    @BindView(R.id.yijiayidali)
    TextView yijiayidali;
    @BindView(R.id.fajiafaguo)
    TextView fajiafaguo;
    @BindView(R.id.fajiafaguo1)
    TextView fajiafaguo1;
    @BindView(R.id.xijiaxibanya)
    TextView xijiaxibanya;
    @BindView(R.id.xijiaxibanya1)
    TextView xijiaxibanya1;
    @BindView(R.id.hecaohelan1)
    TextView hecaohelan1;
    @BindView(R.id.hecaohelan)
    TextView hecaohelan;
    @BindView(R.id.jinsheqiudui)
    TextView jinsheqiudui;
    @BindView(R.id.yinseqiudui)
    TextView yinseqiudui;
    @BindView(R.id.liwufu)
    TextView liwufu;
    //代码获取图片
     @BindView(R.id.iv_qieerxi)
     ImageView iv_qieerxi;
    @BindView(R.id.iv_manchesicheng)
    ImageView iv_manchesicheng;
    @BindView(R.id.iv_ashengna)
    ImageView iv_ashengna;
    @BindView(R.id.iv_manchesitelian)
    ImageView iv_manchesitelian;
    @BindView(R.id.iv_tuotenamu)
    ImageView iv_tuotenamu;
    @BindView(R.id.iv_liwupu)
    ImageView iv_liwupu;
    @BindView(R.id.iv_nananpudun)
    ImageView iv_nananpudun;
    @BindView(R.id.iv_siwangxi)
    ImageView iv_siwangxi;
    @BindView(R.id.iv_youwentusi)
    ImageView iv_youwentusi;
    @BindView(R.id.iv_luoma)
    ImageView iv_luoma;
    @BindView(R.id.iv_laqiao)
    ImageView iv_laqiao;
    @BindView(R.id.iv_fuluolunsa)
    ImageView iv_fuluolunsa;
    @BindView(R.id.iv_naboli)
    ImageView iv_naboli;
    @BindView(R.id.iv_renaya)
    ImageView iv_renaya;
    @BindView(R.id.iv_sangpuduoliya)
    ImageView iv_sangpuduoliya;
    @BindView(R.id.iv_guojimilan)
    ImageView iv_guojimilan;
    @BindView(R.id.iv_basailuola)
    ImageView iv_basailuola;
    @BindView(R.id.iv_huangjiamadeli)
    ImageView iv_huangjiamadeli;
    @BindView(R.id.iv_walunxiya)
    ImageView iv_walunxiya;
    @BindView(R.id.iv_saiweiliya)
    ImageView iv_saiweiliya;
    @BindView(R.id.iv_biliya)
    ImageView iv_biliya;
    @BindView(R.id.iv_bierba)
    ImageView iv_bierba;
    @BindView(R.id.iv_saiweta)
    ImageView iv_saiweta;
    @BindView(R.id.iv_baorenmu)
    ImageView iv_baorenmu;
    @BindView(R.id.iv_woerfu)
    ImageView iv_woerfu;
    @BindView(R.id.iv_mengxing)
    ImageView iv_mengxing;
    @BindView(R.id.iv_lewoku)
    ImageView iv_lewoku;
    @BindView(R.id.iv_aogesibao)
    ImageView iv_aogesibao;
    @BindView(R.id.iv_saerke)
    ImageView iv_saerke;
    @BindView(R.id.iv_boluxiya)
    ImageView iv_boluxiya;
    @BindView(R.id.iv_huofenhaomu)
    ImageView iv_huofenhaomu;
    @BindView(R.id.iv_balisenger)
    ImageView iv_balisenger;
    @BindView(R.id.iv_liang)
    ImageView iv_liang;
    @BindView(R.id.iv_molage)
    ImageView iv_molage;
    @BindView(R.id.iv_masai)
    ImageView iv_masai;
    @BindView(R.id.iv_shengaidian)
    ImageView iv_shengaidian;
    @BindView(R.id.iv_boerduo)
    ImageView iv_boerduo;
    @BindView(R.id.iv_lier)
    ImageView iv_lier;
    @BindView(R.id.iv_mengbiliai)
    ImageView iv_mengbiliai;
    @BindView(R.id.iv_aiyinghuowen)
    ImageView iv_aiyinghuowen;
    @BindView(R.id.iv_ajitu)
    ImageView iv_ajitu;
    @BindView(R.id.iv_helan)
    ImageView iv_helan;
    @BindView(R.id.iv_feiyeruo)
    ImageView iv_feiyeruo;
    @BindView(R.id.iv_weitesai)
    ImageView iv_weitesai;
    @BindView(R.id.iv_zewole)
    ImageView iv_zewole;
    @BindView(R.id.iv_hailunweien)
    ImageView iv_hailunweien;
    @BindView(R.id.iv_gaoninggeng)
    ImageView iv_gaoninggeng;
    @BindView(R.id.iv_madeli)
     ImageView iv_madeli;
    @BindView(R.id.big)
     TextView big;
    @BindView(R.id.xiao)
    TextView xiao;
    @BindView(R.id.shuang)
    TextView shuang;
    @BindView(R.id.dan)
    TextView dan;
    @BindView(R.id.xiaoshuang)
    TextView xiaoshuang;
    @BindView(R.id.xiaodan)
    TextView xiaodan;
    @BindView(R.id.dashuang)
    TextView dashuang;
    @BindView(R.id.dadan)
    TextView dadan;
    @BindView(R.id.fragment_game_hall_one)
    FrameLayout fragment_game_hall_one;
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
    int huyin;
    Long cutdown;
    Bundle bundle;
    BroadcastReceiver mItemViewListClickReceiver;
    int timeend;
    String strperiod48;
    //48balls
    Double gettotalbet48;
    Double tototalbet48=0.0;
    Double bigsmall_maxbet;
    Double bigsmall_mixbet;
    Double evenodd_maxbet;
    Double evenodd_mixbet;
    Double com_maxbet;
    Double com_mixbet;
    Double color_maxbet;
    Double color_mixbet;
    Double silvegold_maxbet;
    Double silvegold_mixbet;
    Double group_maxbet;
    Double group_mixbet;
    Double simglenum48_maxbet;
    Double simglenum48_mixbet;
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
                    Toast.makeText(LiveDDigBetActivity.this, R.string.xiazhusuccess, Toast.LENGTH_LONG).show();

                    break;
                case SHOW_BET_ERROR:
                    blockDialog.dismiss();
                    dialogshow.hide();
                    Toast.makeText(LiveDDigBetActivity.this, BetErrorMessage, Toast.LENGTH_LONG).show();
                    break;
                case SHOW_RESULTS:
                    if (gameResultsAdapter ==  null){

                        gameResultsAdapter = new PersonGameResultAdapter(gameResultsList,LiveDDigBetActivity.this);
                        lv_ball_result.setAdapter(gameResultsAdapter);
                    }else{
                        gameResultsAdapter.notifyDataSetChanged();

                    }
                    if(gameResultsList.size() == 0)
                    {
                        tv_nodata.setVisibility(View.VISIBLE);
//                        lv_ball_result.setVisibility(View.GONE);
                    }

                    break;
                default:
                    break;
            }

        }
    };

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

    private void back(){
        AppTool.activiyJump(this, LiveDigNumberActivity.class);
        finish();
    }

    @Override
    protected void leftClick() {
        back();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(this, "");
        setTitle("48" + getString(R.string.qiu));
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blockDialog = new BlockDialog(LiveDDigBetActivity.this, getString(R.string.xiazhuzhong));
        simpleDateFormat = new SimpleDateFormat("mm:ss");
        videoUrl = WebSiteUrl.LiveNumberGameVideoUrl_48;
        gameName = getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA);
        digGameBean = getApp().getLivegame_48balls();

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        rl_dialog=(RelativeLayout)findViewById(R.id.rl_dialog1);
        tv_toumingdu=(TextView)findViewById(R.id.tv_toumingdu);
        loading= (ProgressBar) findViewById(R.id.loading);
        progress_dialog_tv=(TextView)findViewById(R.id.progress_dialog_tv);
        btn_login.setOnClickListener(new View.OnClickListener() {
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
//                AppTool.activiyJump(LiveDDigBetActivity.this, PersonResultListActivity.class,bundle);

            }
        });
        simpleDateFormat = new SimpleDateFormat("mm:ss");
        InitInterfaceText();
//        InitInterfaceImage();
        clickrb();
        tv_ball_result.setText(48+""+getString(R.string.qiu)+" "+getString(R.string.jieguolist));
        getbetInfo();
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

    public void InitInterfaceText()
    {
        tv_more.setText(getString(R.string.gengduo));
        yingcao.setText(getString(R.string.yincao)
        );
        yijiayidali.setText(getString(R.string.yijiayidali));
        rb_rb_1.setText(getString(R.string.yingcaoyijia));
        madelijingji.setText(getString(R.string.madelijingji));
        yincaoyingelan.setText(getString(R.string.yingcaoyingelan));
        yijiayidali1.setText(getString(R.string.yijiadali));
        xijiaxibanya.setText(getString(R.string.txijiaxibanya));
        dejiadeguo1.setText(getString(R.string.dejiadeguo));
        fajiafaguo1.setText(getString(R.string.tfajiafaguo));
        hecaohelan1.setText(getString(R.string.thecaohelan));
        rb_rb_2.setText(getString(R.string.xiajiadejia));
        rb_rb_3.setText(getString(R.string.fajiahecao));
        rb_rb_4.setText(getString(R.string.fenzujingcai));
        rb_rb_5.setText(getString(R.string.daorxiao));
        peilv45.setText(getString(R.string.peilv45));
        peilv451.setText(getString(R.string.peilv45));
        peilv4511.setText(getString(R.string.peilv45));
        peilv45111.setText(getString(R.string.peilv455));
        //   yingcao.setText(getString(R.string.yingcaogelan));
        qieerxi.setText(getString(R.string.qieerxi));
        manchesic.setText(getString(R.string.mancesitecen));
        manchesitl.setText(getString(R.string.mancesitelian));
        asenna.setText(getString(R.string.asenna));
        tuotenamu.setText(getString(R.string.tuotenamureci));
        nananpudun.setText(getString(R.string.lianpudun));
        sitewangxi.setText(getString(R.string.siwangxi));
        youwentusi.setText(getString(R.string.youwentusi));
        luoma.setText(getString(R.string.luoma));
        laqiao.setText(getString(R.string.laqiao));
        fuoluolunsa.setText(getString(R.string.fuoluolunsa));
        naboli.setText(getString(R.string.naboli));
        renaya.setText(getString(R.string.relaya));
        saerke.setText(getString(R.string.shaerke));
        sangpuduoya.setText(getString(R.string.sangpuduoliya));
        guojimilan.setText(getString(R.string.guojimilan));
        basailuola.setText(getString(R.string.dasailuola));
        huangjiamadeli.setText(getString(R.string.huangjiamadeli));
        walunxiya.setText(getString(R.string.walunxiya));
        bairenmulihei.setText(getString(R.string.bairenmulihei));
        wuerfusibao.setText(getString(R.string.woerfusibao));
        mengxing.setText(getString(R.string.mengxing));
        lewokusen.setText(getString(R.string.lewokusen));
        saiweiliya.setText(getString(R.string.saiweiliya));
        biliyalei.setText(getString(R.string.biliyaleiyaer));
        bierbae.setText(getString(R.string.bierbae));
        saierta.setText(getString(R.string.saierta));
        aogesibao.setText(getString(R.string.aogesibao));
        boluxiya.setText(getString(R.string.boluxiyaduo));
        huofenhaimu.setText(getString(R.string.huofenhaimu));
        balisenger.setText(getString(R.string.balisengerman));
        liang.setText(getString(R.string.liang));
        molage.setText(getString(R.string.molage));
        masai.setText(getString(R.string.masai));
        aiyinhuowen.setText(getString(R.string.aiyinghuowen));
        ajitu.setText(getString(R.string.ajitu));
        helanaerke.setText(getString(R.string.helanaerke));
        feiyeruode.setText(getString(R.string.feiyeruode));
        sengaidian.setText(getString(R.string.shengaidian));
        boerduo.setText(getString(R.string.boerduo));
        lier.setText(getString(R.string.lier));
        mengbiai.setText(getString(R.string.mengbiliai));
        weitese.setText(getString(R.string.weitesai));
        zewole.setText(getString(R.string.zewole));
        hailunweien.setText(getString(R.string.hailunweien));
        gaoninggeng.setText(getString(R.string.gaoninggen));
        cong6.setText(getString(R.string.julebuxuanze));
        cong.setText(getString(R.string.julebuxuanze));
        cong61.setText(getString(R.string.julebuxuanze));
        cong71.setText(getString(R.string.julebuxuanze));
        dejiadeguo.setText(getString(R.string.dejiadeguo));
        dejiadeguo1.setText(getString(R.string.tdejiadeguo));
        yijiayidali.setText(getString(R.string.yijiayidali));
        fajiafaguo.setText(R.string.faguo1);
        fajiafaguo1.setText(getString(R.string.fajiafaguo));
        xijiaxibanya.setText(getString(R.string.xijiaxibanya));
        xijiaxibanya1.setText(getString(R.string.xijiaxibanya));
        hecaohelan1.setText(getString(R.string.hecaohelan));
        hecaohelan.setText(R.string.helan);
        jinsheqiudui.setText(getString(R.string.jinesedui));
        yinseqiudui.setText(getString(R.string.yinsedui));
        liwufu.setText(getString(R.string.liwupu));
        progress_dialog_tv.setText(R.string.kaijiangzhong);
        big.setText(getString(R.string.big));
        xiao.setText(getString(R.string.small));
        shuang.setText(getString(R.string.even_lottery));
        dan.setText(getString(R.string.odd_lottery));
        xiaoshuang.setText(getString(R.string.small_even));
        xiaodan.setText(getString(R.string.small_odd));
        dashuang.setText(getString(R.string.big_even));
        dadan.setText(getString(R.string.big_odd));
        tv_nodata.setText(R.string.nadata);
        xijiaxibanya1.setText(getString(R.string.xijia)+"\n"+"\n"+getString(R.string.xibanya));
        dejiadeguo.setText(getString(R.string.dejia)+"\n"+"\n"+getString(R.string.deguo));

        tv_big.setText("1:" + getApp().getLivegame_48balls().getBigsmall_factor());
        tv_small.setText( "1:" + getApp().getLivegame_48balls().getBigsmall_factor());
        tv_double.setText("1:" + getApp().getLivegame_48balls().getOddeven_factor());
        tv_simgle.setText( "1:" +getApp().getLivegame_48balls().getOddeven_factor());
        tv_smalldouble.setText("1:" +getApp().getLivegame_48balls().getCombination_factor());
        tv_smallsimgle.setText("1:" +getApp().getLivegame_48balls().getCombination_factor());
        tv_bigdouble.setText("1:" +getApp().getLivegame_48balls().getCombination_factor());
        tv_bigsimgle.setText("1:" +getApp().getLivegame_48balls().getCombination_factor());
        tv_golden.setText("1:" + getApp().getLivegame_48balls().getGolodsliver_factor());
        tv_silver.setText("1:" +getApp().getLivegame_48balls().getGolodsliver_factor());

    }

    public void InitInterfaceImage()
    {
        iv_qieerxi.setImageResource(R.drawable.qieer);
        iv_manchesicheng.setImageResource(R.drawable.mancesiceng);
        iv_ashengna.setImageResource(R.drawable.asengna);
        iv_manchesitelian.setImageResource(R.drawable.manchesitelian);
        iv_tuotenamu.setImageResource(R.drawable.tuotenamureci);
        iv_liwupu.setImageResource(R.drawable.liwufu);
        iv_nananpudun.setImageResource(R.drawable.nananpudun);
        iv_siwangxi.setImageResource(R.drawable.siwangxi);
        iv_youwentusi.setImageResource(R.drawable.youwentusi);
        iv_luoma.setImageResource(R.drawable.luoma);
        iv_laqiao.setImageResource(R.drawable.laqiao);
        iv_fuluolunsa.setImageResource(R.drawable.fuoluolunsa);
        iv_naboli.setImageResource(R.drawable.naboli);
        iv_renaya.setImageResource(R.drawable.renaya);
        iv_sangpuduoliya.setImageResource(R.drawable.sangpuduoliya);
        iv_guojimilan.setImageResource(R.drawable.guojimilan);
        iv_basailuola.setImageResource(R.drawable.basailuola);
        iv_huangjiamadeli.setImageResource(R.drawable.huangjiamadeli);
        iv_walunxiya.setImageResource(R.drawable.walunxiya);
        iv_saiweiliya.setImageResource(R.drawable.saierweiya);
        iv_biliya.setImageResource(R.drawable.biliyaleiyaer);
        iv_bierba.setImageResource(R.drawable.bierbaer);
        iv_saiweta.setImageResource(R.drawable.saierta);
        iv_baorenmu.setImageResource(R.drawable.bairenmulihei);
        iv_woerfu.setImageResource(R.drawable.woerfusibao);
        iv_mengxing.setImageResource(R.drawable.mengxing);
        iv_lewoku.setImageResource(R.drawable.lewokuseng);
        iv_aogesibao.setImageResource(R.drawable.aogesibao);
        iv_saerke.setImageResource(R.drawable.saerke);
        iv_boluxiya.setImageResource(R.drawable.boluxiya);
        iv_huofenhaomu.setImageResource(R.drawable.huofenhaimu);
        iv_balisenger.setImageResource(R.drawable.balishengrierman);
        iv_liang.setImageResource(R.drawable.liang);
        iv_molage.setImageResource(R.drawable.molage);
        iv_masai.setImageResource(R.drawable.masai);
        iv_shengaidian.setImageResource(R.drawable.shengaidian);
        iv_boerduo.setImageResource(R.drawable.boerduo);
        iv_lier.setImageResource(R.drawable.lier);
        iv_mengbiliai.setImageResource(R.drawable.mengbiliai);
        iv_aiyinghuowen.setImageResource(R.drawable.aiyinghuowen);
        iv_ajitu.setImageResource(R.drawable.ajitu);
        iv_helan.setImageResource(R.drawable.helanaerkemaer);
        iv_feiyeruo.setImageResource(R.drawable.feiyeruode);
        iv_weitesai.setImageResource(R.drawable.weitesai);
        iv_zewole.setImageResource(R.drawable.zewole);
        iv_hailunweien.setImageResource(R.drawable.hailunweien);
        iv_gaoninggeng.setImageResource(R.drawable.gaoninggeng);
        iv_madeli.setImageResource(R.drawable.madelijingji);
    }

    private void getbetInfo(){
        bigsmall_maxbet=getApp().getLivegame_48balls().getBigsmall_maxbet();
        bigsmall_mixbet=getApp().getLivegame_48balls().getBigsmall_minbet();
        evenodd_maxbet=getApp().getLivegame_48balls().getOddeven_maxbet();
        evenodd_mixbet=getApp().getLivegame_48balls().getOddeven_mixbet();
        com_maxbet=getApp().getLivegame_48balls().getCombination_maxbet();
        com_mixbet=getApp().getLivegame_48balls().getCombination_mixbet();
        color_maxbet=getApp().getLivegame_48balls().getColor_maxbet();
        color_mixbet=getApp().getLivegame_48balls().getColor_minbet();
        silvegold_maxbet=getApp().getLivegame_48balls().getGolodsliver_maxbet();
        silvegold_mixbet=getApp().getLivegame_48balls().getGolodsliver_mixbet();
        group_maxbet=getApp().getLivegame_48balls().getGroup_maxbet();
        group_mixbet=getApp().getLivegame_48balls().getGroup_mixbet();
        simglenum48_maxbet=getApp().getLivegame_48balls().getSimgnum48_maxbet();
        simglenum48_mixbet=getApp().getLivegame_48balls().getSimgnum48_mixbet();
        gettotalbet48=getApp().getLivegame_48balls().getBigsmall_total();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_person_home_game_hall_four;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    private void clickrb() {
        fragment_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

                if (checkId == rb_rb_1.getId()) { //英超/意甲
                    ll_yingcao.setVisibility(View.VISIBLE);
                    ll_xijia.setVisibility(View.GONE);
                    ll_fajia.setVisibility(View.GONE);
                    ll_fenzu_jingcai.setVisibility(View.GONE);
                    ll_daxiao.setVisibility(View.GONE);
                } else if (checkId == rb_rb_2.getId()) {   //西甲/德甲
                //    Log.i("toby", "点击了西甲");
                    ll_xijia.setVisibility(View.VISIBLE);
                    ll_fajia.setVisibility(View.GONE);
                    ll_fenzu_jingcai.setVisibility(View.GONE);
                    ll_daxiao.setVisibility(View.GONE);
                    ll_yingcao.setVisibility(View.GONE);
                } else if (checkId == rb_rb_3.getId()) {  //法甲/荷超
                  //  Log.i("toby", "点击了法甲");
                    ll_fajia.setVisibility(View.VISIBLE);
                    ll_fenzu_jingcai.setVisibility(View.GONE);
                    ll_daxiao.setVisibility(View.GONE);
                    ll_yingcao.setVisibility(View.GONE);
                    ll_xijia.setVisibility(View.GONE);
                } else if (checkId == rb_rb_4.getId()) { //分组竞猜
                 //   Log.i("toby", "点击了分组");
                    ll_fenzu_jingcai.setVisibility(View.VISIBLE);
                    ll_daxiao.setVisibility(View.GONE);
                    ll_yingcao.setVisibility(View.GONE);
                    ll_xijia.setVisibility(View.GONE);
                    ll_fajia.setVisibility(View.GONE);
                } else if (checkId == rb_rb_5.getId()) {  //大/小
                //    Log.i("toby", "点击了大小");
                    ll_daxiao.setVisibility(View.VISIBLE);
                    ll_yingcao.setVisibility(View.GONE);
                    ll_xijia.setVisibility(View.GONE);
                    ll_fajia.setVisibility(View.GONE);
                    ll_fenzu_jingcai.setVisibility(View.GONE);
                }
            }
        });
        //分组竞彩
        ll_england.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      jincaitag = 1;
                //     showDialog();
                ShowBetDialog(digGameBean,4,"1");
            }
        });
        ll_germany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,4,"4");
            }
        });
        ll_italy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,4,"2");
            }
        });
        ll_france.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,4,"5");
            }
        });
        ll_spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,4,"3");
            }
        });
        ll_holland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,4,"6");
            }
        });
        //大小
        ll_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"1");
            }
        });
        ll_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"2");
            }
        });
        ll_double.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"3");

            }
        });
        ll_simgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"4");
            }
        });
        ll_smalldouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"5");
            }
        });
        ll_smallsimgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"6");
            }
        });
        ll_bigdouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"7");
            }
        });
        ll_bigsimgle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"8");
            }
        });
        ll_golden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"9");
            }
        });
        ll_sliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,5,"10");
            }
        });

         //英超/意甲ll
        ll_buqiexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"01");
            }
        });
        ll_manqie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"07");
            }
        });
        ll_asenna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"13");
            }
        });
        ll_manqieste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"19");
            }
        });
        ll_tuotena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"25");
            }
        });
        ll_liwufu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"31");
            }
        });
        ll_nananpdun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"37");
            }
        });
        ll_sitewangxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"43");
            }
        });
        ll_youwentusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"02");
            }
        });
        ll_luoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"08");
            }
        });
        ll_laqiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"14");
            }
        });

        ll_fuluolun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"20");
            }
        });
        ll_naboli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"26");
            }
        });
        ll_renaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"32");
            }
        });
        ll_sangpuduoya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"38");
            }
        });
        ll_guojimilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,1,"44");
            }
        });
        //西甲/德甲ll
        ll_basaluola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"03");
            }
        });
        ll_huangjiamadeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"09");
            }
        });
        ll_madelijingji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"15");
            }
        });
        ll_walunxiya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"21");
            }
        });
        ll_saiweiliya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"27");
            }
        });
        ll_biliyayaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"33");
            }
        });
        ll_bierbae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"39");
            }
        });
        ll_saierta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"45");
            }
        });
        ll_bairenmolihei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"04");
            }
        });
        ll_woerfisibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"10");
            }
        });
        ll_menxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"16");
            }
        });
        ll_lewokusen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"22");
            }
        });
        ll_aogesibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"28");
            }
        });
        ll_saerke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"34");
            }
        });
        ll_boluyaduomengde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"40");
            }
        });
        ll_huofenhaimu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,2,"46");
            }
        });
        //法甲/荷超ll
        ll_balishengrierman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"05");
            }
        });
        ll_liang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"11");
            }
        });
        ll_molage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"17");
            }
        });
        ll_masai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"23");
            }
        });
        ll_shengaidian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"29");
            }
        });
        ll_boerduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"35");
            }
        });
        ll_lier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"41");
            }
        });
        ll_mengbiliai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"47");
            }
        });
        ll_aiyinghuowen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"06");
            }
        });
        ll_ajisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"12");
            }
        });
        ll_helanaermaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"18");
            }
        });
        ll_feiyeruode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"24");
            }
        });
        ll_weitese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"30");
            }
        });
        ll_zewole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"36");
            }
        });
        ll_hailunweien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"42");
            }
        });
        ll_haininggeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBetDialog(digGameBean,3,"48");
            }
        });
    }

    public void InitTeamNameText(TextView tv_title,TextView tv_betType,TextView tv_odds,String teamNumber)
    {
        String teamName = "";
        switch(teamNumber)
        {
            case "01":
            teamName = this.getString(R.string.qieerxi);
            break;
            case "07":
                teamName = this.getString(R.string.mancesitecen);
                break;
            case "13":
                teamName = this.getString(R.string.asenna);
                break;
            case "19":
                teamName = this.getString(R.string.mancesitelian);
                break;
            case "25":
                teamName = this.getString(R.string.tuotenamureci);
                break;
            case "31":
                teamName = this.getString(R.string.liwupu);
                break;
            case "37":
                teamName = this.getString(R.string.lianpudun);
                break;
            case "43":
                teamName = this.getString(R.string.siwangxi);
                break;
            case "02":
                teamName = this.getString(R.string.youwentusi);
                break;
            case "08":
                teamName = this.getString(R.string.luoma);
                break;
            case "14":
                teamName = this.getString(R.string.laqiao);
                break;
            case "20":
                teamName = this.getString(R.string.fuoluolunsa);
                break;
            case "26":
                teamName = this.getString(R.string.naboli);
                break;
            case "32":
                teamName = this.getString(R.string.relaya);
                break;
            case "38":
                teamName = this.getString(R.string.sangpuduoliya);
                break;
            case "44":
                teamName = this.getString(R.string.guojimilan);
                break;

            case "03":
                teamName = this.getString(R.string.dasailuola);
                break;
            case "09":
                teamName = this.getString(R.string.huangjiamadeli);
                break;
            case "15":
                teamName = this.getString(R.string.madelijingji);
                break;
            case "21":
                teamName = this.getString(R.string.walunxiya);
                break;
            case "27":
                teamName = this.getString(R.string.saiweiliya);
                break;
            case "33":
                teamName = this.getString(R.string.biliyaleiyaer);
                break;
            case "39":
                teamName = this.getString(R.string.bierbae);
                break;
            case "45":
                teamName = this.getString(R.string.saierta);
                break;
            case "04":
                teamName = this.getString(R.string.bairenmulihei);
                break;
            case "10":
                teamName = this.getString(R.string.woerfusibao);
                break;
            case "16":
                teamName = this.getString(R.string.mengxing);
                break;
            case "22":
                teamName = this.getString(R.string.lewokusen);
                break;
            case "28":
                teamName = this.getString(R.string.aogesibao);
                break;
            case "34":
                teamName = this.getString(R.string.shaerke);
                break;
            case "40":
                teamName = this.getString(R.string.boluxiyaduo);
                break;
            case "46":
                teamName = this.getString(R.string.huofenhaimu);
                break;

            case "05":
                teamName = this.getString(R.string.balisengerman);
                break;
            case "11":
                teamName = this.getString(R.string.liang);
                break;
            case "17":
                teamName = this.getString(R.string.molage);
                break;
            case "23":
                teamName = this.getString(R.string.masai);
                break;
            case "29":
                teamName = this.getString(R.string.shengaidian);
                break;
            case "35":
                teamName = this.getString(R.string.boerduo);
                break;
            case "41":
                teamName = this.getString(R.string.lier);
                break;
            case "47":
                teamName = this.getString(R.string.mengbiliai);
                break;
            case "06":
                teamName = this.getString(R.string.aiyinghuowen);
                break;
            case "12":
                teamName = this.getString(R.string.ajitu);
                break;
            case "18":
                teamName = this.getString(R.string.helanaerke);
                break;
            case "24":
                teamName = this.getString(R.string.feiyeruode);
                break;
            case "30":
                teamName = this.getString(R.string.weitesai);
                break;
            case "36":
                teamName = this.getString(R.string.zewole);
                break;
            case "42":
                teamName = this.getString(R.string.hailunweien);
                break;
            case "48":
                teamName = this.getString(R.string.gaoninggen);
                break;
        }
        tv_title.setText(R.string.number_lottery);
        tv_betType.setText(teamName);
        tv_odds.setText(getApp().getLivegame_48balls().getSimgnum48_factor());

    }

    public void InitCountryText(TextView tv_title,TextView tv_betType,TextView tv_odds,String teamNumber)
    {
        String teamName = "";
        switch(teamNumber)
        {
            case "1":
                teamName = this.getString(R.string.yingcaogelan);
                break;
            case "2":
                teamName = this.getString(R.string.yijiadali);
                break;
            case "3":
                teamName = this.getString(R.string.xiajiaxiya);
                break;
            case "4":
                teamName = this.getString(R.string.dejiadeguo);
                break;
            case "5":
                teamName = this.getString(R.string.fajiaguo);
                break;
            case "6":
                teamName = this.getString(R.string.hecaohe);
                break;
        }
        tv_title.setText(R.string.zhushu);
        tv_betType.setText(teamName);
        tv_odds.setText(getApp().getLivegame_48balls().getGroup_factor());

    }

    public void InitBigSmallText(TextView tv_title,TextView tv_betType,TextView tv_odds,String teamNumber)
    {
        String teamName = "";
        String titleName = "";
        switch(teamNumber)
        {
            case "1":
                titleName = this.getString(R.string.big_small);
                teamName = this.getString(R.string.big);
                break;
            case "2":
                titleName = this.getString(R.string.big_small);
                teamName = this.getString(R.string.small);
                break;
            case "3":
                titleName = this.getString(R.string.shuangdan);
                teamName = this.getString(R.string.even_lottery);
                break;
            case "4":
                titleName = this.getString(R.string.shuangdan);
                teamName = this.getString(R.string.odd_lottery);
                break;
            case "5":
                titleName = this.getString(R.string.zhuhe);
                teamName = this.getString(R.string.small_even);
                break;
            case "6":
                titleName = this.getString(R.string.zhuhe);
                teamName = this.getString(R.string.small_odd);
                break;
            case "7":
                titleName = this.getString(R.string.zhuhe);
                teamName = this.getString(R.string.big_even);
                break;
            case "8":
                titleName = this.getString(R.string.zhuhe);
                teamName = this.getString(R.string.big_odd);
                break;
            case "9":
                titleName = this.getString(R.string.qiuduiyanse);
                teamName = this.getString(R.string.jinesedui);
                break;
            case "10":
                titleName = this.getString(R.string.qiuduiyanse);
                teamName = this.getString(R.string.yinsedui);
                break;
        }
        tv_title.setText(titleName);
        tv_betType.setText(teamName);
        tv_odds.setText(getApp().getLivegame_48balls().getGroup_factor());

    }

    //betType 1,2,3分别表示分组英超/意甲，西甲/德甲，法甲/荷超  ，teamId,从切尔西开始到高宁根
    public void ShowBetDialog(final LiveGameBean digGameBean,final int betType,final String teamNumber)
    {
        if(digGameBean.getCount_down() <=0 || digGameBean.getCount_down() >3600000)
            return;

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(LiveDDigBetActivity.this);
        View view = LayoutInflater.from(LiveDDigBetActivity.this).inflate(R.layout.betting_dialog, null);
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
            case 1:
            case 2:
            case 3:
                InitTeamNameText(tv_title,tv_betType,tv_odds,teamNumber);
                break;
            case 4:
                InitCountryText(tv_title,tv_betType,tv_odds,teamNumber);
                break;
            case 5:
                InitBigSmallText(tv_title,tv_betType,tv_odds,teamNumber);
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
                                              NumberGameBet(tv_bet_num.getText().toString().trim(), digGameBean,betType,teamNumber);

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

    public void NumberGameBet(String betMoney,LiveGameBean digGameBean,int betType,String gameNumber)
    {
        try{
            if (betMoney == null || betMoney.length() == 0) {
                Toast.makeText(LiveDDigBetActivity.this, R.string.qingchashumu, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(LiveDDigBetActivity.this, R.string.yuebuzhu,Toast.LENGTH_LONG).show();
                    return ;
                }
                boolean bBet = true;
                switch (betType)
                {
                    case 1:
                    case 2:
                    case 3:

                        bBet = LimitBet(d_betMoney,digGameBean.getSimgnum48_mixbet(),digGameBean.getSimgnum48_maxbet(),digGameBean.getSimgnum48_total(),digGameBean.getSimgnum48_bet_total());
                        break;
                    case 4:

                        bBet = LimitBet(d_betMoney,digGameBean.getGroup_mixbet(),digGameBean.getGroup_maxbet(),digGameBean.getGroup_total(),digGameBean.getGroup_bet_total());
                        break;
                    case 5:
                        switch(gameNumber)
                        {
                            case "1":
                            case "2":
                                bBet = LimitBet(d_betMoney,digGameBean.getBigsmall_minbet(),digGameBean.getBigsmall_maxbet(),digGameBean.getBigsmall_total(),digGameBean.getBigsmall_bet_total());
                                break;
                            case "3":
                            case "4":
                                bBet = LimitBet(d_betMoney,digGameBean.getOddeven_mixbet(),digGameBean.getOddeven_maxbet(),digGameBean.getOddeven_total(),digGameBean.getOddeven_bet_total());
                                break;
                            case "5":
                            case "6":
                            case "7":
                            case "8":
                                bBet = LimitBet(d_betMoney,digGameBean.getCombination_mixbet(),digGameBean.getCombination_maxbet(),digGameBean.getCombination_total(),digGameBean.getCombination_bet_total());
                                break;
                            case "9":
                            case "10":
                                bBet = LimitBet(d_betMoney,digGameBean.getGolodsliver_mixbet(),digGameBean.getGolodsliver_maxbet(),digGameBean.getGolodsliver_total(),digGameBean.getGolodsliver_bet_total());
                                break;
                        }

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
                    betNumberGame.setDigGameBean(digGameBean);
                    betNumberGame.setGameNumber(gameNumber);
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
    protected void onResume() {
        updateAllData = true;
        StartUpdateGameStatus();
        super.onResume();

    }

    @Override
    protected void onDestroy()  {
        super.onDestroy();
        if (view!=null){
            view.stopPlayback();
        }
        bActivityExist = false;
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
                    if(bGetResults || (!gameNumber.equals(digGameBean.getGame_perid()) && tv_time.getText().equals("00:00")))
                    {
                        Log.i("LanjianTest","=============GetResults");
                        gameNumber = digGameBean.getGame_perid();
                        getResultsData();//拿结果

                    }
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public class BetNumberGame implements Runnable {
        private int betType;
        private String betMoney;
        private LiveGameBean digGameBean;
        private String gameNumber;

        public void setGameNumber(String gameNumber) {
            this.gameNumber = gameNumber;
        }

        public void setBetMoney(String betMoney) {
            this.betMoney = betMoney;
        }

        public void setDigGameBean(LiveGameBean digGameBean) {
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
                Log.i("LanjianTestBet","betType="+betType+" betMoney="+betMoney+" betNumber="+gameNumber);
                String params = "web_id="+ WebSiteUrl.WebId+"&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()+"&from=App";
                switch(betType)
                {
                    case 1:
                    case 2:
                    case 3:
                        playtype = "number";
                        bettype = gameNumber;
                        break;
                    case 4:
                        playtype = "group";
                        bettype = gameNumber;
                        break;
                    case 5:
                        switch (gameNumber)
                        {
                            case "1":
                                playtype = "bigsmall";
                                bettype = "1";
                                break;
                            case "2":
                                playtype = "bigsmall";
                                bettype = "0";
                                break;
                            case "3":
                                playtype = "evenodd";
                                bettype = "0";
                                break;
                            case "4":
                                playtype = "evenodd";
                                bettype = "1";
                                break;
                            case "5":
                                playtype = "combination";
                                bettype = "0";
                                break;
                            case "6":
                                playtype = "combination";
                                bettype = "1";
                                break;
                            case "7":
                                playtype = "combination";
                                bettype = "2";
                                break;
                            case "8":
                                playtype = "combination";
                                bettype = "3";
                                break;
                            case "9":
                                playtype = "color";
                                bettype = "1";
                                break;
                            case "10":
                                playtype = "color";
                                bettype = "0";
                                break;
                        }
                        break;

                    default:

                        break;
                }
                params += "&get_bet="+playtype+"#"+bettype;
                String postBetMoney = String.format("%.3f",Double.parseDouble(betMoney));
                params += "#" +postBetMoney;

                betUrl = WebSiteUrl.LiveGameBet48;

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
                    switch (betType)
                    {
                        case 1:
                        case 2:
                        case 3:
                            digGameBean.setSimgnum48_bet_total(digGameBean.getSimgnum48_bet_total() + Double.parseDouble(betMoney));
                            break;
                        case 4:

                            digGameBean.setGroup_bet_total(digGameBean.getGroup_bet_total() + Double.parseDouble(betMoney));
                            break;
                        case 5:
                            switch(gameNumber)
                            {
                                case "1":
                                case "2":
                                    digGameBean.setBigsmall_bet_total(digGameBean.getBigsmall_bet_total() + Double.parseDouble(betMoney));
                                    break;
                                case "3":
                                case "4":
                                    digGameBean.setOddeven_bet_total(digGameBean.getOddeven_bet_total() + Double.parseDouble(betMoney));
                                    break;
                                case "5":
                                case "6":
                                case "7":
                                case "8":
                                    digGameBean.setCombination_bet_total(digGameBean.getCombination_bet_total() + Double.parseDouble(betMoney));
                                    break;
                                case "9":
                                case "10":
                                    digGameBean.setGolodsliver_bet_total(digGameBean.getGolodsliver_bet_total() + Double.parseDouble(betMoney));
                                    break;
                            }

                            break;

                    }
                    handlerTimer.sendEmptyMessage(SHOW_BET_SECCESS);
                }else{
                    switch (orgData.getMsg())
                    {
                        case "-1":
                            BetErrorMessage = LiveDDigBetActivity.this.getString(R.string.xiazhucuowu);
                            break;
                        case "-2":
                            BetErrorMessage = LiveDDigBetActivity.this.getString(R.string.xiazhuzuixiao);
                            break;
                        case "-3":
                            BetErrorMessage = LiveDDigBetActivity.this.getString(R.string.xiazhuzuida);
                            break;
                        case "-4":
                            BetErrorMessage = LiveDDigBetActivity.this.getString(R.string.xiazhucaoguo);
                            break;
                        case "-5":
                            BetErrorMessage = LiveDDigBetActivity.this.getString(R.string.betshibai);
                            break;
                        case "-6":
                            BetErrorMessage = LiveDDigBetActivity.this.getString(R.string.daojishiend);
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