package nanyang.com.dig88.SlotsGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.view.indicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.base.BasePageAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Home.Bean.HomeContentListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.SlotsGame.Pop.PopJokerTranfer;
import nanyang.com.dig88.SlotsGame.Presenter.SlotsGamePresenter;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.PopAgFishTransfer;
import nanyang.com.dig88.Util.UIUtil;

/**
 * Created by 47184 on 2019/7/4.
 */

public class SlotsGameActivity extends BaseActivity<SlotsGamePresenter> {
    @BindView(R.id.detail_top_vp)
    ViewPager detailTopVp;
    @BindView(R.id.detail_top_cpi)
    CirclePageIndicator detailTopCpi;
    PopJokerTranfer popJokerTranfer;
    W88GameConfigBean.DataBean clickItem;
    private BasePageAdapter<List<W88GameConfigBean.DataBean>> pageAdapter;
    private int pageSize = 8;
    private int pageCount;
    private int gameCount;
    private boolean isJust;
    private String gameType;
    private int rcContentHeight;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_slots_game_hall;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        toolbar.setBackgroundResource(0);
        setleftViewEnable(true);
        createPresenter(new SlotsGamePresenter(this));
        Intent intent = getIntent();
        HomeContentListBean homeContentListBean = (HomeContentListBean) intent.getSerializableExtra("HomeContentListBean");
        gameType = homeContentListBean.getGameType();
        setTitle(homeContentListBean.getGameName());
        detailTopVp.post(new Runnable() {
            @Override
            public void run() {
                rcContentHeight = detailTopVp.getHeight();
                presenter.getSlotsData(presenter.getGameParam(gameType), gameType);
            }
        });
    }

    public void onGetSlotsData(List<W88GameConfigBean.DataBean> dataList) {
        gameCount = dataList.size();
        if (gameCount % pageSize == 0) {
            isJust = true;
            pageCount = gameCount / pageSize;
        } else {
            isJust = false;
            pageCount = gameCount / pageSize + 1;
        }
        List<List<W88GameConfigBean.DataBean>> pages = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            List<W88GameConfigBean.DataBean> dataBeen = new ArrayList<>();
            int starIndex = 0;
            int endIndex = 0;
            starIndex = i * pageSize;
            if (!isJust && i == pageCount - 1) {
                endIndex = starIndex + gameCount % pageSize;
            } else {
                endIndex = starIndex + pageSize;
            }
            for (int j = starIndex; j < endIndex; j++) {
                dataBeen.add(dataList.get(j));
            }
            pages.add(dataBeen);
        }
        initViewPager(pages);
    }

    public void initViewPager(List<List<W88GameConfigBean.DataBean>> pages) {
        pageAdapter = new BasePageAdapter<List<W88GameConfigBean.DataBean>>(mContext) {
            @Override
            protected void convert(ViewHolder helper, List<W88GameConfigBean.DataBean> dataList, int position) {
                RecyclerView rcContent = helper.retrieveView(R.id.rc_content);
                BaseRecyclerAdapter<W88GameConfigBean.DataBean> adapter = new BaseRecyclerAdapter<W88GameConfigBean.DataBean>(mContext, dataList, R.layout.item_base_image_text) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, W88GameConfigBean.DataBean item) {
                        String imgUrl = presenter.getGameImgUrlHead(gameType);
                        if (gameType.equals(AppConfig.FISHING_GAME)) {
                            imgUrl = presenter.getFishImgUrlHead(item.getGname_en());
                        }
                        ImageView img = holder.getView(R.id.iv_pic);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rcContentHeight / 4 - UIUtil.dip2px(mContext, 40));
                        img.setLayoutParams(params);
                        if (item.getGname_en().equals("Ag Fishing")) {
                            img.setImageResource(R.mipmap.ag_fish);
                        } else {
                            holder.setImageByUrl(R.id.iv_pic, imgUrl + item.getImage());
                        }
                        holder.setText(R.id.tv_text, presenter.getGameName(item));
                    }
                };
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<W88GameConfigBean.DataBean>() {
                    @Override
                    public void onItemClick(View view, W88GameConfigBean.DataBean item, int position) {
                        clickItem = item;
                        if (gameType.equals(AppConfig.HABA_GAME)) {
                            presenter.goHabaGame(item.getGid());
                        } else if (gameType.equals(AppConfig.Joker123_GAME)) {
//                            if (popJokerTranfer == null) {
//                                popJokerTranfer = new PopJokerTranfer(mContext, detailTopVp, screenWidth / 5 * 4, ViewGroup.LayoutParams.WRAP_CONTENT) {
//                                    @Override
//                                    public void enterGame() {
//                                        presenter.goJoker(clickItem.getGid());
//                                    }
//                                };
//                            }
//                            if (getApp().getIsShowJokerPop()) {
//                                popJokerTranfer.initUi();
//                                popJokerTranfer.showPopupCenterWindowBlack();
//                            } else {
//                                presenter.goJoker(clickItem.getGid());
//                            }
                            presenter.goJoker(clickItem.getGid());
                        } else if (gameType.equals(AppConfig.FISHING_GAME)) {
                            String gnameEn = clickItem.getGname_en();
                            if (gnameEn.equals("Ag Fishing")) {
                                Dig88Utils.setLang(mContext);
                                PopAgFishTransfer popAgFishTransfer = new PopAgFishTransfer(mContext, detailTopVp, screenWidth / 5 * 4, ViewGroup.LayoutParams.WRAP_CONTENT);
                                popAgFishTransfer.showPopupCenterWindow();
                            } else if (gnameEn.equals("Fishing king") || gnameEn.equals("Yu Le Wu Qiong")) {
                                presenter.goFish(item.getGid(), -1);
                            } else {
                                if (popJokerTranfer == null) {
                                    popJokerTranfer = new PopJokerTranfer(mContext, detailTopVp, screenWidth / 5 * 4, ViewGroup.LayoutParams.WRAP_CONTENT) {
                                        @Override
                                        public void enterGame() {
                                            presenter.goJoker(clickItem.getGid());
                                        }
                                    };
                                }
                                popJokerTranfer.initUi();
                                popJokerTranfer.showPopupCenterWindowBlack();
                            }
                        } else {
                            Intent intent = new Intent(mContext, SlotsGameWebActivity.class);
                            intent.putExtra("url", presenter.getGameLoadUrl(gameType, item));
                            startActivity(intent);
                        }
                    }
                });
                rcContent.setLayoutManager(new GridLayoutManager(mContext, 2));
                rcContent.setAdapter(adapter);
            }

            @Override
            protected int getPageLayoutRes() {
                return gaming178.com.mylibrary.R.layout.include_recyclerview;
            }
        };
        detailTopVp.setAdapter(pageAdapter);
        detailTopCpi.setViewPager(detailTopVp);
        pageAdapter.setDatas(pages);
    }
}
