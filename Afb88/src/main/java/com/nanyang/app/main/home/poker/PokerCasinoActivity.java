package com.nanyang.app.main.home.poker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * Created by Administrator on 2017/2/15.
 */

public class PokerCasinoActivity extends BaseToolbarActivity<PorkerPresenter> implements PorkerContract.View<String> {
    @Bind(R.id.porkercasino_rc)
    RecyclerView casinoRc;
    @Bind(R.id.banner_Img)
    ImageView bannerImg;
    String gdPorker;
    String abf88Porker;
    String ytPorker;
    String p885Porker;
    String digPorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokercasino);
        createPresenter(new PorkerPresenter(this));
        initUi();
    }

    @Override
    public void onGetData(String data) {
        if(data.length()>0) {
            Bundle bundle = new Bundle();
            bundle.putInt("gameType", 3);
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
        Intent intent= getIntent();
        if (intent.getStringExtra("activity").equals("Porker")){
            bannerImg.setBackgroundResource(R.mipmap.poker_banner);
        }else {
            bannerImg.setBackgroundResource(R.mipmap.live_banner);
        }
        gdPorker = getString(R.string.gd_porker);
        abf88Porker = getString(R.string.abf88_Porker);
        ytPorker = getString(R.string.yt_porker);
        p885Porker = getString(R.string.p885_porker);
        digPorker = getString(R.string.dig_porker);
        casinoRc.setLayoutManager(new LinearLayoutManager(mContext));
        List<PorkerCasinoBean> dataList = new ArrayList<>();
        dataList.add(new PorkerCasinoBean(R.mipmap.home_games,gdPorker , "fdasfdasfadfa"));
        dataList.add(new PorkerCasinoBean(R.mipmap.home_games,abf88Porker , "fasdfdasfdasf"));
        dataList.add(new PorkerCasinoBean(R.mipmap.home_games,ytPorker , "fdasfadsfads"));
        dataList.add(new PorkerCasinoBean(R.mipmap.home_games,p885Porker , "fdasfadsf"));
        dataList.add(new PorkerCasinoBean(R.mipmap.home_games,digPorker , "fadsfadsfads"));
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
                if (item.getCasinoName().equals(gdPorker)) {
                 loginGD();
                } else if (item.getCasinoName().equals(abf88Porker)) {
                    Toast.makeText(mContext, item.getCasinoName(), Toast.LENGTH_SHORT).show();
                } else if (item.getCasinoName().equals(ytPorker)) {
                    Toast.makeText(mContext, item.getCasinoName(), Toast.LENGTH_SHORT).show();
                } else if (item.getCasinoName().equals(p885Porker)) {
                    Toast.makeText(mContext, item.getCasinoName(), Toast.LENGTH_SHORT).show();
                } else if (item.getCasinoName().equals(digPorker)) {
                    Toast.makeText(mContext, item.getCasinoName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        casinoRc.setAdapter(porkerAdapter);
    }

    private void loginGD() {
        presenter.skipGd88();
    }
}
