package gaming178.com.casinogame.Util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

public class PopMenu extends BasePopupWindow {
    public PopMenu(Context context, View v, int width, int height) {
        super(context, v, width, height);
        baseActivity = (BaseActivity) context;
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_menu;
    }

    HttpClient httpClient = new HttpClient("");

    LinearLayout ll_promotion;
    LinearLayout ll_contact;
    BaseActivity baseActivity;

    public void urlHostInit(int type) {
        new Thread() {
            @Override
            public void run() {
                String url = "http://www.grjl25.com/getDomainInform.jsp?";
                String param = "labelid=" + BuildConfig.Labelid;
                String result = httpClient.getHttpClient(url + param, null);
                Log.d("AppData", result);
                WebSiteUrl.setNormal(result);
                baseActivity.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (type == 1) {
                            PopWebView popWebView = getPromotionPop();
                            popWebView.showPopupCenterWindow();
                        } else {
                            PopWebView popWebView = getContactPop();
                            popWebView.showPopupCenterWindow();
                        }
                    }
                });
            }
        }.start();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ll_promotion = view.findViewById(R.id.gd_ll_promotion);
        ll_contact = view.findViewById(R.id.gd_ll_contact);
        ll_promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                if (TextUtils.isEmpty(WebSiteUrl.HEADER)) {
                    urlHostInit(1);
                } else {
                    PopWebView popWebView = getPromotionPop();
                    popWebView.showPopupCenterWindow();
                }
            }
        });
        ll_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                if (TextUtils.isEmpty(WebSiteUrl.HEADER)) {
                    urlHostInit(2);
                } else {
                    PopWebView popWebView = getContactPop();
                    popWebView.showPopupCenterWindow();
                }
            }
        });
    }

    private PopWebView getPromotionPop() {
        PopWebView popWebView = new PopWebView(context, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
            @Override
            public String getUrl() {
                return WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "promom.jsp";
            }

            @Override
            public String getTitle() {
                return context.getString(R.string.PROMOTION);
            }
        };
        return popWebView;
    }

    private PopWebView getContactPop() {
        PopWebView popWebView = new PopWebView(context, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
            @Override
            public String getUrl() {
                return WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "tentangkamim.jsp";
            }

            @Override
            public String getTitle() {
                return context.getString(R.string.CONTACT);
            }
        };
        return popWebView;
    }
}
