package com.nanyang.app.main.home.gdCasino;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.nanyang.app.main.home.gdCasino.model.PorkerCasinoBean;
import com.nanyang.app.main.home.sport.dialog.TransferMoneyPop;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.BaseView;
import com.unkonw.testapp.libs.utils.PermissionUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.finalteam.toolsfinal.ApkUtils;

/**
 * Created by Administrator on 2017/2/15.
 */

public class PokerCasinoActivity extends BaseToolbarActivity<LanguagePresenter> implements ILanguageView<String> {
    @Bind(R.id.porkercasino_rc)
    RecyclerView casinoRc;
    @Bind(R.id.banner_Img)
    ImageView bannerImg;
    AfbApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokercasino);
        createPresenter(new LanguagePresenter(this));
        app = (AfbApplication) getApplication();
        initUi();
       /* readExternalStorage();
        writeExternalStorage();*/
    }

    public void readExternalStorage() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_EXTERNAL_STORAGE, mPermissionGrant);
    }

    public void writeExternalStorage() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE, mPermissionGrant);
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
                    Toast.makeText(mContext, "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_GET_ACCOUNTS:
                    Toast.makeText(mContext, "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
                    Toast.makeText(mContext, "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
                    Toast.makeText(mContext, "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CAMERA:
                    Toast.makeText(mContext, "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
                    Toast.makeText(mContext, "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
                    Toast.makeText(mContext, "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                    Toast.makeText(mContext, "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    Toast.makeText(mContext, "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }

    @Override
    public void onGetData(final String data) {
        presenter.getTransferMoneyData(new BaseConsumer<TransferMoneyBean>(this) {
            @Override
            protected void onBaseGetData(TransferMoneyBean transferMoneyBean) {
                getMoneyMsg(transferMoneyBean, data);
            }
        });
    }

    @Override
    public void onFailed(String error) {

    }

    public void initUi() {
        Intent intent = getIntent();
        tvToolbarLeft.setVisibility(View.VISIBLE);
        if (intent.getStringExtra("activity").equals("Porker")) {
            bannerImg.setBackgroundResource(R.mipmap.poker_banner);
        } else {
            bannerImg.setBackgroundResource(R.mipmap.live_banner);
        }

        casinoRc.setLayoutManager(new LinearLayoutManager(mContext));
        List<PorkerCasinoBean> dataList = new ArrayList<>();
        dataList.add(new PorkerCasinoBean(R.mipmap.casino_gd88, getString(R.string.gd88), getString(R.string.gd_content)));
//        dataList.add(new PorkerCasinoBean(R.mipmap.casino_gdc, getString(R.string.gdc), getString(R.string.gr_content)));

        BaseRecyclerAdapter<PorkerCasinoBean> porkerAdapter = new BaseRecyclerAdapter<PorkerCasinoBean>(mContext, dataList, R.layout.item_porkercasino) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, PorkerCasinoBean item) {
                ImageView iv = holder.getView(R.id.porkercasino_img);
                TextView nameTv = holder.getView(R.id.porkercasino_nameTv);
                TextView introduceTv = holder.getView(R.id.porkercasino_introduceTv);
                iv.setImageResource(item.getImg());
                nameTv.setText(item.getCasinoName());
                introduceTv.setText(item.getCasinoIntroduce());
            }
        };
        porkerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<PorkerCasinoBean>() {
            @Override
            public void onItemClick(View view, PorkerCasinoBean item, int position) {
                if (item.getCasinoName().equals(getString(R.string.gd88)) || item.getCasinoName().equals(getString(R.string.gdc))) {
                    loginGD();
                }

            }
        });
        casinoRc.setAdapter(porkerAdapter);
    }

    @Override
    public void onLanguageSwitchSucceed(String str) {

    }

    @Override
    public void getMoneyMsg(final TransferMoneyBean transferMoneyBean, final String data) {
        TransferMoneyPop pop = new TransferMoneyPop(mContext, casinoRc) {
            @Override
            public void initMsgData(TextView tv_balance, TextView tv_casino_balance, EditText edt_amount) {
                TransferMoneyBean.DicAllBean bean = transferMoneyBean.getDicAll().get(0);
                tv_balance.setText(isStartWithTag(bean.getCredit()));
                tv_casino_balance.setText(isStartWithTag(bean.getGdBalance()));
            }

            @Override
            public void setOnCancelListener() {
                startApp(data);
            }

            @Override
            public void setOnSureListener(final String money) {
                if (!TextUtils.isEmpty(money) && Integer.parseInt(money) != 0) {
                    presenter.gamesGDTransferMonet(money, new BaseConsumer<String>(PokerCasinoActivity.this) {
                        @Override
                        protected void onBaseGetData(String data) {
                            onGetTransferMoneyData(0, money, data);
                        }
                    });
                    closePopupWindow();
                } else {
                    ToastUtils.showShort(getString(R.string.Input_the_amount_please));
                }
            }

        };
        pop.showPopupCenterWindow();
    }

    @Override
    public void onGetTransferMoneyData(int type, String getBackStr, String data) {
        if (getBackStr.contains("not allowed")) {
            ToastUtils.showShort(getBackStr);
        } else {
            ToastUtils.showShort(getBackStr);
            startApp(data);
        }
    }

    private void loginGD() {
        if (ApkUtils.isAvilible(this, "gaming178.com.baccaratgame")) {
            presenter.skipGd88(new BaseView<PokerCasinoActivity, String>(this) {
                @Override
                public void onGetData(String data) {
                    PokerCasinoActivity.this.onGetData(data);
                }
            });
        } else {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(AppConstant.DownLoadDig88AppUrl);
            intent.setData(content_url);
            startActivity(intent);
        }
    }

    private SpannableStringBuilder isStartWithTag(String str) {
        if (str.startsWith("<SPAN")) {
            String needStr = Html.fromHtml(str).toString();
            if (needStr.startsWith("-")) {
                return AfbUtils.handleStringTextColor(needStr, Color.RED);
            }
            return new SpannableStringBuilder(needStr);
        } else {
            if (str.startsWith("-")) {
                return AfbUtils.handleStringTextColor(str, Color.RED);
            } else {
                return new SpannableStringBuilder(str);
            }
        }
    }

    private void startApp(String data) {
        if (data.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("gameType", 3);
            bundle.putString("web_id", "-1");
            bundle.putString("k", data);
            bundle.putString("us", getApp().getUser().getLoginName());
            bundle.putString("lang", AfbUtils.getLanguage(mContext));
            bundle.putInt("homeColor", getHomeColor());
            try {
//                AfbUtils.appJump(mContext, "gaming178.com.baccaratgame", "gaming178.com.casinogame.Activity.WelcomeActivity", bundle);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName comp = new ComponentName("gaming178.com.baccaratgame",
                        "gaming178.com.casinogame.Activity.WelcomeActivity");
                intent.setComponent(comp);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                startActivityForResult(intent, 7);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(AppConstant.DownLoadDig88AppUrl);
                intent.setData(content_url);
                startActivity(intent);
            }
        }
    }

    @Override
    public void finish() {
        skipAct(MainActivity.class);
        super.finish();

    }

    @Override
    public void againLogin(final String gameType) {
        presenter.login(new LoginInfo(app.getUser().getLoginName(), app.getUser().getPassword()), new BaseConsumer<String>(this) {
            @Override
            protected void onBaseGetData(String data) {
                if (BuildConfig.FLAVOR.equals("afb1188"))
                    onLanguageSwitchSucceed(data);
                else
                    onLoginAgainFinish(gameType);
            }
        });
    }

    @Override
    public void onLoginAgainFinish(String gameType) {
        switchSkipAct(gameType);
    }
}
