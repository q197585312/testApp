package nanyang.com.dig88.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Activity.CockfightActivity;
import nanyang.com.dig88.Activity.DgCasinoActivity;
import nanyang.com.dig88.Activity.MainTabActivity;
import nanyang.com.dig88.Activity.MsgBoxActivity;
import nanyang.com.dig88.Activity.RegisterActivity;
import nanyang.com.dig88.Activity.SaGamingActivity;
import nanyang.com.dig88.Activity.SexyCasinoActivity;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Fragment.DepositCenterFragment;
import nanyang.com.dig88.Home.Bean.HomeContentListBean;
import nanyang.com.dig88.Home.Bean.LoginStatusBean;
import nanyang.com.dig88.Home.Presenter.MenuHomeFragmentPresenter;
import nanyang.com.dig88.Login.LoginActivity;
import nanyang.com.dig88.NewKeno.NewKenoActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.SlotsGame.SlotsGameActivity;
import nanyang.com.dig88.Util.AutoScrollLayout;
import nanyang.com.dig88.Util.AutoScrollViewPager;
import nanyang.com.dig88.Util.DialogChangePassword;
import nanyang.com.dig88.Util.DialogRegisterFirstIn;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.PopWin3888;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.ViewPagerAdapter;

/**
 * Created by Administrator on 2019/6/20.
 */

