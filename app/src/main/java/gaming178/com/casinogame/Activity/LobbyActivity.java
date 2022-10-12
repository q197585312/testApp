package gaming178.com.casinogame.Activity;

import android.Manifest;
import android.animation.Animator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cn.finalteam.toolsfinal.StringUtils;
import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Bean.User;
import gaming178.com.casinogame.Control.AutoScrollTextView;
import gaming178.com.casinogame.Fragment.BaseFragment;
import gaming178.com.casinogame.Fragment.LobbyBaccaratFragment;
import gaming178.com.casinogame.Fragment.LobbyDragonTigerFragment;
import gaming178.com.casinogame.Fragment.LobbyRouletteFragment;
import gaming178.com.casinogame.Fragment.LobbySicboFragment;
import gaming178.com.casinogame.Popupwindow.DepositPop;
import gaming178.com.casinogame.Popupwindow.PopContact;
import gaming178.com.casinogame.Popupwindow.PopLiveChat;
import gaming178.com.casinogame.Popupwindow.PopLogout;
import gaming178.com.casinogame.Popupwindow.WithdrawPop;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.BannerViewPager;
import gaming178.com.casinogame.Util.BannerViewPagerAdapter;
import gaming178.com.casinogame.Util.Gd88Utils;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.UIUtil;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.casinogame.entity.BannerBean;
import gaming178.com.casinogame.entity.HallGameItemBean;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.BitmapTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.UpdateManager;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;

/**
 * Created by Administrator on 2016/3/22.
 */
public class LobbyActivity extends BaseActivity {
    @BindView(R2.id.gd__tv_home_user_name)
    TextView tv_home_user_name;
    @BindView(R2.id.gd__tv_home_balance)
    TextView tv_home_balance;

    @BindView(R2.id.gd__gridview_content_gv)
    RecyclerView gridviewContentGv;
    BaseRecyclerAdapter<HallGameItemBean> adapterViewContent;
    @BindView(R2.id.gd__hall_game_bottom_prompt_tv)
    AutoScrollTextView hallGameBottomPromptTv;
    @BindView(R2.id.tv_switch_account)
    TextView tv_switch_account;
    @BindView(R2.id.tv_switch_account_1)
    TextView tv_switch_account_1;
    @BindView(R2.id.tv_lg)
    TextView tv_lg;
    @BindView(R2.id.tv_set)
    TextView tv_set;
    @BindView(R2.id.fl_home)
    FrameLayout fl_home;
    @BindView(R2.id.ll_parent)
    LinearLayout ll_parent;
    @BindView(R2.id.fl_my_game)
    FrameLayout fl_my_game;
    @BindView(R2.id.view_item)
    View view_item;
    @BindView(R2.id.view_game_parent)
    View view_game_parent;
    @BindView(R2.id.ll_content_bg)
    LinearLayout ll_content_bg;
    @BindView(R2.id.tv_home_close_game)
    TextView tv_home_close_game;
    @BindView(R2.id.tv_home_home)
    TextView tv_home_home;
    @BindView(R2.id.tv_home_deposit)
    TextView tv_home_deposit;
    @BindView(R2.id.tv_home_withdraw)
    TextView tv_home_withdraw;
    @BindView(R2.id.tv_home_live_chat)
    TextView tv_home_live_chat;
    @BindView(R2.id.tv_home_logout)
    TextView tv_home_logout;
    @BindView(R2.id.ll_bottom)
    LinearLayout ll_bottom;
    @BindView(R2.id.tv_home_deposit_l)
    TextView tv_home_deposit_l;
    @BindView(R2.id.tv_home_withdraw_l)
    TextView tv_home_withdraw_l;
    @BindView(R2.id.view_bottom)
    View view_bottom;
    @BindView(R2.id.view_top)
    View view_top;
    @BindView(R2.id.view_1)
    View view_1;
    @BindView(R2.id.view_2)
    View view_2;
    @BindView(R2.id.view_3)
    View view_3;
    @BindView(R2.id.view_4)
    View view_4;
    @BindView(R2.id.view_5)
    View view_5;
    @BindView(R2.id.view_6)
    View view_6;
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

    ImageView ahlBack;
    LinearLayout ahlLlUser1, ahlLlUser2;
    TextView ahlTvUser;
    FrameLayout fl_rtp, fl_whatsapp;
    ImageView img_rtp, img_whatsapp;
    TextView main_tv_home_user_name, main_tv_home_balance;

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

