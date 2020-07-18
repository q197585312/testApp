package gaming178.com.casinogame.Activity;

import android.animation.Animator;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.autolayout.config.UseLandscape;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.autolayout.utils.L;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Activity.entity.BetTypeRow;
import gaming178.com.casinogame.Activity.entity.ResultHintBean;
import gaming178.com.casinogame.Bean.BetDetail;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.Bean.LiveInfoBean;
import gaming178.com.casinogame.Bean.Roulette;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.Util.ChipShowHelper;
import gaming178.com.casinogame.Util.CountDownView;
import gaming178.com.casinogame.Util.FrontMuzicService;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.VideoPlayer;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.ToastUtils;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.myview.miscwidgets.interpolator.BounceInterpolator;
import gaming178.com.mylibrary.myview.miscwidgets.interpolator.EasingType;
import gaming178.com.mylibrary.myview.miscwidgets.widget.Panel;
import gaming178.com.mylibrary.popupwindow.AbsListPopupWindow;


/**
 * Created by Administrator on 2016/4/11.
 */
public class RouletteActivity extends BaseActivity implements UseLandscape {

    Map<Boolean, Integer> selectedMap = new HashMap<>();
    Map<FrameLayout, ChipShowHelper> ChipMap = new HashMap<>();
    List<ImageView> imgList = new ArrayList<>();
    private GridLayout bigGradLayout;
    private GoogleApiClient client;
    private View ll_center_small;
    private View ll_center_orphelins;
    private View ll_center_big;
    private View ll_zero;
    private TextView tv_game_number;


    @OnClick(R2.id.iv_baccarat_change_table)
    public void clickTable(View v) {
        showChangeTable(v);
    }

    @BindView(R2.id.tv_service_time)
    TextView serviceTime;
    @BindView(R2.id.fl_vedio_location_parent)
    FrameLayout fl_vedio_location_parent;
    @BindView(R2.id.fl_roulette_result)
    FrameLayout fl_roulette_result;
    @BindView(R2.id.fl_surface_parent)
    FrameLayout fl_surface_parent;
    @BindView(R2.id.fl_vedio_parent)
    FrameLayout fl_vedio_parent;
    @BindView(R2.id.handle1)
    View handle1;
    @BindView(R2.id.rc_hot)
    RecyclerView rc_hot;
    @BindView(R2.id.rc_ice)
    RecyclerView rc_ice;
    RecyclerView roulette_content_fgv;
    @BindView(R2.id.tv_menu)
    TextView tvMenu;
    @BindView(R2.id.ll_chip)
    LinearLayout ll_chip;
    @BindView(R2.id.bottonPanel1)
    FrameLayout bottonPanel1;
    @BindView(R2.id.iv_roulette_road_handle)
    View iv_roulette_road_handle;
    @BindView(R2.id.btn_table_limit_red_title)
    TextView btnTableLimitRedTitle;
    //   @BindView(R2.id.btn_table_pool_title)
    private TextView btn_game_number;

    @BindView(R2.id.tv_table_bet_replay)
    TextView tvTableBetReplay;
    @BindView(R2.id.tv_table_bet_sure)
    TextView tvTableBetSure;

    AdapterView lvBaccaratChips;
    @BindView(R2.id.lv_limit)
    ListView lvLimit;
    @BindView(R2.id.leftPanel1)
    Panel leftPanel1;
    @BindView(R2.id.fl_roulette_board_bg)
    FrameLayout fl_roulette_board_bg;
    @BindView(R2.id.fl_roulette_board_bg_new)
    FrameLayout fl_roulette_board_bg_new;


    @BindView(R2.id.fl_baccarat_parent)
    FrameLayout flBaccaratParent;

    @BindView(R2.id.iv_number03)
    ImageView ivNumber03;
    @BindView(R2.id.iv_number03_new)
    ImageView ivNumber03New;
    @BindView(R2.id.iv_number06)
    ImageView ivNumber06;
    @BindView(R2.id.iv_number06_new)
    ImageView ivNumber06New;
    @BindView(R2.id.iv_number09)
    ImageView ivNumber09;
    @BindView(R2.id.iv_number09_new)
    ImageView ivNumber09New;
    @BindView(R2.id.iv_number12)
    ImageView ivNumber12;
    @BindView(R2.id.iv_number12_new)
    ImageView ivNumber12New;
    @BindView(R2.id.iv_number15)
    ImageView ivNumber15;
    @BindView(R2.id.iv_number15_new)
    ImageView ivNumber15New;
    @BindView(R2.id.iv_number18)
    ImageView ivNumber18;
    @BindView(R2.id.iv_number18_new)
    ImageView ivNumber18New;
    @BindView(R2.id.iv_number21)
    ImageView ivNumber21;
    @BindView(R2.id.iv_number21_new)
    ImageView ivNumber21New;
    @BindView(R2.id.iv_number24)
    ImageView ivNumber24;
    @BindView(R2.id.iv_number24_new)
    ImageView ivNumber24New;
    @BindView(R2.id.iv_number27)
    ImageView ivNumber27;
    @BindView(R2.id.iv_number27_new)
    ImageView ivNumber27New;
    @BindView(R2.id.iv_number30)
    ImageView ivNumber30;
    @BindView(R2.id.iv_number30_new)
    ImageView ivNumber30New;
    @BindView(R2.id.iv_number33)
    ImageView ivNumber33;
    @BindView(R2.id.iv_number33_new)
    ImageView ivNumber33New;
    @BindView(R2.id.iv_1row1x2)
    ImageView iv1row1x2;
    @BindView(R2.id.iv_2row1x2)
    ImageView iv2row1x2;
    @BindView(R2.id.iv_number36)
    ImageView ivNumber36;
    @BindView(R2.id.iv_number36_new)
    ImageView ivNumber36New;
    @BindView(R2.id.fl_split0003)
    FrameLayout flSplit0003;
    @BindView(R2.id.fl_number03)
    FrameLayout flNumber03;
    @BindView(R2.id.fl_number03_new)
    FrameLayout flNumber03New;
    @BindView(R2.id.fl_split0306)
    FrameLayout flSplit0306;
    @BindView(R2.id.fl_number06)
    FrameLayout flNumber06;
    @BindView(R2.id.fl_number06_new)
    FrameLayout flNumber06New;
    @BindView(R2.id.fl_split0609)
    FrameLayout flSplit0609;
    @BindView(R2.id.fl_number09)
    FrameLayout flNumber09;
    @BindView(R2.id.fl_number09_new)
    FrameLayout flNumber09New;
    @BindView(R2.id.fl_split0912)
    FrameLayout flSplit0912;
    @BindView(R2.id.fl_number12)
    FrameLayout flNumber12;
    @BindView(R2.id.fl_number12_new)
    FrameLayout flNumber12New;
    @BindView(R2.id.fl_split1215)
    FrameLayout flSplit1215;
    @BindView(R2.id.fl_number15)
    FrameLayout flNumber15;
    @BindView(R2.id.fl_number15_new)
    FrameLayout flNumber15New;
    @BindView(R2.id.fl_split1518)
    FrameLayout flSplit1518;
    @BindView(R2.id.fl_number18)
    FrameLayout flNumber18;
    @BindView(R2.id.fl_number18_new)
    FrameLayout flNumber18New;
    @BindView(R2.id.fl_split1821)
    FrameLayout flSplit1821;
    @BindView(R2.id.fl_number21)
    FrameLayout flNumber21;
    @BindView(R2.id.fl_number21_new)
    FrameLayout flNumber21New;
    @BindView(R2.id.fl_split2124)
    FrameLayout flSplit2124;
    @BindView(R2.id.fl_number24)
    FrameLayout flNumber24;
    @BindView(R2.id.fl_number24_new)
    FrameLayout flNumber24New;
    @BindView(R2.id.fl_split2427)
    FrameLayout flSplit2427;
    @BindView(R2.id.fl_number27)
    FrameLayout flNumber27;
    @BindView(R2.id.fl_number27_new)
    FrameLayout flNumber27New;
    @BindView(R2.id.fl_split2730)
    FrameLayout flSplit2730;
    @BindView(R2.id.fl_number30)
    FrameLayout flNumber30;
    @BindView(R2.id.fl_number30_new)
    FrameLayout flNumber30New;
    @BindView(R2.id.fl_split3033)
    FrameLayout flSplit3033;
    @BindView(R2.id.fl_number33)
    FrameLayout flNumber33;
    @BindView(R2.id.fl_number33_new)
    FrameLayout flNumber33New;
    @BindView(R2.id.fl_split3336)
    FrameLayout flSplit3336;
    @BindView(R2.id.fl_number36)
    FrameLayout flNumber36;
    @BindView(R2.id.fl_number36_new)
    FrameLayout flNumber36New;
    @BindView(R2.id.fl_1row1x2)
    FrameLayout fl1row1x2;
    @BindView(R2.id.fl_street000203)
    FrameLayout flStreet000203;
    @BindView(R2.id.fl_split0203)
    FrameLayout flSplit0203;
    @BindView(R2.id.fl_corner02030506)
    FrameLayout flCorner02030506;
    @BindView(R2.id.fl_split0506)
    FrameLayout flSplit0506;
    @BindView(R2.id.fl_corner05060809)
    FrameLayout flCorner05060809;
    @BindView(R2.id.fl_split0809)
    FrameLayout flSplit0809;
    @BindView(R2.id.fl_corner08091112)
    FrameLayout flCorner08091112;
    @BindView(R2.id.fl_split1112)
    FrameLayout flSplit1112;
    @BindView(R2.id.fl_corner11121415)
    FrameLayout flCorner11121415;
    @BindView(R2.id.fl_split1415)
    FrameLayout flSplit1415;
    @BindView(R2.id.fl_corner14151718)
    FrameLayout flCorner14151718;
    @BindView(R2.id.fl_split1718)
    FrameLayout flSplit1718;
    @BindView(R2.id.fl_corner17182021)
    FrameLayout flCorner17182021;
    @BindView(R2.id.fl_split2021)
    FrameLayout flSplit2021;
    @BindView(R2.id.fl_corner20212324)
    FrameLayout flCorner20212324;
    @BindView(R2.id.fl_split2324)
    FrameLayout flSplit2324;
    @BindView(R2.id.fl_split2627)
    FrameLayout flSplit2627;
    @BindView(R2.id.fl_corner26272930)
    FrameLayout flCorner26272930;
    @BindView(R2.id.fl_split2930)
    FrameLayout flSplit2930;
    @BindView(R2.id.fl_corner29303233)
    FrameLayout flCorner29303233;
    @BindView(R2.id.fl_split3233)
    FrameLayout flSplit3233;
    @BindView(R2.id.fl_corner32333536)
    FrameLayout flCorner32333536;
    @BindView(R2.id.fl_split3536)
    FrameLayout flSplit3536;
    @BindView(R2.id.iv_number02)
    ImageView ivNumber02;
    @BindView(R2.id.iv_number02_new)
    ImageView ivNumber02New;
    @BindView(R2.id.iv_number05)
    ImageView ivNumber05;
    @BindView(R2.id.iv_number05_new)
    ImageView ivNumber05New;
    @BindView(R2.id.iv_number08)
    ImageView ivNumber08;
    @BindView(R2.id.iv_number08_new)
    ImageView ivNumber08New;
    @BindView(R2.id.iv_number11)
    ImageView ivNumber11;
    @BindView(R2.id.iv_number11_new)
    ImageView ivNumber11New;
    @BindView(R2.id.fl_number00)
    FrameLayout flNumber00;
    @BindView(R2.id.fl_number00_new)
    FrameLayout flNumber00New;
    @BindView(R2.id.iv_number14)
    ImageView ivNumber14;
    @BindView(R2.id.iv_number14_new)
    ImageView ivNumber14New;
    @BindView(R2.id.iv_number17)
    ImageView ivNumber17;
    @BindView(R2.id.iv_number17_new)
    ImageView ivNumber17New;
    @BindView(R2.id.iv_number20)
    ImageView ivNumber20;
    @BindView(R2.id.iv_number20_new)
    ImageView ivNumber20New;
    @BindView(R2.id.iv_number23)
    ImageView ivNumber23;
    @BindView(R2.id.iv_number23_new)
    ImageView ivNumber23New;
    @BindView(R2.id.iv_number26)
    ImageView ivNumber26;
    @BindView(R2.id.iv_number26_new)
    ImageView ivNumber26New;
    @BindView(R2.id.iv_number29)
    ImageView ivNumber29;
    @BindView(R2.id.iv_number29_new)
    ImageView ivNumber29New;
    @BindView(R2.id.iv_number32)
    ImageView ivNumber32;
    @BindView(R2.id.iv_number32_new)
    ImageView ivNumber32New;
    @BindView(R2.id.iv_number35)
    ImageView ivNumber35;
    @BindView(R2.id.iv_number35_new)
    ImageView ivNumber35New;

    @BindView(R2.id.fl_split0002)
    FrameLayout flSplit0002;
    @BindView(R2.id.fl_number02)
    FrameLayout flNumber02;
    @BindView(R2.id.fl_number02_new)
    FrameLayout flNumber02New;
    @BindView(R2.id.fl_split0205)
    FrameLayout flSplit0205;
    @BindView(R2.id.fl_number05)
    FrameLayout flNumber05;
    @BindView(R2.id.fl_number05_new)
    FrameLayout flNumber05New;
    @BindView(R2.id.fl_split0508)
    FrameLayout flSplit0508;
    @BindView(R2.id.fl_number08)
    FrameLayout flNumber08;
    @BindView(R2.id.fl_number08_new)
    FrameLayout flNumber08New;
    @BindView(R2.id.fl_split0811)
    FrameLayout flSplit0811;
    @BindView(R2.id.fl_number11)
    FrameLayout flNumber11;
    @BindView(R2.id.fl_number11_new)
    FrameLayout flNumber11New;
    @BindView(R2.id.fl_split1114)
    FrameLayout flSplit1114;
    @BindView(R2.id.fl_number14)
    FrameLayout flNumber14;
    @BindView(R2.id.fl_number14_new)
    FrameLayout flNumber14New;
    @BindView(R2.id.fl_split1417)
    FrameLayout flSplit1417;
    @BindView(R2.id.fl_number17)
    FrameLayout flNumber17;
    @BindView(R2.id.fl_number17_new)
    FrameLayout flNumber17New;
    @BindView(R2.id.fl_split1720)
    FrameLayout flSplit1720;
    @BindView(R2.id.fl_number20)
    FrameLayout flNumber20;
    @BindView(R2.id.fl_number20_new)
    FrameLayout flNumber20New;
    @BindView(R2.id.fl_split2023)
    FrameLayout flSplit2023;
    @BindView(R2.id.fl_number23)
    FrameLayout flNumber23;
    @BindView(R2.id.fl_number23_new)
    FrameLayout flNumber23New;
    @BindView(R2.id.fl_split2326)
    FrameLayout flSplit2326;
    @BindView(R2.id.fl_number26)
    FrameLayout flNumber26;
    @BindView(R2.id.fl_number26_new)
    FrameLayout flNumber26New;
    @BindView(R2.id.fl_split2629)
    FrameLayout flSplit2629;
    @BindView(R2.id.fl_number29)
    FrameLayout flNumber29;
    @BindView(R2.id.fl_number29_new)
    FrameLayout flNumber29New;
    @BindView(R2.id.fl_split2932)
    FrameLayout flSplit2932;
    @BindView(R2.id.fl_number32)
    FrameLayout flNumber32;
    @BindView(R2.id.fl_number32_new)
    FrameLayout flNumber32New;
    @BindView(R2.id.fl_split3235)
    FrameLayout flSplit3235;
    @BindView(R2.id.fl_number35)
    FrameLayout flNumber35;
    @BindView(R2.id.fl_number35_new)
    FrameLayout flNumber35New;
    @BindView(R2.id.fl_2row1x2)
    FrameLayout fl2row1x2;
    @BindView(R2.id.fl_street000102)
    FrameLayout flStreet000102;
    @BindView(R2.id.fl_split0102)
    FrameLayout flSplit0102;
    @BindView(R2.id.fl_corner01020405)
    FrameLayout flCorner01020405;
    @BindView(R2.id.fl_split0405)
    FrameLayout flSplit0405;
    @BindView(R2.id.fl_corner04050708)
    FrameLayout flCorner04050708;
    @BindView(R2.id.fl_split0708)
    FrameLayout flSplit0708;
    @BindView(R2.id.fl_corner07081011)
    FrameLayout flCorner07081011;
    @BindView(R2.id.fl_split1011)
    FrameLayout flSplit1011;
    @BindView(R2.id.fl_corner10111314)
    FrameLayout flCorner10111314;
    @BindView(R2.id.fl_split1314)
    FrameLayout flSplit1314;
    @BindView(R2.id.fl_corner13141617)
    FrameLayout flCorner13141617;
    @BindView(R2.id.fl_split1617)
    FrameLayout flSplit1617;
    @BindView(R2.id.fl_corner16171920)
    FrameLayout flCorner16171920;
    @BindView(R2.id.fl_split1920)
    FrameLayout flSplit1920;
    @BindView(R2.id.fl_corner19202223)
    FrameLayout flCorner19202223;
    @BindView(R2.id.fl_split2223)
    FrameLayout flSplit2223;
    @BindView(R2.id.fl_corner22232526)
    FrameLayout flCorner22232526;
    @BindView(R2.id.fl_split2526)
    FrameLayout flSplit2526;
    @BindView(R2.id.fl_corner25262829)
    FrameLayout flCorner25262829;
    @BindView(R2.id.fl_split2829)
    FrameLayout flSplit2829;
    @BindView(R2.id.fl_corner28293132)
    FrameLayout flCorner28293132;
    @BindView(R2.id.fl_split3132)
    FrameLayout flSplit3132;
    @BindView(R2.id.fl_corner31323435)
    FrameLayout flCorner31323435;
    @BindView(R2.id.fl_split3435)
    FrameLayout flSplit3435;
    @BindView(R2.id.iv_number01)
    ImageView ivNumber01;
    @BindView(R2.id.iv_number01_new)
    ImageView ivNumber01New;
    @BindView(R2.id.iv_number04)
    ImageView ivNumber04;
    @BindView(R2.id.iv_number04_new)
    ImageView ivNumber04New;
    @BindView(R2.id.iv_number07)
    ImageView ivNumber07;
    @BindView(R2.id.iv_number07_new)
    ImageView ivNumber07New;
    @BindView(R2.id.iv_number10)
    ImageView ivNumber10;
    @BindView(R2.id.iv_number10_new)
    ImageView ivNumber10New;
    @BindView(R2.id.iv_number13)
    ImageView ivNumber13;
    @BindView(R2.id.iv_number13_new)
    ImageView ivNumber13New;
    @BindView(R2.id.iv_number16)
    ImageView ivNumber16;
    @BindView(R2.id.iv_number16_new)
    ImageView ivNumber16New;
    @BindView(R2.id.iv_number19)
    ImageView ivNumber19;
    @BindView(R2.id.iv_number19_new)
    ImageView ivNumber19New;
    @BindView(R2.id.iv_number22)
    ImageView ivNumber22;
    @BindView(R2.id.iv_number22_new)
    ImageView ivNumber22New;
    @BindView(R2.id.iv_number25)
    ImageView ivNumber25;
    @BindView(R2.id.iv_number25_new)
    ImageView ivNumber25New;
    @BindView(R2.id.iv_number28)
    ImageView ivNumber28;
    @BindView(R2.id.iv_number28_new)
    ImageView ivNumber28New;
    @BindView(R2.id.iv_number31)
    ImageView ivNumber31;
    @BindView(R2.id.iv_number31_new)
    ImageView ivNumber31New;
    @BindView(R2.id.iv_number34)
    ImageView ivNumber34;
    @BindView(R2.id.iv_number34_new)
    ImageView ivNumber34New;
    @BindView(R2.id.iv_3row1x2)
    ImageView iv3row1x2;
    @BindView(R2.id.fl_split0001)
    FrameLayout flSplit0001;
    @BindView(R2.id.fl_number01)
    FrameLayout flNumber01;
    @BindView(R2.id.fl_number01_new)
    FrameLayout flNumber01New;
    @BindView(R2.id.fl_split0104)
    FrameLayout flSplit0104;
    @BindView(R2.id.fl_number04)
    FrameLayout flNumber04;
    @BindView(R2.id.fl_number04_new)
    FrameLayout flNumber04New;
    @BindView(R2.id.fl_split0407)
    FrameLayout flSplit0407;
    @BindView(R2.id.fl_number07)
    FrameLayout flNumber07;
    @BindView(R2.id.fl_number07_new)
    FrameLayout flNumber07New;
    @BindView(R2.id.fl_split0710)
    FrameLayout flSplit0710;
    @BindView(R2.id.fl_number10)
    FrameLayout flNumber10;
    @BindView(R2.id.fl_number10_new)
    FrameLayout flNumber10New;
    @BindView(R2.id.fl_split1013)
    FrameLayout flSplit1013;
    @BindView(R2.id.fl_number13)
    FrameLayout flNumber13;
    @BindView(R2.id.fl_number13_new)
    FrameLayout flNumber13New;
    @BindView(R2.id.fl_split1316)
    FrameLayout flSplit1316;
    @BindView(R2.id.fl_number16)
    FrameLayout flNumber16;
    @BindView(R2.id.fl_number16_new)
    FrameLayout flNumber16New;
    @BindView(R2.id.fl_split1619)
    FrameLayout flSplit1619;
    @BindView(R2.id.fl_number19)
    FrameLayout flNumber19;
    @BindView(R2.id.fl_number19_new)
    FrameLayout flNumber19New;
    @BindView(R2.id.fl_split1922)
    FrameLayout flSplit1922;
    @BindView(R2.id.fl_number22)
    FrameLayout flNumber22;
    @BindView(R2.id.fl_number22_new)
    FrameLayout flNumber22New;
    @BindView(R2.id.fl_split2225)
    FrameLayout flSplit2225;
    @BindView(R2.id.fl_number25)
    FrameLayout flNumber25;
    @BindView(R2.id.fl_number25_new)
    FrameLayout flNumber25New;
    @BindView(R2.id.fl_split2528)
    FrameLayout flSplit2528;
    @BindView(R2.id.fl_number28)
    FrameLayout flNumber28;
    @BindView(R2.id.fl_number28_new)
    FrameLayout flNumber28New;
    @BindView(R2.id.fl_split2831)
    FrameLayout flSplit2831;
    @BindView(R2.id.fl_number31)
    FrameLayout flNumber31;
    @BindView(R2.id.fl_number31_new)
    FrameLayout flNumber31New;
    @BindView(R2.id.fl_split3134)
    FrameLayout flSplit3134;
    @BindView(R2.id.fl_number34)
    FrameLayout flNumber34;
    @BindView(R2.id.fl_number34_new)
    FrameLayout flNumber34New;
    @BindView(R2.id.fl_3row1x2)
    FrameLayout fl3row1x2;
    @BindView(R2.id.fl_corner00010203)
    FrameLayout flCorner00010203;
    @BindView(R2.id.fl_street010203)
    FrameLayout flStreet010203;
    @BindView(R2.id.fl_line010203040506)
    FrameLayout flLine010203040506;
    @BindView(R2.id.fl_street040506)
    FrameLayout flStreet040506;
    @BindView(R2.id.fl_line040506070809)
    FrameLayout flLine040506070809;
    @BindView(R2.id.fl_street070809)
    FrameLayout flStreet070809;
    @BindView(R2.id.fl_line070809101112)
    FrameLayout flLine070809101112;
    @BindView(R2.id.fl_street101112)
    FrameLayout flStreet101112;
    @BindView(R2.id.fl_line101112131415)
    FrameLayout flLine101112131415;
    @BindView(R2.id.fl_street131415)
    FrameLayout flStreet131415;
    @BindView(R2.id.fl_line131415161718)
    FrameLayout flLine131415161718;
    @BindView(R2.id.fl_street161718)
    FrameLayout flStreet161718;
    @BindView(R2.id.fl_line161718192021)
    FrameLayout flLine161718192021;
    @BindView(R2.id.fl_street192021)
    FrameLayout flStreet192021;
    @BindView(R2.id.fl_line192021222324)
    FrameLayout flLine192021222324;
    @BindView(R2.id.fl_street222324)
    FrameLayout flStreet222324;
    @BindView(R2.id.fl_line222324252627)
    FrameLayout flLine222324252627;
    @BindView(R2.id.fl_street252627)
    FrameLayout flStreet252627;
    @BindView(R2.id.fl_line252627282930)
    FrameLayout flLine252627282930;
    @BindView(R2.id.fl_street282930)
    FrameLayout flStreet282930;
    @BindView(R2.id.fl_line282930313233)
    FrameLayout flLine282930313233;
    @BindView(R2.id.fl_street313233)
    FrameLayout flStreet313233;
    @BindView(R2.id.fl_line313233343536)
    FrameLayout flLine313233343536;
    @BindView(R2.id.fl_street343536)
    FrameLayout flStreet343536;
    @BindView(R2.id.fl_dozen1)
    FrameLayout flDozen1;
    @BindView(R2.id.fl_dozen2)
    FrameLayout flDozen2;
    @BindView(R2.id.fl_dozen3)
    FrameLayout flDozen3;
    @BindView(R2.id.fl_low)
    FrameLayout flLow;
    @BindView(R2.id.fl_even)
    FrameLayout flEven;
    @BindView(R2.id.fl_black)
    FrameLayout flBlack;
    @BindView(R2.id.fl_red)
    FrameLayout flRed;
    @BindView(R2.id.fl_odd)
    FrameLayout flOdd;
    @BindView(R2.id.fl_high)
    FrameLayout flHigh;

