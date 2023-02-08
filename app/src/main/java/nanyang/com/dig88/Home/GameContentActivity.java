package nanyang.com.dig88.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;


import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Activity.AFBCasinoActivity;
import nanyang.com.dig88.Activity.AfbsportsActivity;
import nanyang.com.dig88.Activity.AgCasinoActivity;
import nanyang.com.dig88.Activity.AllBetActivity;
import nanyang.com.dig88.Activity.AutoNumberActivity;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.ChinaLotteryActivity;
import nanyang.com.dig88.Activity.CockfightActivity;
import nanyang.com.dig88.Activity.DgCasinoActivity;
import nanyang.com.dig88.Activity.EvoCasinoActivity;
import nanyang.com.dig88.Activity.GoldCasinoActivity;
import nanyang.com.dig88.Activity.IgLotteryActivity;
import nanyang.com.dig88.Activity.IsportsActivity;
import nanyang.com.dig88.Activity.LiveDigNumberActivity;
import nanyang.com.dig88.Activity.N2CasinoActivity;
import nanyang.com.dig88.Activity.PPCasinoActivity;
import nanyang.com.dig88.Activity.SaGamingActivity;
import nanyang.com.dig88.Activity.SexyCasinoActivity;
import nanyang.com.dig88.Activity.SsportsActivity;
import nanyang.com.dig88.Activity.W88CasinoActivity;
import nanyang.com.dig88.Activity.WMCasinoActivity;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Home.Bean.HomeContentListBean;
import nanyang.com.dig88.Home.Presenter.GameContentPresenter;
import nanyang.com.dig88.Keno.KenoHomeActivity;
import nanyang.com.dig88.Lottery.LotteryActivity;
import nanyang.com.dig88.Lottery4D.Lottery4DActivity;
import nanyang.com.dig88.NewKeno.NewKenoActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.SlotsGame.SlotsGameActivity;
import nanyang.com.dig88.ThaiLottery.ThaiLotteryActivity;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.Pop918Kiss;
import nanyang.com.dig88.Util.PopFfylTransfer;
import nanyang.com.dig88.Util.PopGpPokerTransfer;
import nanyang.com.dig88.Util.PopJDBTransfer;
import nanyang.com.dig88.Util.PopKYPokerTransfer;
import nanyang.com.dig88.Util.PopKlasPokerTransfer;
import nanyang.com.dig88.Util.PopMega888Transfer;
import nanyang.com.dig88.Util.PopXe88Transfer;
import nanyang.com.dig88.Util.UIUtil;

/**
 * Created by 47184 on 2019/6/25.
 */

