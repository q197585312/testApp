package nanyang.com.dig88.Util;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.SharePreferenceUtil;
import xs.com.mylibrary.base.RequestBean;
import xs.com.mylibrary.base.quick.QuickRequestBean;

/**
 * Created by Administrator on 2015/12/4.
 */
public class DigitalGamesLoginHelper {
    Context mContext;
    ThreadEndT iend;
    LoginInfoBean loginInfoBean;
    private NyVolleyJsonThreadHandler<UserInfoBean> httpHelper;
    private boolean isNeedSaveUser = true;

    public DigitalGamesLoginHelper(Context mContext, ThreadEndT iend) {
        this.mContext = mContext;
        this.iend = iend;
        init();
    }

    public void setIsNeedSaveUser(boolean isNeedSaveUser) {
        this.isNeedSaveUser = isNeedSaveUser;
    }

    private void init() {
        httpHelper = new NyVolleyJsonThreadHandler<UserInfoBean>(mContext) {
            @Override
            protected void successEndT(int total, UserInfoBean data) {

            }

            @Override
            public void successEnd(NyBaseResponse<UserInfoBean> obj) {
                if (obj.getCode().trim().equals("1")) {
                    int total = 0;
                    if (obj.getTotal() != null) {
                        total = obj.getTotal();
                    }
                    if (obj.getData() != null && obj.getData().getSession_id() != null && !obj.getData().getSession_id().equals("")) {
                        UserInfoBean userInfoBean = obj.getData();
                        Date d = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        userInfoBean.setLogin_time(sdf.format(d));
                        ((BaseActivity) mContext).setUserInfoBean(userInfoBean);
                        if (WebSiteUrl.WebId.equals("78")) {
                            if (obj.getData().getId_mod_currency().equals("4")) {
                                WebSiteUrl.AfbAgent = "i@gt";
                            } else if (obj.getData().getId_mod_currency().equals("16")) {
                                WebSiteUrl.AfbAgent = "i@gu";
                            } else {
                                WebSiteUrl.AfbAgent = "i@gt";
                            }
                        }
                    }
                    if (WebSiteUrl.WebId.equals("54")) {
                        loginInfoBean.setNeedShowUser(isNeedSaveUser);
                    }
                    if (WebSiteUrl.WebId.equals("43")) {
                        loginInfoBean.setVipClub360UserName(loginInfoBean.getUsername());
                        loginInfoBean.setUsername(obj.getData().getUseracc());
                    }
                    saveLoginInfo(loginInfoBean);
                    iend.endT(obj, 0);
                } else if (obj.getMsg().trim().equals("-2") && !WebSiteUrl.WebId.equals("30")) {
                    String errorStr = mContext.getString(R.string.username_error) + " or " + mContext.getString(R.string.password_error);
                    if (WebSiteUrl.WebId.equals("54")) {
                        errorStr = "用户名或密码错误,请重新输入";
                    }
                    Toast.makeText(mContext, errorStr, Toast.LENGTH_SHORT).show();
                    iend.endError("");
                } else if (WebSiteUrl.WebId.equals("30") && obj.getMsg().trim().equals("-4")) {
                    String errorStr = mContext.getString(R.string.username_error);
                    Toast.makeText(mContext, errorStr, Toast.LENGTH_SHORT).show();
                    iend.endError("");
                } else if (WebSiteUrl.WebId.equals("30") && obj.getMsg().trim().equals("-5")) {
                    String errorStr = mContext.getString(R.string.password_error);
                    Toast.makeText(mContext, errorStr, Toast.LENGTH_SHORT).show();
                    iend.endError("");
                } else if ((WebSiteUrl.WebId.equals("77") || WebSiteUrl.WebId.equals("78")) && obj.getMsg().trim().equals("-1")) {
                    String errorStr = mContext.getString(R.string.need_activate);
                    Toast.makeText(mContext, errorStr, Toast.LENGTH_SHORT).show();
                    iend.endError("");
                } else {
                    errorEnd("Code:" + obj.getCode() + ",Msg:" + obj.getMsg());
                }
            }


            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("username", loginInfoBean.getUsername());
                params.put("password", loginInfoBean.getPassword());
                String ip = SharePreferenceUtil.getString(mContext, "IP");
                if (ip != null && ip.length() > 1)
                    params.put("IP", ip + "-android/app");
                String lang = AppTool.getAppLanguage(mContext);
                loginInfoBean.setLang("ZH-CN");
                if (lang != null) {
                    if (lang.equals("zh")) {
                        loginInfoBean.setLang("ZH-CN");
                    } else {
                        loginInfoBean.setLang("EN-US");
                    }
                }

                params.put("lang", loginInfoBean.getLang());
                params.put("from", "Android");
                return new QuickRequestBean(RequestBean.Method.POST, WebSiteUrl.Dig88Login, params, new TypeToken<NyBaseResponse<UserInfoBean>>() {
                }.getType());
            }

            @Override
            public void errorEnd(String obj) {
                iend.endError(obj);
                Toast.makeText(mContext, mContext.getString(R.string.failed_to_login), Toast.LENGTH_SHORT).show();
            }
        };

    }

    public void login(LoginInfoBean loginInfoBean) {
        if (loginInfoBean == null)
            loginInfoBean = getLoginInfoBean();
        if (loginInfoBean != null) {
            this.loginInfoBean = loginInfoBean;
            httpHelper.startThread(null);
        }
    }

    public LoginInfoBean getLoginInfoBean() {
        return (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
    }

    public void saveLoginInfo(LoginInfoBean loginInfoBean) {
        AppTool.saveObjectData(mContext, "loginInfo", loginInfoBean);
    }

}