    @BindView(R2.id.tv_table_timer)
    TextView tv_table_timer;
    @BindView(R2.id.countdown_view)
    CountDownView countdown_view;
    @BindView(R2.id.tv_table_bet_cancel)
    TextView tvTableBetCancel;


    @BindView(R2.id.text_red)
    TextView textRed;
    @BindView(R2.id.text_black)
    TextView textBlack;
    @BindView(R2.id.text_zero)
    TextView textZero;
    @BindView(R2.id.text_even)
    TextView textEven;
    @BindView(R2.id.text_odd)
    TextView textOdd;


    @BindView(R2.id.btn_split0003)
    Button btnSplit0003;
    @BindView(R2.id.btn_split0306)
    Button btnSplit0306;
    @BindView(R2.id.btn_split0609)
    Button btnSplit0609;
    @BindView(R2.id.btn_split0912)
    Button btnSplit0912;
    @BindView(R2.id.btn_split1215)
    Button btnSplit1215;
    @BindView(R2.id.btn_split1518)
    Button btnSplit1518;
    @BindView(R2.id.btn_split1821)
    Button btnSplit1821;
    @BindView(R2.id.btn_split2124)
    Button btnSplit2124;
    @BindView(R2.id.btn_split2427)
    Button btnSplit2427;
    @BindView(R2.id.btn_split2730)
    Button btnSplit2730;
    @BindView(R2.id.btn_split3033)
    Button btnSplit3033;
    @BindView(R2.id.btn_split3336)
    Button btnSplit3336;
    //    @BindView(R2.id.btn_street000203)
//    Button btnSplit000203;
    @BindView(R2.id.btn_split0203)
    Button btnSplit0203;
    @BindView(R2.id.btn_corner02030506)
    Button btnCorner02030506;
    @BindView(R2.id.btn_split0506)
    Button btnSplit0506;
    @BindView(R2.id.btn_corner05060809)
    Button btnCorner05060809;
    @BindView(R2.id.btn_split0809)
    Button btnSplit0809;
    @BindView(R2.id.btn_corner08091112)
    Button btnCorner08091112;
    @BindView(R2.id.btn_split1112)
    Button btnSplit1112;
    @BindView(R2.id.btn_corner11121415)
    Button btnCorner11121415;
    @BindView(R2.id.btn_split1415)
    Button btnSplit1415;
    @BindView(R2.id.btn_corner14151718)
    Button btnCorner14151718;
    @BindView(R2.id.btn_split1718)
    Button btnSplit1718;
    @BindView(R2.id.btn_corner17182021)
    Button btnCorner17182021;
    @BindView(R2.id.btn_split2021)
    Button btnSplit2021;
    @BindView(R2.id.btn_corner20212324)
    Button btnCorner20212324;
    @BindView(R2.id.btn_split2324)
    Button btnSplit2324;
    @BindView(R2.id.btn_corner23242627)
    Button btnCorner23242627;
    @BindView(R2.id.fl_corner23242627)
    FrameLayout flCorner23242627;
    @BindView(R2.id.btn_split2627)
    Button btnSplit2627;
    @BindView(R2.id.btn_corner26272930)
    Button btnCorner26272930;
    @BindView(R2.id.btn_split2930)
    Button btnSplit2930;
    @BindView(R2.id.btn_corner29303233)
    Button btnCorner29303233;
    @BindView(R2.id.btn_split3233)
    Button btnSplit3233;
    @BindView(R2.id.btn_corner32333536)
    Button btnCorner32333536;
    @BindView(R2.id.btn_split3536)
    Button btnSplit3536;
    @BindView(R2.id.btn_split0002)
    Button btnSplit0002;
    @BindView(R2.id.btn_split0205)
    Button btnSplit0205;
    @BindView(R2.id.btn_split0508)
    Button btnSplit0508;
    @BindView(R2.id.btn_split0811)
    Button btnSplit0811;
    @BindView(R2.id.btn_split1114)
    Button btnSplit1114;
    @BindView(R2.id.btn_split1417)
    Button btnSplit1417;
    @BindView(R2.id.btn_split1720)
    Button btnSplit1720;
    @BindView(R2.id.btn_split2023)
    Button btnSplit2023;
    @BindView(R2.id.btn_split2326)
    Button btnSplit2326;
    @BindView(R2.id.btn_split2629)
    Button btnSplit2629;
    @BindView(R2.id.btn_split2932)
    Button btnSplit2932;
    @BindView(R2.id.btn_split3235)
    Button btnSplit3235;
    @BindView(R2.id.btn_street000102)
    Button btnStreet000102;
    @BindView(R2.id.btn_split0102)
    Button btnSplit0102;
    @BindView(R2.id.btn_corner01020405)
    Button btnCorner01020405;
    @BindView(R2.id.btn_split0405)
    Button btnSplit0405;
    @BindView(R2.id.btn_corner04050708)
    Button btnCorner04050708;
    @BindView(R2.id.btn_split0708)
    Button btnSplit0708;
    @BindView(R2.id.btn_corner07081011)
    Button btnCorner07081011;
    @BindView(R2.id.btn_split1011)
    Button btnSplit1011;
    @BindView(R2.id.btn_corner10111314)
    Button btnCorner10111314;
    @BindView(R2.id.btn_split1314)
    Button btnSplit1314;
    @BindView(R2.id.btn_corner13141617)
    Button btnCorner13141617;
    @BindView(R2.id.btn_split1617)
    Button btnSplit1617;
    @BindView(R2.id.btn_corner16171920)
    Button btnCorner16171920;
    @BindView(R2.id.btn_split1920)
    Button btnSplit1920;
    @BindView(R2.id.btn_corner19202223)
    Button btnCorner19202223;
    @BindView(R2.id.btn_split2223)
    Button btnSplit2223;
    @BindView(R2.id.btn_corner22232526)
    Button btnCorner22232526;
    @BindView(R2.id.btn_split2526)
    Button btnSplit2526;
    @BindView(R2.id.btn_corner25262829)
    Button btnCorner25262829;
    @BindView(R2.id.btn_split2829)
    Button btnSplit2829;
    @BindView(R2.id.btn_corner28293132)
    Button btnCorner28293132;
    @BindView(R2.id.btn_split3132)
    Button btnSplit3132;
    @BindView(R2.id.btn_corner31323435)
    Button btnCorner31323435;
    @BindView(R2.id.btn_split3435)
    Button btnSplit3435;
    @BindView(R2.id.btn_split0001)
    Button btnSplit0001;
    @BindView(R2.id.btn_split0104)
    Button btnSplit0104;
    @BindView(R2.id.btn_split0407)
    Button btnSplit0407;
    @BindView(R2.id.btn_split0710)
    Button btnSplit0710;
    @BindView(R2.id.btn_split1013)
    Button btnSplit1013;
    @BindView(R2.id.btn_split1316)
    Button btnSplit1316;
    @BindView(R2.id.btn_split1619)
    Button btnSplit1619;
    @BindView(R2.id.btn_split1922)
    Button btnSplit1922;
    @BindView(R2.id.btn_split2225)
    Button btnSplit2225;
    @BindView(R2.id.btn_split2528)
    Button btnSplit2528;
    @BindView(R2.id.btn_split2831)
    Button btnSplit2831;
    @BindView(R2.id.btn_split3134)
    Button btnSplit3134;
    @BindView(R2.id.btn_corner00010203)
    Button btnCorner00010203;
    @BindView(R2.id.btn_street010203)
    Button btnStreet010203;
    @BindView(R2.id.btn_line010203040506)
    Button btnLine010203040506;
    @BindView(R2.id.btn_street040506)
    Button btnStreet040506;
    @BindView(R2.id.btn_line040506070809)
    Button btnLine040506070809;
    @BindView(R2.id.btn_street070809)
    Button btnStreet070809;
    @BindView(R2.id.btn_line070809101112)
    Button btnLine070809101112;
    @BindView(R2.id.btn_street101112)
    Button btnStreet101112;
    @BindView(R2.id.btn_line101112131415)
    Button btnLine101112131415;
    @BindView(R2.id.btn_street131415)
    Button btnStreet131415;
    @BindView(R2.id.btn_line131415161718)
    Button btnLine131415161718;
    @BindView(R2.id.btn_street161718)
    Button btnStreet161718;
    @BindView(R2.id.btn_line161718192021)
    Button btnLine161718192021;
    @BindView(R2.id.btn_street192021)
    Button btnStreet192021;
    @BindView(R2.id.btn_line192021222324)
    Button btnLine192021222324;
    @BindView(R2.id.btn_street222324)
    Button btnStreet222324;
    @BindView(R2.id.btn_line222324252627)
    Button btnLine222324252627;
    @BindView(R2.id.btn_street252627)
    Button btnStreet252627;
    @BindView(R2.id.btn_line252627282930)
    Button btnLine252627282930;
    @BindView(R2.id.btn_street282930)
    Button btnStreet282930;
    @BindView(R2.id.btn_line282930313233)
    Button btnLine282930313233;
    @BindView(R2.id.btn_street313233)
    Button btnStreet313233;
    @BindView(R2.id.btn_line313233343536)
    Button btnLine313233343536;
    @BindView(R2.id.btn_street343536)
    Button btnStreet343536;
    @BindView(R2.id.iv_number00)
    ImageView ivNumber00;
    @BindView(R2.id.iv_number00_new)
    ImageView ivNumber00New;

    @BindView(R2.id.iv_dozen1)
    ImageView ivDozen1;
    @BindView(R2.id.iv_dozen2)
    ImageView ivDozen2;
    @BindView(R2.id.iv_dozen3)
    ImageView ivDozen3;
    @BindView(R2.id.iv_low)
    ImageView ivLow;
    @BindView(R2.id.iv_even)
    ImageView ivEven;
    @BindView(R2.id.iv_black)
    ImageView ivBlack;
    @BindView(R2.id.iv_red)
    ImageView ivRed;
    @BindView(R2.id.iv_odd)
    ImageView ivOdd;
    @BindView(R2.id.iv_high)
    ImageView ivHigh;

    @BindView(R2.id.ll_bet_btn_parent)
    View ll_bet_btn_parent;
    @BindView(R2.id.ll_chip_parent)
    View ll_chip_parent;
    @BindView(R2.id.tv_black_percentage)
    TextView tv_black_percentage;
    @BindView(R2.id.tv_red_percentage)
    TextView tv_red_percentage;
    @BindView(R2.id.tv_odd_percentage)
    TextView tv_odd_percentage;
    @BindView(R2.id.tv_even_percentage)
    TextView tv_even_percentage;
    @BindView(R2.id.rc_road)
    RecyclerView rc_road;
    private boolean limitRedShowAble = false;
    private boolean betInfoShowAble = false;

    AdapterViewContent<LiveInfoBean> contentPool = null;
    AdapterViewContent<LiveInfoBean> contentInfo = null;
    AdapterViewContent<String> contentBetPool = null;


    private boolean personInfoShowAble;
    private VideoPlayer mPreview;
    private boolean isBottomOpen = false;
    private boolean isVisibility;
    private boolean isDiceVisible;
    private BaseRecyclerAdapter<String> contentAdapter;

    //////////////////////lanjian tianjia
    private int rouletteTimer = 0;
    private int betTimeCount = 0;
    private boolean bUpdateRoad = true;
    private boolean bBetSucess = false;
    private boolean showRoad = false;
    private int chooseChip = 0;
    private String gameNumber = "";


    private List<BetDetail> listBetDetail = new ArrayList<BetDetail>();


    private TextView tv_game_number01;
    private TextView tv_roulette_red01;
    private TextView tv_roulette_black01;
    private TextView tv_roulette_zero01;
    private TextView tv_roulette_even01;
    private TextView tv_roulette_odd01;
    private TextView tv_roulette_big01;
    private TextView tv_roulette_small01;

    private UpdateStatus updateStatus = null;
    private Thread threadStatus = null;
    private boolean bGetStatus = true;
    private boolean isLiveVisible;
    private boolean isLimitVisible = true;
    private VideoHelper videoHelper;
    private boolean canBet = true;
    private boolean stateInit = false;


    public void clickLeftPanel(View view) {
        if (leftPanel1.isOpen()) {
            leftPanel1.setOpen(false, true);
        } else
            leftPanel1.setOpen(true, true);
    }

    List<String> smallList = new ArrayList<String>(
            Arrays.asList("33"
                    , "16"
                    , "24"
                    , "05"
                    , "10"
                    , "23"
                    , "08"
                    , "30"
                    , "11"
                    , "36"
                    , "13"
                    , "27"
            )
    );

    public void clickCenterSmall(View view) {
        if (checkChoose()) return;
        if (mAppViewModel.getRoulette01().getGameStatus() != 1)
            return;
        if (ll_center_small == null)
            return;
//        ll_center_small.setVisibility(View.VISIBLE);
        int sure = R.mipmap.sureimg_light;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.noimg_light;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
        for (String s : smallList) {
            clickNumber(s);
        }
    }

    public void clickCenterSmallCancel(View view) {
        clearBetChip("small");
    }

    public void clickCenterSmallSure(View view) {
        singleBet("small");
    }

    List<String> orphelinsList = new ArrayList<String>(
            Arrays.asList("01"
                    , "20"
                    , "14"
                    , "31"
                    , "09"
                    , "06"
                    , "34"
                    , "17"
            )
    );

    public void clickOrphelins(View view) {
        if (checkChoose()) return;
        if (mAppViewModel.getRoulette01().getGameStatus() != 1)
            return;
        if (ll_center_orphelins == null)
            return;
//        ll_center_orphelins.setVisibility(View.VISIBLE);
        int sure = R.mipmap.sureimg_light;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.noimg_light;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
        for (String s : orphelinsList) {
            clickNumber(s);
        }
    }

    public void clickOrphelinsCancel(View view) {
        clearBetChip("orphelins");
    }

    public void clickOrphelinsSure(View view) {
        singleBet("orphelins");
    }

    List<String> zeroList = new ArrayList<String>(
            Arrays.asList("12"
                    , "35"
                    , "03"
                    , "26"
                    , "00"
                    , "32"
                    , "15"

            )
    );

    public void clickZero(View view) {
        if (checkChoose()) return;
        if (mAppViewModel.getRoulette01().getGameStatus() != 1)
            return;
        if (ll_zero == null)
            return;
//        ll_zero.setVisibility(View.VISIBLE);
        int sure = R.mipmap.sureimg_light;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.noimg_light;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
        for (String s : zeroList) {
            clickNumber(s);
        }
    }

    public void clickZeroCancel(View view) {
        clearBetChip("zero");
    }

    public void clickZeroSure(View view) {
        singleBet("zero");
    }

    List<String> bigList = new ArrayList<String>(
            Arrays.asList("22"
                    , "18"
                    , "29"
                    , "07"
                    , "28"
                    , "25"
                    , "02"
                    , "21"
                    , "04"
                    , "19"
                    , "12"
                    , "35"
                    , "03"
                    , "26"
                    , "00"
                    , "32"
                    , "15"
            )
    );

    public void clickCenterBig(View view) {
        if (checkChoose()) return;
        if (mAppViewModel.getRoulette01().getGameStatus() != 1)
            return;
        if (ll_center_big == null)
            return;
//        ll_center_big.setVisibility(View.VISIBLE);
        int sure = R.mipmap.sureimg_light;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.noimg_light;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
        for (String s : bigList) {
            clickNumber(s);
        }
    }

    public void clickCenterBigCancel(View view) {
        clearBetChip("big");
    }

    public void clickCenterBigSure(View view) {
        singleBet("big");
    }


    public class UpdateStatus implements Runnable {
        int iError = 0;