            if (!isAttached) {
                return;
            }
            switch (msg.what) {
                case 1:
                    if (announcement != null && !announcement.equals(mAppViewModel.getAnnouncement())) {
                        announcement = mAppViewModel.getAnnouncement();
                        hallGameBottomPromptTv.stopScroll();
                        hallGameBottomPromptTv.setText(announcement);
                        hallGameBottomPromptTv.setSpeed(0.8f);
                        if (BuildConfig.FLAVOR.equals("ularnaga")) {
                            hallGameBottomPromptTv.setTextColor(Color.parseColor("#AE0907"));
                        } else if (BuildConfig.FLAVOR.equals("depocasino")) {
                            hallGameBottomPromptTv.setTextColor(Color.parseColor("#800080"));
                        } else if (BuildConfig.FLAVOR.equals("wargacasino")) {
                            hallGameBottomPromptTv.setTextColor(Color.BLACK);
                        } else if (BuildConfig.FLAVOR.equals("slotku")) {
                            hallGameBottomPromptTv.setTextColor(Color.WHITE);
                        } else {
                            hallGameBottomPromptTv.setTextColor(mContext.getResources().getColor(R.color.white));
                        }
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
    protected void onAfbLoginSucceed() {
        super.onAfbLoginSucceed();
//        initUI();
//        startUpdateStatusThread();

        if (fl_home.getChildCount() == 0) {
            switchFragment(0);
            initBar();
        }
    }

    public void checkAfb1188Data() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null && !StringUtils.isEmpty(extras.getString("web_id")))
            mAppViewModel.setbLogin(false);
        boolean isbLogin = mAppViewModel.isbLogin();
        if (BuildConfig.FLAVOR.isEmpty() && !isbLogin) {

            int gameType = extras.getInt("gameType", 0);
            fromAfb1188(gameType, extras);
        } else {
//            initOtherData();
        }
    }


