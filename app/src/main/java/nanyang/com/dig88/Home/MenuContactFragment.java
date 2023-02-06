package nanyang.com.dig88.Home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.Activity.AffiliateRegisterActivity;
import nanyang.com.dig88.Activity.MsgBoxActivity;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.ContactBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Home.Presenter.MenuContactPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.Dig88Utils;

/**
 * Created by Administrator on 2019/6/20.
 */

public class MenuContactFragment extends BaseFragment<MenuContactPresenter> {
    @Bind(R.id.rc_content)
    RecyclerView rcContent;
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_msg_count)
    TextView tvMsgCount;
    @Bind(R.id.img_left)
    ImageView imgLeft;
    @Bind(R.id.ll_parent)
    LinearLayout llParent;
    @Bind(R.id.view_title)
    View viewTitle;
    @Bind(R.id.ll_normal)
    LinearLayout llNormal;
    @Bind(R.id.webView)
    WebView webView;
    int[] drawableRes = {R.drawable.rectangle_blue_dark_allradius8, R.drawable.rectangle_geen500_allradius8, R.drawable.rectangle_blue300_allradius8, R.drawable.rectangle_yellow_allradius8, R.drawable.rectangle_orange700_allradius8, R.drawable.rectangle_red_allradius8, R.drawable.rectangle_violet_allradius8};
    BaseRecyclerAdapter<ContactBean.DataBean> adapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_menu_contact;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new MenuContactPresenter(this));
        tvToolbarTitle.setText(getString(R.string.Contact_Us));
        if (BuildConfig.FLAVOR.equals("fun77") || BuildConfig.FLAVOR.equals("kbet3")) {
            llNormal.setVisibility(View.GONE);
            viewTitle.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            presenter.initWebView(presenter.getWebViewLiveChatUrl(), webView);
        } else {
            llNormal.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
            if (BuildConfig.FLAVOR.equals("ibet567") || BuildConfig.FLAVOR.equals("istana168")) {
                llParent.setVisibility(View.GONE);
            }
            imgLeft.setImageResource(R.mipmap.contact_email);
            tvContent.setText(getString(R.string.msg));
            if (!TextUtils.isEmpty(getMsgCount())) {
                setTvMsgCount(getMsgCount());
            }
            llParent.setBackgroundResource(drawableRes[0]);
            adapter = new BaseRecyclerAdapter<ContactBean.DataBean>(mContext, new ArrayList<ContactBean.DataBean>(), R.layout.item_menu_contact) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, ContactBean.DataBean item) {
                    LinearLayout llParent = holder.getView(R.id.ll_parent);
                    String color = item.getColor();
                    if (!TextUtils.isEmpty(color)) {
                        if (!color.contains("#")) {
                            color = "#" + color;
                        }
                        llParent.setBackgroundResource(R.drawable.rectangle_null_allradius8);
                        if (color.equals("#fc0")) {
                            color = "#FFCC00";
                        }
                        GradientDrawable btnPreDrawable = (GradientDrawable) llParent.getBackground();
                        btnPreDrawable.setColor(Color.parseColor(color));
                    } else {
                        llParent.setBackgroundResource(drawableRes[(position + 1) % drawableRes.length]);
                    }
                    TextView tvContent = holder.getView(R.id.tv_content);
                    TextView tvRight = holder.getView(R.id.tv_right);
//                    tvRight.setText(item.getChatType());
                    ImageView imgLeft = holder.getView(R.id.img_left);
                    tvRight.setVisibility(View.INVISIBLE);
                    String content = (item.getChatType().equals("tel") ? "Telphone" : item.getChatType()) + ": " + item.getChatContent();
                    if (item.getChatType().equals("livechat_2")) {
                        content = "livechat";
                    } else if (BuildConfig.FLAVOR.equals("hjlh6688") && item.getChatContent().equals("在线客服")) {
                        content = item.getChatContent();
                    }
                    if (item.getChatType().equals("Affiliate Signup")) {
                        content = getString(R.string.Affiliate_Signup);
                        imgLeft.setImageResource(R.mipmap.affiliate_person);
                    } else {
                        Glide.with(mContext).load(item.getPiclogo()).skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE).into(imgLeft);
                    }
                    tvContent.setText(Dig88Utils.upperFirstLatter(content));
                }
            };
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ContactBean.DataBean>() {
                @Override
                public void onItemClick(View view, ContactBean.DataBean item, int position) {
                    String chat = item.getChat();
                    if (!TextUtils.isEmpty(chat) && chat.startsWith("http")) {
                        startWeb(chat);
                    } else if (item.getChatType().equals("Affiliate Signup")) {
                        startActivity(new Intent(mContext, AffiliateRegisterActivity.class));
                    }
                }
            });
            rcContent.setLayoutManager(new LinearLayoutManager(mContext));
            rcContent.setAdapter(adapter);
            presenter.getContactData();
        }
    }

    private void startWeb(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(intent);
    }

    @OnClick({R.id.ll_parent})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_parent:
                if (!TextUtils.isEmpty(getMsgCount())) {
                    startActivity(new Intent(mContext, MsgBoxActivity.class));
                }
                break;
        }
    }

    public void onGetContactData(List<ContactBean.DataBean> list) {
        adapter.setData(list);
    }

    @Override
    public void showMsgCount(String msgCount) {
        super.showMsgCount(msgCount);
        setTvMsgCount(msgCount);
    }

    private void setTvMsgCount(String msg) {
        if (!msg.equals("0")) {
            tvMsgCount.setVisibility(View.VISIBLE);
            tvMsgCount.setText(msg);
        } else {
            tvMsgCount.setVisibility(View.GONE);
        }
    }
}
