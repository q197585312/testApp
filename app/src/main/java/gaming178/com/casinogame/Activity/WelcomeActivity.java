package gaming178.com.casinogame.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.common.ActivityPageManager;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.entity.AppVersionBean;
import gaming178.com.casinogame.Bean.GdOrGrAgentBean;
import gaming178.com.casinogame.Util.ErrorCode;
import gaming178.com.casinogame.Util.Gd88Utils;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.LogIntervalUtils;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.casinogame.login.LoginActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.allinone.util.GdToastUtils;
import gaming178.com.mylibrary.allinone.util.MD5;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.allinone.util.UpdateManager;
import gaming178.com.mylibrary.base.RequestBean;
import gaming178.com.mylibrary.base.quick.QuickCookieThreadHandler;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;

/**
 * Created by xToney on 2015/12/24.
 */
public class WelcomeActivity extends BaseActivity {
    private static final int LOGIN_ERROR_MSG = -13;
    final int READ_CODE = 101;
    final int INSTALL_CODE = 102;
    final int INSTALL_AFB_CODE = 109;
    long currentTime;
    private String updateUrl;
    private File loadFile;
    private FrameLayout flWelcome;
    private LinearLayout llLogin;
    private ImageView bgImg;
    private String agentName;
    private String requestUrl;
    private int version = Build.VERSION.SDK_INT;
    private String appVersionUrl = "http://www.gd88.app/api/gd88AndroidVersion.php?Labelid=";
    private Gson gson = new Gson();