        public void run() {
            while (bGetStatus) {
                try {
                    handler.sendEmptyMessage(HandlerCode.UPDATE_STATUS);
                    Thread.sleep(1020);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private BackLobby backLobby = null;
    private Thread threadBackLobby = null;

    public class BackLobby implements Runnable {
        int iError = 0;

        public void run() {

            try {
                Thread.sleep(1000);
                handler.sendEmptyMessage(HandlerCode.SHOW_BACK_LOBBY);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private UpdateBetMoney updateBetMoney = null;
    private Thread threadUpdateBetMoney = null;

    public class UpdateBetMoney implements Runnable {
        public void run() {

            try {
                Thread.sleep(1500);
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                        + "&Blid=" + mAppViewModel.getRoulette01().getGameNumber() +
                        "&Xh=" + mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LP_BET_MONEY_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateBetMoney params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateBetMoney = " + strRes);

                String strInfo[] = strRes.split("\\^");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 10) {

                        mAppViewModel.getRoulette01().getRouletteBetInformation().setAllBetMoney((int) Double.parseDouble(strInfo[2]));
                        if (mAppViewModel.getRoulette01().getRouletteBetInformation().getAllBetMoney() > 0)
                            bBetSucess = true;
                        mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail().clear();
                        saveBetInformation("DirectBet", strInfo[3], false);
                        saveBetInformation("SeparateBet", strInfo[4], false);
                        saveBetInformation("StreetBet", strInfo[5], false);
                        saveBetInformation("AngleBet", strInfo[6], false);
                        saveBetInformation("LineBet", strInfo[7], false);
                        saveBetInformation("ThreeBet", strInfo[8], false);
                        saveBetInformation("FourBet", strInfo[9], false);
                        saveBetInformation("FristRow", strInfo[10], false);
                        saveBetInformation("SndRow", strInfo[11], false);
                        saveBetInformation("ThrRow", strInfo[12], false);
                        saveBetInformation("FristCol", strInfo[13], false);
                        saveBetInformation("SndCol", strInfo[14], false);
                        saveBetInformation("ThrCol", strInfo[15], false);
                        saveBetInformation("RedBet", strInfo[16], false);
                        saveBetInformation("BlackBet", strInfo[17], false);
                        saveBetInformation("OddBet", strInfo[18], false);
                        saveBetInformation("EvenBet", strInfo[19], false);
                        saveBetInformation("LowBet", strInfo[20], false);
                        saveBetInformation("HightBet", strInfo[21], false);

                        handler.sendEmptyMessage(HandlerCode.SHOW_BET_MONEY);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private RouletteBet rouletteBet = null;
    private Thread threadRouletteBet = null;

    public List<BetDetail> removeDuplicate(List<BetDetail> list) {
        List<String> listTemp = new ArrayList<>();
        List<BetDetail> listData = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (!listTemp.contains(list.get(i).getNumber())) {
                listTemp.add(list.get(i).getNumber());
                listData.add(list.get(i));
            }
        }
        return listData;
    }

    private String type = "all";

    public class RouletteBet implements Runnable {

        public RouletteBet(String type) {
            RouletteActivity.this.type = type;
        }

        public void run() {
            if (isCanbet) {
                isCanbet = false;
                try {
                    String LowBet = "0";
                    String HightBet = "0";
                    String OddBet = "0";
                    String EvenBet = "0";
                    String DirectBet = "";
                    String SeparateBet = "";
                    String StreetBet = "";
                    String AngleBet = "";
                    String LineBet = "";
                    String ThreeBet = "";
                    String FourBet = "0";
                    String FristRow = "0";
                    String SndRow = "0";
                    String ThrRow = "0";
                    String FristCol = "0";
                    String SndCol = "0";
                    String ThrCol = "0";
                    String RedBet = "0";
                    String BlackBet = "0";

                    listBetDetail = removeDuplicate(listBetDetail);

                    for (int i = 0; i < listBetDetail.size(); i++) {
                        if (listBetDetail.get(i).getMoney() > 0)
                            switch (listBetDetail.get(i).getType()) {
                                case "RedBet":
                                    if (type.equals("all") || type.equals(listBetDetail.get(i).getNumber()))
                                        RedBet = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "BlackBet":
                                    if (type.equals("all") || type.equals(listBetDetail.get(i).getNumber()))
                                        BlackBet = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "HightBet":
                                    if (type.equals("all") || type.equals(listBetDetail.get(i).getNumber()))
                                        HightBet = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "LowBet":
                                    if (type.equals("all") || type.equals(listBetDetail.get(i).getNumber()))
                                        LowBet = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "OddBet":
                                    if (type.equals("all") || type.equals(listBetDetail.get(i).getNumber()))
                                        OddBet = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "EvenBet":
                                    if (type.equals("all") || type.equals(listBetDetail.get(i).getNumber()))
                                        EvenBet = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "FristRow":
                                    if (type.equals("all"))
                                        FristRow = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "SndRow":
                                    if (type.equals("all"))
                                        SndRow = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "ThrRow":
                                    if (type.equals("all"))
                                        ThrRow = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "FristCol":
                                    if (type.equals("all"))
                                        FristCol = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "SndCol":
                                    if (type.equals("all"))
                                        SndCol = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "ThrCol":
                                    if (type.equals("all"))
                                        ThrCol = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "FourBet":
                                    if (type.equals("all"))
                                        FourBet = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "DirectBet":
                                    if (type.equals("all") || type.equals(listBetDetail.get(i).getNumber()) || checkCenter(type, listBetDetail.get(i).getNumber()))
                                        DirectBet += listBetDetail.get(i).getNumber() + "#" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()) + "|");
                                    break;
                                case "SeparateBet":
                                    if (type.equals("all"))
                                        SeparateBet += listBetDetail.get(i).getNumber() + "#" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()) + "|");
                                    break;
                                case "StreetBet":
                                    if (type.equals("all"))
                                        StreetBet += listBetDetail.get(i).getNumber() + "#" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()) + "|");
                                    break;
                                case "AngleBet":
                                    if (type.equals("all"))
                                        AngleBet += listBetDetail.get(i).getNumber() + "#" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()) + "|");
                                    break;
                                case "LineBet":
                                    if (type.equals("all"))
                                        LineBet += listBetDetail.get(i).getNumber() + "#" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()) + "|");
                                    break;
                                case "ThreeBet":
                                    if (type.equals("all"))
                                        ThreeBet += listBetDetail.get(i).getNumber() + "#" + (listBetDetail.get(i).getMoney() - mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()) + "|");
                                    break;
                            }

                    }
                    if ("".equals(DirectBet))
                        DirectBet = "0";
                    if ("".equals(SeparateBet))
                        SeparateBet = "0";
                    if ("".equals(StreetBet))
                        StreetBet = "0";
                    if ("".equals(AngleBet))
                        AngleBet = "0";
                    if ("".equals(LineBet))
                        LineBet = "0";
                    if ("".equals(ThreeBet))
                        ThreeBet = "0";

                    String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                            + "&Blid=" + mAppViewModel.getRoulette01().getGameNumber()
                            + "&Xh=" + mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet()
                            + "&Hl=1"
                            + "&RedBet=" + RedBet + "&BlackBet=" + BlackBet + "&OddBet=" + OddBet + "&EvenBet=" + EvenBet + "&LowBet=" + LowBet + "&HightBet=" + HightBet
                            + "&FristRow=" + FristRow + "&SndRow=" + SndRow + "&ThrRow=" + ThrRow + "&FristCol=" + FristCol + "&SndCol=" + SndCol + "&ThrCol=" + ThrCol
                            + "&FourBet=" + FourBet
                            + "&DirectBet=" + DirectBet + "&SeparateBet=" + SeparateBet + "&StreetBet=" + StreetBet + "&AngleBet=" + AngleBet + "&LineBet=" + LineBet + "&ThreeBet=" + ThreeBet;

                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LP_BET_URL, params);
                    Log.i("RouletteBet params", "RouletteBet params= " + params);
                    Log.i("RouletteBet params", "RouletteBet = " + strRes);
                    String strInfo[] = strRes.split("\\^");
                    if (strRes.startsWith("Results=ok")) {
                        if (strInfo.length >= 10) {
                            mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                            double resMoney = Double.parseDouble(strInfo[2]);
                            //清除之前的下注记录
                            mAppViewModel.getRoulette01().getRouletteBetRepeatInformation().Init();
                            mAppViewModel.getRoulette01().getRouletteBetInformation().setAllBetMoney((int) resMoney);
                            mAppViewModel.getRoulette01().getRouletteBetRepeatInformation().setAllBetMoney((int) resMoney);

                            mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail().clear();
                            mAppViewModel.getRoulette01().getRouletteBetRepeatInformation().getBetDetail().clear();
                            saveBetInformation("DirectBet", strInfo[3], true);
                            saveBetInformation("SeparateBet", strInfo[4], true);
                            saveBetInformation("StreetBet", strInfo[5], true);
                            saveBetInformation("AngleBet", strInfo[6], true);
                            saveBetInformation("LineBet", strInfo[7], true);
                            saveBetInformation("ThreeBet", strInfo[8], true);
                            saveBetInformation("FourBet", strInfo[9], true);
                            saveBetInformation("FristRow", strInfo[10], true);
                            saveBetInformation("SndRow", strInfo[11], true);
                            saveBetInformation("ThrRow", strInfo[12], true);
                            saveBetInformation("FristCol", strInfo[13], true);
                            saveBetInformation("SndCol", strInfo[14], true);
                            saveBetInformation("ThrCol", strInfo[15], true);
                            saveBetInformation("RedBet", strInfo[16], true);
                            saveBetInformation("BlackBet", strInfo[17], true);
                            saveBetInformation("OddBet", strInfo[18], true);
                            saveBetInformation("EvenBet", strInfo[19], true);
                            saveBetInformation("LowBet", strInfo[20], true);
                            saveBetInformation("HightBet", strInfo[21], true);

                            //提示下注成功,清除下注信息
                            bBetSucess = true;
                            betTimeCount = 0;
                            handler.sendEmptyMessage(HandlerCode.SHOW_BET_SUCCESS);

                        }
                    } else {//提示下注失败
                        handler.sendEmptyMessage(HandlerCode.SHOW_BET_ERROR);

                    }
                    isCanbet = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(HandlerCode.THREAD_ERROR);
                    isCanbet = true;
                }
            }
        }
    }

    private boolean checkCenter(String type, String number) {
        if (type.equals("small")) {
            smallList.contains(number);
            return true;
        }
        if (type.equals("big")) {
            bigList.contains(number);
            return true;
        }
        if (type.equals("orphelins")) {
            orphelinsList.contains(number);
            return true;
        }
        if (type.equals("zero")) {
            zeroList.contains(number);
            return true;
        }
        return false;
    }


    private UpdateGameNumber updateGameNumber = null;
    private Thread threadUpdateGameNumber = null;

    public class UpdateGameNumber implements Runnable {
        public void run() {

            try {
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LP_TABLE_GAMENUM, params);
                Log.i(WebSiteUrl.Tag, "UpdateGameNumber params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateGameNumber = " + strRes);
                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        mAppViewModel.getRoulette01().setGameNumber(strInfo[1]);
                        handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER);
                    }
                } else {
                    handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER_ERROR);
                }

            } catch (Exception e) {
                e.printStackTrace();
                handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER_ERROR);
            }

        }
    }

    private UpdateWonMoney updateWonMoney = null;
    private Thread threadUpdateWonMoney = null;

    public class UpdateWonMoney implements Runnable {
        public void run() {

            try {
//                if (bUpdateRoad == false)
//                    Thread.sleep(3000);
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                        + "&Blid=" + mAppViewModel.getRoulette01().getGameNumber() +
                        "&Xh=" + mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LP_WON_MONEY_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateWonMoney params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateWonMoney = " + strRes);

                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        if (rouletteTimer == 0 && mAppViewModel.getRoulette01().getGameStatus() == 5 && isShowWinLose) {
                            mAppViewModel.getRoulette01().setWonMoney(Double.parseDouble(strInfo[2]));
                        } else {
                            mAppViewModel.getRoulette01().setWonMoney(0);
                        }
                        mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                        if (isShowWinLose) {
                            handler.sendEmptyMessage(HandlerCode.SHOW_WIN_LOSS);
                        }
                    } else {
                        mAppViewModel.getRoulette01().setWonMoney(0);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    boolean isShowWinLose = true;
    boolean isNeedBigVedio = true;

    public void updateTimer() {
        if (rouletteTimer == 0 && mAppViewModel.getRoulette01().getGameStatus() == 1 && mAppViewModel.getRoulette01().getTimer() > 0) {
            if (isShowWinLose) {
                isShowWinLose = false;
                updateWonMoney = new UpdateWonMoney();
                threadUpdateWonMoney = new Thread(updateWonMoney);
                threadUpdateWonMoney.start();
            }
            if (!isNeedBigVedio && fl_vedio_parent.getHeight() > fl_vedio_location_parent.getHeight()) {
                isNeedBigVedio = true;
                closeBigVedio();
            } else {
                isNeedBigVedio = true;
            }
            //   Log.i(WebSiteUrl.Tag,"gameNumber="+gameNumber+",getGameNumber="+mAppViewModel.getRoulette01().getGameNumber()+"------");
            //需要调用一下gamegum.jsp接口，更新局号
//            if(updateGameNumber == null){
//                updateGameNumber = new UpdateGameNumber();
//                threadUpdateGameNumber = new Thread(updateGameNumber);
//                threadUpdateGameNumber.start();
//            }
            if (!gameNumber.equals(mAppViewModel.getRoulette01().getGameNumber())) {
                gameNumber = mAppViewModel.getRoulette01().getGameNumber();
                tv_game_number01.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
                rouletteNumberTv.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
                stateInit = true;
                mAppViewModel.getRoulette01().Init();
                clearAllChips();
                rouletteTimer = mAppViewModel.getRoulette01().getTimer();
                countdown_view.setCountdownTime(rouletteTimer);
                countdown_view.startCountDown();
                bUpdateRoad = true;
                bBetSucess = false;

                betTimeCount++;
                if (betTimeCount == 6)//跳转到大厅
                {
                    ToastUtils.showBackToast(mContext, getString(R.string.friendly_message), getString(R.string.show_back_lobby));
                    backLobby = new BackLobby();
                    threadBackLobby = new Thread(backLobby);
                    threadBackLobby.start();
                }else if (betTimeCount==4){
                    ToastUtils.showBackToast(mContext, getString(R.string.friendly_message), getString(R.string.three_no_bet));
                }
                tvTableBetSure.setEnabled(true);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 16, componentFront, mContext, mAppViewModel.getFrontVolume());
                btn_game_number.setText(getString(R.string.number) + ":" + mAppViewModel.getRoulette01().getGameNumber());
                if (tv_game_number != null) {
                    tv_game_number.setText(gameNumber);
                }


            }


            if (fl_roulette_result != null && fl_roulette_result.getVisibility() == View.VISIBLE) {
                fl_roulette_result.setVisibility(View.GONE);
            }

            //    pokerPopupWindow.closePopupWindow();
            //    hidePoker();


        } else if (mAppViewModel.getRoulette01().getGameStatus() == 5)//延迟3秒调用路子接口
        {
            if (bUpdateRoad) {
                bUpdateRoad = false;

                //    showBetPanel();
                //检查输赢
                isShowWinLose = true;
                updateWonMoney = new UpdateWonMoney();
                threadUpdateWonMoney = new Thread(updateWonMoney);
                threadUpdateWonMoney.start();
                showResultsOnUI();
//            if(isActive)
//                initPopResultsWindows();
                if (!TextUtils.isEmpty(mAppViewModel.getRoulette01().getResult())) {
                    handler.sendEmptyMessageDelayed(HandlerCode.SHOW_POPUP_RESULTS_WINDOW, 1500);
                }
//                tablePop.setTablesData(afbApp, games);
            }
            if (isNeedBigVedio && fl_vedio_parent.getHeight() <= fl_vedio_location_parent.getHeight()) {
                isNeedBigVedio = false;
                openBigVedio();
            } else {
                isNeedBigVedio = false;
            }
        } else if (mAppViewModel.getRoulette01().getGameStatus() == 2) {
            if (listBetDetail.size() > 0) {
                //  Log.i(WebSiteUrl.Tag, "clearNoBetChip()");
                clearBetChip("all");
//                clearNoBetChip("all");
            }
            if (tvTableBetSure != null && tvTableBetSure.isEnabled()) {
                tvTableBetSure.setEnabled(false);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_NOMOREBETS, 14, componentFront, mContext, mAppViewModel.getFrontVolume());
            }
            if (isNeedBigVedio && fl_vedio_parent.getHeight() <= fl_vedio_location_parent.getHeight()) {
                isNeedBigVedio = false;
                openBigVedio();
            } else {
                isNeedBigVedio = false;
            }
       /*     //需要隐藏下注区域，看到视频里出结果
            hideBetPanel();*/
        }
  /*      if (!gameNumber.equals(mAppViewModel.getRoulette01().getGameNumber())) {
            clearAllChips();
        }*/
        Log.d("shangpeishengaaaa", "rouletteResult: " + mAppViewModel.getRoulette01().getResult());
    }

    private void updatePercentage() {
        Roulette roulette = mAppViewModel.getRoulette01();
        double red = roulette.getRed();
        double black = roulette.getBlack();
        double odd = roulette.getOdd();
        double even = roulette.getEven();
        double blackPercentage = 0;
        double redPercentage = 0;
        double oddPercentage = 0;
        double evenPercentage = 0;
        double redBlackTotal = red + black;
        double oddEvenTotal = odd + even;
        if (redBlackTotal > 0) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            if (black > 0) {
                blackPercentage = Double.parseDouble(decimalFormat.format(black / redBlackTotal));
            }
            redPercentage = Double.parseDouble(decimalFormat.format(1 - blackPercentage));
            redPercentage = redPercentage * 100;
            blackPercentage = blackPercentage * 100;
        }
        if (oddEvenTotal > 0) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            if (odd > 0) {
                oddPercentage = Double.parseDouble(decimalFormat.format(odd / oddEvenTotal));
            }
            evenPercentage = Double.parseDouble(decimalFormat.format(1 - oddPercentage));
            evenPercentage = evenPercentage * 100;
            oddPercentage = oddPercentage * 100;
        }
        tv_black_percentage.setText(getString(R.string.black_acronym) + "  " + (int) blackPercentage + "%");
        tv_red_percentage.setText(getString(R.string.red_acronym) + "  " + (int) redPercentage + "%");
        tv_odd_percentage.setText(getString(R.string.O) + "  " + (int) oddPercentage + "%");
        tv_even_percentage.setText(getString(R.string.E) + "  " + (int) evenPercentage + "%");
        updateHotIceNumber();

    }

    private void updateHotIceNumber() {
        new Thread() {
            @Override
            public void run() {
                //Results=ok^29#8#14#28#31#18#11#35#6#25#1#16#12#0#9#36#23#5#33#17#10#20#15#27#4#3#24#30#32#26#13#34#21#2#22#19#7#
                String s = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LP_ROAD_URL, "");
                if (s.startsWith("Results=ok")) {
                    final List<String> hotList = new ArrayList<>();
                    final List<String> iceList = new ArrayList<>();
                    String[] split1 = s.split("\\^");
                    String[] split = split1[1].split("#");
                    if (split.length > 10) {
                        for (int i = 0; i < split.length; i++) {
                            if (i < 5) {
                                hotList.add(split[i]);
                            } else if (i > (split.length - 6)) {
                                iceList.add(split[i]);
                            }
                        }
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (rc_hot != null) {
                                    setHotIceData(rc_hot, hotList, 1);
                                }
                                if (rc_ice != null) {
                                    setHotIceData(rc_ice, iceList, 2);
                                }
                            }
                        }, 500);
                    }
                }
            }
        }.start();
    }

    private void setHotIceData(RecyclerView recyclerView, List<String> data, final int type) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BaseRecyclerAdapter contentAdapter = new BaseRecyclerAdapter<String>(mContext, data, R.layout.item_hot_ice) {
            @Override
            public void convert(MyRecyclerViewHolder helper, int position, String item) {
                TextView tvContent = helper.getView(R.id.tv_content);
                ViewGroup.LayoutParams layoutParams = tvContent.getLayoutParams();
                int with;
                if (type == 1) {
                    with = rc_hot.getWidth() / 5;
                } else {
                    with = rc_ice.getWidth() / 5;
                }
                layoutParams.width = with;
                layoutParams.height = with;
                tvContent.setLayoutParams(layoutParams);
                tvContent.setText(item);
                if (type == 1) {
                    tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                } else {
                    tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.blue));
                }
            }
        };
        recyclerView.setAdapter(contentAdapter);
    }

    private boolean isCanClickVedio = true;

    private void closeBigVedio() {
        if (isCanClickVedio) {
            isCanClickVedio = false;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fl_vedio_parent.getLayoutParams();
            layoutParams.width = ScreenUtil.dip2px(mContext, 125);
            layoutParams.height = fl_vedio_location_parent.getHeight();
            fl_vedio_parent.setLayoutParams(layoutParams);
            FrameLayout.LayoutParams mPreviewLayoutParams = (FrameLayout.LayoutParams) fl_surface_parent.getLayoutParams();
            mPreviewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mPreviewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mPreviewLayoutParams.topMargin = 0;
            fl_surface_parent.setLayoutParams(mPreviewLayoutParams);
            fl_vedio_parent.setBackgroundResource(0);
            isCanClickVedio = true;
        }
    }

    private void openBigVedio() {
        if (isCanClickVedio) {
            isCanClickVedio = false;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fl_vedio_parent.getLayoutParams();
                    FrameLayout.LayoutParams mPreviewLayoutParams = (FrameLayout.LayoutParams) fl_surface_parent.getLayoutParams();
                    mPreviewLayoutParams.width = (int) (layoutParams.width * 2.2);
                    mPreviewLayoutParams.height = (int) (layoutParams.height * 2.2);
                    mPreviewLayoutParams.topMargin = fl_vedio_location_parent.getHeight() + ((int) (AutoUtils.getPercentHeight1px() * 10));
                    fl_surface_parent.setLayoutParams(mPreviewLayoutParams);
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    fl_vedio_parent.setLayoutParams(layoutParams);
                    fl_vedio_parent.setBackgroundResource(R.drawable.rectangle_trans_stroke_roulette_black);
                    isCanClickVedio = true;
                }
            }, 500);
        }
    }

    boolean isCanShowChip = true;
    boolean isCanHideChip = true;

    public void updateInterface() {

        if (rouletteTimer > 0 && mAppViewModel.getRoulette01().getGameStatus() != 2) {
            Drawable number00Background = ivNumber00.getBackground();
            Drawable redBackground = ivRed.getBackground();
            Drawable blackBackground = ivBlack.getBackground();
            if (number00Background != null || redBackground != null || blackBackground != null) {
                for (int i = 0; i < imgList.size(); i++) {
                    imgList.get(i).setBackgroundResource(0);
                }
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                int heightPixels = WidgetUtil.getScreenHeight(this);
                int[] location2 = new int[2];
                ll_chip.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                if (location2[1] >= heightPixels && isCanShowChip) {
                    isCanShowChip = false;
                    WidgetUtil.chipTranslateAnimation(ll_chip, 0, ScreenUtil.dip2px(mContext, -43), new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanShowChip = true;
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            isCanShowChip = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            }
            rouletteTimer--;

            if (fl_roulette_result != null && fl_roulette_result.getVisibility() == View.VISIBLE) {
                fl_roulette_result.setVisibility(View.GONE);
            }
            tv_table_timer.setText("" + rouletteTimer);
//            if (rouletteTimer == 10) {
//                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_TIMER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
//            }
            if (rouletteTimer < 6) {
                tv_table_timer.setTextColor(getResources().getColor(R.color.red));
                mAppViewModel.startFrontMuzicService("TIMER", 1, componentFront, mContext, mAppViewModel.getFrontVolume());
            } else
                tv_table_timer.setTextColor(getResources().getColor(R.color.white));
            if (showRoad){
                tv_game_number01.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
                rouletteNumberTv.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
            }

        } else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                int heightPixels = WidgetUtil.getScreenHeight(this);
                int[] location2 = new int[2];
                ll_chip.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                if (location2[1] < heightPixels && isCanHideChip) {
                    isCanHideChip = false;
                    WidgetUtil.chipTranslateAnimation(ll_chip, ScreenUtil.dip2px(mContext, -43), 0, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideChip = true;
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            isCanHideChip = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            }
            rouletteTimer = 0;
            tv_table_timer.setText("" + rouletteTimer);
            if (mAppViewModel.getRoulette01().getGameStatus() == 2) {

            }
//            if (stateInit)
//                displayAll(false);
//            stateInit = false;
        }
        String serverTimer = mAppViewModel.getRoulette01().getServerTime();
        String time = "";
        if (serverTimer != null && serverTimer.indexOf("-") > 0)
            time = "GMT+7  " + serverTimer.substring(serverTimer.indexOf("-") + 1, serverTimer.length());
        tv_time.setText(time);
        setToolbarAndSet(time, usName);


        updateTablePool();
        updateInfo();

        //    if (showRoad)
        updateRoad(mAppViewModel.getRoulette01(), tv_game_number, tv_roulette_red01, tv_roulette_black01, tv_roulette_zero01, tv_roulette_even01, tv_roulette_odd01, tv_roulette_big01, tv_roulette_small01);

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (!isAttached)
                return;
            switch (msg.what) {
                case HandlerCode.SHOW_BACK_LOBBY:
                    gotoLobby();
                    break;
                case HandlerCode.UPDATE_STATUS:
                    updateTimer();
                    updateInterface();
                    break;
                case HandlerCode.SHOW_LIMIT_OVER_MAX:
                    Toast.makeText(mContext, R.string.show_limit_over_max, Toast.LENGTH_LONG).show();
                    break;
                case HandlerCode.UPDATE_GAME_NUMBER_ERROR:
                    updateGameNumber = null;
                    break;
                case HandlerCode.UPDATE_GAME_NUMBER:
                    updateGameNumber = null;
                    btn_game_number.setText(getString(R.string.number) + ":" + mAppViewModel.getRoulette01().getGameNumber());
                    //           contentResults.setData(getSicboResultsData());
                    //           contentResults.notifyDataSetChanged();
                    //      if (showRoad)
                    tv_game_number01.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
                    rouletteNumberTv.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
                    tv_game_number.setText(mAppViewModel.getRoulette01().getGameNumber());
                    if (!gameNumber.equals(mAppViewModel.getRoulette01().getGameNumber())) {
                        clearAllChips();
                    }
                    break;
                case HandlerCode.SHOW_WIN_LOSS:
                    serviceTime.setText(mAppViewModel.getUser().getBalance() + "");
                    //提示输赢
                    if (bBetSucess) {
//                        if (mAppViewModel.getRoulette01().getWonMoney() >= 0/* && mAppViewModel.getRoulette01().getGameStatus() == 5*/) {
//                            if (mAppViewModel.getRoulette01().getWonMoney() > 0)
//                                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 15, componentFront, mContext, mAppViewModel.getFrontVolume());
//                            ToastUtils.showToast(mContext, getResources().getString(R.string.show_win) + " " + mAppViewModel.getRoulette01().getWonMoney(), ContextCompat.getColor(mContext, R.color.blue_word));
//                        } else
//                            ToastUtils.showToast(mContext, getResources().getString(R.string.show_loss) + " " + (mAppViewModel.getRoulette01().getWonMoney() + "").substring(1, (mAppViewModel.getRoulette01().getWonMoney() + "").length()), Color.RED);
                        if (mAppViewModel.getRoulette01().getWonMoney() > 0) {
                            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 7, componentFront, mContext, mAppViewModel.getFrontVolume());
                            ToastUtils.showWinningToast(mContext, getResources().getString(R.string.show_win) + " " + mAppViewModel.getRoulette01().getWonMoney(), ContextCompat.getColor(mContext, R.color.gold));
                        }
                    }
                    //清除所有的下注的筹码
                    clearAllChips();

                    break;
                case HandlerCode.SHOW_BET_SUCCESS:

                    clearBetChip(type);
                    dismissBlockDialog();
                    ToastUtils.showBetSuccessToast(mContext, getResources().getString(R.string.show_bet_sucess) + " " + mAppViewModel.getRoulette01().getRouletteBetInformation().getAllBetMoney());
                    serviceTime.setText(mAppViewModel.getUser().getBalance() + "");
                    break;
                case HandlerCode.SHOW_BET_MONEY:
                    showBetMoney(false);
                    break;
                case HandlerCode.SHOW_BET_ERROR:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.show_bet_error, Toast.LENGTH_LONG).show();
                    clearBetChip(type);
                    break;
                case HandlerCode.SHOW_POPUP_RESULTS_WINDOW:
                    initPopResultsWindows();
                    break;
                case HandlerCode.THREAD_ERROR:
                    dismissBlockDialog();
                    break;

            }
            //

        }
    };

    @Override
    protected void leftClick() {
        mAppViewModel.setbLobby(true);
        mAppViewModel.getRoulette01().setRoadOld("");
        AppTool.activiyJump(mContext, LobbyRouletteActivity.class);
        finish();
    }

    public void startUpdateStatusThread() {
        if (updateStatus == null) {
            bGetStatus = true;

            updateStatus = new UpdateStatus();
            threadStatus = new Thread(updateStatus);
            threadStatus.start();

            updateBetMoney = new UpdateBetMoney();
            threadUpdateBetMoney = new Thread(updateBetMoney);
            threadUpdateBetMoney.start();


//            updateGameNumber = new UpdateGameNumber();
//            threadUpdateGameNumber = new Thread(updateGameNumber);
//            threadUpdateGameNumber.start();

        }
    }

    public void stopUpdateStatusThread() {

        if (updateStatus != null) {
            bGetStatus = false;

            updateStatus = null;
            threadStatus = null;


        }
        mAppViewModel.closeMuzicService(mContext, BackgroudMuzicService.class);
        mAppViewModel.closeMuzicService(mContext, FrontMuzicService.class);

    }

    public void saveBetInformation(String type, String betInfo, boolean isRepeat) {
        if (!"0".equals(betInfo)) {
            if (!betInfo.contains("#"))//说明只有金额
            {
                BetDetail betDetail = new BetDetail();
                betDetail.setMoney(Integer.parseInt(betInfo));
                betDetail.setNumber(type);
                betDetail.setType(type);
                //   Log.i(WebSiteUrl.Tag,"type="+type+",betInfo="+betInfo);
                mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail().add(betDetail);
                if (isRepeat)
                    mAppViewModel.getRoulette01().getRouletteBetRepeatInformation().getBetDetail().add(betDetail);
            } else {
                String strThree[] = betInfo.split("\\|");

                for (int i = 0; i < strThree.length; i++) {

                    String strThreeDetail[] = strThree[i].split("#");
                    if (strThreeDetail != null && strThreeDetail.length == 2) {
                        BetDetail betDetail = new BetDetail();
                        betDetail.setMoney((int) Double.parseDouble(strThreeDetail[1]));
                        betDetail.setNumber(strThreeDetail[0]);
                        betDetail.setType(type);
                        mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail().add(betDetail);
                        if (isRepeat)
                            mAppViewModel.getRoulette01().getRouletteBetRepeatInformation().getBetDetail().add(betDetail);
                    }

                }
            }

        }
    }

    public void initBetInformation(String type) {
        // 清除所有筹码选择信息
        if (type.equals("all")) {
            listBetDetail.clear();
        } else if (listBetDetail != null && listBetDetail.size() > 0) {
            if (type.equals("small")) {
                for (int i = 0; i < listBetDetail.size(); i++) {
                    if (smallList.contains(listBetDetail.get(i).getNumber())) {
                        listBetDetail.remove(i);
                    }
                }
            } else if (type.equals("big")) {
                for (int i = 0; i < listBetDetail.size(); i++) {
                    if (bigList.contains(listBetDetail.get(i).getNumber())) {
                        listBetDetail.remove(i);
                    }
                }

            } else if (type.equals("orphelins")) {
                for (int i = 0; i < listBetDetail.size(); i++) {
                    if (orphelinsList.contains(listBetDetail.get(i).getNumber())) {
                        listBetDetail.remove(i);
                    }
                }

            } else if (type.equals("zero")) {
                for (int i = 0; i < listBetDetail.size(); i++) {
                    if (zeroList.contains(listBetDetail.get(i).getNumber())) {
                        listBetDetail.remove(i);
                    }
                }

            } else {
                for (int i = 0; i < listBetDetail.size(); i++) {
                    if (type.equals(listBetDetail.get(i).getNumber())) {
                        listBetDetail.remove(i);
                        break;
                    }
                }
            }
        }
        int sure = R.mipmap.sureimg;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.noimg;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
    }

    public void initClickCount() {
        for (int i = 0; i < listBetDetail.size(); i++) {
            listBetDetail.get(i).setClickCount(0);
        }
    }

    public void InitButtonClick() {
        flBaccaratParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolbar.getNavigationIcon() != null)
                    displayAll(false);
                else {
                    displayAll(true);
                }
            }
        });
        tvTableBetReplay.setOnClickListener(new ButtonClick());
        tvTableBetSure.setOnClickListener(new ButtonClick());
        tvTableBetCancel.setOnClickListener(new ButtonClick());
    }

    class ButtonClick implements View.OnClickListener {
        public void onClick(View v) {


            int id = v.getId();
            if (id == R.id.tv_table_bet_replay) {
                repeatBet();
            } else if (id == R.id.tv_table_bet_sure) {
                bet();
            } else if (id == R.id.tv_table_bet_cancel) {
                if (listBetDetail.size() > 0)
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
                clearBetChip("all");
            }
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        L.e(
                ",orientationAuto:" + Configuration.ORIENTATION_LANDSCAPE);
        initSize();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

    }

    private void initSize() {
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            AutoLayoutConifg.getInstance().setSize(this);
        }
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        rouletteNumberTv.setVisibility(View.VISIBLE);
        changeBetUiTv.setVisibility(View.VISIBLE);
        changeBetUiTv.setBackgroundResource(R.mipmap.roulette_board_new1);
        changeBetUiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fl_roulette_board_bg.getVisibility() == View.VISIBLE && fl_roulette_board_bg_new.getVisibility() == View.INVISIBLE) {
                    fl_roulette_board_bg.setVisibility(View.INVISIBLE);
                    fl_roulette_board_bg_new.setVisibility(View.VISIBLE);
                    changeBetUiTv.setBackgroundResource(R.mipmap.roulette_board);
                } else {
                    fl_roulette_board_bg.setVisibility(View.VISIBLE);
                    fl_roulette_board_bg_new.setVisibility(View.INVISIBLE);
                    changeBetUiTv.setBackgroundResource(R.mipmap.roulette_board_new1);
                }
            }
        });
        fl_vedio_location_parent.post(new Runnable() {
            @Override
            public void run() {
                int height = fl_vedio_location_parent.getHeight();
                ViewGroup.LayoutParams layoutParams = fl_vedio_parent.getLayoutParams();
                layoutParams.height = height;
                fl_vedio_parent.setLayoutParams(layoutParams);
                fl_vedio_parent.setVisibility(View.VISIBLE);
            }
        });
        fl_vedio_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fl_vedio_parent.getHeight() <= fl_vedio_location_parent.getHeight()) {
                    openBigVedio();
                } else {
                    closeBigVedio();
                }
            }
        });
        toolbar.setNavigationIcon(null);
        initControl();

        serviceTime.setText(mAppViewModel.getUser().getBalance() + "");

        rightTv.setTextColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundResource(R.color.transparent);
        setTablePool(lv_pool);
        setInfoData(lv_user_info);
