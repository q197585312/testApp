package nanyang.com.dig88.Util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.Entity.BannerBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2019/10/16.
 */

public class Pop88gasiaContact extends BasePopupWindow {
    @BindView(R.id.img_content)
    ImageView imgContent;

    public Pop88gasiaContact(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_88gasia_contact;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        imgContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        getImgData();
    }

    public void getImgData() {
        String param = "&web_id=" + WebSiteUrl.WebId + "&lang=" + getLanguage();
        HttpUtils.httpGet(WebSiteUrl.BannerUrl + param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                BannerBean bannerBean = new Gson().fromJson(s, BannerBean.class);
                List<BannerBean.DataBean> dataList = bannerBean.getData();
                for (int i = 0; i < dataList.size(); i++) {
                    BannerBean.DataBean dataBean = dataList.get(i);
                    if (dataBean.getType().equals("31") && dataBean.getStatus().equals("0")) {
                        Glide.with(context).load(dataBean.getUrl()).skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE).into(imgContent);
                        break;
                    }
                }
            }

            @Override
            public void onRequestFailed(String s) {

            }
        });
    }

    private String getLanguage() {
        String lg = AppTool.getAppLanguage(context);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        } else {
            switch (lg) {
                case "en":
                    return "en";
                case "th":
                    return "th";
                case "vn":
                    return "vn";
                case "zh":
                    return "cn";
                case "in":
                    return "id";
                case "kr":
                    return "kr";
                case "ms":
                    return "ma";
                case "kh":
                    return "ca";
                default:
                    return "en";
            }
        }
    }
}
