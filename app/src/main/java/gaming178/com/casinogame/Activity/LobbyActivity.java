package gaming178.com.casinogame.Activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Control.AutoScrollTextView;
import gaming178.com.casinogame.Fragment.BaseFragment;
import gaming178.com.casinogame.Fragment.LobbyBaccaratFragment;
import gaming178.com.casinogame.Fragment.LobbyDragonTigerFragment;
import gaming178.com.casinogame.Fragment.LobbyRouletteFragment;
import gaming178.com.casinogame.Fragment.LobbySicboFragment;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.casinogame.entity.HallGameItemBean;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.BitmapTool;
import gaming178.com.mylibrary.allinone.util.BlockDialog;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.UpdateManager;

/**
 * Created by Administrator on 2016/3/22.
 */
public class LobbyActivity extends BaseActivity {
    @BindView(R2.id.gd__tv_home_user_name)
    TextView tv_home_user_name;
    @BindView(R2.id.gd__tv_home_balance)
    TextView tv_home_balance;

    @BindView(R2.id.gd__iv_set)
    ImageView iv_set;
    @BindView(R2.id.gd__iv_report)
    ImageView iv_report;

    @BindView(R2.id.gd__iv_language)
    ImageView iv_language;

    @BindView(R2.id.gd__view_center)
    View img_home;
    @BindView(R2.id.gd__iv_logout)
    ImageView iv_logout;


    @BindView(R2.id.gd__gridview_content_gv)
    RecyclerView gridviewContentGv;
    BaseRecyclerAdapter<HallGameItemBean> adapterViewContent;
    @BindView(R2.id.gd__hall_game_bottom_prompt_tv)
    AutoScrollTextView hallGameBottomPromptTv;
    @BindView(R2.id.rb_baccarat)
    RadioButton rb_baccarat;
    @BindView(R2.id.rg_home_game)
    RadioGroup rg_home_game;
    @BindView(R2.id.ll_game_pic)
    LinearLayout ll_game_pic;
    @BindView(R2.id.tv_switch_account)
    TextView tv_switch_account;
    @BindView(R2.id.tv_lg)
    TextView tv_lg;
    @BindView(R2.id.tv_set)
    TextView tv_set;
    private BlockDialog dialog;
    private String announcement = "";
    private UpdateAnnouncement updateAnnouncement = null;
    private Thread threadAnnouncement = null;
    private boolean bGetAnnouncement = true;
    private List<BaseFragment> fragmentList;
    public BaseFragment currentFragment;
    public BaseFragment baccaratFragment;
    public BaseFragment dragonTigerFragment;
    public BaseFragment rouletteFragment;
    public BaseFragment sicboFragment;

    private int tableIndex = 0;

    public class UpdateAnnouncement implements Runnable {
        int iError = 0;

        public void run() {
            while (bGetAnnouncement) {
                try {
                    handler.sendEmptyMessageDelayed(1, 1500);
                    Thread.sleep(20000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
    }


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (!isAttached)
                return;
            switch (msg.what) {
                case 1:
                    if (announcement != null && !announcement.equals(mAppViewModel.getAnnouncement())) {
                        announcement = mAppViewModel.getAnnouncement();
                        hallGameBottomPromptTv.stopScroll();
                        hallGameBottomPromptTv.setText(announcement);
                        hallGameBottomPromptTv.setSpeed(0.8f);
                        hallGameBottomPromptTv.setTextColor(mContext.getResources().getColor(R.color.white));
                        hallGameBottomPromptTv.init(hallGameBottomPromptTv.getWidth());
                        hallGameBottomPromptTv.startScroll();
                    }
                    break;
                case HandlerCode.SHOW_BACCARACT:
                    dismissBlockDialog();
                    enterGamesLobby();
                    break;
                case HandlerCode.SHOW_BACCARACT_FAIL:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.server_network_error, Toast.LENGTH_LONG).show();
                    break;
            }
            //

        }
    };