public class MenuHomeFragment extends BaseFragment<MenuHomeFragmentPresenter> {
    @BindView(R.id.rc_home_content)
    RecyclerView rcHomeContent;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R.id.ll_no_login)
    LinearLayout llNoLogin;
    @BindView(R.id.rl_already_login)
    RelativeLayout rl_already_login;
    @BindView(R.id.autoScrollLayout)
    AutoScrollLayout autoScrollLayout;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_toolbar_right)
    TextView tvToolbarRight;
    @BindView(R.id.img_toolbar_left)
    ImageView imgToolbarLeft;
    @BindView(R.id.home_viewpager)
    AutoScrollViewPager homeViewpager;
    @BindView(R.id.ll_indicator)
    LinearLayout llIndicator;
    @BindView(R.id.tv_home_register)
    TextView tvHomeRegister;
    @BindView(R.id.rl_facebook)
    RelativeLayout rlFacebook;
    @BindView(R.id.tv_home_login)
    TextView tvHomeLogin;
    @BindView(R.id.ll_home_deposit)
    LinearLayout llHomeDeposit;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.rl_message)
    RelativeLayout rl_message;
    @BindView(R.id.tv_msg_count)
    TextView tvMsgCount;
    BaseRecyclerAdapter<HomeContentListBean> adapter;
    int rcContentHeight;
    int rowSize;
    private boolean isCanRefreshStatus = false;

    @Override
    public void refreshMoney(String money) {
        double amount = Double.valueOf(money);
        String result = String.format("%.2f", amount);
        tvMoney.setText(result + "(" + getCurrency() + ")");
    }

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_memu_home;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new MenuHomeFragmentPresenter(this));
        tvToolbarRight.setBackgroundResource(presenter.switchLanguage());
        EventBus.getDefault().register(this);
        rcHomeContent.post(new Runnable() {
            @Override
            public void run() {
                rcContentHeight = rcHomeContent.getHeight();
                if (hasLoginInfo()) {
                    initAlreadyLoginContent();
                } else {
                    initNoLoginContent();
                }
            }
        });
        presenter.getNoticeData();
        imgToolbarLeft.setVisibility(View.INVISIBLE);
        tvToolbarTitle.setBackgroundResource(R.mipmap.login_logo);
        presenter.getBannerData();
    }

    private void initHomeRcContent(List<HomeContentListBean> list) {
        if (adapter == null) {
            adapter = new BaseRecyclerAdapter<HomeContentListBean>(mContext, list, R.layout.item_home_content) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, HomeContentListBean item) {
                    SimpleDraweeView pic = holder.getView(R.id.iv_game_picture);
                    GenericDraweeHierarchy hierarchy = pic.getHierarchy();
                    hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
                    if (UIUtil.isPad(mContext)) {
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) pic.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rcContentHeight / rowSize);
                        } else {
                            layoutParams.height = rcContentHeight / rowSize;
                        }
                        pic.setLayoutParams(layoutParams);
                    }
                    Uri uri = Uri.parse("res://mipmap-xhdpi/" + item.getGamePic());
                    pic.setImageURI(uri);
                    TextView name = holder.getView(R.id.tv_game_name);
                    name.setText(item.getGameName());
                }
            };
            rcHomeContent.setLayoutManager(new GridLayoutManager(mContext, 3));
            rcHomeContent.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<HomeContentListBean>() {
                @Override
                public void onItemClick(View view, HomeContentListBean item, int position) {
                    String gameType = item.getGameType();
                    if (!hasLoginInfo() && !gameType.equals(AppConfig.Promotions) && !gameType.equals(AppConfig.LiveScore)) {
                        ToastUtils.showShort(getString(R.string.please_login));
                        return;
                    }
                    switch (gameType) {
                        case AppConfig.Promotions:
                            Intent intent = new Intent(mContext, GameWebActivity.class);
                            intent.putExtra("url", presenter.getPromotionsUrl());
                            intent.putExtra("title", item.getGameName());
                            startActivity(intent);
                            break;
                        case AppConfig.LiveScore:
                            Intent liveScoreIntent = new Intent(mContext, GameWebActivity.class);
                            liveScoreIntent.putExtra("url", presenter.getLiveScoreUrl());
                            liveScoreIntent.putExtra("title", item.getGameName());
                            startActivity(liveScoreIntent);
                            break;
                        case AppConfig.News:
                            Intent newsIntent = new Intent(mContext, GameWebActivity.class);
                            newsIntent.putExtra("url", presenter.getNewsUrl());
                            newsIntent.putExtra("title", item.getGameName());
                            startActivity(newsIntent);
                            break;
                        case AppConfig.Forex:
                            presenter.openUrl(presenter.getForexUrl(getUserInfo().getSession_id()));
                            break;
                        case AppConfig.More:
                            ToastUtils.showShort(getString(R.string.Under_development));
                            break;
                        case AppConfig.AFB1188_GAME:
                            presenter.enterAfb1188(gameType);
                            break;
                        case AppConfig.GD_GAME:
                            presenter.enterGd88();
                            break;
                        case AppConfig.DG_GAME:
                            startActivity(new Intent(mContext, DgCasinoActivity.class));
                            break;
                        case AppConfig.SAGaming_GAME:
                            startActivity(new Intent(mContext, SaGamingActivity.class));
                            break;
                        case AppConfig.SexyCasino_GAME:
                            startActivity(new Intent(mContext, SexyCasinoActivity.class));
                            break;
                        case AppConfig.NEW_KENO_GAME:
                            startActivity(new Intent(mContext, NewKenoActivity.class));
                            break;
                        case AppConfig.Cockfight:
                            AppTool.activiyJump(mContext, CockfightActivity.class);
                            break;
                        case AppConfig.ONGDO_Poker:
                            Dig88Utils.setLang(mContext);
                            presenter.enterIbetPoker(getString(R.string.transfer_ongdo_poker), "10", gameType, rcHomeContent);
                            break;
                        case AppConfig.Poker_Poker:
                            Dig88Utils.setLang(mContext);
                            presenter.enterIbetPoker(getString(R.string.transfer_poker_poker), "5", gameType, rcHomeContent);
                            break;
                        case AppConfig.HAM_Poker:
                            Dig88Utils.setLang(mContext);
                            presenter.enterIbetPoker(getString(R.string.transfer_ham_poker), "5", gameType, rcHomeContent);
                            break;
                        case AppConfig.TIENLEN_Poker:
                            Dig88Utils.setLang(mContext);
                            presenter.enterIbetPoker(getString(R.string.transfer_tienlen_poker), "5", gameType, rcHomeContent);
                            break;
                        case AppConfig.KLA_KLOUK_Poker:
                            Dig88Utils.setLang(mContext);
                            presenter.enterIbetPoker(getString(R.string.transfer_kla_klouk_poker), "5", gameType, rcHomeContent);
                            break;
                        case AppConfig.SIKUTHAI_Poker:
                            Dig88Utils.setLang(mContext);
                            presenter.enterIbetPoker(getString(R.string.transfer_sikuthai_Poker), "5", gameType, rcHomeContent);
                            break;
                        case AppConfig.FISHING_GAME:
                            Intent gameIntent = new Intent(mContext, SlotsGameActivity.class);
                            gameIntent.putExtra("HomeContentListBean", item);
                            startActivity(gameIntent);
                            break;
                        case AppConfig.Cockfight_X:
                            ToastUtils.showShort(getString(R.string.cockfight_x_hint));
                            break;
                        case AppConfig.LiveChat:
                            Intent liveChatIntent = new Intent(mContext, GameWebActivity.class);
                            liveChatIntent.putExtra("url", presenter.getLiveChatUrl());
                            liveChatIntent.putExtra("title", item.getGameName());
                            startActivity(liveChatIntent);
                            break;
                        default:
                            List<String> gameList = item.getGameList();
                            if (gameList != null) {
                                Intent i = new Intent(mContext, GameContentActivity.class);
                                i.putExtra("HomeContentListBean", item);
                                startActivity(i);
                            }
                            break;
                    }
                }
            });
        } else {
            adapter.setData(list);
        }
    }

    public void onGetNoticeData(String content) {
        if (autoScrollLayout != null) {
            autoScrollLayout.setScreenWidth(screenWidth);
            autoScrollLayout.setContent(content);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    autoScrollLayout.startAnimation();
                }
            }, 1000);
        }
    }

    private void initRowSize(int dataSize) {
        if (dataSize % 3 == 0) {
            rowSize = dataSize / 3;
        } else {
            rowSize = dataSize / 3 + 1;
        }
    }

    private void initNoLoginContent() {
        presenter.getLoginContentData(getAct().getApp().getAffiliateId(), 0);
        llNotice.setBackgroundResource(R.drawable.notice_bg);
        rl_already_login.setVisibility(View.GONE);
        llNoLogin.setVisibility(View.VISIBLE);
    }

    private void initAlreadyLoginContent() {
        if (getApp().isRegisterFirstIn()) {
            DialogRegisterFirstIn dialogRegisterFirstIn = new DialogRegisterFirstIn(getAct()) {
                @Override
                public void onClickIn() {
                    MainTabActivity activity = (MainTabActivity) getActivity();
                    activity.switchDepositUi();
                }
            };
            dialogRegisterFirstIn.show();
        }
        int set = getUserInfo().getSet();
        presenter.getVipInfoBean(getUserInfo());
        if (set == 1) {
            DialogChangePassword dialogChangePassword = new DialogChangePassword(getAct());
            dialogChangePassword.show();
        }
        llNotice.setBackgroundResource(R.drawable.notice_bg1);
        rl_already_login.setVisibility(View.VISIBLE);
        llNoLogin.setVisibility(View.GONE);
        LoginInfoBean loginInfoBean = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        String username = loginInfoBean.getUsername();
        tvUsername.setText(username);
        presenter.getLoginContentData(getAct().getApp().getAffiliateId(), 1);
    }

    public void onGetNoLoginContentData(List<HomeContentListBean> beanList) {
        initRowSize(beanList.size());
        initHomeRcContent(beanList);
    }

    public void onGetAlreadyLoginContentData(List<HomeContentListBean> beanList) {
        isCanRefreshStatus = true;
        initRowSize(beanList.size());
        initHomeRcContent(beanList);
    }

    public void onGetBannerData(List<Object> dataList) {
        llIndicator.removeAllViews();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(dataList, llIndicator, mContext);
        homeViewpager.removeAllViews();
        homeViewpager.setAdapter(viewPagerAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvenBusMessage(LoginStatusBean loginStatusBean) {
        String loginStatus = loginStatusBean.getLoginStatus();
        if (loginStatus.equals("1")) {//1登录成功 2home界面注册跳转到登录
            initAlreadyLoginContent();
        } else if (loginStatus.equals("2")) {
            Intent i = new Intent(mContext, LoginActivity.class);
            i.putExtra("LoginStatusBean", loginStatusBean);
            startActivity(i);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (autoScrollLayout != null) {
            autoScrollLayout.stopAnimation();
        }
    }

    @OnClick({R.id.tv_home_login, R.id.ll_home_deposit, R.id.tv_home_register, R.id.ll_right_click, R.id.rl_facebook, R.id.img_message})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_login:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.ll_home_deposit:
                Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                intent.putExtra("type", DepositCenterFragment.class.getName());
                startActivity(intent);
                break;
            case R.id.tv_home_register:
                Intent i = new Intent(mContext, RegisterActivity.class);
                i.putExtra("isNeedSend", "isNeedSend");
                startActivity(i);
                break;
            case R.id.ll_right_click:
                startActivity(new Intent(mContext, SwitchLanguageActivity.class));
                break;
            case R.id.rl_facebook:
                Intent loginIntent = new Intent(mContext, LoginActivity.class);
                loginIntent.putExtra("isFacebookLogin", "isFacebookLogin");
                startActivity(loginIntent);
                break;
            case R.id.img_message:
                startActivity(new Intent(mContext, MsgBoxActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (hasLoginInfo() && isCanRefreshStatus) {
            presenter.getLoginContentData(getAct().getApp().getAffiliateId(), 1);
        }
    }

    @Override
    public void showMsgCount(String msg) {
        super.showMsgCount(msg);
        if (!msg.equals("0")) {
            tvMsgCount.setVisibility(View.VISIBLE);
            tvMsgCount.setText(msg);
            imgMessage.setImageResource(R.mipmap.home_message);
        } else {
            tvMsgCount.setVisibility(View.GONE);
            imgMessage.setImageResource(R.mipmap.notice);
        }
    }
}