//        setTableBetPool(lv_person_bet_info,1);
        mPreview =  findViewById(R.id.surface);
        setPlayVideo();
        setLeftPanel();
        /*setTableLimit();*/
        setLimit();
        InitButtonClick();
        //路子
        leftPanel1.setOnPanelListener(new Panel.OnPanelListener() {
            @Override
            public void onPanelClosed(Panel panel) {
                showRoad = false;
            }

            @Override
            public void onPanelOpened(Panel panel) {
                //    mAppViewModel.getRoulette01().setRoadOld("");
                showRoad = true;
            }
        });
        leftPanel1.setInterpolator(new BounceInterpolator(EasingType.Type.OUT));
        mAppViewModel.getRoulette01().setRoadOld("");
        btn_game_number.setText(getString(R.string.number) + ":" + mAppViewModel.getRoulette01().getGameNumber());
        tv_game_number01.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
        rouletteNumberTv.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
        initArcMenu(tvMenu, "RL1", 1);
        chipListChoice.add(new ChipBean(R.mipmap.sureimg, "", -1));
        chipListChoice.add(new ChipBean(R.mipmap.noimg, "", -2));
        chipListChoice.add(new ChipBean(R.mipmap.replayimg, "", -3));
        setChip();
        initUI();
        startUpdateStatusThread();
    }

    private void setPlayVideo() {

        videoHelper = new VideoHelper(mContext, mPreview) {
            @Override
            public void doVideoFix() {
//            super.doVideoFix();
            }
        };
        String path = mAppViewModel.getUser().getVideoUrl() + "/" + mAppViewModel.getRoulette01().getVideoUrlIndex() + "/LR01";
//        path = "rtmp://202.36.58.169/live/LR01";
        videoHelper.setPlayUrl(path);
    }

    private void setLeftPanel() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rc_road.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            contentAdapter = new BaseRecyclerAdapter<String>(mContext, new ArrayList<>(Collections.nCopies(22, "10")), R.layout.item_roulette_road_content) {
                @Override
                public void convert(MyRecyclerViewHolder helper, int position, String item) {
                    int height = tv_black_percentage.getHeight();
                    TextView roulette_title_tv = helper.getView(R.id.roulette_title_tv);
                    ViewGroup.LayoutParams layoutParams = roulette_title_tv.getLayoutParams();
                    layoutParams.width = height;
                    layoutParams.height = height;
                    roulette_title_tv.setLayoutParams(layoutParams);
                    switch (item) {
                        case "2":
                        case "4":
                        case "6":
                        case "8":
                        case "10":
                        case "11":
                        case "13":
                        case "15":
                        case "17":
                        case "20":
                        case "22":
                        case "24":
                        case "26":
                        case "28":
                        case "29":
                        case "31":
                        case "33":
                        case "35":
                            helper.setBackgroundRes(R.id.roulette_title_tv, R.drawable.shape_roulette_black_bg);
                            break;
                        case "0":
                            helper.setBackgroundRes(R.id.roulette_title_tv, R.drawable.shape_roulette_zero_bg);
                            break;
                        default:
                            helper.setBackgroundRes(R.id.roulette_title_tv, R.drawable.shape_roulette_red_bg);
                            break;
                    }
                    helper.setText(R.id.roulette_title_tv, item);
                }
            };
            rc_road.setAdapter(contentAdapter);
        } else {
            if (mAppViewModel.getRoulette01().getRoad() != null && !mAppViewModel.getRoulette01().getRoad().equals(""))
                mAppViewModel.showRoulette(mAppViewModel.getRoulette01().getRoad(), mContext, bigGradLayout, 6, ScreenUtil.getDisplayMetrics(mContext).density);
        }
    }

    private void setLiveList() {
        AdapterViewContent<LiveInfoBean> contentLive = new AdapterViewContent<>(mContext, lv_pool);
        contentLive.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_live_info3;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                helper.setText(R.id.tv_type, item.getType());
                helper.setText(R.id.tv_value2, item.getValue1());
            }
        });
        List<LiveInfoBean> list = new ArrayList<>(Collections.nCopies(9, new LiveInfoBean("Max/Min", "1222", "")));
        contentLive.setData(list);
    }

    public void setLimit() {
        AdapterViewContent<LiveInfoBean> contentList = new AdapterViewContent<>(mContext, lvLimit);
        contentList.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_live_info3;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                helper.setText(R.id.tv_type, item.getType());

                helper.setText(R.id.tv_value, item.getValue1() + "  -  " + item.getValue2());
                helper.setTextColor(R.id.tv_value, getResources().getColor(R.color.yellow_gold));
                helper.setVisibility(R.id.tv_value2, View.GONE);
            }
        });
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        String data = "";
        //   Log.i(WebSiteUrl.Tag, "GetBankerPool = " + mAppViewModel.getTableId());
        LiveInfoBean pool_max_min = new LiveInfoBean(getString(R.string.min_max), "0", "");
        int index = mAppViewModel.getRoulette01().getLimitIndex();
        int minTotal = mAppViewModel.getRoulette01().getRouletteLimit(index).getMinTotalBet();
        int maxTotal = mAppViewModel.getRoulette01().getRouletteLimit(index).getMaxTotalBet();
        pool_max_min.setValue("" + mAppViewModel.covertLimit(minTotal), mAppViewModel.covertLimit(maxTotal));
        strData.add(pool_max_min);

        LiveInfoBean pool_single = new LiveInfoBean(getString(R.string.single), "0", "");
        pool_single.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet()));
        strData.add(pool_single);

        LiveInfoBean pool_split = new LiveInfoBean(getString(R.string.split), "0", "");
        pool_split.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet()));
        strData.add(pool_split);

        LiveInfoBean pool_street = new LiveInfoBean(getString(R.string.street), "0", "");
        pool_street.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet()));
        strData.add(pool_street);

        LiveInfoBean pool_corner = new LiveInfoBean(getString(R.string.corner), "0", "");
        pool_corner.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet()));
        strData.add(pool_corner);

        LiveInfoBean pool_line = new LiveInfoBean(getString(R.string.line), "0", "");
        pool_line.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet()));
        strData.add(pool_line);

        LiveInfoBean pool_colume = new LiveInfoBean(getString(R.string.colume), "0", "");
        pool_colume.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet()));
        strData.add(pool_colume);
        LiveInfoBean pool_dozen = new LiveInfoBean(getString(R.string.dozen), "0", "");
        pool_dozen.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet()));
        strData.add(pool_dozen);
        LiveInfoBean pool_even = new LiveInfoBean(getString(R.string.even_lp), "0", "");
        pool_even.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet()));
        strData.add(pool_even);


        //   List<LiveInfoBean> list = Collections.nCopies(9, new LiveInfoBean("Max/Min", "1222"));
        contentList.setData(strData);

        btnTableLimitRedTitle.setText("RL1:" + getString(R.string.POOL));
    }

    ImageView currentSure;
    ImageView currentCancel;

    public void setChip() {
        final AdapterViewContent<ChipBean> chips = new AdapterViewContent<>(mContext, lvBaccaratChips);
        chips.setBaseAdapter(new QuickAdapterImp<ChipBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_image_chip;
            }

            @Override
            public void convert(final ViewHolder helper, ChipBean item, final int position) {
                final LinearLayout llParent = helper.retrieveView(R.id.ll_chip_parent);
                ImageView imgChip = helper.retrieveView(R.id.iv_chip_pic);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (position == 0) {
                        llParent.post(new Runnable() {
                            @Override
                            public void run() {
                                int width = ScreenUtil.dip2px(mContext, 48) * chipListChoice.size();
                                int screenWidth = WidgetUtil.getScreenWidth(RouletteActivity.this);
                                int padding = (screenWidth - width) / 2;
                                if (padding > 0) {
                                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) lvBaccaratChips.getLayoutParams();
                                    layoutParams1.leftMargin = padding;
                                    lvBaccaratChips.setLayoutParams(layoutParams1);
                                }
                            }
                        });
                    }
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                    layoutParams.width = ScreenUtil.dip2px(mContext, 40);
                    layoutParams.height = ScreenUtil.dip2px(mContext, 40);
                    layoutParams.leftMargin = ScreenUtil.dip2px(mContext, 3);
                    layoutParams.rightMargin = ScreenUtil.dip2px(mContext, 3);
                    llParent.setLayoutParams(layoutParams);
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                    layoutParams1.width = ScreenUtil.dip2px(mContext, 40);
                    layoutParams1.height = ScreenUtil.dip2px(mContext, 40);
                    imgChip.setLayoutParams(layoutParams1);
                }
                if (selectedMap.get(true) != null && position == selectedMap.get(true).intValue()) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                    layoutParams.width = (int) (layoutParams.width * 1.2);
                    layoutParams.height = (int) (layoutParams.height * 1.2);
                    imgChip.setLayoutParams(layoutParams);
                    helper.setBackgroundRes(R.id.ll_chip_parent, R.drawable.rectangle_trans_stroke_yellow);
                } else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                    layoutParams.width = ScreenUtil.dip2px(mContext, 40);
                    layoutParams.height = ScreenUtil.dip2px(mContext, 40);
                    imgChip.setLayoutParams(layoutParams);
                }
                imgChip.setBackgroundResource(item.getDrawableRes());
                helper.setText(R.id.tv_chip_amount, item.getName());
                if (item.getValue() == -1) {
                    currentSure = imgChip;
                } else if (item.getValue() == -2) {
                    currentCancel = imgChip;
                }
            }
        });
        chips.setItemClick(new ItemCLickImp<ChipBean>() {
            @Override
            public void itemCLick(View view, ChipBean chipBean, int position) {
                if (chipBean.getValue() > 0) {
                    selectedMap.put(true, position);
                    chips.notifyDataSetChanged();
                    chooseChip = chipBean.getValue();
                    initClickCount();
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 8, componentFront, mContext, mAppViewModel.getFrontVolume());
                } else {
                    switch (chipBean.getValue()) {
                        case -1:
                            bet();
                            break;
                        case -2:
                            if (listBetDetail.size() > 0)
                                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
                            clearBetChip("all");
                            break;
                        case -3:
                            repeatBet();
                            break;
                    }
                }
            }
        });
        chips.setData(chipListChoice);
    }


    private void setTablePool(ListView listView) {

        if (contentPool == null)
            contentPool = new AdapterViewContent<>(mContext, lv_pool);
        contentPool.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_user_info_roulette;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.tv_name);
                TextView tvValue = helper.retrieveView(R.id.tv_value);
                tvType.setText(item.getType());
                tvValue.setText(item.getValue1());
                tvType.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.gold));
            }
        });

        contentPool.setData(getPoolData());
    }

    public void updateTablePool() {
        if (lv_pool != null && View.VISIBLE == lv_pool.getVisibility() && contentPool != null) {
            contentPool.setData(getPoolData());
            contentPool.notifyDataSetChanged();
        }

    }

    public void updateInfo() {
        if (View.VISIBLE == lv_user_info.getVisibility()) {
            contentInfo.setData(updateInfoData());
            contentInfo.notifyDataSetChanged();
        }

    }

    private void setListDataTest(ListView lv, List<String> data) {
        AdapterViewContent<String> contentAdapter = new AdapterViewContent<>(mContext, lv);
        contentAdapter.setBaseAdapter(new QuickAdapterImp<String>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_limit_red_text;
            }

            @Override
            public void convert(ViewHolder helper, String item, int position) {
                TextView tv = helper.retrieveView(R.id.text_tv1);
                tv.setTextSize(12);
                tv.setText(item);
                tv.setTextColor(getResources().getColor(R.color.yellow_brown_white_word));
            }
        });
        if (data == null)
            contentAdapter.setData(new ArrayList<String>(Arrays.asList("BANKEER:500", "PLAYER:123", "BANKEER:500", "TIE:500", "BP:500", "PP:500")));
        else {
            contentAdapter.setData(data);
        }
    }

    public List<LiveInfoBean> getPoolData() {
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        String data = "";
        //   Log.i(WebSiteUrl.Tag, "GetBankerPool = " + mAppViewModel.getTableId());
        LiveInfoBean pool_max_min = new LiveInfoBean(getString(R.string.min_max), "0", "");
        pool_max_min.setValue("" + (mAppViewModel.getRoulette01().getRoulettePool().getNumber()
                + mAppViewModel.getRoulette01().getRoulettePool().getColumn() + mAppViewModel.getRoulette01().getRoulettePool().getCorner()
                + mAppViewModel.getRoulette01().getRoulettePool().getDozen() + mAppViewModel.getRoulette01().getRoulettePool().getEven()
                + mAppViewModel.getRoulette01().getRoulettePool().getLine() + mAppViewModel.getRoulette01().getRoulettePool().getRed_black_odd_even_big_small()
                + mAppViewModel.getRoulette01().getRoulettePool().getSplit() + mAppViewModel.getRoulette01().getRoulettePool().getStreet()), "");
        strData.add(pool_max_min);

        LiveInfoBean pool_single = new LiveInfoBean(getString(R.string.single), "0", "");
        pool_single.setValue("" + mAppViewModel.getRoulette01().getRoulettePool().getNumber(), "");
        strData.add(pool_single);

        LiveInfoBean pool_split = new LiveInfoBean(getString(R.string.split), "0", "");
        pool_split.setValue("" + mAppViewModel.getRoulette01().getRoulettePool().getSplit(), "");
        strData.add(pool_split);

        LiveInfoBean pool_street = new LiveInfoBean(getString(R.string.street), "0", "");
        pool_street.setValue("" + mAppViewModel.getRoulette01().getRoulettePool().getStreet(), "");
        strData.add(pool_street);

        LiveInfoBean pool_corner = new LiveInfoBean(getString(R.string.corner), "0", "");
        pool_corner.setValue("" + mAppViewModel.getRoulette01().getRoulettePool().getCorner(), "");
        strData.add(pool_corner);

        LiveInfoBean pool_line = new LiveInfoBean(getString(R.string.line), "0", "");
        pool_line.setValue("" + mAppViewModel.getRoulette01().getRoulettePool().getLine(), "");
        strData.add(pool_line);

        LiveInfoBean pool_colume = new LiveInfoBean(getString(R.string.colume), "0", "");
        pool_colume.setValue("" + mAppViewModel.getRoulette01().getRoulettePool().getColumn(), "");
        strData.add(pool_colume);
        LiveInfoBean pool_dozen = new LiveInfoBean(getString(R.string.dozen), "0", "");
        pool_dozen.setValue("" + mAppViewModel.getRoulette01().getRoulettePool().getDozen(), "");
        strData.add(pool_dozen);
        LiveInfoBean pool_even = new LiveInfoBean(getString(R.string.even_lp), "0", "");
        pool_even.setValue("" + mAppViewModel.getRoulette01().getRoulettePool().getRed_black_odd_even_big_small(), "");
        strData.add(pool_even);


        return strData;
    }

    public void initUI() {
        rouletteTimer = 0;
        mAppViewModel.getRoulette01().setTimer(0);
        gameNumber = "0";
        tv_table_timer.setText("0");
        tv_game_number01.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
        rouletteNumberTv.setText("RL1:" + mAppViewModel.getRoulette01().getGameNumber());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_roulette_bet_game2;
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoHelper.pauseVideo();
//        stopUpdateStatusThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSize();
//        initUI();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());
            }
        }, 1000);
