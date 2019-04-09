package com.nanyang.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.Utils.TimeUtils;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.BetCenter.Bean.More;
import com.nanyang.app.main.BetCenter.BetCenterFragment;
import com.nanyang.app.main.changeLanguage.ChangeLanguageFragment;
import com.nanyang.app.main.contact.ContactFragment;
import com.nanyang.app.main.home.HomeFragment;
import com.nanyang.app.main.home.huayThai.HuayThaiActivity;
import com.nanyang.app.main.home.keno.KenoActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.howtouse.HowToUseFragment;
import com.nanyang.app.main.message.MessageFragment;
import com.nanyang.app.main.person.PersonCenterFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.utils.NetWorkUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

public abstract class BaseToolbarActivity<T extends IBasePresenter> extends BaseActivity<T> {
    @Nullable
    protected
    TextView tvToolbarTitle;
    @Nullable
    protected
    TextView tvToolbarRight;
    @Nullable
    protected
    TextView tvTime;
    @Nullable
    protected
    Toolbar toolbar;
    @Nullable
    protected
    LinearLayout llRight;
    @Nullable
    protected
    DrawerLayout drawerLayout;
    @Nullable
    protected
    RecyclerView drawerLayoutRightRc;

    private CompositeDisposable mCompositeSubscription;
    int errorCount = 0;
    protected TextView tvToolbarRight1;
    protected TextView tvToolbarLeft;

    @Override
    public void initData() {
        super.initData();
//        initDrawerLayout();
        mCompositeSubscription = new CompositeDisposable();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarRight = (TextView) findViewById(R.id.tv_toolbar_right);
        tvToolbarRight1 = (TextView) findViewById(R.id.tv_toolbar_right1);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        tvToolbarLeft = (TextView) findViewById(R.id.tv_toolbar_left);
        llRight = (LinearLayout) findViewById(R.id.ll_right);
        toolbar.setNavigationIcon(R.mipmap.arrow_white_back);
        toolbar.setBackgroundResource(R.color.green_black_word);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackCLick(v);
            }
        });
        assert tvToolbarTitle != null;
//        tvToolbarTitle.setBackgroundResource(R.mipmap.logo);
//        tvToolbarTitle.getLayoutParams().width = DeviceUtils.dip2px(mContext, 100);
        tvToolbarLeft.setBackgroundResource(R.mipmap.sport_home_white_24dp);
        tvToolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMenus(v);
            }
        });
        if (isNeedUpdateTime()) {
            llRight.setVisibility(View.VISIBLE);
            updateHandler.post(timeUpdateRunnable);
        }