    private void skipLobbyToAfb1188() {
        if (mAppViewModel.isSkipLobbyToAfb1188()) {
            mAppViewModel.setSkipLobbyToAfb1188(false);
            goAfb1188();
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            if (!TextUtils.isEmpty(mAppViewModel.getLiveChatStr())) {
                tv_home_live_chat.setVisibility(View.VISIBLE);
            }
        }
        skipLobbyToAfb1188();
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
        checkAfb1188Data();
        baccaratFragment = new LobbyBaccaratFragment();
        dragonTigerFragment = new LobbyDragonTigerFragment();
        rouletteFragment = new LobbyRouletteFragment();
        sicboFragment = new LobbySicboFragment();
        fragmentList = Arrays.asList(baccaratFragment, dragonTigerFragment, rouletteFragment, sicboFragment);

        //   startUpdateStatus();
        if (!TextUtils.isEmpty(BuildConfig.FLAVOR) && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                setBanner();
            } else {
                setGameContent();
            }
        } else {
            setGameContent();
        }
        if (!TextUtils.isEmpty(BuildConfig.FLAVOR) && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            tv_lg.setText(getString(R.string.member_center));
            tv_lg.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.home_member_center, 0, 0, 0);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                tv_set.setVisibility(View.GONE);
                tv_home_deposit_l.setVisibility(View.VISIBLE);
                tv_home_withdraw_l.setVisibility(View.VISIBLE);
                tv_switch_account.setVisibility(View.GONE);
                tv_switch_account_1.setVisibility(View.VISIBLE);
            } else {
                tv_set.setVisibility(View.GONE);
                tv_lg.setVisibility(View.GONE);
                tv_switch_account.setVisibility(View.GONE);
                ll_bottom.setVisibility(View.VISIBLE);
            }
        } else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                tv_set.setVisibility(View.VISIBLE);
                tv_home_deposit_l.setVisibility(View.GONE);
                tv_home_withdraw_l.setVisibility(View.GONE);
                tv_switch_account.setVisibility(View.VISIBLE);
                tv_switch_account_1.setVisibility(View.GONE);
            } else {
                tv_set.setVisibility(View.VISIBLE);
                tv_lg.setVisibility(View.VISIBLE);
                tv_switch_account.setVisibility(View.VISIBLE);
                ll_bottom.setVisibility(View.GONE);
            }
        }
        if (mAppViewModel.isbLogin()) {
            switchFragment(0);
        }
        tv_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguagePop(tv_lg, 0.75f);
            }
        });
        tv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetPop(tv_set, Gravity.TOP);

            }
        });
        tv_switch_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        tv_switch_account_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        tv_home_close_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeGame();
                clickItem = -1;
                adapterViewContent.notifyDataSetChanged();
            }
        });
        tv_home_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguagePop(tv_lg, 0.75f);
            }
        });
        tv_home_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDeposit(v);
            }
        });
        tv_home_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWithdraw(v);
            }
        });
        tv_home_live_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BuildConfig.FLAVOR.equals("ahlicasino")) {
                    int screenWidth = WidgetUtil.getPopScreenWidth(LobbyActivity.this);
                    int width = screenWidth / 15 * 14;
                    PopContact popContact = new PopContact(mContext, v, width, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popContact.showPopupCenterWindow();
                } else {
                    PopLiveChat popLiveChat = new PopLiveChat(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    popLiveChat.showPopupCenterWindow();
                }
            }
        });
        tv_home_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BuildConfig.FLAVOR.equals("ahlicasino")) {
                    popLogout();
                } else {
                    logout();
                }
            }
        });
        tv_home_deposit_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDeposit(v);
            }
        });
        tv_home_withdraw_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWithdraw(v);
            }
        });

        if (BuildConfig.FLAVOR.equals("hitamslot")) {
            LinearLayout ll_top_parent = findViewById(R.id.ll_top_parent);
            ll_top_parent.setBackgroundColor(Color.BLACK);
            ll_bottom.setBackgroundColor(ContextCompat.getColor(mContext, R.color.hitam_color2));
        } else if (BuildConfig.FLAVOR.equals("hokicasino88") || BuildConfig.FLAVOR.equals("doacasino") || BuildConfig.FLAVOR.equals("ularnaga") ||
                BuildConfig.FLAVOR.equals("ratucasino88") || BuildConfig.FLAVOR.equals("depocasino") || BuildConfig.FLAVOR.equals("wargacasino") ||
                BuildConfig.FLAVOR.equals("slotku")) {
            LinearLayout ll_lobby_parent = findViewById(R.id.ll_lobby_parent);
            LinearLayout ll_top_parent = findViewById(R.id.ll_top_parent);
            LinearLayout gd_ll_notice = findViewById(R.id.gd__ll_notice);

            ll_bottom.setBackgroundResource(R.drawable.hokicasino_home_bottom);
            tv_home_home.setBackgroundResource(R.drawable.hokicasino_home_bottom_item);
            tv_home_deposit.setBackgroundResource(R.drawable.hokicasino_home_bottom_item);
            tv_home_withdraw.setBackgroundResource(R.drawable.hokicasino_home_bottom_item);
            tv_home_live_chat.setBackgroundResource(R.drawable.hokicasino_home_bottom_item);
            tv_home_logout.setBackgroundResource(R.drawable.hokicasino_home_bottom_item);
            tv_home_home.setTextColor(Color.parseColor("#C4A550"));
            tv_home_deposit.setTextColor(Color.parseColor("#C4A550"));
            tv_home_withdraw.setTextColor(Color.parseColor("#C4A550"));
            tv_home_live_chat.setTextColor(Color.parseColor("#C4A550"));
            tv_home_logout.setTextColor(Color.parseColor("#C4A550"));
            view_1.setVisibility(View.VISIBLE);
            view_2.setVisibility(View.VISIBLE);
            view_3.setVisibility(View.VISIBLE);
            view_4.setVisibility(View.VISIBLE);
            view_5.setVisibility(View.VISIBLE);
            view_6.setVisibility(View.VISIBLE);
            view_top.setVisibility(View.VISIBLE);
            view_bottom.setVisibility(View.VISIBLE);
            if (BuildConfig.FLAVOR.equals("doacasino")) {
                ll_lobby_parent.setBackgroundResource(0);
                ll_lobby_parent.setBackgroundColor(Color.BLACK);
            } else if (BuildConfig.FLAVOR.equals("ularnaga")) {
                ll_top_parent.setBackgroundResource(R.drawable.ularnaga_top_bg);
                ll_lobby_parent.setBackgroundResource(0);
                ll_lobby_parent.setBackgroundColor(Color.parseColor("#CD1012"));
                gd_ll_notice.setBackgroundResource(R.drawable.hokicasino_home_item);
                tv_home_home.setTextColor(Color.WHITE);
                tv_home_deposit.setTextColor(Color.WHITE);
                tv_home_withdraw.setTextColor(Color.WHITE);
                tv_home_live_chat.setTextColor(Color.WHITE);
                tv_home_logout.setTextColor(Color.WHITE);
            } else if (BuildConfig.FLAVOR.equals("ratucasino88")) {
                ll_top_parent.setBackgroundResource(R.drawable.ularnaga_top_bg);
                ll_lobby_parent.setBackgroundResource(R.mipmap.gd_login_bg);
                gd_ll_notice.setBackgroundResource(R.drawable.ratu_home_notice);
                tv_home_home.setTextColor(Color.WHITE);
                tv_home_deposit.setTextColor(Color.WHITE);
                tv_home_withdraw.setTextColor(Color.WHITE);
                tv_home_live_chat.setTextColor(Color.WHITE);
                tv_home_logout.setTextColor(Color.WHITE);
            } else if (BuildConfig.FLAVOR.equals("depocasino")) {
                ll_top_parent.setBackgroundResource(R.drawable.ularnaga_top_bg);
                gd_ll_notice.setBackgroundResource(R.drawable.ratu_home_notice);
                tv_home_home.setTextColor(Color.WHITE);
                tv_home_deposit.setTextColor(Color.WHITE);
                tv_home_withdraw.setTextColor(Color.WHITE);
                tv_home_live_chat.setTextColor(Color.WHITE);
                tv_home_logout.setTextColor(Color.WHITE);
            } else if (BuildConfig.FLAVOR.equals("wargacasino")) {
                ll_top_parent.setBackgroundResource(R.drawable.ularnaga_top_bg);
                gd_ll_notice.setBackgroundResource(R.drawable.ratu_home_notice);
                tv_home_home.setTextColor(Color.WHITE);
                tv_home_deposit.setTextColor(Color.WHITE);
                tv_home_withdraw.setTextColor(Color.WHITE);
                tv_home_live_chat.setTextColor(Color.WHITE);
                tv_home_logout.setTextColor(Color.WHITE);
            } else if (BuildConfig.FLAVOR.equals("slotku")) {
                gd_ll_notice.setBackgroundResource(R.drawable.ratu_home_notice);//#AE0907
                tv_home_home.setTextColor(Color.parseColor("#D3AE48"));
                tv_home_deposit.setTextColor(Color.parseColor("#D3AE48"));
                tv_home_withdraw.setTextColor(Color.parseColor("#D3AE48"));
                tv_home_live_chat.setTextColor(Color.parseColor("#D3AE48"));
                tv_home_logout.setTextColor(Color.parseColor("#D3AE48"));
            }
        } else if (BuildConfig.FLAVOR.equals("kasino365")) {
            ll_bottom.setBackgroundColor(Color.parseColor("#050606"));
        } else if (BuildConfig.FLAVOR.equals("mainkasino")) {
            LinearLayout ll_top_parent = findViewById(R.id.ll_top_parent);
            ll_top_parent.setBackgroundResource(0);
            ll_bottom.setBackgroundColor(Color.parseColor("#93000000"));
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                LinearLayout ll_d_w = findViewById(R.id.ll_d_w);
                ImageView img_d = findViewById(R.id.img_d);
                ImageView img_w = findViewById(R.id.img_w);
                ll_d_w.setVisibility(View.VISIBLE);
                Glide.with(this).asGif().load(R.mipmap.deposit_gif).into(img_d);
                Glide.with(this).asGif().load(R.mipmap.withdraw_gif).into(img_w);
                img_d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goDeposit(v);
                    }
                });
                img_w.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goWithdraw(v);
                    }
                });
                fl_rtp = findViewById(R.id.fl_rtp);
                img_rtp = findViewById(R.id.img_rtp);
                fl_whatsapp = findViewById(R.id.fl_whatsapp);
                img_whatsapp = findViewById(R.id.img_whatsapp);
                tv_home_deposit.setVisibility(View.GONE);
                tv_home_withdraw.setVisibility(View.GONE);
                fl_rtp.setVisibility(View.VISIBLE);
                fl_whatsapp.setVisibility(View.VISIBLE);
                Glide.with(this).asGif().load(R.mipmap.rtp_slots_gif).into(img_rtp);
                Glide.with(this).asGif().load(R.mipmap.whatsapp_gif).into(img_whatsapp);
                fl_rtp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gd88Utils.goBrowser(mContext, "https://polamainkasino.com/");
                    }
                });
                fl_whatsapp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gd88Utils.goBrowser(mContext, "https://wa.me/855712533580");
                    }
                });
                main_tv_home_user_name = findViewById(R.id.main_tv_home_user_name);
                main_tv_home_balance = findViewById(R.id.main_tv_home_balance);
                main_tv_home_user_name.setText(mAppViewModel.getUser().getName());
                main_tv_home_balance.setText(Gd88Utils.formatToSepara(mAppViewModel.getUser().getBalance()));
                tv_home_user_name.setVisibility(View.GONE);
                tv_home_balance.setVisibility(View.GONE);
                ImageView gd_img_logo = findViewById(R.id.gd_img_logo);
                gd_img_logo.setMaxWidth(UIUtil.dip2px(mContext, 200));
                LinearLayout gd_ll_menu = findViewById(R.id.gd_ll_menu);
                gd_ll_menu.setVisibility(View.VISIBLE);
                gd_ll_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showLanguagePop(tv_lg, 0.75f);
                    }
                });
                tv_home_home.setVisibility(View.GONE);
            }
        } else if (BuildConfig.FLAVOR.equals("garudakasino")) {
            LinearLayout gd_ll_notice = findViewById(R.id.gd__ll_notice);
            gd_ll_notice.setBackgroundResource(R.drawable.ratu_home_notice);
            ll_bottom.setBackgroundColor(Color.parseColor("#9f1411"));
        }
    }

    private void goDeposit(View v) {
        int screenWidth = WidgetUtil.getPopScreenWidth(LobbyActivity.this);
        int width = screenWidth / 15 * 14;
        User u = mAppViewModel.getUser();
        DepositPop pop = new DepositPop(mContext, v, width, LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setDialog(dialog);
        pop.setUser(u);
        pop.showPopupCenterWindow();
    }

    private void goWithdraw(View v) {
        int screenWidth = WidgetUtil.getPopScreenWidth(LobbyActivity.this);
        int width = screenWidth / 15 * 14;
        User u = mAppViewModel.getUser();
        WithdrawPop p = new WithdrawPop(mContext, v, width, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.setDialog(dialog);
        p.setUser(u);
        p.showPopupCenterWindow();
    }

    BannerViewPager viewPager;

    private void setBanner() {
        setGameContent();
        RelativeLayout rl_banner = findViewById(R.id.rl_banner);
        viewPager = findViewById(R.id.auto_viewpager);
        LinearLayout inLayout = findViewById(R.id.in_layout);
        if (rl_banner != null && viewPager != null && inLayout != null) {
            new Thread() {
                @Override
                public void run() {
                    String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getSliderImg.jsp";
                    String result = mAppViewModel.getHttpClient().sendPost(url, "");
                    if (result.contains("Success")) {
                        BannerBean bannerBean = new Gson().fromJson(result, BannerBean.class);
                        List<BannerBean.DataBean> data = bannerBean.getData();
                        if (data != null && data.size() > 0) {
                            List<String> lists = new ArrayList<>();
                            for (int i = 0; i < data.size(); i++) {
                                BannerBean.DataBean dataBean = data.get(i);
                                lists.add(dataBean.getPath());
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    rl_banner.setVisibility(View.VISIBLE);
                                    BannerViewPagerAdapter adapter = new BannerViewPagerAdapter(lists, inLayout, LobbyActivity.this);
                                    viewPager.setAdapter(adapter);
                                    viewPager.addOnPageChangeListener(viewPager.listener);
                                }
                            });
                        }
                    }
                }
            }.start();
        }
    }

    LinearLayout ll_ahl_game_content;
    LinearLayout ll_banner;
    int contentHeight;
    int itemWidth;

    private void initAhlUi() {
        ll_banner = findViewById(R.id.ll_banner);
        ll_ahl_game_content = findViewById(R.id.ll_ahl_game_content);
        int itemWidth = view_item.getWidth();
        RecyclerView rc_liveCasino = findViewById(R.id.rc_liveCasino);
        RecyclerView rc_slotOnline = findViewById(R.id.rc_slotOnline);
        RecyclerView rc_sportBook_JudiBola = findViewById(R.id.rc_sportBook_JudiBola);
        RecyclerView rc_pokerOnline = findViewById(R.id.rc_pokerOnline);
        int spanCount = 4;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 8;
        }
        rc_liveCasino.setLayoutManager(new GridLayoutManager(mContext, spanCount));
        rc_slotOnline.setLayoutManager(new GridLayoutManager(mContext, spanCount));
        rc_sportBook_JudiBola.setLayoutManager(new GridLayoutManager(mContext, spanCount));
        rc_pokerOnline.setLayoutManager(new GridLayoutManager(mContext, spanCount));
        BaseRecyclerAdapter<HallGameItemBean> liveCasinoAdapterViewContent = getAdapterViewContent(itemWidth);
        BaseRecyclerAdapter<HallGameItemBean> slotOnlineAdapterViewContent = getAdapterViewContent(itemWidth);
        BaseRecyclerAdapter<HallGameItemBean> sportBook_JudiBolaAdapterViewContent = getAdapterViewContent(itemWidth);
        BaseRecyclerAdapter<HallGameItemBean> pokerOnlineAdapterViewContent = getAdapterViewContent(itemWidth);
        rc_liveCasino.setAdapter(liveCasinoAdapterViewContent);
        rc_slotOnline.setAdapter(slotOnlineAdapterViewContent);
        rc_sportBook_JudiBola.setAdapter(sportBook_JudiBolaAdapterViewContent);
        rc_pokerOnline.setAdapter(pokerOnlineAdapterViewContent);
        liveCasinoAdapterViewContent.addAllAndClear(Gd88Utils.ahlTypeLobbyGameList(LobbyActivity.this, Gd88Utils.LiveCasino));
        slotOnlineAdapterViewContent.addAllAndClear(Gd88Utils.ahlTypeLobbyGameList(LobbyActivity.this, Gd88Utils.SlotOnline));
        sportBook_JudiBolaAdapterViewContent.addAllAndClear(Gd88Utils.ahlTypeLobbyGameList(LobbyActivity.this, Gd88Utils.SportBook_JudiBola));
        pokerOnlineAdapterViewContent.addAllAndClear(Gd88Utils.ahlTypeLobbyGameList(LobbyActivity.this, Gd88Utils.PokerOnline));
        liveCasinoAdapterViewContent.setOnItemClickListener(getItemClickListener());
        slotOnlineAdapterViewContent.setOnItemClickListener(getItemClickListener());
        sportBook_JudiBolaAdapterViewContent.setOnItemClickListener(getItemClickListener());
        pokerOnlineAdapterViewContent.setOnItemClickListener(getItemClickListener());

        ahlBack = findViewById(R.id.ahl_lobby_back);
        ahlLlUser1 = findViewById(R.id.ahl_ll_user1);
        ahlLlUser2 = findViewById(R.id.ahl_ll_user2);
        ahlTvUser = findViewById(R.id.ahl_tv_home_user_name);
        if (ahlBack != null) {
            ahlBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (gridviewContentGv.getVisibility() == View.VISIBLE) {
                        closeAhlLocalGame();
                    }
                }
            });
        }
        if (ahlTvUser != null) {
            ahlTvUser.setText(mAppViewModel.getUser().getName());
        }
    }

    private void initCommonUi() {
        int count = 4;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            count = 8;
        }
        itemWidth = view_item.getWidth();
        if (!TextUtils.isEmpty(BuildConfig.FLAVOR) && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            LinearLayout ll_top_parent = findViewById(R.id.ll_top_parent);
            LinearLayout ll_top_1 = findViewById(R.id.ll_top_1);
            ImageView gd_img_logo = findViewById(R.id.gd_img_logo);
            if (ll_top_parent != null && ll_top_1 != null && gd_img_logo != null) {
                if (!BuildConfig.FLAVOR.equals("ahlicasino")) {
                    ViewGroup.LayoutParams layoutParams = ll_top_parent.getLayoutParams();
                    layoutParams.height = ll_top_1.getHeight() * 2;
                    ll_top_parent.setLayoutParams(layoutParams);
                    ll_top_1.setVisibility(View.GONE);
                    gd_img_logo.setVisibility(View.VISIBLE);
                }
            }
        } else {
            tv_home_close_game.setVisibility(View.GONE);
            ll_content_bg.setBackgroundResource(0);
            ll_parent.setGravity(Gravity.BOTTOM);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_content_bg.getLayoutParams();
            int size = count == 4 ? 2 : 1;
            int dp = 5;
            if (TextUtils.isEmpty(BuildConfig.FLAVOR)) {
                size = 1;
                dp = count == 4 ? 0 : 5;
            }
            params.height = ll_parent.getHeight() - itemWidth * size - UIUtil.dip2px(mContext, dp);
            ll_content_bg.setLayoutParams(params);
            clickItem = 0;
            ll_content_bg.setVisibility(View.VISIBLE);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, count);
        gridviewContentGv.setLayoutManager(layoutManager);
        adapterViewContent = new BaseRecyclerAdapter<HallGameItemBean>(mContext, new ArrayList<HallGameItemBean>(), R.layout.gd_item_hall_game) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, HallGameItemBean item) {
                RelativeLayout rl_parent = holder.getRelativeLayout(R.id.gd_rl_parent);
                ImageView imgTopLeftNew = holder.getImageView(R.id.img_top_left_new);
                ViewGroup.LayoutParams layoutParams = rl_parent.getLayoutParams();
                layoutParams.height = itemWidth;
                rl_parent.setLayoutParams(layoutParams);
                ImageView imageView = holder.getImageView(R.id.gd__hall_game_pic_iv);
                Bitmap bitmap = BitmapTool.toRoundCorner(BitmapFactory.decodeResource(getResources(), item.getImageRes()), ScreenUtil.dip2px(mContext, 5));
                imageView.setImageBitmap(bitmap);
                if (item.getGameType() == AppConfig.pragmatic || item.getGameType() == AppConfig.pg) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                } else {
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
                if (item.getGameType() == AppConfig.slots || item.getGameType() == AppConfig.afb_casino) {
                    imgTopLeftNew.setVisibility(View.VISIBLE);
                } else {
                    imgTopLeftNew.setVisibility(View.GONE);
                }
                TextView textView = holder.getTextView(R.id.gd__hall_game_title_tv);
                textView.setText(item.getTitle());
                if (position == clickItem) {
                    rl_parent.setBackgroundResource(R.mipmap.home_game_select);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.home_select_color));
                } else {
                    if (BuildConfig.FLAVOR.equals("hokicasino88") || BuildConfig.FLAVOR.equals("doacasino") || BuildConfig.FLAVOR.equals("ularnaga") ||
                            BuildConfig.FLAVOR.equals("ratucasino88") || BuildConfig.FLAVOR.equals("depocasino") || BuildConfig.FLAVOR.equals("wargacasino") ||
                            BuildConfig.FLAVOR.equals("slotku") || BuildConfig.FLAVOR.equals("ahlicasino")) {
                        rl_parent.setBackgroundResource(R.drawable.hokicasino_home_item);
                    } else {
                        rl_parent.setBackgroundResource(R.mipmap.home_game_no_select);
                    }
                    if (BuildConfig.FLAVOR.equals("hitamslot") || BuildConfig.FLAVOR.equals("ratucasino88") || BuildConfig.FLAVOR.equals("depocasino") ||
                            BuildConfig.FLAVOR.equals("garudakasino") || BuildConfig.FLAVOR.equals("ahlicasino")) {
                        textView.setTextColor(Color.WHITE);
                    } else if (BuildConfig.FLAVOR.equals("ularnaga")) {
                        textView.setTextColor(ContextCompat.getColor(mContext, R.color.ularnaga_home_no_select_color));
                    } else if (BuildConfig.FLAVOR.equals("wargacasino")) {
                        textView.setTextColor(Color.parseColor("#6D4709"));
                    } else {
                        textView.setTextColor(ContextCompat.getColor(mContext, R.color.home_no_select_color));
                    }
                }
            }
        };
        gridviewContentGv.setAdapter(adapterViewContent);
        setAdapterData();
        adapterViewContent.setOnItemClickListener(getItemClickListener());
    }

    private BaseRecyclerAdapter<HallGameItemBean> getAdapterViewContent(int itemWidth) {
        return new BaseRecyclerAdapter<HallGameItemBean>(mContext, new ArrayList<HallGameItemBean>(), R.layout.gd_item_hall_game) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, HallGameItemBean item) {
                RelativeLayout rl_parent = holder.getRelativeLayout(R.id.gd_rl_parent);
                ImageView imgTopLeftNew = holder.getImageView(R.id.img_top_left_new);
                ViewGroup.LayoutParams layoutParams = rl_parent.getLayoutParams();
                layoutParams.height = itemWidth;
                rl_parent.setLayoutParams(layoutParams);
                ImageView imageView = holder.getImageView(R.id.gd__hall_game_pic_iv);
                Bitmap bitmap = BitmapTool.toRoundCorner(BitmapFactory.decodeResource(getResources(), item.getImageRes()), ScreenUtil.dip2px(mContext, 5));
                imageView.setImageBitmap(bitmap);
                if (item.getGameType() == AppConfig.pragmatic || item.getGameType() == AppConfig.pg) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                } else {
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
                if (item.getGameType() == AppConfig.slots || item.getGameType() == AppConfig.afb_casino) {
                    imgTopLeftNew.setVisibility(View.VISIBLE);
                } else {
                    imgTopLeftNew.setVisibility(View.GONE);
                }
                TextView textView = holder.getTextView(R.id.gd__hall_game_title_tv);
                textView.setText(item.getTitle());
                rl_parent.setBackgroundResource(R.drawable.hokicasino_home_item);
                textView.setTextColor(Color.WHITE);
            }
        };
    }

    private BaseRecyclerAdapter.OnItemClickListener<HallGameItemBean> getItemClickListener() {
        return new BaseRecyclerAdapter.OnItemClickListener<HallGameItemBean>() {
            @Override
            public void onItemClick(View view, HallGameItemBean hallGameItemBean, int position) {
                if (position < 4) {
                    adapterViewContent.notifyDataSetChanged();
                }
                int count = 3;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    count = 7;
                }
                if (!isCanShowGame() && position > count) {
                    return;
                }
                AppTool.setAppLanguage(LobbyActivity.this, AppTool.getAppLanguage(LobbyActivity.this));
                if (hallGameItemBean.getGameType() == AppConfig.baccarat) {
                    showGameContent(0, getString(R.string.baccarat));
                } else if (hallGameItemBean.getGameType() == AppConfig.dragon_tiger) {
                    showGameContent(1, getString(R.string.dragon_tiger));
                } else if (hallGameItemBean.getGameType() == AppConfig.roulette) {
                    showGameContent(2, getString(R.string.roulette));
                } else if (hallGameItemBean.getGameType() == AppConfig.sicbo) {
                    showGameContent(3, getString(R.string.sicbo));
                } else if (hallGameItemBean.getGameType() == AppConfig.slots) {
                    skipAct(SlotsGameActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.cq9) {
                    skipAct(CQSlotsGameActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.cockfighting) {
                    skipAct(CockFightingWebActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.afb1188) {
                    goAfb1188();
                } else if (hallGameItemBean.getGameType() == AppConfig.dsv_casino) {
                    skipAct(DsvCasinoActivity.class);
                } else if (hallGameItemBean.getGameType() == AppConfig.we1poker) {
                    skipAct(We1PokerWebActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.pragmatic) {
                    skipAct(PragmaticGameActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.kingKong) {
                    skipAct(KingKongGameActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.bandarq) {
                    if (!TextUtils.isEmpty(hallGameItemBean.getBrowserUrl())) {
                        Gd88Utils.goBrowser(mContext, hallGameItemBean.getBrowserUrl());
                    }
                } else if (hallGameItemBean.getGameType() == AppConfig.lg88) {
                    skipAct(LG88WebActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.haba) {
                    skipAct(HabaGameActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.pg) {
                    skipAct(PGGameActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.afb_casino) {
                    skipAct(AfbCasinoActivity.class);
                    finish();
                } else if (hallGameItemBean.getGameType() == AppConfig.gd_casino) {
                    showAhlLocalGame();
                } else if (hallGameItemBean.getGameType() == AppConfig.pragmatic_casino) {
                    skipAct(PragmaticCasinoActivity.class);
                    finish();
                }

            }
        };
    }

    private void showAhlLocalGame() {
        if (ahlBack != null) {
            ahlLlUser1.setVisibility(View.GONE);
            ahlBack.setVisibility(View.VISIBLE);
            ahlLlUser2.setVisibility(View.VISIBLE);
        }
        ll_ahl_game_content.setVisibility(View.GONE);
        gridviewContentGv.setVisibility(View.VISIBLE);
        ll_banner.setVisibility(View.GONE);
        int height = ll_parent.getHeight();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_content_bg.getLayoutParams();
        if (height != params.height) {
            tv_home_close_game.setVisibility(View.GONE);
            ll_content_bg.setBackgroundResource(0);
            ll_parent.setGravity(Gravity.BOTTOM);
            int dp = 5;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                dp = 35;
            }
            params.height = ll_parent.getHeight() - itemWidth - UIUtil.dip2px(mContext, dp);
            ll_content_bg.setLayoutParams(params);
        }
        clickItem = 0;
        ll_content_bg.setVisibility(View.VISIBLE);
    }

    private void closeAhlLocalGame() {
        if (ahlBack != null) {
            ahlLlUser1.setVisibility(View.VISIBLE);
            ahlBack.setVisibility(View.GONE);
            ahlLlUser2.setVisibility(View.GONE);
        }
        ll_ahl_game_content.setVisibility(View.VISIBLE);
        gridviewContentGv.setVisibility(View.GONE);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ll_banner.setVisibility(View.VISIBLE);
        }
        ll_content_bg.setVisibility(View.INVISIBLE);
        if (clickItem != 0) {
            showGameContent(0, getString(R.string.baccarat));
        }
    }

    private void setGameContent() {
        ViewTreeObserver vto = view_item.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view_item.getViewTreeObserver().removeOnPreDrawListener(this);
                if (BuildConfig.FLAVOR.equals("ahlicasino")) {
                    initAhlUi();
                }
                initCommonUi();
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewPager != null) {
            viewPager.stopTask();
        }
    }

    private boolean isOpenGame = true;

    private boolean isCanShowGame() {
        return isOpenGame;
    }

    private void openGame() {
        WidgetUtil.translateAnimation(ll_content_bg, 0, -contentHeight, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isOpenGame = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void closeGame() {
        WidgetUtil.translateAnimation(ll_content_bg, -contentHeight, 0, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isOpenGame = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initGameWayHeight() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_content_bg.getLayoutParams();
        if (params.height <= 0) {
            int height = ll_parent.getHeight();
            int dp = 6;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                dp = 10;
            }
            contentHeight = height - itemWidth - UIUtil.dip2px(mContext, dp);
            params.height = contentHeight;
            params.topMargin = height;
            ll_content_bg.setLayoutParams(params);
        }
        if (ll_content_bg.getVisibility() != View.VISIBLE) {
            ll_content_bg.setVisibility(View.VISIBLE);
        }
    }

    private void showGameContent(int type, String name) {
        if (!TextUtils.isEmpty(BuildConfig.FLAVOR) && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365") && !BuildConfig.FLAVOR.equals("ahlicasino")) {
            initGameWayHeight();
        }
        clickItem = type;
        if (lastIndex == type) {
            if (!TextUtils.isEmpty(BuildConfig.FLAVOR) && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365") && !BuildConfig.FLAVOR.equals("ahlicasino")) {
                if (isCanShowGame()) {
                    openGame();
                }
            }
            return;
        }
        tv_home_close_game.setText(name);
        switchFragment(type);
        if (!TextUtils.isEmpty(BuildConfig.FLAVOR) && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365") && !BuildConfig.FLAVOR.equals("ahlicasino")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isCanShowGame()) {
                        openGame();
                    }
                }
            }, 100);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    private void initBar() {
        toolbar.setVisibility(View.GONE);
        tv_home_user_name.setText(mAppViewModel.getUser().getName());
        tv_home_balance.setText(Gd88Utils.formatToSepara(mAppViewModel.getUser().getBalance()));
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
        ArrayList<HallGameItemBean> hallGameItemBeenS = new ArrayList<>();
        if (WebSiteUrl.isDomain && WebSiteUrl.GameType != 3) {
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.home_b, getString(R.string.baccarat), AppConfig.baccarat));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.home_dt, getString(R.string.dragon_tiger), AppConfig.dragon_tiger));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.home_r, getString(R.string.roulette), AppConfig.roulette));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.home_s, getString(R.string.sicbo), AppConfig.sicbo));
        } else {
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.home_b, getString(R.string.baccarat), AppConfig.baccarat));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.home_dt, getString(R.string.dragon_tiger), AppConfig.dragon_tiger));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.home_r, getString(R.string.roulette), AppConfig.roulette));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.home_s, getString(R.string.sicbo), AppConfig.sicbo));

            if (!BuildConfig.FLAVOR.equals("ahlicasino")) {
                hallGameItemBeenS.addAll(Gd88Utils.getLobbyGameList(this));
            }

            if (BuildConfig.FLAVOR.equals("oricasino") || BuildConfig.FLAVOR.equals("wargacasino") || BuildConfig.FLAVOR.equals("ratucasino88") ||
                    BuildConfig.FLAVOR.equals("depocasino") || BuildConfig.FLAVOR.equals("ularnaga") || BuildConfig.FLAVOR.equals("mainkasino")) {
                if (BuildConfig.FLAVOR.equals("oricasino")) {
                    hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_bandarq, getString(R.string.bandarq), AppConfig.bandarq, "http://202.95.10.249/"));
                } else if (BuildConfig.FLAVOR.equals("wargacasino")) {
                    hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_bandarq, getString(R.string.bandarq), AppConfig.bandarq, "http://202.95.10.248/"));
                } else if (BuildConfig.FLAVOR.equals("ratucasino88")) {
                    hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_bandarq, getString(R.string.bandarq), AppConfig.bandarq, "https://b.link/arenaqq"));
                } else if (BuildConfig.FLAVOR.equals("depocasino")) {
                    hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_bandarq, getString(R.string.bandarq), AppConfig.bandarq, "https://b.link/adaqq"));
                } else if (BuildConfig.FLAVOR.equals("ularnaga")) {
                    hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_bandarq, getString(R.string.bandarq), AppConfig.bandarq, "https://b.link/adaqq"));
                } else if (BuildConfig.FLAVOR.equals("mainkasino")) {
                    hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_warungqq, "WARUNGQQ", AppConfig.bandarq, "https://45.76.184.5/core/m/?ref=mainkasinoapp"));
                }
            }
        }
        if (adapterViewContent != null) {
            adapterViewContent.addAllAndClear(hallGameItemBeenS);
        }
        if (adapterViewContent != null) {
            if (adapterViewContent.getItemCount() == 0) {
                gridviewContentGv.setVisibility(View.GONE);
            }
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
        hallGameBottomPromptTv.startScroll();


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
//        super.leftClick();
        if (BuildConfig.FLAVOR.equals("ahlicasino")) {
            if (gridviewContentGv.getVisibility() == View.VISIBLE) {
                closeAhlLocalGame();
                return;
            }
            popLogout();
            return;
        }
        bGetGameStatus = false;
        bGetGameTimer = false;
        mAppViewModel.setbLogin(false);
        logout();
    }

    private void popLogout() {
        int screenWidth = WidgetUtil.getPopScreenWidth(this);
        int width = screenWidth / 15 * 14;
        PopLogout popLogout = new PopLogout(mContext, tv_home_balance, width, UIUtil.dip2px(mContext, 130));
        popLogout.showPopupCenterWindow();
    }

    int clickItem = -1;
    int lastIndex = -1;

    private void switchFragment(int index) {
        if (isFinishing() || isDestroyed())
            return;
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
//        if (!WidgetUtil.isRunBackground(this)) {
//            if (currentFragment != null) {
//                getSupportFragmentManager().beginTransaction().remove(currentFragment).commitAllowingStateLoss();
//            }
//        }
//        super.onSaveInstanceState(outState);
    }

}