    private void enterGamesLobby() {
        switch (tableIndex) {
            case 0:
            case 1:
            case 2:
                skipAct(LobbyBaccaratActivity.class);
                break;
            case 3:
                skipAct(LobbyRouletteActivity.class);
                break;
            case 4:
                skipAct(LobbySicboActivity.class);
                break;
            case 5:
                skipAct(LobbyDragonTigerActivity.class);
                break;
        }
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
//        getWindow().setAttributes(params);

        AppTool.setAppLanguage(mContext, AppTool.getAppLanguage(mContext));
        setMoreToolbar(true);
//        backTv.setText(R.string.back);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hallGameBottomPromptTv = findViewById(R.id.gd__hall_game_bottom_prompt_tv);
        hallGameBottomPromptTv.setSelected(true);
        initOrientation();
        //设置公告
        announcement = "";
        hallGameBottomPromptTv.setText("  ");

        initBar();

        baccaratFragment = new LobbyBaccaratFragment();
        dragonTigerFragment = new LobbyDragonTigerFragment();
        rouletteFragment = new LobbyRouletteFragment();
        sicboFragment = new LobbySicboFragment();
        fragmentList = Arrays.asList(baccaratFragment, dragonTigerFragment, rouletteFragment, sicboFragment);

        //   startUpdateStatus();
        rb_baccarat.post(new Runnable() {
            @Override
            public void run() {
                int width = rb_baccarat.getWidth();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) rg_home_game.getLayoutParams();
                layoutParams.height = width;
                rg_home_game.setLayoutParams(layoutParams);
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ll_game_pic.getLayoutParams();
                params.height = width;
                ll_game_pic.setLayoutParams(params);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                gridviewContentGv.setLayoutManager(linearLayoutManager);
                adapterViewContent = new BaseRecyclerAdapter<HallGameItemBean>(mContext, new ArrayList<HallGameItemBean>(), R.layout.gd_item_hall_game) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, HallGameItemBean item) {
                        RelativeLayout rl_parent = holder.getRelativeLayout(R.id.gd_rl_parent);
                        int width = rb_baccarat.getWidth();
                        ViewGroup.LayoutParams layoutParams = rl_parent.getLayoutParams();
                        layoutParams.width = width / 6 * 5;
                        layoutParams.height = (int) ((width / 6 * 5) * 1.2);
                        rl_parent.setLayoutParams(layoutParams);
                        ImageView imageView = holder.getImageView(R.id.gd__hall_game_pic_iv);
                        Bitmap bitmap = BitmapTool.toRoundCorner(BitmapFactory.decodeResource(getResources(), item.getImageRes()), ScreenUtil.dip2px(mContext, 5));
                        imageView.setImageBitmap(bitmap);
                        holder.setText(R.id.gd__hall_game_title_tv, item.getTitle());
                    }
                };
                gridviewContentGv.setAdapter(adapterViewContent);
                setAdapterData();
                adapterViewContent.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<HallGameItemBean>() {
                    @Override
                    public void onItemClick(View view, HallGameItemBean hallGameItemBean, int position) {
                        AppTool.setAppLanguage(LobbyActivity.this, AppTool.getAppLanguage(LobbyActivity.this));
                        if (hallGameItemBean.getTitle().equals(getString(R.string.baccarat))) {
                            tableIndex = 0;
                            skipAct(LobbyBaccaratActivity.class);
                        } else if (hallGameItemBean.getTitle().equals(getString(R.string.roulette))) {
                            tableIndex = 3;
                            if (mAppViewModel.getRoulette01().getStatus() != 1) {
                                Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            skipAct(LobbyRouletteActivity.class);
                        } else if (hallGameItemBean.getTitle().equals(getString(R.string.sicbo))) {
                            if (mAppViewModel.getSicbo01().getStatus() != 1) {
                                Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            tableIndex = 4;
                            skipAct(LobbySicboActivity.class);
                        } else if (hallGameItemBean.getTitle().equals(getString(R.string.dragon_tiger))) {
                            if (mAppViewModel.getDragonTiger01().getStatus() != 1) {
                                Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            tableIndex = 5;
                            skipAct(LobbyDragonTigerActivity.class);
                        } else if (hallGameItemBean.getTitle().equals(getString(R.string.slots))) {
                            skipAct(SlotsGameActivity.class);
                        } else if (hallGameItemBean.getTitle().equals(getString(R.string.cq))) {
                            skipAct(CQSlotsGameActivity.class);
                        } else if (hallGameItemBean.getTitle().equals(getString(R.string.cock_fighting))) {
                            skipAct(CockFightingWebActivity.class);
                        } else if (hallGameItemBean.getTitle().equals(getString(R.string.afb1188))) {
                            goAfb1188();
                        } else if (hallGameItemBean.getTitle().equals(getString(R.string.DSV_Casino))) {
                            skipAct(DsvCasinoActivity.class);
                        }

                    }
                });

            }
        });
        FrameLayout fl_home = findViewById(R.id.fl_home);
        fl_home.removeAllViews();
        switchFragment(0);
        rg_home_game.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_baccarat:
                        switchFragment(0);
                        break;
                    case R.id.rb_dragon_tiger:
                        switchFragment(1);
                        break;
                    case R.id.rb_roulette:
                        switchFragment(2);
                        break;
                    case R.id.rb_sicbo:
                        switchFragment(3);
                        break;
                }
            }
        });
        tv_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguagePop(img_home, 0.75f);
            }
        });
        tv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetPop(img_home, Gravity.TOP);
            }
        });
        tv_switch_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    private void initBar() {
        toolbar.setVisibility(View.GONE);
        tv_home_user_name.setText(usName);
        tv_home_balance.setText(mAppViewModel.getUser().getBalance() + "");
        iv_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguagePop(img_home, 0.75f);
            }
        });
        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        iv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetPop(img_home, Gravity.TOP);
            }
        });
        iv_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReport();
            }
        });
    }

    private void goAfb1188() {
        new Thread() {
            @Override
            public void run() {
                String dataUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "afb1188path.jsp";
                if (TextUtils.isEmpty(dataUrl) || mAppViewModel.getHttpClient() == null) {
                    return;
                }
                final String result = mAppViewModel.getHttpClient().sendPost(dataUrl, "gameplat=APP");
                //Results=ok#https://www.sv33888.com/api/player/gd88/login?cert=Q1ltduaIEwBwkoVn&extension1=g1234567&user=RAJA01&key=fnmKjjnmoCIuPEnzAVRM4gHdxhDWvNnA7gI66aFWy2U%3D&balance=12416.314&language=1
                if (result.startsWith("Results=ok")) {
                    String[] split = result.split("#");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 1; i < split.length; i++) {
                        stringBuilder.append(split[i]);
                        stringBuilder.append("&");
                    }
                    String url = stringBuilder.toString();
                    url = url.substring(0, url.length() - 1);
                    final String finalUrl = url;
                    LobbyActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            skipAfb1188(finalUrl);
                        }
                    });

                } else {
                    handler.sendEmptyMessage(HandlerCode.SHOW_BACCARACT_FAIL);
                }
            }
        }.start();
    }

    private void skipAfb1188(String finalUrl) {
        if (AppTool.isAvilible(mContext, "com.nanyang.afb1188")) {//Check to see if your phone has afb1188 installed
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName componentName = new ComponentName("com.nanyang.afb1188", "com.nanyang.app.load.welcome.WelcomeActivity");
            intent.setComponent(componentName);
            intent.putExtra("companyKey", "gd88");//your companyKey
            intent.putExtra("loginUrl", finalUrl);//your companyKey
            startActivity(intent);
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                int REQUEST_EXTERNAL_STORAGE = 1;
                String[] PERMISSIONS_STORAGE = {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                };
                int permission = ActivityCompat.checkSelfPermission(LobbyActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            LobbyActivity.this,
                            PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE
                    );
                }
            }
            UpdateManager updateManager = new UpdateManager(mContext);
            updateManager.setCancel("");
            updateManager.setTitle(getString(R.string.welcome_to_afb1188));
            updateManager.setUpdate(getString(R.string.download));
            updateManager.setUpdateMsg(getString(R.string.welcome_to_afb1188_return));
            updateManager.setLoadTitle(getString(R.string.loading));
            updateManager.setOnLoadEnd(new UpdateManager.ILoad() {
                @Override
                public void onLoadEnd(File file) {
                    LobbyActivity.this.loadFile = file;
                    checkIsAndroidO();
                }
            });
            updateManager.checkUpdate("http://www.appgd88.com/androidAppDownload/afb1188.apk");
        }
    }

    private File loadFile;
    final int INSTALL_CODE = 102;
    final int INSTALL_AFB_CODE = 109;

    public void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
            } else {
                //请求安装未知应用来源的权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_CODE);
            }
        } else {
            AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case INSTALL_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                } else {
                    //  引导用户手动开启安装权限
                    Uri packageURI = Uri.parse("package:gaming178.com.baccaratgame");//设置包名，可直接跳转当前软件的设置页面
                    Intent ii = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                    startActivityForResult(ii, INSTALL_AFB_CODE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INSTALL_AFB_CODE:
                AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                break;
        }
    }


    private void setAdapterData() {
        if (WebSiteUrl.isDomain && WebSiteUrl.GameType != 3) {
//            if (WebSiteUrl.GameType == 0) {
//                if (WebSiteUrl.HEADER.equals("http://202.178.114.15/")) {
//                    adapterViewContent.addAllAndClear(new ArrayList<>(Arrays.asList(new HallGameItemBean(R.mipmap.gd_ba1, getString(R.string.baccarat)))));
//                } else {
//                    adapterViewContent.addAllAndClear(new ArrayList<>(Arrays.asList(new HallGameItemBean(R.mipmap.gd_ba1, getString(R.string.baccarat)),
//                            new HallGameItemBean(R.mipmap.gd_longhu, getString(R.string.dragon_tiger)),
//                            new HallGameItemBean(R.mipmap.gd_lunpan, getString(R.string.roulette)),
//                            new HallGameItemBean(R.mipmap.gd_toubao, getString(R.string.sicbo))
//                    )));
//                }
//            } else {
//                adapterViewContent.addAllAndClear(new ArrayList<>(Arrays.asList(new HallGameItemBean(R.mipmap.gd_ba1, getString(R.string.baccarat)))));
//            }
        } else {
            ArrayList<HallGameItemBean> hallGameItemBeenS = new ArrayList<>();
            if (WebSiteUrl.GameType != 3 && !BuildConfig.FLAVOR.equals("liga365")) {
                hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_sport_afb1188, getString(R.string.afb1188)));
            }
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_cq_slots, getString(R.string.cq)));
            if (!BuildConfig.FLAVOR.equals("ahlicasino")) {
                hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_cock_fighting, getString(R.string.cock_fighting)));
            }
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_slots, getString(R.string.slots)));
            adapterViewContent.addAllAndClear(hallGameItemBeenS);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_main_tab;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bGetAnnouncement = true;
        updateAnnouncement = new UpdateAnnouncement();
        threadAnnouncement = new Thread(updateAnnouncement);
        threadAnnouncement.start();


    }

    @Override
    protected void onPause() {
        //     Log.i(WebSiteUrl.Tag, "Lobby StopUpdateGameStatus() ");
        super.onPause();
        bGetAnnouncement = false;
        hallGameBottomPromptTv.stopScroll();
    }

    @Override
    protected void leftClick() {
        super.leftClick();
        bGetGameStatus = false;
        bGetGameTimer = false;
        mAppViewModel.setbLogin(false);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initOrientation();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            logout();
            return true;
        }
        return false;
    }

    int lastIndex = -1;

    private void switchFragment(int index) {
        if (lastIndex == index) {
            return;
        }
        BaseFragment baseFragment = fragmentList.get(index);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!baseFragment.isAdded()) {
            fragmentTransaction.add(R.id.fl_home, baseFragment);
        } else {
            fragmentTransaction.show(baseFragment);
        }
        currentFragment = baseFragment;
        if (lastIndex != -1) {
            fragmentTransaction.hide(fragmentList.get(lastIndex));
        }
        lastIndex = index;
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().beginTransaction()
                .remove(currentFragment).commitAllowingStateLoss();
        super.onSaveInstanceState(outState);
    }

}
