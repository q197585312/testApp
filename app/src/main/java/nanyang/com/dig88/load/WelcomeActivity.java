package nanyang.com.dig88.load;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by xToney on 2015/12/24.
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> {
    private static final int[] drawableIds = new int[]{R.mipmap.welcome1, R.mipmap.welcome2, R.mipmap.welcome3};
    @Bind(R.id.vp_guide)
    ViewPager vpGuide;
    @Bind(R.id.viewPagerContainer)
    RelativeLayout viewPagerContainer;
    @Bind(R.id.rl_parent)
    RelativeLayout rlParent;
    @Bind(R.id.img_bg)
    ImageView imgBg;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new WelcomePresenter(this));//声明本页面架构 MVP中的P 逻辑处理放P  页面只处理 控件的展示
        presenter.getNetIp();
        presenter.handleAffiliateId();
        presenter.handleNewVersion();
        if (BuildConfig.FLAVOR.equals("kbet3") || BuildConfig.FLAVOR.equals("ttwin168") || BuildConfig.FLAVOR.equals("club988")) {
            if (!BuildConfig.FLAVOR.equals("kbet3")) {
                rlParent.setBackgroundResource(0);
                imgBg.setBackgroundResource(0);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imgBg.getLayoutParams();
                params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                imgBg.setLayoutParams(params);
                if (BuildConfig.FLAVOR.equals("club988")){
                    rlParent.setBackgroundColor(Color.BLACK);
                    imgBg.setImageResource(R.mipmap.login_logo_1);
                }else {
                    imgBg.setImageResource(R.mipmap.login_logo);
                }
            }
            viewPagerContainer.setVisibility(View.GONE);
            imgBg.setVisibility(View.VISIBLE);
        } else {
            imgBg.setVisibility(View.GONE);
            viewPagerContainer.setVisibility(View.VISIBLE);
            presenter.initViewPager(viewPagerContainer, vpGuide, drawableIds);
        }
    }

}
