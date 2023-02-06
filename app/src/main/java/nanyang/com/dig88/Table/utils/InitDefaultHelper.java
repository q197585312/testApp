package nanyang.com.dig88.Table.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.RecommendGameBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.DeviceUtils;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.base.AdapterViewContent;
import xs.com.mylibrary.base.BasePageAdapter;
import xs.com.mylibrary.base.ItemCLickImp;
import xs.com.mylibrary.base.QuickAdapterImp;
import xs.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2015/12/4.
 */
public class InitDefaultHelper {
    Context mContext;
    ListView mListView;
    AdapterViewContent<RecommendGameBean> content;
    ItemCLickImp itemCLickImp;
    BaseActivity activity;
    boolean enableHeaderItemClick = false;
    private BasePageAdapter<RecommendGameBean> pageAdapter;
    private boolean isIpad;


    public InitDefaultHelper(Context context, ListView listView) {

        this.mContext = context;
        this.mListView = listView;
        init();
    }

    public boolean isEnableHeaderItemClick() {
        return enableHeaderItemClick;
    }

    public void setEnableHeaderItemClick(boolean enableHeaderItemClick) {
        this.enableHeaderItemClick = enableHeaderItemClick;
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
                return R.layout.item_recomend_game;
            }

            @Override
            public void convert(ViewHolder helper, RecommendGameBean item, int position) {
                LinearLayout ll_other = helper.retrieveView(R.id.ll_other);
                LinearLayout ll_afb33 = helper.retrieveView(R.id.ll_afb33);
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView recommend_right_img_item_tv = helper.retrieveView(R.id.recommend_right_img_item_tv);
                View line = helper.retrieveView(R.id.line);
                recommend_right_img_item_tv.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                rl_content.setBackgroundResource(0);
                ll_afb33.setVisibility(View.GONE);
                ll_other.setVisibility(View.VISIBLE);
                helper.setText(R.id.recommend_game_hot_tv, item.getDescription());
                helper.setText(R.id.recommend_game_title_tv, item.getName());
                ImageView imageView = helper.retrieveView(R.id.recommend_game_pic_iv);
                if (item.getImg() != null && item.getRes() < 1) {
                    Glide.with(mContext).load(item.getImg()).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                } else {
                    Glide.with(mContext).load(item.getRes()).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                }
                if (item.getRightDrawableRes() < 1) {
                    helper.setBackgroundRes(R.id.recommend_right_img_item_tv, R.mipmap.oval_arrow_right_red);
                } else {
                    helper.setBackgroundRes(R.id.recommend_right_img_item_tv, item.getRightDrawableRes());
                }
            }
        };
    }

    public void setData(List<RecommendGameBean> listData, List<RecommendGameBean> headData) {
        content.setData(listData);
    }


    private void initBanner(ListView listView) {


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


    }


}