//        startUpdateStatusThread();
        videoHelper.loadVideo();
    }





    public void clickSplit(View v) {
        showBetTypeList(v, 2);
    }

    public void clickStreet(View v) {
        showBetTypeList(v, 3);
    }

    public void clickCorner(View v) {
        showBetTypeList(v, 4);
    }

    public void clickLine(View v) {
        showBetTypeList(v, 6);
    }

    public void showBetTypeList(View v, final int type) {
        List<BetTypeRow> rows = new ArrayList<>();
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        if (type == 2) {
            width = ScreenUtil.dip2px(mContext, 60);
            rows.add(new BetTypeRow(Arrays.asList(0, 1)));
            rows.add(new BetTypeRow(Arrays.asList(0, 2)));
            for (int i = 0; i < 34; i++) {
                rows.add(new BetTypeRow(Arrays.asList(i, i + 3)));
            }

            for (int i = 1; i < 36; i++) {
                if (i % 3 == 0) {
                    continue;
                }
                rows.add(new BetTypeRow(Arrays.asList(i, i + 1)));
            }
        } else if (type == 3) {
            width = ScreenUtil.dip2px(mContext, 90);
            rows.add(new BetTypeRow(Arrays.asList(0, 1, 2)));
            rows.add(new BetTypeRow(Arrays.asList(0, 2, 3)));
            for (int i = 0; i < 12; i++) {
                int n = i * 3 + 1;
                rows.add(new BetTypeRow(Arrays.asList(n, n + 1, n + 2)));
            }
        } else if (type == 4) {
            width = ScreenUtil.dip2px(mContext, 120);
            rows.add(new BetTypeRow(Arrays.asList(0, 1, 2, 3, 4)));
            for (int i = 1; i < 33; i++) {
                if (i % 3 == 0) {
                    continue;
                }
                rows.add(new BetTypeRow(Arrays.asList(i, i + 1, i + 3, i + 4)));
            }
        } else if (type == 6) {
            width = ScreenUtil.dip2px(mContext, 180);
            for (int i = 0; i < 11; i++) {
                int n = i * 3 + 1;
                rows.add(new BetTypeRow(Arrays.asList(n, n + 1, n + 2, n + 3, n + 4, n + 5)));
            }
        }
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        AbsListPopupWindow<BetTypeRow> rowsPopup = new AbsListPopupWindow<BetTypeRow>(mContext, v, width, height) {
            @Override
            protected void popItemCLick(BetTypeRow betTypeRow, int position) {

            }

            @Override
            protected void convertItem(ViewHolder helper, BetTypeRow item, int position) {
                if (type == 2) {
                    helper.setText(R.id.roulette_bet_type_tv1, item.getNumbers().get(0).toString());
                    helper.setText(R.id.roulette_bet_type_tv2, item.getNumbers().get(1).toString());
                    helper.setVisible(R.id.roulette_bet_type_tv3, false);
                    helper.setVisible(R.id.roulette_bet_type_tv4, false);
                    helper.setVisible(R.id.roulette_bet_type_tv5, false);
                    helper.setVisible(R.id.roulette_bet_type_tv6, false);
                }
                if (type == 3) {
                    helper.setText(R.id.roulette_bet_type_tv1, item.getNumbers().get(0).toString());
                    helper.setText(R.id.roulette_bet_type_tv2, item.getNumbers().get(1).toString());
                    helper.setText(R.id.roulette_bet_type_tv3, item.getNumbers().get(2).toString());
                    helper.setVisible(R.id.roulette_bet_type_tv4, false);
                    helper.setVisible(R.id.roulette_bet_type_tv5, false);
                    helper.setVisible(R.id.roulette_bet_type_tv6, false);
                }
                if (type == 4) {
                    helper.setText(R.id.roulette_bet_type_tv1, item.getNumbers().get(0).toString());
                    helper.setText(R.id.roulette_bet_type_tv2, item.getNumbers().get(1).toString());
                    helper.setText(R.id.roulette_bet_type_tv3, item.getNumbers().get(2).toString());
                    helper.setText(R.id.roulette_bet_type_tv4, item.getNumbers().get(3).toString());
                    helper.setVisible(R.id.roulette_bet_type_tv5, false);
                    helper.setVisible(R.id.roulette_bet_type_tv6, false);
                }
                if (type == 6) {
                    helper.setText(R.id.roulette_bet_type_tv1, item.getNumbers().get(0).toString());
                    helper.setText(R.id.roulette_bet_type_tv2, item.getNumbers().get(1).toString());
                    helper.setText(R.id.roulette_bet_type_tv3, item.getNumbers().get(2).toString());
                    helper.setText(R.id.roulette_bet_type_tv4, item.getNumbers().get(3).toString());
                    helper.setText(R.id.roulette_bet_type_tv5, item.getNumbers().get(4).toString());
                    helper.setText(R.id.roulette_bet_type_tv6, item.getNumbers().get(5).toString());
                }
            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.item_roulette_bet_type_row;
            }

            @Override
            protected int getListViewId() {
                return R.id.list_content_lv;
            }

            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.layout_bet_type_rows;
            }
        };
        rowsPopup.setData(rows);
        rowsPopup.showPopupGravityWindow(Gravity.LEFT, 10, 10);
    }

    public void updateRoad(Roulette roulette
            , TextView tv_roulette_number, TextView tv_red, TextView tv_black, TextView tv_zero, TextView tv_even, TextView tv_odd, TextView tv_big, TextView tv_small) {
        if (roulette.getStatus() == 0)//清除路子信息
        {


            tv_roulette_number.setText("0");
            tv_red.setText("0");
            tv_black.setText("0");
            tv_zero.setText("0");

            //需要显示关桌
        } else {//显示路子
            if (roulette.getRoad() != null && !roulette.getRoad().equals(roulette.getRoadOld())) {

                roulette.setRoadOld(roulette.getRoad());
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    mAppViewModel.showRoulette(roulette.getRoad(), mContext, bigGradLayout, 6, ScreenUtil.getDisplayMetrics(mContext).density);
                } else {
                    String roadDetail[] = roulette.getRoad().split("\\#");
                    if (roadDetail != null) {
                        List<String> listItem = new ArrayList<String>();
                        int n = roadDetail.length;
                        for (int i = n - 1; i >= n - 22; i--) {
                            listItem.add(roadDetail[i]);
                        }
                        if (contentAdapter != null && listItem != null) {
                            contentAdapter.addAllAndClear(listItem);
                        }
                    }
                }
                //      Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+roulette.getTableName()+",Luzi roads="+roulette.getRoadOld()+ ",Road="+roulette.getRoad()+",roadDetail="+roadDetail.length);
                //更新局数
                updateGameNumber(mAppViewModel.getRoulette01(), tv_roulette_number, tv_roulette_red01, tv_roulette_black01, tv_roulette_zero01, tv_roulette_even01, tv_roulette_odd01, tv_roulette_big01, tv_roulette_small01);
                updatePercentage();
            }

        }
    }


    public void updateGameNumber(Roulette roulette, TextView tv_roulette_number, TextView tv_red, TextView tv_black, TextView tv_zero, TextView tv_even, TextView tv_odd, TextView tv_big, TextView tv_small) {
        if (roulette.getStatus() == 0) {
            tv_roulette_number.setText("0");
            tv_red.setText("0");
            tv_black.setText("0");
            tv_zero.setText("0");
            tv_even.setText("0");
            tv_odd.setText("0");
            tv_big.setText("0");
            tv_small.setText("0");
        } else {
            roulette.getTotal(roulette.getRoad());
            if (tv_roulette_number != null) {
                tv_roulette_number.setText("" + roulette.getGameNumber());
            }
            if (tv_red != null) {
                tv_red.setText("" + roulette.getRed());
            }
            if (tv_black != null) {
                tv_black.setText("" + roulette.getBlack());
            }
            if (tv_zero != null) {
                tv_zero.setText("" + roulette.getZero());
            }
            if (tv_even != null) {
                tv_even.setText("" + roulette.getEven());
            }
            if (tv_odd != null) {
                tv_odd.setText("" + roulette.getOdd());
            }
            if (tv_big != null) {
                tv_big.setText("" + roulette.getBig());
            }
            if (tv_small != null) {
                tv_small.setText("" + roulette.getSmall());
            }
        }

    }

    public void initControl() {
        serviceTime.setTextSize(15);
        tv_game_number01 = (TextView) findViewById(R.id.tv_table_game_number);
        tv_game_number = (TextView) findViewById(R.id.text_shoe_game_number1);
        tv_roulette_red01 = (TextView) findViewById(R.id.text_red);
        tv_roulette_black01 = (TextView) findViewById(R.id.text_black);
        tv_roulette_zero01 = (TextView) findViewById(R.id.text_zero);
        tv_roulette_even01 = (TextView) findViewById(R.id.text_even);
        tv_roulette_odd01 = (TextView) findViewById(R.id.text_odd);
        tv_roulette_big01 = (TextView) findViewById(R.id.text_big);
        tv_roulette_small01 = (TextView) findViewById(R.id.text_small);
        btn_game_number = (TextView) findViewById(R.id.btn_table_pool_title);
        imgList.clear();
        imgList.add(ivNumber00);
        imgList.add(ivNumber00New);
        imgList.add(ivNumber01);
        imgList.add(ivNumber01New);
        imgList.add(ivNumber02);
        imgList.add(ivNumber02New);
        imgList.add(ivNumber03);
        imgList.add(ivNumber03New);
        imgList.add(ivNumber04);
        imgList.add(ivNumber04New);
        imgList.add(ivNumber05);
        imgList.add(ivNumber05New);
        imgList.add(ivNumber06);
        imgList.add(ivNumber06New);
        imgList.add(ivNumber07);
        imgList.add(ivNumber07New);
        imgList.add(ivNumber08);
        imgList.add(ivNumber08New);
        imgList.add(ivNumber09);
        imgList.add(ivNumber09New);
        imgList.add(ivNumber10);
        imgList.add(ivNumber10New);
        imgList.add(ivNumber11);
        imgList.add(ivNumber11New);
        imgList.add(ivNumber12);
        imgList.add(ivNumber12New);
        imgList.add(ivNumber13);
        imgList.add(ivNumber13New);
        imgList.add(ivNumber14);
        imgList.add(ivNumber14New);
        imgList.add(ivNumber15);
        imgList.add(ivNumber15New);
        imgList.add(ivNumber16);
        imgList.add(ivNumber16New);
        imgList.add(ivNumber17);
        imgList.add(ivNumber17New);
        imgList.add(ivNumber18);
        imgList.add(ivNumber18New);
        imgList.add(ivNumber19);
        imgList.add(ivNumber19New);
        imgList.add(ivNumber20);
        imgList.add(ivNumber20New);
        imgList.add(ivNumber21);
        imgList.add(ivNumber21New);
        imgList.add(ivNumber22);
        imgList.add(ivNumber22New);
        imgList.add(ivNumber23);
        imgList.add(ivNumber23New);
        imgList.add(ivNumber24);
        imgList.add(ivNumber24New);
        imgList.add(ivNumber25);
        imgList.add(ivNumber25New);
        imgList.add(ivNumber26);
        imgList.add(ivNumber26New);
        imgList.add(ivNumber27);
        imgList.add(ivNumber27New);
        imgList.add(ivNumber28);
        imgList.add(ivNumber28New);
        imgList.add(ivNumber29);
        imgList.add(ivNumber29New);
        imgList.add(ivNumber30);
        imgList.add(ivNumber30New);
        imgList.add(ivNumber31);
        imgList.add(ivNumber31New);
        imgList.add(ivNumber32);
        imgList.add(ivNumber32New);
        imgList.add(ivNumber33);
        imgList.add(ivNumber33New);
        imgList.add(ivNumber34);
        imgList.add(ivNumber34New);
        imgList.add(ivNumber35);
        imgList.add(ivNumber35New);
        imgList.add(ivNumber36);
        imgList.add(ivNumber36New);
        imgList.add(iv1row1x2);
        imgList.add(iv2row1x2);
        imgList.add(iv3row1x2);
        imgList.add(ivDozen1);
        imgList.add(ivDozen2);
        imgList.add(ivDozen3);
        imgList.add(ivRed);
        imgList.add(ivBlack);
        imgList.add(ivLow);
        imgList.add(ivHigh);
        imgList.add(ivOdd);
        imgList.add(ivEven);

        llCenter.setVisibility(View.VISIBLE);

        llCenter.setVisibility(View.VISIBLE);
    }

    public boolean checkChoose() {
        if (chooseChip < 1) {
            Toast.makeText(getApplicationContext(), getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void clickChipBet(String number, String typebet, int chooseChip, int minLimit, int maxLimit,
                             int alreadyBet, int totalbet, FrameLayout fl_chip) {
        if (checkChoose()) return;
        if (mAppViewModel.getRoulette01().getGameStatus() != 1)
            return;
        Log.i(WebSiteUrl.Tag, "alreadyBet = " + alreadyBet);
        boolean isIn = false;
        boolean showBefore = false;
        int betMoney = 0;
        for (int i = 0; i < listBetDetail.size(); i++) {//连续点击，还未下注
            if (number.equals(listBetDetail.get(i).getNumber())) {
                isIn = true;
                listBetDetail.get(i).setClickCount(listBetDetail.get(i).getClickCount() + 1);
                betMoney = mAppViewModel.getBetMoney(chooseChip, minLimit,
                        maxLimit, listBetDetail.get(i).getClickCount(),
                        alreadyBet, totalbet, mContext, componentFront);
                if (betMoney > 0) {
                    listBetDetail.get(i).setMoney(betMoney);
                } else {
                    listBetDetail.remove(i);
                    showBefore = true;
                }
                break;
            }
        }
        if (!isIn) {
            BetDetail betDetail = new BetDetail();
            betDetail.setType(typebet);
            betDetail.setNumber(number);
            betDetail.setClickCount(1);

            betMoney = mAppViewModel.getBetMoney(chooseChip, minLimit,
                    maxLimit, 1,
                    alreadyBet, totalbet, mContext, componentFront);
            betDetail.setMoney(betMoney);
            if (betMoney > 0) {
                listBetDetail.add(betDetail);
            } else
                showBefore = true;
        }
        if (showBefore) {
            showBetChip(fl_chip, true, alreadyBet, false);
            //超过了最大的值，要提醒
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        } else {
            showBetChip(fl_chip, true, betMoney, true);
        }


    }

    public void bet() {
        if (mAppViewModel.getRoulette01().getGameStatus() == 2 || mAppViewModel.getRoulette01().getGameStatus() == 5)
            return;
        if (mAppViewModel.getUser().getBalance() <= 0) {
            ToastUtils.showToast(mContext, getString(R.string.Insufficient));
            return;
        }
        if (listBetDetail.size() > 0) {
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 10, componentFront, mContext, mAppViewModel.getFrontVolume());
            rouletteBet = new RouletteBet("all");
            threadRouletteBet = new Thread(rouletteBet);
//            canBet = false;
            showBlockDialog();
            Executors.newSingleThreadExecutor().execute(threadRouletteBet);
        }
    }

    volatile boolean isCanbet = true;

    public void singleBet(String type) {
        if (mAppViewModel.getRoulette01().getGameStatus() == 2 || mAppViewModel.getRoulette01().getGameStatus() == 5)
            return;
        if (mAppViewModel.getUser().getBalance() <= 0) {
            ToastUtils.showToast(mContext, getString(R.string.Insufficient));
            return;
        }
        if (listBetDetail.size() > 0) {
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 10, componentFront, mContext, mAppViewModel.getFrontVolume());
            rouletteBet = new RouletteBet(type);
            threadRouletteBet = new Thread(rouletteBet);
//            canBet = false;
            showBlockDialog();
            Executors.newSingleThreadExecutor().execute(threadRouletteBet);
        }
    }

    public void repeatBet() {
        if (bBetSucess == false)//当前局还没有下注的时候允许重复下注
        {
            showBetMoney(true);


        }
    }

    public String getType(FrameLayout fl) {
        if (fl_roulette_board_bg.getVisibility() == View.VISIBLE && fl_roulette_board_bg_new.getVisibility() == View.INVISIBLE) {
            if (fl.equals(flNumber00)) {
                return "00";
                //显示筹"00" 码
            } else if (fl.equals(flNumber01)) {
                return "01";
            } else if (fl.equals(flNumber02)) {
                return "02";
            } else if (fl.equals(flNumber03)) {
                return "03";
            } else if (fl.equals(flNumber04)) {
                return "04";
            } else if (fl.equals(flNumber05)) {
                return "05";
            } else if (fl.equals(flNumber06)) {
                return "06";
            } else if (fl.equals(flNumber07)) {
                return "07";
            } else if (fl.equals(flNumber08)) {
                return "08";
            } else if (fl.equals(flNumber09)) {
                return "09";
            } else if (fl.equals(flNumber10)) {
                return "10";
            } else if (fl.equals(flNumber11)) {
                return "11";
            } else if (fl.equals(flNumber12)) {
                return "12";
            } else if (fl.equals(flNumber13)) {
                return "13";
            } else if (fl.equals(flNumber14)) {
                return "14";
            } else if (fl.equals(flNumber15)) {
                return "15";
            } else if (fl.equals(flNumber16)) {
                return "16";
            } else if (fl.equals(flNumber17)) {
                return "17";
            } else if (fl.equals(flNumber18)) {
                return "18";
            } else if (fl.equals(flNumber19)) {
                return "19";
            } else if (fl.equals(flNumber20)) {
                return "20";
            } else if (fl.equals(flNumber21)) {
                return "21";
            } else if (fl.equals(flNumber22)) {
                return "22";
            } else if (fl.equals(flNumber23)) {
                return "23";
            } else if (fl.equals(flNumber24)) {
                return "24";
            } else if (fl.equals(flNumber25)) {
                return "25";
            } else if (fl.equals(flNumber26)) {
                return "26";
            } else if (fl.equals(flNumber27)) {
                return "27";
            } else if (fl.equals(flNumber28)) {
                return "28";
            } else if (fl.equals(flNumber29)) {
                return "29";
            } else if (fl.equals(flNumber30)) {
                return "30";
            } else if (fl.equals(flNumber31)) {
                return "31";
            } else if (fl.equals(flNumber32)) {
                return "32";
            } else if (fl.equals(flNumber33)) {
                return "33";
            } else if (fl.equals(flNumber34)) {
                return "34";
            } else if (fl.equals(flNumber35)) {
                return "35";
            } else if (fl.equals(flNumber36)) {
                return "36";
            } else if (fl.equals(flRed))
                return "RedBet";
            else if (fl.equals(flBlack))
                return "BlackBet";
            else if (fl.equals(flHigh))
                return "HightBet";
            else if (fl.equals(flLow))
                return "LowBet";
            else if (fl.equals(flOdd))
                return "OddBet";
            else if (fl.equals(flEven))
                return "EvenBet";
            else {
                return "all";
            }
        } else {
            if (fl.equals(flNumber00New)) {
                return "00";
                //显示筹"00" 码
            } else if (fl.equals(flNumber01New)) {
                return "01";
            } else if (fl.equals(flNumber02New)) {
                return "02";
            } else if (fl.equals(flNumber03New)) {
                return "03";
            } else if (fl.equals(flNumber04New)) {
                return "04";
            } else if (fl.equals(flNumber05New)) {
                return "05";
            } else if (fl.equals(flNumber06New)) {
                return "06";
            } else if (fl.equals(flNumber07New)) {
                return "07";
            } else if (fl.equals(flNumber08New)) {
                return "08";
            } else if (fl.equals(flNumber09New)) {
                return "09";
            } else if (fl.equals(flNumber10New)) {
                return "10";
            } else if (fl.equals(flNumber11New)) {
                return "11";
            } else if (fl.equals(flNumber12New)) {
                return "12";
            } else if (fl.equals(flNumber13New)) {
                return "13";
            } else if (fl.equals(flNumber14New)) {
                return "14";
            } else if (fl.equals(flNumber15New)) {
                return "15";
            } else if (fl.equals(flNumber16New)) {
                return "16";
            } else if (fl.equals(flNumber17New)) {
                return "17";
            } else if (fl.equals(flNumber18New)) {
                return "18";
            } else if (fl.equals(flNumber19New)) {
                return "19";
            } else if (fl.equals(flNumber20New)) {
                return "20";
            } else if (fl.equals(flNumber21New)) {
                return "21";
            } else if (fl.equals(flNumber22New)) {
                return "22";
            } else if (fl.equals(flNumber23New)) {
                return "23";
            } else if (fl.equals(flNumber24New)) {
                return "24";
            } else if (fl.equals(flNumber25New)) {
                return "25";
            } else if (fl.equals(flNumber26New)) {
                return "26";
            } else if (fl.equals(flNumber27New)) {
                return "27";
            } else if (fl.equals(flNumber28New)) {
                return "28";
            } else if (fl.equals(flNumber29New)) {
                return "29";
            } else if (fl.equals(flNumber30New)) {
                return "30";
            } else if (fl.equals(flNumber31New)) {
                return "31";
            } else if (fl.equals(flNumber32New)) {
                return "32";
            } else if (fl.equals(flNumber33New)) {
                return "33";
            } else if (fl.equals(flNumber34New)) {
                return "34";
            } else if (fl.equals(flNumber35New)) {
                return "35";
            } else if (fl.equals(flNumber36New)) {
                return "36";
            } else if (fl.equals(flRed))
                return "RedBet";
            else if (fl.equals(flBlack))
                return "BlackBet";
            else if (fl.equals(flHigh))
                return "HightBet";
            else if (fl.equals(flLow))
                return "LowBet";
            else if (fl.equals(flOdd))
                return "OddBet";
            else if (fl.equals(flEven))
                return "EvenBet";
            else {
                return "all";
            }
        }

    }

    public FrameLayout getFrameLayoutOther(FrameLayout fl) {
        if (fl.equals(flNumber00)) {
            return flNumber00New;
        } else if (fl.equals(flNumber00New)) {
            return flNumber00;
        } else if (fl.equals(flNumber01)) {
            return flNumber01New;
        } else if (fl.equals(flNumber01New)) {
            return flNumber01;
        } else if (fl.equals(flNumber02)) {
            return flNumber02New;
        } else if (fl.equals(flNumber02New)) {
            return flNumber02;
        } else if (fl.equals(flNumber03)) {
            return flNumber03New;
        } else if (fl.equals(flNumber03New)) {
            return flNumber03;
        } else if (fl.equals(flNumber04)) {
            return flNumber04New;
        } else if (fl.equals(flNumber04New)) {
            return flNumber04;
        } else if (fl.equals(flNumber05)) {
            return flNumber05New;
        } else if (fl.equals(flNumber05New)) {
            return flNumber05;
        } else if (fl.equals(flNumber06)) {
            return flNumber06New;
        } else if (fl.equals(flNumber06New)) {
            return flNumber06;
        } else if (fl.equals(flNumber07)) {
            return flNumber07New;
        } else if (fl.equals(flNumber07New)) {
            return flNumber07;
        } else if (fl.equals(flNumber08)) {
            return flNumber08New;
        } else if (fl.equals(flNumber08New)) {
            return flNumber08;
        } else if (fl.equals(flNumber09)) {
            return flNumber09New;
        } else if (fl.equals(flNumber09New)) {
            return flNumber09;
        } else if (fl.equals(flNumber10)) {
            return flNumber10New;
        } else if (fl.equals(flNumber10New)) {
            return flNumber10;
        } else if (fl.equals(flNumber11)) {
            return flNumber11New;
        } else if (fl.equals(flNumber11New)) {
            return flNumber11;
        } else if (fl.equals(flNumber12)) {
            return flNumber12New;
        } else if (fl.equals(flNumber12New)) {
            return flNumber12;
        } else if (fl.equals(flNumber13)) {
            return flNumber13New;
        } else if (fl.equals(flNumber13New)) {
            return flNumber13;
        } else if (fl.equals(flNumber14)) {
            return flNumber14New;
        } else if (fl.equals(flNumber14New)) {
            return flNumber14;
        } else if (fl.equals(flNumber15)) {
            return flNumber15New;
        } else if (fl.equals(flNumber15New)) {
            return flNumber15;
        } else if (fl.equals(flNumber16)) {
            return flNumber16New;
        } else if (fl.equals(flNumber16New)) {
            return flNumber16;
        } else if (fl.equals(flNumber17)) {
            return flNumber17New;
        } else if (fl.equals(flNumber17New)) {
            return flNumber17;
        } else if (fl.equals(flNumber18)) {
            return flNumber18New;
        } else if (fl.equals(flNumber18New)) {
            return flNumber18;
        } else if (fl.equals(flNumber19)) {
            return flNumber19New;
        } else if (fl.equals(flNumber19New)) {
            return flNumber19;
        } else if (fl.equals(flNumber20)) {
            return flNumber20New;
        } else if (fl.equals(flNumber20New)) {
            return flNumber20;
        } else if (fl.equals(flNumber21)) {
            return flNumber21New;
        } else if (fl.equals(flNumber21New)) {
            return flNumber21;
        } else if (fl.equals(flNumber22)) {
            return flNumber22New;
        } else if (fl.equals(flNumber22New)) {
            return flNumber22;
        } else if (fl.equals(flNumber23)) {
            return flNumber23New;
        } else if (fl.equals(flNumber23New)) {
            return flNumber23;
        } else if (fl.equals(flNumber24)) {
            return flNumber24New;
        } else if (fl.equals(flNumber24New)) {
            return flNumber24;
        } else if (fl.equals(flNumber25)) {
            return flNumber25New;
        } else if (fl.equals(flNumber25New)) {
            return flNumber25;
        } else if (fl.equals(flNumber26)) {
            return flNumber26New;
        } else if (fl.equals(flNumber26New)) {
            return flNumber26;
        } else if (fl.equals(flNumber27)) {
            return flNumber27New;
        } else if (fl.equals(flNumber27New)) {
            return flNumber27;
        } else if (fl.equals(flNumber28)) {
            return flNumber28New;
        } else if (fl.equals(flNumber28New)) {
            return flNumber28;
        } else if (fl.equals(flNumber29)) {
            return flNumber29New;
        } else if (fl.equals(flNumber29New)) {
            return flNumber29;
        } else if (fl.equals(flNumber30)) {
            return flNumber30New;
        } else if (fl.equals(flNumber30New)) {
            return flNumber30;
        } else if (fl.equals(flNumber31)) {
            return flNumber31New;
        } else if (fl.equals(flNumber31New)) {
            return flNumber31;
        } else if (fl.equals(flNumber32)) {
            return flNumber32New;
        } else if (fl.equals(flNumber32New)) {
            return flNumber32;
        } else if (fl.equals(flNumber33)) {
            return flNumber33New;
        } else if (fl.equals(flNumber33New)) {
            return flNumber33;
        } else if (fl.equals(flNumber34)) {
            return flNumber34New;
        } else if (fl.equals(flNumber34New)) {
            return flNumber34;
        } else if (fl.equals(flNumber35)) {
            return flNumber35New;
        } else if (fl.equals(flNumber35New)) {
            return flNumber35;
        } else if (fl.equals(flNumber36)) {
            return flNumber36New;
        } else if (fl.equals(flNumber36New)) {
            return flNumber36;
        } else {
            return null;
        }
    }

    public FrameLayout getFrameLayout(String type) {
        if (fl_roulette_board_bg.getVisibility() == View.VISIBLE && fl_roulette_board_bg_new.getVisibility() == View.INVISIBLE) {
            switch (type) {
                case "00":
                    //显示筹码
                    return flNumber00;

                case "01":
                    return flNumber01;
                case "02":
                    return flNumber02;
                case "03":
                    return flNumber03;
                case "04":
                    return flNumber04;
                case "05":
                    return flNumber05;
                case "06":
                    return flNumber06;
                case "07":
                    return flNumber07;
                case "08":
                    return flNumber08;
                case "09":
                    return flNumber09;
                case "10":
                    return flNumber10;
                case "11":
                    return flNumber11;
                case "12":
                    return flNumber12;
                case "13":
                    return flNumber13;
                case "14":
                    return flNumber14;
                case "15":
                    return flNumber15;
                case "16":
                    return flNumber16;
                case "17":
                    return flNumber17;
                case "18":
                    return flNumber18;
                case "19":
                    return flNumber19;
                case "20":
                    return flNumber20;
                case "21":
                    return flNumber21;
                case "22":
                    return flNumber22;
                case "23":
                    return flNumber23;
                case "24":
                    return flNumber24;
                case "25":
                    return flNumber25;
                case "26":
                    return flNumber26;
                case "27":
                    return flNumber27;
                case "28":
                    return flNumber28;
                case "29":
                    return flNumber29;
                case "30":
                    return flNumber30;
                case "31":
                    return flNumber31;
                case "32":
                    return flNumber32;
                case "33":
                    return flNumber33;
                case "34":
                    return flNumber34;
                case "35":
                    return flNumber35;
                case "36":
                    return flNumber36;
                case "RedBet":
                    return flRed;
                case "BlackBet":
                    return flBlack;
                case "HightBet":
                    return flHigh;
                case "LowBet":
                    return flLow;
                case "OddBet":
                    return flOdd;
                case "EvenBet":
                    return flEven;
                case "FristRow":
                    return fl3row1x2;
                case "SndRow":
                    return fl2row1x2;
                case "ThrRow":
                    return fl1row1x2;
                case "FristCol":
                    return flDozen1;
                case "SndCol":
                    return flDozen2;
                case "ThrCol":
                    return flDozen3;
                case "FourBet":
                    return flCorner00010203;
                case "0001":
                    return flSplit0001;
                case "0002":
                    return flSplit0002;
                case "0003":
                    return flSplit0003;
                case "0102":
                    return flSplit0102;
                case "0203":
                    return flSplit0203;
                case "0104":
                    return flSplit0104;
                case "0205":
                    return flSplit0205;
                case "0306":
                    return flSplit0306;
                case "0405":
                    return flSplit0405;
                case "0506":
                    return flSplit0506;

                case "0407":
                    return flSplit0407;
                case "0508":
                    return flSplit0508;
                case "0609":
                    return flSplit0609;
                case "0708":
                    return flSplit0708;
                case "0809":
                    return flSplit0809;
                case "0710":
                    return flSplit0710;
                case "0811":
                    return flSplit0811;
                case "0912":
                    return flSplit0912;
                case "1011":
                    return flSplit1011;
                case "1112":
                    return flSplit1112;
                case "1013":
                    return flSplit1013;
                case "1114":
                    return flSplit1114;
                case "1215":
                    return flSplit1215;
                case "1314":
                    return flSplit1314;
                case "1415":
                    return flSplit1415;
                case "1316":
                    return flSplit1316;
                case "1417":
                    return flSplit1417;
                case "1518":
                    return flSplit1518;
                case "1617":
                    return flSplit1617;
                case "1718":
                    return flSplit1718;
                case "1619":
                    return flSplit1619;
                case "1720":
                    return flSplit1720;
                case "1821":
                    return flSplit1821;
                case "1920":
                    return flSplit1920;
                case "2021":
                    return flSplit2021;
                case "1922":
                    return flSplit1922;
                case "2023":
                    return flSplit2023;
                case "2124":
                    return flSplit2124;
                case "2223":
                    return flSplit2223;
                case "2324":
                    return flSplit2324;
                case "2225":
                    return flSplit2225;
                case "2326":
                    return flSplit2326;
                case "2427":
                    return flSplit2427;
                case "2526":
                    return flSplit2526;
                case "2627":
                    return flSplit2627;
                case "2528":
                    return flSplit2528;
                case "2629":
                    return flSplit2629;
                case "2730":
                    return flSplit2730;
                case "2829":
                    return flSplit2829;
                case "2930":
                    return flSplit2930;
                case "2831":
                    return flSplit2831;
                case "2932":
                    return flSplit2932;
                case "3033":
                    return flSplit3033;
                case "3132":
                    return flSplit3132;
                case "3233":
                    return flSplit3233;
                case "3134":
                    return flSplit3134;
                case "3235":
                    return flSplit3235;
                case "3336":
                    return flSplit3336;
                case "3435":
                    return flSplit3435;
                case "3536":
                    return flSplit3536;
                case "000203":
                    return flStreet000203;
                case "000102":
                    return flStreet000102;
                case "010203":
                    return flStreet010203;
                case "040506":
                    return flStreet040506;
                case "070809":
                    return flStreet070809;
                case "101112":
                    return flStreet101112;
                case "131415":
                    return flStreet131415;
                case "161718":
                    return flStreet161718;
                case "192021":
                    return flStreet192021;
                case "222324":
                    return flStreet222324;
                case "252627":
                    return flStreet252627;
                case "282930":
                    return flStreet282930;
                case "313233":
                    return flStreet313233;
                case "343536":
                    return flStreet343536;
                case "01020405":
                    return flCorner01020405;
                case "02030506":
                    return flCorner02030506;
                case "04050708":
                    return flCorner04050708;
                case "05060809":
                    return flCorner05060809;
                case "07081011":
                    return flCorner07081011;
                case "08091112":
                    return flCorner08091112;
                case "10111314":
                    return flCorner10111314;
                case "11121415":
                    return flCorner11121415;
                case "13141617":
                    return flCorner13141617;
                case "14151718":
                    return flCorner14151718;
                case "16171920":
                    return flCorner16171920;
                case "17182021":
                    return flCorner17182021;
                case "19202223":
                    return flCorner19202223;
                case "20212324":
                    return flCorner20212324;
                case "22232526":
                    return flCorner22232526;
                case "23242627":
                    return flCorner23242627;
                case "25262829":
                    return flCorner25262829;
                case "26272930":
                    return flCorner26272930;
                case "28293132":
                    return flCorner28293132;
                case "29303233":
                    return flCorner29303233;
                case "31323435":
                    return flCorner31323435;
                case "32333536":
                    return flCorner32333536;
                case "010203040506":
                    return flLine010203040506;
                case "040506070809":
                    return flLine040506070809;
                case "070809101112":
                    return flLine070809101112;
                case "101112131415":
                    return flLine101112131415;
                case "131415161718":
                    return flLine131415161718;
                case "161718192021":
                    return flLine161718192021;
                case "192021222324":
                    return flLine192021222324;
                case "222324252627":
                    return flLine222324252627;
                case "252627282930":
                    return flLine252627282930;
                case "282930313233":
                    return flLine282930313233;
                case "313233343536":
                    return flLine313233343536;
                default:
                    return null;

            }
        } else {
            switch (type) {
                case "00":
                    //显示筹码
                    return flNumber00New;

                case "01":
                    return flNumber01New;
                case "02":
                    return flNumber02New;
                case "03":
                    return flNumber03New;
                case "04":
                    return flNumber04New;
                case "05":
                    return flNumber05New;
                case "06":
                    return flNumber06New;
                case "07":
                    return flNumber07New;
                case "08":
                    return flNumber08New;
                case "09":
                    return flNumber09New;
                case "10":
                    return flNumber10New;
                case "11":
                    return flNumber11New;
                case "12":
                    return flNumber12New;
                case "13":
                    return flNumber13New;
                case "14":
                    return flNumber14New;
                case "15":
                    return flNumber15New;
                case "16":
                    return flNumber16New;
                case "17":
                    return flNumber17New;
                case "18":
                    return flNumber18New;
                case "19":
                    return flNumber19New;
                case "20":
                    return flNumber20New;
                case "21":
                    return flNumber21New;
                case "22":
                    return flNumber22New;
                case "23":
                    return flNumber23New;
                case "24":
                    return flNumber24New;
                case "25":
                    return flNumber25New;
                case "26":
                    return flNumber26New;
                case "27":
                    return flNumber27New;
                case "28":
                    return flNumber28New;
                case "29":
                    return flNumber29New;
                case "30":
                    return flNumber30New;
                case "31":
                    return flNumber31New;
                case "32":
                    return flNumber32New;
                case "33":
                    return flNumber33New;
                case "34":
                    return flNumber34New;
                case "35":
                    return flNumber35New;
                case "36":
                    return flNumber36New;
                case "RedBet":
                    return flRed;
                case "BlackBet":
                    return flBlack;
                case "HightBet":
                    return flHigh;
                case "LowBet":
                    return flLow;
                case "OddBet":
                    return flOdd;
                case "EvenBet":
                    return flEven;
                case "FristRow":
                    return fl3row1x2;
                case "SndRow":
                    return fl2row1x2;
                case "ThrRow":
                    return fl1row1x2;
                case "FristCol":
                    return flDozen1;
                case "SndCol":
                    return flDozen2;
                case "ThrCol":
                    return flDozen3;
                case "FourBet":
                    return flCorner00010203;
                case "0001":
                    return flSplit0001;
                case "0002":
                    return flSplit0002;
                case "0003":
                    return flSplit0003;
                case "0102":
                    return flSplit0102;
                case "0203":
                    return flSplit0203;
                case "0104":
                    return flSplit0104;
                case "0205":
                    return flSplit0205;
                case "0306":
                    return flSplit0306;
                case "0405":
                    return flSplit0405;
                case "0506":
                    return flSplit0506;

                case "0407":
                    return flSplit0407;
                case "0508":
                    return flSplit0508;
                case "0609":
                    return flSplit0609;
                case "0708":
                    return flSplit0708;
                case "0809":
                    return flSplit0809;
                case "0710":
                    return flSplit0710;
                case "0811":
                    return flSplit0811;
                case "0912":
                    return flSplit0912;
                case "1011":
                    return flSplit1011;
                case "1112":
                    return flSplit1112;
                case "1013":
                    return flSplit1013;
                case "1114":
                    return flSplit1114;
                case "1215":
                    return flSplit1215;
                case "1314":
                    return flSplit1314;
                case "1415":
                    return flSplit1415;
                case "1316":
                    return flSplit1316;
                case "1417":
                    return flSplit1417;
                case "1518":
                    return flSplit1518;
                case "1617":
                    return flSplit1617;
                case "1718":
                    return flSplit1718;
                case "1619":
                    return flSplit1619;
                case "1720":
                    return flSplit1720;
                case "1821":
                    return flSplit1821;
                case "1920":
                    return flSplit1920;
                case "2021":
                    return flSplit2021;
                case "1922":
                    return flSplit1922;
                case "2023":
                    return flSplit2023;
                case "2124":
                    return flSplit2124;
                case "2223":
                    return flSplit2223;
                case "2324":
                    return flSplit2324;
                case "2225":
                    return flSplit2225;
                case "2326":
                    return flSplit2326;
                case "2427":
                    return flSplit2427;
                case "2526":
                    return flSplit2526;
                case "2627":
                    return flSplit2627;
                case "2528":
                    return flSplit2528;
                case "2629":
                    return flSplit2629;
                case "2730":
                    return flSplit2730;
                case "2829":
                    return flSplit2829;
                case "2930":
                    return flSplit2930;
                case "2831":
                    return flSplit2831;
                case "2932":
                    return flSplit2932;
                case "3033":
                    return flSplit3033;
                case "3132":
                    return flSplit3132;
                case "3233":
                    return flSplit3233;
                case "3134":
                    return flSplit3134;
                case "3235":
                    return flSplit3235;
                case "3336":
                    return flSplit3336;
                case "3435":
                    return flSplit3435;
                case "3536":
                    return flSplit3536;
                case "000203":
                    return flStreet000203;
                case "000102":
                    return flStreet000102;
                case "010203":
                    return flStreet010203;
                case "040506":
                    return flStreet040506;
                case "070809":
                    return flStreet070809;
                case "101112":
                    return flStreet101112;
                case "131415":
                    return flStreet131415;
                case "161718":
                    return flStreet161718;
                case "192021":
                    return flStreet192021;
                case "222324":
                    return flStreet222324;
                case "252627":
                    return flStreet252627;
                case "282930":
                    return flStreet282930;
                case "313233":
                    return flStreet313233;
                case "343536":
                    return flStreet343536;
                case "01020405":
                    return flCorner01020405;
                case "02030506":
                    return flCorner02030506;
                case "04050708":
                    return flCorner04050708;
                case "05060809":
                    return flCorner05060809;
                case "07081011":
                    return flCorner07081011;
                case "08091112":
                    return flCorner08091112;
                case "10111314":
                    return flCorner10111314;
                case "11121415":
                    return flCorner11121415;
                case "13141617":
                    return flCorner13141617;
                case "14151718":
                    return flCorner14151718;
                case "16171920":
                    return flCorner16171920;
                case "17182021":
                    return flCorner17182021;
                case "19202223":
                    return flCorner19202223;
                case "20212324":
                    return flCorner20212324;
                case "22232526":
                    return flCorner22232526;
                case "23242627":
                    return flCorner23242627;
                case "25262829":
                    return flCorner25262829;
                case "26272930":
                    return flCorner26272930;
                case "28293132":
                    return flCorner28293132;
                case "29303233":
                    return flCorner29303233;
                case "31323435":
                    return flCorner31323435;
                case "32333536":
                    return flCorner32333536;
                case "010203040506":
                    return flLine010203040506;
                case "040506070809":
                    return flLine040506070809;
                case "070809101112":
                    return flLine070809101112;
                case "101112131415":
                    return flLine101112131415;
                case "131415161718":
                    return flLine131415161718;
                case "161718192021":
                    return flLine161718192021;
                case "192021222324":
                    return flLine192021222324;
                case "222324252627":
                    return flLine222324252627;
                case "252627282930":
                    return flLine252627282930;
                case "282930313233":
                    return flLine282930313233;
                case "313233343536":
                    return flLine313233343536;
                default:
                    return null;
            }
        }

    }

    public void showBetMoney(boolean bRepeat) {
        List<BetDetail> betDetails = null;
        if (bRepeat) {
            listBetDetail.clear();
            betDetails = mAppViewModel.getRoulette01().getRouletteBetRepeatInformation().getBetDetail();
            if (betDetails.size() > 0) {
                int sure = R.mipmap.sureimg_light;
                if (currentSure != null && sure != 0) {
                    currentSure.setBackgroundResource(sure);
                }
                int no = R.mipmap.noimg_light;
                if (currentCancel != null && no != 0) {
                    currentCancel.setBackgroundResource(no);
                }
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
            }
        } else {
            betDetails = mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail();
        }
        for (int i = 0; i < betDetails.size(); i++) {
            if (bRepeat)
                listBetDetail = betDetails;
            showBetChip(getFrameLayout(betDetails.get(i).getNumber()), true, betDetails.get(i).getMoney(), false);
        }
        if (!bRepeat) {
            int sure = R.mipmap.sureimg;
            if (currentSure != null && sure != 0) {
                currentSure.setBackgroundResource(sure);
            }
            int no = R.mipmap.noimg;
            if (currentCancel != null && no != 0) {
                currentCancel.setBackgroundResource(no);
            }
        }

    }

    public void gotoLobby() {
        mAppViewModel.getRoulette01().Init();
        //   mAppViewModel.setTableId(0);
        mAppViewModel.setSerialId(0);
        mAppViewModel.setAreaId(0);
        mAppViewModel.setbLobby(true);
        mAppViewModel.getRoulette01().setRoadOld("");


        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 0);
        AppTool.activiyJump(mContext, LobbyRouletteActivity.class, bundle);
    }

    private void clearBetChip(String type) {
        int sure = R.mipmap.sureimg;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.noimg;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
        if (type.equals("all")) {
            List<BetDetail> betDetails = mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail();
            clearAllChips();
            for (int i = 0; i < betDetails.size(); i++) {
                showBetChip(getFrameLayout(betDetails.get(i).getNumber()), true, betDetails.get(i).getMoney(), false);
            }
        } else {
            if (type.equals("small")) {
                ll_center_small.setVisibility(View.GONE);
                for (String number : smallList) {
                    showBetChipOld(getFrameLayout(number), false, 0);
                }
                List<BetDetail> betDetails = mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail();
                for (int i = 0; i < betDetails.size(); i++) {
                    if (smallList.contains(betDetails.get(i).getNumber())) {
                        showBetChip(getFrameLayout(betDetails.get(i).getNumber()), true, betDetails.get(i).getMoney(), false);
                    }
                }

            } else if (type.equals("orphelins")) {
                ll_center_orphelins.setVisibility(View.GONE);
                for (String number : orphelinsList) {
                    showBetChipOld(getFrameLayout(number), false, 0);
                }
                List<BetDetail> betDetails = mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail();
                for (int i = 0; i < betDetails.size(); i++) {
                    if (orphelinsList.contains(betDetails.get(i).getNumber())) {

                        showBetChip(getFrameLayout(betDetails.get(i).getNumber()), true, betDetails.get(i).getMoney(), false);
                    }
                }

            } else if (type.equals("big")) {
                ll_center_big.setVisibility(View.GONE);
                for (String number : bigList) {
                    showBetChipOld(getFrameLayout(number), false, 0);
                }
                List<BetDetail> betDetails = mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail();
                for (int i = 0; i < betDetails.size(); i++) {
                    if (bigList.contains(betDetails.get(i).getNumber())) {
                        showBetChip(getFrameLayout(betDetails.get(i).getNumber()), true, betDetails.get(i).getMoney(), false);
                    }
                }
                return;
            } else if (type.equals("zero")) {
                ll_zero.setVisibility(View.GONE);
                for (String number : zeroList) {
                    showBetChipOld(getFrameLayout(number), false, 0);
                }
                List<BetDetail> betDetails = mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail();
                for (int i = 0; i < betDetails.size(); i++) {
                    if (zeroList.contains(betDetails.get(i).getNumber())) {
                        showBetChipOld(getFrameLayout(betDetails.get(i).getNumber()), false, 0);
                        showBetChip(getFrameLayout(betDetails.get(i).getNumber()), true, betDetails.get(i).getMoney(), false);
                    }
                }

            } else {
                FrameLayout fl = getFrameLayout(type);
                ChipShowHelper chipHelper = ChipMap.get(fl);
                if (chipHelper != null)
                    chipHelper.setOperationButtonDisplay(false);
                showBetChipOld(fl, false, 0);
                List<BetDetail> betDetails = mAppViewModel.getRoulette01().getRouletteBetInformation().getBetDetail();
                for (int i = 0; i < betDetails.size(); i++) {
                    if (betDetails.get(i) != null && type.equals(betDetails.get(i).getNumber())) {
                        showBetChip(getFrameLayout(betDetails.get(i).getNumber()), true, betDetails.get(i).getMoney(), false);
                        break;
                    }
                }
            }

        }
        initBetInformation(type);
    }

    public void clearAllChips() {
        int sure = R.mipmap.sureimg;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.noimg;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
        showBetChipOld(flNumber00, false, 0);
        showBetChipOld(flNumber00New, false, 0);
        showBetChipOld(flNumber01, false, 0);
        showBetChipOld(flNumber01New, false, 0);
        showBetChipOld(flNumber02, false, 0);
        showBetChipOld(flNumber02New, false, 0);
        showBetChipOld(flNumber03, false, 0);
        showBetChipOld(flNumber03New, false, 0);
        showBetChipOld(flNumber04, false, 0);
        showBetChipOld(flNumber04New, false, 0);
        showBetChipOld(flNumber05, false, 0);
        showBetChipOld(flNumber05New, false, 0);
        showBetChipOld(flNumber06, false, 0);
        showBetChipOld(flNumber06New, false, 0);
        showBetChipOld(flNumber07, false, 0);
        showBetChipOld(flNumber07New, false, 0);
        showBetChipOld(flNumber08, false, 0);
        showBetChipOld(flNumber08New, false, 0);
        showBetChipOld(flNumber09, false, 0);
        showBetChipOld(flNumber09New, false, 0);
        showBetChipOld(flNumber10, false, 0);
        showBetChipOld(flNumber10New, false, 0);
        showBetChipOld(flNumber11, false, 0);
        showBetChipOld(flNumber11New, false, 0);
        showBetChipOld(flNumber12, false, 0);
        showBetChipOld(flNumber12New, false, 0);
        showBetChipOld(flNumber13, false, 0);
        showBetChipOld(flNumber13New, false, 0);
        showBetChipOld(flNumber14, false, 0);
        showBetChipOld(flNumber14New, false, 0);
        showBetChipOld(flNumber15, false, 0);
        showBetChipOld(flNumber15New, false, 0);
        showBetChipOld(flNumber16, false, 0);
        showBetChipOld(flNumber16New, false, 0);
        showBetChipOld(flNumber17, false, 0);
        showBetChipOld(flNumber17New, false, 0);
        showBetChipOld(flNumber18, false, 0);
        showBetChipOld(flNumber18New, false, 0);
        showBetChipOld(flNumber19, false, 0);
        showBetChipOld(flNumber19New, false, 0);
        showBetChipOld(flNumber20, false, 0);
        showBetChipOld(flNumber20New, false, 0);
        showBetChipOld(flNumber21, false, 0);
        showBetChipOld(flNumber21New, false, 0);
        showBetChipOld(flNumber22, false, 0);
        showBetChipOld(flNumber22New, false, 0);
        showBetChipOld(flNumber23, false, 0);
        showBetChipOld(flNumber23New, false, 0);
        showBetChipOld(flNumber24, false, 0);
        showBetChipOld(flNumber24New, false, 0);
        showBetChipOld(flNumber25, false, 0);
        showBetChipOld(flNumber25New, false, 0);
        showBetChipOld(flNumber26, false, 0);
        showBetChipOld(flNumber26New, false, 0);
        showBetChipOld(flNumber27, false, 0);
        showBetChipOld(flNumber27New, false, 0);
        showBetChipOld(flNumber28, false, 0);
        showBetChipOld(flNumber28New, false, 0);
        showBetChipOld(flNumber29, false, 0);
        showBetChipOld(flNumber29New, false, 0);
        showBetChipOld(flNumber30, false, 0);
        showBetChipOld(flNumber30New, false, 0);
        showBetChipOld(flNumber31, false, 0);
        showBetChipOld(flNumber31New, false, 0);
        showBetChipOld(flNumber32, false, 0);
        showBetChipOld(flNumber32New, false, 0);
        showBetChipOld(flNumber33, false, 0);
        showBetChipOld(flNumber33New, false, 0);
        showBetChipOld(flNumber34, false, 0);
        showBetChipOld(flNumber34New, false, 0);
        showBetChipOld(flNumber35, false, 0);
        showBetChipOld(flNumber35New, false, 0);
        showBetChipOld(flNumber36, false, 0);
        showBetChipOld(flNumber36New, false, 0);
        showBetChipOld(flSplit0001, false, 0);
        showBetChipOld(flSplit0002, false, 0);
        showBetChipOld(flSplit0003, false, 0);
        showBetChipOld(flSplit0102, false, 0);
        showBetChipOld(flSplit0203, false, 0);
        showBetChipOld(flSplit0104, false, 0);
        showBetChipOld(flSplit0205, false, 0);
        showBetChipOld(flSplit0306, false, 0);
        showBetChipOld(flSplit0405, false, 0);
        showBetChipOld(flSplit0506, false, 0);
        showBetChipOld(flSplit0407, false, 0);
        showBetChipOld(flSplit0508, false, 0);
        showBetChipOld(flSplit0609, false, 0);
        showBetChipOld(flSplit0708, false, 0);
        showBetChipOld(flSplit0809, false, 0);
        showBetChipOld(flSplit0710, false, 0);
        showBetChipOld(flSplit0811, false, 0);
        showBetChipOld(flSplit0912, false, 0);
        showBetChipOld(flSplit1011, false, 0);
        showBetChipOld(flSplit1112, false, 0);

        showBetChipOld(flSplit1013, false, 0);
        showBetChipOld(flSplit1114, false, 0);
        showBetChipOld(flSplit1215, false, 0);
        showBetChipOld(flSplit1314, false, 0);
        showBetChipOld(flSplit1415, false, 0);

        showBetChipOld(flSplit1316, false, 0);
        showBetChipOld(flSplit1417, false, 0);
        showBetChipOld(flSplit1518, false, 0);
        showBetChipOld(flSplit1617, false, 0);
        showBetChipOld(flSplit1718, false, 0);

        showBetChipOld(flSplit1619, false, 0);
        showBetChipOld(flSplit1720, false, 0);
        showBetChipOld(flSplit1821, false, 0);
        showBetChipOld(flSplit1920, false, 0);
        showBetChipOld(flSplit2021, false, 0);

        showBetChipOld(flSplit1922, false, 0);
        showBetChipOld(flSplit2023, false, 0);
        showBetChipOld(flSplit2124, false, 0);
        showBetChipOld(flSplit2223, false, 0);
        showBetChipOld(flSplit2324, false, 0);

        showBetChipOld(flSplit2225, false, 0);
        showBetChipOld(flSplit2326, false, 0);
        showBetChipOld(flSplit2427, false, 0);
        showBetChipOld(flSplit2526, false, 0);
        showBetChipOld(flSplit2627, false, 0);

        showBetChipOld(flSplit2528, false, 0);
        showBetChipOld(flSplit2629, false, 0);
        showBetChipOld(flSplit2730, false, 0);
        showBetChipOld(flSplit2829, false, 0);
        showBetChipOld(flSplit2930, false, 0);

        showBetChipOld(flSplit2831, false, 0);
        showBetChipOld(flSplit2932, false, 0);
        showBetChipOld(flSplit3033, false, 0);
        showBetChipOld(flSplit3132, false, 0);
        showBetChipOld(flSplit3233, false, 0);

        showBetChipOld(flSplit3134, false, 0);
        showBetChipOld(flSplit3235, false, 0);
        showBetChipOld(flSplit3336, false, 0);
        showBetChipOld(flSplit3435, false, 0);
        showBetChipOld(flSplit3536, false, 0);

        showBetChipOld(flStreet000102, false, 0);
        showBetChipOld(flStreet000203, false, 0);
        showBetChipOld(flStreet010203, false, 0);
        showBetChipOld(flStreet040506, false, 0);
        showBetChipOld(flStreet070809, false, 0);
        showBetChipOld(flStreet101112, false, 0);
        showBetChipOld(flStreet131415, false, 0);
        showBetChipOld(flStreet161718, false, 0);
        showBetChipOld(flStreet192021, false, 0);
        showBetChipOld(flStreet222324, false, 0);
        showBetChipOld(flStreet252627, false, 0);
        showBetChipOld(flStreet282930, false, 0);
        showBetChipOld(flStreet313233, false, 0);
        showBetChipOld(flStreet343536, false, 0);

        showBetChipOld(flCorner00010203, false, 0);
        showBetChipOld(flCorner01020405, false, 0);
        showBetChipOld(flCorner02030506, false, 0);
        showBetChipOld(flCorner04050708, false, 0);
        showBetChipOld(flCorner05060809, false, 0);
        showBetChipOld(flCorner07081011, false, 0);
        showBetChipOld(flCorner08091112, false, 0);
        showBetChipOld(flCorner10111314, false, 0);
        showBetChipOld(flCorner11121415, false, 0);
        showBetChipOld(flCorner13141617, false, 0);
        showBetChipOld(flCorner14151718, false, 0);
        showBetChipOld(flCorner16171920, false, 0);
        showBetChipOld(flCorner17182021, false, 0);
        showBetChipOld(flCorner19202223, false, 0);
        showBetChipOld(flCorner20212324, false, 0);
        showBetChipOld(flCorner22232526, false, 0);
        showBetChipOld(flCorner23242627, false, 0);
        showBetChipOld(flCorner25262829, false, 0);
        showBetChipOld(flCorner26272930, false, 0);
        showBetChipOld(flCorner28293132, false, 0);
        showBetChipOld(flCorner29303233, false, 0);
        showBetChipOld(flCorner31323435, false, 0);
        showBetChipOld(flCorner32333536, false, 0);

        showBetChipOld(flLine010203040506, false, 0);
        showBetChipOld(flLine040506070809, false, 0);
        showBetChipOld(flLine070809101112, false, 0);
        showBetChipOld(flLine101112131415, false, 0);
        showBetChipOld(flLine131415161718, false, 0);
        showBetChipOld(flLine161718192021, false, 0);
        showBetChipOld(flLine192021222324, false, 0);
        showBetChipOld(flLine222324252627, false, 0);
        showBetChipOld(flLine252627282930, false, 0);
        showBetChipOld(flLine282930313233, false, 0);
        showBetChipOld(flLine313233343536, false, 0);

        showBetChipOld(fl1row1x2, false, 0);
        showBetChipOld(fl2row1x2, false, 0);
        showBetChipOld(fl3row1x2, false, 0);
        showBetChipOld(flDozen1, false, 0);
        showBetChipOld(flDozen2, false, 0);
        showBetChipOld(flDozen3, false, 0);

        showBetChipOld(flOdd, false, 0);
        showBetChipOld(flEven, false, 0);
        showBetChipOld(flBlack, false, 0);
        showBetChipOld(flRed, false, 0);
        showBetChipOld(flLow, false, 0);
        showBetChipOld(flHigh, false, 0);
        if (ll_center_orphelins != null) {
            ll_center_orphelins.setVisibility(View.GONE);
        }

    }