    private boolean hasVersionChecked;
    private boolean hasWebsiteChecked;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {


            switch (msg.what) {
                case ErrorCode.LOGIN_ERROR_NETWORK:
                    Toast.makeText(mContext, R.string.login_network_error, Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case ErrorCode.DATA_ERROR_LENGTH:
                    Toast.makeText(mContext, R.string.login_data_error, Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case ErrorCode.LOGIN_AREADY:
                    goLobby();
                    break;
                case HandlerCode.SHOW_BACCARACT:
                    dismissBlockDialog();
                    skipAct(LobbyBaccaratActivity.class);
                    AppTool.setAppLanguage(mContext, AppTool.getAppLanguage(mContext));
                    finish();
                    break;
                case HandlerCode.SHOW_BACCARACT_FAIL:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.server_network_error, Toast.LENGTH_LONG).show();
                    break;
                case ErrorCode.LOGIN_SECCESS:
                    dismissBlockDialog();
                    skipAct(LobbyActivity.class);
                    if (WebSiteUrl.GameType != 3) {
                        finish();
                    }
                    break;
                case ErrorCode.LOGIN_ERROR_USERNAME:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.login_username_error, Toast.LENGTH_LONG).show();
                    break;
                case LOGIN_ERROR_MSG:
                    dismissBlockDialog();
                    Toast.makeText(mContext, (String) msg.obj, Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case ErrorCode.REQUEST_SECCESS:
                    initAgentName(getIntent());
                    break;
                case ErrorCode.CHECHED_VERSION:
                    hasVersionChecked = true;
                    CheckNext();
                    break;
                case ErrorCode.GET_WEBSITE:
                    hasWebsiteChecked = true;
                    CheckNext();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_welcome_gd;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        String firstLg;
        if (!BuildConfig.FLAVOR.isEmpty() && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            firstLg = "my";
        } else {
            firstLg = "en";
        }
        if (Gd88Utils.isFirstIn(mContext)) {
            AppTool.setAppLanguage(mContext, firstLg);
        }
        LogIntervalUtils.logCustomTime(currentTime, "onCreate");
        ActivityPageManager.getInstance().addActivity(this);
        flWelcome = (FrameLayout) findViewById(R.id.gd__fl_welcome);
        llLogin = (LinearLayout) findViewById(R.id.gd__ll_login);
        bgImg = (ImageView) findViewById(R.id.gd__welcome_img);
        if (!BuildConfig.FLAVOR.isEmpty() && BuildConfig.FLAVOR.equals("gd88") || BuildConfig.FLAVOR.equals("liga365")) {
            bgImg.setImageResource(R.mipmap.gd88_welcome_logo);
        } else {
            bgImg.setImageResource(R.mipmap.gd_title_logo);
        }
        flWelcome.setVisibility(View.VISIBLE);
        llLogin.setVisibility(View.GONE);
        mContext = this;
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        if (it != null && bundle != null && !TextUtils.isEmpty(bundle.getString("web_id"))) {
            bgImg.setVisibility(View.GONE);
        } else {
            bgImg.setVisibility(View.VISIBLE);
        }
        initWebsiteUrl();
    }

    private void initWebsiteUrl() {
        hasWebsiteChecked = false;
        handler.sendEmptyMessage(ErrorCode.GET_WEBSITE);
        hasNewVersion();
    }

    private void hasNewVersion() {
        if (BuildConfig.FLAVOR.isEmpty()) {
            goMain();
            return;
        }
        updateUrl = null;
        hasVersionChecked = false;
        QuickCookieThreadHandler threadHandler = new QuickCookieThreadHandler(this) {
            @Override
            protected QuickRequestBean getRequestBean() {
                return new QuickRequestBean(RequestBean.Method.GET, appVersionUrl + BuildConfig.Labelid, new HashMap(), new TypeToken<String>() {
                }.getType());
            }

            @Override
            public void successEnd(String data) {
                if (data == null)
                    return;
                AppVersionBean appVersionBean = gson.fromJson(data, AppVersionBean.class);
                PackageInfo packageInfo = AppTool.getApkInfo(mContext);
                float localVersion = Float.parseFloat(packageInfo.versionName);
                float serverVersion = Float.parseFloat(appVersionBean.getData().getVersion().trim());
                Log.d("AppData", serverVersion + ":server");
                Log.d("AppData", localVersion + ":local");
                Log.d("AppData", data + ":data");
                if (version >= 23) {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    int REQUEST_EXTERNAL_STORAGE = 1;
                    String[] PERMISSIONS_STORAGE = {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    };
                    int permission = ActivityCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                                WelcomeActivity.this,
                                PERMISSIONS_STORAGE,
                                REQUEST_EXTERNAL_STORAGE
                        );
                    }
                }
                if (AppTool.getApkInfo(mContext) != null && serverVersion > localVersion) {
                    updateUrl = appVersionBean.getData().getUrl();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            dismissBlockDialog();
                        }
                    });
                }
                handler.sendEmptyMessage(ErrorCode.CHECHED_VERSION);
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                handler.sendEmptyMessage(ErrorCode.CHECHED_VERSION);
            }
        };
        threadHandler.startThread(null);
    }

    private void goMain() {
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        if (BuildConfig.FLAVOR.isEmpty() || BuildConfig.FLAVOR.equals("gd88")) {
            if (it != null && bundle != null && !TextUtils.isEmpty(bundle.getString("web_id"))) {
                hasAppIntent();
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AppTool.activiyJump(WelcomeActivity.this, LoginActivity.class);
                        AppTool.setAppLanguage(mContext, AppTool.getAppLanguage(mContext));
                        finish();
                    }
                }, 2000);
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppTool.activiyJump(WelcomeActivity.this, LoginActivity.class);
                    AppTool.setAppLanguage(mContext, AppTool.getAppLanguage(mContext));
                    finish();
                }
            }, 2000);
        }

    }

    private void hasAppIntent() {
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        Log.d("AppData", it.toString());
        final int gameType = bundle.getInt("gameType", 0);
        LogIntervalUtils.logCustomTime(currentTime, "进入欢迎页开始解析数据");
        final int web_id = Integer.parseInt(bundle.getString("web_id"));
        final String currency = bundle.getString("currency");
        fromDig88(web_id, currency, gameType);
    }

    private void CheckNext() {
        if (hasVersionChecked && hasWebsiteChecked) {
            if (!StringUtils.isNull(updateUrl)) {
                updateApp(updateUrl);
            } else {
                goMain();
            }
        }
    }

    private void updateApp(String updateUrl) {
        this.updateUrl = updateUrl;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE

        };
        if (requestPermission(PERMISSIONS_STORAGE, READ_CODE)) {
            showDownload(updateUrl);
        }
    }

    private void showDownload(String updateUrl) {
        UpdateManager updateManager = new UpdateManager(mContext);
        updateManager.setCancel("");
        updateManager.setTitle(getString(R.string.gd_version_update));
        updateManager.setUpdate(getString(R.string.update));
        updateManager.setUpdateMsg(getString(R.string.gd_update_msg));
        updateManager.setLoadTitle(getString(R.string.app_update));
        updateManager.checkUpdate(updateUrl);
        updateManager.setOnLoadEnd(new UpdateManager.ILoad() {
            @Override
            public void onLoadEnd(File file) {
                WelcomeActivity.this.loadFile = file;
                checkIsAndroidO();
            }
        });
    }

    public void goLobby() {
        if (WebSiteUrl.GameType == 0) {
            AppTool.activiyJump(WelcomeActivity.this, LobbyActivity.class);
            AppTool.setAppLanguage(mContext, AppTool.getAppLanguage(mContext));
            finish();
        } else {
            updateTableStatus();
        }
    }

    private void updateTableStatus() {
        UpdateGameStatus updateGameStatus = new UpdateGameStatus();
        new Thread(updateGameStatus).start();
    }

    private void loginInit(int gameType, String currency, String balance) {
        String strRes;
        String annoucementParams = "lng=" + 0 + "&Usid=" + mAppViewModel.getUser().getName();
        strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.GAME_GG_URL, annoucementParams);
        if (strRes.equals("netError")) {
            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
            return;
        }

        String ann[] = strRes.split("Results=ok\\|");
        if (ann.length > 1)
            mAppViewModel.setAnnouncement(ann[1]);

        strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.TABLE_INFO_A_URL/*"http://96.9.71.29/DIGKorean/select_tb_infoa.jsp"*/, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
        if (strRes.equals("netError")) {
            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
            return;
        }
        String tableInfo[] = strRes.split("\\^");
        if (tableInfo.length < 8) {
            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
            return;
        }
        mAppViewModel.setbInitLimit(false);
        mAppViewModel.splitTableInfo(strRes, mAppViewModel.getHallId());
        mAppViewModel.getUser().setCurrency(currency);
        mAppViewModel.getUser().setBalance(DecimalUtils.round(Double.valueOf(balance), 2));
        strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.COUNTDOWN_URL, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
        if (strRes.equals("netError")) {
            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
            return;
        }
        mAppViewModel.splitTimer(strRes);
        mAppViewModel.setbLogin(true);
        mAppViewModel.setbLobby(true);
        handler.sendEmptyMessage(ErrorCode.LOGIN_AREADY);
        WebSiteUrl.isDomain = true;
        return;
    }

    private void fromDig88(int web_id, String currency, int gameType) {
        try {
            WebSiteUrl.setDomain(gameType, "113.130.125.201");
            getAgentName(gameType, currency, web_id);
        } catch (Exception e) {
            Log.d("AppData", e.getMessage());
            e.printStackTrace();
            Toast.makeText(WelcomeActivity.this, getResources().getString(R.string.login_data_error), Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
        }
    }

    private void loginFromDig88(final int gameType, final int web_id, final String currency, final String username, final String balance, final String language) {
        try {
            if (agentName != null) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Map<String, String> patameterMap = new HashMap<String, String>();
                            Map<String, String> moneyMap = new HashMap<String, String>();
                            String userName = web_id + "t" + username;
                            String password = null;
                            patameterMap.put("strUsername", userName);
                            moneyMap.put("strUsername", userName);
                            moneyMap.put("addCredit", balance);
                            moneyMap.put("sIP", AppTool.getLocalIP());
                            SimpleDateFormat dff = new SimpleDateFormat("MMddHHmmss");
                            dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                            String dateStr = dff.format(new Date());
                            moneyMap.put("Serial", dateStr);
                            patameterMap.put("strAGName", agentName);
                            password = "DIG88" + username;
                            patameterMap.put("OperatorName", "dig88");
                            patameterMap.put("OperatorPwd", "GDC=>HL111888");
                            moneyMap.put("OperatorName", "dig88");
                            moneyMap.put("OperatorPwd", "GDC=>HL111888");
                            password = MD5.md5(password);
                            patameterMap.put("strPassword", password);
                            moneyMap.put("strPwd", password);
                            String appWebServiceUrl = WebSiteUrl.AppWebServiceUrl;
                            String nameSpace = WebSiteUrl.AppWebServiceNameSpace;
                            HttpClient httpClient = new HttpClient(appWebServiceUrl, "");
                            String soapRequestData = httpClient.buildRequestData(patameterMap, nameSpace, "CreateMember");
                            String strPost = httpClient.sendPostSoap(WebSiteUrl.AppWebServiceUrl, soapRequestData);
                            String moneyRequestData = httpClient.buildRequestData(moneyMap, nameSpace, "memDeposit");
                            String strMoney = httpClient.sendPostSoap(WebSiteUrl.AppWebServiceUrl, moneyRequestData);
                            if (strPost != null && !"netError".equals(strPost)) {
                                StringReader sr = new StringReader(strPost);
                                InputSource is = new InputSource(sr);
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                Document doc = builder.parse(is);
                                if (doc.getElementsByTagName("return") != null) {
                                    String return_value = doc.getElementsByTagName("return").item(0).getFirstChild().getNodeValue();
                                    if ("0".equals(return_value) || "2".equals(return_value)) {
                                        String params = "txtAcctid=" + userName + "&txtPwd=" + password + "&txtLang=" + (language.equals("zh") ? "0" : "1") + "&txtRandCode=jBmkBignwbhcMiVEvneJNRPlNhLGKEgnsiToyrIjMbHZBbcLAm";
                                        String resReturn = httpClient.sendPost(WebSiteUrl.AppDig88LoginUrl, params);
                                        if ((resReturn != null && resReturn.contains("Results=ok#")) || (WebSiteUrl.GameType == 1 && !resReturn.equals("netError"))) {//登录成功
                                            String strRes = "";
                                            switch (language) {
                                                case "zh":
                                                    AppTool.setAppLanguage(WelcomeActivity.this, "zh");
                                                    break;
                                                case "zh_TW":
                                                    AppTool.setAppLanguage(WelcomeActivity.this, "zh_TW");
                                                    break;
                                                case "th":
                                                    AppTool.setAppLanguage(WelcomeActivity.this, "th");
                                                    break;
                                                default:
                                                    AppTool.setAppLanguage(WelcomeActivity.this, "en");
                                                    break;
                                            }
                                            mAppViewModel.getUser().setName(userName);
                                            if (mAppViewModel.getCookie() == null || mAppViewModel.getCookie().equals("")) {
                                                mAppViewModel.setHttpClient(new HttpClient(WebSiteUrl.INDEX, mAppViewModel.getCookie()));
                                                if (mAppViewModel.getHttpClient().connect("POST") == false) {
                                                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                                                    return;
                                                }
                                                mAppViewModel.getHttpClient().getBodyString("UTF-8");
                                                mAppViewModel.setCookie(mAppViewModel.getHttpClient().getSessionId());
                                            }
                                            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LOGOUT_URL/*"http://96.9.71.29/DIGKorean/main.jsp"*/, "membername=" + mAppViewModel.getUser().getName() + "&lang=0");
                                            //语言设定
                                            loginInit(gameType, currency, balance);
                                            return;
                                        }
                                    }
                                }
                                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                            } else {
                                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                            }
                        } catch (Exception e) {
                            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                            if (e != null) {
                                Log.d("AppData", e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }
                }
                );
                thread.start();
            } else {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(WelcomeActivity.this, getResources().getString(R.string.login_data_error), Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
        }
    }

    private void initAgentName(Intent it) {
        Bundle bundle = it.getExtras();
        if (Intent.ACTION_VIEW.equals(it.getAction())) {
            Uri uri = it.getData();
            if (uri != null) {
                String language = uri.getQueryParameter("language");
                String balance = uri.getQueryParameter("balance");
                String username = uri.getQueryParameter("username");
                String web_id = uri.getQueryParameter("web_id");
                String game_type = uri.getQueryParameter("game_type");
                String currency = uri.getQueryParameter("currency");
                loginFromDig88(Integer.valueOf(game_type), Integer.valueOf(web_id), currency, username, balance, language);
            }
        } else {
            final int gameType = bundle.getInt("gameType", 0);
            final int web_id = Integer.parseInt(bundle.getString("web_id"));
            final String currency = bundle.getString("currency");
            final String username = bundle.getString("username");
            final String balance = bundle.getString("balance");
            final String language = bundle.getString("language");
            loginFromDig88(gameType, web_id, currency, username, balance, language);
        }
    }

    public void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
            } else {
                //请求安装未知应用来源的权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_CODE);
            }
        } else {
            AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
        }
    }

    public void getAgentName(int gameType, final String currency, final int web_id) {
        final Gson gson = new Gson();
        if (gameType == 1) {
            requestUrl = WebSiteUrl.GET_GR_CASINO_URL;
        } else {
            requestUrl = WebSiteUrl.GET_GD_CASINO_URL;
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpClient httpClient = new HttpClient();
                String resultStr = httpClient.sendPost(requestUrl, "web_id=" + web_id + "&currency=" + currency);
                try {
                    GdOrGrAgentBean bean = gson.fromJson(resultStr, GdOrGrAgentBean.class);
                    agentName = bean.getData().getAgent();
                    handler.sendEmptyMessage(ErrorCode.REQUEST_SECCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                }

            }
        }.start();
    }

    public class UpdateGameStatus implements Runnable {
        int iError = 0;

        public void run() {
            try {
                if (mAppViewModel.isbLogin()) {
                    String statusUrl = "";
                    statusUrl = WebSiteUrl.TABLE_INFO_A_URL;
                    String strRes = mAppViewModel.getHttpClient().sendPost(statusUrl, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                    String tableInfo[] = strRes.split("\\^");
                    if (strRes.equals("netError") || strRes.equals("Results=no") || tableInfo.length < 9) {//连续5次拿不到数据就退出，返回到登录界面
                        iError++;
                    } else {
                        iError = 0;
                    }
                    if (iError == 0) {
                        mAppViewModel.splitTableInfo(strRes, mAppViewModel.getHallId());
                    }
                    switch (mAppViewModel.getHallId()) {
                        case 1:
                            statusUrl = WebSiteUrl.COUNTDOWN_URL_A;
                            break;
                        case 2:
                            statusUrl = WebSiteUrl.COUNTDOWN_URL_B;
                            break;
                    }
                    strRes = mAppViewModel.getHttpClient().sendPost(statusUrl, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                    if (strRes.equals("netError") || strRes.equals("Results=no")) {//连续10次拿不到数据就退出，返回到登录界面
                        iError++;
                    } else {
                        iError = 0;
                    }
                    if (iError == 0) {
                        mAppViewModel.splitTimer(strRes);
                        handler.sendEmptyMessage(HandlerCode.SHOW_BACCARACT);
                    } else
                        handler.sendEmptyMessage(HandlerCode.SHOW_BACCARACT_FAIL);
                }

            } catch (Exception e) {
                e.printStackTrace();
                handler.sendEmptyMessage(HandlerCode.SHOW_BACCARACT_FAIL);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showDownload(updateUrl);
                } else {
                    GdToastUtils.showToast(this, getString(R.string.no_permission));
                    //  引导用户手动开启安装权限
                }
                break;
            case INSTALL_CODE:
                AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                } else {
                    GdToastUtils.showToast(this, getString(R.string.open_install));
                    //  引导用户手动开启安装权限
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    startActivityForResult(intent, INSTALL_CODE);
                    Uri packageURI = Uri.parse("package:gaming178.com.baccaratgame");//设置包名，可直接跳转当前软件的设置页面
                    Intent ii = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                    startActivityForResult(ii, INSTALL_AFB_CODE);
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INSTALL_AFB_CODE:
                AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSiteUrl.GameType = 0;
    }
}
