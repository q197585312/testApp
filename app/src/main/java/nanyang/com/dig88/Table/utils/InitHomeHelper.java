package nanyang.com.dig88.Table.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.BasePageAdapter;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.indicator.CirclePageIndicator;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.RecommendGameBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.AutoScrollLayout;
import nanyang.com.dig88.Util.DeviceUtils;
import nanyang.com.dig88.Util.UIUtil;

/**
 * Created by Administrator on 2015/12/4.
 */
public class InitHomeHelper {
    Context mContext;
    GridView mListView;
    AdapterViewContent<RecommendGameBean> content;
    ItemCLickImp itemCLickImp;
    BaseActivity activity;
    boolean enableHeaderItemClick = false;
    Handler handler = new Handler();
    private View header;
    private FrameLayout fl_header_include;
    private LinearLayout detailTopFl;
    private int width;
    private BasePageAdapter<RecommendGameBean> pageAdapter;
    private LinearLayout detail_bottom_ll;
    private ViewPager detailTopVp;
    private boolean isIpad;
    private AutoScrollLayout autoScrollLayout;
    private LinearLayout ll_autoScrollLayout;

    public InitHomeHelper(Context context, GridView listView, View header) {

        this.mContext = context;
        this.mListView = listView;
        this.header = header;
        init();
    }

    public boolean isEnableHeaderItemClick() {
        return enableHeaderItemClick;
    }

    public void setEnableHeaderItemClick(boolean enableHeaderItemClick) {
        this.enableHeaderItemClick = enableHeaderItemClick;
    }

    public View getHeader() {
        return header;
    }

    public FrameLayout getFl_header_include() {
        return fl_header_include;
    }

    public ItemCLickImp getItemCLickImp() {
        return itemCLickImp;
    }

    public void setItemCLickImp(ItemCLickImp itemCLickImp) {
        this.itemCLickImp = itemCLickImp;

    }

    private void init() {
        activity = (BaseActivity) mContext;
        isIpad = UIUtil.isPad(mContext);
        initBanner(mListView);
        width = DeviceUtils.getScreenWidth(activity)/7;
        content = new AdapterViewContent<>(mContext, mListView);
        content.setBaseAdapter(getAdapterImp());
        content.setItemClick(new ItemCLickImp<RecommendGameBean>() {
            @Override
            public void itemCLick(View v, RecommendGameBean recommendGameBean, int position) {
                if (itemCLickImp == null)
                    Toast.makeText(mContext, mContext.getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
                else
                    itemCLickImp.itemCLick(v, recommendGameBean, position);
            }
        });
    }

    @NonNull
    public QuickAdapterImp<RecommendGameBean> getAdapterImp() {
        return new QuickAdapterImp<RecommendGameBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.home_fragment_home_grid_item;
            }

            @Override
            public void convert(ViewHolder helper, RecommendGameBean item, int position) {
                if(UIUtil.isPad(mContext)){
                    FrameLayout fl = helper.retrieveView(R.id.iv_game_layout);
                    fl.getLayoutParams().width=width;
                    fl.getLayoutParams().height=width;
                }

                ImageView imageView = helper.retrieveView(R.id.iv_game_picture);
                TextView name = helper.retrieveView(R.id.tv_game_name);
                name.setText(item.getName());
                if (item.getImg() != null && item.getRes() < 1) {
                    Glide.with(mContext).load(item.getImg()).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                } else {
                    Glide.with(mContext).load(item.getRes()).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                }
            }
        };
    }

    public void setData(List<RecommendGameBean> listData, List<RecommendGameBean> headData) {
        content.setData(listData);
        if (headData != null && headData.size() > 0) {
            pageAdapter.setDatas(headData);
        } else {
            pageAdapter.setDatas(listData);
        }
        if (headData.size() > 1) {
            handleBanner();
        }
    }

    protected void handleBanner() {
        if (header == null) {
            return;
        }
        Runnable digitalRunnable = new Runnable() {
            public int i;

            @Override
            public void run() {
                detailTopVp.setCurrentItem((i++) % pageAdapter.getCount());
                handler.postDelayed(this, 5000);// 50是延时时长
            }
        };
        if (pageAdapter.getCount() > 1)
            handler.post(digitalRunnable);
    }

    public AutoScrollLayout getAutoScrollLayout() {
        return autoScrollLayout;
    }

    public LinearLayout getLlAutoScrollLayout() {
        return ll_autoScrollLayout;
    }

    public LinearLayout getDetailTopFl() {
        return detailTopFl;
    }

    private void initBanner(GridView listView) {
        if (header == null) {
            return;
        }
//        fl_header_include = (FrameLayout) header.findViewById(R.id.fl_header_include);
        detailTopVp = (ViewPager) header.findViewById(R.id.detail_top_vp);
//        ll_autoScrollLayout = (LinearLayout) header.findViewById(R.id.ll_autoScrollLayout);
        autoScrollLayout = (AutoScrollLayout) header.findViewById(R.id.autoScrollLayout);
        CirclePageIndicator detailTopCpi = (CirclePageIndicator) header.findViewById(R.id.detail_top_cpi);
        detailTopFl = (LinearLayout) header.findViewById(R.id.detail_top_fl);
        detail_bottom_ll = (LinearLayout) header.findViewById(R.id.detail_bottom_ll);
        detailTopFl.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, activity.screenHeight / 4));

        pageAdapter = new BasePageAdapter<RecommendGameBean>(mContext) {
            @Override
            protected void convert(ViewHolder helper, final RecommendGameBean recommendGameBean, final int position) {
                if (recommendGameBean.getRes() < 1)
                    helper.setImageUrl(R.id.image_base_iv, recommendGameBean.getImg());
                else {
                    helper.setImageResource(R.id.image_base_iv, recommendGameBean.getRes());
                    helper.setClickLisenter(R.id.image_base_iv, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (itemCLickImp != null && isEnableHeaderItemClick())
                                itemCLickImp.itemCLick(v, recommendGameBean, position);
                        }
                    });
                }
            }

            @Override
            protected int getPageLayoutRes() {
                return R.layout.item_base_image;
            }
        };
        detailTopVp.setAdapter(pageAdapter);
        detailTopCpi.setViewPager(detailTopVp);


    }
}