//        switchFragment(getFirstShowFragment());
    }

    public BaseSwitchFragment homeFragment = new HomeFragment();
    public BaseSwitchFragment statementFragment = new BetCenterFragment();
    public BaseSwitchFragment contactFragment = new ContactFragment();
    public BaseSwitchFragment changeLanguageFragment = new ChangeLanguageFragment();
    public BaseSwitchFragment personFragment = new PersonCenterFragment();
    public BaseSwitchFragment howToUseFragment = new HowToUseFragment();
    public BaseSwitchFragment messageFragment = new MessageFragment();
    public BaseSwitchFragment indexFragment;
    public BaseSwitchFragment lastIndexFragment;

    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(getDrawerLayoutId());
        drawerLayoutRightRc = (RecyclerView) findViewById(R.id.main_more);
        More m1 = new More(R.mipmap.myacount, getString(R.string.my_account), 0, personFragment);
        More m2 = new More(R.mipmap.messages, getString(R.string.messages), R.mipmap.message, messageFragment);
        More m3 = new More(R.mipmap.statement, getString(R.string.statement), 0, statementFragment, BetCenterFragment.statementNew);
        More m4 = new More(R.mipmap.result, getString(R.string.result), 0, statementFragment, BetCenterFragment.grade);
        More m5 = new More(R.mipmap.phone, getString(R.string.contact), 0, contactFragment);
        More m6 = new More(R.mipmap.setting, getString(R.string.setting), 0, changeLanguageFragment);
        More m7 = new More(R.mipmap.setting, getString(R.string.how_to_use), 0, howToUseFragment);
        More m8 = new More(R.mipmap.logout, getString(R.string.logout), 0);
        List<More> dataList = new ArrayList<>();
        dataList.add(m1);
        dataList.add(m2);
        dataList.add(m3);
        dataList.add(m4);
        dataList.add(m5);
        dataList.add(m6);
        dataList.add(m7);
        dataList.add(m8);
        BaseRecyclerAdapter<More> adapter = new BaseRecyclerAdapter<More>(mContext, dataList, R.layout.item_main_more) {

            @Override
            public void convert(MyRecyclerViewHolder holder, int position, More item) {
                ImageView ivLeft = holder.getImageView(R.id.more_left_img);
                ImageView ivRight = holder.getImageView(R.id.more_img_right);
                ivLeft.setImageResource(item.getImage_left());
                if (item.getImage_right() != 0) {
                    ivRight.setImageResource(item.getImage_right());
                }
                TextView tv = holder.getTextView(R.id.more_text);
                tv.setText(item.getText());
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<More>() {
            @Override
            public void onItemClick(View view, More item, int position) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
                if (getString(R.string.logout).equals(item.getText())) {
                    BaseYseNoChoosePopupWindow pop = new BaseYseNoChoosePopupWindow(mContext, new View(mContext)) {
                        @Override
                        protected void clickSure(View v) {
                            Intent intent = new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    };
                    pop.getChooseTitleTv().setText(getString(R.string.confirm_or_not));
                    pop.getChooseMessage().setText(getString(R.string.login_out));
                    pop.getChooseSureTv().setText(getString(R.string.sure));
                    pop.getChooseCancelTv().setText(getString(R.string.cancel));
                    onPopupWindowCreated(pop, Gravity.CENTER);
                } else {
                    BaseSwitchFragment fragment = item.getFragment();
                    if (fragment != null) {
                        if (fragment instanceof BetCenterFragment) {
                            String switchType = item.getSwitchType();
                            if (!TextUtils.isEmpty(switchType)) {
                                fragment.setSwitchType(item.getSwitchType());
                                fragment.showContent();
                            }
                        }
                        switchFragment(fragment);
                    }
                }
            }
        });
        drawerLayoutRightRc.setLayoutManager(new LinearLayoutManager(mContext));
        drawerLayoutRightRc.setAdapter(adapter);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }


    public void initAgent() {
        tvToolbarLeft.setVisibility(View.GONE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startUpdateState();
        if (AppConstant.getInstance().IS_AGENT) {
            initAgent();
        }
    }

    protected void onBackCLick(View v) {
        finish();
    }

    void stopUpdateState() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
    }

    public boolean isNeedUpdateTime() {
        return false;
    }

    Runnable timeUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            String currentTime = "HK: " + TimeUtils.getTime("dd MM月 yyyy hh:mm:ss aa z", Locale.ENGLISH);
            tvTime.setText(currentTime);
            updateHandler.postDelayed(this, 1000);
        }
    };

    Runnable dataUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            Disposable subscribe = getService(ApiService.class).getData(AppConstant.getInstance().URL_UPDATE_STATE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .takeWhile(new Predicate<String>() {
                        @Override
                        public boolean test(String s) throws Exception {
                            return NetWorkUtil.isNetConnected(mContext);
                        }
                    })
                    .subscribe(new Consumer<String>() {//onNext
                                   @Override
                                   public void accept(String allData) throws Exception {
                                       if (!allData.trim().equals("100")) {
                                           reLoginPrompt("", new SportContract.CallBack() {
                                               @Override
                                               public void clickCancel(View v) {
                                                   Intent intent = new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                   startActivity(intent);
                                               }
                                           });
                                           errorCount = 0;
                                       }

                                   }
                               }, new Consumer<Throwable>() {
                                   @Override
                                   public void accept(Throwable throwable) {
                                       ToastUtils.showShort(throwable.getMessage());
                                       checkError();
                                   }
                               }, new Action() {//完成
                                   @Override
                                   public void run() throws Exception {
                                   }
                               }, new Consumer<Subscription>() {//开始绑定
                                   @Override
                                   public void accept(Subscription subscription1) throws Exception {
                                       if (!NetWorkUtil.isNetConnected(mContext)) {
                                           subscription1.cancel();
                                           checkError();
                                       }
                                       subscription1.request(Long.MAX_VALUE);
                                   }
                               }

                    );
            mCompositeSubscription.add(subscribe);
            updateHandler.postDelayed(this, 20000);// 50是延时时长
        }
    };

    private void checkError() {
        errorCount++;
        if (errorCount > 4) {
            reLoginPrompt(getString(R.string.failed_to_connect), new SportContract.CallBack() {
                @Override
                public void clickCancel(View v) {
                    errorCount = 0;
                }
            });
        }
    }


    Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 7:
                    tvTime.setText((String) msg.obj);
                    break;
            }
        }
    };

    public void startUpdateState() {
        stopUpdateState();
        updateHandler.postDelayed(dataUpdateRunnable, 2000);// 打开定时器，执行操作
        updateBalance();

    }

    public void updateBalance() {

        Disposable updateBalanceSubscribe = getService(ApiService.class).getData(AppConstant.getInstance().URL_UPDATE_BALANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                               @Override
                               public void accept(String allData) throws Exception {
                                   updateBalanceTv(allData);
                                   getApp().getUser().setBalances(allData);
                               }
                           }, new Consumer<Throwable>() {//错误
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                               }
                           }
                );
        mCompositeSubscription.add(updateBalanceSubscribe);
    }

    protected void updateBalanceTv(String allData) {
        tvToolbarRight.setText(allData);
    }


    @Override
    protected void onStop() {
        super.onStop();
        stopUpdateState();
    }

    @Nullable
    public TextView getTvToolbarTitle() {
        return tvToolbarTitle;
    }

    @Nullable
    public TextView getTvToolbarRight() {
        return tvToolbarRight;
    }

    @Nullable
    public Toolbar getToolbar() {
        return toolbar;
    }

    public AfbApplication getApp() {
        return (AfbApplication) getApplication();
    }

    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        createPopupWindow(pop);
        if (center == -2) {
            popWindow.showPopupDownWindow();
        } else
            popWindow.showPopupGravityWindow(center, 0, 0);
    }

    public void onPopupWindowCreated(BasePopupWindow pop, int center, int x, int y) {
        createPopupWindow(pop);
        popWindow.showPopupGravityWindow(center, x, y);
    }

    public void onBetSuccess(String betResult) {
        if (popWindow != null)
            popWindow.closePopupWindow();
        getApp().setBetParList(null);
        updateBalance();

    }


    public void reLoginPrompt(String msg, final SportContract.CallBack callBack) {
        BaseYseNoChoosePopupWindow pop = new BaseYseNoChoosePopupWindow(mContext, new View(mContext)) {
            @Override
            protected void clickSure(View v) {
                if (AppConstant.IS_AGENT) {
                    finish();
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            protected void clickCancel(View v) {
                super.clickCancel(v);
                if (callBack != null) {
                    callBack.clickCancel(v);
                }
            }
        };
        pop.getChooseTitleTv().setText(getString(R.string.confirm_or_not));
        if (msg.isEmpty())
            msg = getString(R.string.another_login);
        pop.getChooseMessage().setText(msg);
        pop.getChooseSureTv().setText(getString(R.string.sure));
        pop.getChooseCancelTv().setText(getString(R.string.cancel));
        onPopupWindowCreated(pop, Gravity.CENTER);
    }

    protected void gameMenus(View v) {
        popWindow = new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_all_game;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                BaseRecyclerAdapter adapter = AfbUtils.getGamesAdapter(mContext, rv);
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        switchSkipAct(item.getType());
                        closePopupWindow();

                    }
                });
            }
        };
        popWindow.showPopupCenterWindow();
    }

    public void defaultSkip(String type) {
        MenuItemInfo<String> menuItemInfo = new MenuItemInfo<String>(0, getString(R.string.Today));
        menuItemInfo.setType("Today");
        menuItemInfo.setParent(type);
        Bundle b = new Bundle();
        b.putSerializable(AppConstant.KEY_DATA, menuItemInfo);
        skipAct(SportActivity.class, b);
    }

    public void switchSkipAct(String gameType) {
        SportIdBean sportIdBean = AfbUtils.identificationSportById(gameType);
        switch (gameType) {
            case "SportBook":
            case "Financial":
            case "Specials_4D":
            case "Muay_Thai":
            case "E_Sport":
            case "Myanmar_Odds":
            case "Europe":
                defaultSkip(gameType);
           /*             createPopupWindow(getPopupWindow(item.getType()));
                        popWindow.showPopupCenterWindow();*/
                break;
            case "Huay_Thai":
                skipAct(HuayThaiActivity.class);
                break;
            case "Live_Casino":
                Bundle b = new Bundle();
                b.putString("activity", "Live");
                ToastUtils.showShort(R.string.coming_soon);
//                skipAct(PokerCasinoActivity.class, b);
                break;
            case "Poker":
                ToastUtils.showShort(R.string.coming_soon);
                break;
            case "Keno":
                skipAct(KenoActivity.class);
                break;
            default:
                ToastUtils.showShort(R.string.coming_soon);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == 8) {
            String gameType = data.getStringExtra("gameType");
            againLogin(gameType);
        }
    }

    public void againLogin(String gameType) {

    }

    public int getHomeColor() {
        switch (getString(R.string.app_name)) {
            case "Afb88":
                return 0xff0d5924;
            case "I1bet88":
                return 0xff0E3D59;
            case "AP889":
                return 0xff300F2D;
        }
        return 0xff0d5924;
    }

    public void setToolbarVisibility(boolean b) {
        if (b) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    public abstract int getDrawerLayoutId();

    public abstract BaseSwitchFragment getFirstShowFragment();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isNeedUpdateTime()) {
            updateHandler.removeCallbacks(timeUpdateRunnable);
        }
    }

    public void switchFragment(BaseSwitchFragment fragment) {
        if (fragment == indexFragment && lastIndexFragment != null) {
            indexFragment.showContent();
            return;
        }
        indexFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_main_content, fragment);
        } else {
            transaction.show(fragment);
        }
        if (lastIndexFragment != null) {
            transaction.hide(lastIndexFragment);
        }
        lastIndexFragment = indexFragment;
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
            if (indexFragment == homeFragment) {
                finish();
            } else {
                indexFragment.back();
            }
        }
        return true;
    }
}