/*
    public void clearNoBetChip(String type) {
//        clearAllChips();
        ////显示下注过的筹码



        //////


    }*/

    public ResultHintBean getAnimationDrawable(String type) {
        if (fl_roulette_board_bg.getVisibility() == View.VISIBLE && fl_roulette_board_bg_new.getVisibility() == View.INVISIBLE) {
            switch (type) {
                case "0":
                    return new ResultHintBean(ivNumber00, R.mipmap.number_0_trans);
                case "1":
                    return new ResultHintBean(ivNumber01, R.mipmap.r2);
                case "2":
                    return new ResultHintBean(ivNumber02, R.mipmap.r2);
                case "3":
                    return new ResultHintBean(ivNumber03, R.mipmap.r2);
                case "4":
                    return new ResultHintBean(ivNumber04, R.mipmap.r5);
                case "5":
                    return new ResultHintBean(ivNumber05, R.mipmap.r5);
                case "6":
                    return new ResultHintBean(ivNumber06, R.mipmap.r5);
                case "7":
                    return new ResultHintBean(ivNumber07, R.mipmap.r8);
                case "8":
                    return new ResultHintBean(ivNumber08, R.mipmap.r8);
                case "9":
                    return new ResultHintBean(ivNumber09, R.mipmap.r8);
                case "10":
                    return new ResultHintBean(ivNumber10, R.mipmap.r11);
                case "11":
                    return new ResultHintBean(ivNumber11, R.mipmap.r11);
                case "12":
                    return new ResultHintBean(ivNumber12, R.mipmap.r11);
                case "13":
                    return new ResultHintBean(ivNumber13, R.mipmap.r14);
                case "14":
                    return new ResultHintBean(ivNumber14, R.mipmap.r14);
                case "15":
                    return new ResultHintBean(ivNumber15, R.mipmap.r14);
                case "16":
                    return new ResultHintBean(ivNumber16, R.mipmap.r17);
                case "17":
                    return new ResultHintBean(ivNumber17, R.mipmap.r17);
                case "18":
                    return new ResultHintBean(ivNumber18, R.mipmap.r17);
                case "19":
                    return new ResultHintBean(ivNumber19, R.mipmap.r20);
                case "20":
                    return new ResultHintBean(ivNumber20, R.mipmap.r20);
                case "21":
                    return new ResultHintBean(ivNumber21, R.mipmap.r20);
                case "22":
                    return new ResultHintBean(ivNumber22, R.mipmap.r23);
                case "23":
                    return new ResultHintBean(ivNumber23, R.mipmap.r23);
                case "24":
                    return new ResultHintBean(ivNumber24, R.mipmap.r23);
                case "25":
                    return new ResultHintBean(ivNumber25, R.mipmap.r26);
                case "26":
                    return new ResultHintBean(ivNumber26, R.mipmap.r26);
                case "27":
                    return new ResultHintBean(ivNumber27, R.mipmap.r26);
                case "28":
                    return new ResultHintBean(ivNumber28, R.mipmap.r29);
                case "29":
                    return new ResultHintBean(ivNumber29, R.mipmap.r29);
                case "30":
                    return new ResultHintBean(ivNumber30, R.mipmap.r29);
                case "31":
                    return new ResultHintBean(ivNumber31, R.mipmap.r32);
                case "32":
                    return new ResultHintBean(ivNumber32, R.mipmap.r32);
                case "33":
                    return new ResultHintBean(ivNumber33, R.mipmap.r32);
                case "34":
                    return new ResultHintBean(ivNumber34, R.mipmap.r35);
                case "35":
                    return new ResultHintBean(ivNumber35, R.mipmap.r35);
                case "36":
                    return new ResultHintBean(ivNumber36, R.mipmap.r35);
                case "Low":
                    return new ResultHintBean(ivLow, R.mipmap.table_roulette_1_18);
                case "High":
                    return new ResultHintBean(ivHigh, R.mipmap.table_roulette_19_36);
                case "Red":
                    return new ResultHintBean(ivRed, R.mipmap.table_roulette_red);
                case "Black":
                    return new ResultHintBean(ivBlack, R.mipmap.table_roulette_black);
                case "Odd":
                    return new ResultHintBean(ivOdd, R.mipmap.ganjil);
                case "Even":
                    return new ResultHintBean(ivEven, R.mipmap.genap);
                case "Row1":
                    return new ResultHintBean(iv1row1x2, R.mipmap.row_1x2);
                case "Row2":
                    return new ResultHintBean(iv2row1x2, R.mipmap.row_1x2);
                case "Row3":
                    return new ResultHintBean(iv3row1x2, R.mipmap.row_1x2);
                case "Col1":
                    return new ResultHintBean(ivDozen1, R.mipmap.table_roulette_1_12);
                case "Col2":
                    return new ResultHintBean(ivDozen2, R.mipmap.table_roulette_13_24);
                case "Col3":
                    return new ResultHintBean(ivDozen3, R.mipmap.table_roulette_25_36);
                default:
                    return null;
            }
        } else {
            switch (type) {
                case "0":
                    return new ResultHintBean(ivNumber00New, R.mipmap.number_0_new);
                case "1":
                    return new ResultHintBean(ivNumber01New, R.mipmap.number_other_new);
                case "2":
                    return new ResultHintBean(ivNumber02New, R.mipmap.number_other_new);
                case "3":
                    return new ResultHintBean(ivNumber03New, R.mipmap.number_3_new);
                case "4":
                    return new ResultHintBean(ivNumber04New, R.mipmap.number_other_new);
                case "5":
                    return new ResultHintBean(ivNumber05New, R.mipmap.number_5_new);
                case "6":
                    return new ResultHintBean(ivNumber06New, R.mipmap.number_other_new);
                case "7":
                    return new ResultHintBean(ivNumber07New, R.mipmap.number_other_new);
                case "8":
                    return new ResultHintBean(ivNumber08New, R.mipmap.number_8_new);
                case "9":
                    return new ResultHintBean(ivNumber09New, R.mipmap.number_other_new);
                case "10":
                    return new ResultHintBean(ivNumber10New, R.mipmap.number_10_new);
                case "11":
                    return new ResultHintBean(ivNumber11New, R.mipmap.number_other_new);
                case "12":
                    return new ResultHintBean(ivNumber12New, R.mipmap.number_other_new);
                case "13":
                    return new ResultHintBean(ivNumber13New, R.mipmap.number_other_new);
                case "14":
                    return new ResultHintBean(ivNumber14New, R.mipmap.number_other_new);
                case "15":
                    return new ResultHintBean(ivNumber15New, R.mipmap.number_other_new);
                case "16":
                    return new ResultHintBean(ivNumber16New, R.mipmap.number_other_new);
                case "17":
                    return new ResultHintBean(ivNumber17New, R.mipmap.number_other_new);
                case "18":
                    return new ResultHintBean(ivNumber18New, R.mipmap.number_other_new);
                case "19":
                    return new ResultHintBean(ivNumber19New, R.mipmap.number_other_new);
                case "20":
                    return new ResultHintBean(ivNumber20New, R.mipmap.number_other_new);
                case "21":
                    return new ResultHintBean(ivNumber21New, R.mipmap.number_other_new);
                case "22":
                    return new ResultHintBean(ivNumber22New, R.mipmap.number_other_new);
                case "23":
                    return new ResultHintBean(ivNumber23New, R.mipmap.number_23_new);
                case "24":
                    return new ResultHintBean(ivNumber24New, R.mipmap.number_24_new);
                case "25":
                    return new ResultHintBean(ivNumber25New, R.mipmap.number_other_new);
                case "26":
                    return new ResultHintBean(ivNumber26New, R.mipmap.number_26_new);
                case "27":
                    return new ResultHintBean(ivNumber27New, R.mipmap.number_other_new);
                case "28":
                    return new ResultHintBean(ivNumber28New, R.mipmap.number_other_new);
                case "29":
                    return new ResultHintBean(ivNumber29New, R.mipmap.number_other_new);
                case "30":
                    return new ResultHintBean(ivNumber30New, R.mipmap.number_30_new);
                case "31":
                    return new ResultHintBean(ivNumber31New, R.mipmap.number_other_new);
                case "32":
                    return new ResultHintBean(ivNumber32New, R.mipmap.number_32_new);
                case "33":
                    return new ResultHintBean(ivNumber33New, R.mipmap.number_other_new);
                case "34":
                    return new ResultHintBean(ivNumber34New, R.mipmap.number_other_new);
                case "35":
                    return new ResultHintBean(ivNumber35New, R.mipmap.number_35_new);
                case "36":
                    return new ResultHintBean(ivNumber36New, R.mipmap.number_other_new);
                case "Low":
                    return new ResultHintBean(ivLow, R.mipmap.table_roulette_1_18);
                case "High":
                    return new ResultHintBean(ivHigh, R.mipmap.table_roulette_19_36);
                case "Red":
                    return new ResultHintBean(ivRed, R.mipmap.table_roulette_red);
                case "Black":
                    return new ResultHintBean(ivBlack, R.mipmap.table_roulette_black);
                case "Odd":
                    return new ResultHintBean(ivOdd, R.mipmap.ganjil);
                case "Even":
                    return new ResultHintBean(ivEven, R.mipmap.genap);
                case "Row1":
                    return new ResultHintBean(iv1row1x2, R.mipmap.row_1x2);
                case "Row2":
                    return new ResultHintBean(iv2row1x2, R.mipmap.row_1x2);
                case "Row3":
                    return new ResultHintBean(iv3row1x2, R.mipmap.row_1x2);
                case "Col1":
                    return new ResultHintBean(ivDozen1, R.mipmap.table_roulette_1_12);
                case "Col2":
                    return new ResultHintBean(ivDozen2, R.mipmap.table_roulette_13_24);
                case "Col3":
                    return new ResultHintBean(ivDozen3, R.mipmap.table_roulette_25_36);
                default:
                    return null;
            }
        }

    }

    public void showResultsOnUI() {
        if (mAppViewModel.getRoulette01().getResult() != null && !"".equals(mAppViewModel.getRoulette01().getResult())
                && mAppViewModel.getRoulette01().getGameStatus() == 5) {
            int musicIndex = 17;
            musicIndex += Integer.parseInt(mAppViewModel.getRoulette01().getResult());
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, musicIndex, componentFront, mContext, mAppViewModel.getFrontVolume());
//            AnimationDrawable animationDrawable = getAnimationDrawable(mAppViewModel.getRoulette01().getResult());
            ResultHintBean animationDrawable = getAnimationDrawable(mAppViewModel.getRoulette01().getResult());
            if (animationDrawable == null) {
                return;
            }
            animationDrawable.getImg().setBackgroundResource(animationDrawable.getRes());
            if (!"0".equals(mAppViewModel.getRoulette01().getResult())) {
                switch (mAppViewModel.getRoulette01().getResult()) {

                    case "2":
                    case "4":
                    case "6":
                    case "8":
                    case "10":
                    case "11":
                    case "13":
                    case "15":
                    case "17":
                    case "20":
                    case "22":
                    case "24":
                    case "26":
                    case "28":
                    case "29":
                    case "31":
                    case "33":
                    case "35":
                        animationDrawable = getAnimationDrawable("Black");

                        break;
                    default:
                        animationDrawable = getAnimationDrawable("Red");

                        break;
                }
                animationDrawable.getImg().setBackgroundResource(animationDrawable.getRes());


                if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) % 2 == 0) {
                    animationDrawable = getAnimationDrawable("Even");

                } else {
                    animationDrawable = getAnimationDrawable("Odd");

                }

                animationDrawable.getImg().setBackgroundResource(animationDrawable.getRes());


                if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) > 18) {
                    animationDrawable = getAnimationDrawable("High");
                } else
                    animationDrawable = getAnimationDrawable("Low");

                animationDrawable.getImg().setBackgroundResource(animationDrawable.getRes());


                if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) > 0 && Integer.parseInt(mAppViewModel.getRoulette01().getResult()) <= 12) {
                    animationDrawable = getAnimationDrawable("Col1");
                } else if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) >= 13 && Integer.parseInt(mAppViewModel.getRoulette01().getResult()) <= 24) {
                    animationDrawable = getAnimationDrawable("Col2");
                } else if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) >= 25 && Integer.parseInt(mAppViewModel.getRoulette01().getResult()) <= 36) {
                    animationDrawable = getAnimationDrawable("Col3");
                }

                animationDrawable.getImg().setBackgroundResource(animationDrawable.getRes());

                switch (mAppViewModel.getRoulette01().getResult()) {
                    case "1":
                    case "4":
                    case "7":
                    case "10":
                    case "13":
                    case "16":
                    case "19":
                    case "22":
                    case "25":
                    case "28":
                    case "31":
                    case "34":

                        animationDrawable = getAnimationDrawable("Row3");
                        break;
                    case "2":
                    case "5":
                    case "8":
                    case "11":
                    case "14":
                    case "17":
                    case "20":
                    case "23":
                    case "26":
                    case "29":
                    case "32":
                    case "35":

                        animationDrawable = getAnimationDrawable("Row2");
                        break;
                    default:
                        animationDrawable = getAnimationDrawable("Row1");
                        break;
                }
                animationDrawable.getImg().setBackgroundResource(animationDrawable.getRes());

            }


        }

    }

    public void clickNumber(String number) {
        int minLimit = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet();
        int maxLimit = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet();
        int alreadyBet = mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number);
        int totalbet = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet();
        FrameLayout fl_chip = getFrameLayout(number);
        String typebet = "DirectBet";
        if (checkChoose()) return;
        if (mAppViewModel.getRoulette01().getGameStatus() != 1)
            return;

        boolean isIn = false;
        boolean showBefore = false;
        int betMoney = 0;
        for (int i = 0; i < listBetDetail.size(); i++) {//连续点击，还未下注
            if (number.equals(listBetDetail.get(i).getNumber())) {
                isIn = true;
                listBetDetail.get(i).setClickCount(listBetDetail.get(i).getClickCount() + 1);
                betMoney = mAppViewModel.getBetMoney(chooseChip, minLimit,
                        maxLimit, listBetDetail.get(i).getClickCount(),
                        alreadyBet, totalbet, mContext, componentFront);
                if (betMoney > 0) {
                    listBetDetail.get(i).setMoney(betMoney);
                } else {
                    listBetDetail.remove(i);
                    showBefore = true;
                }
                break;
            }
        }
        if (!isIn) {
            BetDetail betDetail = new BetDetail();
            betDetail.setType(typebet);
            betDetail.setNumber(number);
            betDetail.setClickCount(1);

            betMoney = mAppViewModel.getBetMoney(chooseChip, minLimit,
                    maxLimit, 1,
                    alreadyBet, totalbet, mContext, componentFront);
            betDetail.setMoney(betMoney);
            if (betMoney > 0) {
                listBetDetail.add(betDetail);
            } else
                showBefore = true;
        }
        if (showBefore) {
            showBetChip(fl_chip, true, alreadyBet, false);
            //超过了最大的值，要提醒
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        } else {
            showBetChip(fl_chip, true, betMoney, false);
        }
    }

    //点击事件
    public void clickNumber0(View v) {

        String number = "00";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber1(View v) {
        //   showViewAnimation(v);
        //   showBetChipOld(flNumber01, true, 10);
        String number = "01";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet010203(View v) {
        String number = "010203";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber2(View v) {

        String number = "02";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber3(View v) {

        String number = "03";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber4(View v) {

        String number = "04";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber5(View v) {

        String number = "05";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber6(View v) {

        String number = "06";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber7(View v) {

        String number = "07";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber8(View v) {

        String number = "08";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber9(View v) {

        String number = "09";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber10(View v) {

        String number = "10";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber11(View v) {

        String number = "11";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber12(View v) {

        String number = "12";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber13(View v) {

        String number = "13";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber14(View v) {

        String number = "14";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber15(View v) {
        String number = "15";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber16(View v) {

        String number = "16";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber17(View v) {
        String number = "17";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber18(View v) {
        String number = "18";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber19(View v) {

        String number = "19";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber20(View v) {
        String number = "20";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber21(View v) {

        String number = "21";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber22(View v) {

        String number = "22";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber23(View v) {

        String number = "23";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber24(View v) {

        String number = "24";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber25(View v) {

        String number = "25";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber26(View v) {

        String number = "26";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber27(View v) {

        String number = "27";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber28(View v) {

        String number = "28";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber29(View v) {

        String number = "29";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber30(View v) {
        String number = "30";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber31(View v) {

        String number = "31";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber32(View v) {

        String number = "32";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber33(View v) {

        String number = "33";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber34(View v) {
        String number = "34";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber35(View v) {

        String number = "35";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickNumber36(View v) {
        String number = "36";
        clickChipBet(number, "DirectBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void click1Row1x2(View v) {
        if ((getNumberBetMoney("SndRow") > 0 && getNumberBetMoney("FristRow") > 0) ||
                (mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("SndRow") > 0 &&
                        mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("FristRow") > 0)) {
            Toast.makeText(mContext, R.string.show_limit_row, Toast.LENGTH_LONG).show();
            return;
        }

        String number = "ThrRow";
        clickChipBet(number, "ThrRow", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void click2Row1x2(View v) {
        if ((getNumberBetMoney("ThrRow") > 0 && getNumberBetMoney("FristRow") > 0) ||
                (mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("ThrRow") > 0 &&
                        mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("FristRow") > 0)) {
            Toast.makeText(mContext, R.string.show_limit_row, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "SndRow";
        clickChipBet(number, "SndRow", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void click3Row1x2(View v) {
        if ((getNumberBetMoney("ThrRow") > 0 && getNumberBetMoney("SndRow") > 0) ||
                (mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("ThrRow") > 0 &&
                        mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("SndRow") > 0)) {
            Toast.makeText(mContext, R.string.show_limit_row, Toast.LENGTH_LONG).show();
            return;
        }

        String number = "FristRow";
        clickChipBet(number, "FristRow", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickDozen1(View v) {
        if ((getNumberBetMoney("SndCol") > 0 && getNumberBetMoney("ThrCol") > 0) ||
                (mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("SndCol") > 0 &&
                        mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("ThrCol") > 0)) {
            Toast.makeText(mContext, R.string.show_limit_column, Toast.LENGTH_LONG).show();
            return;
        }

        String number = "FristCol";
        clickChipBet(number, "FristCol", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickDozen2(View v) {
        if ((getNumberBetMoney("ThrCol") > 0 && getNumberBetMoney("FristCol") > 0) ||
                (mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("ThrCol") > 0 &&
                        mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("FristCol") > 0)) {
            Toast.makeText(mContext, R.string.show_limit_column, Toast.LENGTH_LONG).show();
            return;
        }

        String number = "SndCol";
        clickChipBet(number, "SndCol", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickDozen3(View v) {
        if ((getNumberBetMoney("SndCol") > 0 && getNumberBetMoney("FristCol") > 0) ||
                (mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("SndCol") > 0 &&
                        mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("FristCol") > 0)) {
            Toast.makeText(mContext, R.string.show_limit_column, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "ThrCol";
        clickChipBet(number, "ThrCol", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }


    public void clickLimitList(View v) {
        showLimit();
    }

    public void clickStreet000203(View view) {
        String number = "000203";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickHigh(View view) {
        if (getNumberBetMoney("LowBet") > 0 || mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("LowBet") > 0) {
            Toast.makeText(mContext, R.string.show_limit_big_small, Toast.LENGTH_LONG).show();
            return;
        }

        String number = "HightBet";
        clickChipBet(number, "HightBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0306(View view) {
        String number = "0306";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0609(View view) {
        String number = "0609";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0912(View view) {
        String number = "0912";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1215(View view) {
        String number = "1215";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1518(View view) {
        String number = "1518";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1821(View view) {
        String number = "1821";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2124(View view) {
        String number = "2124";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2427(View view) {
        String number = "2427";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2730(View view) {
        String number = "2730";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit3033(View view) {
        String number = "3033";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit3336(View view) {
        String number = "3336";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0203(View view) {
        String number = "0203";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner02030506(View view) {
        String number = "02030506";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0506(View view) {
        String number = "0506";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner05060809(View view) {
        String number = "05060809";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0809(View view) {
        String number = "0809";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner08091112(View view) {
        String number = "08091112";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1112(View view) {
        String number = "1112";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner11121415(View view) {
        String number = "11121415";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1415(View view) {
        String number = "1415";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner14151718(View view) {
        String number = "14151718";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1718(View view) {
        String number = "1718";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner17182021(View view) {
        String number = "17182021";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2021(View view) {
        String number = "2021";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner20212324(View view) {
        String number = "20212324";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2324(View view) {
        String number = "2324";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner23242627(View view) {
        String number = "23242627";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2627(View view) {
        String number = "2627";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner26272930(View view) {
        String number = "26272930";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2930(View view) {
        String number = "2930";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner29303233(View view) {
        String number = "29303233";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit3233(View view) {
        String number = "3233";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner32333536(View view) {
        String number = "32333536";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clicksSlit3536(View view) {
        String number = "3536";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0002(View view) {
        String number = "0002";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0205(View view) {
        String number = "0205";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0508(View view) {
        String number = "0508";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0811(View view) {
        String number = "0811";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1114(View view) {
        String number = "1114";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1417(View view) {
        String number = "1417";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1720(View view) {
        String number = "1720";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2023(View view) {
        String number = "2023";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2326(View view) {
        String number = "2326";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2629(View view) {
        String number = "2629";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2932(View view) {
        String number = "2932";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit3235(View view) {
        String number = "3235";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet000102(View view) {
        String number = "000102";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0102(View view) {
        String number = "0102";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner01020405(View view) {
        String number = "01020405";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0405(View view) {
        String number = "0405";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner04050708(View view) {
        String number = "04050708";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0708(View view) {
        String number = "0708";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner07081011(View view) {
        String number = "07081011";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1011(View view) {
        String number = "1011";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner10111314(View view) {
        String number = "10111314";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1314(View view) {
        String number = "1314";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner13141617(View view) {
        String number = "13141617";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1617(View view) {
        String number = "1617";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner16171920(View view) {
        String number = "16171920";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1920(View view) {
        String number = "1920";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner19202223(View view) {
        String number = "19202223";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2223(View view) {
        String number = "2223";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner22232526(View view) {
        String number = "22232526";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2526(View view) {
        String number = "2526";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner25262829(View view) {
        String number = "25262829";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2829(View view) {
        String number = "2829";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner28293132(View view) {
        String number = "28293132";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit3132(View view) {
        String number = "3132";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner31323435(View view) {
        String number = "31323435";
        clickChipBet(number, "AngleBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit3435(View view) {
        String number = "3435";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0001(View view) {
        String number = "0001";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0003(View view) {
        String number = "0003";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }


    public void clickSplit0104(View view) {
        String number = "0104";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0407(View view) {
        String number = "0407";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit0710(View view) {
        String number = "0710";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1013(View view) {
        String number = "1013";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1316(View view) {
        String number = "1316";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1619(View view) {
        String number = "1619";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit1922(View view) {
        String number = "1922";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2225(View view) {
        String number = "2225";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2528(View view) {
        String number = "2528";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit2831(View view) {
        String number = "2831";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickSplit3134(View view) {
        String number = "3134";
        clickChipBet(number, "SeparateBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine010203040506(View view) {
        String number = "010203040506";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet040506(View view) {
        String number = "040506";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine040506070809(View view) {
        String number = "040506070809";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine070809101112(View view) {
        String number = "070809101112";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet101112(View view) {
        String number = "101112";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine101112131415(View view) {
        String number = "101112131415";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet131415(View view) {
        String number = "131415";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine131415161718(View view) {
        String number = "131415161718";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet161718(View view) {
        String number = "161718";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine161718192021(View view) {
        String number = "161718192021";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet192021(View view) {
        String number = "192021";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine192021222324(View view) {
        String number = "192021222324";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet222324(View view) {
        String number = "222324";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine222324252627(View view) {
        String number = "222324252627";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet252627(View view) {
        String number = "252627";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine252627282930(View view) {
        String number = "252627282930";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet282930(View view) {
        String number = "282930";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine282930313233(View view) {
        String number = "282930313233";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet313233(View view) {
        String number = "313233";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLine313233343536(View view) {
        String number = "313233343536";
        clickChipBet(number, "LineBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet343536(View view) {
        String number = "343536";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickStreet070809(View view) {
        String number = "070809";
        clickChipBet(number, "StreetBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickCorner00010203(View view) {
        String number = "FourBet";
        clickChipBet(number, "FourBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLow(View view) {
        if (getNumberBetMoney("HightBet") > 0 || mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("HightBet") > 0) {
            Toast.makeText(mContext, R.string.show_limit_big_small, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "LowBet";
        clickChipBet(number, "LowBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickBlack(View view) {
        if (getNumberBetMoney("RedBet") > 0 || mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("RedBet") > 0) {
            Toast.makeText(mContext, R.string.show_limit_red_black, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "BlackBet";
        clickChipBet(number, "BlackBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickRed(View view) {
        if (getNumberBetMoney("BlackBet") > 0 || mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("BlackBet") > 0) {
            Toast.makeText(mContext, R.string.show_limit_red_black, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "RedBet";
        clickChipBet(number, "RedBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickOdd(View view) {
        if (getNumberBetMoney("EvenBet") > 0 || mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("EvenBet") > 0) {
            Toast.makeText(mContext, R.string.show_limit_odd_even, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "OddBet";
        clickChipBet(number, "OddBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickEven(View view) {
        if (getNumberBetMoney("OddBet") > 0 || mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney("OddBet") > 0) {
            Toast.makeText(mContext, R.string.show_limit_odd_even, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "EvenBet";
        clickChipBet(number, "EvenBet", chooseChip, mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet(),
                mAppViewModel.getRoulette01().getRouletteBetInformation().getNumberBetMoney(number),
                mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );

    }

    public void showViewAnimation(View v) {
        AnimationDrawable bg = (AnimationDrawable) v.getBackground();
        if (bg.isRunning())
            bg.stop();
        bg.start();
    }

    private void showBetChip(final FrameLayout f, boolean isShow, int money, int gravity, boolean operationDisplay) {
        if (f == null)
            return;
        final FrameLayout frameLayoutOther = getFrameLayoutOther(f);
        ChipShowHelper chipHelper = ChipMap.get(f);
        if (chipHelper == null) {
            int iCount = f.getChildCount();
            chipHelper = new ChipShowHelper(mContext, f, chipList);
            chipHelper.setFirstIndex(iCount);
            chipHelper.setTextGravity(gravity);
            chipHelper.setTopMargin(AutoUtils.getPercentHeightSize(4));
            chipHelper.setMoneyTipsTextSize(AutoUtils.getPercentHeightSize(4));
            chipHelper.setMoneyTipsTextSize(8);
            chipHelper.setOperationWH(22, 22);
            chipHelper.setOperationButton(0, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = getType(f);
                    singleBet(type);
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = getType(f);
                    clearBetChip(type);
                }
            }, null);
            ChipMap.put(f, chipHelper);
        }
        ChipShowHelper chipHelperNew = null;
        if (frameLayoutOther != null) {
            chipHelperNew = ChipMap.get(frameLayoutOther);
            if (chipHelperNew == null) {
                int iCount = frameLayoutOther.getChildCount();
                chipHelperNew = new ChipShowHelper(mContext, frameLayoutOther, chipList);
                chipHelperNew.setFirstIndex(iCount);
                chipHelperNew.setTextGravity(gravity);
                chipHelperNew.setTopMargin(AutoUtils.getPercentHeightSize(4));
                chipHelperNew.setMoneyTipsTextSize(AutoUtils.getPercentHeightSize(4));
                chipHelperNew.setMoneyTipsTextSize(8);
                chipHelperNew.setOperationWH(22, 22);
                chipHelperNew.setOperationButton(0, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String type = getType(frameLayoutOther);
                        singleBet(type);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String type = getType(frameLayoutOther);
                        clearBetChip(type);
                    }
                }, null);
                ChipMap.put(frameLayoutOther, chipHelperNew);
            }
        }
        if (isShow && money > 0) {
            if (operationDisplay) {
                int sure = R.mipmap.sureimg_light;
                if (currentSure != null && sure != 0) {
                    currentSure.setBackgroundResource(sure);
                }
                int no = R.mipmap.noimg_light;
                if (currentCancel != null && no != 0) {
                    currentCancel.setBackgroundResource(no);
                }
            }
            chipHelper.showChip(money, 0, AutoUtils.getPercentHeightSize(12), AutoUtils.getPercentHeightSize(34), AutoUtils.getPercentHeightSize(17), 0, AutoUtils.getPercentHeightSize(17), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(16));
            if (chipHelperNew != null) {
                chipHelperNew.showChip(money, 0, AutoUtils.getPercentHeightSize(12), AutoUtils.getPercentHeightSize(34), AutoUtils.getPercentHeightSize(17), 0, AutoUtils.getPercentHeightSize(17), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(16));
            }
        } else {
            chipHelper.clearAllChips();
            if (chipHelperNew != null) {
                chipHelperNew.clearAllChips();
            }
        }
        chipHelper.setOperationButtonDisplay(operationDisplay);
        if (chipHelperNew != null) {
            chipHelperNew.setOperationButtonDisplay(operationDisplay);
        }
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

        } else {
            showBetChipOld(f, isShow, money);
            if (frameLayoutOther != null) {
                showBetChipOld(frameLayoutOther, isShow, money);
            }
        }
    }

    private void showBetChipOld(final FrameLayout f, boolean isShow, int money) {
        if (f == null)
            return;
        ChipShowHelper chipHelper = ChipMap.get(f);
        FrameLayout frameLayoutOther = getFrameLayoutOther(f);
        ChipShowHelper chipHelperNew = null;
        if (frameLayoutOther != null) {
            chipHelperNew = ChipMap.get(frameLayoutOther);
        }
        if (chipHelper == null)
            return;
        chipHelper.showChip(money, 0, AutoUtils.getPercentHeightSize(12), AutoUtils.getPercentHeightSize(34), AutoUtils.getPercentHeightSize(17), 0, AutoUtils.getPercentHeightSize(17), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(16));
        chipHelper.setOperationButtonDisplay(false);
        if (chipHelperNew != null) {
            chipHelperNew.showChip(money, 0, AutoUtils.getPercentHeightSize(12), AutoUtils.getPercentHeightSize(34), AutoUtils.getPercentHeightSize(17), 0, AutoUtils.getPercentHeightSize(17), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(16));
            chipHelperNew.setOperationButtonDisplay(false);
        }
    }

    private void showBetChip(FrameLayout f, boolean isShow, int money, boolean operationDisplay) {
        showBetChip(f, isShow, money, Gravity.TOP, operationDisplay);
    }

    public void initPopResultsWindows() {
        TextView tv_pop_number = (TextView) fl_roulette_result.findViewById(R.id.tv_pop_number);
        TextView tv_red_black = (TextView) fl_roulette_result.findViewById(R.id.tv_red_black);
        TextView tv_odd_even = (TextView) fl_roulette_result.findViewById(R.id.tv_odd_even);
        if (mAppViewModel.getRoulette01().getResult() != null && !"".equals(mAppViewModel.getRoulette01().getResult())
        ) {
            switch (mAppViewModel.getRoulette01().getResult()) {
                case "2":
                case "4":
                case "6":
                case "8":
                case "10":
                case "11":
                case "13":
                case "15":
                case "17":
                case "20":
                case "22":
                case "24":
                case "26":
                case "28":
                case "29":
                case "31":
                case "33":
                case "35":
                    tv_red_black.setBackgroundResource(R.drawable.shape_roulette_black_bg);
                    tv_red_black.setText(getString(R.string.black_acronym));
//                            tv_pop_number.setBackgroundResource(R.drawable.rectangle_black_corner2);
                    break;
                case "0":
                    tv_red_black.setBackgroundResource(R.drawable.shape_roulette_zero_bg);
                    tv_red_black.setText(getString(R.string.zero_acronym));
//                            tv_pop_number.setBackgroundResource(R.drawable.rectangle_green_corner2);
                    break;
                default:
                    tv_red_black.setBackgroundResource(R.drawable.shape_roulette_red_bg);
                    tv_red_black.setText(getString(R.string.red_acronym));
//                            tv_pop_number.setBackgroundResource(R.drawable.rectangle_red_corner2);
                    break;
            }
            String resultStr = mAppViewModel.getRoulette01().getResult();
            tv_pop_number.setText(resultStr);
            int result = Integer.parseInt(resultStr);
            if (result % 2 == 0) {
                tv_odd_even.setBackgroundResource(R.drawable.shape_roulette_red_bg);
                tv_odd_even.setText(getString(R.string.E));
            } else {
                tv_odd_even.setBackgroundResource(R.drawable.shape_roulette_black_bg);
                tv_odd_even.setText(getString(R.string.O));
            }
        }
        fl_roulette_result.setVisibility(View.VISIBLE);
    }

    public int getNumberBetMoney(String type) {
        int betMoney = 0;
        for (int i = 0; i < listBetDetail.size(); i++) {
            if (type.equals(listBetDetail.get(i).getNumber())) {
                betMoney = listBetDetail.get(i).getMoney();
                break;
            }

        }
        return betMoney;
    }


    private void displayAll(boolean b) {
        if (b) {
            toolbar.setNavigationIcon(R.mipmap.back_black);
        } else {
            toolbar.setNavigationIcon(null);
        }
        showHideUserInfo();
    }

 /*@Override
 protected void clickRight(View v) {
  super.clickRight(v);
  showMenuPop(v);
 }*/

    @Override
    protected void showPool() {
        super.showPool();
//        if (lv_pool.getVisibility() == View.VISIBLE)
//            lv_pool.setVisibility(View.GONE);
//        else
//            lv_pool.setVisibility(View.VISIBLE);
    }

    @Override
    protected void showLimit() {
        super.showLimit();
//        if (lvLimit.getVisibility() == View.GONE)
//            lvLimit.setVisibility(View.VISIBLE);
//        else
//            lvLimit.setVisibility(View.GONE);
    }


    protected void initOrientation() {
        super.initOrientation();
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lvBaccaratChips = (AdapterView) findViewById(R.id.lv_baccarat_chips_h);
            roulette_content_fgv = (RecyclerView) findViewById(R.id.roulette_content_fgv);
        } else {
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)

            {
                lvBaccaratChips = (AdapterView) findViewById(R.id.lv_baccarat_chips_h);
                roulette_content_fgv = (RecyclerView) findViewById(R.id.roulette_content_fgv);
                bigGradLayout = (GridLayout) findViewById(R.id.baccarat_gridlayout2);
                leftPanel1.setOpen(true, true);
            }
            ll_bet_btn_parent.setVisibility(View.GONE);
        }
        ll_center_small = findViewById(R.id.ll_center_small);
        ll_center_orphelins = findViewById(R.id.ll_center_orphelins);
        ll_center_big = findViewById(R.id.ll_center_big);
        ll_zero = findViewById(R.id.ll_zero);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoHelper.stopVideo();
        stopUpdateStatusThread();
        handler.removeCallbacksAndMessages(null);
    }

    @BindView(R2.id.ll_info)
    LinearLayout ll_info;
    @BindView(R2.id.lv_user_info)
    ListView lv_user_info;
    @BindView(R2.id.lv_pool)
    ListView lv_pool;
    @BindView(R2.id.tv_time)
    TextView tv_time;

    @Override
    protected void showMenuPop(View v) {
        showHideUserInfo();
    }

    private void showHideUserInfo() {
        if (ll_info.getLayoutParams().height > 0) {
            WidgetUtil.shrinkAnimation(ll_info, ScreenUtil.dip2px(mContext, 165), 0);
        } else {
            WidgetUtil.shrinkAnimation(ll_info, 0, ScreenUtil.dip2px(mContext, 165));
        }
    }

    private List<LiveInfoBean> updateInfoData() {
        AppTool.setAppLanguage(this, AppTool.getAppLanguage(this));
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        LiveInfoBean data;
        String name = usName;
        data = new LiveInfoBean(getString(R.string.ID), name.toUpperCase(), "");
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.BET), mAppViewModel.getRoulette01().getRouletteBetInformation().getAllBetMoney() + "", "");
        if (Integer.parseInt(data.getValue1()) > 0) {
            rightBetTv.setText(mAppViewModel.covertLimit(Integer.parseInt(data.getValue1())) + "");
        } else {
            rightBetTv.setText(getString(R.string.BET) + " :0");
        }
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.W_L), mAppViewModel.getRoulette01().getWonMoney() + "", "");
        if (mAppViewModel.getRoulette01().getWonMoney() > 0) {
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getRoulette01().getWonMoney()) + "");
        } else if (mAppViewModel.getRoulette01().getWonMoney() == 0) {
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.bet_color));
            rightWinLoseTv.setText(getString(R.string.W_L) + " :0");
        } else {
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.banker_color));
            rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getRoulette01().getWonMoney()) + "");
        }
        strData.add(data);
        data = new LiveInfoBean(TextUtils.isEmpty(currency) ? getString(R.string.BAL) : currency, mAppViewModel.getUser().getBalance() + "", "");
        rightBalanceTv.setText(mAppViewModel.getUser().getBalance() + "");
        strData.add(data);
        LiveInfoBean limit = new LiveInfoBean(getString(R.string.LIMIT_POP), "0", "");

        LiveInfoBean pool_max_min = new LiveInfoBean(getString(R.string.min_max), "0", "");
        int index = mAppViewModel.getRoulette01().getLimitIndex();
        int minTotal = mAppViewModel.getRoulette01().getRouletteLimit(index).getMinTotalBet();
        int maxTotal = mAppViewModel.getRoulette01().getRouletteLimit(index).getMaxTotalBet();
        limit.setValue("" + mAppViewModel.covertLimit(minTotal), mAppViewModel.covertLimit(maxTotal));
        pool_max_min.setValue("" + mAppViewModel.covertLimit(minTotal), mAppViewModel.covertLimit(maxTotal));
        strData.add(limit);
        strData.add(pool_max_min);

        LiveInfoBean pool_single = new LiveInfoBean(getString(R.string.single), "0", "");
        pool_single.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet()));
        strData.add(pool_single);

        LiveInfoBean pool_split = new LiveInfoBean(getString(R.string.split), "0", "");
        pool_split.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinSplitBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxSplitBet()));
        strData.add(pool_split);

        LiveInfoBean pool_street = new LiveInfoBean(getString(R.string.street), "0", "");
        pool_street.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinStreetBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxStreetBet()));
        strData.add(pool_street);

        LiveInfoBean pool_corner = new LiveInfoBean(getString(R.string.corner), "0", "");
        pool_corner.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinCornerBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxCornerBet()));
        strData.add(pool_corner);

        LiveInfoBean pool_line = new LiveInfoBean(getString(R.string.line), "0", "");
        pool_line.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinLineBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxLineBet()));
        strData.add(pool_line);

        LiveInfoBean pool_colume = new LiveInfoBean(getString(R.string.colume), "0", "");
        pool_colume.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet()));
        strData.add(pool_colume);
        LiveInfoBean pool_dozen = new LiveInfoBean(getString(R.string.dozen), "0", "");
        pool_dozen.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet()));
        strData.add(pool_dozen);
        LiveInfoBean pool_even = new LiveInfoBean(getString(R.string.even_lp), "0", "");
        pool_even.setValue("" + mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet()), mAppViewModel.covertLimit(mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet()));
        strData.add(pool_even);
        return strData;
    }

    private void setInfoData(ListView listView) {
        if (contentInfo == null)
            contentInfo = new AdapterViewContent<>(mContext, listView);
        contentInfo.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {

            @Override
            public int getBaseItemResource() {
                return R.layout.item_user_info1_roulette;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.tv_name);
                TextView tvValue = helper.retrieveView(R.id.tv_value);
                tvType.setText(item.getType());
                tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.gold));
                if (position >= 4) {
                    tvValue.setText(item.getValue1() + "-" + item.getValue2());
                } else {
                    if (position == 2) {
                        if (item.getValue1().startsWith("-")) {
                            tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.banker_color));
                        } else if (Double.parseDouble(item.getValue1()) > 0) {
                            tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.player_color));
                        } else {
                            tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.gold));
                        }
                    }
                    tvValue.setText(item.getValue1());
                }
            }
        });
        contentInfo.setData(updateInfoData());
    }
}
