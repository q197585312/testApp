package com.nanyang.app.main.home.sport.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballFragment;
import com.nanyang.app.main.home.sport.e_sport.ESportFragment;
import com.nanyang.app.main.home.sport.financial.FinancialFragment;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.nanyang.app.main.home.sport.game4d.Game4dFragment;
import com.nanyang.app.main.home.sport.muayThai.MuayThaiFragment;
import com.nanyang.app.main.home.sport.myanmarOdds.MyanmarFragment;
import com.nanyang.app.main.home.sport.tennis.TennisFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;


public class SportActivity extends BaseToolbarActivity {
    BaseSportFragment soccerFragment = new SoccerFragment();
    BaseSportFragment basketballFragment = new BasketballFragment();
    BaseSportFragment tennisFragment = new TennisFragment();
    BaseSportFragment financialFragment = new FinancialFragment();
    BaseSportFragment game4dFragment = new Game4dFragment();
    BaseSportFragment eSportFragment = new ESportFragment();
    BaseSportFragment muayThaiFragment = new MuayThaiFragment();
    BaseSportFragment myanmarFragment = new MyanmarFragment();


    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.tv_refresh)
    TextView tvRefresh;
    @Bind(R.id.tv_collection)
    TextView tvCollection;
    @Bind(R.id.tv_menu)
    TextView tvMenu;
    @Bind(R.id.tv_mix)
    TextView tvMix;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_sport_menu_bottom)
    LinearLayout llSportMenuBottom;

    private String currentTag;
    private HashMap<String, BaseSportFragment> mapFragment;
    private BaseSportFragment currentFragment;
    private Disposable subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
        assert tvToolbarRight != null;
        tvToolbarRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.sport_list_layer, 0);
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment.toolbarRightClick(v);
            }
        });
        tvToolbarTitle.setBackgroundResource(0);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type = "";

    @Override
    public void initData() {
        super.initData();
        MenuItemInfo<String> item = (MenuItemInfo<String>) getIntent().getSerializableExtra(AppConstant.KEY_DATA);
        if (item != null) {
            type = item.getType();
            assert tvToolbarTitle != null;
            tvToolbarTitle.setText(item.getText());
        }
        mapFragment = new HashMap<>();
        switch (item.getParent()) {

            case "Financial":
                mapFragment.put(getString(R.string.Financial), financialFragment);
                currentFragment = financialFragment;
                currentTag = getString(R.string.Financial);
                break;
            case "Specials_4D":
                mapFragment.put(getString(R.string.Specials_4D), game4dFragment);
                currentFragment = game4dFragment;
                currentTag = getString(R.string.Specials_4D);
                break;
            case "Muay_Thai":
                mapFragment.put(getString(R.string.Muay_Thai), muayThaiFragment);
                currentFragment = muayThaiFragment;
                currentTag = getString(R.string.Muay_Thai);
                break;
            case "E_Sport":
                mapFragment.put(getString(R.string.E_Sport), eSportFragment);
                currentFragment = eSportFragment;
                currentTag = getString(R.string.E_Sport);
                break;
            case "Myanmar_Odds":
                mapFragment.put(getString(R.string.Myanmar_Odds), myanmarFragment);
                currentFragment = myanmarFragment;
                currentTag = getString(R.string.Myanmar_Odds);
                break;
            default:
                mapFragment.put(getString(R.string.Soccer), soccerFragment);
                mapFragment.put(getString(R.string.Basketball), basketballFragment);
                mapFragment.put(getString(R.string.Tennis), tennisFragment);
                currentFragment = soccerFragment;
                currentTag = getString(R.string.Soccer);
                break;
        }


        tvTitle.setText(currentTag);
        showFragmentToActivity(currentFragment, R.id.fl_content, currentTag);

        getApp().setBetParList(null);
        oddsType();
    }

    private void oddsType() {
        subscription = getService(ApiService.class).getData(AppConstant.URL_ODDS_TYPE + "HK").subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {

                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort(throwable.getMessage());

                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                        subscription.request(Long.MAX_VALUE);
                    }
                });

    }

    private void selectFragmentTag(String tag) {
        if (!currentTag.equals(tag)) {
            tvTitle.setText(tag);
            hideFragmentToActivity(mapFragment.get(currentTag));
            MenuItemInfo stateType = mapFragment.get(currentTag).presenter.getStateHelper().getStateType();
            showFragmentToActivity(mapFragment.get(tag), R.id.fl_content, tag);
            currentTag = tag;
            currentFragment = mapFragment.get(currentTag);
            currentFragment.switchParentType(stateType);
            setType(stateType.getType());
        }
    }

    @OnClick({R.id.tv_refresh, R.id.tv_collection, R.id.tv_menu, R.id.tv_mix, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_refresh:
                currentFragment.refresh();
                break;
            case R.id.tv_collection:
                currentFragment.collection(tvCollection);
                break;
            case R.id.tv_menu:
                currentFragment.menu(tvMenu);
                break;
            case R.id.tv_mix:
                if (currentFragment.mix(tvMix))
                    tvCollection.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_star_black, 0, 0);
                else {
                    getApp().setBetParList(null);
                }
                break;
            case R.id.iv_add:
                createPopupWindow(new BasePopupWindow(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, 350) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_game;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                        rv.setLayoutManager(new GridLayoutManager(mContext, 3));
                        List<MenuItemInfo> list = new ArrayList<>();
                        Iterator<String> iterator = mapFragment.keySet().iterator();
                        while (iterator.hasNext()) {
                            list.add(new MenuItemInfo(0, iterator.next()));
                        }

                        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, list, R.layout.text_base) {
                            @Override
                            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                                TextView tv = holder.getView(R.id.item_text_tv);
                                tv.setBackgroundResource(R.color.google_green);
                                tv.setText(item.getText());
                                tv.setTextColor(getResources().getColor(R.color.white));
                            }

                        };

                        rv.setAdapter(baseRecyclerAdapter);
                        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                            @Override
                            public void onItemClick(View view, MenuItemInfo item, int position) {
                                selectFragmentTag(item.getText());
                                closePopupWindow();
                            }
                        });
                    }
                });
                popWindow.setTrans(1f);
                popWindow.showPopupDownWindow();
                break;

        }
    }

    @Override
    protected void updateBalanceTv(String allData) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription!=null)
        subscription.isDisposed();
    }
}
