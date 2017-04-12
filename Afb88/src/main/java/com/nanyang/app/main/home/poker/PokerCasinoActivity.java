package com.nanyang.app.main.home.poker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.poker.model.PorkerCasinoBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.finalteam.toolsfinal.ApkUtils;

/**
 * Created by Administrator on 2017/2/15.
 */

public class PokerCasinoActivity extends BaseToolbarActivity<PorkerPresenter> implements PorkerContract.View<String> {
    @Bind(R.id.porkercasino_rc)
    RecyclerView casinoRc;
    @Bind(R.id.banner_Img)
    ImageView bannerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokercasino);
        createPresenter(new PorkerPresenter(this));
        initUi();
    }

    @Override
    public void onGetData(String data) {
        if (data.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("gameType", 3);
            bundle.putString("web_id", "-1");
            bundle.putString("k", data);
            bundle.putString("us", getApp().getUser().getUserName());

            try {
                AfbUtils.appJump(mContext, "gaming178.com.baccaratgame", "gaming178.com.casinogame.Activity.WelcomeActivity", bundle);
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
    public void onFailed(String error) {

    }

    @Override
    public void initUi() {
        Intent intent = getIntent();
        if (intent.getStringExtra("activity").equals("Porker")) {
            bannerImg.setBackgroundResource(R.mipmap.poker_banner);
        } else {
            bannerImg.setBackgroundResource(R.mipmap.live_banner);
        }

        casinoRc.setLayoutManager(new LinearLayoutManager(mContext));
        List<PorkerCasinoBean> dataList = new ArrayList<>();
        dataList.add(new PorkerCasinoBean(R.mipmap.casino_gd88, getString(R.string.gd88), getString(R.string.gd_content)));
        dataList.add(new PorkerCasinoBean(R.mipmap.casino_gdc, getString(R.string.gdc), getString(R.string.gr_content)));

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

    private void loginGD() {
        if (ApkUtils.isAvilible(this, "gaming178.com.baccaratgame")) {
            presenter.skipGd88();
        } else {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(AppConstant.DownLoadDig88AppUrl);
            intent.setData(content_url);
            startActivity(intent);
        }
    }
}
