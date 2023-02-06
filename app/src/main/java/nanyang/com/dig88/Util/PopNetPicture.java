package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import nanyang.com.dig88.Entity.BannerBean;
import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/4/17.
 */

public class PopNetPicture extends BasePopupWindow implements View.OnClickListener {
    private ImageView img;
    private TextView tv_exit;

    public PopNetPicture(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_net_picture;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        img = (ImageView) view.findViewById(R.id.img_content);
        tv_exit = (TextView) view.findViewById(R.id.tv_exit);
        img.setOnClickListener(this);
        tv_exit.setOnClickListener(this);
    }

    public void loadLocalImg(int pic) {
        img.setBackgroundResource(pic);
        showPopupCenterWindowBlack();
    }

    public void getLoadImgUrl() {
        String imgUrl = "http://app.info.dig88api.com/index.php?page=get_banners_submitter";
        imgUrl += "&web_id=" + WebSiteUrl.WebId;
        OkhttpUtils.getRequest(imgUrl, new OkhttpUtils.Result() {
            @Override
            public void onSuccess(String result) {
                BannerBean bannerBean = new Gson().fromJson(result, BannerBean.class);
                boolean status = false;
                if (bannerBean.getMsg().equals("1")) {
                    String loadUrl = "";
                    for (int i = 0; i < bannerBean.getData().size(); i++) {
                        BannerBean.DataBean dataBean = bannerBean.getData().get(i);
                        if (WebSiteUrl.WebId.equals("54")) {
                            if (dataBean.getType().equals("1") && dataBean.getRemark().equals("popup")) {
                                loadUrl = dataBean.getUrl();
                                status = true;
                                break;
                            }
                        } else if (WebSiteUrl.WebId.equals("4")) {
                            if (dataBean.getType().equals("31") && dataBean.getLang().equals("ca")) {
                                loadUrl = dataBean.getUrl();
                                if (dataBean.getStatus().equals("0")) {
                                    status = true;
                                }
                                break;
                            }
                        } else if (WebSiteUrl.WebId.equals("70") || WebSiteUrl.WebId.equals("46") ||
                                WebSiteUrl.WebId.equals("34") || WebSiteUrl.WebId.equals("233") ||
                                WebSiteUrl.WebId.equals("98")) {
                            if (dataBean.getType().equals("31")) {
                                loadUrl = dataBean.getUrl();
                                if (dataBean.getStatus().equals("0")) {
                                    status = true;
                                }
                                break;
                            }
                        }
                    }
                    if (status) {
                        Glide.with(context).load(loadUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
                        showPopupCenterWindowBlack();
                    }
                }
            }

            @Override
            public void onFailed(String result) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        closePopupWindow();
    }
}