public class GameContentActivity extends BaseActivity<GameContentPresenter> {
    @BindView(R.id.iv_game_picture)
    SimpleDraweeView simpleDraweeView;
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<HomeContentListBean> adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_game_content;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new GameContentPresenter(this));
        Intent intent = getIntent();
        setleftViewEnable(true);
        HomeContentListBean homeContentListBean = (HomeContentListBean) intent.getSerializableExtra("HomeContentListBean");
        setTitle(homeContentListBean.getGameName());
        int bannerPic = presenter.getBannerPic(homeContentListBean.getGameType());
        if (bannerPic != 0) {
            GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
            hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
            simpleDraweeView.setImageURI(Uri.parse("res://mipmap-xhdpi/" + bannerPic));
        }
        adapter = new BaseRecyclerAdapter<HomeContentListBean>(mContext, presenter.getContent(homeContentListBean.getGameList()), R.layout.item_game_content) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, HomeContentListBean item) {
                SimpleDraweeView pic = holder.getView(R.id.iv_game_picture);
                SimpleDraweeView picRight = holder.getView(R.id.iv_game_right);
                GenericDraweeHierarchy hierarchy = pic.getHierarchy();
                hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
                if (UIUtil.isPad(mContext)) {
                    int iPadContentHeight = UIUtil.dip2px(mContext, 160);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) pic.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iPadContentHeight);
                    } else {
                        layoutParams.height = iPadContentHeight;
                    }
                    pic.setLayoutParams(layoutParams);
                }
                Uri uri = Uri.parse("res://mipmap-xhdpi/" + item.getGamePic());
                pic.setImageURI(uri);
                TextView name = holder.getView(R.id.tv_game_name);
                name.setText(item.getGameName());
                if (getLocalLanguage().equals("in")) {
                    if (item.getGameType().equals(AppConfig.AFB1188_GAME) || item.getGameType().equals(AppConfig.AFB1188_Desktop_GAME) ||
                            item.getGameType().equals(AppConfig.GD_GAME) || item.getGameType().equals(AppConfig.BEST_GAME) ||
                            item.getGameType().equals(AppConfig.We1poker)) {
                        int gif;
                        if (item.getGameType().equals(AppConfig.AFB1188_GAME) || item.getGameType().equals(AppConfig.AFB1188_Desktop_GAME) ||
                                item.getGameType().equals(AppConfig.GD_GAME)) {
                            gif = R.mipmap.hot_gif;
                        } else {
                            gif = R.mipmap.new_gif;
                        }
                        Uri uriRight = new Uri.Builder()
                                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                                .path(String.valueOf(gif))
                                .build();
                        DraweeController draweeController =
                                Fresco.newDraweeControllerBuilder()
                                        .setUri(uriRight)
                                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                                        .build();
                        picRight.setController(draweeController);
                        picRight.setVisibility(View.VISIBLE);
                    } else {
                        picRight.setVisibility(View.GONE);
                    }
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<HomeContentListBean>() {
            @Override
            public void onItemClick(View view, HomeContentListBean item, int position) {
                AppTool.setAppLanguage(mContext, AppTool.getAppLanguage(mContext));
                switch (item.getGameType()) {
                    case AppConfig.AFB_GAME:
                        presenter.loginSport();
                        break;
                    case AppConfig.AFB_h5_GAME:
                    case AppConfig.AFB_h5_Desktop_GAME:
                        Intent afb88H5Intent = new Intent(mContext, AfbsportsActivity.class);
                        if (item.getGameType().equals(AppConfig.AFB_h5_GAME)) {
                            afb88H5Intent.putExtra("sportType", "3G");
                        } else {
                            afb88H5Intent.putExtra("sportType", "Desktop");
                        }
                        startActivity(afb88H5Intent);
                        break;
                    case AppConfig.AFB1188_GAME:
                    case AppConfig.AFB1188_Desktop_GAME:
                        presenter.enterAfb1188(item.getGameType());
                        break;
                    case AppConfig.IBC_GAME:
                        startActivity(new Intent(mContext, IsportsActivity.class));
                        break;
                    case AppConfig.SBO_GAME:
                        startActivity(new Intent(mContext, SsportsActivity.class));
                        break;
                    case AppConfig.GD_GAME:
                        presenter.enterGd88();
                        break;
                    case AppConfig.W88_Casino_GAME:
                        startActivity(new Intent(mContext, W88CasinoActivity.class));
                        break;
                    case AppConfig.GOLD_GAME:
                        startActivity(new Intent(mContext, GoldCasinoActivity.class));
                        break;
                    case AppConfig.AllBet_GAME:
                        startActivity(new Intent(mContext, AllBetActivity.class));
                        break;
                    case AppConfig.AG_GAME:
                        startActivity(new Intent(mContext, AgCasinoActivity.class));
                        break;
                    case AppConfig.DG_GAME:
                        startActivity(new Intent(mContext, DgCasinoActivity.class));
                        break;
                    case AppConfig.SAGaming_GAME:
                        startActivity(new Intent(mContext, SaGamingActivity.class));
                        break;
                    case AppConfig.WM_Casino_GAME:
                        startActivity(new Intent(mContext, WMCasinoActivity.class));
                        break;
                    case AppConfig.SexyCasino_GAME:
                        startActivity(new Intent(mContext, SexyCasinoActivity.class));
                        break;
                    case AppConfig.EVO_Casino_GAME:
                        startActivity(new Intent(mContext, EvoCasinoActivity.class));
                        break;
                    case AppConfig.BEST_GAME:
                    case AppConfig.HABA_GAME:
                    case AppConfig.SAgaming_Slots_GAME:
                    case AppConfig.Joker123_GAME:
                    case AppConfig.W88_GAME:
                    case AppConfig.PT_GAME:
                    case AppConfig.MG_GAME:
                    case AppConfig.FISHING_GAME:
                    case AppConfig.PP_Slots:
                    case AppConfig.RTG_Slots:
                    case AppConfig.NETENT_GAME:
                    case AppConfig.PLAYSTAR_SLOTS:
                    case AppConfig.PG_SLOTS:
                        Intent intent = new Intent(mContext, SlotsGameActivity.class);
                        intent.putExtra("HomeContentListBean", item);
                        startActivity(intent);
                        break;
                    case AppConfig.POKER_GAME:
                        PopGpPokerTransfer popGpPokerTransfer = new PopGpPokerTransfer(mContext, rcContent, screenWidth / 5 * 4, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popGpPokerTransfer.showPopupAtLocation(popIndexX(), popIndexY(view));
                        break;
                    case AppConfig.KLAS_Poker:
                        PopKlasPokerTransfer popKlasPokerTransfer = new PopKlasPokerTransfer(mContext, rcContent, screenWidth / 5 * 4, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popKlasPokerTransfer.showPopupAtLocation(popIndexX(), popIndexY(view));
                        break;
                    case AppConfig.KENO_GAME:
                        startActivity(new Intent(mContext, KenoHomeActivity.class));
                        break;
                    case AppConfig.NEW_KENO_GAME:
                        startActivity(new Intent(mContext, NewKenoActivity.class));
                        break;
                    case AppConfig.NUMBER_GAME:
                        presenter.enterNumber();
                        break;
                    case AppConfig.LIVE_NUMBER_GAME:
                        presenter.enterLiveNumber();
                        break;
                    case AppConfig.HC_LOTTERY:
                        AppTool.activiyJump(mContext, ChinaLotteryActivity.class);
                        break;
                    case AppConfig.IG_LOTTERY:
                        AppTool.activiyJump(mContext, IgLotteryActivity.class);
                        break;
                    case AppConfig.LOTTERY_GAME:
                        AppTool.activiyJump(mContext, LotteryActivity.class);
                        break;
                    case AppConfig.THAI_LOTTERY:
                        AppTool.activiyJump(mContext, ThaiLotteryActivity.class);
                        break;
                    case AppConfig.LOTTERY_4D:
                        AppTool.activiyJump(mContext, Lottery4DActivity.class);
                        break;
                    case AppConfig.Cockfight:
                        AppTool.activiyJump(mContext, CockfightActivity.class);
                        break;
                    case AppConfig.Scr888:
                        Pop918Kiss pop918Kiss = new Pop918Kiss(mContext, rcContent, screenWidth / 7 * 6, ViewGroup.LayoutParams.WRAP_CONTENT);
                        pop918Kiss.showPopupCenterWindowBlack();
                        break;
                    case AppConfig.Xe88_Slot:
                        PopXe88Transfer popXe88Transfer = new PopXe88Transfer(mContext, rcContent, screenWidth / 6 * 5, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popXe88Transfer.showPopupCenterWindowBlack();
                        break;
                    case AppConfig.ONGDO_Poker:
                        Dig88Utils.setLang(mContext);
                        presenter.enterIbetPoker(getString(R.string.transfer_ongdo_poker), "10", item.getGameType(), rcContent);
                        break;
                    case AppConfig.Poker_Poker:
                        Dig88Utils.setLang(mContext);
                        presenter.enterIbetPoker(getString(R.string.transfer_poker_poker), "5", item.getGameType(), rcContent);
                        break;
                    case AppConfig.HAM_Poker:
                        Dig88Utils.setLang(mContext);
                        presenter.enterIbetPoker(getString(R.string.transfer_ham_poker), "5", item.getGameType(), rcContent);
                        break;
                    case AppConfig.TIENLEN_Poker:
                        Dig88Utils.setLang(mContext);
                        presenter.enterIbetPoker(getString(R.string.transfer_tienlen_poker), "5", item.getGameType(), rcContent);
                        break;
                    case AppConfig.KLA_KLOUK_Poker:
                        Dig88Utils.setLang(mContext);
                        presenter.enterIbetPoker(getString(R.string.transfer_kla_klouk_poker), "5", item.getGameType(), rcContent);
                        break;
                    case AppConfig.SIKUTHAI_Poker:
                        Dig88Utils.setLang(mContext);
                        presenter.enterIbetPoker(getString(R.string.transfer_sikuthai_Poker), "5", item.getGameType(), rcContent);
                        break;
                    case AppConfig.Kate_Poker:
                        Dig88Utils.setLang(mContext);
                        presenter.enterIbetPoker(getString(R.string.transfer_kate_Poker), "5", item.getGameType(), rcContent);
                        break;
                    case AppConfig.Apoung_Poker:
                        Dig88Utils.setLang(mContext);
                        presenter.enterIbetPoker(getString(R.string.transfer_Apoung_Poker), "5", item.getGameType(), rcContent);
                        break;
                    case AppConfig.FFYL_POKER_GAME:
                        PopFfylTransfer popFfylTransfer = new PopFfylTransfer(mContext, rcContent, screenWidth / 6 * 5, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popFfylTransfer.showPopupCenterWindow();
                        break;
                    case AppConfig.KY_poker:
                        PopKYPokerTransfer popKYPokerTransfer = new PopKYPokerTransfer(mContext, rcContent, screenWidth / 6 * 5, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popKYPokerTransfer.showPopupCenterWindow();
                        break;
                    case AppConfig.Mega888:
                        PopMega888Transfer popMega888Transfer = new PopMega888Transfer(mContext, rcContent, screenWidth / 6 * 5, ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void enter() {
                                presenter.getMega888Url();
                            }
                        };
                        popMega888Transfer.showPopupCenterWindow();
                        break;
                    case AppConfig.JdbSlots:
                        PopJDBTransfer popJDBTransfer = new PopJDBTransfer(mContext, rcContent, screenWidth / 6 * 5, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popJDBTransfer.showPopupCenterWindow();
                        break;
                    case AppConfig.We1poker:
                        Intent we1pokerIntent = new Intent(mContext, GameWebActivity.class);
                        we1pokerIntent.putExtra("url", presenter.getWe1PokerUrl());
                        we1pokerIntent.putExtra("title", item.getGameName());
                        startActivity(we1pokerIntent);
                        break;
                    case AppConfig.Lottery_VN:
                        ToastUtils.showShort(getString(R.string.lottery_vn_hint));
                        break;
                    case AppConfig.AFB_CASINO:
                        AppTool.activiyJump(mContext, AFBCasinoActivity.class);
                        break;
                    case AppConfig.PP_CASINO:
                        AppTool.activiyJump(mContext, PPCasinoActivity.class);
                        break;
                    case AppConfig.N2_CASINO:
                        AppTool.activiyJump(mContext, N2CasinoActivity.class);
                        break;
                }
            }
        });
        rcContent.setLayoutManager(new GridLayoutManager(mContext, 3));
        rcContent.setAdapter(adapter);
    }

    private int popIndexY(View view) {
        int itemHeight = view.getHeight();
        int rcHeight = rcContent.getHeight();
        int y = screenHeight - rcHeight + itemHeight;
        return y;
    }

    private int popIndexX() {
        return (screenWidth - screenWidth / 5 * 4) / 2;
    }

    public void onGetEnterNumberStatus() {
        AppTool.activiyJump(mContext, AutoNumberActivity.class);
    }

    public void onGetEnterLiveNumberStatus() {
        AppTool.activiyJump(mContext, LiveDigNumberActivity.class);
    }
}
