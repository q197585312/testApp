package nanyang.com.dig88.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2018/1/10.
 */

public class IbetPokerActivity extends BaseWebGameActivity {

    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.img_toolbar_left)
    ImageView imgToolbarLeft;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    private LoginInfoBean s;
    private String pokerId;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Intent intent = getIntent();
        ContentInfoBean contentInfoBean = (ContentInfoBean) intent.getSerializableExtra(AppConfig.Ibet567_Poker);
        pokerId = contentInfoBean.getContentId();
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        rlTitle.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imgToolbarLeft.getLayoutParams();
        params.width = UIUtil.dip2px(mContext, 15);
        params.height = UIUtil.dip2px(mContext, 15);
        imgToolbarLeft.setLayoutParams(params);
        imgToolbarLeft.setImageResource(R.mipmap.back_new);
        imgToolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvToolbarTitle.setText(contentInfoBean.getContent());
    }

    @Override
    public void initGameData() {
        super.initGameData();
        showBlockDialog();
        webView.loadUrl(getGameLoadUrl());
    }

    private String getGameLoadUrl() {
        String url = WebSiteUrl.Ibet567PokerLoginrUrl;
        url += "web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() + "&language=" + getLoginLanguage() + "&platform=Mobile" +
                "&token=" + getUserInfoBean().getSession_id() + "&gameid=" + pokerId;
        return url;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    public String getType2() {
        return "264";
    }

    private String getLoginLanguage() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        }
        switch (lg) {
            case "en":
                return "en";
            case "zh":
                return "cn";
            case "kr":
                return "kr";
            case "in":
                return "id";
            case "th":
                return "th";
            case "vn":
                return "vn";
            case "ma":
                return "ma";
            case "kh":
                return "ca";
            default:
                return "en";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Dig88Utils.setLang(mContext);
    }
}
