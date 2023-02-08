package nanyang.com.dig88.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.DigApp;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BlockDialog;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/10/20.
 */
public abstract class BaseFragment<T extends BaseRetrofitPresenter> extends gaming178.com.mylibrary.base.component.BaseFragment implements IBaseContext {
    public int screenWidth;
    public int screenHeight;
    public Gson gson;
    public boolean isNeedShowBack = false;
    public boolean isUploading;
    protected T presenter;
    protected BlockDialog dialog;
    private String title;
    private String receipt;
    private String msgCount;
    private int contentType = -1;

    @Override
    public com.unkonw.testapp.libs.base.BaseActivity getBaseActivity() {
        return getAct();
    }

    @Override
    public void showLoadingDialog() {
        if (dialog != null && !dialog.isShowing())
            dialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    /**
     * 创建相应的 presenter
     */
    public void createPresenter(T presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (getActivity() == null) {
            return;
        }
        Dig88Utils.setLang(mContext);
        dialog = new BlockDialog(mContext, getString(R.string.loading));
        gson = new Gson();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;         // 屏幕宽度（像素）
        screenHeight = dm.heightPixels;       // 屏幕高度（像素）
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserInfoBean getUserInfo() {
        if (getApp() != null)
            return getApp().getUserInfo();
        return null;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        if (getApp() != null) {
            getApp().setUserInfo(userInfo);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Dig88Utils.setLang(mContext);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    public DigApp getApp() {
        if (getActivity() != null)
            return ((BaseActivity) getActivity()).getApp();
        return null;
    }

    protected BaseActivity getAct() {
        return (BaseActivity) getActivity();
    }

    public void showBlockDialog() {
        getAct().showBlockDialog();
    }

    public void dismissBlockDialog() {
        if (getAct() != null)
            getAct().dismissBlockDialog();
    }

    public boolean isshowGame(String gameStu) {
        if (TextUtils.isEmpty(gameStu)) {
            return false;
        }
        if (gameStu.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public String getCurrency() {
        if (getUserInfo() == null || getUserInfo().getId_mod_currency() == null) {
            return "";
        }
        String currencyId = getUserInfo().getId_mod_currency();
        String currency = "USD";
        if (currencyId.equals("1")) {
            currency = "USD";
        } else if (currencyId.equals("2")) {
            currency = "SGD";
        } else if (currencyId.equals("3")) {
            currency = "MYR";
            if (WebSiteUrl.WebId.equals("155")) {
                if (getUserInfo().getPoker_id().equals("1")) {
                    currency = "BND";
                }
            }
        } else if (currencyId.equals("4")) {
            currency = "THB";
        } else if (currencyId.equals("5")) {
            currency = "CNY";
        } else if (currencyId.equals("6")) {
            currency = "HKD";
        } else if (currencyId.equals("8")) {
            currency = "VND";
        } else if (currencyId.equals("9")) {
            currency = "TEST";
        } else if (currencyId.equals("11")) {
            currency = "IDR";
        } else if (currencyId.equals("12")) {
            currency = "KRW";
        } else if (currencyId.equals("13")) {
            currency = "EUR";
        } else if (currencyId.equals("14")) {
            currency = "GBP";
        } else if (currencyId.equals("15")) {
            currency = "SEK";
        } else if (currencyId.equals("16")) {
            currency = "TWD";
        } else if (currencyId.equals("17")) {
            currency = "ZAR";
        } else if (currencyId.equals("18")) {
            currency = "MMK";
        } else if (currencyId.equals("19")) {
            currency = "BT4";
        } else if (currencyId.equals("20")) {
            currency = "BND";
        } else if (currencyId.equals("21")) {
            currency = "LTC";
        }
        return currency;
    }

    public String getCurrency(String currencyId) {
        String currency = "USD";
        if (currencyId.equals("1")) {
            currency = "USD";
        } else if (currencyId.equals("2")) {
            currency = "SGD";
        } else if (currencyId.equals("3")) {
            currency = "MYR";
            if (WebSiteUrl.WebId.equals("155")) {
                if (getUserInfo().getPoker_id().equals("1")) {
                    currency = "BND";
                }
            }
        } else if (currencyId.equals("4")) {
            currency = "THB";
        } else if (currencyId.equals("5")) {
            currency = "CNY";
        } else if (currencyId.equals("6")) {
            currency = "HKD";
        } else if (currencyId.equals("8")) {
            currency = "VND";
        } else if (currencyId.equals("9")) {
            currency = "TEST";
        } else if (currencyId.equals("11")) {
            currency = "IDR";
        } else if (currencyId.equals("12")) {
            currency = "KRW";
        } else if (currencyId.equals("13")) {
            currency = "EUR";
        } else if (currencyId.equals("14")) {
            currency = "GBP";
        } else if (currencyId.equals("15")) {
            currency = "SEK";
        } else if (currencyId.equals("16")) {
            currency = "TWD";
        } else if (currencyId.equals("17")) {
            currency = "ZAR";
        } else if (currencyId.equals("18")) {
            currency = "MMK";
        } else if (currencyId.equals("19")) {
            currency = "BT4";
        } else if (currencyId.equals("20")) {
            currency = "BND";
        } else if (currencyId.equals("21")) {
            currency = "LTC";
        }
        return currency;
    }

    public String getLocalLanguage() {
        return AppTool.getAppLanguage(mContext);
    }

    public boolean hasLoginInfo() {
        UserInfoBean userInfo = getUserInfo();
        if (userInfo == null) {
            return false;
        }
        String sessionId = userInfo.getSession_id();
        String userId = userInfo.getUser_id();
        LoginInfoBean info = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        if (info == null || TextUtils.isEmpty(sessionId) || TextUtils.isEmpty(userId)) {
            return false;
        } else {
            return true;
        }
    }

    public void refreshMoney(String money) {

    }

    public void requestPermission() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }

    public void showMsgCount(String msgCount) {

    }

    public void showChoosePic(Uri uri) {

    }

    public void back() {
        getActivity().finish();
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }
}
